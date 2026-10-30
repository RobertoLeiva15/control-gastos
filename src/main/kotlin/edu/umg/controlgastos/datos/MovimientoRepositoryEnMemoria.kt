package edu.umg.controlgastos.datos

import edu.umg.controlgastos.modelo.Movimiento

class MovimientoRepositoryEnMemoria : MovimientoRepository {

    private val movimientos = mutableListOf<Movimiento>()
    private var siguienteId = 1

    override suspend fun agregar(movimiento: Movimiento): Movimiento {
        // copy crea un Movimiento igual pero con el id asignado
        val guardado = movimiento.copy(id = siguienteId++)
        movimientos.add(guardado)
        return guardado
    }

    // toList devuelve una copia, así nadie modifica la lista original
    override suspend fun listar(): List<Movimiento> = movimientos.toList()

    // removeAll devuelve true si borró algo
    override suspend fun eliminar(id: Int): Boolean =
        movimientos.removeAll { it.id == id }

    // indexOfFirst devuelve la posición del primero que cumpla, o -1 si no hay
    override suspend fun actualizar(movimiento: Movimiento): Boolean {
        val posicion = movimientos.indexOfFirst { it.id == movimiento.id }
        if (posicion == -1) return false
        movimientos[posicion] = movimiento
        return true
    }
}