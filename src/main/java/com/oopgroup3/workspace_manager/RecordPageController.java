package com.oopgroup3.workspace_manager;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class RecordPageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private VBox recordBox;
    @FXML
    private TextField workspaceNameField;


    // Method to set the stage (GPT)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    private SceneController sceneController;
//    // Method to inject SceneController
//    public void injectSceneController(SceneController sceneController){
//        this.sceneController = sceneController;
//    }

    List<Records> ls_records;
    List<String> TempList = new ArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources){

        // !!!!!!!!
        //NEED to implement a checking of .txt file. If exist only display if on no need

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

                recordBox.setSpacing(2.5);

                // add the ca to the Main's VBox
                recordBox.getChildren().add(customCard);

            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    //------------------------------- Extarct to display on templist --------------------------------
    private List<Records> records(){
        List<Records> ls = new ArrayList<>();

        //Check if Application.txt exists


        // Identify the file
        String recordPath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Applications/Applications.txt";
        File file = new File(recordPath);

        // Iterate through list, split lines and parse them
        for (String element : TempList){
            System.out.println(element);
            Records records = getRecords(element.trim()); // Trim each part to remove extra spaces
            ls.add(records);
        }

        return ls;
    }
    //--------------------------------------------------------------------------------------------------

//    private List<Records> records(){
//        List<Records> ls = new ArrayList<>();
//
//        //Check if Application.txt exists
//
//
//        // Identify the file
//        String recordPath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Applications/Applications.txt";
//        File file = new File(recordPath);
//
//        // Check if the file exists
//        if (!file.exists()) {
//            System.out.println("File does not exist.");
//            // You can add code here to handle the case where the file doesn't exist
//            return ls; // Return an empty list since there are no records to read
//        }
//
//        // Iterate through text file, split lines and parse them
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                // Splitting the line by the comma
//                String[] parts = line.split(",");
//                for (String part : parts) {
//                    Records records = getRecords(part.trim()); // Trim each part to remove extra spaces
//                    ls.add(records);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(ls);
//        return ls;
//    }

    private Records getRecords(String part) {

        System.out.println("getRecords called");
        // Assuming at least one element is required
        StringBuilder pathBuilder = new StringBuilder();
        String appName = "";

        // Extracting the appName from the last part
        int lastIndex = part.lastIndexOf(File.separator);
        if (lastIndex != -1) {
            appName = part.substring(lastIndex + 1);
            pathBuilder.append(part, 0, lastIndex); // Build path excluding the appName
        } else {
            appName = part; // If no separator found, consider the whole part as appName
        }
        String path = pathBuilder.toString();

        // Records set variables
        System.out.println("AppName: " + appName);
        System.out.println("Path: " + path);
        return new Records(appName, path);
    }


//___________________________________ 4/3/2024 _____________________________________________________________________
//        // Loop through just to display
//    private List<Records> records(){
//        List<Records> ls = new ArrayList<>();
//
//        // Identify the file
//        String recordPath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Applications.txt";
//        File file = new File(recordPath);
//        // Iterate through text file. split line via ",", then return into List<Workspace> ls = new ArrayList<>()
//        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] parts = line.split(",");
//                // create a new instance of records class and assign with another workspace instance
//                Records records = getRecords(parts);
//
//                // add the records instance to Main's workspace list
//                ls.add(records);
//
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return ls;
//    }
//
//    private Records getRecords(String[] parts) {
//        System.out.println("Entered get records");
//        // Assuming at least one element is required
//        StringBuilder pathBuilder = new StringBuilder();
//        String appName = "";
//
//        // Loop through each part, excluding the last one
//        for (int i = 0; i < parts.length - 1; i++) {
//            pathBuilder.append(parts[i]);
//            pathBuilder.append(File.separator); // Using File.separator for platform independence
//        }
//        String path = pathBuilder.toString();
//
//        // Extracting the last part as appName
//        if (parts.length > 0) {
//            String lastPart = parts[parts.length - 1];
//            // Extracting the appName from the last part
//            int lastIndex = lastPart.lastIndexOf(File.separator);
//            if (lastIndex != -1) {
//                appName = lastPart.substring(lastIndex + 1);
//            } else {
//                appName = lastPart; // If no separator found, consider the whole part as appName
//            }
//        }
//
//        // Records set variables
//        System.out.println("AppName:" + appName);
//        System.out.println("Path:" + path);
//        return new Records(appName, path);
//    }
// __________________________________________________________________________________________________________________

    // _________________________________ To write and read files with dialogbox _________________________________________________________
//    // Open file dialog when pressed openRecordRecord
//    public void openRecordRecord(ActionEvent event) throws IOException {
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Executable File");
//
//        javafx.stage.FileChooser.ExtensionFilter extFilter = new javafx.stage.FileChooser.ExtensionFilter("Executable Files (*.exe)", "*.exe");
//        fileChooser.getExtensionFilters().add(extFilter);
//
//        // Save chosen option into selectedFile
//        File selectedFile = fileChooser.showOpenDialog(null); // Passing null as owner
//        if (selectedFile != null) {
//            System.out.println("Selected file: " + selectedFile.getAbsolutePath());
//            // Do something with the selected .exe file
//            writePath(selectedFile.getPath());
//        } else {
//            System.out.println("No file selected.");
//        }
//    }

//    // Write string in Path into Application.txt
//    public void writePath(String path) throws IOException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter("/com/oopgroup3/workspace_manager/Records/Applications.txt", true))) {
//            writer.write(path);
//            writer.newLine();
//        }
//    }
//
//    // Return string in Path from Application.txt
//    public String[] readPaths() throws IOException {
//        File file = new File("/com/oopgroup3/workspace_manager/Records/Applications.txt");
//        if (!file.exists()) {
//            return new String[0];
//        }
//        try (BufferedReader reader = new BufferedReader(new FileReader("/com/oopgroup3/workspace_manager/Records/Applications.txt"))) {
//            return reader.lines().toArray(String[]::new);
//        }
//    }
//
//    // Execute single App
//    private void executeApplication(String path) {
//        try {
//            Runtime.getRuntime().exec(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    //Execuate the remaining number of apps
//    public void executeApplications() {
//        try {
//            RecordPageController recordPageController = new RecordPageController();
//            String[] paths = recordPageController.readPaths();
//            for (String path : paths) {
//                executeApplication(path);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

// ______________________________________________________ Buttons Functions ____________________________________________
    // Method to handle switching to the main page
    @FXML
    private void switchToMain(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root = loader.load();
        scene.setRoot(root);
    }

    @FXML
    private void openDialogBox(ActionEvent event) {
        System.out.println("Recording button pressed");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Executable File");

        // Set extension filter for .exe files
        System.out.println("Set extension filter for .exe files");
        ExtensionFilter extFilter = new ExtensionFilter("Executable Files (*.exe)", "*.exe");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show open file dialog
        System.out.println("Show open file dialog");
        File selectedFile = fileChooser.showOpenDialog(stage);

        // If a file is selected
        if (selectedFile != null) {

            //Save into temporary list
            TempList.add(selectedFile.getAbsolutePath());

            // Write selected file path to application.txt
            //writePath(selectedFile.getAbsolutePath());   <-- Wirte file code
            // Show confirmation message
            showAlert(Alert.AlertType.CONFIRMATION, "File Selected", "Selected file: " + selectedFile.getAbsolutePath());

            // Refresh the FXML page
            //refreshPage();
            System.out.println(TempList);
        } else {
            // Show error message if no file selected
            showAlert(Alert.AlertType.ERROR, "No File Selected", "No file selected.");
        }
    }

    // Method to save temporary list into a text file
    @FXML
    private void saveList(ActionEvent event){

        // Iterate through temporary list to write into file
        for (String element : TempList){
            writePath(element);
        }

        String nameField = workspaceNameField.getText();
        String filePath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Records.txt";

        String data = "\n" + nameField + ",\"dd/mm/yyyy\",\"Path and shit\",";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            // Write the data to the file
            writer.write(data);

            // Flush the writer
            writer.flush();

            System.out.println("Data has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }


    }


    // Method to write the selected file path to application.txt
    private void writePath(String path) {
        // Get Workspace Namefield text
        // Create a TextField
        String nameField = workspaceNameField.getText(); // Get text from the TextField
        System.out.println("Name Field: " + workspaceNameField);

        if (nameField == null || nameField.isEmpty()) {
            // Show an alert if the TextField is null or empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("TextField is null or empty!");
            alert.showAndWait();

            while (nameField == null || nameField.isEmpty()) {
                try {
                    Thread.sleep(1000); // Sleep for a second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nameField = workspaceNameField.getText();
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/com/oopgroup3/workspace_manager/Records/Applications/" + nameField +".txt", true))) {
            writer.write(path+ ",");
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., show an error dialog
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to write file path.");
        }
        System.out.println("Filename: " + nameField + ".txt created.");
    }

    // Method to show an alert dialog
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.showAndWait();
    }

    // Method to refresh the FXML page
    private void refreshPage() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oopgroup3/workspace_manager/Record_Page.fxml")));
            Scene scene = new Scene(root);
            stage.setResizable(false); // disable user from maximizing
            stage.setTitle("Workspace Manager");
            stage.setScene(scene);
            stage.show();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Record_Page.fxml"));
//            loader.setController(this); // Set the controller to this instance
//            Node root = loader.load();
//            scene.setRoot((Parent) root);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception appropriately, e.g., show an error dialog
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to refresh page.");
        }
    }

}
