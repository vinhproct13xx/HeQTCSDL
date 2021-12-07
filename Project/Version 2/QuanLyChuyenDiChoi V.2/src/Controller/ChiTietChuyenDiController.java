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
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    @FXML
    private AnchorPane paneChiTietChuyenDiOld;
        
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
public void initData(String MaChuyenDi)
    {
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
    public void setTextFieldMaChuyenDi(String maChuyenDi){
        tfMaChuyenDi.setText(maChuyenDi);
    }
    
    @FXML
    private void btnMoiClick(ActionEvent event) {
    }


    @FXML
    private void btnXoaClick(ActionEvent event) {
    }

    @FXML
    private void TableHocSInhClick(MouseEvent event) {
    }

    @FXML
    private void btnLuuClick(ActionEvent event) {
    }

    
}
