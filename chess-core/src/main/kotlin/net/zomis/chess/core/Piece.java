package net.zomis.chess.core;

public enum Piece {

    KING('K'), QUEEN('Q'), ROOK('R'), BISHOP('B'), KNIGHT('N'), PAWN('P');

    private final char ch;

    Piece(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }
}
