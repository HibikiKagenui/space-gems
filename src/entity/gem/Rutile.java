package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Rutile extends Gem {
    public Rutile() {
        super();
        image = new Image(getClass().getResource("assets\\rutile sm.png").toExternalForm());
        type = "Rutile";
        score = 40;
    }
}
