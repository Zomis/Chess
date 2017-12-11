package net.zomis.chess.core

enum class Piece private constructor(private val ch: Char) {

    KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N'), PAWN('P');

    override fun toString(): String {
        return ch.toString()
    }
}
