package entity.gem;

import javafx.scene.image.Image;

public class Zircon extends Gem {
    public Zircon() {
        super();
        image = new Image(getClass().getResource("assets\\zircon sm.png").toExternalForm());
        type = "Zircon";
        score = 75;
    }
}
