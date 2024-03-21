//package com.oopgroup3.workspace_manager;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//public class SceneManager {
//
//    private Stage stage;
//
//    public SceneManager(Stage stage) {
//        this.stage = stage;
//    }
//
//    public void switchToMain() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Page.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//
//        // Set SceneManager reference for controllers
//        SceneController sceneController = loader.getController();
//        sceneController.setSceneManager(this);
//    }
//
//    public void switchToRecord() throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Record_Page.fxml"));
//        Parent root = loader.load();
//        Scene scene = new Scene(root);
//        stage.setScene(scene);
//
//        // Set SceneManager reference for controllers
//        SceneController sceneController = loader.getController();
//        sceneController.setSceneManager(this);
//    }
//}
//
