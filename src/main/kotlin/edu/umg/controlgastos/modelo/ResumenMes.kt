package edu.umg.controlgastos.modelo

// Los 4 posibles estados de un mes, cada uno con su mensaje para la pantalla
enum class NivelAlerta(val mensaje: String) {
    SIN_DATOS("Aún no hay movimientos este mes"),
    TRANQUILO("¡Vas muy bien! Sigue así"),
    CUIDADO("⚠ Cuidado: ya usaste el 80 % de tus ingresos"),
    EXCEDIDO("⛔ Gastaste más de lo que ingresaste")
}

// El resumen de un mes. Solo guarda los totales;
// saldo, porcentaje y nivel se CALCULAN solos con get()
data class ResumenMes(
    val mes: String,                  // "2026-10"
    val ingresos: Double,
    val egresos: Double,
    val cantidadMovimientos: Int
) {
    val saldo: Double
        get() = ingresos - egresos

    // Qué porcentaje de los ingresos ya se gastó (para la barra de progreso)
    val porcentajeGastado: Int
        get() = when {
            ingresos > 0 -> ((egresos / ingresos) * 100).toInt()
            egresos > 0 -> 100 // hay gastos pero no ingresos
            else -> 0
        }

    val nivel: NivelAlerta
        get() = when {
            cantidadMovimientos == 0 -> NivelAlerta.SIN_DATOS
            // 0.005 = medio centavo de margen por los errores de redondeo de Double
            egresos - ingresos > 0.005 -> NivelAlerta.EXCEDIDO
            ingresos > 0 && egresos >= ingresos * 0.8 -> NivelAlerta.CUIDADO
            else -> NivelAlerta.TRANQUILO
        }
}