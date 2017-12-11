package net.zomis.chess

import net.zomis.chess.core.ChessBoard
import net.zomis.chess.core.Player

fun main(args: Array<String>) {
    val moves = "d4 e6 e3 Nc6 Nc3"
    val board = ChessBoard.standardGame().moves(moves)
    board.print(Player.BLACK)
}
//        String game = "0pNa3Hlm";
//        LichessFetch fetch = new LichessFetch();
//        fetch.fetch("");

