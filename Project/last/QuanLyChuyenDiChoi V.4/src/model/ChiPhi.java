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
public class ChiPhi {
    private String MaChyenDi;
    private float VeCong;
    private float TienXe;
    private float TienAnTrua;
    private float PhiHDV;
    private float NuocUong;
    private float TienAnXe;
    private float LinhTinh;
    private float TongTien;
    private String GhiChu;
    
    public ChiPhi(String MaChyenDi,float VeCong,float TienXe,float TienAnTrua,float PhiHDV,float NuocUong,float TienAnXe,float LinhTinh,float TongTien,String GhiChu){
        this.MaChyenDi=MaChyenDi;
        this.VeCong=VeCong;
        this.TienXe=TienXe;
        this.TienAnTrua=TienAnTrua;
        this.PhiHDV=PhiHDV;
        this.NuocUong=NuocUong;
        this.TienAnXe=TienAnXe;
        this.LinhTinh=LinhTinh;
        this.GhiChu=GhiChu;
    }
    public ChiPhi(){
    }
    
    public String getMaChuyenDi(){
        return MaChyenDi;
    }
    public String getGhiChu(){
        return GhiChu;
    }
    public float getVetCong(){
        return VeCong;
    }
    public float getTienXe(){
        return TienXe;
    }
    public float getTienAnTrua(){
        return TienAnTrua;
    }
    public float getPhiHDV(){
        return PhiHDV;
    }
    public float getNuocUong(){
        return NuocUong;
    }
    public float getTienAnXe(){
        return TienAnXe;
    }
    public float getLinhTinh(){
        return LinhTinh;
    }
    public void setMaChuyenDi(String MaChuyenDi){
        this.MaChyenDi=MaChuyenDi;
    }
    public void setGhiChu(String GhiChu){
        this.GhiChu=GhiChu;
    }
    public void setVeCong(float VeCong){
        this.VeCong=VeCong;
    }
    public void setTienXe(float TienXe){
        this.TienXe=TienXe;
    }
    public void setTienAnTrua(float TienAnTrua){
        this.TienAnTrua=TienAnTrua;
    }
    public void setPhiHDV(float PhiHDV){
        this.PhiHDV=PhiHDV;
    }
    public void setNuocUong(float NuocUong){
        this.NuocUong=NuocUong;
    }
    public void setTienAnXe(float TienAnXe){
        this.TienAnXe=TienAnXe;
    }
    public void setLinhTinh(float LinhTinh){
        this.LinhTinh=LinhTinh;
    }
}
