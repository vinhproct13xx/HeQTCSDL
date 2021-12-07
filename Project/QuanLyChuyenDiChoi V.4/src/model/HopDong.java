/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Scoobydo
 */
public class HopDong {
        private String maHopDong;
        private String maChuyenDi;
        private String maCongTy;
        private float triGia;
        private String trangThai;
        private Date ngayKy;

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public String getMaChuyenDi() {
        return maChuyenDi;
    }

    public void setMaChuyenDi(String maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    public String getMaCongTy() {
        return maCongTy;
    }

    public void setMaCongTy(String maCongTy) {
        this.maCongTy = maCongTy;
    }

    public float getTriGia() {
        return triGia;
    }

    public void setTriGia(float triGia) {
        this.triGia = triGia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    public Date getNgayKy() {
        return ngayKy;
    }

    public void setNgayKy(Date ngayKy) {
        this.ngayKy = ngayKy;
    }    
        
}
