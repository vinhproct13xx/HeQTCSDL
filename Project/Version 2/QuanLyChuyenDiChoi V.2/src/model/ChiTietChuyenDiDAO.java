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
        String sql = "select * from ChiTietChuyenDi where MaChuyenDi = "+ MaChuyenDi;
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
}
