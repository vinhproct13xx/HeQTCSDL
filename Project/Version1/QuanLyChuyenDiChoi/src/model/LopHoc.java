
package model;

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
  
     @Override
    public String toString(){
        return this.strTenLop;
    }
}
