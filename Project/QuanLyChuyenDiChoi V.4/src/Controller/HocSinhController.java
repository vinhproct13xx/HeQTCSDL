/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.StudentDAO;
import model.NamHoc;
import model.LopHoc;
import model.HocSinh;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;

/**
 *
 * @author MyPC
 */
public class HocSinhController implements Initializable {

    @FXML
    private JFXTextField tfMaHocSinh;
    @FXML
    private JFXTextField tfTenHS;
    @FXML
    private JFXTextField tfDiaChi;
    @FXML
    private JFXTextField tfTenCha;
    @FXML
    private JFXTextField tfTenMe;
    @FXML
    private JFXTextField tfTenNguoiGiamHo;
    @FXML
    private JFXTextField tfSDT;
    @FXML
    private JFXDatePicker dpNgaySinh;
    @FXML
    private TableColumn<HocSinh, Integer> colMaHS;
    @FXML
    private TableColumn<HocSinh, String> colTen;
    @FXML
    private TableColumn<HocSinh, Date> colNgaySinh;
    @FXML
    private TableColumn<HocSinh, String> colDiaChi;
    @FXML
    private TableColumn<HocSinh, String> colTenCha;
    @FXML
    private TableColumn<HocSinh, String> colTenMe;
    @FXML
    private TableColumn<HocSinh, String> colNguoiGiamHo;
    @FXML
    private TableColumn<HocSinh, String> colSDT;
    @FXML
    private JFXComboBox<LopHoc> cmbLopHoc;
    @FXML
    private JFXComboBox<NamHoc> cmbNamHoc;

