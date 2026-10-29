package edu.umg.controlgastos.datos

import edu.umg.controlgastos.modelo.Movimiento

// suspend: la función puede tardar, por eso se llama desde una corrutina
interface MovimientoRepository {
    suspend fun agregar(movimiento: Movimiento): Movimiento
    suspend fun listar(): List<Movimiento>
    suspend fun eliminar(id: Int): Boolean
}