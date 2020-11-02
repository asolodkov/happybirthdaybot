package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.Constants;

public class HelloController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button getCongratulation;

    @FXML
    void initialize() {
        getCongratulation.setOnAction(actionEvent -> {

            // Отрисовка следующего окна
            getCongratulation.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/test.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle(Constants.TITLE);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/resources/pictures/icon3.jpg")));
            stage.setScene(new Scene(root));
            stage.show();
            // Конец отрисовки следующего окна
        });
    }
}

