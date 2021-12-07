/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Date;

/**
 *
 * @author Scoobydo
 */
public class GiaoVien {
    private int MaGV;
    private String TenGV;
    private Date NgaySinh;
    private String DiaChi;
    private String SDT;
    private String CMND;
    private String MaLop;
    private Integer MaNH;

    public int getMaGV() {
        return MaGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public String getCMND() {
        return CMND;
    }

    public String getMaLop() {
        return MaLop;
    }

    public Integer getMaNH() {
        return MaNH;
    }

    public void setMaGV(int MaGV) {
        this.MaGV = MaGV;
    }

    public void setTenGV(String TenGV) {
        this.TenGV = TenGV;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }

    public void setCMND(String CMND) {
        this.CMND = CMND;
    }

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public void setMaNH(Integer MaNH) {
        this.MaNH = MaNH;
    }

    

   
    
    
}
