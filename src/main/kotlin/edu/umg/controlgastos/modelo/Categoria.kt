package edu.umg.controlgastos.modelo

enum class Categoria(val nombreVisible: String, val tipo: TipoMovimiento) {
    SALARIO("Salario", TipoMovimiento.INGRESO),
    NEGOCIO("Negocio propio", TipoMovimiento.INGRESO),
    REGALO("Regalo o mesada", TipoMovimiento.INGRESO),
    OTRO_INGRESO("Otro ingreso", TipoMovimiento.INGRESO),

    COMIDA("Comida", TipoMovimiento.EGRESO),
    TRANSPORTE("Transporte", TipoMovimiento.EGRESO),
    ESTUDIOS("Estudios", TipoMovimiento.EGRESO),
    SALUD("Salud", TipoMovimiento.EGRESO),
    DIVERSION("Diversión", TipoMovimiento.EGRESO),
    OFRENDA("Ofrenda o diezmo", TipoMovimiento.EGRESO),
    OTRO_EGRESO("Otro egreso", TipoMovimiento.EGRESO);

    override fun toString(): String = nombreVisible

    // companion object funciona como los métodos static de Java
    companion object {
        // filter se queda solo con las categorías del tipo pedido
        fun deTipo(tipo: TipoMovimiento): List<Categoria> =
            entries.filter { it.tipo == tipo }
    }
}