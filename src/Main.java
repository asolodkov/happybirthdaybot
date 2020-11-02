import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.Constants;

import java.awt.*;
//Создание ветки dev

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/hello.fxml"));
        primaryStage.setTitle(Constants.TITLE);
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("resources/pictures/icon3.jpg")));
        Scene primaryScene = new Scene(root, 600, 400);
        primaryStage.setScene(primaryScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
