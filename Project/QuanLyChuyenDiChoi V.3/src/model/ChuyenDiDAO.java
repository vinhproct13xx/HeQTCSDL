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
public class ChuyenDiDAO {

    public ObservableList<ChuyenDi> getlistChuyenDi() throws SQLException {
//        String sql = "SELECT MaChuyenDi,TenCongTy,TenDiaDiem,NgayKhoiHanh FROM dbo.ChuyenDi,dbo.DiaDiem,dbo.CongTyDuLich "
//                + "where dbo.ChuyenDi.MaCongTyDuLich=dbo.CongTyDuLich.MaCongTy AND dbo.ChuyenDi.MaDiaDiem=dbo.DiaDiem.MaDiaDiem " ;
        String sql = "select * from ChuyenDi";
        System.out.println(sql);
        ObservableList<ChuyenDi> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                ChuyenDi cd = new ChuyenDi();
                cd.setMaChuyenDi(rs.getString(1));
                cd.setTenCongTy(rs.getString(2));
                cd.setTenDiaDiem(rs.getString(3));
                cd.setNgayKhoiHanh(rs.getDate(4));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load Chuyen di");
        }

        return list;
    }
    public  boolean ThemChuyenDi(String maChuyenDi, String maCongTy, String maDiaDiem, Date ngayKhoiHanh) throws SQLException{
        String sql ="INSERT INTO dbo.ChuyenDi( MaChuyenDi ,MaCongTyDuLich ,MaDiaDiem ,NgayKhoiHanh) " +
                    "VALUES  ( N'CD"+maChuyenDi+ "' , N'"+maCongTy+"' , N'"+maDiaDiem+"' , '"+ngayKhoiHanh+"' )";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
     public  boolean SuaChuyenDi(String maChuyenDi, String maCongTy, String maDiaDiem, Date ngayKhoiHanh) throws SQLException{
        String sql ="UPDATE dbo.ChuyenDi SET MaCongTyDuLich = N'"+maCongTy+"',MaDiaDiem = N'"+maDiaDiem+"',NgayKhoiHanh = '"
                +ngayKhoiHanh+"' WHERE MaChuyenDi=N'"+maChuyenDi+"'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
    public  boolean xoaChuyenDi(String maChuyenDi) throws SQLException{
        
        String sql ="DELETE dbo.ChuyenDi WHERE MaChuyenDi = '"+ maChuyenDi+ "'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;       
    }    
    public ObservableList<ChuyenDi> SearchChuyenDi(Date ngayKhoiHanh) throws SQLException {
//        String sql = "SELECT MaChuyenDi,TenCongTy,TenDiaDiem,NgayKhoiHanh FROM dbo.ChuyenDi,dbo.DiaDiem,dbo.CongTyDuLich "
//                + "where dbo.ChuyenDi.MaCongTyDuLich=dbo.CongTyDuLich.MaCongTy AND dbo.ChuyenDi.MaDiaDiem=dbo.DiaDiem.MaDiaDiem " ;
        String sql = "SELECT * FROM dbo.ChuyenDi WHERE NgayKhoiHanh = '"+ngayKhoiHanh+"'";
        System.out.println(sql);
        ObservableList<ChuyenDi> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                ChuyenDi cd = new ChuyenDi();
                cd.setMaChuyenDi(rs.getString(1));
                cd.setTenCongTy(rs.getString(2));
                cd.setTenDiaDiem(rs.getString(3));
                cd.setNgayKhoiHanh(rs.getDate(4));
                list.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load Chuyen di");
        }

        return list;
    }
    public int MaxMaChuyenDi(){
        String sql = "SELECT MAX(MaChuyenDi) AS maxcd FROM dbo.ChuyenDi";
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
                        max=Integer.parseInt(maxstring.substring(2));
                    System.out.println(max);
                }
            } 
        catch (SQLException ex) {
            Logger.getLogger(ChuyenDiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
}
