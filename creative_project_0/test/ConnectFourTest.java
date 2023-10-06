

public class ConnectFourTest {
    
    // Fields
    ConnectFour game;

    @BeforeEach
    public setUp() {
        game = new ConnectFour();
    }

    @Test
    public testInstructions() {
        System.out.println(game.instructions());
    }
}