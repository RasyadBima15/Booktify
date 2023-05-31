package booktify.scene;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import booktify.dao.CustomerDao;
import booktify.models.Customer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene {
    private Stage stage;

    public LoginScene(Stage stage){
        this.stage = stage;
    }

    public Scene show() {
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
        space.setPrefHeight(10);

        Text textLogin = new Text("Login");

        TextField tfUsername = new TextField();
        tfUsername.setMaxWidth(380);
        tfUsername.setMaxHeight(1000);
        tfUsername.setPromptText("Username");
        PasswordField tfPass = new PasswordField();
        tfPass.setMaxWidth(380);
        tfPass.setMaxHeight(1000);
        tfPass.setPromptText("Password");

        Label lbLoginFailed = new Label("Username atau password tidak valid!");
        lbLoginFailed.setAlignment(Pos.CENTER);

        Button btnLogin = new Button("Masuk");
        Text textRegis = new Text("Belum punya akun? ayo");
        Hyperlink regis = new Hyperlink("daftar");

        FlowPane flwPane = new FlowPane();
        flwPane.getChildren().addAll(textRegis, regis);
        flwPane.setAlignment(Pos.CENTER);

        regis.setOnAction(v -> {
            RegisScene regisScene = new RegisScene(stage);
            stage.setScene(regisScene.show());
        });

        btnLogin.setOnAction(v -> {
            List<Customer> listCustomer = new ArrayList<>();
            CustomerDao cusDao = new CustomerDao();
            String username = tfUsername.getText();
            String password = tfPass.getText();
            try {
                listCustomer.addAll(cusDao.get(username));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (!listCustomer.isEmpty()) {
                String usernameDao = listCustomer.get(0).getUsername();
                String hashedPassword = listCustomer.get(0).getPassword();
        
                if (username.equals(usernameDao) && BCrypt.checkpw(password, hashedPassword)) {
                    HomeScene homeScene = new HomeScene(stage);
                    stage.setScene(homeScene.show());
                } else {
                    spLayout.getChildren().clear();
                    VBox vLayout = new VBox(ivLogo, textBrand, lbDesc, space, textLogin, tfUsername, tfPass, lbLoginFailed, btnLogin, flwPane);
                    vLayout.setSpacing(10);
                    spLayout.getChildren().add(vLayout);
                    vLayout.setAlignment(Pos.CENTER);
                    
                    lbLoginFailed.setText("Password tidak valid!");
                    
                }
            } else {
                spLayout.getChildren().clear();
                VBox vLayout = new VBox(ivLogo, textBrand, lbDesc, space, textLogin, tfUsername, tfPass, lbLoginFailed, btnLogin, flwPane);
                vLayout.setSpacing(10);
                spLayout.getChildren().add(vLayout);
                vLayout.setAlignment(Pos.CENTER);
        
                lbLoginFailed.setText("Username atau password tidak valid!");
            }
        });

        VBox vLayout = new VBox(ivLogo, textBrand, lbDesc, space, textLogin, tfUsername, tfPass, btnLogin, flwPane );
        vLayout.setSpacing(10);
        spLayout.getChildren().add(vLayout);
        vLayout.setAlignment(Pos.CENTER);

        stage.setScene(scene);
        return scene;
    }
}
