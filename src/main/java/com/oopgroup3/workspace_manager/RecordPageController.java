package com.oopgroup3.workspace_manager;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RecordPageController implements Initializable {
    @FXML
    private VBox recordBox;

    private SceneController sceneController;
    // Method to inject SceneController
    public void injectSceneController(SceneController sceneController){
        this.sceneController = sceneController;
    }

    List<Records> ls_records;
    @Override
    public void initialize(URL location, ResourceBundle resources){
        ls_records = new ArrayList<>(records());
        // Load out according to list
        try{
            for (Records records : ls_records){
                // Load the custom card from other .fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oopgroup3/workspace_manager/Record_Page_Card.fxml"));
                // set the root control (Pane) of Custom Card to be the loaded UI
                Pane customCard = loader.load();
                // Access the CardController instance
                CardController cardController = loader.getController();
                // set the data of the Custom Card using setData method in CardController.java
                cardController.setRecordData(records);
                System.out.println("printLoadDynamicRecordCards");
            }
        }catch (IOException e) {
            System.out.println("printStackTrace");
            e.printStackTrace();
        }
    }

        // Loop through just to display
    private List<Records> records(){
        List<Records> ls = new ArrayList<>();

        // Identify the file
        String recordPath = "/com/oopgroup3/workspace_manager/Records/Applications.txt";
        File file = new File(recordPath);

        // Iterate through text file. split line via ",", then return into List<Workspace> ls = new ArrayList<>()
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // create a new instance of records class and assign with another workspace instance
                Records records = getRecords(parts);

                // add the records instance to Main's workspace list
                ls.add(records);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ls;
    }

    private Records getRecords(String[] parts) {
        // Assuming at least one element is required
        StringBuilder pathBuilder = new StringBuilder();
        String appName = "";

        // Loop through each part, including the last one
        for (int i = 0; i < parts.length; i++) {
            pathBuilder.append(parts[i]);
            if (i < parts.length - 1) {
                pathBuilder.append("/");
            }
        }
        String path = pathBuilder.toString();

        // Extracting the last part as appName
        if (parts.length > 0) {
            String lastPart = parts[parts.length - 1];
            // Splitting the last part by "/"
            String[] lastPartParts = lastPart.split("/");
            // Using the last part or second last part as appName
            if (lastPartParts.length > 0) {
                appName = lastPartParts[lastPartParts.length - 1];
            }
        }

        // Records set variables
        return new Records(appName, path);
    }

    // Open file dialog when pressed openRecordRecord
    public void openRecordRecord(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Executable File");

        javafx.stage.FileChooser.ExtensionFilter extFilter = new javafx.stage.FileChooser.ExtensionFilter("Executable Files (*.exe)", "*.exe");
        fileChooser.getExtensionFilters().add(extFilter);

        // Save chosen option into selectedFile
        File selectedFile = fileChooser.showOpenDialog(null); // Passing null as owner
        if (selectedFile != null) {
            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            // Do something with the selected .exe file
            writePath(selectedFile.getPath());
        } else {
            System.out.println("No file selected.");
        }
    }

    // Write string in Path into Application.txt
    public void writePath(String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/com/oopgroup3/workspace_manager/Records/Applications.txt", true))) {
            writer.write(path);
            writer.newLine();
        }
    }

    // Return string in Path from Application.txt
    public String[] readPaths() throws IOException {
        File file = new File("/com/oopgroup3/workspace_manager/Records/Applications.txt");
        if (!file.exists()) {
            return new String[0];
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("/com/oopgroup3/workspace_manager/Records/Applications.txt"))) {
            return reader.lines().toArray(String[]::new);
        }
    }

    // Execute single App
    private void executeApplication(String path) {
        try {
            Runtime.getRuntime().exec(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Execuate the remaining number of apps
    public void executeApplications() {
        try {
            RecordPageController recordPageController = new RecordPageController();
            String[] paths = recordPageController.readPaths();
            for (String path : paths) {
                executeApplication(path);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to handle switching to the main page
    @FXML
    private void switchToMain(ActionEvent event) throws IOException {
        System.out.println("RecordPageController switch pressed");
        sceneController.switchToMain(event);
    }

}
