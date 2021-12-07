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
public class ThanhToan {
    private String maHopDong;
    private int lanThanhToan;
    private Date ngayThanhToan;
    private Float soTien;

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public int getLanThanhToan() {
        return lanThanhToan;
    }

    public void setLanThanhToan(int lanThanhToan) {
        this.lanThanhToan = lanThanhToan;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Float getSoTien() {
        return soTien;
    }

    public void setSoTien(Float soTien) {
        this.soTien = soTien;
    }
    
    
}
