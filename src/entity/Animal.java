/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Abderrazekbenhamouda
 */
public class Animal {
    
    
    private int id ,numpas,idUser;
    private String nom,animal,race,image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumpas() {
        return numpas;
    }

    public void setNumpas(int numpas) {
        this.numpas = numpas;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAnimal() {
        return animal;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "animal{" + "id=" + id + ", numpas=" + numpas + ", idUser=" + idUser + ", nom=" + nom + ", animal=" + animal + ", race=" + race + ", image=" + image + '}';
    }
    
    
    
    
    
    
}
