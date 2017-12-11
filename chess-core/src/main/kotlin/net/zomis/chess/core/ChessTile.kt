package net.zomis.chess.core

class ChessTile(x: Int, y: Int) {

    val position: ChessPosition
    var owner: Player? = null
        private set
    var piece: Piece? = null
        private set

    val fullName: String
        get() = if (owner == null || piece == null) {
            "EMPTY"
        } else owner!!.name + "_" + piece!!.name

    init {
        this.position = ChessPosition(x, y)
    }

    override fun toString(): String {
        val pl = if (owner == null) "_" else owner!!.toString()
        val piece = if (this.piece == null) "_" else this.piece!!.toString()
        return pl + piece
    }

    operator fun set(owner: Player?, piece: Piece?) {
        this.owner = owner
        this.piece = piece
    }

    fun clear() {
        this.owner = null
        this.piece = null
    }

    fun set(tile: ChessTile) {
        this[tile.owner] = tile.piece
    }
}
