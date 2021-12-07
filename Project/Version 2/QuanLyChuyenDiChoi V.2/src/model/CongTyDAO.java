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
public class CongTyDAO {
       public ObservableList<CongTyDuLich> getlistCongTy() throws SQLException {
        String sql = "SELECT * FROM dbo.CongTyDuLich" ;
        System.out.println(sql);
        ObservableList<CongTyDuLich> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                CongTyDuLich ct = new CongTyDuLich();
                ct.setMaCongTy(rs.getString(1));
                ct.setTenCongTy(rs.getString(2));
                ct.setDiaChi(rs.getString(3));
                ct.setSDT(rs.getString(4));
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load cong ty");
        }

        return list;
    }
}
