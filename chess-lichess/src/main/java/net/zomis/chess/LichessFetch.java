package net.zomis.chess;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class LichessFetch {

    public String fetchMovesForGame(String game) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode tree = mapper.readTree(new URL("https://en.lichess.org/api/game/" + game + "?with_moves=1"));
        JsonNode movesNode = tree.get("moves");
        return movesNode.asText();
    }

}
