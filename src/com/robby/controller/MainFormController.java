package com.robby.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Robby
 */
public class MainFormController implements Initializable {

    @FXML
    private VBox basePane;
    @FXML
    private TextArea txtFieldPane;
    @FXML
    private TextField txtFileName;

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
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
                Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void chooseAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.jpg"));
        File file = fileChooser.showOpenDialog(basePane.getScene().getWindow());
        if (file != null && Files.exists(file.toPath(), LinkOption.NOFOLLOW_LINKS) && Files.isReadable(file.toPath())) {
            if (!txtFileName.getText().trim().isEmpty()) {
                try {
                    Path sourcePath = Paths.get(file.getPath());
                    Path targetPath = Paths.get(System.getProperty("user.dir") + "\\uploads\\" + file.getName());
                    //  Copy file from source to target
                    Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);

                    //  Renaming file
                    String oldFileName = file.getName();
                    String extension = extension(oldFileName, '.');
                    String newName;
                    if (!extension.isEmpty()) {
                        newName = txtFileName.getText().trim() + "." + extension;
                    } else {
                        newName = txtFileName.getText().trim();
                    }
                    Files.move(targetPath, targetPath.resolveSibling(newName), StandardCopyOption.REPLACE_EXISTING);

                    //  Clearing field name and show message to user when copy and rename process finished
                    txtFileName.clear();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setContentText("Upload success");
                    alert.showAndWait();
                } catch (IOException ex) {
                    Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Please fill new file name");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    private String extension(String fullPath, char extensionSeparator) {
        int dot = fullPath.lastIndexOf(extensionSeparator);
        if (dot == -1) {
            return "";
        }
        return fullPath.substring(dot + 1);
    }
}
