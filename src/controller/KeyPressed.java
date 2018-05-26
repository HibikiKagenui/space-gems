package controller;

import entity.Plane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.LeaderboardView;

/**
 * Class implement EventHandler
 * Berisi method yang mengatur pengaturan keyevent
 * Sebuah scene dapat memasukkan instance class ini ke method setOnKeyPressed nya untuk mendapatkan konfigurasi tombolnya
 * Berisi atribut Plane karena EventHandler ini memanipulasi arah gerak Plane
 * Atribut stage untuk merubah stage menjadi scene LeaderboardView
 * Atribut class runnable Gameplay agar bisa menghentikan runnable nya
 */
public class KeyPressed implements EventHandler<KeyEvent> {
    private Plane plane;
    private Stage stage;
    private Gameplay r;

    public KeyPressed(Plane plane, Stage stage, Gameplay r) {
        this.plane = plane;
        this.stage = stage;
        this.r = r;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
                plane.setUP(true);
                break;
            case DOWN:
                plane.setDOWN(true);
                break;
            case LEFT:
                plane.setLEFT(true);
                break;
            case RIGHT:
                plane.setRIGHT(true);
                break;
            case SHIFT:
                plane.speedUp();
                break;
            case SPACE:
                new LeaderboardView().show(stage);
                r.terminate();
                break;
        }
    }
}
