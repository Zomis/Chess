package net.zomis.chess.core

class ChessPosition(val x: Int, val y: Int) {

    val column: Char
        get() = ('A' + x).toChar()

    val row: Int
        get() = y + 1

    fun offset(x: Int, y: Int): ChessPosition {
        return ChessPosition(this.x + x, this.y + y)
    }

    override fun toString(): String {
        return column.toString() + row
    }

    fun onBoard(): Boolean {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE
    }

    companion object {
        private val SIZE = 8

        fun fromMoveNotation(move: String): ChessPosition {
            var move = move
            move = move.toUpperCase()
            val col = move[move.length - 2]
            val row = move[move.length - 1].toInt()
            return ChessPosition(col - 'A', row - 1)
        }
    }
}
