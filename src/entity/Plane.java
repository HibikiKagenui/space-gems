package entity;

import entity.gem.Gem;
import global.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Class object pesawat player
 */
public class Plane {
    // atribut static final untuk panjang-lebar pesawat, kecepatan pesawat, dan perubahan kecepatan pesawat ketika SHIFT ditekan
    public static final double width = 100;
    public static final double height = 100;
    private static final double speedModifier = 5;
    private static double speed = 15;
    private GraphicsContext gc;
    private Image image;
    private double x;
    private double y;

    // boolean untuk arah gerakan
    private boolean UP;
    private boolean DOWN;
    private boolean LEFT;
    private boolean RIGHT;

    public Plane(GraphicsContext gc, double x, double y) {
        this.gc = gc;
        this.image = new Image(getClass().getResource("assets\\plane.png").toExternalForm());
        this.x = x;
        this.y = y;
        this.LEFT = this.RIGHT = this.UP = this.DOWN = false; // arah gerakan default false
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// GETTER SETTER {x, y, UP, DOWN, LEFT, RIGHT} /////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public boolean isUP() {
        return UP;
    }

    public void setUP(boolean UP) {
        this.UP = UP;
    }

    public boolean isDOWN() {
        return DOWN;
    }

    public void setDOWN(boolean DOWN) {
        this.DOWN = DOWN;
    }

    public boolean isLEFT() {
        return LEFT;
    }

    public void setLEFT(boolean LEFT) {
        this.LEFT = LEFT;
    }

    public boolean isRIGHT() {
        return RIGHT;
    }

    public void setRIGHT(boolean RIGHT) {
        this.RIGHT = RIGHT;
    }

    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Gerakkan pesawat ke atas selama belum mengenai batas atas window
     */
    public void moveUp() {
        if (this.y + (height / 2) > 0) this.y -= Plane.speed;
    }

    /**
     * Gerakkan pesawat ke bawah selama belum mengenai batas bawah window
     */
    public void moveDown() {
        if (this.y + (height / 2) < Constant.HEIGHT) this.y += Plane.speed;
    }

    /**
     * Gerakkan pesawat ke kiri selama belum mengenai batas kiri window
     */
    public void moveLeft() {
        if (this.x + (width / 2) > 0) this.x -= Plane.speed;
    }

    /**
     * Gerakkan pesawat ke kanan selama belum mengenai batas kanan window
     */
    public void moveRight() {
        if (this.x + (width / 2) < Constant.WIDTH) this.x += Plane.speed;
    }

    /**
     * Method menambah dan mengurangi kecepatan sesuai speedModifier
     */
    public void speedUp() {
        Plane.speed += speedModifier;
    }

    public void slowDown() {
        Plane.speed -= speedModifier;
    }

    /**
     * Menggambar pesawat ke gc canvas
     */
    public void draw() {
        gc.drawImage(image, x, y, width, height);
    }

    // dua method dibawah ini membantu mendeteksi apabila pesawat sudah mengenai batu

    /**
     * Membuat sebuah object Rectangle2D yang seukuran dengan gambar
     * @return Rectangle2D
     */
    private Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }

    /**
     * Memeriksa apabila boundary pesawat sudah berdempet dengan boundary batu
     * @param gem
     * @return true jika sudah berdempet
     */
    public boolean intersects(Gem gem) {
        return gem.getBoundary().intersects(this.getBoundary());
    }
}