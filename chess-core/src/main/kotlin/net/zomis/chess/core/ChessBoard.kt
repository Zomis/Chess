package net.zomis.chess.core

class ChessBoard {

    val SIZE = 8

    private val tiles: Array<Array<ChessTile>>
    private var current = Player.WHITE
    private val moveEngine = MoveEngine(this)

    init {
        this.tiles = Array(SIZE) { y ->
            Array(SIZE) { x ->
                ChessTile(x, y)
            }
        }
    }

    fun move(move: String) {
        println("Move: " + move)
        val destination = ChessPosition.fromMoveNotation(move)
        var source: ChessTile? = null
        if (move.length == 2) {
            // Moving a pawn
            val list = (0 until SIZE)
                    .map({ i -> ChessPosition(destination.x, i) })
                    .map({ this.tile(it) })
                    .filter({ ch -> ch.owner == current })
                    .filter({ ch -> ch.piece == Piece.PAWN })
                    .toList()
            if (list.size == 1) {
                source = list[0]
            } else {
                throw IllegalStateException("Unexpected number of pawns found for move " + move)
            }
        }
        if (moveEngine.moveValid(source, destination)) {
            this.move(source, destination)
        } else {
            throw IllegalStateException("Illegal move: " + move)
        }

        switchTurn()
    }

    private fun switchTurn() {
        current = current.opponent()
    }

    fun moves(moves: String): ChessBoard {
        val board = ChessBoard.standardGame()
        for (move in moves.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            board.move(move)
        }
        return board
    }

    fun tile(column: Char, row: Int): ChessTile {
        if (row <= 0 || row > SIZE || column < 'A' || column >= 'A' + SIZE) {
            throw IndexOutOfBoundsException("Column " + column + " and row " +
                    row + " is out of bounds for size" + SIZE)
        }
        return tiles[row - 1][column - 'A']
    }

    fun print(perspective: Player) {
        for (y in 1..SIZE) {
            for (x in 1..SIZE) {
                val tile = tileFromPerspective(x, y, perspective)
                val str = tile.toString()
                print(str)
                print(' ')
            }
            println()
        }
    }

    private fun tileFromPerspective(x: Int, y: Int, perspective: Player): ChessTile {
        var cx = x - 1
        var cy = y - 1
        if (perspective == Player.WHITE) {
            cy = SIZE - y
        }
        if (perspective == Player.BLACK) {
            cx = SIZE - x
        }
        if (!ChessPosition(cx, cy).onBoard()) {
            throw IllegalArgumentException("not on board: " + x + ", " + y + " perspective " + perspective
                    + ". values " + cx + ", " + cy)
        }
        return tiles[cy][cx]
    }

    fun tile(pos: ChessPosition): ChessTile {
        return this.tiles[pos.y][pos.x]
    }

    internal fun move(tile: ChessTile?, pos: ChessPosition) {
        println("Move " + tile!!.fullName + " at " + tile.position + " to " + pos)
        tile(pos).set(tile)
        tile.clear()
    }

    companion object {

        private val SIZE = 8

        fun standardGame(): ChessBoard {
            val board = ChessBoard()
            for (i in 0 until SIZE) {
                board.tile(col(i), 2).set(Player.WHITE, Piece.PAWN)
                board.tile(col(i), SIZE - 1).set(Player.BLACK, Piece.PAWN)
            }

            for (pl in Player.values()) {
                val row = if (pl == Player.WHITE) 1 else SIZE
                board.tile('A', row).set(pl, Piece.ROOK)
                board.tile('B', row).set(pl, Piece.KNIGHT)
                board.tile('C', row).set(pl, Piece.BISHOP)
                board.tile('D', row).set(pl, Piece.QUEEN)
                board.tile('E', row).set(pl, Piece.KING)
                board.tile('F', row).set(pl, Piece.BISHOP)
                board.tile('G', row).set(pl, Piece.KNIGHT)
                board.tile('H', row).set(pl, Piece.ROOK)
            }

            return board
        }

        private fun col(i: Int): Char {
            return ('A' + i).toChar()
        }
    }
}
