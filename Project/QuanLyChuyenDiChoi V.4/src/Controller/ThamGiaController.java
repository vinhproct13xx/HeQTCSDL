/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.*;

/**
 * FXML Controller class
 *
 * @author Tung
 */
public class ThamGiaController implements Initializable {

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
    ObservableList<GiaoVienThamGia> listGiaoVien = FXCollections.observableArrayList();
    ObservableList<LopHoc> listLopHoc = FXCollections.observableArrayList();
    @FXML
    private TableColumn<HocSinhThamGia, String> colTenHS;
    @FXML
    private TableColumn<HocSinhThamGia, Boolean> colHSThamGia;
    String MaChuyenDi;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    void loadCombobox() throws SQLException {
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
//        colHSThamGia.setCellValueFactory(new PropertyValueFactory<HocSinhThamGia, Boolean>("ThamGia"));
//        colDaThuPhi.setCellValueFactory(new PropertyValueFactory<HocSinhThamGia, Boolean>("IsDongTien"));
        colMaGV.setCellValueFactory(new PropertyValueFactory<GiaoVienThamGia, Integer>("MaGiaoVien"));
        colTaGV.setCellValueFactory(new PropertyValueFactory<GiaoVienThamGia, String>("TenGV"));
        colHSThamGia.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HocSinhThamGia, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<HocSinhThamGia, Boolean> param) {
                HocSinhThamGia hs = param.getValue();
                SimpleBooleanProperty booleanHocSinhThamGia = new SimpleBooleanProperty(hs.isThamGia());
                booleanHocSinhThamGia.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        hs.setThamGia(newValue);
                        ChangeHocSinh(hs, listHocSinh);
                    }
                });
                return booleanHocSinhThamGia;
            }
        });
        colDaThuPhi.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HocSinhThamGia, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<HocSinhThamGia, Boolean> param) {
                HocSinhThamGia hs = param.getValue();
                SimpleBooleanProperty booleanHocSinhDaThuPhi = new SimpleBooleanProperty(hs.isDaThuPhi());
                booleanHocSinhDaThuPhi.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        hs.setDaThuPhi(newValue);
                        ChangeHocSinh(hs, listHocSinh);
                    }
                });
                return booleanHocSinhDaThuPhi;
            }
        });
          colGVThamGia.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GiaoVienThamGia, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<GiaoVienThamGia, Boolean> param) {
                GiaoVienThamGia gv = param.getValue();
                SimpleBooleanProperty booleanGiaoVienThamGia = new SimpleBooleanProperty(gv.isThamGia());
                booleanGiaoVienThamGia.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        gv.setThamGia(newValue);
                        changeGiaoVien(gv, listGiaoVien);
                    }
                });
                return booleanGiaoVienThamGia;
            }
        });
          setCellFactory();
    }

    void setCellFactory() {
        colHSThamGia.setCellFactory(new Callback<TableColumn<HocSinhThamGia, Boolean>, TableCell<HocSinhThamGia, Boolean>>() {
            @Override
            public TableCell<HocSinhThamGia, Boolean> call(TableColumn<HocSinhThamGia, Boolean> param) {
                CheckBoxTableCell<HocSinhThamGia, Boolean> cell = new CheckBoxTableCell<HocSinhThamGia, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;

            }
        });
        colGVThamGia.setCellFactory(new Callback<TableColumn<GiaoVienThamGia, Boolean>, TableCell<GiaoVienThamGia, Boolean>>() {
            @Override
            public TableCell<GiaoVienThamGia, Boolean> call(TableColumn<GiaoVienThamGia, Boolean> param) {
                CheckBoxTableCell<GiaoVienThamGia, Boolean> cell = new CheckBoxTableCell<GiaoVienThamGia, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;

            }
        });
        colDaThuPhi.setCellFactory(new Callback<TableColumn<HocSinhThamGia, Boolean>, TableCell<HocSinhThamGia, Boolean>>() {
            @Override
            public TableCell<HocSinhThamGia, Boolean> call(TableColumn<HocSinhThamGia, Boolean> param) {
                CheckBoxTableCell<HocSinhThamGia, Boolean> cell = new CheckBoxTableCell<HocSinhThamGia, Boolean>();
                cell.setAlignment(Pos.CENTER);
                return cell;

            }
        });
    }

    @FXML
    private void ComboboxSelectValue(ActionEvent event) {
        String MaLop = cmbLop.getValue().getStrMaLop();
        thamGiaDAO = new ThamGiaDAO();
        listHocSinh = thamGiaDAO.getlistHocSinh(MaLop, MaChuyenDi);
        listGiaoVien = thamGiaDAO.getlistGiaoVien(MaLop, MaChuyenDi);
        SetCellValueFactory();
        tbHocSinh.setItems(listHocSinh);
        tbGiaoVien.setItems(listGiaoVien);
        tbHocSinh.setEditable(true);
        tbGiaoVien.setEditable(true);

    }

    void ChangeHocSinh(HocSinhThamGia hs, ObservableList<HocSinhThamGia> list) {
        for (HocSinhThamGia hstg : list) {
            if (hstg.getMaHS() == hs.getMaHS()) {
                hstg = hs;
            }
        }
    }

    void changeGiaoVien(GiaoVienThamGia gv, ObservableList<GiaoVienThamGia> list) {
        for (GiaoVienThamGia gvtg : list) {
            if (gvtg.getMaGV() == gv.getMaGV()) {
                gvtg = gv;
            }
        }
    }

    @FXML
    private void btnLuuClick(ActionEvent event) {
        String MaLop = cmbLop.getSelectionModel().getSelectedItem().getStrMaLop();
        try {
             for(HocSinhThamGia hs : listHocSinh)
            {
                thamGiaDAO.UpdateHocSinh(hs);
            }
            for(GiaoVienThamGia gv : listGiaoVien)
            {
                thamGiaDAO.UpdateGiaoVien(gv);
            }
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Successfuly!");
            alert.setContentText("Đã cập nhật danh sách tham gia!");
            alert.show();
             thamGiaDAO = new ThamGiaDAO();
             listHocSinh.clear();
             listGiaoVien.clear();
        listHocSinh = thamGiaDAO.getlistHocSinh(MaLop, MaChuyenDi);
        listGiaoVien = thamGiaDAO.getlistGiaoVien(MaLop, MaChuyenDi);
        SetCellValueFactory();
        tbHocSinh.setItems(listHocSinh);
        tbGiaoVien.setItems(listGiaoVien);
        tbHocSinh.setEditable(true);
        tbGiaoVien.setEditable(true);
        } catch (Exception e) {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Error!");
            alert.setContentText("Chưa cập nhật được danh sách tham gia!");
            alert.show();
        }
    
       
    }
}
