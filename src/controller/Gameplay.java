package controller;

import entity.FloatingScore;
import entity.Plane;
import entity.gem.*;
import global.Constant;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Class runnable yang menjalankan animasi gameplay
 * Selain menggerakkan pesawat player, instance class ini juga bertugas:
 * - memunculkan dan menghapus batu-batu yang berterbangan di canvas
 * - memberi control keyboard atau mouse ke scene dari view gameplayview
 * - menghitung skor total pemain
 * - menghitung jumlah dari tiap-tiap jenis batu yang didapatkan player
 * - memutar background music (bgm)
 */
public class Gameplay implements Runnable {
    private GraphicsContext gc;
    private String username;
    private int spawnFrequency;
    private Plane plane;
    private Integer newScore;

    private List<Gem> gems; // berisi batu-batu yang sedang berkeliaran di canvas
    private List<Gem> deleteGems; // berisi batu-batu yang harus dihilangkan karena sudah diambil player/melewati border window
    private List<FloatingScore> fs; // berisi floating score yang muncul saat player mendapatkan batu
    private List<FloatingScore> deleteFs; // berisi floating score yang harus dihilangkan karena sudah muncul di canvas selama beberapa detik

    private HashMap<String, Integer> gemCounters; // berisi counter tiap-tiap jenis batu

    private MediaPlayer bgm; // media player untuk background music

    private boolean running; // boolean yang mengendalikan looping animasi

    public Gameplay(GraphicsContext gc, String username, String controls, int spawnFrequency, Stage stage, Scene scene) {
        // set atribut
        this.gc = gc;
        this.username = username;
        this.spawnFrequency = spawnFrequency;

        // counter skor dimulai dari 0
        this.newScore = 0;

        // buat instance pesawat player
        this.plane = new Plane((Constant.WIDTH / 2) - 50, Constant.HEIGHT - 100);

        // buat instance keempat list
        gems = new ArrayList<>();
        deleteGems = new ArrayList<>();
        fs = new ArrayList<>();
        deleteFs = new ArrayList<>();

        // set hashmap
        gemCounters = new HashMap<>();
        gemCounters.put("Yellow Diamond", 0);
        gemCounters.put("Zircon", 0);
        gemCounters.put("Jade", 0);
        gemCounters.put("Rutile", 0);
        gemCounters.put("Phosphophyllite", 0);

        // set mediaplayer bgm untuk memainkan file yang sudah ada
        bgm = new MediaPlayer(new Media(new File("src/controller/sounds/bgm.mp3").toURI().toString()));

        // set control permainan
        switch (controls) {
            case "Keyboard":
                // bila menggunakan keyboard
                // set eventhandler di scene menjadi eventhandler dari class controller.KeyPressed dan controller.KeyReleased
                scene.setOnKeyPressed(new KeyPressed(plane, stage, this));
                scene.setOnKeyReleased(new KeyReleased(plane));
                break;
            case "Mouse":
                // bila menggunakan mouse
                // set eventhandler di scene menjadi eventhandler dari class controller.MouseMovement dan controller.SpaceKeyPressed
                scene.setCursor(Cursor.NONE);
                scene.setOnMouseMoved(new MouseMovement(plane));
                scene.setOnKeyPressed(new SpaceKeyPressed(stage, this));
                break;
        }

        // default boolean running adalah true
        running = true;
    }

    /**
     * method ini dipanggil ketika spacebar ditekan
     * sesuai isi eventhandler controller.SpaceKeyPressed dan controller.KeyPressed
     */
    public void terminate() {
        running = false;
    }

