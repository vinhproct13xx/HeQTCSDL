/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author MyPC
 */
public class ChiTietChuyenDiDAO {
    private ChiTietChuyenDi createInvoiceDetails(ResultSet rs) {
        ChiTietChuyenDi chiTietChuyenDi = new ChiTietChuyenDi();
        try {
            chiTietChuyenDi.setMaChuyenDi(rs.getString("MaChuyenDi"));
            chiTietChuyenDi.setThoiGian(rs.getTime("ThoiGian"));
            chiTietChuyenDi.setHoatDong(rs.getString("HoatDong"));
            chiTietChuyenDi.setGhiChu(rs.getString("GhiChu"));

        } catch (Exception e) {
        }
        return chiTietChuyenDi;
    }
    public ObservableList<ChiTietChuyenDi> getlistChiTietChuyenDi(String MaChuyenDi)

    {
        String sql = "select * from ChiTietChuyenDi where MaChuyenDi = N'"+ MaChuyenDi+"'";
        ObservableList<ChiTietChuyenDi> list = FXCollections.observableArrayList();
         try {
             ResultSet rs = DBConnect.dbExcute(sql);
             while (rs.next()) {                 
                 ChiTietChuyenDi invoiceDetail = createInvoiceDetails(rs);
                 list.add(invoiceDetail);
             }
         } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public  boolean ThemCTChuyenDi(String maChuyenDi, Time thoiGian, String hoatDong, String ghiChu) throws SQLException{
        String sql ="INSERT INTO dbo.ChiTietChuyenDi( MaChuyenDi ,ThoiGian ,HoatDong ,GhiChu) " +
                    "VALUES  ( N'"+maChuyenDi+ "' , '"+thoiGian+"' , N'"+hoatDong+"' , N'"+ghiChu+"' )";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
    public  boolean SuaCTChuyenDi(String maChuyenDi, Time thoiGian, String hoatDong, String ghiChu) throws SQLException{
        String sql ="UPDATE dbo.ChiTietChuyenDi SET ThoiGian = '"+thoiGian+"',HoatDong = N'"+hoatDong+"',GhiChu = N'"
                +ghiChu+"' WHERE MaChuyenDi=N'"+maChuyenDi+"'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
    public  boolean xoaChuyenDi(String maChuyenDi, Time thoiGian) throws SQLException{
        String sql ="DELETE dbo.ChiTietChuyenDi WHERE MaChuyenDi = '"+ maChuyenDi+ "' AND ThoiGian ='"+thoiGian+"'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;       
    }    
}
