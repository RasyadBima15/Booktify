package booktify.scene;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;

import org.mindrot.jbcrypt.BCrypt;

import booktify.abstract_class.Home;
import booktify.abstract_class.ShowScene;
import booktify.dao.BookDao;
import booktify.dao.CustomerDao;
import booktify.dao.TransactionsDao;
import booktify.models.Books;
import booktify.models.CombinedData;
import booktify.models.Customer;
import booktify.models.Transaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
import javafx.util.converter.IntegerStringConverter;

public class HomeScene extends Home implements ShowScene {
    private Stage stage;

    public HomeScene(Stage stage) {
        this.stage = stage;
    }

    public Scene show() throws SQLException {
        VBox vPage = new VBox();
        Scene scene = new Scene(vPage, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        HBox upperSide = generateUpperSide(scene.getWidth(), 65);
        bottomSide = generateBottomSide(scene.getWidth(), scene.getHeight() - 65);
        changeMenu(1);

        vPage.getChildren().addAll(upperSide, bottomSide);

        stage.setScene(scene);
        return scene; 
    }
    private HBox generateUpperSide(double width, double height) {
        HBox hNavbar = new HBox();
        hNavbar.setPrefSize(width, height);
        hNavbar.setMaxSize(width, height);

        ImageView ivLogo = new ImageView("/images/bookt.png");
        ivLogo.setFitHeight(50);
        ivLogo.setFitWidth(40);
        StackPane sp = new StackPane();
        sp.setPadding(new Insets(0,0,0,30));
        sp.getChildren().add(ivLogo);

        HBox hboxLogo = new HBox(sp);
        hboxLogo.setAlignment(Pos.TOP_LEFT);

        Region spacerNavbar = new Region();

        spacerNavbar.setPrefWidth(50);

        hNavbar.getChildren().addAll(hboxLogo, spacerNavbar);
        hNavbar.getChildren().addAll(generateMenuItem());
        hNavbar.getStyleClass().add("navbar");

        return hNavbar;
    }
    private VBox generateBottomSide(double width, double height) {
        VBox hBoxLayout = new VBox();
        hBoxLayout.setPrefSize(width, height);
        hBoxLayout.setMaxSize(width, height);
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
        VBox vLayout = new VBox();
        List<Customer> listCustomer = new ArrayList<>();
        Text tHome = new Text("Selamat Datang di Booktify,");
        CustomerDao custDao = new CustomerDao();
        listCustomer.addAll(custDao.get(LoginScene.username));

        ImageView img = new ImageView("/images/buku.jpg");
        img.setFitHeight(480 - 65);
        img.setFitWidth(640);
        StackPane stp1 = new StackPane(img, vLayout);
        
        Text tHomeUser = new Text(listCustomer.get(0).getUsername());
        tHomeUser.setFont(Font.font("Britannic", 30));
        tHomeUser.setFill(Color.WHITE);

        tHome.setFont(Font.font("Britannic", 30));
        tHome.setFill(Color.WHITE);
        VBox vContent = new VBox(tHome, tHomeUser);

        Text Ind = new Text("Toko buku online terbesar, terlengkap dan terpercaya di Indonesia");
        Ind.setFont(Font.font("Britannic", 15));
        Ind.setFill(Color.WHITE);

        VBox Indd = new VBox(Ind);
        Indd.setAlignment(Pos.CENTER);
        Indd.getStyleClass().add("bInd");

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

        HBox tc = new HBox(cp, hbwlogo, no, hbilogo, ig);
        tc.setSpacing(10);
        tc.setAlignment(Pos.BOTTOM_CENTER);
        tc.setPadding(new Insets(10));

        VBox tcc = new VBox(tc);
        tcc.setAlignment(Pos.BOTTOM_CENTER);
        tcc.getStyleClass().add("barbar");

        VBox bawah = new VBox(Indd, tcc);

        vContent.setPrefHeight(480 - 65 - tcc.getHeight());
        vContent.setAlignment(Pos.CENTER);

        vLayout.setAlignment(Pos.CENTER);
        vLayout.getChildren().addAll(vContent, bawah);
        bottomSide.setPadding(new Insets(0));
        bottomSide.getChildren().add(stp1);
        bottomSide.setSpacing(140);
    }
    private HBox[] generateMenuItem() {
        String[] listTitle = { "Home", "Daftar Buku", "Riwayat Pembelian", "Cek Saldo", "Logout"};

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
                if (index != 4){
                    changeMenuStatus(listHboxMenu[index], true);
                }
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
    private void showListBooks() throws SQLException {
        bottomSide.getChildren().clear();
        ObservableList<Books> listBooks = FXCollections.observableArrayList();

        List<Customer> listCustomer = new ArrayList<>();
        CustomerDao custDao = new CustomerDao();
        listCustomer.addAll(custDao.get(LoginScene.username));

        BookDao bookDao = new BookDao();
        listBooks.addAll(bookDao.get());

        TableView<Books> tableBooks = new TableView<>();

        TableColumn<Books, String> column1 = new TableColumn<>("Judul Buku");
        TableColumn<Books, String> column2 = new TableColumn<>("Penulis");
        TableColumn<Books, String> column3 = new TableColumn<>("Kategori");
        TableColumn<Books, Integer> column4 = new TableColumn<>("Harga");
        TableColumn<Books, Integer> column5 = new TableColumn<>("Stock");

        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        column2.setCellValueFactory(new PropertyValueFactory<>("author"));
        column3.setCellValueFactory(new PropertyValueFactory<>("category"));
        column4.setCellValueFactory(new PropertyValueFactory<>("price"));
        column5.setCellValueFactory(new PropertyValueFactory<>("stock"));

        tableBooks.getColumns().addAll(column1, column2, column3, column4, column5);

        tableBooks.setItems(listBooks);

        Button btnBuy = new Button("Beli");
        btnBuy.setPrefWidth(200);
        
        btnBuy.setOnAction(v -> {
            bottomSide.getChildren().clear();
            Text transaksi = new Text("Input Jumlah Stok yang ingin dibeli");

            index = tableBooks.getSelectionModel().getSelectedIndex();
            if (index == -1 ) {
                bottomSide.getChildren().clear();
                Text noneStock = new Text("Sebelum melakukan pembelian, mohon pilih terlebih dahulu bukunya dari daftar yang tersedia. Terima kasih!");
                noneStock.setTextAlignment(TextAlignment.CENTER);

                Button back = new Button("Kembali ke daftar buku");
                back.setAlignment(Pos.CENTER);

                back.setOnAction(r -> {
                    bottomSide.getChildren().clear();
                    try {
                        showListBooks();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });

                VBox vIndex = new VBox(noneStock, back);
                vIndex.setSpacing(10);
                vIndex.setAlignment(Pos.CENTER);
                bottomSide.getChildren().add(vIndex);
            } else {
                int remainStock = listBooks.get(index).getStock();
                if (remainStock == 0){
                    bottomSide.getChildren().clear();
                    Text noneStock = new Text("Maaf, stok buku ini telah habis. Silakan cek kembali nanti atau jelajahi buku-buku lain yang tersedia");
                    noneStock.setTextAlignment(TextAlignment.CENTER);

                    Button back = new Button("Kembali ke daftar buku");
                    back.setAlignment(Pos.CENTER);

                    back.setOnAction(r -> {
                        bottomSide.getChildren().clear();
                        try {
                            showListBooks();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                    VBox vNoneStock = new VBox(noneStock, back);
                    vNoneStock.setSpacing(10);
                    vNoneStock.setAlignment(Pos.CENTER);
                    bottomSide.getChildren().add(vNoneStock);
                } else {
                    Button btnBeli = new Button("Beli");
                    btnBeli.setMaxWidth(80);
                    Button btnBatal = new Button("Batal");
                    btnBatal.setMaxWidth(80);

                    TextField jumlahStok = new TextField();
                    jumlahStok.setMaxWidth(400);

                    UnaryOperator<TextFormatter.Change> integerFilter = change -> {
                        String newText = change.getControlNewText();
                        if (newText.matches("\\d*")){
                            return change;
                        }
                        return null;
                    };
                    TextFormatter<Integer> textFormatter = new TextFormatter<>(new IntegerStringConverter(), null, integerFilter);
                    jumlahStok.setTextFormatter(textFormatter);

                    HBox ConfirmBtn = new HBox(btnBatal, btnBeli);
                    ConfirmBtn.setSpacing(5);
                    ConfirmBtn.setAlignment(Pos.CENTER);

                    VBox vLayoutTextField = new VBox(jumlahStok, ConfirmBtn);
                    vLayoutTextField.setSpacing(5);
                    vLayoutTextField.setAlignment(Pos.CENTER);

                    VBox vLayout = new VBox(transaksi, vLayoutTextField, ConfirmBtn);
                    vLayout.setSpacing(10);
                    vLayout.setAlignment(Pos.CENTER);
                    bottomSide.getChildren().add(vLayout);

                    btnBatal.setOnAction(j -> {
                        bottomSide.getChildren().clear();
                        try {
                            showListBooks();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });

                    btnBeli.setOnAction(i -> {
                        bottomSide.getChildren().clear();

                        if (Integer.parseInt(jumlahStok.getText()) == 0){
                            bottomSide.getChildren().clear();
                            Text alert = new Text("Masukkan jumlah stock dengan benar");
                            alert.setTextAlignment(TextAlignment.CENTER);

                            VBox vLayout1 = new VBox(transaksi, vLayoutTextField, alert, ConfirmBtn);
                            vLayout1.setSpacing(10);
                            vLayout1.setAlignment(Pos.CENTER);
                            bottomSide.getChildren().add(vLayout1);
                        } else if (Integer.parseInt(jumlahStok.getText()) > remainStock) {
                            bottomSide.getChildren().clear();
                            Text noneStock = new Text("Mohon maaf, jumlah barang yang ingin Anda beli melebihi persediaan stok yang tersedia");
                            noneStock.setTextAlignment(TextAlignment.CENTER);

                            Button back = new Button("Kembali ke daftar buku");
                            back.setAlignment(Pos.CENTER);

                            back.setOnAction(r -> {
                                bottomSide.getChildren().clear();
                                try {
                                    showListBooks();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                            VBox vNoneStock2 = new VBox(noneStock, back);
                            vNoneStock2.setSpacing(10);
                            vNoneStock2.setAlignment(Pos.CENTER);
                            bottomSide.getChildren().add(vNoneStock2);
                        } else {
                            Text inpPs = new Text("Masukkan Password");
                            inpPs.setTextAlignment(TextAlignment.CENTER);
            
                            PasswordField inpPass = new PasswordField();
                            inpPass.setAlignment(Pos.CENTER);
                            inpPass.setMaxWidth(400);
            
                            VBox vInp = new VBox(inpPs, inpPass);
                            vInp.setSpacing(8);
                            vInp.setAlignment(Pos.CENTER);
            
                            Button conf = new Button("Konfirmasi");
                            Button cancel = new Button("Batal");
                            HBox hLayout = new HBox(cancel, conf);
                            hLayout.setSpacing(10);
                            hLayout.setAlignment(Pos.CENTER);
            
                            Text passWrong = new Text("Password yang anda masukkan salah");
                            passWrong.setTextAlignment(TextAlignment.CENTER);
            
                            VBox vLayoutPass = new VBox(vInp, hLayout);
                            vLayoutPass.setAlignment(Pos.CENTER);
                            vLayoutPass.setSpacing(10);
                            bottomSide.getChildren().add(vLayoutPass);
                            bottomSide.setAlignment(Pos.CENTER);
            
                            cancel.setOnAction(k -> {
                                bottomSide.getChildren().clear();
                                try {
                                    showListBooks();
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            });
            
                            conf.setOnAction(l -> {
                                bottomSide.getChildren().clear();
            
                                String passDao = listCustomer.get(0).getPassword();
                                String password = inpPass.getText();
            
                                if (BCrypt.checkpw(password, passDao)){
                                    BookDao booksDao = new BookDao();
                                    int bookId = listBooks.get(index).getId();
                                    int stock = listBooks.get(index).getStock();
                                    String stockBuy = jumlahStok.getText();
                                    int stockBuyInt = Integer.parseInt(stockBuy);
                                    int uang = listCustomer.get(0).getUang();
                                    int harga = listBooks.get(index).getPrice();
                                    String username = listCustomer.get(0).getUsername();
                                    
                                    if (uang >= harga*stockBuyInt){
                                        try {
                                            booksDao.buyBook(bookId, stock, stockBuyInt, uang, harga, username);
                                        } catch (SQLException e) {
                                            e.printStackTrace();
                                        }
                                        TransactionsDao transactionsDao = new TransactionsDao();
                                        List<Transaction> listTransactions = new ArrayList<>();
                                        Date date = new Date();
                                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                        String formattedDate = sdf.format(date);
                                        listTransactions.add(new Transaction(listBooks.get(index).getId(), listCustomer.get(0).getid(), formattedDate, stockBuyInt));
                                        transactionsDao.insert(listTransactions);
                
                                        Text tTrans = new Text("Transaksi Berhasil! Terima kasih telah membeli buku di mini marketplace kami!");
                                        tTrans.setTextAlignment(TextAlignment.CENTER);
                
                                        Button toHome = new Button("Kembali ke Daftar Buku");
                                        toHome.setAlignment(Pos.CENTER);
                
                                        listCustomer.clear();
                                        listBooks.clear();
                                        listTransactions.clear();
                
                                        toHome.setOnAction(m -> {
                                            bottomSide.getChildren().clear();
                                            try {
                                                showListBooks();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        });
                
                                        VBox vBox = new VBox(tTrans, toHome);
                                        vBox.setSpacing(10);
                                        vBox.setAlignment(Pos.CENTER);
                
                                        bottomSide.getChildren().add(vBox);
                                    } else {
                                        bottomSide.getChildren().clear();
                                        Text tTrans = new Text("Transaksi Gagal! Saldo anda tidak cukup!");
                                        tTrans.setTextAlignment(TextAlignment.CENTER);

                                        Text text1 = new Text("Ingin menambah saldo?");
                                        Hyperlink addSaldo = new Hyperlink("klik disini");

                                        FlowPane flwPane = new FlowPane();
                                        flwPane.getChildren().addAll(text1, addSaldo);
                                        flwPane.setAlignment(Pos.CENTER);

                                        addSaldo.setOnAction(p -> {
                                            changeMenuStatus(listHboxMenu[1], false);
                                            changeMenuStatus(listHboxMenu[3], true);
                                            bottomSide.getChildren().clear();
                                            try {
                                                showBalance();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        });
                
                                        Button toHome = new Button("Kembali ke Daftar Buku");
                                        toHome.setAlignment(Pos.CENTER);

                                        toHome.setOnAction(w -> {
                                            bottomSide.getChildren().clear();
                                            try {
                                                showListBooks();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                        });

                                        VBox vBox2 = new VBox(tTrans, flwPane, toHome);
                                        vBox2.setSpacing(10);
                                        vBox2.setAlignment(Pos.CENTER);
                
                                        bottomSide.getChildren().add(vBox2);
                                    }
                                } else {
                                    bottomSide.getChildren().clear();
                                    VBox newvLayout = new VBox(vInp, passWrong, hLayout);
                                    newvLayout.setAlignment(Pos.CENTER);
                                    newvLayout.setSpacing(10);
                                    bottomSide.getChildren().add(newvLayout);
                                }
                            }); 
                        }  
                    });
                }
            }
        });
        bottomSide.setPadding(new Insets(7));
        bottomSide.getChildren().addAll(tableBooks, btnBuy);
        bottomSide.setSpacing(10);
        bottomSide.setAlignment(Pos.CENTER);
    }

    private void showHistoryPurchase() throws SQLException {
        bottomSide.getChildren().clear();
        TransactionsDao transactionsDao = new TransactionsDao();
        BookDao booksDao = new BookDao();
        CustomerDao custDao = new CustomerDao();

        ObservableList<Transaction> listTransactions = FXCollections.observableArrayList();
        listTransactions.addAll(transactionsDao.get());

        ObservableList<Books> listBooks = FXCollections.observableArrayList();
        listBooks.addAll(booksDao.get());

        TableView<CombinedData> tableCombined = new TableView<>();

        TableColumn<CombinedData, String> column1 = new TableColumn<>("Judul Buku");
        TableColumn<CombinedData, Integer> column2 = new TableColumn<>("Harga");
        TableColumn<CombinedData, String> column3 = new TableColumn<>("Tanggal");
        TableColumn<CombinedData, Integer> column4 = new TableColumn<>("Total Stock");

        column1.setCellValueFactory(new PropertyValueFactory<>("bookName"));
        column2.setCellValueFactory(new PropertyValueFactory<>("priceBook"));
        column3.setCellValueFactory(new PropertyValueFactory<>("date"));
        column4.setCellValueFactory(new PropertyValueFactory<>("stockBuy"));

        tableCombined.getColumns().addAll(column1, column2, column3, column4);

        List<Customer> listCustomers = new ArrayList<>();
        listCustomers.addAll(custDao.get(LoginScene.username));

        ObservableList<CombinedData> combinedDataList = FXCollections.observableArrayList();
        for (Transaction transaction : listTransactions) {
            boolean isUsernameMatched = false;
            for (Customer cust : listCustomers) {
                if (transaction.getId_customer() == cust.getid()) {
                    isUsernameMatched = true;
                    break;
                }
            }
            if (isUsernameMatched) {
                for (Books books : listBooks) {
                    if (transaction.getid_Book() == books.getId()) {
                        CombinedData combinedData = new CombinedData(books.getName(), books.getPrice(), transaction.getDate(), transaction.getStockPurchased());
                        combinedDataList.add(combinedData);
                        break;
                    }
                }
            }
        }

        tableCombined.setItems(combinedDataList);
        
        bottomSide.setPadding(new Insets(7));
        bottomSide.getChildren().addAll(tableCombined);
    }

    private void showBalance() throws SQLException {
        bottomSide.getChildren().clear();
        List<Customer> listCustomers = new ArrayList<>();
        CustomerDao custDao = new CustomerDao();
        listCustomers.addAll(custDao.get(LoginScene.username));

        Text infoSaldo = new Text("Saldo di Rekening Anda sebanyak Rp" + listCustomers.get(0).getUang());

        Button btnKlikTambah = new Button("Klik untuk Tambah Saldo");
        btnKlikTambah.setPrefWidth(400);

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
            vLayoutAll.setPrefHeight(480 - 65);
            vLayoutAll.setAlignment(Pos.CENTER);
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
                                vLayoutAllWithCorrectToken.setPrefHeight(480 - 65);
                                vLayoutAllWithCorrectToken.setSpacing(10);
                                vLayoutAllWithCorrectToken.setAlignment(Pos.CENTER);
                                bottomSide.getChildren().add(vLayoutAllWithCorrectToken);
                            } else {
                                bottomSide.getChildren().clear();
                                Text wrongToken = new Text("Token anda sudah digunakan");
                                wrongToken.setTextAlignment(TextAlignment.CENTER);
                                VBox vLayoutAllWithWrongToken = new VBox(newvLayout, wrongToken, newhLayout);
                                vLayoutAllWithWrongToken.setPrefHeight(480 - 65);
                                vLayoutAllWithWrongToken.setSpacing(10);
                                vLayoutAllWithWrongToken.setAlignment(Pos.CENTER);
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
                    vLayoutAllWithWrongToken.setPrefHeight(480 - 65);
                    vLayoutAllWithWrongToken.setAlignment(Pos.CENTER);
                    bottomSide.getChildren().add(vLayoutAllWithWrongToken);
                }
            });
        });

        VBox vLayout = new VBox(infoSaldo,btnKlikTambah);
        vLayout.setSpacing(10);
        vLayout.setPrefHeight(480 - 65);
        vLayout.setAlignment(Pos.CENTER);
        bottomSide.setPadding(new Insets(0));
        bottomSide.getChildren().add(vLayout);

    }
    private void showLogout() {
        for (int i = 0; i < listHboxMenu.length; i++){
            changeMenuStatus(listHboxMenu[i], false);
        }
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setContentText("Apakah anda yakin keluar?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            LoginScene loginScene = new LoginScene(stage);
            stage.setScene(loginScene.show());
        }
    }
}