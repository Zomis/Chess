package net.zomis.chess.core;

public enum Player {

    BLACK('B'), WHITE('W');

    private final char ch;

    Player(char ch) {
        this.ch = ch;
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }

    public Player opponent() {
        return this == BLACK ? WHITE : BLACK;
    }
}
