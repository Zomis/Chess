package net.zomis.chess;

public class MoveEngine {

    private final ChessBoard board;

    public MoveEngine(ChessBoard board) {
        this.board = board;
    }

    public Iterable<ChessPosition> getAllowedMoves(ChessTile tile) {
        switch (tile.getPiece()) {
            case PAWN:
        }
        throw new UnsupportedOperationException();
    }

    public boolean moveValid(ChessTile tile, ChessPosition pos) {
        if (tile == null) {
            return false;
            // throw new NullPointerException("tile cannot be null");
        }
        switch (tile.getPiece()) {
            case PAWN:
                return true;
            default:
                throw new UnsupportedOperationException(tile.getPiece() + " cannot move yet");
        }
    }


}