    /**
     * Looping animasi
     */
    @Override
    public void run() {
        Random rand = new Random(); // random untuk penentuan jenis batu apa yang akan dispawn (dimunculkan)
        int frameCounter = 0; // penghitung frame untuk menentukan jangka waktu antar spawn
        String type; // menampung tipe batu apa yang akan dispawn sesuai hasil random
        bgm.play(); // memulai bgm
        gc.setFont(Constant.GAME_UI); // ubah font text canvas menjadi yang sudah diatur di class global.Constant
        while (running) {
            // refresh canvas
            gc.clearRect(0, 0, Constant.WIDTH, Constant.HEIGHT);
            // Draw username and scoreboard
            gc.setFill(Color.BLACK);
            gc.fillText("CURRENTLY PLAYING: " + username, 25, 25);
            gc.fillText("" + newScore, Constant.WIDTH - 50, 25);
            // Draw plane
            plane.drawTo(gc);
            // Draw gems
            for (Gem x : gems) {
                x.drawTo(gc);
            }
            // Draw floating scores
            for (FloatingScore x : fs) {
                x.drawTo(gc);
            }
            if (frameCounter % spawnFrequency == 0 && frameCounter > 300) {
                // Random tipe batu yang akan dispawn dan spawn setiap beberapa detik
                // frekuensi spawn ditentukan setting difficulty yang dipilih pemain
                type = Gem.types.get(rand.nextInt(6));
                switch (type) {
                    case "Yellow Diamond":
                        gems.add(new YellowDiamond());
                        break;
                    case "Zircon":
                        gems.add(new Zircon());
                        break;
                    case "Jade":
                        gems.add(new Jade());
                        break;
                    case "Rutile":
                        gems.add(new Rutile());
                        break;
                    case "Phosphophyllite":
                        gems.add(new Phosphophyllite());
                        break;
                    case "Game Over":
                        gems.add(new GameOver());
                        break;
                }
            }
            // Move plane
            if (plane.isUP()) plane.moveUp();
            if (plane.isDOWN()) plane.moveDown();
            if (plane.isLEFT()) plane.moveLeft();
            if (plane.isRIGHT()) plane.moveRight();
            // Move gems
            for (Gem x : gems) {
                x.move();
                // intersects method credits to:
                // https://stackoverflow.com/questions/35073654/how-to-know-collision-between-2-images-javafx/35086192
                if (plane.intersects(x)) {
                    // bila pesawat menabrak batu
                    if (x.getType().equals("Game Over")) {
                        // bila menabrak batu hitam
                        // hentikan thread, mainkan suara kekalahan, dan mulai thread baru untuk memasukkan data permainan ke database
                        terminate();
                        new MediaPlayer(new Media(new File("src/controller/sounds/whoops.wav").toURI().toString())).play();
                        new Thread(new DataUpdater(gemCounters, username, newScore)).start();
                    } else {
                        // bila menabrak selain batu hitam
                        // mainkan suara, tambah nilai counter di hashmap gemCounters, dan tambah score
                        new MediaPlayer(new Media(new File("src/controller/sounds/get.wav").toURI().toString())).play();
                        gemCounters.replace(x.getType(), gemCounters.get(x.getType()) + 1);
                        newScore += x.getScore();

                        // masukkan batu ke list deleteGems
                        deleteGems.add(x);
                        // buat object floating score
                        fs.add(new FloatingScore(x.getScore(), x.getX(), x.getY()));
                    }
                }
                // periksa jika ada batu yang belum dikenai pesawat tetapi sudah melewati batas window
                if (!deleteGems.contains(x)) {
                    switch (x.getDirection()) {
                        case "UP":
                            if (x.getBottomEdge() < 0) deleteGems.add(x);
                            break;
                        case "DOWN":
                            if (x.getTopEdge() > Constant.HEIGHT) deleteGems.add(x);
                            break;
                        case "LEFT":
                            if (x.getRightEdge() < 0) deleteGems.add(x);
                            break;
                        case "RIGHT":
                            if (x.getLeftEdge() > Constant.WIDTH) deleteGems.add(x);
                            break;
                    }
                }
            }
            // Move floating scores
            for (FloatingScore x : fs) {
                x.move();
                // periksa jika ada floating score yang sudah melewati batas waktu kemunculannya
                if (x.stop()) {
                    // masukkan ke deleteFs
                    deleteFs.add(x);
                }
            }
            // Hilangkan semua batu yang ada di deleteGems dari gems
            for (Gem x : deleteGems) {
                gems.remove(x);
            }
            // Hilangkan semua floating score yang ada di deleteFs dari fs
            for (FloatingScore x : deleteFs) {
                fs.remove(x);
            }
            // Thread sleep //
            try {
                Thread.sleep(Constant.SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // iterasi counter frame
            frameCounter++;
        }
        ///////////////////
        // Di bawah ini hanya akan jalan ketika ada perintah terminate()
        ///////////////////

        // hentikan bgm
        bgm.stop();

        // buat rectangle overlay hitam transparan yang menutupi seluruh window
        gc.setFill(Color.BLACK);
        gc.setGlobalAlpha(0.5);
        gc.fillRect(0, 0, Constant.WIDTH, Constant.HEIGHT);

        // buat text bertuliskan game over serta skor yang didapat
        String msg = "GAME OVER\n" +
                "YOUR SCORE IS " +
                newScore +
                "\nPress space to return to main menu...";
        gc.setFill(Color.WHITE);
        gc.setGlobalAlpha(1);
        gc.fillText(msg, Constant.WIDTH / 2, Constant.HEIGHT / 2);
    }
}
