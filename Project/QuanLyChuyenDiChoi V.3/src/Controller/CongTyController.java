/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import model.ChuyenDiDAO;
import model.CongTyDAO;
import model.CongTyDuLich;
import model.DBConnect;
import model.GiaoVienDAO;
import model.HopDong;
import model.HopDongDAO;
import model.ThanhToan;

/**
 * FXML Controller class
 *
 * @author Scoobydo
 */
public class CongTyController implements Initializable {

    @FXML
    private JFXTextField tfDiaChi;
    @FXML
    private JFXTextField tfSDT;
    @FXML
    private JFXButton btnMoi;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private Label lbThongBao;
    @FXML
    private JFXTextField tfTimKiem;
    @FXML
    private JFXButton btnTim;
    @FXML
    private TableView<CongTyDuLich> tbCongTy;
    @FXML
    private TableColumn<CongTyDuLich, String> colMaCongTy;
    @FXML
    private TableColumn<CongTyDuLich, String> colTenCongTy;
    @FXML
    private TableColumn<CongTyDuLich, String> colDiaChi;
    @FXML
    private TableColumn<CongTyDuLich, String> colSDT;
    
    private ObservableList<CongTyDuLich> listCongTyDuLich = FXCollections.observableArrayList();
    private CongTyDAO congTyDAO;
    @FXML
    private JFXTextField tfMaCongTy;
    @FXML
    private JFXTextField tfTenCongTy;
    @FXML
    private JFXButton btnThem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            LoadTable();
            btnMoi.setVisible(false);
            btnLuu.setVisible(false);
            btnXoa.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(CongTyController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void LoadTable() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        congTyDAO = new CongTyDAO();
        listCongTyDuLich = congTyDAO.getlistCongTy();
        setCellValueFactory();
        tbCongTy.setItems(listCongTyDuLich);
    }
    public boolean addCongTy() throws SQLException {
        congTyDAO = new CongTyDAO();
        int id;
        id = congTyDAO.MaxMaCongTy()+ 1;
        String s = String.valueOf(id);
        String tenCongTy = tfTenCongTy.getText();
        String diaChi = tfDiaChi.getText();
        String SDT = tfSDT.getText();
 
        return congTyDAO.ThemCongTy(s, tenCongTy, diaChi, SDT);
    }
    public void setCellValueFactory() {
        colMaCongTy.setCellValueFactory(new PropertyValueFactory<CongTyDuLich, String>("maCongTy"));
        colTenCongTy.setCellValueFactory(new PropertyValueFactory<CongTyDuLich, String>("tenCongTy"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<CongTyDuLich, String>("diaChi"));
        colSDT.setCellValueFactory(new PropertyValueFactory<CongTyDuLich, String>("SDT"));

    }
    public boolean deleteCongTy() throws SQLException {
        String maCongTy = tfMaCongTy.getText();
        congTyDAO = new CongTyDAO();
        return congTyDAO.xoaCongTy(maCongTy);
    }
     public void showAlertDeleteCongTy() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("B???n c?? ch???c ch???n mu???n x??a?");
        alert.setContentText("");

        ButtonType buttontypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttontypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
        ButtonType buttontypeCancel = new ButtonType("CANCLE", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttontypeYes, buttontypeNo, buttontypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Th??ng b??o");
        alert1.setHeaderText("");
        if (result.get() == buttontypeYes) {
            if (deleteCongTy()== true) {
                alert1.setContentText("X??a th??nh c??ng");
                alert1.show();
            } else {
                alert1.setContentText("X??a that bai");
                alert1.show();
            }

            LoadTable();
        } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
            alert.close();
        } else {
            alert.close();
        }
    }
      public boolean updateCongTy() throws SQLException {
        congTyDAO = new CongTyDAO();
        String maCongTy = tfMaCongTy.getText();
        String tenCongTy = tfTenCongTy.getText();
        String diaChi = tfDiaChi.getText();
        String SDT = tfSDT.getText();

        return congTyDAO.suaCongTy(maCongTy, tenCongTy, diaChi, SDT);
    }
     public void searchCongTy() throws SQLException{
        //load combobox lop hoc, nam hoc, 
        congTyDAO = new CongTyDAO();
        String data=tfTimKiem.getText();
        listCongTyDuLich = congTyDAO.TimCongTy(data);
        setCellValueFactory();
        tbCongTy.setItems(listCongTyDuLich);   
      
    }
    @FXML
    private void btnMoiClick(ActionEvent event) throws SQLException {
        tfMaCongTy.clear();
        tfTenCongTy.clear();
        tfDiaChi.clear();
        tfSDT.clear();
        btnMoi.setVisible(false);
        btnThem.setVisible(true);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        lbThongBao.setText("");
        LoadTable();
    }

    @FXML
    private void btnLuuClick(ActionEvent event) throws SQLException {
        if (updateCongTy()== true) {
                lbThongBao.setText("C???p nh???t th??nh c??ng!");
            } else {
                lbThongBao.setText("C???p nh???t th???t b???i!");
            }
        LoadTable();
    }
//    public  boolean xoaCongTy(String maCongTy) throws SQLException{
//        
//        String sql ="EXEC dbo.USP_DeleteCongTy @maHD = N'"+ maHopDong+ "'";
//        System.out.println(sql);
//        int row = DBConnect.dbExcuteQuery(sql);
//        if(row>0)
//            return true;
//        else
//            return false;       
//    }
    @FXML
    private void btnXoaClick(ActionEvent event) throws SQLException {
        CongTyDuLich congTy=tbCongTy.getSelectionModel().getSelectedItem();
         if (congTy!=null) {
            showAlertDeleteCongTy();
            tfMaCongTy.clear();
            tfTenCongTy.clear();
            tfDiaChi.clear();
            tfSDT.clear();
            
        }
         else
            lbThongBao.setText("Ch???n c??ng ty c???n x??a!");

    }

    @FXML
    private void btnTimClick(ActionEvent event) throws SQLException {
         if(tfTimKiem.getText().isEmpty()){
            LoadTable();
        }
        else
            searchCongTy();
    }
    
    @FXML
    private void tbCongTyClick(MouseEvent e) {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1) {
            btnLuu.setVisible(true);
            btnXoa.setVisible(true);
            btnMoi.setVisible(true);
            btnThem.setVisible(false);
            CongTyDuLich congTy = tbCongTy.getSelectionModel().getSelectedItem();
            tfMaCongTy.setText(String.valueOf(congTy.getMaCongTy()));
            tfTenCongTy.setText(String.valueOf(congTy.getTenCongTy()));
            tfDiaChi.setText(String.valueOf(congTy.getDiaChi()));
            tfSDT.setText(String.valueOf(congTy.getSDT()));   
            
        }
    }

    @FXML
    private void btnThemClick(ActionEvent event) throws SQLException {
        
            if (addCongTy()== true) {
                lbThongBao.setText("Th??m th??nh c??ng!");
            } else {
                lbThongBao.setText("Th??m th???t b???i!");
            }
        
        LoadTable();
    }
    
}
