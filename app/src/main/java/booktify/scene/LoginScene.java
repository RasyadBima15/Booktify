package booktify.scene;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import booktify.abstract_class.Login;
import booktify.abstract_class.ShowScene;
import booktify.dao.CustomerDao;
import booktify.models.Customer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginScene extends Login implements ShowScene {
    private Stage stage;

    public LoginScene(Stage stage) {
        this.stage = stage;
    }

    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        ImageView ivLogo = new ImageView("/images/bookt.png");
        ivLogo.setFitHeight(125);
        ivLogo.setFitWidth(100);

        Text textBrand = new Text("Booktify");
        textBrand.setFont(Font.font("Britannic", 25));

        Label lbDesc = new Label("Aplikasi belanja buku yang mudah dan efisien bagi pengguna");
        lbDesc.setStyle("-fx-font-size: 14px;");

        VBox vUpper = new VBox(ivLogo, textBrand, lbDesc);
        vUpper.setAlignment(Pos.CENTER);
        vUpper.setSpacing(3);

        Text textLogin = new Text("Login");
        textLogin.setFont(Font.font("Britannic", 25));

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

        VBox vBottom1 = new VBox(textLogin, tfUsername, tfPass);
        vBottom1.setAlignment(Pos.CENTER);
        vBottom1.setSpacing(10);

        VBox vBottom2 = new VBox(btnLogin, flwPane);
        vBottom2.setAlignment(Pos.CENTER);
        vBottom2.setSpacing(5);

        VBox vLayout = new VBox(vUpper, vBottom1, vBottom2);
        vLayout.setSpacing(10);
        spLayout.getChildren().add(vLayout);
        vLayout.setAlignment(Pos.CENTER);
        vLayout.getStyleClass().add("login");
        vLayout.setPadding(new Insets(0, 0, 60, 0));

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
                    try {
                        LoginScene.username = usernameDao;
                        stage.setScene(scene);
                        homeScene.show();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    spLayout.getChildren().clear();
                    VBox vLayout2 = new VBox(vUpper, vBottom1, lbLoginFailed, vBottom2);
                    vLayout2.setSpacing(10);
                    spLayout.getChildren().add(vLayout2);
                    spLayout.getStyleClass().add("login");
                    vLayout2.setAlignment(Pos.CENTER);
                    vLayout2.setPadding(new Insets(0, 0, 60, 0));
                    
                    lbLoginFailed.setText("Password tidak valid!");
                    
                }
            } else {
                spLayout.getChildren().clear();
                VBox vLayout3 = new VBox(vUpper, vBottom1, lbLoginFailed, vBottom2);
                vLayout3.setSpacing(10);
                spLayout.getChildren().add(vLayout3);
                spLayout.getStyleClass().add("login");
                vLayout3.setAlignment(Pos.CENTER);
                vLayout3.setPadding(new Insets(0, 0, 60, 0));
        
                lbLoginFailed.setText("Username atau password tidak valid!");
            }
        });

        stage.setScene(scene);
        return scene;
    }
}