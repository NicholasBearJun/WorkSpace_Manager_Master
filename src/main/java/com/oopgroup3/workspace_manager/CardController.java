package com.oopgroup3.workspace_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.*;
import java.util.List;

public class CardController {
    // Controller skeleton for Main Page Card
    @FXML
    private VBox cardBox;
    @FXML
    private Label Deadline;
    @FXML
    private Label Percentage;
    @FXML
    private Label WorkSpaceName;

    // Controller skeleton for Record Page Card
    @FXML
    private VBox recordBox;
    @FXML
    private TextField AppName;
    @FXML
    private TextField Path;


    // Set the Names according to Workspace.java
    public void setWorkspaceData(Workspace workspace){

        WorkSpaceName.setText(workspace.getWorkSpace_Name());
        Deadline.setText(workspace.getDeadline_Time());
    }
    // Set the Names according to Records.java
    public void setRecordData(Records records){
        AppName.setText(records.getAppName());
        Path.setText(records.getPath());
    }

    public void setTempRecordData(Records list){
        AppName.setText(list.getAppName());
        Path.setText(list.getPath());
    }

    // ______________________________________________________ Buttons Functions ____________________________________________
    // This method handles the button click event
    @FXML
    private void openApps(ActionEvent event) {
        // Get the text from the label and do something with it
        String labelText = WorkSpaceName.getText();

        // Find using filenametext.txt then access all the path inside the txt
        // Construct the file path
        String filePath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Applications/"+ labelText +".txt";

        // Check if the file exists
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File found: " + filePath);
            // Do something with the file, e.g., read its contents
        } else {
            System.out.println("File not found: " + filePath);
            // Handle the case where the file does not exist
        }

        // Loop through file and run applications
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Remove any trailing commas
                line = line.replaceAll(",+$", "");

                // Execute the program specified in the line
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder(line);
                    Process process = processBuilder.start();
                    System.out.println("Executing " + line + "...");
                    //process.waitFor();
                    System.out.println("Execution completed.");
                } catch (IOException e) { //catch (IOException | InterruptedException e)
                    System.out.println("Error executing " + line + ": " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }

    // Method to return the text from the label
    public String getWorkspaceLabelText() {
        return WorkSpaceName.getText();
    }

}
