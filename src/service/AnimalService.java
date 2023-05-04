/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Animal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class AnimalService {
    
      private final Connection cnx;

    private static AnimalService instance;
    
        public AnimalService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static AnimalService getInstance()
    {
        if (instance == null) {
            instance = new AnimalService();
        }
        return instance; 
    }
    
   public void addAnimal(Animal q)throws SQLDataException, SQLException{
         String query ="INSERT INTO `animal`(`nom`,`race`,`num_Pass`,`image`,`id_user`,`animal`) VALUES (?,?,?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);
                st.setString(1,q.getNom());
                st.setString(2,q.getRace());
                st.setInt(3,q.getNumpas());
                st.setString(4,q.getImage());
                st.setInt(5,q.getIdUser());
                st.setString(6,q.getAnimal()); 
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public boolean ModifierAnimal(Animal q) throws SQLDataException {
                String query = "UPDATE `animal` SET `nom`=?,`race`=?,`num_Pass`=?,`image`=?,`id_user`=?,`animal`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
                st.setString(1,q.getNom());
                st.setString(2,q.getRace());
                st.setInt(3,q.getNumpas());
                st.setString(4,q.getImage());
                st.setInt(5,q.getIdUser());
                st.setString(6,q.getAnimal()); 
                st.setInt(7,q.getNumpas());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
    
        public boolean ModifierAnimalIdUser(Animal q) throws SQLDataException {
                String query = "UPDATE `animal` SET `id_user`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
   
                st.setInt(1,q.getIdUser());
                st.setInt(2,q.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }

    public ObservableList<Animal> getAlAnimal() throws SQLDataException {

        
        List<Animal> list =new ArrayList<Animal>();
        int count =0;
        
        String requete="select * from animal";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Animal e = new Animal();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setAnimal(rs.getString("animal"));
                e.setImage(rs.getString("image"));
                e.setRace(rs.getString("race"));
                e.setIdUser(rs.getInt("id_user"));
                e.setNumpas(rs.getInt("num_Pass"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    
        public ObservableList<Animal> getAlAnimalNotAddepted() throws SQLDataException {

        
        List<Animal> list =new ArrayList<Animal>();
        int count =0;
        
        String requete="SELECT * from animal WHERE id_user IS NULL";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Animal e = new Animal();
                e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setAnimal(rs.getString("animal"));
                e.setImage(rs.getString("image"));
                e.setRace(rs.getString("race"));
                e.setIdUser(rs.getInt("id_user"));
                e.setNumpas(rs.getInt("num_Pass"));
                list.add(e);
                
                count++;
            }
            if(count == 0){
                return null;
           }else{
             ObservableList lc_final = FXCollections.observableArrayList(list);

               return lc_final;
            
           
        }
         }
        catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
   
           
}
    
    
    
    
    
       public Animal get_AnimalById(int i) {
        Animal e = new Animal();
        int nombre = 0;
        String requete = "select * from animal where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 e.setId(rs.getInt("id"));
                e.setNom(rs.getString("nom"));
                e.setAnimal(rs.getString("animal"));
                e.setImage(rs.getString("image"));
                e.setRace(rs.getString("race"));
                e.setIdUser(rs.getInt("id_user"));
                e.setNumpas(rs.getInt("num_Pass"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
   
    

    public boolean deleteAnimal(int idCat) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM animal WHERE id="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
}

    

