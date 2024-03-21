//package com.oopgroup3.workspace_manager;
//
//import javafx.application.Application;
//import javafx.stage.FileChooser;
//import javafx.stage.Stage;
//import javafx.stage.FileChooser.ExtensionFilter;
//import java.io.File;
//
//public class FileChooser extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Executable File");
//
//        // Set the filter to accept only .exe files
//        ExtensionFilter extFilter = new ExtensionFilter("Executable Files (*.exe)", "*.exe");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Display the dialog box
//        File selectedFile = fileChooser.showOpenDialog(primaryStage);
//        if (selectedFile != null) {
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//            // Do something with the selected .exe file
//        } else {
//            System.out.println("No file selected.");
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
