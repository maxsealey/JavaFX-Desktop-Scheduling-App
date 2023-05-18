package sealey.javafxdesktopschedulingapp.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sealey.javafxdesktopschedulingapp.helpers.Helpers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GenerateReports implements Initializable
{
    @FXML
    private Button backButton;
    @FXML
    void onActionBack(ActionEvent event) throws IOException {
        Helpers.setStage("Dashboard.fxml", "Employee Dashboard", backButton);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
