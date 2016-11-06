package net.zomis.chess;

import java.util.stream.IntStream;

import static net.zomis.chess.Piece.PAWN;

public class ChessBoard {

    private static final int SIZE = 8;
    private final ChessTile[][] tiles;
    private Player current = Player.WHITE;
    private MoveEngine moveEngine = new MoveEngine(this);

    public ChessBoard() {
        this.tiles = new ChessTile[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                tiles[y][x] = new ChessTile(x, y);
            }
        }
    }

    public void move(String move) {
        System.out.println("Move: " + move);
        ChessPosition destination = ChessPosition.fromMoveNotation(move);
        ChessTile source = null;
        if (move.length() == 2) {
            // Moving a pawn
            source = IntStream.range(0, SIZE)
                .mapToObj(i -> new ChessPosition(destination.getX(), i))
                .map(this::tile)
                .filter(ch -> ch.getOwner() == current)
                .filter(ch -> ch.getPiece() == PAWN)
                .collect(new UniqueCollector<>())
                .orElseThrow(() -> new IllegalStateException("Unexpected number of pawns found for move " + move));
        }
        if (moveEngine.moveValid(source, destination)) {
            this.move(source, destination);
        } else {
            throw new IllegalStateException("Illegal move: " + move);
        }

        switchTurn();
    }

    private void switchTurn() {
        current = current.opponent();
    }

    public ChessBoard moves(String moves) {
        ChessBoard board = ChessBoard.standardGame();
        for (String move : moves.split(" ")) {
            board.move(move);
        }
        return board;
    }

    public ChessTile tile(char column, int row) {
        if (row <= 0 || row > SIZE || column < 'A' || column >= 'A' + SIZE) {
            throw new IndexOutOfBoundsException("Column " + column + " and row " +
                row + " is out of bounds for size" + SIZE);
        }
        return tiles[row - 1][column - 'A'];
    }

    public static ChessBoard standardGame() {
        ChessBoard board = new ChessBoard();
        for (int i = 0; i < SIZE; i++) {
            board.tile(col(i), 2).set(Player.WHITE, PAWN);
            board.tile(col(i), SIZE - 1).set(Player.BLACK, PAWN);
        }

        for (Player pl : Player.values()) {
            int row = pl == Player.WHITE ? 1 : SIZE;
            board.tile('A', row).set(pl, Piece.ROOK);
            board.tile('B', row).set(pl, Piece.KNIGHT);
            board.tile('C', row).set(pl, Piece.BISHOP);
            board.tile('D', row).set(pl, Piece.QUEEN);
            board.tile('E', row).set(pl, Piece.KING);
            board.tile('F', row).set(pl, Piece.BISHOP);
            board.tile('G', row).set(pl, Piece.KNIGHT);
            board.tile('H', row).set(pl, Piece.ROOK);
        }

        return board;
    }

    private static char col(int i) {
        return (char) ('A' + i);
    }

    public void print(Player perspective) {
        for (int y = 1; y <= SIZE; y++) {
            for (int x = 1; x <= SIZE; x++) {
                ChessTile tile = tileFromPerspective(x, y, perspective);
                String str = tile.toString();
                System.out.print(str);
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    private ChessTile tileFromPerspective(int x, int y, Player perspective) {
        int cx = x - 1;
        int cy = y - 1;
        if (perspective == Player.WHITE) {
            cy = SIZE - y;
        }
        if (perspective == Player.BLACK) {
            cx = SIZE - x;
        }
        if (!(new ChessPosition(cx, cy).onBoard())) {
            throw new IllegalArgumentException("not on board: " + x + ", " + y + " perspective " + perspective
            + ". values " + cx + ", " + cy);
        }
        return tiles[cy][cx];
    }

    public ChessTile tile(ChessPosition pos) {
        return this.tiles[pos.getY()][pos.getX()];
    }

    void move(ChessTile tile, ChessPosition pos) {
        System.out.println("Move " + tile.getFullName() + " at " + tile.getPosition() + " to " + pos);
        tile(pos).set(tile);
        tile.clear();
    }
}
