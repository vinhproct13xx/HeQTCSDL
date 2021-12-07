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
public class ThamGiaDAO {
     private HocSinhThamGia createHocSinhThamGia(ResultSet rs) {
        HocSinhThamGia hs = new HocSinhThamGia();
        try {
            hs.setMaHS(rs.getInt("MaHS"));
            hs.setTenHS(rs.getString("TenHS"));
            hs.setMaChuyenDi(rs.getString("MaChuyenDi"));
            hs.setMaLop(rs.getString("MaLop"));
            hs.setThamGia(rs.getBoolean("ThamGia"));
            hs.setDaThuPhi(rs.getBoolean("IsDongTien"));
            
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
     private GiaoVienThamGia createGiaoVien(ResultSet rs)
     {
         GiaoVienThamGia gv = new GiaoVienThamGia();
         try {
             gv.setMaGV(rs.getInt("MaGiaoVien"));
             gv.setTenGV(rs.getString("TenGV"));
             gv.setMaLop(rs.getString("MaLop"));
             gv.setMaChuyenDi(rs.getString("MaChuyenDi"));
             gv.setThamGia(rs.getBoolean("ThamGia"));
             
         } catch (Exception e) {
             
         }
         return gv;
     }
     public ObservableList<GiaoVienThamGia> getlistGiaoVien(String MaLopString, String MaChuyenDi)
     {
         String sql = " SELECT * FROM dbo.GiaoVien INNER JOIN dbo.GiaoVienThamGia ON GiaoVienThamGia.MaGiaoVien = GiaoVien.MaGV "
                 + "WHERE MaLop = '"+MaLopString+ "' AND MaChuyenDi = '"+MaChuyenDi+"'";
          System.out.print(sql);
     ObservableList<GiaoVienThamGia> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                GiaoVienThamGia gv = createGiaoVien(rs);
                list.add(gv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load HocSinh");
        }
        return list;
     }
     public  void UpdateHocSinh(HocSinhThamGia hs)
     {
         String sql = "UPDATE dbo.HocSinhThamGia SET ThamGia = '"+hs.isThamGia()+"', IsDongTien = '"+hs.isDaThuPhi()+"' "
                 + "WHERE MaHS = "+hs.getMaHS()+" AND MaLop = '"+hs.getMaLop()+"' AND MaChuyenDi = '"+hs.getMaChuyenDi()+"'";
         System.out.print(sql); 
         try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't cap nhat HocSinh tham gia!");
        }
     }
        public  void UpdateGiaoVien(GiaoVienThamGia gv)
     {
         String sql = "UPDATE dbo.GiaoVienThamGia SET ThamGia = '"+gv.isThamGia()+"' WHERE MaGiaoVien = "+gv.getMaGV()+" AND MaLop = '"+gv.getMaLop()+"' AND MaChuyenDi = '"+gv.getMaChuyenDi()+"'" +
                "SELECT * FROM dbo.GiaoVienThamGia";
         System.out.print(sql);
          try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't cap nhat giao vien tham gia!");
        }
     }
        
        
}
