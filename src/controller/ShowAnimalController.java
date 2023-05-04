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
import java.sql.SQLException;
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
public class ShowAnimalController implements Initializable {

    @FXML
    private ListView<Animal> listView;
   
    ObservableList<Animal> data;
    
    public static int idE ;
    
    AnimalService cs = new AnimalService();
 
    AdoptService as = new AdoptService();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            data = (ObservableList<Animal>) cs.getAlAnimalNotAddepted();   
            listView.setItems(data);
            listView.setCellFactory((ListView<Animal> param) -> new ListViewAnimal());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowAnimalController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    





    @FXML
    private void Commentaire(ActionEvent event) throws SQLException {
                   ObservableList<Animal> e;
            e = listView.getSelectionModel().getSelectedItems();
         for (Animal m : e) 
          idE=m.getId();
         
         
         System.out.println("as.get_AdoptByIdUserIdAnimal(1, idE)"+as.get_AdoptByIdUserIdAnimal(1, idE) );
         if (as.get_AdoptByIdUserIdAnimal(1, idE).getId() != 0){
         
                 Notifications notificationBuilder = Notifications.create()
               .title("Alert").text("Vous s'avez déja une demande sur cette animal").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.darkStyle();
        notificationBuilder.show();
         
         
         
         
         }else{
             
             Adopt a = new Adopt();
             //a.setDate(new Date());
             a.setIdAnimal(idE);
             a.setIdUser(1);
             a.setEtat("en cours");
             as.Adopt(a);
             
                      
                 Notifications notificationBuilder = Notifications.create()
               .title("Success").text("Votre demande est envoyer avec success").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.darkStyle();
        notificationBuilder.show();
         
         
             
             
             
             
             
             
         try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowAnimal.fxml"));
            Stage myWindow =  (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
        }
         }
    }

    @FXML
    private void Demande(ActionEvent event) {
        
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

    

