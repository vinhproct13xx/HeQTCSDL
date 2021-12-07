/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.security.PrivateKey;
import java.sql.Time;
import javafx.scene.text.Text;

/**
 *
 * @author MyPC
 */
public class ChiTietChuyenDi {

    private String MaChuyenDi;
    private Time ThoiGian;
    private String HoatDong;
    private String GhiChu;

    public String getMaChuyenDi() {
        return MaChuyenDi;
    }

    public Time getThoiGian() {
        return ThoiGian;
    }

    public String getHoatDong() {
        return HoatDong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setMaChuyenDi(String MaChuyenDi) {
        this.MaChuyenDi = MaChuyenDi;
    }

    public void setThoiGian(Time ThoiGian) {
        this.ThoiGian = ThoiGian;
    }

    public void setHoatDong(String HoatDong) {
        this.HoatDong = HoatDong;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    

}
