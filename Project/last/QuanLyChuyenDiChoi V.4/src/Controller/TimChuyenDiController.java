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
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.ChuyenDi;
import model.ChuyenDiDAO;
import model.CongTyDAO;
import model.CongTyDuLich;
import model.DiaDiem;
import model.DiaDiemDAO;

/**
 *
 * @author Scoobydo
 */
public class TimChuyenDiController {

   @FXML
    private JFXTextField tfMaChuyenDi;
    @FXML
    private JFXDatePicker dpNgayKhoiHanh;
    @FXML
    private JFXComboBox<CongTyDuLich> cmbTenCongTy;

    @FXML
    private JFXComboBox<DiaDiem> cmbTenDiaDiem;
    @FXML
    private JFXButton btnTim;
    @FXML
    private JFXDatePicker dpTimNgayKhoiHanh;
    @FXML
    private TableView<ChuyenDi> tbChuyenDi;
    @FXML
    private TableColumn<ChuyenDi, String> colMaChuyenDi;
    @FXML
    private TableColumn<ChuyenDi, CongTyDuLich> colTenCongTy;
    @FXML
    private TableColumn<ChuyenDi, DiaDiem> colTenDiaDiem;
    @FXML
    private TableColumn<ChuyenDi, Date> colNgayKhoiHanh;

    private ObservableList<ChuyenDi> listChuyenDi = FXCollections.observableArrayList();
    private ObservableList<CongTyDuLich> listCongTy = FXCollections.observableArrayList();
    private ObservableList<DiaDiem> listDiaDiem = FXCollections.observableArrayList();
    private ChuyenDiDAO chuyenDiDAO;
    private CongTyDAO congTyDAO;
    private DiaDiemDAO diaDiemDAO;
    @FXML
    private Label lbKetQua;
    @FXML
    private JFXButton btnChiTiet;
    @FXML
    private JFXButton btnChiPhi;
    @FXML
    private AnchorPane paneChuyenDi;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            LoadTable();
            loadComBoBox();
            //loadcombobox(listCongTy,listDiaDiem);
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenDiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTable() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        chuyenDiDAO = new ChuyenDiDAO();
        listChuyenDi = chuyenDiDAO.getlistChuyenDi();
        setCellValueFactory();
        tbChuyenDi.setItems(listChuyenDi);
        
        loadComBoBox();

    }

    public void loadComBoBox() throws SQLException {
        congTyDAO = new CongTyDAO();
        listCongTy = congTyDAO.getlistCongTy();
        cmbTenCongTy.setItems(listCongTy);

        diaDiemDAO = new DiaDiemDAO();
        listDiaDiem = diaDiemDAO.getlistDiaDiem();
        cmbTenDiaDiem.setItems(listDiaDiem);

    }

    public void setCellValueFactory() {
        colMaChuyenDi.setCellValueFactory(new PropertyValueFactory<ChuyenDi, String>("MaChuyenDi"));
        colTenCongTy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChuyenDi, CongTyDuLich>, ObservableValue<CongTyDuLich>>() {
            @Override
            public ObservableValue<CongTyDuLich> call(TableColumn.CellDataFeatures<ChuyenDi, CongTyDuLich> param) {
                ChuyenDi cd = param.getValue();
                String MaCty = cd.getTenCongTy();
                CongTyDuLich cty = CongTyDuLich.CongTyDuLich(MaCty, listCongTy);
                return new SimpleObjectProperty<CongTyDuLich>(cty);
            }
        });

        colTenDiaDiem.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ChuyenDi, DiaDiem>, ObservableValue<DiaDiem>>() {
            @Override
            public ObservableValue<DiaDiem> call(TableColumn.CellDataFeatures<ChuyenDi, DiaDiem> param) {
                ChuyenDi cd = param.getValue();
                String MaCty = cd.getTenDiaDiem();
                DiaDiem diaDiem = DiaDiem.DiaDiem(MaCty, listDiaDiem);
                return new SimpleObjectProperty<DiaDiem>(diaDiem);

            }
        });

        colNgayKhoiHanh.setCellValueFactory(new PropertyValueFactory<ChuyenDi, Date>("NgayKhoiHanh"));

    }
    public void TimChuyenDi() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        chuyenDiDAO = new ChuyenDiDAO();
        Date ngayKhoiHanh = Date.valueOf(dpTimNgayKhoiHanh.getValue());
        listChuyenDi = chuyenDiDAO.SearchChuyenDi(ngayKhoiHanh);
        setCellValueFactory();
        tbChuyenDi.setItems(listChuyenDi);

    }
    @FXML
    private void btnChonClick(ActionEvent event) throws SQLException {
        ChuyenDi chuyenDi = tbChuyenDi.getSelectionModel().getSelectedItem();
        if(chuyenDi!=null){
            String maChuyenDi=chuyenDi.getMaChuyenDi();
            String maCongTy=chuyenDi.getTenCongTy();
        AnchorPane paneChiTietChuyenDi = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenDi, "/quanlychuyendichoi/HopDong.fxml");
        fXMLLoader.<HopDongController>getController().getMaChuyenDi_CongTy(maChuyenDi, maCongTy);
        paneChiTietChuyenDi.getChildren().add(paneChiTietChuyenDi);
        GeneralFuntion.FitChildContent(paneChiTietChuyenDi);
        }
        else
            lbKetQua.setText("Vui lòng chọn chuyến đi!");
    }

    @FXML
    private void btnTroLaiClick(ActionEvent event) {
         AnchorPane paneChiTietChuyenDi = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenDi, "/quanlychuyendichoi/HopDong.fxml");
        paneChiTietChuyenDi.getChildren().add(paneChiTietChuyenDi);
        GeneralFuntion.FitChildContent(paneChiTietChuyenDi);
    }

    @FXML
    private void btnTimClick(ActionEvent event) throws SQLException {
        if (dpTimNgayKhoiHanh.getValue() == null) {
            LoadTable();
        } else {
            TimChuyenDi();
        }
    }

    @FXML
    private void tableChuyenDiClick(MouseEvent e) {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1) {
            ChuyenDi chuyenDi = tbChuyenDi.getSelectionModel().getSelectedItem();
            tfMaChuyenDi.setText(String.valueOf(chuyenDi.getMaChuyenDi()));
            cmbTenCongTy.setValue(CongTyDuLich.CongTyDuLich(chuyenDi.getTenCongTy(), listCongTy));
            cmbTenDiaDiem.setValue(DiaDiem.DiaDiem(chuyenDi.getTenDiaDiem(), listDiaDiem));
            dpNgayKhoiHanh.setValue(LocalDate.parse(String.valueOf(chuyenDi.getNgayKhoiHanh())));
        }
    }
    
}
