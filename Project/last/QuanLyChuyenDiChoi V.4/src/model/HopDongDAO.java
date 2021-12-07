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
import javafx.scene.control.Alert;

/**
 *
 * @author Scoobydo
 */
public class HopDongDAO {
     public ObservableList<HopDong> getlistHopDong() throws SQLException {

        String sql = "SELECT * FROM dbo.HopDong";
        System.out.println(sql);
        ObservableList<HopDong> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                HopDong hd = new HopDong();
                hd.setMaHopDong(rs.getString(1));
                hd.setMaChuyenDi(rs.getString(2));
                hd.setMaCongTy(rs.getString(3));
                hd.setTriGia(rs.getFloat(4));
                hd.setTrangThai(rs.getString(5));
                hd.setNgayKy(rs.getDate(6));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load hop dong");
        }

        return list;
    }
      public ObservableList<ThanhToan> getlistThanhToan(String maHopDong) throws SQLException {

        String sql = "SELECT * FROM dbo.ThongTinThanhToan WHERE MaHopDong=N'"+maHopDong+"'";
        System.out.println(sql);
        ObservableList<ThanhToan> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                ThanhToan tt = new ThanhToan();
                tt.setMaHopDong(rs.getString(1));
                tt.setLanThanhToan(rs.getInt(2));
                tt.setNgayThanhToan(rs.getDate(3));
                tt.setSoTien(rs.getFloat(4));

                list.add(tt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load thanh toan");
        }

        return list;
    }
     public  boolean themHopDong( String maChuyenDi, String maCongTy, float triGia,Date ngayKy) throws SQLException{
        String sql ="INSERT INTO dbo.HopDong( MaChuyenDi ,MaCongTy ,TriGia, TrangThai,NgayKy) " +
                    "VALUES  (  N'"+maChuyenDi+"' , N'"+maCongTy+"' , "+triGia+" , N'Chưa thanh toán' , '"+ngayKy+"' )";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
     public float TongTienThanhToan(String maHopDong) throws SQLException{
         String sql="SELECT SUM(SoTien) AS maxtt FROM dbo.ThongTinThanhToan WHERE MaHopDong=N'"+maHopDong+"'";
         System.out.println("sql");
         float result = 0;
         String maxstring;
         ResultSet rs=null;
         rs=DBConnect.dbExcute(sql);
        while (rs.next()){
                    maxstring=rs.getString("maxtt");
                    if(maxstring==null)
                    {
                        result=0;
                    }
                    else
                        result=Float.parseFloat(maxstring);
                    System.out.println(result);
                }
         return result;
     }
     public float getTriGiaHD(String maHopDong) throws SQLException{
         String sql="SELECT TriGia FROM dbo.HopDong WHERE MaHopDong = N'"+maHopDong+"'";
         System.out.println(sql);
         float triGiaHD=0;
         ResultSet rs=null;
         rs=DBConnect.dbExcute(sql);
         while(rs.next()){
             triGiaHD=rs.getFloat("TriGia");
         }
         return triGiaHD;
     }
     public int MaxMaHopDong(){
        String sql = "SELECT MAX(MaHopDong) AS maxcd FROM dbo.HopDong";
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
     public int MaxLanThanhToan(String maHopDong){
        String sql = "SELECT MAX(LanThanhToan) as maxLTT  FROM dbo.ThongTinThanhToan WHERE MaHopDong = N'"+maHopDong+"'";
        ResultSet rs = null;
        int max=0;
    
        try {
            rs=DBConnect.dbExcute(sql);
                while (rs.next()){
                    max=rs.getInt("maxLTT");
                    if(String.valueOf(max)==null)
                    {
                        max=0;
                    }
    
                    System.out.println(max);
                }
            } 
        catch (SQLException ex) {
            Logger.getLogger(ChuyenDiDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return max;
    }
      public  boolean SuaHopDong(String maHopDong, String maChuyenDi, String maCongTy, float triGia,Date ngayKy) throws SQLException{
        String sql ="UPDATE dbo.HopDong SET MaChuyenDi = N'"+maChuyenDi+"',MaCongTy = N'"+maCongTy+"', TriGia ="+triGia+", NgayKy = '"
                +ngayKy+"' WHERE MaHopDong=N'"+maHopDong+"'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
      public  boolean xoaHopDong(String maHopDong) throws SQLException{
        
        String sql ="EXEC dbo.USP_DeleteHopDong @maHD = N'"+ maHopDong+ "'";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;       
    }
      public ObservableList<HopDong> SearchHopDong(String maHopDong) throws SQLException {
//        String sql = "SELECT MaChuyenDi,TenCongTy,TenDiaDiem,NgayKhoiHanh FROM dbo.ChuyenDi,dbo.DiaDiem,dbo.CongTyDuLich "
//                + "where dbo.ChuyenDi.MaCongTyDuLich=dbo.CongTyDuLich.MaCongTy AND dbo.ChuyenDi.MaDiaDiem=dbo.DiaDiem.MaDiaDiem " ;
        String sql = "SELECT * FROM dbo.HopDong WHERE MaHopDong = N'"+maHopDong+"'";
        System.out.println(sql);
        ObservableList<HopDong> list = FXCollections.observableArrayList();
        try {
            ResultSet rs = DBConnect.dbExcute(sql);
            while (rs.next()) {
                HopDong hd = new HopDong();
                 hd.setMaHopDong(rs.getString(1));
                hd.setMaChuyenDi(rs.getString(2));
                hd.setMaCongTy(rs.getString(3));
                hd.setTriGia(rs.getFloat(4));
                hd.setTrangThai(rs.getString(5));
                hd.setNgayKy(rs.getDate(6));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print("Can't load Hop dong");
        }

        return list;
    }
      
      public  boolean themThanhToan(String maHopDong, int lanThanhToan, Date ngayThanhToan, float soTien) throws SQLException {
        String sql ="INSERT INTO dbo.ThongTinThanhToan( MaHopDong ,LanThanhToan ,NgayThanhToan ,SoTien) " +
                    "VALUES  ( N'"+maHopDong+ "' , "+lanThanhToan+" , '"+ngayThanhToan+"' , "+soTien+"  )";
        System.out.println(sql);
        
        int row = 0;
        row = DBConnect.dbExcuteQuery(sql);
        return row>0;
        
    }
      public  boolean xoaThanhToan(String maHopDong, int lanThanhToan) throws SQLException{
        
        String sql ="DELETE dbo.ThongTinThanhToan WHERE MaHopDong = N'"+maHopDong+"' AND LanThanhToan = "+lanThanhToan+"";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;       
    }
      public  boolean suaThanhToan(String maHopDong, int lanThanhToan, Date ngayThanhToan, float soTien) throws SQLException{
        String sql ="UPDATE dbo.ThongTinThanhToan SET NgayThanhToan = '"+ngayThanhToan+"', SoTien ="+soTien+""
                + " WHERE MaHopDong = N'"+maHopDong+"' AND LanThanhToan ="+lanThanhToan+"";
        System.out.println(sql);
        int row = DBConnect.dbExcuteQuery(sql);
        if(row>0)
            return true;
        else
            return false;
        
    }
}
