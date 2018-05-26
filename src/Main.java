import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import view.LeaderboardView;

/*
Saya Muhammad Nabillah tidak melakukan kecurangan yang dispesifikasikan
pada tugas masa depan PBO pada saat mengerjakan Tugas Masa Depan PBO.
Jika saya melakukan kecurangan maka Allah/Tuhan adalah saksi saya,
dan saya bersedia menerima hukumanNya. Aamiin.
*/

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Menghilangkan border window
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle("");

        LeaderboardView view = new LeaderboardView();
        view.show(stage);
    }
}
