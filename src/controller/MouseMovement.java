package controller;

import entity.Plane;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Class implement EventHandler
 * Berisi method yang mengatur pengaturan MouseEvent
 * Berisi atribut Plane karena EventHandler ini memanipulasi posisi Plane
 * Dipasangkan dengan EventHandler controller.SpaceKeyPressed
 */
public class MouseMovement implements EventHandler<MouseEvent> {
    private Plane plane;

    public MouseMovement(Plane plane) {
        this.plane = plane;
    }

    @Override
    public void handle(MouseEvent event) {
        plane.setX(event.getSceneX());
        plane.setY(event.getSceneY());
    }
}
