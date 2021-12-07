/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.*;

/**
 * FXML Controller class
 *
 * @author Scoobydo
 */
public class ChiTietChuyenDiController implements Initializable {

    @FXML
    private JFXTextField tfMaChuyenDi;
    @FXML
    private JFXButton btnMoi;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private JFXTimePicker tpThoiGian;
    @FXML
    private JFXTextArea taHoatDong;
    @FXML
    private JFXTextArea taGhiChu;
    @FXML
    private TableView<ChiTietChuyenDi> tbChiTietChuyenDi;
    @FXML
    private TableColumn<ChiTietChuyenDi, String> colMaChuyenDi;
    @FXML
    private TableColumn<ChiTietChuyenDi, Time> colThoiGian;
    @FXML
    private TableColumn<ChiTietChuyenDi, String> colHoatDong;
    @FXML
    private TableColumn<ChiTietChuyenDi, String> colGhiChu;
        private  ChiTietChuyenDiDAO chiTietChuyenDiDAO;
        ObservableList<ChiTietChuyenDi> listChiTietChuyenDi = FXCollections.observableArrayList();
        private ChiTietChuyenDiDAO ctChuyenDiDAO;

    @FXML
    private AnchorPane paneChiTietChuyenDiOld;
    @FXML
    private Label lbThongBao;
    @FXML
    private JFXButton btnThem;
    @FXML
    private MaterialDesignIconView IconReturn;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         btnMoi.setVisible(false);
        btnLuu.setVisible(false);
        btnXoa.setVisible(false);
        // TODO
    }
public void initData(String MaChuyenDi)
    {
       
        tfMaChuyenDi.setText(MaChuyenDi);
        chiTietChuyenDiDAO = new ChiTietChuyenDiDAO();
       listChiTietChuyenDi = chiTietChuyenDiDAO.getlistChiTietChuyenDi(MaChuyenDi);
       SetCellValueFactory();
       tbChiTietChuyenDi.setItems(listChiTietChuyenDi);
       
    }


private void SetCellValueFactory()
{
    colMaChuyenDi.setCellValueFactory(new PropertyValueFactory<ChiTietChuyenDi, String>("MaChuyenDi"));
    colHoatDong.setCellValueFactory(new PropertyValueFactory<ChiTietChuyenDi, String>("HoatDong"));
    colGhiChu.setCellValueFactory(new PropertyValueFactory<ChiTietChuyenDi, String>("GhiChu"));
    colThoiGian.setCellValueFactory(new PropertyValueFactory<ChiTietChuyenDi, Time>("ThoiGian"));
    
}
 
    
      public boolean addCTChuyenDi() throws SQLException{
        ctChuyenDiDAO = new ChiTietChuyenDiDAO();
        String maChuyenDi=tfMaChuyenDi.getText();
        Time thoiGian=Time.valueOf(tpThoiGian.getValue());
        String hoatDong=taHoatDong.getText();
        String ghiChu=taGhiChu.getText();      
        
        return ctChuyenDiDAO.ThemCTChuyenDi(maChuyenDi, thoiGian, hoatDong, ghiChu);
    }
    public boolean UpdateCTChuyenDi() throws SQLException{
        ctChuyenDiDAO = new ChiTietChuyenDiDAO();
        String maChuyenDi=tfMaChuyenDi.getText();
        Time thoiGian=Time.valueOf(tpThoiGian.getValue());
        String hoatDong=taHoatDong.getText();
        String ghiChu=taGhiChu.getText();
     
        return ctChuyenDiDAO.SuaCTChuyenDi(maChuyenDi, thoiGian, hoatDong, ghiChu);
    }
     public boolean deleteCTChuyenDi() throws SQLException{
        String maChuyenDi=tfMaChuyenDi.getText();
        Time thoiGian=Time.valueOf(tpThoiGian.getValue());
        ctChuyenDiDAO = new ChiTietChuyenDiDAO();
        return ctChuyenDiDAO.xoaChuyenDi(maChuyenDi, thoiGian);
    }
    @FXML
    private void btnMoiClick(ActionEvent event) throws SQLException {
        taHoatDong.clear();
        taGhiChu.clear();
        btnMoi.setVisible(false);
        btnThem.setVisible(true);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        lbThongBao.setText("");
    
    }
     public void showAlertDelete() throws SQLException{
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation");
          alert.setHeaderText("Bạn có chắc chắn muốn xóa?");
          alert.setContentText("");
          
          ButtonType buttontypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
          ButtonType buttontypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
          ButtonType buttontypeCancel = new ButtonType("CANCLE", ButtonBar.ButtonData.CANCEL_CLOSE);
            
          alert.getButtonTypes().setAll(buttontypeYes,buttontypeNo,buttontypeCancel);
          
          Optional<ButtonType> result = alert.showAndWait();
          
           Alert alert1=new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("Thông báo");
                alert1.setHeaderText("");
          if(result.get()==buttontypeYes){
            if(deleteCTChuyenDi()==true){
                alert1.setContentText("Xóa thành công");
                alert1.show();
            }
            else{
                alert1.setContentText("Xóa that bai");
                alert1.show();
            }
        
              initData(tfMaChuyenDi.getText());
          }
          else if (result.get().getButtonData()==ButtonBar.ButtonData.NO)
              alert.close();
          else
              alert.close();
    }

    @FXML
    private void btnXoaClick(ActionEvent event) throws SQLException {
                if(!tfMaChuyenDi.getText().isEmpty()){
            showAlertDelete();
        }
        else
            lbThongBao.setText("Chọn chuyến đi cần xóa!");
    }


    @FXML
    private void btnLuuClick(ActionEvent event) throws SQLException {
        if(UpdateCTChuyenDi()==true)
                lbThongBao.setText("Cập nhật thành công!");
            else
                lbThongBao.setText("Cập nhật thất bại!");
        initData(tfMaChuyenDi.getText());
    }


    @FXML
    private void tableCTChuyenDiClick(MouseEvent e) {
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
            btnLuu.setVisible(true);
            btnXoa.setVisible(true);
            btnMoi.setVisible(true);
            btnThem.setVisible(false);
            ChiTietChuyenDi ctChuyenDi = tbChiTietChuyenDi.getSelectionModel().getSelectedItem();
            tfMaChuyenDi.setText(String.valueOf(ctChuyenDi.getMaChuyenDi()));
            tpThoiGian.setValue(LocalTime.parse(String.valueOf(ctChuyenDi.getThoiGian())));
            taHoatDong.setText(ctChuyenDi.getHoatDong());
            taGhiChu.setText(ctChuyenDi.getGhiChu());
            
        }
    }

    @FXML
    private void btnThemClick(ActionEvent event) throws SQLException {
          if(addCTChuyenDi()==true)
                lbThongBao.setText("Thêm thành công!");
            else
                lbThongBao.setText("Thêm thất bại!");
        initData(tfMaChuyenDi.getText());
    }

    @FXML
    private void IconReturnClick(MouseEvent event) {
        
        
        
    }

    

    
}
