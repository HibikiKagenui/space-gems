package view;

import entity.gem.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.List;

/**
 * Class berisi view yang menampilkan petunjuk singkat permainan
 *
 */
public class HowToPlayView {
    public void show(Stage stage) {
        ///////////////////////////////////////////////////////////////////////////////////////
        // TITLE //////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        Label title = new Label("GEMS 101");
        title.setStyle("-fx-font-size: 18pt");

        Label intro = new Label("Once you press play, you have 5 seconds until the gems will start to fly around from all directions\n" +
                "Your job is to get as many gems as you can. You will be awarded points according to what type of gems you get");

        ///////////////////////////////////////////////////////////////////////////////////////
        // THE ROCKS //////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        List<ImageView> gemImages = Arrays.asList(
                new ImageView(new YellowDiamond().getImage()),
                new ImageView(new Zircon().getImage()),
                new ImageView(new Jade().getImage()),
                new ImageView(new Rutile().getImage()),
                new ImageView(new Phosphophyllite().getImage()),
                new ImageView(new GameOver().getImage())
        );

        ///////////////////////////////////////////////////////////////////////////////////////
        // ABOUT THE ROCKS ////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        List<Label> gemLabels = Arrays.asList(
                new Label("Yellow Diamonds. Worth 100 points"),
                new Label("Zircons. Worth 75 points"),
                new Label("Jades. Worth 60 points"),
                new Label("Rutiles. Worth 40 points"),
                new Label("Phosphophyllites. Worth 35 points"),
                new Label("Just plain rocks.\nWorth nothing and can destroy your ship if you crash into them")
        );

        ///////////////////////////////////////////////////////////////////////////////////////
        // GRIDPANE FOR LAYOUT ////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);

        int i = 0;
        for (ImageView x : gemImages) {
            x.setFitWidth(60);
            x.setPreserveRatio(true);
            grid.add(x, 0, i++);
        }
        i = 0;
        for (Label x : gemLabels) {
            x.setStyle("-fx-font-size: 14pt");
            x.setStyle("-fx-font-size: 14pt");
            grid.add(x, 1, i++);
        }

        ///////////////////////////////////////////////////////////////////////////////////////
        // BUTTON TO GO BACK TO MAIN MENU /////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        Button back = new Button("Back");
        back.setMinHeight(50);
        back.setMinWidth(128);
        back.setOnAction(e -> new LeaderboardView().show(stage));

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET LAYOUT /////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        VBox vbox = new VBox();
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10));
        vbox.getChildren().addAll(title, intro, grid, back);

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET SCENE //////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        Scene scene = new Scene(vbox, 800, 700);
        scene.getStylesheets().add(getClass().getResource("styles\\style.css").toExternalForm());
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
