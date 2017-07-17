/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jimchen;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Jim
 */
public class InsertFrameGUIController implements Initializable {
    
    @FXML private Button closeButton, saveButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void cancel() {
        System.out.println("Need to exit");
        
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    private void save() {
        System.out.println("Need to save");
        
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}
