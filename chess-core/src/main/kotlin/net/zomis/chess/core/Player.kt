package net.zomis.chess.core

enum class Player private constructor(private val ch: Char) {

    BLACK('B'), WHITE('W');

    override fun toString(): String {
        return ch.toString()
    }

    fun opponent(): Player {
        return if (this == BLACK) WHITE else BLACK
    }
}
