package edu.umg.controlgastos.biblico

class GeneradorVersiculos {

    private var ultimo = -1

    // El do-while repite el sorteo mientras salga el mismo versículo que la vez anterior
    fun siguiente(): Versiculo {
        var indice: Int
        do {
            indice = LISTA.indices.random()
        } while (indice == ultimo)
        ultimo = indice
        return LISTA[indice]
    }

    private companion object {

        val LISTA = listOf(
            Versiculo("Todo lo puedo en Cristo que me fortalece.", "Filipenses 4:13"),
            Versiculo("Jehová es mi pastor; nada me faltará.", "Salmos 23:1"),
            Versiculo("Fíate de Jehová de todo tu corazón, y no te apoyes en tu propia prudencia.", "Proverbios 3:5"),
            Versiculo("Mira que te mando que te esfuerces y seas valiente; no temas ni desmayes, porque Jehová tu Dios estará contigo en dondequiera que vayas.", "Josué 1:9"),
            Versiculo("Porque yo sé los pensamientos que tengo acerca de vosotros, dice Jehová, pensamientos de paz, y no de mal, para daros el fin que esperáis.", "Jeremías 29:11"),
            Versiculo("No temas, porque yo estoy contigo; no desmayes, porque yo soy tu Dios que te esfuerzo; siempre te ayudaré.", "Isaías 41:10"),
            Versiculo("Encomienda a Jehová tus obras, y tus pensamientos serán afirmados.", "Proverbios 16:3"),
            Versiculo("El principio de la sabiduría es el temor de Jehová; los insensatos desprecian la sabiduría y la enseñanza.", "Proverbios 1:7"),
            Versiculo("Lámpara es a mis pies tu palabra, y lumbrera a mi camino.", "Salmos 119:105"),
            Versiculo("Mas buscad primeramente el reino de Dios y su justicia, y todas estas cosas os serán añadidas.", "Mateo 6:33"),
            Versiculo("Porque donde esté vuestro tesoro, allí estará también vuestro corazón.", "Mateo 6:21"),
            Versiculo("El que es fiel en lo muy poco, también en lo más es fiel.", "Lucas 16:10"),
            Versiculo("Los pensamientos del diligente ciertamente tienden a la abundancia.", "Proverbios 21:5"),
            Versiculo("Las riquezas de vanidad disminuirán; pero el que recoge con mano laboriosa las aumenta.", "Proverbios 13:11"),
            Versiculo("Honra a Jehová con tus bienes, y con las primicias de todos tus frutos.", "Proverbios 3:9"),
            Versiculo("Mi Dios, pues, suplirá todo lo que os falta conforme a sus riquezas en gloria en Cristo Jesús.", "Filipenses 4:19")
        )
    }
}