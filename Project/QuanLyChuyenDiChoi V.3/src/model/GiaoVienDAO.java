/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Scoobydo
 */
public class GiaoVienDAO {
    public ObservableList<GiaoVien> getlistGiaoVien() throws SQLException {

        String sql = "select * from dbo.GiaoVien";
        System.out.println(sql);
        ObservableList<GiaoVien> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                GiaoVien gv = new GiaoVien();
                gv.setMaGiaoVien(rs.getInt(1));
                gv.setTenGiaoVien(rs.getString(2));
                gv.setNgaySinh(rs.getDate(3));
                gv.setDiaChi(rs.getString(4));
                gv.setSDT(rs.getString(5));
                gv.setCMND(rs.getString(6));
                gv.setMaLop(rs.getString(7));
                list.add(gv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load giao vien");
        }

        return list;
    }
    public ObservableList<GiaoVien> TimGiaoVien(String data) throws SQLException {

        String sql = "SELECT * FROM dbo.GiaoVien WHERE TenGV LIKE N'%"+data
                +"%' OR DiaChi LIKE N'%"+data+"%' OR SDT LIKE N'%"+data+"%' OR CMND LIKE N'%"+data+"%'";
        System.out.println(sql);
        ObservableList<GiaoVien> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                GiaoVien gv = new GiaoVien();
                gv.setMaGiaoVien(rs.getInt(1));
                gv.setTenGiaoVien(rs.getString(2));
                gv.setNgaySinh(rs.getDate(3));
                gv.setDiaChi(rs.getString(4));
                gv.setSDT(rs.getString(5));
                gv.setCMND(rs.getString(6));
                gv.setMaLop(rs.getString(7));
                list.add(gv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load giao vien");
        }

        return list;
    }
    public  boolean ThemGiaoVien(String maGiaoVien, String tenGiaoVien, Date ngaySinh, String diaChi, String SDT,String CMND, String maLop) throws SQLException{
        String sql ="INSERT INTO dbo.GiaoVien( MaGV ,TenGV ,NgaySinh ,DiaChi,SDT,CMND,MaLop) " +
                    "VALUES  ("+maGiaoVien+ " , N'"+tenGiaoVien+"' , '"+ngaySinh+"' , N'"+diaChi+"' , N'"+SDT+"' , N'"+CMND+"' , N'"+maLop+"' )";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
    public  boolean suaGiaoVien(String maGiaoVien, String tenGiaoVien, Date ngaySinh, String diaChi, String SDT,String CMND, String maLop) throws SQLException{
        String sql ="UPDATE dbo.GiaoVien SET TenGV = N'"+tenGiaoVien+"',NgaySinh = '"+ngaySinh+"',DiaChi = N'"
                +diaChi+"', SDT = N'"+SDT+"', CMND = N'"+CMND+"', MaLop = N'"+maLop+"' WHERE MaGV= "+maGiaoVien+"";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
     public  boolean xoaGiaoVien(String maGiaoVien) throws SQLException{
        
        String sql ="DELETE dbo.GiaoVien WHERE MaGV = '"+ maGiaoVien+ "'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;       
    }    
     public int MaxMaGiaoVien(){
        String sql = "SELECT MAX(MaGV) AS maxcd FROM dbo.GiaoVien";
        ResultSet rs = null;
        int max=0;
        String maxstring;
        try {
            rs=DBConnect.dbExcute(sql);
                while (rs.next()){
                    maxstring=rs.getString("maxcd");
                    if(maxstring==null)
                    {
                        max=0;
                    }
                    else
                        max=Integer.parseInt(maxstring);
                    System.out.println(max);
                }
            } 
        catch (SQLException ex) {
            Logger.getLogger(ChuyenDiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
    
}
