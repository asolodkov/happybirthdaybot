package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.Constants;

public class TestController {

//    public static void setIsRight(boolean isRight) {
////        TestController.isRight = isRight;
////    }
////
////    public static boolean isIsRight() {
////        return isRight;
////    }
////
////    private static boolean isRight = true;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button startValidate;

    @FXML
    private TextField name;

    @FXML
    private TextField area;

    @FXML
    private TextField group;

    @FXML
    private TextField framework;

    @FXML
    private TextField dotaVersion;

    @FXML
    void initialize() {
        startValidate.setOnAction(actionEvent -> {
            boolean isRight = true;
            String resourceForNextWindow = "";
            try {
                String nameToValidate = name.getText().trim();
                String areaToValidate = area.getText().trim();
                String groupToValidate = group.getText().trim();
                String frameworkToValidate = framework.getText().trim();
                String dotaVersionToValidate = dotaVersion.getText().trim();

                // Проверка введенного имени
                nameToValidate = nameToValidate.toLowerCase();
                boolean testBool = nameToValidate.equals("алексей");
                if (nameToValidate != null && nameToValidate != "") {
                    if (!nameToValidate.equals("алексей")) {
                        isRight = false;
                    }
                }

                // Проверка введенноого района обитания
                areaToValidate = areaToValidate.toLowerCase();
                if (areaToValidate != null && areaToValidate != "") {
                    if (!areaToValidate.equals("шуист")) {
                        isRight = false;
                    }
                }

                // Проверка введенной группы
                groupToValidate = groupToValidate.toLowerCase();
                if (groupToValidate != null && groupToValidate != "") {
                    if (!groupToValidate.equals("07уа1")) {
                        isRight = false;
                    }
                }

                // Проверка введенного фреймворка
                frameworkToValidate = frameworkToValidate.toLowerCase();
                if (frameworkToValidate != null && frameworkToValidate != "") {
                    if (!frameworkToValidate.equals("spring mvc")) {
                        isRight = false;
                    }
                }

                // Проверка введенной версии доты
                dotaVersionToValidate = dotaVersionToValidate.toLowerCase();
                if (dotaVersionToValidate != null && dotaVersionToValidate != "") {
                    if (!dotaVersionToValidate.contains("5.84")) {
                        isRight = false;
                    }
                }

                if (!isRight) {
                    resourceForNextWindow = "/view/fail.fxml";
                } else {
                    resourceForNextWindow = "/view/success.fxml";
                }

            } catch (Exception ex) {
                resourceForNextWindow = "/view/fail.fxml";
            }

            finally {
                resourceForNextWindow = "/view/progressIndicator.fxml"; // :TODO доделать прогрессбар
                // Отрисовка следующего окна
                startValidate.getScene().getWindow().hide();

                FXMLLoader loaderTest = new FXMLLoader();
                loaderTest.setLocation(getClass().getResource(resourceForNextWindow));

                try {
                    loaderTest.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent rootTest = loaderTest.getRoot();
                Stage stageTest = new Stage();
                stageTest.setTitle(Constants.TITLE);
                stageTest.getIcons().add(new Image(getClass().getResourceAsStream("/resources/pictures/icon3.jpg")));
                stageTest.setScene(new Scene(rootTest));
                ProgressIndicatorController progressIndicatorController = loaderTest.getController();
                progressIndicatorController.setIsRight(isRight);
                stageTest.show();
                // Конец отрисовки следующего окна

                // TODO Временное решение, доделать, чтобы окно с прогресс баром закрывалось само, а не по таймеру
                PauseTransition delay = new PauseTransition(Duration.seconds(4));
                delay.setOnFinished(event -> stageTest.close());
                delay.play();
            }
        });
    }
}

