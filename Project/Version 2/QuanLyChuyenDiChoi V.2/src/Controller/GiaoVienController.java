/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author Scoobydo
 */
public class GiaoVienController implements Initializable {

    @FXML
    private JFXTextField tfMaGiaoVien;
    @FXML
    private JFXTextField tfTenGiaoVien;
    @FXML
    private JFXDatePicker dpNgaySinh;
    @FXML
    private JFXTextField tfDiaChi;
    @FXML
    private JFXTextField tfSDT;
    @FXML
    private JFXTextField tfCMND;
    @FXML
    private JFXComboBox<?> cmbLop;
    @FXML
    private JFXButton btnMoi;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private JFXTextField tfTimTenGiaoVien;
    @FXML
    private JFXButton btnTim;
    @FXML
    private TableView<?> tbGiaoVien;
    @FXML
    private TableColumn<?, ?> colMaGiaoVien;
    @FXML
    private TableColumn<?, ?> colTen;
    @FXML
    private TableColumn<?, ?> colNgaySinh;
    @FXML
    private TableColumn<?, ?> colDiaChi;
    @FXML
    private TableColumn<?, ?> colSDT;
    @FXML
    private TableColumn<?, ?> colCMND;
    @FXML
    private TableColumn<?, ?> colLop;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnMoiClick(ActionEvent event) {
    }


    @FXML
    private void btnXoaClick(ActionEvent event) {
    }

    @FXML
    private void btnTimClick(ActionEvent event) {
    }

    @FXML
    private void btnLuuClick(ActionEvent event) {
    }

    
    
}
