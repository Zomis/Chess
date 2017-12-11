package net.zomis.chess;

public class Main {

    public static void main(String[] args) {
//        String game = "0pNa3Hlm";
//        LichessFetch fetch = new LichessFetch();
//        fetch.fetch("");
        String moves = "d4 e6 e3 Nc6 Nc3";
        ChessBoard board = ChessBoard.standardGame().moves(moves);
        board.print(Player.BLACK);
    }

}
