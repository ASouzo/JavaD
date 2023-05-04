/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import static controller.ShowAdoptController.idE;
import entity.Adopt;
import entity.Animal;
import entity.mail;
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
import service.UserService;


/**
 * FXML Controller class
 *
 * @author dell
 */
public class ShowAdoptAdminController implements Initializable {

    @FXML
    private ListView<Adopt> listView;
   
    ObservableList<Adopt> data;
    
    public static int idE ;
    
    AnimalService cs = new AnimalService();
 
    AdoptService as = new AdoptService();
    UserService us = new UserService();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
              ObservableList<Adopt> e;
            e = listView.getSelectionModel().getSelectedItems();
          for (Adopt m : e) 
          idE=m.getId();
         
        try {
            data = (ObservableList<Adopt>) as.getAlAdopt(); 
            System.out.println("controller.ShowAdoptController.initialize()"+data);
            listView.setItems(data);
            listView.setCellFactory((ListView<Adopt> param) -> new ListViewAdoptAdmin());
            
            
            // TODO
        } catch (SQLDataException ex) {
            Logger.getLogger(ShowAdoptAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    







    @FXML
    private void refuser(ActionEvent event) throws SQLDataException, Exception {
        
        
        
              ObservableList<Adopt> e;
            e = listView.getSelectionModel().getSelectedItems();
          for (Adopt m : e) 
          idE=m.getId();
          
          Adopt a = as.get_AdoptById(idE);
          a.setEtat("Refuser");
          as.ModifieEtat(a);
         
                          mail.envoi(us.get_User(idE).getEmail(), "Consultation", " Votre Demmande est Refuser : ");

                                  Notifications notificationBuilder = Notifications.create()
               .title("Sucss").text("Le Client a reçoi un mail avec votre Reponse").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                       try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowAdoptAdmin.fxml"));
            Stage myWindow =  (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
        }
    }

    @FXML
    private void aceepter(ActionEvent event) throws SQLDataException, Exception {
        
         Adopt a = as.get_AdoptById(idE);
          a.setEtat("Accepter");
          as.ModifieEtat(a);
            System.out.println("ssssssssss<w"+a.toString());
          System.out.println("ssssssssss<w"+us.get_User(a.getIdUser()).getEmail());
                          mail.envoi(us.get_User(a.getIdUser()).getEmail(), "Consultation", " Votre Demmande est Accepter : ");


                                      Notifications notificationBuilder = Notifications.create()
               .title("Sucss").text("Le Client a reçoi un mail avec votre Reponse").graphic(null).hideAfter(javafx.util.Duration.seconds(5))
               .position(Pos.CENTER_LEFT)
               .onAction(new EventHandler<ActionEvent>(){
                   public void handle(ActionEvent event)
                   {
                       System.out.println("clicked ON ");
               }});
        notificationBuilder.darkStyle();
        notificationBuilder.show();
                       try {
            Parent root = FXMLLoader.load(getClass().getResource("/gui/ShowAdoptAdmin.fxml"));
            Stage myWindow =  (Stage) listView.getScene().getWindow();
            Scene sc = new Scene(root);
            myWindow.setScene(sc);
            myWindow.setTitle("");
            myWindow.show();
           
        } catch (IOException ex) {
        }
    }




    }

    

