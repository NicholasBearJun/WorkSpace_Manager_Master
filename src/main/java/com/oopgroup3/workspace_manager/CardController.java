package com.oopgroup3.workspace_manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
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
        Percentage.setText(workspace.getWork_Percentage() + "%");
        Deadline.setText(workspace.getDeadline_Time());
    }
    // Set the Names according to Records.java
    public void setRecordData(Records records){
        AppName.setText(records.getAppName());
        Path.setText(records.getPath());
    }
}
