/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Adopt;
import entity.Animal;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLDataException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.AdoptService;
import service.AnimalService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowAdoptController implements Initializable {

    @FXML
    private ListView<Adopt> listView;
   
    ObservableList<Adopt> data;
    
    public static int idE ;
    
    AnimalService cs = new AnimalService();
 
    AdoptService as = new AdoptService();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            data = (ObservableList<Adopt>) as.getAlAdoptByUser(1); 
            System.out.println("controller.ShowAdoptController.initialize()"+data);
            listView.setItems(data);
            listView.setCellFactory((ListView<Adopt> param) -> new ListViewAdopt());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowAdoptController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    






    @FXML
    private void Demande(ActionEvent event) throws SQLDataException {
        
                   ObservableList<Adopt> e;
            e = listView.getSelectionModel().getSelectedItems();
         for (Adopt m : e) 
          idE=m.getId();
         as.deleteAdopt(idE);
         
                          Notifications notificationBuilder = Notifications.create()
               .title("Sucss").text("Supprimer Avec Success").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.darkStyle();
        notificationBuilder.show();
         
        
                 try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowAdopt.fxml"));
            Stage myWindow =  (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
        }
    }




    }

    

