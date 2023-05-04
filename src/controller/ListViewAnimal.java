/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;



import entity.Animal;
import javafx.scene.control.ListCell;

/**
 *
 * @author dell
 */
public class ListViewAnimal extends ListCell<Animal> {
    
    
     @Override
     public void updateItem(Animal e, boolean empty)
    {
        super.updateItem(e,empty);
        if(e != null)
        {
            
            AnimalItemController data = new AnimalItemController();
            data.setInfo(e);
            setGraphic(data.getHbox());
            setGraphic(data.getBox());
        }
    }
    
}
