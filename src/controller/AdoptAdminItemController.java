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
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import service.AnimalService;
import service.UserService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class AdoptAdminItemController implements Initializable {

    @FXML
    private HBox Hbox;
    @FXML
    private Text nomuser;
    @FXML
    private Text type;
    @FXML
    private HBox hbox;
    @FXML
    private ImageView imv;
    
    String ImageUrl = "/gui/img/";
    
    UserService us = new UserService();
    AnimalService an = new AnimalService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
 
   
    }  
    
    public AdoptAdminItemController(){
    
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/gui/AdopAdmintItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        
    }

    public HBox getBox() {
        return Hbox;
    }

    public HBox getHbox() {
        return hbox;
    }

    public void setHbox(HBox hbox) {
        this.hbox = hbox;
    }
    
    
    
    
        public void setInfo(Adopt p)  {  
                        System.out.println("qqqq"+p.toString());

            System.out.println("ssssssssssssssss"+us.get_User(p.getIdUser()).getNom());
            System.out.println("ssssssss"+an.get_AnimalById(p.getId()).getNom());
        nomuser.setText(us.get_User(p.getIdUser()).getNom());
        type.setText(an.get_AnimalById(p.getIdAnimal()).getNom());
       imv.setImage(new Image("/gui/img/"+an.get_AnimalById(p.getIdAnimal()).getImage()));
     
     
}
        
}
