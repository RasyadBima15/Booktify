package booktify.scene;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegisScene {
    private Stage stage;

    public RegisScene(Stage stage){
        this.stage = stage;
    }
   
    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);

        Text createAccount = new Text("Buat Akun");

        TextField tfUsername = new TextField();
        Label lbStrongUsername = new Label("ket: bla bla");
        TextField tfPassword = new TextField();
        Label lbStrongPassword = new Label("Ket: Password harus memiliki minimal bla bla bla");

        VBox vLayoutTextField = new VBox(tfUsername, lbStrongUsername, tfPassword, lbStrongPassword);
        vLayoutTextField.setSpacing(5);
        vLayoutTextField.setAlignment(Pos.CENTER);

        Button btnRegis = new Button("Daftar");

        btnRegis.setOnAction(v -> {
            LoginScene loginScene = new LoginScene(stage);
            stage.setScene(loginScene.show());
        });

        VBox vLayout = new VBox(createAccount, vLayoutTextField,btnRegis);
        vLayout.setSpacing(10);
        spLayout.getChildren().add(vLayout);
        vLayout.setAlignment(Pos.CENTER);

        return scene;
    }
}
