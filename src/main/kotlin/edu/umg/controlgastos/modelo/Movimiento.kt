package edu.umg.controlgastos.modelo

// id = 0 significa que todavía no se ha guardado
data class Movimiento(
    val id: Int = 0,
    val descripcion: String,
    val monto: Double,
    val tipo: TipoMovimiento,
    val categoria: Categoria,
    val fecha: String
)