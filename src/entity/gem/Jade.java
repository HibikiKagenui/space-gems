package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Jade extends Gem {
    public Jade() {
        image = new Image(getClass().getResource("assets\\jade sm.png").toExternalForm());
    }

    public Jade(GraphicsContext gc) {
        super(gc);
        image = new Image(getClass().getResource("assets\\jade sm.png").toExternalForm());
        type = "Jade";
        score = 60;
    }
}
