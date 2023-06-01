package booktify.scene;

import javax.management.BadBinaryOpValueExpException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeScene {
    private Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/home_style.css").toExternalForm());

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

        listBooks.setOnMouseClicked(v -> {
            System.out.println("tes");
            TransactionScene transactionScene = new TransactionScene(stage);
            stage.setScene(transactionScene.show());

        });

        logout.setOnMouseClicked(v -> {
            LoginScene loginScene = new LoginScene(stage);
            stage.setScene(loginScene.show());
        });

        cekSaldo.setOnMouseClicked(v -> {
            SaldoScene saldoScene = new SaldoScene(stage);
            stage.setScene(saldoScene.show());
        });

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

        Text tHome = new Text("Selamat Datang di Booktify");
        Text tHomeUser = new Text("{ Nama }");
        tHome.setFont(Font.font("Britannic", 30));
        VBox vContent = new VBox(tHome, tHomeUser);
        vContent.setAlignment(Pos.CENTER);

        Text Ind = new Text("Toko buku online terbesar, terlengkap dan terpercaya di Indonesia");
        Ind.setFont(Font.font("Britannic", 15));
        VBox Indd = new VBox(Ind);
        Indd.setAlignment(Pos.CENTER);

        ImageView waLogo = new ImageView("/images/WA.png");
        waLogo.setFitHeight(15);
        waLogo.setFitWidth(15);
        HBox hbwlogo = new HBox(waLogo);
        hbwlogo.setAlignment(Pos.BOTTOM_LEFT);

        ImageView igLogo = new ImageView("/images/IG2.png");
        igLogo.setFitHeight(15);
        igLogo.setFitWidth(15);
        HBox hbilogo = new HBox(igLogo);
        hbilogo.setAlignment(Pos.BOTTOM_RIGHT);

        Label cp = new Label("Contact Admin");
        Label no = new Label("+628871293167");
        Label ig = new Label("@booktify.acc");

        Region sc = new Region();
        sc.setPrefSize(60, 0);

        HBox tc = new HBox(cp, no, ig, sc);
        tc.setSpacing(10);
        tc.setAlignment(Pos.CENTER);

        HBox ct = new HBox(hbwlogo, hbilogo, tc);
        ct.setAlignment(Pos.BOTTOM_CENTER);
        ct.setPadding(new Insets(10));

        VBox tcc = new VBox(ct);
        tcc.setAlignment(Pos.BOTTOM_CENTER);
        tcc.getStyleClass().add("barbar");

        // Text tContact = new Text("Contact Admin");
        // Text tNumPhone = new Text("+628871293167");
        // VBox vContactAdmin = new VBox(tContact, tNumPhone);
        // vContactAdmin.setAlignment(Pos.CENTER);

        VBox vL = new VBox(tcc);
        vL.setAlignment(Pos.BOTTOM_CENTER);

        VBox vLayout = new VBox(vNavbar, vContent,Indd,vL);
        vLayout.setAlignment(Pos.TOP_CENTER);

        VBox.setMargin(vContent, new Insets(150, 0, 150, 0));

        spLayout.getChildren().add(vLayout);
        // spLayout.getChildren().add(vL);
        return scene;
    }
}
