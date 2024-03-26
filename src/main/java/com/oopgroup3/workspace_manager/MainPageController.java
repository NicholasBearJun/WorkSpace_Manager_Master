package com.oopgroup3.workspace_manager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Label WorkSpaceName;
    @FXML
    private VBox cardBox;
    @FXML
    private Button btnToRecord;

    // Method to set the stage (GPT)
    public void setStage(Stage stage) {
        this.stage = stage;
    }

//    private SceneController sceneController;
//
//    //inject SceneController
//    public void injectSceneController(SceneController sceneController){
//        this.sceneController = sceneController;
//    }

    // List variable initialization for cards
    List<Workspace> ls_workspaces;

    // Initialize and place for CardController
    @Override
    public void initialize(URL location, ResourceBundle resources){
        // assign the value of new ArrayList as the return value of method
        ls_workspaces = new ArrayList<>(workspaces());

        try{
            // Load every workspace into a card
            for (Workspace workspace : ls_workspaces){
                // Load the custom card from other .fxml
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oopgroup3/workspace_manager/Main_Page_Card.fxml"));
                // set the root control (Pane) of Custom Card to be the loaded UI
                Pane customCard = loader.load();

                // Access the CardController instance
                CardController cardController = loader.getController();

                // set the data of the Custom Card using setData method in CardController.java
                cardController.setWorkspaceData(workspace);

                cardBox.setSpacing(2.5);

                // add the ca to the Main's VBox
                cardBox.getChildren().add(customCard);

                System.out.println("Main page completed");
                System.out.println(ls_workspaces);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    // loop through workspace
    private List<Workspace> workspaces(){
        List<Workspace> ls = new ArrayList<>();

        // Identify the file
        String recordPath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Records.txt";
        File file = new File(recordPath);

        // Iterate through text file. split line via ",", then return into List<Workspace> ls = new ArrayList<>()
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    // create a new instance of Workspace class and assign with another workspace instance
                    Workspace workspace = getWorkspace(parts);

                    // add the workspace instance to Main's workspace list
                    ls.add(workspace);

                } else {
                    System.out.println("Invalid line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.print(ls);
        return ls;
    }

    // Method to handle the separation of data from text file's readLine
    private Workspace getWorkspace(String[] parts) {
        String name = parts[0];
        String date = parts[1];

        // workspace set variables
        System.out.print("Name:" + name);
        System.out.print("Date:" + date);
        return new Workspace(name,date); // return a instance of Workspace with constructor
    }


    // ______________________________________________________ Buttons Functions ____________________________________________
    // Change page to Record Page
    @FXML
    private void switchToRecord(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Record_Page.fxml"));
        stage = (Stage) btnToRecord.getScene().getWindow();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (stage == null) {
            return;
        }
        stage.setScene(new Scene(root));
        stage.show();
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("Record_Page.fxml"));
//        Parent root = loader.load();
//        scene.setRoot(root);
//        try {
//            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/oopgroup3/workspace_manager/Main_Page.fxml")));
//            Scene scene = new Scene(root);
//            stage.setResizable(false); // disable user from maximize
//            stage.setTitle("Workspace Manager");
//            stage.setScene(scene);
//            stage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    private void closeApplication(ActionEvent event) throws IOException {
        // Close the application
        Platform.exit();

    }



}