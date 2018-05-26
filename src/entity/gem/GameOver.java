package entity.gem;

import javafx.scene.image.Image;

public class GameOver extends Gem {
    public GameOver() {
        super();
        image = new Image(getClass().getResource("assets\\game over sm.png").toExternalForm());
        type = "Game Over";
        score = 0;
    }
}
