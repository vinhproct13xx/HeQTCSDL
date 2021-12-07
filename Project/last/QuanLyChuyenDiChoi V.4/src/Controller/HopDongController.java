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
import model.HopDong;
import model.HopDongDAO;
import model.ThanhToan;

/**
 * FXML Controller class
 *
 * @author Scoobydo
 */
public class HopDongController implements Initializable {

    @FXML
    private JFXTextField tfTimMaHopDong;
    @FXML
    private TableView<HopDong> tbHopDong;
    @FXML
    private TableColumn<HopDong, String> colMaHopDong1;
    @FXML
    private TableColumn<HopDong, String> colMaChuyenDi;
    @FXML
    private TableColumn<HopDong, CongTyDuLich> colTenCongTy;
    @FXML
    private TableColumn<HopDong, Date> colNgayKy;
    @FXML
    private TableColumn<HopDong, Float> colTriGia1;
    @FXML
    private TableColumn<HopDong, String> colTrangThai;
    @FXML
    private JFXTextField tfMaHopDong1;
    @FXML
    private JFXTextField tfMaChuyenDi;
    @FXML
    private JFXComboBox<CongTyDuLich> cmbTenCongTy;
    @FXML
    private JFXButton btnTimChuyenDi;
    @FXML
    private JFXDatePicker dpNgayKy;
    private JFXTextField tfTriGia;
    @FXML
    private JFXButton btnMoi1;
    @FXML
    private JFXButton btnLuu1;
    @FXML
    private JFXButton btnXoa1;
    @FXML
    private Label lbThongBaoHD;
    @FXML
    private Label lbThongBaoTT;
    @FXML
    private TableView<ThanhToan> tbThanhToan;

    @FXML
    private TableColumn<ThanhToan, String> colMaHopDong2;
    @FXML
    private TableColumn<ThanhToan, Integer> colLanThanhToan;
    @FXML
    private TableColumn<ThanhToan, Date> colNgayThanhToan;
    @FXML
    private TableColumn<ThanhToan, Float> colTriGia2;
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

    @FXML
    private JFXTextField tfTriGia1;

    private ObservableList<HopDong> listHopDong = FXCollections.observableArrayList();
    private ObservableList<ThanhToan> listThanhToan = FXCollections.observableArrayList();
    private ObservableList<CongTyDuLich> listCongTyDuLich = FXCollections.observableArrayList();
    private HopDongDAO hopDongDAO;
    private CongTyDAO congTyDAO;
    @FXML
    private JFXTextField tfTriGia2;
    @FXML
    private JFXButton btnThem1;
    @FXML
    private JFXButton btnThem2;
    @FXML
    private JFXTextField tfTong;
    @FXML
    private JFXTextField tfConNo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            LoadTableHopDong();
            loadComBoBox();
            btnMoi1.setVisible(false);
            btnMoi2.setVisible(false);
            btnThem2.setVisible(false);
            btnLuu1.setVisible(false);
            btnLuu2.setVisible(false);
            btnXoa1.setVisible(false);
            btnXoa2.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(HopDongController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void LoadTableHopDong() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        hopDongDAO = new HopDongDAO();
        listHopDong = hopDongDAO.getlistHopDong();
        setCellValueFactoryHopDong();
        tbHopDong.setItems(listHopDong);
    }

    public void LoadTableThanhToan(String maHopDong) throws SQLException {
        //load combobox lop hoc, nam hoc, 
        hopDongDAO = new HopDongDAO();
        listThanhToan = hopDongDAO.getlistThanhToan(maHopDong);
        setCellValueFactoryThanhToan();
        tbThanhToan.setItems(listThanhToan);
    }

    public void loadComBoBox() throws SQLException {
        congTyDAO = new CongTyDAO();
        listCongTyDuLich = congTyDAO.getlistCongTy();
        cmbTenCongTy.setItems(listCongTyDuLich);

    }

    public void getMaChuyenDi_CongTy(String maChuyenDi, String maCongTy) throws SQLException {
        loadComBoBox();
        tfMaChuyenDi.setText(maChuyenDi);
        cmbTenCongTy.setValue(CongTyDuLich.CongTyDuLich(maCongTy, listCongTyDuLich));
    }

    public void setCellValueFactoryHopDong() {
        colMaHopDong1.setCellValueFactory(new PropertyValueFactory<HopDong, String>("MaHopDong"));
        colMaChuyenDi.setCellValueFactory(new PropertyValueFactory<HopDong, String>("MaChuyenDi"));
        colTenCongTy.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HopDong, CongTyDuLich>, ObservableValue<CongTyDuLich>>() {
            @Override
            public ObservableValue<CongTyDuLich> call(TableColumn.CellDataFeatures<HopDong, CongTyDuLich> param) {
                HopDong hd = param.getValue();
                String MaCty = hd.getMaCongTy();
                CongTyDuLich cty = CongTyDuLich.CongTyDuLich(MaCty, listCongTyDuLich);
                return new SimpleObjectProperty<CongTyDuLich>(cty);
            }
        });

