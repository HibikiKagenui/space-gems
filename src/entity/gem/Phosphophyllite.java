package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Phosphophyllite extends Gem {
    public Phosphophyllite() {
        super();
        image = new Image(getClass().getResource("assets\\phosphophyllite sm.png").toExternalForm());
        type = "Phosphophyllite";
        score = 35;
    }
}
