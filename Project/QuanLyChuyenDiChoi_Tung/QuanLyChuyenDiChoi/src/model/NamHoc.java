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
public class NamHoc {
    private int MaNH;
    private String TenNH;

    public int getMaNH() {
        return MaNH;
    }

    public String getTenNH() {
        return TenNH;
    }

    public void setMaNH(int MaNH) {
        this.MaNH = MaNH;
    }

    public void setTenNH(String TenNH) {
        this.TenNH = TenNH;
    }
    @Override
    public String toString(){
        return TenNH;
    }
}
