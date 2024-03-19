module com.oopgroup3.workspace_manager {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.oopgroup3.workspace_manager to javafx.fxml;
    exports com.oopgroup3.workspace_manager;
}