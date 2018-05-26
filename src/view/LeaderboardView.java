package view;

import controller.ControllerKoleksi;
import entity.HighScore;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class memuat view papan skor
 * Mendapat data dari ControllerKoleksi dan menampilkannya di TableView table
 */
public class LeaderboardView {
    // deklarasi list yang digunakan untuk menyimpan data yang ditampilkan table
    private final ObservableList<HighScore> data;
    // controller
    private ControllerKoleksi controller;
    // deklarasi tabel
    private TableView<HighScore> table;

    public LeaderboardView() {
        // instansiasi atribut
        this.data = FXCollections.observableArrayList();
        this.table = new TableView<>();
        this.controller = new ControllerKoleksi();
    }

    public void show(Stage stage) {
        ///////////////////////////////////////////////////////////////////////////////////////
        // TITLE //////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        Label title = new Label("SPACE GEMS");
        title.setId("title");

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET TABLE //////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //pembuatan kolom pada tabel
        //kolom nomor dengan auto numbering
        TableColumn<HighScore, Integer> noCol = new TableColumn<>("#");
        noCol.setCellValueFactory(cell -> new ReadOnlyObjectWrapper<>(table.getItems().indexOf(cell.getValue()) + 1));
        noCol.setSortable(false);

        // kolom username
        TableColumn<HighScore, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setMaxWidth(120);
        usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));

        // kolom jenisKoleksi
        TableColumn<HighScore, String> jenisKoleksiCol = new TableColumn<>("Jenis Koleksi");
        jenisKoleksiCol.setMaxWidth(150);
        jenisKoleksiCol.setCellValueFactory(new PropertyValueFactory<>("JenisKoleksi"));

        // kolom skorKoleksi
        TableColumn<HighScore, Integer> skorKoleksiCol = new TableColumn<>("Skor Koleksi");
        skorKoleksiCol.setMaxWidth(150);
        skorKoleksiCol.setCellValueFactory(new PropertyValueFactory<>("SkorKoleksi"));

        // kolom skorTotal
        TableColumn<HighScore, Integer> skorTotalCol = new TableColumn<>("Skor Total");
        skorTotalCol.setMaxWidth(150);
        skorTotalCol.setCellValueFactory(new PropertyValueFactory<>("SkorTotal"));

        // masukkan semua kolom ke tableview
        table.getColumns().addAll(noCol, usernameCol, jenisKoleksiCol, skorKoleksiCol, skorTotalCol);

        //mengisi tabel dengan data
        refreshTable(stage);
        table.setItems(data);

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET USERNAME INPUT /////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        TextField usernameField = new TextField();

        HBox usernameInput = new HBox(new Label("Username: "), usernameField);
        usernameInput.setAlignment(Pos.CENTER_LEFT);
        usernameInput.setSpacing(10);

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET CONTROLS COMBO BOX /////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        ComboBox<String> controlsComboBox = new ComboBox<>();
        controlsComboBox.setItems(FXCollections.observableArrayList("Keyboard", "Mouse"));

        HBox controlsInput = new HBox(new Label("Control Type: "), controlsComboBox);
        controlsInput.setAlignment(Pos.CENTER);
        controlsInput.setSpacing(10);

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET DIFFICULTY COMBO BOX ///////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        ComboBox<String> difficultyComboBox = new ComboBox<>();
        difficultyComboBox.setItems(FXCollections.observableArrayList("Easy", "Medium", "Hard"));
        // difficulty default adalah medium
        difficultyComboBox.setValue("Medium");

        HBox difficultyInput = new HBox(new Label("Difficulty: "), difficultyComboBox);
        difficultyInput.setAlignment(Pos.CENTER);
        difficultyInput.setSpacing(10);

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET PLAY-HOWTO-EXIT BUTTONS ////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        // play button
        Button play = new Button("Play");
        play.setOnAction(e -> {
            if (usernameField.getText().isEmpty()) {
                // bila textfield username kosong, munculkan pemberitahuan
                Alert info = new Alert(
                        AlertType.INFORMATION,
                        "Insert your username first :D",
                        ButtonType.OK
                );
                info.showAndWait();
            } else if (controlsComboBox.getSelectionModel().isEmpty()) {
                // bila belum memilih tipe control,
                // munculkan pemberitahuan yang memiliki 2 tombol bervalue dua jenis control yang ada + cancel
                Alert info = new Alert(
                        AlertType.INFORMATION,
                        "Which one do you want?",
                        new ButtonType("Keyboard"),
                        new ButtonType("Mouse"),
                        ButtonType.CANCEL);
                info.setHeaderText("Pick a control type first :D");
                info.showAndWait();

                if (info.getResult() != ButtonType.CANCEL) {
                    // bila tidak klik cancel, ambil value dari tombol yang diklik
                    new GameplayView(usernameField.getText(), info.getResult().getText(), difficultyComboBox.getValue()).show(stage);
                }
            } else {
                // bila inputan lengkap, langsung tampilkan view gameplay
                new GameplayView(usernameField.getText(), controlsComboBox.getValue(), difficultyComboBox.getValue()).show(stage);
            }
        });
        // button howto menampilkan view howto
        Button howTo = new Button("How to Play");
        howTo.setOnAction(e -> new HowToPlayView().show(stage));
        // button exit langsung keluar dari aplikasi
        Button exit = new Button("Exit");
        exit.setOnAction(e -> stage.close());

        // set bentuk semua button
        List<Button> buttons = Arrays.asList(play, howTo, exit);
        for (Button x : buttons) {
            x.setMinHeight(50);
            x.setMinWidth(128);
        }

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET INPUT HBOX /////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        HBox input = new HBox(usernameInput, controlsInput, difficultyInput);
        input.setAlignment(Pos.CENTER);
        input.setSpacing(20);
        input.setPadding(new Insets(10));

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET BUTTONS HBOX ///////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        HBox bottom = new HBox();
        bottom.getChildren().addAll(buttons);
        bottom.setAlignment(Pos.CENTER);
        bottom.setSpacing(30);
        bottom.setPadding(new Insets(10));

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET CENTER /////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        VBox center = new VBox();
        center.setSpacing(25);
        center.setAlignment(Pos.CENTER);
        center.setPadding(new Insets(100));
        center.getChildren().addAll(title, table, input, bottom);

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET INTERFACE LAYOUT ///////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        BorderPane root = new BorderPane();
        root.setCenter(center);

        Scene scene = new Scene(root, 1024, 600);
        scene.getStylesheets().add(getClass().getResource("styles\\style.css").toExternalForm());

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET TABLE LISTENER /////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // bila ada yang diklik
                // isi textfield username dengan username dari row yang diklik
                usernameField.setText(newSelection.getUsername());
            }
        });

        ///////////////////////////////////////////////////////////////////////////////////////
        // SET STAGE //////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    private void refreshTable(Stage stage) {
        /*
         * Method untuk merefresh data dari tabel
         * dipanggil ketika view akan dimunculkan di stage
         */

        List<HighScore> daftar = new ArrayList<>();

        try {
            // panggil proses getAll dari controller dan ambil hasilnya
            controller.getAll();
            daftar = controller.getHasil();
        } catch (Exception ex) {
            // munculkan pesan error lalu tutup aplikasi
            Alert error = new Alert(AlertType.ERROR, ex.toString(), ButtonType.CLOSE);
            error.showAndWait();
            stage.close();
        }

        for (HighScore x : daftar) {
            // pindahkan isi dari hasil getAll ke ObservableList data
            data.add(new HighScore(x.getUsername(), x.getJenisKoleksi(), x.getSkorKoleksi(), x.getSkorTotal()));
        }
    }
}
