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
public class ChiPhiDAO {
        private ChiPhi createChiPhi(ResultSet rs){
            ChiPhi cp = new ChiPhi();
            try {
                cp.setMaChuyenDi(rs.getString("MaChuyenDi"));
                cp.setVeCong(rs.getFloat("VeCong"));
                cp.setTienXe(rs.getFloat("TienXe"));
                cp.setTienAnTrua(rs.getFloat("TienAnTrua"));
                cp.setPhiHDV(rs.getFloat("PhiHuongDanVien"));
                cp.setNuocUong(rs.getFloat("NuocUong"));
                cp.setTienAnXe(rs.getFloat("TienAnXe"));
                cp.setLinhTinh(rs.getFloat("LinhTinh"));                   
                cp.setGhiChu(rs.getString("GhiChu"));
                
            }catch(Exception e){
            e.printStackTrace();
            System.out.print("Can't create ChiPhi");
            }
            return cp;
        }
    
        public ChiPhi getChiPhi(String MaChuyenDi){
            ChiPhi cp=new ChiPhi();
            String sql1="Select * from ChiPhi where MaChuyenDi='"+MaChuyenDi+"'";
            System.out.println(sql1);
            try {
            ResultSet rs = DBConnect.dbExcute(sql1);
                      
            if (rs.first()==false) {  
                String sql2="insert into ChiPhi values ('"+MaChuyenDi+"',0,0,0,0,0,0,0,'',0)";
                System.out.println(sql1);
                int stmt = DBConnect.dbExcuteQuery(sql2);
                rs = DBConnect.dbExcute(sql1);
                cp=createChiPhi(rs);
            }else  {
                cp=createChiPhi(rs);
            } 
            } catch (Exception e) {
                e.printStackTrace();
                System.out.print("Can't load ChiPhi");
            }
            return cp;
        }
        public void Luu(ChiPhi cp) throws SQLException{
            String sql="exec LuuChiPhi '"+cp.getMaChuyenDi()+"',"+cp.getVetCong()+","+cp.getTienXe()+","
                    +cp.getTienAnTrua()+","+cp.getPhiHDV()+","+cp.getNuocUong()+","+cp.getTienAnXe()+","
                    +cp.getLinhTinh()+",N'"+cp.getGhiChu()+"'";
            System.err.println(sql);
            try {
                ResultSet rs = DBConnect.dbExcute(sql);
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Cam Luu ChiPhi!");
            }
        }
}
