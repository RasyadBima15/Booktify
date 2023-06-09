package booktify.scene;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mindrot.jbcrypt.BCrypt;

import booktify.abstract_class.ShowScene;
import booktify.dao.CustomerDao;
import booktify.models.Customer;

public class RegisScene implements ShowScene{
    private Stage stage;

    public RegisScene(Stage stage){
        this.stage = stage;
    }

    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);
        scene.getStylesheets().add(getClass().getResource("/styles/style.css").toExternalForm());

        Text createAccount = new Text("Buat Akun");
        createAccount.setFont(Font.font("Britannic", 30));

        TextField tfUsername = new TextField();
        tfUsername.setMaxWidth(500);
        tfUsername.setMaxHeight(1000);
        tfUsername.setPromptText("Username");
        Label lbStrongUsername = new Label("Username harus mengandung huruf, angka dan tidak mengandung simbol lain");
        lbStrongUsername.setPadding(new Insets(0, 67, 0, 0));
        
        PasswordField tfPassword = new PasswordField();
        tfPassword.setMaxWidth(500);
        tfPassword.setMaxHeight(1000);
        tfPassword.setPromptText("Password");

        Label lbStrongPassword = new Label("Password harus memiliki setidaknya 8 karakter, termasuk huruf besar, huruf kecil, dan angka");
        Label lbUsernameExist = new Label("Username sudah digunakan! Mohon mencoba menggunakan username baru");
        lbUsernameExist.setPadding(new Insets(0, 75, 0, 0));

        Button btnRegis = new Button("Daftar");
        btnRegis.getStyleClass().add("btn-beli");
        Button btnBack = new Button("Kembali");
        btnBack.getStyleClass().add("btn-beli");

        HBox button = new HBox(btnBack, btnRegis);
        button.setSpacing(10);
        button.setAlignment(Pos.CENTER);

        VBox vLayout = new VBox(createAccount, tfUsername, tfPassword, button);
        vLayout.setSpacing(10);
        vLayout.getStyleClass().add("login");
        spLayout.getChildren().add(vLayout);
        vLayout.setAlignment(Pos.CENTER);

        btnRegis.setOnAction(v -> {
            CustomerDao custDao = new CustomerDao();
            String username = tfUsername.getText();
            String password = tfPassword.getText();

            // Menerapkan validasi regex pada username
            String usernamePattern = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]+$";
            Pattern pattern = Pattern.compile(usernamePattern);
            Matcher matcher = pattern.matcher(username);
            boolean isUsernameValid = matcher.matches();

            // Menerapkan validasi regex pada password
            String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$";
            pattern = Pattern.compile(passwordPattern);
            matcher = pattern.matcher(password);
            boolean isPasswordValid = matcher.matches();
            
            try {
                boolean isUsernameExist = custDao.checkUsernameExist(username);
                if(isUsernameValid && !isUsernameExist && isPasswordValid){
                    List<Customer> listCustomer = new ArrayList<>();
                    //tambahkan enkripsi password user biar aman
                    String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    
                    Customer customer = new Customer(username, hashedPassword, 0, 1);
                    listCustomer.add(customer);
                    custDao.insert(listCustomer);
    
                    LoginScene loginScene = new LoginScene(stage);
                    stage.setScene(loginScene.show());
                } else if (isUsernameValid && isUsernameExist && !isPasswordValid){
                    spLayout.getChildren().clear();
                    VBox vLayoutWithLabel = new VBox(createAccount, tfUsername, lbUsernameExist, tfPassword, lbStrongPassword, button);
                    vLayoutWithLabel.getStyleClass().add("login");
                    vLayoutWithLabel.setSpacing(10);
                    spLayout.getChildren().add(vLayoutWithLabel);
                    vLayoutWithLabel.setAlignment(Pos.CENTER); 
                    show();
                } else if (isUsernameValid && isUsernameExist && isPasswordValid){
                    spLayout.getChildren().clear();
                    VBox vLayoutWithoutPassLabel = new VBox(createAccount, tfUsername, lbUsernameExist, tfPassword, button);
                    vLayoutWithoutPassLabel.getStyleClass().add("login");
                    vLayoutWithoutPassLabel.setSpacing(10);
                    spLayout.getChildren().add(vLayoutWithoutPassLabel);
                    vLayoutWithoutPassLabel.setAlignment(Pos.CENTER); 
                    show();
                } else if (!isUsernameValid && isPasswordValid){
                    spLayout.getChildren().clear();
                    VBox vLayoutWithLabel = new VBox(createAccount, tfUsername, lbStrongUsername, tfPassword, button);
                    vLayoutWithLabel.getStyleClass().add("login");
                    vLayoutWithLabel.setSpacing(10);
                    spLayout.getChildren().add(vLayoutWithLabel);
                    vLayoutWithLabel.setAlignment(Pos.CENTER); 
                    show();
                } else if (isUsernameValid && !isPasswordValid){
                    spLayout.getChildren().clear();
                    VBox vLayoutWithLabel = new VBox(createAccount, tfUsername, tfPassword, lbStrongPassword, button);
                    vLayoutWithLabel.getStyleClass().add("login");
                    vLayoutWithLabel.setSpacing(10);
                    spLayout.getChildren().add(vLayoutWithLabel);
                    vLayoutWithLabel.setAlignment(Pos.CENTER); 
                    show();
                } else if (!isUsernameValid && !isPasswordValid){
                    spLayout.getChildren().clear();
                    VBox vLayoutWithLabel = new VBox(createAccount, tfUsername, lbStrongUsername, tfPassword, lbStrongPassword, button);
                    vLayoutWithLabel.getStyleClass().add("login");
                    vLayoutWithLabel.setSpacing(10);
                    spLayout.getChildren().add(vLayoutWithLabel);
                    vLayoutWithLabel.setAlignment(Pos.CENTER); 
                    show();
                }
                
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        btnBack.setOnAction(v -> {
            LoginScene loginScene = new LoginScene(stage);
            stage.setScene(loginScene.show());
        });

        return scene;
    }
}
