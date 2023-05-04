/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Adopt;
import entity.Animal;
import java.sql.Connection;
import java.sql.Date;
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
public class AdoptService {
    
          private final Connection cnx;

    private static AnimalService instance;
    
        public AdoptService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static AnimalService getInstance()
    {
        if (instance == null) {
            instance = new AnimalService();
        }
        return instance; 
    }
    
   public void Adopt(Adopt q)throws SQLDataException, SQLException{
         String query ="INSERT INTO `adopte`(`id_user`,`id_animal`,`date`,`etat`) VALUES (?,?,?,?)";
 
         PreparedStatement st;
        
        try {
            st = cnx.prepareStatement(query);

                st.setInt(1,q.getIdUser());
                st.setInt(2,q.getIdAnimal());
                 st.setDate(3, (Date) q.getDate());
                 st.setString(4, q.getEtat());
                st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
        }
                

    }

    public ObservableList<Adopt> getAlAdopt() throws SQLDataException {

        
        List<Adopt> list =new ArrayList<Adopt>();
        int count =0;
        
        String requete="select * from adopte";
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Adopt e = new Adopt();
                e.setId(rs.getInt("id"));
  
                e.setIdUser(rs.getInt("id_user"));
                e.setIdAnimal(rs.getInt("id_animal"));
                e.setDate(rs.getDate("date"));
                e.setEtat(rs.getString("etat"));
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
    
    
    
    
     public ObservableList<Adopt> getAlAdoptByUser(int id) throws SQLDataException {

        
        List<Adopt> list =new ArrayList<Adopt>();
        int count =0;
        
        String requete="select * from adopte where id_user="+id;
         try{
             Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(requete);
            
            while (rs.next()){
                
                Adopt e = new Adopt();
                e.setId(rs.getInt("id"));
  
                e.setIdUser(rs.getInt("id_user"));
                e.setIdAnimal(rs.getInt("id_animal"));
                e.setDate(rs.getDate("date"));
                e.setEtat(rs.getString("etat"));
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
    
    
    
    
    
    
    
    
    public Adopt get_AdoptById(int i) {
        Adopt e = new Adopt();
        int nombre = 0;
        String requete = "select * from adopte where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 e.setId(rs.getInt("id"));
     
                e.setIdUser(rs.getInt("id_user"));
                e.setIdAnimal(rs.getInt("id_animal"));
                e.setDate(rs.getDate("date"));
                e.setEtat(rs.getString("etat"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
       
       
       public Adopt get_AdoptByIdUserIdAnimal(int idu,int ida) {
        Adopt e = new Adopt();
        int nombre = 0;
        String requete = "select * from adopte where id_user="+idu+" and id_animal="+ida;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                 e.setId(rs.getInt("id"));
     
                e.setIdUser(rs.getInt("id_user"));
                e.setIdUser(rs.getInt("id_animal"));
                e.setDate(rs.getDate("date"));
                e.setEtat(rs.getString("etat"));
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }       
       
       
       
       
   
    

    public boolean deleteAdopt(int idCat) throws SQLDataException {

        try {
            
            Statement st=cnx.createStatement();
            String req= "DELETE FROM adopte WHERE id="+idCat;
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }
    
    
           public boolean ModifieEtat(Adopt q) throws SQLDataException {
                String query = "UPDATE `adopte` SET `etat`=? WHERE `id` = ?";
		PreparedStatement st;
        try {
                st = cnx.prepareStatement(query);
   
                st.setString(1,q.getEtat());
                st.setInt(2,q.getId());
                st.executeUpdate();
                return true;
                
        } catch (SQLException ex) {
            Logger.getLogger(AnimalService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
                
               
    }
    
}
