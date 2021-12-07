/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import  javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import model.ChiPhi;
import model.ChiPhiDAO;

/**
 * FXML Controller class
 *
 * @author Tung
 */
public class ChiPhiController implements Initializable {

    @FXML
    private Button btnCapNhat;
    @FXML
    private Button btnLuu;
    @FXML
    private JFXTextField tfVeCong;
    @FXML
    private JFXTextField tfPhiHDV;
    @FXML
    private JFXTextField tfTienAnTrua;
    @FXML
    private JFXTextField tfTienXe;
    @FXML
    private JFXTextField tfTienAnXe;
    @FXML
    private JFXTextField tfNuocUong;
    @FXML
    private JFXTextField tfLinhTinh;
    @FXML
    private JFXTextField tfTongTien;
    @FXML
    private Label lbThongBao;
    @FXML
    private JFXTextArea taGhiChu;
    private ChiPhiDAO cpdao;
    @FXML
    private Label lbMaChuyenDi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbThongBao.setVisible(true);
        tfTongTien.setEditable(false);
        try {
            loadData();
        } catch (SQLException ex){
            System.out.println("Can't load data in initialize");
        }
    }
    public void loadData() throws SQLException{
        ChiPhi cp = new ChiPhi();
        cpdao = new ChiPhiDAO();
        cp=cpdao.getChiPhi(lbMaChuyenDi.getText());
        tfLinhTinh.setText(String.valueOf(cp.getLinhTinh()));
        tfNuocUong.setText(String.valueOf(cp.getNuocUong()));
        taGhiChu.setText(cp.getGhiChu());
        tfPhiHDV.setText(String.valueOf(cp.getPhiHDV()));
        tfTienAnTrua.setText(String.valueOf(cp.getTienAnTrua()));
        tfTienAnXe.setText(String.valueOf(cp.getTienAnXe()));
        tfTienXe.setText(String.valueOf(cp.getTienXe()));
        tfVeCong.setText(String.valueOf(cp.getVetCong()));
        float tong;
        tong= cp.getLinhTinh() + cp.getNuocUong() + cp.getPhiHDV() + cp.getTienAnTrua() + cp.getTienAnXe()
                + cp.getTienXe() + cp.getVetCong();
        tfTongTien.setText(String.valueOf(tong));
    }
   
    @FXML
    private void btnCapNhatClick(ActionEvent event) {
        if (checkNull()==false){
            lbThongBao.setText("Vui lòng nhập chi phí đầy đủ!!");
            lbThongBao.setVisible(true);     
        } else if (checkSo()==false){
            lbThongBao.setVisible(true);
            lbThongBao.setText("Vui lòng chỉ nhập số!!");
            }
        else {
            lbThongBao.setVisible(false);
            float tong;     
            tong=Float.parseFloat(tfLinhTinh.getText())+Float.parseFloat(tfNuocUong.getText())+Float.parseFloat(tfPhiHDV.getText())+
                    Float.parseFloat(tfTienAnTrua.getText())+Float.parseFloat(tfTienAnXe.getText())+Float.parseFloat(tfTienXe.getText()) +
                    Float.parseFloat(tfVeCong.getText());
            System.out.println(tong);
            tfTongTien.setText(String.valueOf(tong));
        }
    }


    @FXML
    private void btnLuuClick(ActionEvent event) throws SQLException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cảnh báo");
        alert.setHeaderText("Góc hỏi đáp:");
        alert.setContentText("Bạn có thật sự muốn lưu không?");
        ButtonType buttonTypeYes = new ButtonType("Đồng ý");
        ButtonType buttonTypeNo = new ButtonType("Hủy");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        switch (result.get().getText()) {
        case "Đồng ý":{
            if (checkNull()==false){
                lbThongBao.setText("Vui lòng nhập chi phí đầy đủ!!");
                lbThongBao.setVisible(true);     
            } else if (checkSo()==false){
                lbThongBao.setVisible(true);  
                lbThongBao.setText("Vui lòng chỉ nhập số!!");
                }
            else {
                lbThongBao.setVisible(false);
                ChiPhi cp = new ChiPhi();
                if(taGhiChu.getText()==null)
                    taGhiChu.setText("");
                cp.setGhiChu(taGhiChu.getText());
                cp.setLinhTinh(Float.parseFloat(tfLinhTinh.getText()));
                cp.setMaChuyenDi(lbMaChuyenDi.getText());
                cp.setNuocUong(Float.parseFloat(tfNuocUong.getText()));
                cp.setPhiHDV(Float.parseFloat(tfPhiHDV.getText()));
                cp.setTienAnTrua(Float.parseFloat(tfTienAnTrua.getText()));
                cp.setTienAnXe(Float.parseFloat(tfTienAnXe.getText()));
                cp.setTienXe(Float.parseFloat(tfTienXe.getText()));
                cp.setVeCong(Float.parseFloat(tfVeCong.getText()));
                try {
                    cpdao.Luu(cp);
                    Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                    alert1.setTitle("Thông báo");
                    alert1.setHeaderText("Góc chúc mừng:");
                    alert1.setContentText("Thành công yeah yeah!!");
                    alert1.showAndWait();
                } catch (Exception e) {
                    System.out.println("Không thể thêm dữ liệu!!!");
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Thông báo");
                    alert2.setHeaderText("Góc chia buồn:");
                    alert2.setContentText("Lỗi cập nhật dữ liệu!!");
                    alert2.showAndWait();
                }
                loadData();
            }
            break;
        }
        case "Hủy":
            break;
        default:
            break;
    }
    }

    @FXML
    private void tfVeCongClick(ActionEvent event) {
    }

    @FXML
    private void tfPhiHDVClick(ActionEvent event) {
    }

    @FXML
    private void tfTienAnTruaClick(ActionEvent event) {
    }

    @FXML
    private void tfTienXeClick(ActionEvent event) {
    }

    @FXML
    private void tfTienAnXeClick(ActionEvent event) {
    }

    @FXML
    private void tfNuocUongClick(ActionEvent event) {
    }

    @FXML
    private void tfLinhTinhClick(ActionEvent event) {
    }

    @FXML
    private void tfTongTienClick(ActionEvent event) {
    }
    
    private boolean checkNull(){
        if(tfVeCong==null | tfLinhTinh==null | tfNuocUong==null | tfPhiHDV==null | tfTienAnTrua==null |tfTienAnXe==null | tfTienXe==null ){
            return false;
        }
        else return true;
    }
    private boolean  checkNumber(String s){
        try {
            Float.parseFloat(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
    private boolean checkSo(){
        if(checkNumber(tfLinhTinh.getText())==false | checkNumber(tfNuocUong.getText())==false | checkNumber(tfPhiHDV.getText())==false |
                checkNumber(tfTienAnTrua.getText())==false | checkNumber(tfTienAnXe.getText())==false | checkNumber(tfTienXe.getText())==false |
                checkNumber(tfVeCong.getText())==false)
            return false;
        else return true;
    }
}
