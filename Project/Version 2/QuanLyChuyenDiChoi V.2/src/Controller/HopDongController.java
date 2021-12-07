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
public class HopDongController implements Initializable {

    @FXML
    private JFXTextField tfTimMaHopDong;
    @FXML
    private TableView<?> tbHopDong;
    @FXML
    private TableColumn<?, ?> colMaHopDong;
    @FXML
    private TableColumn<?, ?> colMaChuyenDi;
    @FXML
    private TableColumn<?, ?> colTenCongTy;
    @FXML
    private TableColumn<?, ?> colNgayKy;
    @FXML
    private TableColumn<?, ?> colTriGia;
    @FXML
    private JFXTextField tfMaHopDong1;
    @FXML
    private JFXTextField tfMaChuyenDi;
    @FXML
    private JFXComboBox<?> cmbTenCongTy;
    @FXML
    private JFXButton btnTimChuyenDi;
    @FXML
    private JFXDatePicker dpNgayKy;
    @FXML
    private JFXTextField tfTriGia;
    @FXML
    private JFXButton btnMoi1;
    @FXML
    private JFXButton btnLuu1;
    @FXML
    private JFXButton btnXoa1;
    @FXML
    private TableView<?> tbThanhToan;
    @FXML
    private TableColumn<?, ?> colLanThanhToan;
    @FXML
    private TableColumn<?, ?> colNgayThanhToan;
    @FXML
    private JFXTextField tfMaHopDong2;
    @FXML
    private JFXTextField tfLanThanhToan;
    @FXML
    private JFXDatePicker dpNgayThanhToan;
    @FXML
    private JFXButton btnMoi2;
    @FXML
    private JFXButton btnLuu2;
    @FXML
    private JFXButton btnXoa2;
    @FXML
    private JFXButton btnTimHopDong;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnTimHopDong(ActionEvent event) {
    }

    @FXML
    private void btnTimChuyenDiClick(ActionEvent event) {
    }

    @FXML
    private void btnMoi1Click(ActionEvent event) {
    }

    @FXML
    private void btnLuu1Click(ActionEvent event) {
    }

    @FXML
    private void btnXoa1Click(ActionEvent event) {
    }

    @FXML
    private void btnMoi2Click(ActionEvent event) {
    }

    @FXML
    private void btnLuu2Click(ActionEvent event) {
    }

    @FXML
    private void btnXoa2Click(ActionEvent event) {
    }
    
}
