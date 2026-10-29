package edu.umg.controlgastos.modelo

enum class TipoMovimiento(val etiqueta: String) {
    INGRESO("Ingreso"),
    EGRESO("Egreso");

    // Lo que se muestra en pantalla en lugar de INGRESO o EGRESO
    override fun toString(): String = etiqueta
}