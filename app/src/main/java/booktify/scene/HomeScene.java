package booktify.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScene {
    private Stage stage;

    public HomeScene(Stage stage){
        this.stage = stage;
    }

    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);
        scene.getStylesheets().addAll(getClass().getResource("/styles/home_style.css").toExternalForm());

        ImageView ivLogo = new ImageView("/images/logoBooktify.jpg");
        ivLogo.setFitHeight(50);
        ivLogo.setFitWidth(50);

        HBox hboxLogo = new HBox(ivLogo);
        hboxLogo.setAlignment(Pos.TOP_LEFT);

        Label home = new Label("Home");
        Label cekSaldo = new Label("Cek Saldo");
        Label listBooks = new Label("Daftar Buku");
        Label historyPurchase = new Label("Riwayat Pembelian");
        Label logout = new Label("Logout");

        Region spacerNavbar = new Region();
        spacerNavbar.setPrefSize(60, 0);

        HBox hboxHome = new HBox(spacerNavbar, home, listBooks, historyPurchase, cekSaldo, logout);
        hboxHome.setSpacing(10);
        hboxHome.setAlignment(Pos.CENTER);

        HBox hNavbar = new HBox(hboxLogo, hboxHome);
        hNavbar.setAlignment(Pos.TOP_LEFT);
        hNavbar.setPadding(new Insets(10));

        VBox vNavbar = new VBox(hNavbar);
        vNavbar.setAlignment(Pos.TOP_CENTER);
        vNavbar.getStyleClass().add("navbar");

        // Region spacerContent = new Region();
        // spacerContent.setPrefSize(300, 0);

        Text tHome = new Text("Selamat Datang di Booktify,");
        Text tHomeUser = new Text("{ Nama }");

        VBox vContent = new VBox(tHome, tHomeUser);
        vContent.setAlignment(Pos.CENTER);

        Text tContact = new Text("Contact Admin");
        Text tNumPhone = new Text("+628871293167");
        VBox vContactAdmin = new VBox(tContact, tNumPhone);
        vContactAdmin.setAlignment(Pos.CENTER);
 
        VBox vLayout = new VBox(vNavbar, vContent, vContactAdmin);
        vLayout.setAlignment(Pos.TOP_CENTER);

        VBox.setMargin(vContent, new Insets(150, 0, 150, 0));

        spLayout.getChildren().add(vLayout);
        return scene;  
    } 
}
