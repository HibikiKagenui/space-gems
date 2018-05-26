package controller;

import entity.Plane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

/**
 * Class implement EventHandler
 * Berisi method yang mengatur pengaturan keyevent
 * Sebuah scene dapat memasukkan instance class ini ke method setOnKeyReleased nya untuk mendapatkan konfigurasi tombolnya
 * Berisi atribut Plane karena EventHandler ini memanipulasi arah gerak Plane
 */
public class KeyReleased implements EventHandler<KeyEvent> {
    private Plane plane;

    public KeyReleased(Plane plane) {
        this.plane = plane;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                plane.setUP(false);
                break;
            case DOWN:
                plane.setDOWN(false);
                break;
            case LEFT:
                plane.setLEFT(false);
                break;
            case RIGHT:
                plane.setRIGHT(false);
                break;
            case SHIFT:
                plane.slowDown();
                break;
        }
    }
}
