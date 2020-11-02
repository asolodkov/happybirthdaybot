package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import utils.Constants;

public class ProgressIndicatorController {

    private boolean isRight = true;

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label progressLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    void initialize() {
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                for (int i = 0; i < 100; i++) {
                    progressBar.setProgress(i / 100.0);
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };

        task.setOnFailed(wse -> {
            wse.getSource().getException().printStackTrace();
        });

        task.setOnSucceeded(wse -> {
            String resourceForNextWindow = isRight ? "/view/success.fxml" : "/view/fail.fxml";
            FXMLLoader loaderProgress = new FXMLLoader();
            loaderProgress.setLocation(getClass().getResource(resourceForNextWindow));

            try {
                loaderProgress.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent rootProgress = loaderProgress.getRoot();
            Stage stageProgress = new Stage();
            stageProgress.setTitle(Constants.TITLE);
            stageProgress.getIcons().add(new Image(getClass().getResourceAsStream("/resources/pictures/icon3.jpg")));
            stageProgress.setScene(new Scene(rootProgress));
            stageProgress.show();
            // Конец отрисовки следующего окна
        });

        new Thread(task).start();
    }
}

