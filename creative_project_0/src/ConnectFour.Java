import java.util.*;
import java.nio.file.*;
import java.io.*;

public class ConnectFour implements AbstractStrategyGame {

    // Fields
    Board board;
    Player player1;
    Player player2;

    ConnectFour() {
        board = new Board();
        player1 = new Player();
        player2 = new Player();
    }

    public String instructions() {
        return new String(Files.readAllBytes(Paths.get("../resources/instructions.txt")));

    }

    public static void main(String[] args) {
        ConnectFour game = new ConnectFour();
        System.out.println(game.instructions());
    }
}