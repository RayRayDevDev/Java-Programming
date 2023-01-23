module com.raydev.sandwichshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.raydev.sandwichshop to javafx.fxml;
    exports com.raydev.sandwichshop;
}