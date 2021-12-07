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

}
