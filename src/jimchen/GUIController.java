/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jimchen;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.*;
import java.util.ResourceBundle;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.beans.value.ChangeListener ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 * FXML Controller class
 * 
 * need to add arrival date
 * need to query by last name, so separate the name
 * make rows editable
 * add distributor column (add check mark to choose between displaying which ones)
 * 
 * display names last, first
 * 
 * GUI must be dynamic 
 * 
 *
 * @author Jim
 */
public class GUIController implements Initializable {
    
    private ObservableList<Frame> data;
    
    @FXML private Label infoLabel; // will use this to display help
    @FXML private TextField brandField;
    @FXML private TextField styleField;
    @FXML private TextField colorField;
    @FXML private TextField sizeField;
    @FXML private TextField lastNameField;
    @FXML private TextField firstNameField;
    @FXML private TextField costField;
    @FXML private TextField retailField;
    @FXML private TextField soldPriceField;
    @FXML private TextField soldDateField;
    @FXML private TextField insuranceField;
    @FXML private TextField notesField;
    
    @FXML private TableView<Frame> tableView;
    @FXML private TableColumn<Frame, String> brand;
    @FXML private TableColumn<Frame, String> style;
    @FXML private TableColumn<Frame, String> color;
    @FXML private TableColumn<Frame, String> size;
    @FXML private TableColumn<Frame, String> patient;
    @FXML private TableColumn<Frame, String> cost;
    @FXML private TableColumn<Frame, String> retail;
    @FXML private TableColumn<Frame, String> soldPrice;
    @FXML private TableColumn<Frame, String> soldDate;
    @FXML private TableColumn<Frame, String> insuranceCov;
    @FXML private TableColumn<Frame, String> notes;
    
    private List linkedList = new LinkedList();
    
    private boolean canAdd = false; // when true, user can add
        
    @FXML
    protected void addConfirmFrame(ActionEvent event) {
        
        // should only allow this to open if there is something in the fields
        
        /**
        Frame frame = new Frame();
        frame.setBrand(brandField.getText());
        frame.setStyle(styleField.getText());
        frame.setColor(colorField.getText());
        frame.setSize(sizeField.getText());
        frame.setPatient(lastNameField.getText()); **/ // uncomment this block to begin saving entries
        
        // these items within the stage must be dynamic
        
        /**
        
        Stage dialogStage = new Stage();
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40, 0, 0, 50));
        gridPane.setHgap(5); gridPane.setVgap(5);

        Scene scene = new Scene(gridPane, 700, 300);
        Label confirm = new Label("Please Confirm:");
        //GridPane.setHalignment(confirm, HPos.CENTER);
     
        Button yes = new Button("Yes");        
        Button no = new Button("Cancel");

        GridPane.setMargin(yes, new Insets(10, 0, 0, 0));
        GridPane.setMargin(no, new Insets(10, 0, 0, 0));

        gridPane.add(confirm, 0, 0);
        gridPane.add(yes, 75, 35);
        gridPane.add(no, 80, 35);

        dialogStage.setTitle("Confirmation");
        dialogStage.setScene(scene);
        dialogStage.show(); **/

        try {
            
            FXMLLoader loader = new FXMLLoader (getClass().getResource("InsertFrameGUI.fxml"));
            Parent root = (Parent) loader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root));
            stage.show();
            
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    
    @FXML
    protected void clearEntries(ActionEvent event) {
        brandField.setText("");
        styleField.setText("");
        colorField.setText("");
        sizeField.setText("");
        lastNameField.setText("");
        firstNameField.setText("");
        costField.setText("");
        retailField.setText("");
        soldPriceField.setText("");
        soldDateField.setText("");
        insuranceField.setText("");
        notesField.setText("");
        tableView.getItems().setAll(getAllFrames());
        canAdd = false;
    }
    
