package entity;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class entitas yang melambangkan object floating score
 * - angka skor kecil yang muncul ketika pesawat mendapatkan batu
 * - score otomatis hilang setelah beberapa waktu
 * - selalu bergerak ke atas
 */
public class FloatingScore {
    // batas waktu pergerakan floating score setelah dimunculkan di canvas
    private static final int stepLimit = 30;

    private GraphicsContext gc;
    private int score;
    private double x;
    private double y;
    private int step;

    public FloatingScore(GraphicsContext gc, int score, double x, double y) {
        this.gc = gc;
        this.score = score;
        this.x = x;
        this.y = y;
        this.step = 0; // step selalu mulai dari 0
    }

    /**
     * Method untuk menggerakan floating score
     * Floating score akan hilang setelah 30 kali bergerak
     */
    public void move() {
        y -= 2;
        step++;
    }

    /**
     * Menandakan apakah floating score harus dihilangkan atau belum
     * @return true bila step sudah melebihi batas
     */
    public boolean stop() {
        return step > stepLimit;
    }

    /**
     * Method menggambarkan floating score ke gc canvas
     */
    public void draw() {
        gc.setFill(Color.GOLDENROD);
        gc.fillText("" + score, x, y);
    }
}
