/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author MyPC
 */
public class LopHoc {
    private String strMaLop;
    private String strTenLop;
   

    public String getStrMaLop() {
        return strMaLop;
    }

    public String getStrTenLop() {
        return strTenLop;
    }

    public void setStrMaLop(String strMaLop) {
        this.strMaLop = strMaLop;
    }

    public void setStrTenLop(String strTenLop) {
        this.strTenLop = strTenLop;
    }
   public  static LopHoc lopHoc(String ID , List<LopHoc> list)
    {
        for(LopHoc lh : list)
        {
            if(lh.getStrMaLop().equals(ID))
                return lh;
        }
        return null;
    }
     @Override
    public String toString(){
        return this.strTenLop;
    }
}