    private void saveEntry() {
        // this is most likely a duplicate from clearEntries()
        System.out.println("adding frame");
        brandField.setText("");
        styleField.setText("");
        colorField.setText("");
        sizeField.setText("");
        lastNameField.setText("");
        firstNameField.setText("");
        costField.setText("");
        retailField.setText("");
        soldPriceField.setText("");
        soldDateField.setText("");
        insuranceField.setText("");
        notesField.setText("");
        canAdd = false;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
        brand.setCellValueFactory(new PropertyValueFactory<Frame, String>("brand"));
        style.setCellValueFactory(new PropertyValueFactory<Frame, String>("style"));
        color.setCellValueFactory(new PropertyValueFactory<Frame, String>("color"));
        size.setCellValueFactory(new PropertyValueFactory<Frame, String>("size"));
        patient.setCellValueFactory(new PropertyValueFactory<Frame, String>("patient"));
        cost.setCellValueFactory(new PropertyValueFactory<Frame, String>("cost"));
        retail.setCellValueFactory(new PropertyValueFactory<Frame, String>("retail"));
        soldPrice.setCellValueFactory(new PropertyValueFactory<Frame, String>("soldPrice"));
        soldDate.setCellValueFactory(new PropertyValueFactory<Frame, String>("soldDate"));
        insuranceCov.setCellValueFactory(new PropertyValueFactory<Frame, String>("insuranceCov"));
        notes.setCellValueFactory(new PropertyValueFactory<Frame, String>("notes"));
        tableView.getItems().setAll(getAllFrames());
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("its me");
        }
        setInputListeners();
    }

    private List<Frame> getAllFrames() {
        
        Connection conn;
        linkedList = new LinkedList();
        Statement statement;
        ResultSet rs;
        String url = "jdbc:mysql://localhost:3306/InventoryDB";
        String user = "root";
        String pw = "";
        String driver = "com.mysql.jdbc.Driver";
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
            statement = conn.createStatement();
            String get = ("SELECT * FROM Frame");
            rs = statement.executeQuery(get);
            while (rs.next()) {
                Integer _id = rs.getInt("_id");
                String _brand = rs.getString("_brand");
                String _style = rs.getString("_style");
                String _color = rs.getString("_color");
                String _size = rs.getString("_size");
                double _cost = rs.getDouble("_cost");
                double _retailPrice = rs.getDouble("_retailPrice");
                double _soldPrice = rs.getDouble("_soldPrice");
                String _soldDate = rs.getString("_soldDate");
                String _patient = rs.getString("_patient");
                String _firstName = rs.getString("_firstName");
                String _lastName = rs.getString("_lastName");
                double _insuranceCov = rs.getDouble("_insuranceCov");
                String _notes = rs.getString("_notes");
                
                linkedList.add(new Frame(_id, _brand, _style, _color, _size, _cost,
                        _retailPrice, _soldPrice, _soldDate, _insuranceCov, _patient, _firstName, _lastName, _notes));
            }
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("its me too");
        }
        
        return linkedList;
        
    }

    private List<Frame> queryFrames(String brand, String style, String color, String size, String lastName, String firstName, String cost, String retail, String soldPrice, String soldDate, String insurance, String notes) {
        
        Connection conn;
        linkedList = new LinkedList();
        Statement statement;
        ResultSet rs;
        String url = "jdbc:mysql://localhost:3306/InventoryDB";
        String user = "root";
        String pw = "";
        String driver = "com.mysql.jdbc.Driver";
        
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pw);
            statement = conn.createStatement();
            
            String get = ("SELECT * FROM Frame WHERE _brand LIKE '" + brand 
                    + "%' AND _style LIKE '" + style + "%' AND _color LIKE '" 
                    + color + "%' AND _size LIKE '" + size + "%' AND _firstName LIKE '" 
                    + firstName + "%' AND _lastName LIKE '" + lastName + "%' AND _cost LIKE '" 
                    + cost + "%' AND _retailPrice LIKE '" + retail + "%' AND _soldPrice LIKE '" 
                    + soldPrice + "%' AND _insuranceCov LIKE '" + insurance + "%' AND _notes LIKE '" 
                    + notes + "%' AND _soldDate LIKE '" + soldDate + "%'");
            
            rs = statement.executeQuery(get);
            while (rs.next()) {
                Integer _id = rs.getInt("_id");
                String _brand = rs.getString("_brand");
                String _style = rs.getString("_style");
                String _color = rs.getString("_color");
                String _size = rs.getString("_size");
                double _cost = rs.getDouble("_cost");
                double _retailPrice = rs.getDouble("_retailPrice");
                double _soldPrice = rs.getDouble("_soldPrice");
                String _soldDate = rs.getString("_soldDate");
                String _patient = rs.getString("_patient");
                String _firstName = rs.getString("_firstName");
                String _lastName = rs.getString("_lastName");
                double _insuranceCov = rs.getDouble("_insuranceCov");
                String _notes = rs.getString("_notes");
                                
                linkedList.add(new Frame(_id, _brand, _style, _color, _size, _cost,
                        _retailPrice, _soldPrice, _soldDate, _insuranceCov, _patient, _firstName, _lastName, _notes));
            }
            
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("it cant be me");
        }
        
        return linkedList;
    }
    
    private void setInputListeners() {
        
        ChangeListener<String> textListener = (observable, oldValue, newValue) -> {
            //System.out.println(newValue);
            tableView.getItems().setAll(queryFrames(brandField.getCharacters().toString(), 
                    styleField.getCharacters().toString(), colorField.getCharacters().toString(), 
                    sizeField.getCharacters().toString(), lastNameField.getCharacters().toString(), 
                    firstNameField.getCharacters().toString(), costField.getCharacters().toString(), 
                    retailField.getCharacters().toString(), soldPriceField.getCharacters().toString(), 
                    soldDateField.getCharacters().toString(), insuranceField.getCharacters().toString(), notesField.getCharacters().toString()));
        };
        
        brandField.textProperty().addListener(textListener);
        styleField.textProperty().addListener(textListener);
        colorField.textProperty().addListener(textListener);
        sizeField.textProperty().addListener(textListener);
        lastNameField.textProperty().addListener(textListener);
        firstNameField.textProperty().addListener(textListener);
        
        costField.textProperty().addListener(textListener);
        retailField.textProperty().addListener(textListener);
        soldPriceField.textProperty().addListener(textListener);
        soldDateField.textProperty().addListener(textListener);
        insuranceField.textProperty().addListener(textListener);
        notesField.textProperty().addListener(textListener);
        
        // check if any fields are filled, if proper fields are filled then allow user to add
        
        // remove items from list


    }
    
    private String formatName(String first, String last) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
