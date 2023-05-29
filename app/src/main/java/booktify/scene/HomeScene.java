package booktify.scene;

import javax.management.BadBinaryOpValueExpException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScene {
    private Stage stage;

    public HomeScene(Stage stage){
        this.stage = stage;
    }

    public void show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);
        
        // scene.getStylesheets().add(getClass().getResource("/styles/home_style.css").toExternalForm());
        ImageView ivLogo = new ImageView("/images/logoBooktify.jpg");
        ivLogo.setFitHeight(100);
        ivLogo.setFitWidth(200);

        Text textBrand = new Text("Booktify");

        Label lbDesc = new Label("Aplikasi belanja buku yang mudah dan efisien bagi pengguna");
        lbDesc.getStyleClass().add("desc-text");

        Region space = new Region();
        space.setPrefHeight(12);

        Text textLogin = new Text("Login");

        TextField tfUsername = new TextField();
        TextField tfPass = new TextField();

        // tfUsername.setPrefWidth(10);
        // tfPass.setPrefHeight(10);

        Button btnLogin = new Button("Masuk");
        Text textRegis = new Text("Belum punya akun? ayo");
        Hyperlink regis = new Hyperlink("daftar");

        // regis.setOnAction(v -> {
        //     System.out.println("Ini akuuu");
        // });

        FlowPane flwPane = new FlowPane();
        flwPane.getChildren().addAll(textRegis, regis);
        flwPane.setAlignment(Pos.CENTER);

        VBox vLayout = new VBox(ivLogo, textBrand, lbDesc, space, textLogin, tfUsername, tfPass, btnLogin, flwPane);
        vLayout.setSpacing(10);
        spLayout.getChildren().add(vLayout);
        vLayout.setAlignment(Pos.CENTER);

        stage.setScene(scene);

    }
}
