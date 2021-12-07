/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.sql.Date;
import javafx.scene.text.Text;

/**
 *
 * @author Tung
 */
public class DiaDiem {
    private String MaDiaDiem;
    private String TenDiaDiem;
    private String DiaChi;
    
    public DiaDiem(String MaDiaDiem,String TenDiaDiem,String DiaChi){
        this.MaDiaDiem = MaDiaDiem;
        this.TenDiaDiem = TenDiaDiem;
        this.DiaChi = DiaChi;
    }
    
    public DiaDiem(){
    }
    
    public String getMaDiaDiem(){
        return MaDiaDiem;
    }
    
    public String getTenDiaDiem(){
        return TenDiaDiem;
    }
    
    public String getDiaChi(){
        return DiaChi;
    }
    
    public void setMaDiaDiem(String MaDiaDiem){
        this.MaDiaDiem = MaDiaDiem;
    }
    
    public void setTenDiaDiem(String TenDiaDiem){
        this.TenDiaDiem = TenDiaDiem;
    }
    
    public void setDiaChi(String DiaChi){
        this.DiaChi = DiaChi;
    }

   
}
