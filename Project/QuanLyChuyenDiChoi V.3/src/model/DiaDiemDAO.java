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
 * @author Scoobydo
 */
public class DiaDiemDAO {
    public ObservableList<DiaDiem> getlistDiaDiem() throws SQLException {
        String sql = "SELECT * FROM dbo.DiaDiem" ;
        System.out.println(sql);
        ObservableList<DiaDiem> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                DiaDiem dd = new DiaDiem();
                dd.setMaDiaDiem(rs.getString(1));
                dd.setTenDiaDiem(rs.getString(2));
                dd.setDiaChi(rs.getString(3));
                list.add(dd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load Dia diem");
        }

        return list;
    }
}
