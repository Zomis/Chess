package net.zomis.chess.core

class MoveEngine(private val board: ChessBoard) {

    fun getAllowedMoves(tile: ChessTile): Iterable<ChessPosition> {
        when (tile.piece) {
            else -> throw UnsupportedOperationException()
        }
    }

    fun moveValid(tile: ChessTile?, pos: ChessPosition): Boolean {
        if (tile == null) {
            throw IllegalArgumentException("Tile may not be null")
        }
        when (tile.piece) {
            Piece.PAWN -> return true
            else -> throw UnsupportedOperationException("${tile.piece} cannot move yet")
        }
    }


}
