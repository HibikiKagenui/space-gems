package entity.gem;

import global.Constant;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Class melambangkan batu "Gem" secara general
 * Memiliki 6 subclass yang merupakan jenis-jenis Gem yang berbeda
 */
public class Gem {
    // static final list berisi nama-nama subclass nya
    public static final List<String> types = Arrays.asList(
            "Yellow Diamond",
            "Zircon",
            "Jade",
            "Rutile",
            "Phosphophyllite",
            "Game Over"
    );

    // static list yang berisi 4 kemungkinan arah gerakan batu
    private static final List<String> directions = Arrays.asList("UP", "DOWN", "LEFT", "RIGHT");
    // static panjang dan lebar batu
    private static final double width = 50;
    private static final double height = 50;

    // 3 atribut ini yang value nya akan berbeda dari tiap-tiap subclass
    protected Image image;
    protected String type;
    protected Integer score;

    private GraphicsContext gc;
    private String direction;
    private double x;
    private double y;
    private double speed;

    public Gem() {
    }

    Gem(GraphicsContext gc) {
        this.gc = gc;

        Random rand = new Random();

        // randomize direction
        this.direction = directions.get(rand.nextInt(4));

        // randomize spawn position based on direction
        switch (direction) {
            case "UP":
                this.x = rand.nextInt((int) (Constant.WIDTH - width) + 1);
                this.y = Constant.HEIGHT;
                break;
            case "DOWN":
                this.x = rand.nextInt((int) (Constant.WIDTH - width) + 1);
                this.y = 0 - height;
                break;
            case "LEFT":
                this.x = Constant.WIDTH;
                this.y = rand.nextInt((int) (Constant.HEIGHT - height) + 1);
                break;
            case "RIGHT":
                this.x = 0 - width;
                this.y = rand.nextInt((int) (Constant.HEIGHT - height) + 1);
                break;
        }

        // randomize speed
        this.speed = rand.nextInt(6) + 10;
    }

    /////////////////////////////////////////////////////////////////////////////////
    /// GETTER SETTER {x, y, UP, DOWN, LEFT, RIGHT} /////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////

    public Image getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public Integer getScore() {
        return score;
    }

    public String getDirection() {
        return direction;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Get top bottom left right edge
     * @return posisi pinggiran atas bawah kiri kanan batu untuk dibandingkan dengan pinggiran window
     * jika sudah melewati window, maka batu harus dihapus
     */

    public double getTopEdge() {
        return y;
    }

    public double getBottomEdge() {
        return y + height;
    }

    public double getLeftEdge() {
        return x;
    }

    public double getRightEdge() {
        return x + width;
    }

    /////////////////////////////////////////////////////////////////////////////////

    /**
     * Method menggerakkan batu sesuai direction nya
     */
    public void move() {
        switch (direction) {
            case "UP":
                this.y -= this.speed;
                break;
            case "DOWN":
                this.y += this.speed;
                break;
            case "LEFT":
                this.x -= this.speed;
                break;
            case "RIGHT":
                this.x += this.speed;
                break;
        }
    }

    /**
     * Mengambar batu ke gc canvas
     */
    public void draw() {
        gc.drawImage(image, x, y, width, height);
    }

    /**
     * Membantu method intersect yang ada di class entity.Plane
     * @return Rectangle2D boundary dari batu
     */
    public Rectangle2D getBoundary() {
        return new Rectangle2D(x, y, width, height);
    }
}
