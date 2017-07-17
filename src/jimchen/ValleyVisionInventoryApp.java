/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jimchen;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;


import javafx.scene.layout.*;
import javafx.geometry.*;

import javafx.scene.text.Text;
import javafx.scene.control.TextField;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javafx.scene.layout.HBox;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Driver;

import javafx.fxml.*;

/**
 *
 * @author Jim
 */
public class ValleyVisionInventoryApp extends Application {
    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/InventoryDB";
    private static Connection con;
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        
        primaryStage.setTitle("Valley Vision Inventory");
        Pane myPane = (Pane) FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(myPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        connectDatabase();
        launch(args);
        
    }
    
    public static void print(Object o) {
        System.out.println(o);
    }
    
    public static void connectDatabase() {
                
        con = null;
        
        print("attempting to connect database");
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(DATABASE_URL, "root", "");
            print("successfully connected to db");
            
        } catch (Exception e) {
            print("failed to connect to db: " + e);
        }
        
    }
    
    public static void insertEntry(Frame frame) {
        
        //throw new UnsupportedOperationException("Not implemented yet");

        try {
            
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO Frames " + "VALUES (" + frame.getBrand() + ", " 
                    + frame.getStyle() + ", " + frame.getColor() + ", " + frame.getSize() + ", " 
                    + frame.getCost() + ", " + frame.getRetail() + ", " 
                    + frame.getSoldPrice() + ", " + frame.getSoldDate() + ", " 
                    + frame.getPatient() + "," + frame.getInsuranceCov() + ", " 
                    + frame.getNotes() + ")");
            
        } catch (Exception e) {
            print(e);
        }
    }
    
    public static void queryDatabase() {
        
    }
    
}
