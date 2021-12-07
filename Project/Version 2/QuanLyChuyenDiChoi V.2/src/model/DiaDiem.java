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
public class DiaDiem {
    private String maDiaDiem;
    private String tenDiaDiem;
    private String diaChi;

    public String getMaDiaDiem() {
        return maDiaDiem;
    }

    public void setMaDiaDiem(String maDiaDiem) {
        this.maDiaDiem = maDiaDiem;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    public  static DiaDiem DiaDiem(String ID , List<DiaDiem> list)
    {
        for(DiaDiem d : list)
        {
            if(d.getMaDiaDiem().equals(ID))
                return d;
        }
        return null;
    }
    @Override
    public String toString(){
        return getTenDiaDiem();
    }
}
