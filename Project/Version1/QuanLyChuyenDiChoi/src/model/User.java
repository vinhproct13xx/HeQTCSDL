/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.ObservableList;

/**
 *
 * @author MyPC
 */
public class User {
    private  int ID;
    private  int level;
    private String Email;
    private String Password;

    public User(int ID, int level, String Email, String Password) {
        this.ID = ID;
        this.level = level;
        this.Email = Email;
        this.Password = Password;
    }

    public User() {
    }

    public int getID() {
        return ID;
    }

    public int getLevel() {
        return level;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    public static User getbyEmail(String emailString, ObservableList<User> list)
    {
       for (User u : list) {
            if (u.getEmail().equals(emailString)) 
            {
                return u;
            }
        }
       return null;
    }
    
}
