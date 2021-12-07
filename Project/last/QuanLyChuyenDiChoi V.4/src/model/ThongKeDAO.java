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
public class ThongKeDAO {
    public float tkTongChiPhi(int nam) throws SQLException{
        float tong;
        tong=0;
        String sql = "exec chartTongChiPhi "+nam+"";
        System.out.println(sql);
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            try {
                if(rs.first()==true ){
                tong=rs.getFloat("TongChiPhi");
                }
            } catch (Exception e) {
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't lay TongChiPhi");
        }
        return tong;
    }
    public float tkChiPhiTB(int nam) throws SQLException{
        float tb;
        tb=0;
        String sql= "exec chartChiPhiTB "+nam;
        System.out.println(sql);
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            if(rs.first()==true)
            {
                tb=rs.getFloat("ChiPhiTB");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't lay ChiPhiTB!");
        }
        return tb;
    }
    
    public float tkHSTB(int nam) throws SQLException{
        float tb;
        tb=0;
        String sql= "exec chartHSTB "+nam;
        System.out.println(sql);
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            if(rs.first()==true)
            {
                tb=rs.getFloat(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't lay HSTB!");
        }
        return tb;
    }
    
    public int tkChuyenDi (int nam) throws SQLException{
        int cd;
        cd=0;
        String sql = "exec chartSoChuyenDi "+nam;
        System.out.println(sql);
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            if(rs.first()==true)
            {
                cd=rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Can't lay Chuyen di!");
        }
        return cd;
    }
}
