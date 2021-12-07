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
public class LopHocDAO {
    public ObservableList<LopHoc> getlistLopHoc() throws SQLException {
        String sql = "SELECT * FROM dbo.LopHoc" ;
        System.out.println(sql);
        ObservableList<LopHoc> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                LopHoc lh = new LopHoc();
                lh.setStrMaLop(rs.getString(1));
                lh.setStrTenLop(rs.getString(2));
                list.add(lh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load lop hoc");
        }

        return list;
    }
}
