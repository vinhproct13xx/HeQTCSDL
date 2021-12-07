/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author Scoobydo
 */
public class CongTyDuLich {
    private String maCongTy;
    private String tenCongTy;
    private String diaChi;
    private String SDT;

    public String getMaCongTy() {
        return maCongTy;
    }

    public void setMaCongTy(String maCongTy) {
        this.maCongTy = maCongTy;
    }

    public String getTenCongTy() {
        return tenCongTy;
    }

    public void setTenCongTy(String tenCongTy) {
        this.tenCongTy = tenCongTy;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSDT() {
        return SDT;
    }

    public void setSDT(String SDT) {
        this.SDT = SDT;
    }
    public  static CongTyDuLich CongTyDuLich(String ID , List<CongTyDuLich> list)
    {
        for(CongTyDuLich d : list)
        {
            if(d.getMaCongTy().equals(ID))
                return d;
        }
        return null;
    }
    @Override
    public String toString(){
        return this.tenCongTy;
    }
}
