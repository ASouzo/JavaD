/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DataSource;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class UserService {
    
         private final Connection cnx;

    private static UserService instance;
    
        public UserService() {
        cnx = DataSource.getInstance().getCnx();
    }
    
    public static UserService getInstance()
    {
        if (instance == null) {
            instance = new UserService();
        }
        return instance; 
    }
    
    
    
           public User get_User(int i) {
        User e = new User();
        int nombre = 0;
        String requete = "select * from user where id="+i;
        try {
            PreparedStatement ps = cnx.prepareStatement(requete);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                e.setId(rs.getInt("id"));
                e.setEmail(rs.getString("email"));
                e.setNom(rs.getString("nom"));
                
                nombre++;

            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;

    }
}
