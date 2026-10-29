package edu.umg.controlgastos.biblico

data class Versiculo(val texto: String, val cita: String) {
    override fun toString(): String = "\"$texto\"\n— $cita"
}