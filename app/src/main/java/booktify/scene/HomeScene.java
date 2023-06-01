package booktify.scene;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.BadBinaryOpValueExpException;

import booktify.dao.CustomerDao;
import booktify.models.Customer;
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
        ivLogo.setFitHeight(71);
        ivLogo.setFitWidth(70);

        HBox hboxLogo = new HBox(ivLogo);
        hboxLogo.setAlignment(Pos.TOP_LEFT);

        // Label home = new Label("Home");
        // Label cekSaldo = new Label("Cek Saldo");
        // Label listBooks = new Label("Daftar Buku");
        // Label historyPurchase = new Label("Riwayat Pembelian");
        // Label logout = new Label("Logout");

        // listBooks.setOnMouseClicked(v -> {
        //     TransactionScene transactionScene = new TransactionScene(stage);
        //     stage.setScene(transactionScene.show());
        // });

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

    private VBox generateBottomSide(double width, double height) {
        VBox hBoxLayout = new VBox();
        hBoxLayout.setPrefSize(width, height);
        hBoxLayout.setMaxSize(width, height);
        hBoxLayout.setPadding(new Insets(24));
        return hBoxLayout;
    }

    private void changeMenu(int indexMenu) throws SQLException {
        switch (indexMenu) {
            case 1:
                showHomeView();
                break;
            case 2:
                showListBooks();
                break;
            case 3:
                showHistoryPurchase();
                break;
            case 4:
                showBalance();
                break;
            case 5:
                showLogout();
                break;
            default:
                break;
        }
    }

    private void showHomeView() throws SQLException {
        bottomSide.getChildren().clear();
        List<Customer> listCustomer = new ArrayList<>();
        Text tHome = new Text("Selamat Datang di Booktify,");
        CustomerDao custDao = new CustomerDao();
        listCustomer.addAll(custDao.get(LoginScene.username));
        
        Text tHomeUser = new Text(listCustomer.get(0).getUsername());

        VBox vContent = new VBox(tHome, tHomeUser);
        vContent.setAlignment(Pos.CENTER);

        Text tContact = new Text("Contact Admin");
        Text tNumPhone = new Text("+628871293167");
        VBox vContactAdmin = new VBox(tContact, tNumPhone);
        vContactAdmin.setAlignment(Pos.CENTER);

        bottomSide.getChildren().addAll(vContent, vContactAdmin);
        bottomSide.setSpacing(160);
        bottomSide.setPadding(new Insets(165, 0, 0, 0));
    }

    private HBox[] generateMenuItem() {
        String[] listTitle = { "Home", "Daftar Buku", "Riwayat Pembelian", "Cek Saldo", "Logout"};
        HBox[] listHboxMenu = new HBox[5];

        for (int i = 0; i < listHboxMenu.length; i++){
            Label labelMenu = new Label(listTitle[i]);
            listHboxMenu[i] = new HBox(labelMenu);
            listHboxMenu[i].setAlignment(Pos.CENTER);
            listHboxMenu[i].setPadding(new Insets(12, 20, 12, 20));
            listHboxMenu[i].setSpacing(4);
            changeMenuStatus(listHboxMenu[i], i == 0 ? true : false);
        }

        for (int i = 0; i < listHboxMenu.length; i++){
            int index = i;
            listHboxMenu[i].setOnMouseClicked(v -> {
                changeMenuStatus(listHboxMenu[index], true);
                try {
                    changeMenu(index + 1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                for (int j = 0; j < listHboxMenu.length; j++){
                    if (index != j){
                        changeMenuStatus(listHboxMenu[j], false);
                    }
                }
            });
        }
        return listHboxMenu;
    }
    private void changeMenuStatus(HBox menu, boolean isActive) {
        if (isActive) {
            menu.getStyleClass().add("menu-active");
        } else {
            menu.getStyleClass().clear();
        }
    }
    private void showListBooks() {
        
    }
    private void showHistoryPurchase() {
        
    }
    private void showBalance() throws SQLException {
        bottomSide.getChildren().clear();
        List<Customer> listCustomers = new ArrayList<>();
        CustomerDao custDao = new CustomerDao();
        listCustomers.addAll(custDao.get(LoginScene.username));

        Text infoSaldo = new Text("Saldo di Rekening Anda sebanyak Rp" + listCustomers.get(0).getUang());

        Button btnKlikTambah = new Button("Klik untuk Tambah Saldo");
        btnKlikTambah.setMaxWidth(400);

        btnKlikTambah.setOnAction(v -> {
            bottomSide.getChildren().clear();
            TextField jumlahSaldoTambah = new TextField();
            jumlahSaldoTambah.setMaxWidth(400);

            Button btnTambah = new Button("Tambah");
            btnTambah.setMaxWidth(80);

            Button btnBatal = new Button("Batal");
            btnBatal.setMaxWidth(80);

            HBox hLayout = new HBox(btnBatal, btnTambah);
            hLayout.setSpacing(5);
            hLayout.setAlignment(Pos.CENTER);

            Text voucher = new Text("Masukkan Voucher Anda");
            voucher.setTextAlignment(TextAlignment.CENTER);

            VBox vLayout = new VBox(infoSaldo, btnKlikTambah, voucher, jumlahSaldoTambah);
            vLayout.setSpacing(10);
            vLayout.setAlignment(Pos.CENTER);

            VBox vLayoutAll = new VBox(vLayout, hLayout);
            vLayoutAll.setSpacing(10);
            bottomSide.setPadding(new Insets(110, 0, 150, 0));
            bottomSide.getChildren().add(vLayoutAll);

            btnBatal.setOnAction(l -> {
                try {
                    showBalance();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            btnTambah.setOnAction(j -> {
                if (listCustomers.isEmpty()){
                    try {
                        listCustomers.addAll(custDao.get(LoginScene.username));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                String voucherToken = jumlahSaldoTambah.getText();
                if (voucherToken.equals("SURGAKI")) {
                    if (!listCustomers.isEmpty()){
                        String username = listCustomers.get(0).getUsername();
                        int uang = listCustomers.get(0).getUang();
                        try {
                            int id = custDao.addBalance(username, uang);
                            listCustomers.remove(0);
                            custDao.unactivateToken(username);

                            listCustomers.addAll(custDao.get(username));

                            Text newinfoSaldo = new Text("Saldo di Rekening Anda sebanyak Rp" + listCustomers.get(0).getUang());

                            HBox newhLayout = new HBox(btnBatal, btnTambah);
                            newhLayout.setSpacing(5);
                            newhLayout.setAlignment(Pos.CENTER);

                            Text newvoucher = new Text("Masukkan Voucher Anda");
                            newvoucher.setTextAlignment(TextAlignment.CENTER);

                            VBox newvLayout = new VBox(newinfoSaldo, btnKlikTambah, newvoucher, jumlahSaldoTambah);
                            newvLayout.setSpacing(10);
                            newvLayout.setAlignment(Pos.CENTER);

                            if (id != 0){
                                bottomSide.getChildren().clear();
                                Text wrongToken = new Text("Saldo anda berhasil ditambahkan sebanyak Rp300000");
                                wrongToken.setTextAlignment(TextAlignment.CENTER);
                                VBox vLayoutAllWithCorrectToken = new VBox(newvLayout, wrongToken, newhLayout);
                                vLayoutAllWithCorrectToken.setSpacing(10);
                                vLayoutAllWithCorrectToken.setAlignment(Pos.CENTER);
                                bottomSide.setPadding(new Insets(110, 0, 150, 0));
                                bottomSide.getChildren().add(vLayoutAllWithCorrectToken);
                            } else {
                                bottomSide.getChildren().clear();
                                Text wrongToken = new Text("Token anda sudah digunakan");
                                wrongToken.setTextAlignment(TextAlignment.CENTER);
                                VBox vLayoutAllWithWrongToken = new VBox(newvLayout, wrongToken, newhLayout);
                                vLayoutAllWithWrongToken.setSpacing(10);
                                vLayoutAllWithWrongToken.setAlignment(Pos.CENTER);
                                bottomSide.setPadding(new Insets(110, 0, 150, 0));
                                bottomSide.getChildren().add(vLayoutAllWithWrongToken);
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    bottomSide.getChildren().clear();
                    Text newinfoSaldo = new Text("Saldo di Rekening Anda sebanyak Rp" + listCustomers.get(0).getUang());
                    HBox newhLayout = new HBox(btnBatal, btnTambah);
                    newhLayout.setSpacing(5);
                    newhLayout.setAlignment(Pos.CENTER);

                    Text newvoucher = new Text("Masukkan Voucher Anda");
                    newvoucher.setTextAlignment(TextAlignment.CENTER);

                    VBox newvLayout = new VBox(newinfoSaldo, btnKlikTambah, newvoucher, jumlahSaldoTambah);
                    newvLayout.setSpacing(10);
                    newvLayout.setAlignment(Pos.CENTER);

                    Text wrongToken = new Text("Token yang anda masukkan salah!");
                    wrongToken.setTextAlignment(TextAlignment.CENTER);
                    VBox vLayoutAllWithWrongToken = new VBox(newvLayout, wrongToken, newhLayout);
                    vLayoutAllWithWrongToken.setSpacing(10);
                    vLayoutAllWithWrongToken.setAlignment(Pos.CENTER);
                    bottomSide.setPadding(new Insets(110, 0, 150, 0));
                    bottomSide.getChildren().add(vLayoutAllWithWrongToken);
                }
            });
        });

        VBox vLayout = new VBox(infoSaldo,btnKlikTambah);
        vLayout.setSpacing(10);
        vLayout.setAlignment(Pos.CENTER);
        bottomSide.setPadding(new Insets(130, 0, 150, 0));
        bottomSide.getChildren().add(vLayout);
    }
    private void showLogout() {
        LoginScene loginScene = new LoginScene(stage);
        stage.setScene(loginScene.show());
    }
}
