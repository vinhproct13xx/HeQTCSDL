/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MyPC
 */
public class ThamGiaDAO {
     private HocSinhThamGia createHocSinhThamGia(ResultSet rs) {
        HocSinhThamGia hs = new HocSinhThamGia();
        try {
            hs.setMaHS(rs.getInt("MaHS"));
            hs.setTenHS(rs.getString("TenHS"));
            hs.setMaChuyenDi(rs.getString("MaChuyenDi"));
            hs.setMaLop(rs.getString("MaLop"));
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load HocSinh");
        }
        return hs;
    }
     public ObservableList<HocSinhThamGia> getlistHocSinh(String MaLop,String MaChuyenDi)
     {
         String sql = "select * from HocSinh inner join HocSinhThamGia on HocSinh.MaHS = HocSinhThamGia.MaHS "
                 + "where MaLop = '"+MaLop+"' and MaChuyenDi = '" +MaChuyenDi+ "'";
         System.out.print(sql);
     ObservableList<HocSinhThamGia> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                HocSinhThamGia hs = createHocSinhThamGia(rs);
                list.add(hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load HocSinh");
        }

        return list;
    }
}
