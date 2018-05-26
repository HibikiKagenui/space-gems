package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rutile extends Gem {
    public Rutile() {
        image = new Image(getClass().getResource("assets\\rutile sm.png").toExternalForm());
    }

    public Rutile(GraphicsContext gc) {
        super(gc);
        image = new Image(getClass().getResource("assets\\rutile sm.png").toExternalForm());
        type = "Rutile";
        score = 40;
    }
}
