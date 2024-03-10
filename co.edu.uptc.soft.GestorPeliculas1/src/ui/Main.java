package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Image icon = new Image("/Img/video.png");
        primaryStage.getIcons().add(icon);

        Parent root = FXMLLoader.load(getClass().getResource("/application/EscenaPrincipal.fxml"));
        primaryStage.setTitle("SJ MOVIES");
        primaryStage.setScene(new Scene(root, 1510, 790));
        
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
