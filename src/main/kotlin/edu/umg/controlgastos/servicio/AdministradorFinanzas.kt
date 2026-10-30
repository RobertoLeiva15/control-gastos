package edu.umg.controlgastos.servicio

import edu.umg.controlgastos.biblico.GeneradorVersiculos
import edu.umg.controlgastos.biblico.Versiculo
import edu.umg.controlgastos.datos.MovimientoRepository
import edu.umg.controlgastos.modelo.Categoria
import edu.umg.controlgastos.modelo.Movimiento
import edu.umg.controlgastos.modelo.ResumenMes
import edu.umg.controlgastos.modelo.TipoMovimiento

// Si hay error, versiculo viene null; si todo salió bien, error viene null
data class ResultadoRegistro(val error: String? = null, val versiculo: Versiculo? = null)

class AdministradorFinanzas(private val repositorio: MovimientoRepository) {

    private val generador = GeneradorVersiculos()

    // Devuelve null si el movimiento es válido, o el mensaje del primer error
    fun validar(m: Movimiento): String? {
        if (m.descripcion.isBlank()) return "La descripción no puede estar vacía"
        if (m.monto <= 0) return "El monto debe ser mayor que 0"
        if (m.categoria.tipo != m.tipo) return "La categoría no corresponde al tipo de movimiento"
        if (!FORMATO_FECHA.matches(m.fecha)) return "La fecha debe tener el formato yyyy-MM-dd"
        return null
    }

    suspend fun registrar(m: Movimiento): ResultadoRegistro {
        val error = validar(m)
        if (error != null) return ResultadoRegistro(error = error)
        repositorio.agregar(m)
        return ResultadoRegistro(versiculo = generador.siguiente())
    }

    suspend fun listar(): List<Movimiento> = repositorio.listar()

    suspend fun totalIngresos(): Double = sumar(TipoMovimiento.INGRESO)

    suspend fun totalEgresos(): Double = sumar(TipoMovimiento.EGRESO)

    // Saldo disponible = ingresos menos egresos
    suspend fun saldo(): Double = totalIngresos() - totalEgresos()

    // Alerta si ya gastaste el 80 % o más de lo que has ingresado
    suspend fun alerta(): String? {
        val ingresos = totalIngresos()
        val egresos = totalEgresos()
        if (egresos > ingresos) return "⚠ Estás gastando más de lo que ingresaste"
        if (ingresos > 0 && egresos >= ingresos * 0.8) return "⚠ Ya usaste el 80 % de tus ingresos"
        return null
    }


    // valida y guarda los cambios de un movimiento existente.
    // Devuelve null si salió bien, o el mensaje de error
    suspend fun actualizar(m: Movimiento): String? {
        val error = validar(m)
        if (error != null) return error
        if (!repositorio.actualizar(m)) return "No se encontró el movimiento"
        return null
    }

    // busca un movimiento por su id (para poder editarlo)
    suspend fun buscarPorId(id: Int): Movimiento? =
        repositorio.listar().find { it.id == id }

    // Solo los movimientos de un mes ("2026-10"), del más nuevo al más viejo
    suspend fun movimientosDelMes(mes: String): List<Movimiento> =
        repositorio.listar()
            .filter { mesDe(it.fecha) == mes }
            .sortedWith(compareByDescending<Movimiento> { it.fecha }.thenByDescending { it.id })

    // El resumen de un mes; si no se indica, usa el mes actual
    suspend fun resumenDelMes(mes: String = mesActual()): ResumenMes {
        val lista = movimientosDelMes(mes)
        return ResumenMes(
            mes = mes,
            ingresos = lista.filter { it.tipo == TipoMovimiento.INGRESO }.sumOf { it.monto },
            egresos = lista.filter { it.tipo == TipoMovimiento.EGRESO }.sumOf { it.monto },
            cantidadMovimientos = lista.size
        )
    }

    // Cuánto se movió por categoría en un mes, de mayor a menor.
    // groupBy junta los movimientos por categoría y mapValues suma cada grupo.
    suspend fun totalesPorCategoria(
        mes: String,
        tipo: TipoMovimiento = TipoMovimiento.EGRESO
    ): Map<Categoria, Double> =
        movimientosDelMes(mes)
            .filter { it.tipo == tipo }
            .groupBy { it.categoria }
            .mapValues { (_, grupo) -> grupo.sumOf { it.monto } }
            .toList()
            .sortedByDescending { it.second }
            .toMap()

    // Los meses que tienen datos, del más nuevo al más viejo (el mes actual siempre aparece)
    suspend fun mesesConMovimientos(): List<String> =
        (repositorio.listar().map { mesDe(it.fecha) } + mesActual())
            .distinct()
            .sortedDescending()

    // filter deja solo los movimientos del tipo pedido y sumOf suma sus montos
    private suspend fun sumar(tipo: TipoMovimiento): Double =
        repositorio.listar().filter { it.tipo == tipo }.sumOf { it.monto }

    private companion object {
        // Regex: 4 dígitos, guion, 2 dígitos, guion, 2 dígitos
        val FORMATO_FECHA = Regex("""\d{4}-\d{2}-\d{2}""")
    }
}

fun formatearQuetzales(monto: Double): String = "Q %.2f".format(monto)