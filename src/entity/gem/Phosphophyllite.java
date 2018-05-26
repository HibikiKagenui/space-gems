package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Phosphophyllite extends Gem {
    public Phosphophyllite() {
        image = new Image(getClass().getResource("assets\\phosphophyllite sm.png").toExternalForm());
    }

    public Phosphophyllite(GraphicsContext gc) {
        super(gc);
        image = new Image(getClass().getResource("assets\\phosphophyllite sm.png").toExternalForm());
        type = "Phosphophyllite";
        score = 35;
    }
}
