package net.zomis.chess;

public class ChessPosition {

    private static final int SIZE = 8;

    private final int x;
    private final int y;

    public ChessPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static ChessPosition fromMoveNotation(String move) {
        move = move.toUpperCase();
        char col = move.charAt(move.length() - 2);
        int row = Character.digit(move.charAt(move.length() - 1), 10);
        return new ChessPosition(col - 'A', row - 1);
    }

    public char getColumn() {
        return (char) ('A' + x);
    }

    public ChessPosition offset(int x, int y) {
        return new ChessPosition(this.x + x, this.y + y);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return String.valueOf(getColumn()) + getRow();
    }

    public int getRow() {
        return getY() + 1;
    }

    public boolean onBoard() {
        return x >= 0 && x < SIZE && y >= 0 && y < SIZE;
    }
}
