package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class GameOver extends Gem {
    public GameOver() {
        image = new Image(getClass().getResource("assets\\game over sm.png").toExternalForm());
    }

    public GameOver(GraphicsContext gc) {
        super(gc);
        image = new Image(getClass().getResource("assets\\game over sm.png").toExternalForm());
        type = "Game Over";
        score = 0;
    }
}
