package com.robby.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Robby
 */
public class MainFormController implements Initializable {

    @FXML
    private AnchorPane basePane;
    @FXML
    private TextArea txtFieldPane;

    @FXML
    private void btnClearTextAction(ActionEvent event) {
        txtFieldPane.clear();
    }

    @FXML
    private void btnLoadTextAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("Java Files", "*.java"));
        File file = fileChooser.showOpenDialog(basePane.getScene().getWindow());
        if (file != null && Files.exists(file.toPath(), LinkOption.NOFOLLOW_LINKS) && Files.isReadable(file.toPath())) {
            try {
                List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.ISO_8859_1);
                lines.stream().forEach(line -> {
                    txtFieldPane.appendText(line);
                    txtFieldPane.appendText(System.lineSeparator());
                });
            } catch (IOException ex) {
                Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
