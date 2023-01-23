package com.raydev.sandwichshop;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;


public class SandwichController {
    @FXML
    private TextField guiGrandTotal;
    public SandwichController() {
    }
    @FXML
    private void initialize() {
    }

    @FXML
    private RadioButton rye, sourdough, ciabatta, wheat, white, salami, chicken, turkey, roastBeef, eggSalad;

    @FXML
    public void displayGrandTotal() {
        Double total = Prices.prices(9);
        guiGrandTotal.setText(String.valueOf(total));
    }
}