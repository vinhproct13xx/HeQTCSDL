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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import  javafx.scene.control.Button;

/**
 *
 * @author MyPC
 */
public class HocSinhController implements Initializable{

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
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Loaddata();
            loadcombobox(listClassStudents, listScholastics);
            
        } catch (SQLException ex) {
            System.out.println("Can't load data in initialize");
        }
    }
    void Loaddata() throws SQLException{
        //load combobox lop hoc, nam hoc, 
        hocsinhDAO = new StudentDAO();
        listClassStudents = hocsinhDAO.getListClass();
        listScholastics = hocsinhDAO.getListScholastics();
    }
    void loadcombobox(ObservableList<LopHoc> listLopHoc, ObservableList<NamHoc> listNamHoc){
        cmbLopHoc.setItems(listClassStudents);
        cmbNamHoc.setItems(listScholastics);
    }


    @FXML
    private void btnXoaClick(ActionEvent event) {
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

    void setCellValueFactory(){
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
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
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
    }

    @FXML
    private void btlLuuClick(ActionEvent event) {
    }

    @FXML
    private void btnTimClick(ActionEvent event) {
    }
}