    private ObservableList<LopHoc> listClassStudents = FXCollections.observableArrayList();
    private ObservableList<NamHoc> listScholastics = FXCollections.observableArrayList();
    private StudentDAO hocsinhDAO;
    private ObservableList<HocSinh> listStudents = FXCollections.observableArrayList();
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnXem;
    @FXML
    private TableView<HocSinh> tbHocSinh;
    @FXML
    private JFXButton btnMoi;
    @FXML
    private JFXButton btnLuu;
    @FXML
    private JFXButton btnTim;
    private int max = 0;
    @FXML
    private JFXTextField tfSearch;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Loaddata();
            loadcombobox(listClassStudents, listScholastics);
            btnXoa.setVisible(false);
            btnLuu.setVisible(false);

        } catch (SQLException ex) {
            System.out.println("Can't load data in initialize");
        }
    }

    void Loaddata() throws SQLException {
        //load combobox lop hoc, nam hoc, 
        hocsinhDAO = new StudentDAO();
        listClassStudents = hocsinhDAO.getListClass();
        listScholastics = hocsinhDAO.getListScholastics();
        max = hocsinhDAO.MaxMaHS();
    }

    void loadcombobox(ObservableList<LopHoc> listLopHoc, ObservableList<NamHoc> listNamHoc) {
        cmbLopHoc.setItems(listClassStudents);
        cmbNamHoc.setItems(listScholastics);
    }

    @FXML
    private void btnXemClick(ActionEvent event) throws SQLException {
        int NamHoc = cmbNamHoc.getValue().getMaNH();
        String Lop = cmbLopHoc.getValue().getStrMaLop();
        listStudents = hocsinhDAO.getlistStudents(Lop, NamHoc);
        setCellValueFactory();
        tbHocSinh.setItems(listStudents);
        autoFitTable(tbHocSinh);
    }

    void setCellValueFactory() {
        colMaHS.setCellValueFactory(new PropertyValueFactory<HocSinh, Integer>("MaHS"));
        colTen.setCellValueFactory(new PropertyValueFactory<HocSinh, String>("TenHS"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<HocSinh, Date>("NgaySinh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<HocSinh, String>("DiaChi"));
        colTenCha.setCellValueFactory(new PropertyValueFactory<HocSinh, String>("TenCha"));
        colTenMe.setCellValueFactory(new PropertyValueFactory<HocSinh, String>("TenMe"));
        colSDT.setCellValueFactory(new PropertyValueFactory<HocSinh, String>("SDT"));
        colNguoiGiamHo.setCellValueFactory(new PropertyValueFactory<HocSinh, String>("TenNguoiGiamHo"));
    }
    private static Method columnToFitMethod;

    static {
        try {
            columnToFitMethod = TableViewSkin.class.getDeclaredMethod("resizeColumnToFitContent", TableColumn.class, int.class);
            columnToFitMethod.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void autoFitTable(TableView tableView) {
        tableView.getItems().addListener(new ListChangeListener<Object>() {
            @Override
            public void onChanged(ListChangeListener.Change<?> c) {
                for (Object column : tableView.getColumns()) {
                    try {
                        columnToFitMethod.invoke(tableView.getSkin(), column, -1);

                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @FXML
    private void TableHocSInhClick(MouseEvent e) {
        if (MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1) {
            btnXoa.setVisible(true);
            btnLuu.setVisible(true);
            HocSinh hocsinh = tbHocSinh.getSelectionModel().getSelectedItem();
            tfMaHocSinh.setText(String.valueOf(hocsinh.getMaHS()));
            tfTenHS.setText(hocsinh.getTenHS());

            tfTenCha.setText(hocsinh.getTenCha());
            tfTenMe.setText(hocsinh.getTenMe());
            tfTenNguoiGiamHo.setText(hocsinh.getTenNguoiGiamHo());
            tfDiaChi.setText(hocsinh.getDiaChi());
            tfSDT.setText(hocsinh.getSDT());
            dpNgaySinh.setValue(LocalDate.parse(hocsinh.getNgaySinh().toString()));

        }
    }

    @FXML
    private void btnMoiClick(ActionEvent event) {
        tfMaHocSinh.setText(null);
        tfMaHocSinh.setDisable(true);
        tfTenHS.setText(null);
        dpNgaySinh.setValue(LocalDate.now());
        tfDiaChi.setText(null);
        tfTenCha.setText(null);
        tfTenMe.setText(null);
        tfTenNguoiGiamHo.setText(null);
        tfSDT.setText(null);
        btnLuu.setVisible(true);
    }

    @FXML
    private void btlLuuClick(ActionEvent event) throws SQLException {
        HocSinh hs = new HocSinh();
        try {
            int id = 0;
        hs.setTenHS(tfTenHS.getText());
        hs.setNgaySinh(Date.valueOf(dpNgaySinh.getValue()));
        hs.setDiaChi(tfDiaChi.getText());
        hs.setTenCha(tfTenCha.getText());
        hs.setTenMe(tfTenMe.getText());
        hs.setTenNguoiGiamHo(tfTenNguoiGiamHo.getText());;
        hs.setSDT(tfSDT.getText());
        hs.setMaLop(cmbLopHoc.getValue().getStrMaLop());
        hs.setNamHoc(cmbNamHoc.getValue().getMaNH());
        if (tfMaHocSinh.getText() == null) {
            id = max + 1;
            hs.setMaHS(id);
            hocsinhDAO.AddHocSinh(hs);
            listStudents.add(hs);
        } else {
            id = Integer.parseInt(tfMaHocSinh.getText());
            hs.setMaHS(id);
            hocsinhDAO.UpdateHocSinh(hs);
            for (HocSinh hocsinh : listStudents) {
                id = hocsinh.getMaHS();
                hocsinh = hs;
            }
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Successfully");
        alert.setContentText("Xin chúc mừng!");
        alert.show();
        } catch (Exception e) {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Error");
        alert.setContentText("Xin lỗi bạn. Không thể lưu học sinh!");
        alert.show();
        }
        hocsinhDAO = new StudentDAO();
        max = hocsinhDAO.MaxMaHS();
        listStudents = hocsinhDAO.getlistStudents(hs.getMaLop(), hs.getNamHoc());
        setCellValueFactory();
        tbHocSinh.setItems(listStudents);
        autoFitTable(tbHocSinh);

    }

    @FXML
    private void btnXoaClick(ActionEvent event) throws SQLException {
        HocSinh hs = tbHocSinh.getSelectionModel().getSelectedItem();
        try {
        hocsinhDAO.DeleteHocSinh(hs);    
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Successfully");
        alert.setContentText("Xin chúc mừng!");
        alert.show();
        } catch (Exception e) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Successfully");
        alert.setContentText("Không thể xóa học sinh khỏi lớp");
        alert.show();
        }
        
        hocsinhDAO = new StudentDAO();
        listStudents = hocsinhDAO.getlistStudents(hs.getMaLop(), hs.getNamHoc());
        setCellValueFactory();
        tbHocSinh.setItems(listStudents);
        autoFitTable(tbHocSinh);

    }

    @FXML
    private void btnTimClick(ActionEvent event) {
        try {
            int MaHS = Integer.parseInt(tfSearch.getText());
            String sql = "SELECT * FROM dbo.HocSinh INNER JOIN dbo.CTLop ON CTLop.MaHS = HocSinh.MaHS WHERE MaLop = '" + cmbLopHoc.getValue().getStrMaLop()
                    + "' AND MaNH = " + cmbNamHoc.getValue().getMaNH() + " and TrangThai = 1 and CTLop.MaHS = " + MaHS;
            HocSinh hs = hocsinhDAO.SearchHocSinh(sql);
            listStudents.clear();
            listStudents.add(hs);
            if (hs == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Warming");
                alert.setContentText("Không tìm thấy học sinh bạn yêu cầu!");
                alert.show();
            } else {
                setCellValueFactory();
                tbHocSinh.setItems(listStudents);
                autoFitTable(tbHocSinh);
            }

        } catch (Exception e) {
            String TenHS = tfSearch.getText();
            String sql = "SELECT * FROM dbo.HocSinh INNER JOIN dbo.CTLop ON CTLop.MaHS = HocSinh.MaHS WHERE MaLop = '" + cmbLopHoc.getValue().getStrMaLop()
                    + "' AND MaNH = " + cmbNamHoc.getValue().getMaNH() + " and TrangThai = 1 and TenHS = '" + TenHS + "'";
            HocSinh hs = hocsinhDAO.SearchHocSinh(sql);
            listStudents.clear();
            listStudents.add(hs);
            if (hs == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("Warming");
                alert.setContentText("Không tìm thấy học sinh bạn yêu cầu!");
                alert.show();
            } else {
                setCellValueFactory();
                tbHocSinh.setItems(listStudents);
                autoFitTable(tbHocSinh);
            }
        }

    }
}
