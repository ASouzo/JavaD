/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.AnimalService;

/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class ListAnimalController implements Initializable {

    @FXML
    private TableView<Animal> table;


    private ObservableList<Animal> UserData = FXCollections.observableArrayList();
    
    AnimalService cs = new AnimalService();
    
    static int idEvent ;
    @FXML
    private TextField rechercher;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> animal;
    @FXML
    private TableColumn<?, ?> race;
    @FXML
    private TableColumn<?, ?> numPas;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              try {
            List<Animal> listb= new ArrayList<Animal>();
            
            listb = cs.getAlAnimal();
     
            UserData.clear();
            UserData.addAll(listb);
            table.setItems(UserData);
            
            race.setCellValueFactory
                      (
            new PropertyValueFactory<>("race")
                      );
            animal.setCellValueFactory
                      (
            new PropertyValueFactory<>("animal")
                      );
            nom.setCellValueFactory
                      (
            new PropertyValueFactory<>("nom")
                      );
                 numPas.setCellValueFactory
                      (
            new PropertyValueFactory<>("numpas")
                      );
        } catch (SQLDataException ex) {
            Logger.getLogger(ListAnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void Ajouter(ActionEvent event) {
        
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/AjouterAnimal.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterAnimalController.class.getName()).log(Level.SEVERE, null, ex);
           }
        
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLDataException {
        
                
        cs.deleteAnimal(table.getSelectionModel().getSelectedItem().getId());
        resetTableData();
        
        
    }

    @FXML
    private void Modifier(ActionEvent event) {
        idEvent  =  table.getSelectionModel().getSelectedItem().getId();     

               Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ModifierAnimal.fxml"));
          Stage myWindow = (Stage) table.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterAnimalController.class.getName()).log(Level.SEVERE, null, ex);
           }
    }
    
    
    
        public void resetTableData() throws SQLDataException
    {
        List<Animal> lisre = new ArrayList<>();
        lisre = cs.getAlAnimal();
        ObservableList<Animal> data = FXCollections.observableArrayList(lisre);
        table.setItems(data);
    }

    @FXML
    private void rechercher(ActionEvent event) throws SQLDataException {
        
              
            race.setCellValueFactory
                      (
            new PropertyValueFactory<>("race")
                      );
            animal.setCellValueFactory
                      (
            new PropertyValueFactory<>("animal")
                      );
            nom.setCellValueFactory
                      (
            new PropertyValueFactory<>("nom")
                      );
                 numPas.setCellValueFactory
                      (
            new PropertyValueFactory<>("numpas")
                      );
                  
            List<Animal> list = cs.getAlAnimal();
            
            //tableview.setItems(observablelist);
            
            FilteredList<Animal> filtredData= new FilteredList<>(UserData, b ->true);
            rechercher.textProperty().addListener((observable,oldValue,newValue) -> {
                Predicate<? super Animal> Reservation;
                filtredData.setPredicate((Animal e) -> {
                    if (newValue == null || newValue.isEmpty()){
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();
                    if(e.getAnimal().toLowerCase().indexOf(lowerCaseFilter) != -1 ){
                        return true;
                    }
                    else if (e.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                        return true; // Filter matches last name.
                    }
                    
                    else
                        return false;
                } );
            });
             // 3. Wrap the FilteredList in a SortedList. 
        SortedList<Animal> sortedData = new SortedList<>(filtredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);
        
        
        
        
        
        
        
        
        
    }
        
}
    
