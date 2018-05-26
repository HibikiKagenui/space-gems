package entity.gem;

import javafx.scene.image.Image;

public class Jade extends Gem {
    public Jade() {
        super();
        image = new Image(getClass().getResource("assets\\jade sm.png").toExternalForm());
        type = "Jade";
        score = 60;
    }
}
