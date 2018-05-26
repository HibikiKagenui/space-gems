package view;

import controller.Gameplay;
import global.Constant;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Class memuat view gameplay
 * Berisi canvas dan memanggil runnable dari controller untuk menjalankan proses permainannya
 */
public class GameplayView {
    private String username;
    private String controls;
    private String difficulty;

    GameplayView(String username, String controls, String difficulty) {
        this.username = username;
        this.controls = controls;
        this.difficulty = difficulty;
    }

    void show(Stage stage) {
        ///////////////////////////////////////////////////////////////////////////////////////
        // THE CANVAS AND GC //////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        Canvas area = new Canvas(Constant.WIDTH, Constant.HEIGHT);
        GraphicsContext gc = area.getGraphicsContext2D();

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET SCENE //////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        Group root = new Group(area);
        Scene scene = new Scene(root, Constant.WIDTH, Constant.HEIGHT);

        // tentukan frekuensi spawn batu sesuai difficulty yang dipilih pemain
        int spawnFrequency = 0;
        switch (difficulty) {
            case "Easy":
                // batu akan spawn setiap setengah detik sekali
                spawnFrequency = 30;
                break;
            case "Medium":
                // batu akan spawn setiap seperempat detik sekali
                spawnFrequency = 15;
                break;
            case "Hard":
                // batu akan spawn setiap seperenam detik sekali
                spawnFrequency = 10;
                break;
        }

        // buat instance runnable gameplay
        Gameplay r = new Gameplay(gc, username, controls, spawnFrequency, stage, scene);

        // set stage
        stage.setScene(scene);
        stage.centerOnScreen();

        // jalankan thread gameplay
        new Thread(r).start();
    }
}