package com.oopgroup3.workspace_manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.RecordComponent;
import java.util.Objects;

public class Main extends Application {
    // Load up main page
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oopgroup3/workspace_manager/Main_Page.fxml"));
            Parent root = loader.load();

            // Get the controller instance
            // Remember to change this controller
            MainPageController controller = loader.getController();
            // Pass the stage to the controller
            controller.setStage(stage);

            Scene scene = new Scene(root);
            stage.setResizable(false); // disable user from maximizing
            stage.setTitle("Workspace Manager");
            stage.setScene(scene);
            stage.show();
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oopgroup3/workspace_manager/Record_Page.fxml")));
//            Scene scene = new Scene(root);
//            stage.setResizable(false); // disable user from maximize
//            stage.setTitle("Workspace Manager");
//            stage.setScene(scene);
//            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


//public class Main extends Application {
//
//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        // Load the Scene.fxml file
//        FXMLLoader sceneLoader = new FXMLLoader(getClass().getResource("Scene.fxml"));
//        Parent sceneRoot = sceneLoader.load();
//        SceneController sceneController = sceneLoader.getController();
//
//        // Load the MainPage.fxml file
//        FXMLLoader mainPageLoader = new FXMLLoader(getClass().getResource("Main_Page.fxml"));
//        Parent mainPageRoot = mainPageLoader.load();
//        MainPageController mainPageController = mainPageLoader.getController();
//
//        // Load the RecordPage.fxml file
//        FXMLLoader recordPageLoader = new FXMLLoader(getClass().getResource("Record_Page.fxml"));
//        Parent recordPageRoot = recordPageLoader.load();
//        RecordPageController recordPageController = recordPageLoader.getController();
//
//        // Inject SceneController into MainPageController and RecordPageController
//        mainPageController.injectSceneController(sceneController);
//        recordPageController.injectSceneController(sceneController);
//
//        // Set the initial scene to MainPage.fxml
//        sceneController.setScene(new Scene(mainPageRoot));
//
//        // Set the stage and show
//        primaryStage.setScene(sceneController.getScene());
//        primaryStage.setTitle("Main Page");
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}