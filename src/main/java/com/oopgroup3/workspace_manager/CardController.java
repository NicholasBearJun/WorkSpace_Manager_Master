package com.oopgroup3.workspace_manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardController {

    private Stage stage;

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

    @FXML
    private Button btnComplete;

    // Set the Names according to Workspace.java
    public void setWorkspaceData(Workspace workspace) {

        WorkSpaceName.setText(workspace.getWorkSpace_Name());
        //Deadline.setText(workspace.getDeadline_Time());
        String date = getDateForWorkspaceName();
        Deadline.setText(calDeadline(date));
    }

    // Set the Names according to Records.java
    public void setRecordData(Records records) {
        AppName.setText(records.getAppName());
        Path.setText(records.getPath());
    }

    public void setTempRecordData(Records list) {
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
        String filePath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Applications/" + labelText + ".txt";

        // Check if the file exists
        File file = new File(filePath);
        if (file.exists()) {
            System.out.println("File found: " + filePath);
        } else {
            System.out.println("File not found: " + filePath);
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

    @FXML
    private void eraseWorkspace(ActionEvent event) {
        // Get the text from the label and do something with it
        String labelText = WorkSpaceName.getText();
        String filePath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Records.txt";

        try {
            // Input file
            File inputFile = new File(filePath);
            // Temp file
            File tempFile = new File("temp.txt");

            File doomedFile = new File ("src/main/resources/com/oopgroup3/workspace_manager/Records/Applications/", labelText + ".txt");
            doomedFile.delete();

            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            // FileWriter writes text files in the default encoding.
            FileWriter fileWriter = new FileWriter(tempFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String lineToRemove;
            while ((lineToRemove = bufferedReader.readLine()) != null) {
                // Check if the line starts with the labelText
                if (!lineToRemove.startsWith(labelText)) {
                    bufferedWriter.write(lineToRemove + "\n");
                }
            }
            bufferedReader.close();
            bufferedWriter.close();

            // Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file.");
                return;
            }

            // Rename the temp file to the original file name
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temp file.");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + filePath + "'");
        } catch (IOException ex) {
            System.out.println("Error reading/writing file '" + filePath + "'");
        }


        // ------------------------------------ Reload page -------------------------
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Main_Page.fxml"));
        stage = (Stage) btnComplete.getScene().getWindow();
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            // Handle loading exception
            e.printStackTrace();
            return;
        }
        if (stage == null) {
            // Handle null stage scenario (e.g., throw exception)
            return;
        }
        stage.setScene(new Scene(root));
        stage.show();
    }


    // Method to return the text from the label
    public String getWorkspaceLabelText() {
        return WorkSpaceName.getText();
    }

    // --------------------------------------------------- Get file to compare deadline

    private String getDateForWorkspaceName() {
        String labelText = WorkSpaceName.getText();
        String filePath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Records.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 2) {
                    if (parts[0].trim().equals(labelText)) {
                        System.out.println(parts[1].trim());
                        return parts[1].trim();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    // ----------------------------------------------------- Method to compare deadline --------------------------------------
    private String calDeadline(String date){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date deadlineDatecalc = null;
        try{
            deadlineDatecalc = formatter.parse(date);
        }catch (ParseException e){

        }

        Date currentDate = new Date();

        long remainingTime = (deadlineDatecalc.getTime() - currentDate.getTime()) / 1000 / 3600 / 24 + 1;
        System.out.println("Remaining time till deadline is: " + remainingTime);
        String s1 = Long.toString(remainingTime);
        String timeLeft = s1 + " days left";
        return timeLeft;
    }

//    // -------------------------------------------------- Get file to compare deadline ----------------------------------------------
//    private String getDeadline() {
//        String labelText = WorkSpaceName.getText();
//        String filePath = "src/main/resources/com/oopgroup3/workspace_manager/Records/Records.txt";
//        String dateOfFile;
//        // Loop through file and find the equal workSpacename then take dd/mm/yyyy
//        try {
//            File getFile = new File(filePath);
//            Scanner reader = new Scanner(getFile);
//            while (reader.hasNextLine()) {
//                String data = reader.nextLine();
//                String[] parts = data.split(",");
//                if (parts.length == 3) {
//                    for (int i = 0; i < 3; i++) {
//
//                        if (parts[i] == labelText) {
//
//                        }
//                    }
//                } else {
//                    System.out.println("Invalid Data:" + data);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//    } return dateOfFile;
}
