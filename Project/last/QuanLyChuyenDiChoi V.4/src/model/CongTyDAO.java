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
       public  boolean ThemCongTy( String tenCongTy, String diaChi, String SDT) throws SQLException{
        String sql ="INSERT INTO dbo.CongTyDuLich(  TenCongTy ,DiaChi ,SDT) " +
                    "VALUES  ( N'"+tenCongTy+"' , N'"+diaChi+"' , N'"+SDT+"' )";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
       public int MaxMaCongTy(){
        String sql = "SELECT MAX(MaCongTy) AS maxcd FROM dbo.CongTyDuLich";
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
        public  boolean xoaCongTy(String maCongTy) throws SQLException{
        
        String sql ="EXEC dbo.USP_DeleteCongTy @maCongTy = N'"+ maCongTy+ "'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;       
    }
        public ObservableList<CongTyDuLich> TimCongTy(String data) throws SQLException {

        String sql = "SELECT * FROM dbo.CongTyDuLich WHERE TenCongTy LIKE N'%"+data
                +"%' OR DiaChi LIKE N'%"+data+"%' OR SDT LIKE N'%"+data+"%'";
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
            System.out.print("Can't load Cong Ty");
        }

        return list;
    }
    public  boolean suaCongTy(String maCongTy, String tenCongTy,  String diaChi, String SDT) throws SQLException{
        String sql ="UPDATE dbo.CongTyDuLich SET TenCongTy = N'"+tenCongTy+"',DiaChi = N'"
                +diaChi+"', SDT = N'"+SDT+"'  WHERE MaCongTy= N'"+maCongTy+"'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
}
