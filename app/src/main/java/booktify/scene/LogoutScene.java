package booktify.scene;

import javax.management.BadBinaryOpValueExpException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
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

public class LogoutScene {
    private Stage stage;

    public LogoutScene(Stage stage) {
        this.stage = stage;
    }

    public Scene show() {
        StackPane spLayout = new StackPane();
        Scene scene = new Scene(spLayout, 640, 480);

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to Logout!");
        alert.setContentText("Do you want to save before exiting?");

//         alert.setOnAction(v -> {
//             OutScene outScene = new OutScene(stage);
//             stage.setScene(outScene.show());
//         });

//         // if(alert.showAndWait().get() == ButtonType.OK){
//         // stage = (Stage)scenePane.getScene().getWindow();
//         // // System.out.println("you successfully logged out!");
//         // stage.close();
//         // }

        return scene;
    }

}
