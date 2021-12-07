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
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import javafx.util.Callback;
import model.ChuyenDi;
import model.ChuyenDiDAO;
import model.CongTyDAO;
import model.CongTyDuLich;
import model.DiaDiem;
import model.DiaDiemDAO;
import model.HocSinh;
import model.LopHoc;
import model.NamHoc;
import model.StudentDAO;

/**
 *
 * @author Scoobydo
 */
public class ChuyenDiController implements Initializable {

    @FXML
    private JFXTextField tfMaChuyenDi;
    @FXML
    private JFXDatePicker dpNgayKhoiHanh;
    @FXML
    private JFXComboBox<CongTyDuLich> cmbTenCongTy;
    @FXML
    private JFXButton btnMoi;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXButton btnXoa;
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
    @FXML
    private JFXButton btnThem;
    @FXML
    private JFXButton btnThamGiia;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            LoadTable();
            loadComBoBox();
            btnMoi.setVisible(false);
            btnLuu.setVisible(false);
            btnXoa.setVisible(false);
            btnChiPhi.setVisible(false);
            btnChiTiet.setVisible(false);
            btnThamGiia.setVisible(false);
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

    public boolean addChuyenDi() throws SQLException {
        chuyenDiDAO = new ChuyenDiDAO();
        
        String maCongTy = cmbTenCongTy.getValue().getMaCongTy();
        String maDiaDiem = cmbTenDiaDiem.getValue().getMaDiaDiem();
        Date ngayKhoiHanh = Date.valueOf(dpNgayKhoiHanh.getValue());
        return chuyenDiDAO.ThemChuyenDi( maCongTy, maDiaDiem, ngayKhoiHanh);
    }

    public boolean UpdateChuyenDi() throws SQLException {
        chuyenDiDAO = new ChuyenDiDAO();
        String maChuyenDi = tfMaChuyenDi.getText();
        String maCongTy = cmbTenCongTy.getValue().getMaCongTy();
        String maDiaDiem = cmbTenDiaDiem.getValue().getMaDiaDiem();
        Date ngayKhoiHanh = Date.valueOf(dpNgayKhoiHanh.getValue());

        return chuyenDiDAO.SuaChuyenDi(maChuyenDi, maCongTy, maDiaDiem, ngayKhoiHanh);
    }

    public boolean deleteChuyenDi() throws SQLException {
        String maChuyenDi = tfMaChuyenDi.getText();
        chuyenDiDAO = new ChuyenDiDAO();
        return chuyenDiDAO.xoaChuyenDi(maChuyenDi);
    }

    public void showAlertDelete() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa?");
        alert.setContentText("");

