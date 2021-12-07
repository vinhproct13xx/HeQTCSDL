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
public class ChuyenDi {
    private String maChuyenDi;
    private String tenCongTy;
    private String tenDiaDiem;
    private  Date ngayKhoiHanh;

    public ChuyenDi() {
    }

    public ChuyenDi(String maChuyenDi, String tenCongTy, String tenDiaDiem, Date ngayKhoiHanhDate) {
        this.maChuyenDi = maChuyenDi;
        this.tenCongTy = tenCongTy;
        this.tenDiaDiem = tenDiaDiem;
        this.ngayKhoiHanh = ngayKhoiHanhDate;
    }

    public String getMaChuyenDi() {
        return maChuyenDi;
    }

    public void setMaChuyenDi(String maChuyenDi) {
        this.maChuyenDi = maChuyenDi;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public Date getNgayKhoiHanh() {
        return ngayKhoiHanh;
    }

    public void setNgayKhoiHanh(Date ngayKhoiHanhDate) {
        this.ngayKhoiHanh = ngayKhoiHanhDate;
    }
    
    
    
}
