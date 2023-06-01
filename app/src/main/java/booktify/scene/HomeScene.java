package booktify.scene;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.BadBinaryOpValueExpException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class HomeScene {
    private Stage stage;
    private VBox bottomSide;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public Scene show() throws SQLException {
        VBox vPage = new VBox();
        Scene scene = new Scene(vPage, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/home_style.css").toExternalForm());

        HBox upperSide = generateUpperSide(scene.getWidth(), scene.getHeight() * .10);
        bottomSide = generateBottomSide(scene.getWidth(), scene.getHeight() * .64);
        changeMenu(1);

        vPage.getChildren().addAll(upperSide, bottomSide);

        stage.setScene(scene);
        return scene;  
    }
    private HBox generateUpperSide(double width, double height) {
        HBox hNavbar = new HBox();
        hNavbar.setPrefSize(width, height);
        hNavbar.setMaxSize(width, height);

        ImageView ivLogo = new ImageView("/images/logoBooktify.jpg");
        HBox hboxLogo = new HBox(ivLogo);
        hboxLogo.setAlignment(Pos.TOP_LEFT);

        // Label home = new Label("Home");
        // Label cekSaldo = new Label("Cek Saldo");
        // Label listBooks = new Label("Daftar Buku");
        // Label historyPurchase = new Label("Riwayat Pembelian");
        // Label logout = new Label("Logout");

        // logout.setOnMouseClicked(v -> {
        //     LoginScene loginScene = new LoginScene(stage);
        //     stage.setScene(loginScene.show());
        // });

        // cekSaldo.setOnMouseClicked(v -> {
        //     SaldoScene saldoScene = new SaldoScene(stage);
        //     stage.setScene(saldoScene.show());
        // });

        Region spacerNavbar = new Region();
        spacerNavbar.setPrefWidth(83);

        // HBox hboxHome = new HBox(spacerNavbar, home, listBooks, historyPurchase, cekSaldo, logout);
        // hboxHome.setSpacing(10);
        // hboxHome.setAlignment(Pos.CENTER);

        hNavbar.getChildren().addAll(hboxLogo, spacerNavbar);
        hNavbar.getChildren().addAll(generateMenuItem());
        hNavbar.getStyleClass().add("navbar");

        return hNavbar;
    }
}
