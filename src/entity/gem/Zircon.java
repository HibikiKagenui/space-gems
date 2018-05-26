package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Zircon extends Gem {
    public Zircon() {
        image = new Image(getClass().getResource("assets\\zircon sm.png").toExternalForm());
    }

    public Zircon(GraphicsContext gc) {
        super(gc);
        image = new Image(getClass().getResource("assets\\zircon sm.png").toExternalForm());
        type = "Zircon";
        score = 75;
    }
}
