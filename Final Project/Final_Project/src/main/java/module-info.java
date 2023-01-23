module com.raydev.final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;


    opens com.raydev.final_project to javafx.fxml;
    exports com.raydev.final_project;
}