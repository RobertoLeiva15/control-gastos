package edu.umg.controlgastos.servicio

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

// Este archivo es de tipo File: tiene funciones sueltas (sin clase), como los métodos static de Java.
// Usamos Calendar y no java.time, porque java.time no existe en Android antes de la API 26
// y nuestra app soporta desde la API 24.

private val NOMBRES_MESES = listOf(
    "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
    "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
)

// La fecha de hoy en formato yyyy-MM-dd, por ejemplo "2026-10-29"
fun fechaDeHoy(): String =
    SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Calendar.getInstance().time)

// El mes de hoy en formato yyyy-MM, por ejemplo "2026-10"
fun mesActual(): String = fechaDeHoy().take(7)

// Saca el mes de una fecha: "2026-10-29" -> "2026-10"
fun mesDe(fecha: String): String = fecha.take(7)

// Avanza o retrocede meses: moverMes("2026-01", -1) -> "2025-12"
// Calendar se encarga solo de cambiar de año
fun moverMes(mes: String, cantidad: Int): String {
    val partes = mes.split("-")
    val calendario = Calendar.getInstance()
    calendario.clear()
    calendario.set(partes[0].toInt(), partes[1].toInt() - 1, 1) // en Calendar enero es 0
    calendario.add(Calendar.MONTH, cantidad)
    return String.format(
        Locale.US, "%04d-%02d",
        calendario.get(Calendar.YEAR), calendario.get(Calendar.MONTH) + 1
    )
}

// Nombre para mostrar en pantalla: "2026-10" -> "Octubre 2026"
fun nombreDelMes(mes: String): String {
    val partes = mes.split("-")
    return "${NOMBRES_MESES[partes[1].toInt() - 1]} ${partes[0]}"
}