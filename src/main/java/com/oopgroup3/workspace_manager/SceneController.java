//package com.oopgroup3.workspace_manager;
//
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import java.io.IOException;
//
//
//public class SceneController {
//
//
//    private Stage stage;
//    private Scene scene;
//    private Parent root;
//    @FXML
//
//    private MainPageController mainPageController;
//    @FXML
//    private RecordPageController recordController;
//
////    @FXML private void intializeMain(){
////        mainPageController.injectSceneController(this);
////    }
//
//    // Method to inject MainPageController
//    public void injectMainPageController(MainPageController mainPageController) {
//        this.mainPageController = mainPageController;
//    }
//
//    // Method to switch to the main page
//    public void switchToMain(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
//        Parent root = loader.load();
//        scene.setRoot(root);
//    }
//
//    // Method to switch to the record page
//    public void switchToRecord(ActionEvent event) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("RecordPage.fxml"));
//        Parent root = loader.load();
//        scene.setRoot(root);
//    }
//
//// _________________________ 21-3-2024 _______________________________________
////    private void switchScene(String fxmlFile, ActionEvent event) throws IOException {
////        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
////        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////        scene = new Scene(root);
////        stage.setScene(scene);
////        stage.show();
////    }
////
////    public void switchToMain(ActionEvent event) throws IOException {
////        Parent root = FXMLLoader.load(getClass().getResource("Main_Page.fxml"));
////        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////        scene = new Scene(root);
////        stage.setScene(scene);
////        stage.show();
////    }
////
////    public void switchToRecord(ActionEvent event) throws IOException {
////        System.out.println("SceneController switch loaded");
//////        intializeMain();
////        switchScene("Record_Page.fxml", event);
////    }
//// _____________________________________________________________________________
//
////    public void switchToRecord(ActionEvent event) throws IOException {
////        Parent root = FXMLLoader.load(getClass().getResource("Record_Page.fxml"));
////        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
////        scene = new Scene(root);
////        stage.setScene(scene);
////        stage.show();
////    }
//
//}
//
//
//
////public class SceneController implements Initializable {
////
////    private Stage stage;
////    private Scene scene;
////    private Parent root;
////
////    @FXML
////    private VBox cardBox;
////
////
////    // List variable initialization for cards
////    List<Workspace> ls_workspaces;
////
////    // Initialize and place for CardController
////    @Override
////    public void initialize(URL location, ResourceBundle resources){
////        // assign the value of new ArrayList as the return value of method
////        ls_workspaces = new ArrayList<>(workspaces());
////
////        try{
////            // Load every workspace into a card
////            for (Workspace workspace : ls_workspaces){
////                // Load the custom card from other .fxml
////                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/oopgroup3/workspace_manager/Main_Page_Card.fxml"));
////                // set the root control (Pane) of Custom Card to be the loaded UI
////                Pane customCard = loader.load();
////
////                // Access the CardController instance
////                CardController cardController = loader.getController();
////
////                // set the data of the Custom Card using setData method in CardController.java
////                cardController.setWorkspaceData(workspace);
////
////                cardBox.setSpacing(2.5);
////
////                // add the ca to the Main's VBox
////                cardBox.getChildren().add(customCard);
////
////                System.out.println("Main page completed");
////            }
////        } catch (IOException e){
////            e.printStackTrace();
////        }
////    }
////
////    // loop through workspace
////    private List<Workspace> workspaces(){
////        List<Workspace> ls = new ArrayList<>();
////
////        // Identify the file
////        String recordPath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Records.txt";
////        File file = new File(recordPath);
////
////        // Iterate through text file. split line via ",", then return into List<Workspace> ls = new ArrayList<>()
////        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
////            String line;
////            while ((line = br.readLine()) != null) {
////                String[] parts = line.split(",");
////                if (parts.length == 4) {
////                    // create a new instance of Workspace class and assign with another workspace instance
////                    Workspace workspace = getWorkspace(parts);
////
////                    // add the workspace instance to Main's workspace list
////                    ls.add(workspace);
////
////                } else {
////                    System.out.println("Invalid line: " + line);
////                }
////            }
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        return ls;
////    }
////
////    // Method to handle the separation of data from text file's readLine
////    private Workspace getWorkspace(String[] parts) {
////        String name = parts[0];
////        String percentage = parts[1];
////        String date = parts[2];
////        String txtFilePath = parts[3];
////
////        // workspace set variables
////        return new Workspace(name, percentage ,date, txtFilePath); // return a instance of Workspace with constructor
////    }
////}
