package controller;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import view.LeaderboardView;

/**
 * Class implement EventHandler
 * Berisi method yang mengatur pengaturan keyevent
 * Sebuah scene dapat memasukkan instance class ini ke method setOnKeyPressed nya untuk mendapatkan konfigurasi tombolnya
 * Dipasangkan dengan class controller.MouseMovement sebagai konfigurasi control game dengan mouse
 * Atribut stage untuk merubah stage menjadi scene LeaderboardView ketika klik SPACE
 * Atribut class runnable Gameplay agar bisa menghentikan runnable nya ketika klik SPACE
 */
public class SpaceKeyPressed implements EventHandler<KeyEvent> {
    private Stage stage;
    private Gameplay r;

    public SpaceKeyPressed(Stage stage, Gameplay r) {
        this.stage = stage;
        this.r = r;
    }

    @Override
    public void handle(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            new LeaderboardView().show(stage);
            r.terminate();
        }
    }
}