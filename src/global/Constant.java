package global;

import javafx.scene.text.Font;

/**
 * Class berisi atribut-atribut yang digunakan secara global di program ini
 */
public class Constant {
    public static final float WIDTH = 1280; // lebar canvas
    public static final float HEIGHT = 720; // tinggi canvas
    public static final long SLEEP_TIME = 1000 / 60; // waktu sleep thread agar diseragamkan
    public static final Font GAME_UI = new Font("Consolas",24); // font yang digunakan di gameplayview
}
