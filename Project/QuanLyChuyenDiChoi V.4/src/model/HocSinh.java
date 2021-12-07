/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author MyPC
 */
public class HocSinh {
    private int MaHS;
    private String TenHS;
    private Date NgaySinh;
    private String DiaChi;
    private String TenCha;
    private String TenMe;
    private String TenNguoiGiamHo;
    private String SDT;
    private String MaLop;
    private int NamHoc;

    public HocSinh(int MaHS, String TenHS, Date NgaySinh,String DiaChi, String TenCha, String TenMe, String TenNguoiGiamHo, String SDT) {
        this.MaHS = MaHS;
        this.TenHS = TenHS;
        this.NgaySinh = NgaySinh;
        this.DiaChi = DiaChi;
        this.TenCha = TenCha;
        this.TenMe = TenMe;
        this.TenNguoiGiamHo = TenNguoiGiamHo;
        this.SDT = SDT;
        
    }

    public HocSinh() {
    }

    public int getMaHS() {
        return MaHS;
    }

    public String getTenHS() {
        return TenHS;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }
public String getDiaChi() {
        return DiaChi;
    }
    public String getTenCha() {
        return TenCha;
    }

    public String getTenMe() {
        return TenMe;
    }

    public String getTenNguoiGiamHo() {
        return TenNguoiGiamHo;
    }

    public String getSDT() {
        return SDT;
    }

    public void setMaHS(int MaHS) {
        this.MaHS = MaHS;
    }

    public void setTenHS(String TenHS) {
        this.TenHS = TenHS;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }
    
    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setTenCha(String TenCha) {
        this.TenCha = TenCha;
    }

    public void setTenMe(String TenMe) {
        this.TenMe = TenMe;
    }

    public void setTenNguoiGiamHo(String TenNguoiGiamHo) {
        this.TenNguoiGiamHo = TenNguoiGiamHo;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    public String getMaLop(){
        return MaLop;
    }
     public void setMaLop(String MaLop) {
      this.MaLop  = MaLop;
    }
      public Integer getNamHoc(){
        return NamHoc;
    }
     public void setNamHoc(int MaNH) {
        this.NamHoc = MaNH;
    }
    
    
}
