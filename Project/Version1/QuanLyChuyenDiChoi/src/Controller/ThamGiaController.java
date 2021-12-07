/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.util.ResourceBundle;
<<<<<<< HEAD
import javafx.fxml.Initializable;
=======
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
>>>>>>> parent of 5b85053... tham gia

/**
 * FXML Controller class
 *
 * @author Tung
 */
public class ThamGiaController implements Initializable {
<<<<<<< HEAD

=======
    
    @FXML
    private JFXComboBox<LopHoc> cmbLop;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private TableView<HocSinhThamGia> tbHocSinh;
    @FXML
    private TableColumn<HocSinhThamGia, Integer> colMaHS;
    @FXML
    private TableColumn<HocSinhThamGia, Boolean> colDaThuPhi;
    @FXML
    private TableView<GiaoVienThamGia> tbGiaoVien;
    @FXML
    private TableColumn<GiaoVienThamGia, Integer> colMaGV;
    @FXML
    private TableColumn<GiaoVienThamGia, String> colTaGV;
    @FXML
    private TableColumn<GiaoVienThamGia, Boolean> colGVThamGia;
    ThamGiaDAO thamGiaDAO;
    StudentDAO HocSinhDAO;
    ObservableList<HocSinhThamGia> listHocSinh = FXCollections.observableArrayList();    
    ObservableList<LopHoc> listLopHoc = FXCollections.observableArrayList();    
    @FXML
    private TableColumn<HocSinhThamGia, String> colTenHS;
    @FXML
    private TableColumn<HocSinhThamGia, Boolean> colHSThamGia;
    String MaChuyenDi;
>>>>>>> parent of 5b85053... tham gia
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
<<<<<<< HEAD
    
=======
    void loadCombobox() throws SQLException
    {
        HocSinhDAO = new StudentDAO();
        listLopHoc = HocSinhDAO.getListClass();
        cmbLop.setItems(listLopHoc);
    }
    
    public void initdata(String MaChuyenDi) {
        this.MaChuyenDi = MaChuyenDi;  
        try {
            loadCombobox();
        } catch (SQLException ex) {
            Logger.getLogger(ThamGiaController.class.getName()).log(Level.SEVERE, null, ex);
        }
//       cmbLop.valueProperty().addListener(new ChangeListener<LopHoc>(){
//           @Override
//            public void changed(ObservableValue observable, LopHoc oldValue, LopHoc newValue) {
//                 String MaLop = cmbLop.getValue().getStrMaLop();
//            listHocSinh = thamGiaDAO.getlistHocSinh(MaLop, MaChuyenDi);
//        SetCellValueFactory();
//        tbHocSinh.setItems(listHocSinh);
//            }
//       }
//               );
    }

    void SetCellValueFactory() {
        colMaHS.setCellValueFactory(new PropertyValueFactory<HocSinhThamGia, Integer>("MaHS"));
        colTenHS.setCellValueFactory(new PropertyValueFactory<HocSinhThamGia, String>("TenHS"));
        colHSThamGia.setCellValueFactory(new PropertyValueFactory<HocSinhThamGia, Boolean>("ThamGia"));
        colDaThuPhi.setCellValueFactory(new PropertyValueFactory<HocSinhThamGia, Boolean>("IsDongTien"));
    }

    @FXML
    private void ComboboxSelectValue(ActionEvent event) {
        String MaLop = cmbLop.getValue().getStrMaLop();
        thamGiaDAO = new ThamGiaDAO();
                
         listHocSinh = thamGiaDAO.getlistHocSinh(MaLop, MaChuyenDi);
         
        SetCellValueFactory();
        tbHocSinh.setItems(listHocSinh);
        
    }

    @FXML
    private void btnLuuClick(ActionEvent event) {
    }
>>>>>>> parent of 5b85053... tham gia
}
