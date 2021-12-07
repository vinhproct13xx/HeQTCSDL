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
 * @author Tung
 */
public class DiaDiemDAO {
    private DiaDiem createDiaDiem (ResultSet rs){
        DiaDiem dd = new DiaDiem();
        try{
            dd.setMaDiaDiem(rs.getString("MaDiaDiem"));
            dd.setTenDiaDiem(rs.getString("TenDiaDiem"));
            dd.setDiaChi(rs.getString("DiaChi"));
        } catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't load DiaDiem");
        }
        return dd;
    }
    
    public ObservableList<DiaDiem> getlistDiaDiem() throws SQLException {
        String sql = "SELECT * FROM dbo.DiaDiem order by MaDiaDiem desc";
        System.out.println(sql);
        ObservableList<DiaDiem> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                DiaDiem dd = createDiaDiem(rs);
                list.add(dd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load DiaDiem!");
        }
        return list;
    }
    
    public void Them(DiaDiem dd) throws SQLException{
        String sql= "insert into DiaDiem values ('DD"+dd.getMaDiaDiem()+"',N'"+dd.getTenDiaDiem() + "',N'"+dd.getDiaChi() + "')";
        System.out.println(sql);
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't add DiaDiem!");
        }
    }
    public void Xoa(DiaDiem dd) {
        String sql = "delete DiaDiem where MaDiaDiem = '" + dd.getMaDiaDiem() + "'";
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't delete DiaDiem!");
        }
    }
    
    public void Sua(DiaDiem dd){
        String sql="update DiaDiem set TenDiaDiem = N'"+dd.getTenDiaDiem()+"' , DiaChi = N'" + dd.getDiaChi() + "' where MaDiaDiem = '"+dd.getMaDiaDiem() +"'";
        try {
            int stmt = DBConnect.dbExcuteQuery(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Can't update DiaDiem!");
        }
    }
    
    public int MaxMaDiaDiem() {
        String sql = "SELECT MAX(dbo.DiaDiem.MaDiaDiem) AS maxdd FROM dbo.DiaDiem";
        ResultSet rs = null;
        int max = 0;
        String maxstring;
        try {
            rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                maxstring = rs.getString("maxdd");
                max=Integer.parseInt(maxstring.substring(2));
            }
        } catch (Exception e) {
        }
        return max;
    }
}
