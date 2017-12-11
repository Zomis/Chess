package net.zomis.chess.core;

public class ChessTile {

    private final ChessPosition position;
    private Player player;
    private Piece piece;

    public ChessTile(int x, int y) {
        this.position = new ChessPosition(x, y);
    }

    @Override
    public String toString() {
        String pl = player == null ? "_" : player.toString();
        String piece = this.piece == null ? "_" : this.piece.toString();
        return pl + piece;
    }

    public void set(Player owner, Piece piece) {
        this.player = owner;
        this.piece = piece;
    }

    public Player getOwner() {
        return player;
    }

    public Piece getPiece() {
        return piece;
    }

    public ChessPosition getPosition() {
        return position;
    }

    public void clear() {
        this.player = null;
        this.piece = null;
    }

    public void set(ChessTile tile) {
        this.set(tile.getOwner(), tile.getPiece());
    }

    public String getFullName() {
        if (player == null || piece == null) {
            return "EMPTY";
        }
        return player.name() + "_" + piece.name();
    }
}
