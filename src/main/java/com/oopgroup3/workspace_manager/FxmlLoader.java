package com.oopgroup3.workspace_manager;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.net.URL;

import static javafx.fxml.FXMLLoader.load;

public class FxmlLoader {
    // Get "Pane" / "AnchorPane"
    private AnchorPane view;

    // Loadout fxml file onto main.fxml "Pane"
    public AnchorPane getPage(String fileName) {
        try {
            URL fileUrl = Main.class.getResource("/resources/com/oopgroup3/workspace_manager/"+fileName+".fxml");
            if (fileUrl == null) {
                throw new java.io.FileNotFoundException("FXML file can't be found");
            }

            System.out.println("Filename.fxml gotten");

            new FXMLLoader();
            view = FXMLLoader.load(fileUrl);

        } catch (Exception e){
            System.out.println("No page " + fileName + " please check FxmlLoader.");
        }
        return view;
    }
}