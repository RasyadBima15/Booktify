package booktify.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SaldoScene {
    static final int TITLE_SIZE = 48;
    static final int ALL_TEXT_SIZE = 28;
    private Stage stage;

    public SaldoScene(Stage stage){
        this.stage = stage;
    }

    public Scene show(){
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);

        Text infoSaldo = new Text("Saldo di Rekening Anda sebanyak Rp.{database_saldo}");
        TextField jumlahSaldoTambah = new TextField();
        jumlahSaldoTambah.setMaxWidth(400);

        Button btnTambah = new Button("Tambah");
        btnTambah.setMaxWidth(80);
        Button btnBatal = new Button("Batal");
        btnBatal.setMaxWidth(80);
        Button btnKlikTambah = new Button("Klik untuk Tambah Saldo");
        btnKlikTambah.setMaxWidth(400);

        btnBatal.setOnAction(v -> {
            HomeScene homeScene = new HomeScene(stage);
            stage.setScene(homeScene.show());
        });

        VBox vLayout = new VBox(btnKlikTambah, jumlahSaldoTambah);
        vLayout.setSpacing(5);
        vLayout.setAlignment(Pos.CENTER);

        HBox hLayout = new HBox(btnTambah, btnBatal);
        hLayout.setSpacing(5);
        hLayout.setAlignment(Pos.CENTER);

        VBox vLayoutAll = new VBox(vLayout, hLayout);
        vLayoutAll.setSpacing(5);
        spLayout.getChildren().add(vLayoutAll);
        vLayoutAll.setAlignment(Pos.CENTER);


        return scene;
    }
}
