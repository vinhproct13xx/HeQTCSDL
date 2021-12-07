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
public class GiaoVienThamGia {
    private int MaGV;
    private String TenGV;
    private boolean ThamGia;
    private String MaLop;
    private String MaChuyenDi;

    public void setMaLop(String MaLop) {
        this.MaLop = MaLop;
    }

    public void setMaChuyenDi(String MaChuyenDi) {
        this.MaChuyenDi = MaChuyenDi;
    }
       
    public String getMaLop() {
        return MaLop;
    }

    public String getMaChuyenDi() {
        return MaChuyenDi;
    }

    public int getMaGV() {
        return MaGV;
    }

    public String getTenGV() {
        return TenGV;
    }

    public boolean isThamGia() {
        return ThamGia;
    }

    public void setMaGV(int MaGV) {
        this.MaGV = MaGV;
    }

    public void setTenGV(String TenGV) {
        this.TenGV = TenGV;
    }

    public void setThamGia(boolean ThamGia) {
        this.ThamGia = ThamGia;
    }
    
}
