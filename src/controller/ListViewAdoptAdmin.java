/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entity.Adopt;
import entity.Animal;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewAdoptAdmin extends ListCell<Adopt> {
    
    
     @Override
     public void updateItem(Adopt e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            AdoptAdminItemController data = new AdoptAdminItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