        ButtonType buttontypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttontypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
        ButtonType buttontypeCancel = new ButtonType("CANCLE", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttontypeYes, buttontypeNo, buttontypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("Thông báo");
        alert1.setHeaderText("");
        if (result.get() == buttontypeYes) {
            if (deleteChuyenDi() == true) {
                alert1.setContentText("Xóa thành công");
                alert1.show();
            } else {
                alert1.setContentText("Xóa that bai");
                alert1.show();
            }

            LoadTable();
        } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
            alert.close();
        } else {
            alert.close();
        }
    }

    public void TimChuyenDi() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        chuyenDiDAO = new ChuyenDiDAO();
        Date ngayKhoiHanh = Date.valueOf(dpTimNgayKhoiHanh.getValue());
        listChuyenDi = chuyenDiDAO.SearchChuyenDi(ngayKhoiHanh);
        if (listChuyenDi.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Thông báo");
            alert1.setHeaderText("Không tìm thấy");
            alert1.show();
        }
        else{
        setCellValueFactory();
        tbChuyenDi.setItems(listChuyenDi);
        }

    }

    @FXML
    private void btnMoiClick(ActionEvent event) throws SQLException {
        tfMaChuyenDi.clear();
        btnMoi.setVisible(false);
        btnThem.setVisible(true);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        btnChiTiet.setVisible(false);
        btnChiPhi.setVisible(false);
        btnThamGiia.setVisible(false);
        lbKetQua.setText("");
        LoadTable();

    }

    @FXML
    private void btlLuuClick(ActionEvent event) throws SQLException {
        if (tfMaChuyenDi.getText().isEmpty() == false) {
            if (UpdateChuyenDi() == true) {
                lbKetQua.setText("Cập nhật thành công!");
            } else {
                lbKetQua.setText("Cập nhật thất bại!");
            }
        }
        LoadTable();
    }

    @FXML
    private void btnXoaClick(ActionEvent event) throws SQLException {
        if (!tfMaChuyenDi.getText().isEmpty()) {
            showAlertDelete();
            tfMaChuyenDi.clear();
        } else {
            lbKetQua.setText("Chọn chuyến đi cần xóa!");
        }

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
            btnThem.setVisible(false);
            btnMoi.setVisible(true);
            btnLuu.setVisible(true);
            btnXoa.setVisible(true);
            btnChiPhi.setVisible(true);
            btnChiTiet.setVisible(true);
            btnThamGiia.setVisible(true);
            ChuyenDi chuyenDi = tbChuyenDi.getSelectionModel().getSelectedItem();
            tfMaChuyenDi.setText(String.valueOf(chuyenDi.getMaChuyenDi()));
            cmbTenCongTy.setValue(CongTyDuLich.CongTyDuLich(chuyenDi.getTenCongTy(), listCongTy));
            cmbTenDiaDiem.setValue(DiaDiem.DiaDiem(chuyenDi.getTenDiaDiem(), listDiaDiem));
            dpNgayKhoiHanh.setValue(LocalDate.parse(String.valueOf(chuyenDi.getNgayKhoiHanh())));
        }
    }

    @FXML
    private void btnChiTietClick(ActionEvent e) throws IOException {
        ChuyenDi cd = tbChuyenDi.getSelectionModel().getSelectedItem();
        if (cd == null) {
            lbKetQua.setText("Vui lòng chọn chuyến đi!");
        } else {
            ShowChiTietChuyenDi(cd.getMaChuyenDi());
        }
    }

    public void ShowChiTietChuyenDi(String MaChuyenDi) {

        AnchorPane paneChiTietChuyenDi = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenDi, "/quanlychuyendichoi/ChiTietChuyenDi.fxml");
        fXMLLoader.<ChiTietChuyenDiController>getController().initData(MaChuyenDi);
        paneChiTietChuyenDi.getChildren().add(paneChiTietChuyenDi);
        GeneralFuntion.FitChildContent(paneChiTietChuyenDi);
    }

    @FXML
    private void btnChiPhiClick(ActionEvent event) throws SQLException {
        ChuyenDi cd = tbChuyenDi.getSelectionModel().getSelectedItem();
        if (cd == null) {
            lbKetQua.setText("Vui lòng chọn chuyến đi!");
        } else {
            ShowChiPhiChuyenDi(cd.getMaChuyenDi());
        }

    }

    public void ShowChiPhiChuyenDi(String MaChuyenDi) throws SQLException {

        AnchorPane paneChiPhiChuyenDi = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiPhiChuyenDi, "/quanlychuyendichoi/ChiPhi.fxml");
        fXMLLoader.<ChiPhiController>getController().initdata(MaChuyenDi);
        paneChiPhiChuyenDi.getChildren().add(paneChiPhiChuyenDi);
        GeneralFuntion.FitChildContent(paneChiPhiChuyenDi);
    }

    @FXML
    private void btnThemClick(ActionEvent event) throws SQLException {
        if(cmbTenCongTy.getValue()==null||cmbTenDiaDiem.getValue()==null||dpNgayKhoiHanh.getValue()==null)
            lbKetQua.setText("Vui lòng nhập đầy đủ thông tin!");
        else{
            if (addChuyenDi() == true) {
                lbKetQua.setText("Thêm thành công!");
            } else {
                lbKetQua.setText("Thêm thất bại!");
            }
            }
        LoadTable();
    }

    @FXML
    private void btnThamGiaClick(ActionEvent event) {
        ChuyenDi cd = tbChuyenDi.getSelectionModel().getSelectedItem();
        if (cd == null) {
            lbKetQua.setText("Vui lòng chọn chuyến đi!");
        } else {
            ShowThamGiaChuyenDi(cd.getMaChuyenDi());
        }

    }

    void ShowThamGiaChuyenDi(String MaChuyenDi) {

        AnchorPane paneThamGia = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneThamGia, "/quanlychuyendichoi/ThamGia.fxml");
        fXMLLoader.<ThamGiaController>getController().initdata(MaChuyenDi);
        paneThamGia.getChildren().add(paneThamGia);
        GeneralFuntion.FitChildContent(paneThamGia);
    }
}
