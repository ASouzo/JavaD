/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import entity.Animal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.converter.IntegerStringConverter;
import service.AnimalService;


/**
 * FXML Controller class
 *
 * @author Abderrazekbenhamouda
 */
public class AjouterAnimalController implements Initializable {
    int c;
    int file;
    File pDir;
    File pfile;
    String lien;

    AnimalService bs = new AnimalService();

    @FXML
    private ImageView imv;
    @FXML
    private TextField nom;
    @FXML
    private TextField race;
    @FXML
    private TextField race1;
    @FXML
    private TextField numPas;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            c = (int) (Math.random() * (300000 - 2 + 1)) + 2;
            pDir = new File("src/gui/img/animal" + c + ".jpg");
            lien = "animal" + c + ".jpg";
                     UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String newText = change.getControlNewText();
            if (newText.matches("-?([1-9][0-9]*)?")) { 
                return change;
            }
            return null;
        };
            numPas.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(), 0, integerFilter));
    }    

    @FXML
    private void Upload(ActionEvent event) throws MalformedURLException {
        
                
             
                 FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image: ");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp")
        );
        Window stage = null;
        pfile = fileChooser.showOpenDialog(stage);

        /* - draw image */
        if (pfile != null) {
            file=1;
            Image image = new Image(pfile.toURI().toURL().toExternalForm());
            imv.setImage(image);
    }
    
}
        
    
        
    public static boolean copier(File source, File dest) {
        try (InputStream sourceFile = new java.io.FileInputStream(source);
                OutputStream destinationFile = new FileOutputStream(dest)) {
            // Lecture par segment de 0.5Mo  
            byte buffer[] = new byte[512 * 1024];
            int nbLecture;
            while ((nbLecture = sourceFile.read(buffer)) != -1) {
                destinationFile.write(buffer, 0, nbLecture);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false; // Erreur 
        }
        return true; // Résultat OK   
    }

    @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        
        if (race.getText().equals("") || nom.getText().equals("")|| race1.getText().equals("")|| numPas.getText().equals("0")){
       
            Alert alert = new Alert(Alert.AlertType.ERROR, "vérifier votre champs", ButtonType.OK);
           alert.showAndWait();
        
       
       }
       else{
         copier(pfile, pDir);


            Animal e = new Animal();
       e.setAnimal(race1.getText());
       e.setImage(lien);
       e.setIdUser(1);
       e.setNom(nom.getText());
       e.setRace(race.getText());
       e.setNumpas(Integer.parseInt(numPas.getText()));
       bs.addAnimal(e);

       
       
       
       Parent root;
        try {
          root = FXMLLoader.load(getClass().getResource("/gui/ListAnimal.fxml"));
          Stage myWindow = (Stage) nom.getScene().getWindow();
          Scene sc = new Scene(root);
          myWindow.setScene(sc);
          myWindow.setTitle("page name");
                            //myWindow.setFullScreen(true);
          myWindow.show();
          } catch (IOException ex) {
          Logger.getLogger(AjouterAnimalController.class.getName()).log(Level.SEVERE, null, ex);
           }
       }
    }


}
