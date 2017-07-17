/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jimchen;

import java.text.NumberFormat;
import javafx.beans.property.*;


/**
 *
 * @author Jim
 */
public class Frame {
    
    private SimpleStringProperty _id, brand, style, color, size, patient, notes, soldDate; 
    private SimpleStringProperty cost, retail, soldPrice, insuranceCov;
    private SimpleStringProperty _firstName, _lastName;

      
    public Frame() {
        
    }
    
    public Frame(Integer _id, String brand, String style, String color,
            String size, double cost, double retail, double soldPrice,
            String soldDate, double insurance, String patient, String firstName, String lastName, String notes) {
        
        setId(_id);
        setBrand(brand);
        setStyle(style);
        setColor(color);
        setSize(size);
        setCost(cost);
        setRetail(retail);
        setSoldPrice(soldPrice);
        setSoldDate(soldDate);
        setInsuranceCov(insurance); // really stupid naming convention
        setPatient(patient);
        setNotes(notes);
        
    }
    
    public void setId(Integer id) {
        this._id = new SimpleStringProperty(String.valueOf(id));
    }
            
    public void setBrand(String brand) {
        this.brand = new SimpleStringProperty(brand);
    }
    
    public void setStyle(String style) {
        this.style = new SimpleStringProperty(style);
    }
    
    public void setColor(String color) {
        this.color = new SimpleStringProperty(color);
    }
    
    public void setSize(String size) {
        this.size = new SimpleStringProperty(size);
    }
    
    public void setPatient(String patient) {
        this.patient = new SimpleStringProperty(patient);
    }
    
    public void setFirstName(String name) {
        this._firstName = new SimpleStringProperty(name);
    }
    
    public void setLastName(String name) {
        this._lastName = new SimpleStringProperty(name);
    }
    
    public void setNotes(String notes) {
        this.notes = new SimpleStringProperty(notes);
    }
    
    public void setCost(double cost) {
        this.cost = new SimpleStringProperty(String.valueOf(cost));
    }
    
    public void setRetail(double retail) {
        this.retail = new SimpleStringProperty(String.valueOf(retail));
    }
    
    public void setSoldPrice(double price) {
        this.soldPrice = new SimpleStringProperty(String.valueOf(price));
    }
    
    public void setSoldDate(String date) {
        this.soldDate = new SimpleStringProperty(date);
    }
    
    public void setInsuranceCov(double cov) {
        this.insuranceCov = new SimpleStringProperty(String.valueOf(cov));
    }
    
    public String getId() {
        return this._id.get();
    }
    
    public String getBrand() {
        return this.brand.get();
    }
    
    public String getStyle() {
        return this.style.get();
    }
    
    public String getColor() {
        return this.color.get();
    }
    
    public String getSize() {
        return this.size.get();
    }
    
    public String getPatient() {
        return this.patient.get();
    }
    
    public String getFirstName() {
        return this._firstName.get();
    }
    
    public String getLastName() {
        return this._lastName.get();
    }
    
    public String getNotes() {
        return this.notes.get();
    }
    
    public String getCost() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(Double.parseDouble(this.cost.get()));
    }
    
    public String getRetail() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(Double.parseDouble(this.retail.get()));
    }
    
    public String getSoldPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(Double.parseDouble(this.soldPrice.get()));
    }
    
    public String getSoldDate() {
        return this.soldDate.get();
    }
    
    public String getInsuranceCov() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return formatter.format(Double.parseDouble(this.insuranceCov.get()));
    }
    
    public StringProperty getIdProperty() {
        return this._id;
    }
    
    public StringProperty getBrandProperty() {
        return this.brand;
    }
    
    public StringProperty getStyleProperty() {
        return this.style;
    }
    
    public StringProperty getColorProperty() {
        return this.color;
    }
    
    public StringProperty getSizeProperty() {
        return this.size;
    }
    
    public StringProperty getPatientProperty() {
        return this.patient;
    }
    
    public StringProperty getFirstNameProperty() {
        return this._firstName;
    }
    
    public StringProperty getLastNameProperty() {
        return this._lastName;
    }
    
    public StringProperty getNotesProperty() {
        return this.notes;
    }
    
    public StringProperty getCostProperty() {
        return this.cost;
    }
    
    public StringProperty getRetailProperty() {
        return this.retail;
    }
    
    public StringProperty getSoldPriceProperty() {
        return this.soldPrice;
    }
    
    public StringProperty getSoldDateProperty() {
        return this.soldDate;
    }
    
    public StringProperty getInsuranceCovProperty() {
        return this.insuranceCov;
    }
    
}
