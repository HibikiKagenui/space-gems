package entity.gem;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class YellowDiamond extends Gem {
    public YellowDiamond() {
        image = new Image(getClass().getResource("assets\\yellow diamond sm.png").toExternalForm());
    }

    public YellowDiamond(GraphicsContext gc) {
        super(gc);
        image = new Image(getClass().getResource("assets\\yellow diamond sm.png").toExternalForm());
        type = "Yellow Diamond";
        score = 100;
    }
}
