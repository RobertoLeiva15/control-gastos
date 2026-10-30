package edu.umg.controlgastos.datos

import edu.umg.controlgastos.modelo.Movimiento

// El contrato: QUÉ se puede hacer con los movimientos (no CÓMO).

interface MovimientoRepository {
    suspend fun agregar(movimiento: Movimiento): Movimiento
    suspend fun listar(): List<Movimiento>
    suspend fun eliminar(id: Int): Boolean

    // NUEVO: reemplaza el movimiento que tenga el mismo id; false si no existe
    suspend fun actualizar(movimiento: Movimiento): Boolean
}