import net.zomis.chess.core.ChessBoard
import org.junit.jupiter.api.Test

class MoveTFTest {

    @Test
    fun sfa() {
        val board = ChessBoard.standardGame().moves("d4 e6 e3 Nc6 Nc3")
        // d4 e6 e3 are pawns (e6 black). c6 is black Knight and c3 is white Knight

    }

}