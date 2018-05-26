package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class YellowDiamond extends Gem {
    public YellowDiamond() {
        super();
        image = new Image(getClass().getResource("assets\\yellow diamond sm.png").toExternalForm());
        type = "Yellow Diamond";
        score = 100;
    }
}
