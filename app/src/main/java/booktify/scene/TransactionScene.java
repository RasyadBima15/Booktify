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

public class TransactionScene {
    private Stage stage;

    public TransactionScene(Stage stage){
        this.stage = stage;
    }
    
    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);

        Text transaksi = new Text("Input Jumlah Stok yang ingin dibeli");
        transaksi.setFont(null);

        Button btnBeli = new Button("Beli");
        btnBeli.setMaxWidth(80);
        Button btnBatal = new Button("Batal");
        btnBatal.setMaxWidth(80);

        btnBatal.setOnAction(v -> {
            HomeScene homeScene = new HomeScene(stage);
            stage.setScene(homeScene.show());
        });

        TextField jumlahStok = new TextField();
        jumlahStok.setMaxWidth(400);

        HBox ConfirmBtn = new HBox(btnBatal, btnBeli);
        ConfirmBtn.setSpacing(5);
        ConfirmBtn.setAlignment(Pos.CENTER);

        VBox vLayoutTextField = new VBox(jumlahStok, ConfirmBtn);
        vLayoutTextField.setSpacing(5);
        vLayoutTextField.setAlignment(Pos.CENTER);

        VBox vLayout = new VBox(transaksi, vLayoutTextField, ConfirmBtn);
        vLayout.setSpacing(10);
        spLayout.getChildren().add(vLayout);
        vLayout.setAlignment(Pos.CENTER);

        return scene;
}        
}
