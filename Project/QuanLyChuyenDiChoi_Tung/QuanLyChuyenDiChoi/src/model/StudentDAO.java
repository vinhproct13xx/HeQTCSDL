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
import javax.naming.spi.DirStateFactory;

/**
 *
 * @author MyPC
 */
public class StudentDAO {

    private HocSinh createStudent(ResultSet rs) {
        HocSinh hs = new HocSinh();
        try {
            hs.setMaHS(rs.getInt("MaHS"));
            hs.setTenHS(rs.getString("TenHS"));
            hs.setNgaySinh(rs.getDate("NgaySinh"));
            hs.setDiaChi(rs.getString("DiaChi"));
            hs.setTenCha(rs.getString("TenCha"));
            hs.setTenMe(rs.getString("TenMe"));
            hs.setTenNguoiGiamHo(rs.getString("TenNguoiGiamHo"));
            hs.setSDT(rs.getString("SDT"));
            hs.setMaLop(rs.getString("MaLop"));
            hs.setNamHoc(rs.getInt("MaNH"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load HocSinh");
        }
        return hs;
    }

    public ObservableList<HocSinh> getlistStudents(String MaLop, int MaNH) throws SQLException {
        String sql = "SELECT * FROM dbo.HocSinh INNER JOIN dbo.CTLop ON CTLop.MaHS = HocSinh.MaHS WHERE MaLop = '" + MaLop +
                "' AND MaNH = " +MaNH;
        System.out.println(sql);
        ObservableList<HocSinh> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                HocSinh hs = createStudent(rs);
                list.add(hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load HocSinh");
        }
        return list;
    }

    private LopHoc createClass(ResultSet rs) {
        LopHoc c = new LopHoc();
        try {
            c.setStrMaLop(rs.getString("MaLop"));
            c.setStrTenLop(rs.getString("TenLop"));
           

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load LopHoc");
        }
        return c;
    }

    public ObservableList<LopHoc> getListClass() throws SQLException {
        String sql = "select * from  LopHoc";
        ObservableList<LopHoc> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                LopHoc hs = createClass(rs);
                list.add(hs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load lop hoc");
        }
        return list;
    }
    private NamHoc createScholastic(ResultSet rs) {
        NamHoc scholastic = new NamHoc();
        try {
            
            scholastic.setMaNH(rs.getInt("MaNH"));
            scholastic.setTenNH(rs.getString("TenNH"));

        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load Nam hoc");
        }
        return scholastic;
    }

    public ObservableList<NamHoc> getListScholastics() throws SQLException {
        String sql = "select * from  NamHoc";
        ObservableList<NamHoc> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                NamHoc scholastic = createScholastic(rs);
                list.add(scholastic);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load HocSinh");
        }
        return list;
    }
}
