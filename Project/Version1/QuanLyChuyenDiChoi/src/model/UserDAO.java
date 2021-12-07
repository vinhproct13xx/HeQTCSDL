/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MyPC
 */
public class UserDAO {

    private User createUser(ResultSet rs) {
        User user = new User();
        try {
            user.setID(rs.getInt("ID"));
            user.setEmail(rs.getString("Email"));
            user.setLevel(rs.getInt("Level"));
            user.setPassword(rs.getString("Password"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't get user");
        }
        return user;

    }

    public ObservableList<User> getListUser() throws SQLException {
        String sql = "select * from dbo.[User]";
        ObservableList<User> list = FXCollections.observableArrayList();
        try {

            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                User user = createUser(rs);
                list.add(user);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void AddUser(User hs) {
        String sql = "INSERT INTO dbo.[User]\n"
                + "        ( ID, Email, Password, Level )\n"
                + "VALUES  ( " + hs.getID() + ", \n"
                + "          N'" + hs.getEmail() + "', \n"
                + "          N'" + hs.getPassword() + "', \n"
                + "          1  \n"
                + "          )";

        System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't Add HocSinh!");
        }
    }

    public void UpdateUser(User hs) {
        String sql = "  UPDATE dbo.[User]  SET Password = '"+hs.getPassword()+"' WHERE ID = "+ hs.getID();
        System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't Add HocSinh!");
        }
    }

}