        colTriGia1.setCellValueFactory(new PropertyValueFactory<HopDong, Float>("TriGia"));
        colTrangThai.setCellValueFactory(new PropertyValueFactory<HopDong, String>("TrangThai"));
        colNgayKy.setCellValueFactory(new PropertyValueFactory<HopDong, Date>("NgayKy"));

    }

    public void setCellValueFactoryThanhToan() {
        colMaHopDong2.setCellValueFactory(new PropertyValueFactory<ThanhToan, String>("maHopDong"));
        colLanThanhToan.setCellValueFactory(new PropertyValueFactory<ThanhToan, Integer>("lanThanhToan"));
        colNgayThanhToan.setCellValueFactory(new PropertyValueFactory<ThanhToan, Date>("ngayThanhToan"));
        colTriGia2.setCellValueFactory(new PropertyValueFactory<ThanhToan, Float>("soTien"));
    }

    public boolean addHopDong() throws SQLException {
        hopDongDAO = new HopDongDAO();
        
        String maChuyenDi = tfMaChuyenDi.getText();
        String maCongTy = cmbTenCongTy.getValue().getMaCongTy();
        Date ngayKy = Date.valueOf(dpNgayKy.getValue());
        float triGia = Float.parseFloat(tfTriGia1.getText());

        return hopDongDAO.themHopDong( maChuyenDi, maCongTy, triGia, ngayKy);
    }

    public boolean addThanhToan() throws SQLException {
        hopDongDAO = new HopDongDAO();

        String maHopDong = tfMaHopDong1.getText();
        int soLanThanhToan = hopDongDAO.MaxLanThanhToan(maHopDong) + 1;
        Date ngayThanhToan = Date.valueOf(dpNgayThanhToan.getValue());
        float soTien = Float.parseFloat(tfTriGia2.getText());

        return hopDongDAO.themThanhToan(maHopDong, soLanThanhToan, ngayThanhToan, soTien);
    }

    public boolean UpdateHopDong() throws SQLException {
        hopDongDAO = new HopDongDAO();
        String maHopDong = tfMaHopDong1.getText();
        String maChuyenDi = tfMaChuyenDi.getText();
        String maCongTy = cmbTenCongTy.getValue().getMaCongTy();
        float triGia = Float.parseFloat(tfTriGia1.getText());
        Date ngayKy = Date.valueOf(dpNgayKy.getValue());

        return hopDongDAO.SuaHopDong(maHopDong, maChuyenDi, maCongTy, triGia, ngayKy);
    }

    public boolean deleteHopDong() throws SQLException {
        String maHopDong = tfMaHopDong1.getText();
        hopDongDAO = new HopDongDAO();
        return hopDongDAO.xoaHopDong(maHopDong);
    }

    public void showAlertDeleteHopDong() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Báº¡n cĂ³ cháº¯c cháº¯n muá»‘n xĂ³a?");
        alert.setContentText("");

        ButtonType buttontypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
        ButtonType buttontypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
        ButtonType buttontypeCancel = new ButtonType("CANCLE", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttontypeYes, buttontypeNo, buttontypeCancel);

        Optional<ButtonType> result = alert.showAndWait();

        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
        alert1.setTitle("ThĂ´ng bĂ¡o");
        alert1.setHeaderText("");
        if (result.get() == buttontypeYes) {
            if (deleteHopDong() == true) {
                alert1.setContentText("XĂ³a thĂ nh cĂ´ng");
                alert1.show();
            } else {
                alert1.setContentText("XĂ³a that bai");
                alert1.show();
            }

            LoadTableHopDong();
        } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
            alert.close();
        } else {
            alert.close();
        }
    }

    public boolean deleteThanhToan() throws SQLException {
        String maHopDong = tfMaHopDong2.getText();
        int lanThanhToan = Integer.parseInt(tfLanThanhToan.getText());
        hopDongDAO = new HopDongDAO();
        return hopDongDAO.xoaThanhToan(maHopDong, lanThanhToan);
    }

    public void showAlertDeleteThanhToan() throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Báº¡n cĂ³ cháº¯c cháº¯n muá»‘n xĂ³a?");
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
            if (deleteThanhToan() == true) {
                alert1.setContentText("Xóa thành công!");
                alert1.show();
            } else {
                alert1.setContentText("Xóa thất bại");
                alert1.show();
            }

        } else if (result.get().getButtonData() == ButtonBar.ButtonData.NO) {
            alert.close();
        } else {
            alert.close();
        }
    }

    public void timHopDong() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        hopDongDAO = new HopDongDAO();
        String maHopDong = tfTimMaHopDong.getText();
        listHopDong = hopDongDAO.SearchHopDong(maHopDong);
        if (listHopDong.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Thông báo");
            alert1.setHeaderText("Không tìm thấy");
            alert1.show();
        }
        else{
        setCellValueFactoryHopDong();
        tbHopDong.setItems(listHopDong);
        }

    }

    public boolean UpdateThanhToan() throws SQLException {
        hopDongDAO = new HopDongDAO();
        String maHopDong = tfMaHopDong2.getText();
        int lanThanhToan = Integer.parseInt(tfLanThanhToan.getText());
        Date ngayThanhToan = Date.valueOf(dpNgayThanhToan.getValue());
        float triGia = Float.parseFloat(tfTriGia2.getText());

        return hopDongDAO.suaThanhToan(maHopDong, lanThanhToan, ngayThanhToan, triGia);
    }

    public void tongThanhToan() throws SQLException {
        hopDongDAO = new HopDongDAO();
        String maHopDong = tfMaHopDong2.getText();
        String tongThanhToan = String.valueOf(hopDongDAO.TongTienThanhToan(maHopDong));
        tfTong.setText(tongThanhToan);
    }

    public void conNo() throws SQLException {
        hopDongDAO = new HopDongDAO();
        float triGiaHD = hopDongDAO.getTriGiaHD(tfMaHopDong1.getText());
        float tienDaDong = Float.parseFloat(tfTong.getText());
        float conNo = tienDaDong - triGiaHD;
        System.out.println("Tri gia HD:" + triGiaHD);
        System.out.println("TienDaDong:" + tienDaDong);
        System.out.println("Con no:" + conNo);
        if (conNo > 0) {
            tfConNo.setText("+" + conNo);
        } else {
            tfConNo.setText(String.valueOf(conNo));
        }
    }

    @FXML
    private void btnTimHopDong(ActionEvent event) throws SQLException {
        if (tfTimMaHopDong.getText().isEmpty()) {
            LoadTableHopDong();
        } else {
            timHopDong();
        }
    }

    @FXML
    private void btnTimChuyenDiClick(ActionEvent e) throws IOException, SQLException {
        AnchorPane paneChiTietChuyenDi = new AnchorPane();
        FXMLLoader fXMLLoader = MainController.getMainController().createPage(paneChiTietChuyenDi, "/quanlychuyendichoi/ChuyenDi_TimKiem.fxml");
        fXMLLoader.<TimChuyenDiController>getController().LoadTable();
        paneChiTietChuyenDi.getChildren().add(paneChiTietChuyenDi);
        GeneralFuntion.FitChildContent(paneChiTietChuyenDi);
    }

    @FXML
    private void btnMoi1Click(ActionEvent event) throws SQLException {
        tfMaHopDong1.clear();
        tfMaChuyenDi.clear();
        tfTriGia1.clear();
        btnMoi1.setVisible(false);
        btnThem1.setVisible(true);
        btnXoa1.setVisible(false);
        btnLuu1.setVisible(false);
        btnMoi2.setVisible(false);
        btnThem2.setVisible(false);
        btnXoa2.setVisible(false);
        btnLuu2.setVisible(false);

        lbThongBaoTT.setText("");
        lbThongBaoHD.setText("");
        LoadTableHopDong();

    }

    @FXML
    private void btnLuu1Click(ActionEvent event) throws SQLException {

        if (tfMaHopDong1.getText().isEmpty() || dpNgayKy.getValue() == null || tfTriGia1.getText().isEmpty()) {
            lbThongBaoHD.setText("Vui lòng  nhập đầy dủ thông tin!");
        } else {
            if (UpdateHopDong() == true) {
                lbThongBaoHD.setText("Cập nhật thành công!");
            } else {
                lbThongBaoHD.setText("Cập nhật thất bại!");
            }
        }
        conNo();
        LoadTableHopDong();
    }

    @FXML
    private void btnXoa1Click(ActionEvent event) throws SQLException {
        HopDong hopDong = tbHopDong.getSelectionModel().getSelectedItem();
        if (hopDong != null) {
            showAlertDeleteHopDong();
            tfMaChuyenDi.clear();
            tfMaHopDong1.clear();
            tfTriGia1.clear();
            LoadTableThanhToan(tfMaHopDong2.getText());
        } else {
            lbThongBaoHD.setText("Chọn hợp đồng cần xóa!");
        }

    }

    @FXML
    private void btnMoi2Click(ActionEvent event) {
        btnMoi2.setVisible(false);
        btnThem2.setVisible(true);
        btnXoa2.setVisible(false);
        btnLuu2.setVisible(false);

        tfTriGia2.clear();

    }

    @FXML
    private void btnLuu2Click(ActionEvent event) throws SQLException {
        if (dpNgayThanhToan.getValue() == null || tfTriGia2.getText().isEmpty()) {
            lbThongBaoTT.setText("Vui lòng  nhập đầy dủ thông tin!");
        } else {
            if (UpdateThanhToan() == true) {
                lbThongBaoTT.setText("Cập nhật thành công!");
            } else {
                lbThongBaoTT.setText("Cập nhật thất bại!");
            }
        }
        LoadTableThanhToan(tfMaHopDong1.getText());
        LoadTableHopDong();
        tongThanhToan();
        conNo();

    }

    @FXML
    private void btnXoa2Click(ActionEvent event) throws SQLException {
        ThanhToan thanhToan = tbThanhToan.getSelectionModel().getSelectedItem();
        if (thanhToan != null) {
            showAlertDeleteThanhToan();

        } else {
            lbThongBaoTT.setText("Chọn chuyến đi cần xóa");
        }

        LoadTableHopDong();
        LoadTableThanhToan(tfMaHopDong1.getText());
        tongThanhToan();
        conNo();
    }

    @FXML
    private void tableThanhToanClick(MouseEvent e) {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1) {
            btnMoi2.setVisible(true);
            btnThem2.setVisible(false);
            btnXoa2.setVisible(true);
            btnLuu2.setVisible(true);
            ThanhToan thanhToan = tbThanhToan.getSelectionModel().getSelectedItem();
            tfMaHopDong2.setText(String.valueOf(thanhToan.getMaHopDong()));
            tfLanThanhToan.setText(String.valueOf(thanhToan.getLanThanhToan()));
            dpNgayThanhToan.setValue(LocalDate.parse(String.valueOf(thanhToan.getNgayThanhToan())));
            tfTriGia2.setText(String.valueOf(thanhToan.getSoTien()));

        }
    }

    @FXML
    private void tableHopDongClick(MouseEvent e) throws SQLException {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1) {

            btnMoi1.setVisible(true);
            btnThem1.setVisible(false);
            btnXoa1.setVisible(true);
            btnLuu1.setVisible(true);
            btnMoi2.setVisible(false);
            btnThem2.setVisible(true);
            btnXoa2.setVisible(false);
            btnLuu2.setVisible(false);
            HopDong hopDong = tbHopDong.getSelectionModel().getSelectedItem();
            tfMaHopDong1.setText(String.valueOf(hopDong.getMaHopDong()));
            tfMaHopDong2.setText(String.valueOf(hopDong.getMaHopDong()));
            tfMaChuyenDi.setText(String.valueOf(hopDong.getMaChuyenDi()));
            cmbTenCongTy.setValue(CongTyDuLich.CongTyDuLich(hopDong.getMaCongTy(), listCongTyDuLich));
            tfTriGia1.setText(String.valueOf(hopDong.getTriGia()));
            dpNgayKy.setValue(LocalDate.parse(String.valueOf(hopDong.getNgayKy())));

            tongThanhToan();
            conNo();
            LoadTableThanhToan(hopDong.getMaHopDong());
        }
    }

    @FXML
    private void btnThemClick1(ActionEvent event) throws SQLException {

        if (tfMaChuyenDi.getText().isEmpty() || dpNgayKy.getValue() == null || tfTriGia1.getText().isEmpty()) {
            lbThongBaoHD.setText("Vui lòng nhập đầy đủ thông tin!");
        } else {
            if (addHopDong() == true) {
                lbThongBaoHD.setText("Thêm thành công!");
            } else {
                lbThongBaoHD.setText("Thêm thất bại!");
            }
        }
        LoadTableHopDong();

    }

    @FXML
    private void btnThem2Click(ActionEvent event) throws SQLException {
        if (dpNgayThanhToan.getValue() == null || tfTriGia2.getText().isEmpty()) {
            lbThongBaoTT.setText("Vui lòng nhập đầy đủ thông tin!");
        } else {
            if (addThanhToan() == true) {
                try {
                    lbThongBaoTT.setText("Thêm thành công!");
                } catch (Exception e) {
                    System.out.println("Không thể hiển thị thông báo");
                }
            } else {
                lbThongBaoTT.setText("Thêm thất bại!");
            }

        }
        LoadTableThanhToan(tfMaHopDong1.getText());
        LoadTableHopDong();
        tongThanhToan();
        conNo();
    }

}
