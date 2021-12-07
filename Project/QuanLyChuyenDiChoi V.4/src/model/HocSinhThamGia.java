/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MyPC
 */
public class HocSinhThamGia {
    private int MaHS;
    private  String TenHS;
    private  String MaLop;
    private String MaChuyenDi;
    private  boolean ThamGia;
    private boolean DaThuPhi;

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public String getMaLop() {
        return MaLop;
    }

    public int getMaHS() {
        return MaHS;
    }

    public String getTenHS() {
        return TenHS;
    }

    public boolean isThamGia() {
        return ThamGia;
    }

    public boolean isDaThuPhi() {
        return DaThuPhi;
    }

    public void setMaHS(int MaHS) {
        this.MaHS = MaHS;
    }

    public void setTenHS(String TenHS) {
        this.TenHS = TenHS;
    }

    public void setThamGia(boolean ThamGia) {
        this.ThamGia = ThamGia;
    }

    public void setDaThuPhi(boolean DaThuPhi) {
        this.DaThuPhi = DaThuPhi;
    }

    public String getMaChuyenDi() {
        return MaChuyenDi;
    }

    public void setMaChuyenDi(String MaChuyenDi) {
        this.MaChuyenDi = MaChuyenDi;
    }
    
}
