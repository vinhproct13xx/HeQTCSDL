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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import model.ChuyenDi;
import model.ChuyenDiDAO;
import model.CongTyDuLich;
import model.DiaDiem;
import model.GiaoVien;
import model.GiaoVienDAO;
import model.LopHoc;
import model.LopHocDAO;

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
    private JFXComboBox<LopHoc> cmbLop;
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
    private TableView<GiaoVien> tbGiaoVien;
    @FXML
    private TableColumn<GiaoVien, Integer> colMaGiaoVien;
    @FXML
    private TableColumn<GiaoVien, String> colTen;
    @FXML
    private TableColumn<GiaoVien, Date> colNgaySinh;
    @FXML
    private TableColumn<GiaoVien, String> colDiaChi;
    @FXML
    private TableColumn<GiaoVien, String> colSDT;
    @FXML
    private TableColumn<GiaoVien ,String> colCMND;
    @FXML
    private TableColumn<GiaoVien, LopHoc> colLop;
    @FXML
    private Label lbThongBao;

    private ObservableList<GiaoVien> listGiaoVien = FXCollections.observableArrayList();
    private ObservableList<LopHoc> listLopHoc = FXCollections.observableArrayList();
    
    private GiaoVienDAO giaoVienDAO;
    private LopHocDAO lopHocDAO;
    @FXML
    private JFXButton btnThem;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            loadTable();
            loadCombobox();
            btnMoi.setVisible(false);
            btnLuu.setVisible(false);
            btnXoa.setVisible(false);
        } catch (SQLException ex) {
            Logger.getLogger(GiaoVienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public void loadTable() throws SQLException{
        giaoVienDAO = new GiaoVienDAO();
        listGiaoVien = giaoVienDAO.getlistGiaoVien();
        setCellValueFactory();
        tbGiaoVien.setItems(listGiaoVien);   
    }
    public void loadCombobox() throws SQLException{
        lopHocDAO = new LopHocDAO();
        listLopHoc=lopHocDAO.getlistLopHoc();
        cmbLop.setItems(listLopHoc);
    }
    public void setCellValueFactory(){
        colMaGiaoVien.setCellValueFactory(new PropertyValueFactory<GiaoVien, Integer>("MaGV"));
        colTen.setCellValueFactory(new PropertyValueFactory<GiaoVien, String>("TenGV"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<GiaoVien, Date>("NgaySinh"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<GiaoVien, String>("DiaChi"));
        colSDT.setCellValueFactory(new PropertyValueFactory<GiaoVien, String>("SDT"));
        colCMND.setCellValueFactory(new PropertyValueFactory<GiaoVien, String>("CMND"));
        colLop.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<GiaoVien, LopHoc>, ObservableValue<LopHoc>>() {
            @Override
            public ObservableValue<LopHoc> call(TableColumn.CellDataFeatures<GiaoVien, LopHoc> param) {
                GiaoVien gv = param.getValue();
                String maLopHoc = gv.getMaLop();
                LopHoc lopHoc = LopHoc.lopHoc(maLopHoc, listLopHoc);
                return  new SimpleObjectProperty<LopHoc>(lopHoc);
            }
            });
        
       
    }
    public boolean addGiaoVien() throws SQLException{
        giaoVienDAO = new GiaoVienDAO();
       
        String tenGiaoVien = tfTenGiaoVien.getText();
        Date ngaySinh=Date.valueOf(dpNgaySinh.getValue());
        String diaChi= tfDiaChi.getText();
        String SDT=tfSDT.getText();
        String CMND = tfCMND.getText();
        String maLop=cmbLop.getValue().getStrMaLop();

        return giaoVienDAO.ThemGiaoVien( tenGiaoVien, ngaySinh, diaChi, SDT, CMND, maLop);
    }
    public boolean updateGiaoVien() throws SQLException{
        giaoVienDAO = new GiaoVienDAO();
        String maGiaoVien=tfMaGiaoVien.getText();
        String tenGiaoVien=tfTenGiaoVien.getText();
        Date ngaySinh=Date.valueOf(dpNgaySinh.getValue());
        String diaChi=tfDiaChi.getText();
        String SDT = tfSDT.getText();
        String CMND = tfCMND.getText();
        String maLop=cmbLop.getValue().getStrMaLop();
       
        return giaoVienDAO.suaGiaoVien(maGiaoVien, tenGiaoVien, ngaySinh, diaChi, SDT, CMND, maLop);
    }
      public void searchGiaoVien() throws SQLException{
        //load combobox lop hoc, nam hoc, 
        giaoVienDAO = new GiaoVienDAO();
        String data=tfTimTenGiaoVien.getText();
        listGiaoVien = giaoVienDAO.TimGiaoVien(data);
        if (listGiaoVien.isEmpty()) {
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Thông báo");
            alert1.setHeaderText("Không tìm thấy");
            alert1.show();
        }
        setCellValueFactory();
        tbGiaoVien.setItems(listGiaoVien);   
      
    }
    @FXML
    private void btnMoiClick(ActionEvent event) throws SQLException {
        tfMaGiaoVien.clear();
        tfTenGiaoVien.clear();
        tfDiaChi.clear();
        tfSDT.clear();
        tfCMND.clear();
         btnMoi.setVisible(false);
        btnThem.setVisible(true);
        btnXoa.setVisible(false);
        btnLuu.setVisible(false);
        lbThongBao.setText("");
        loadTable();
    }


    @FXML
    private void btnXoaClick(ActionEvent event) throws SQLException {
        if(!tfMaGiaoVien.getText().isEmpty()){
            showAlertDelete();
        }
        else
            lbThongBao.setText("Chọn giáo viên cần xóa!");
    }

    @FXML
    private void btnTimClick(ActionEvent event) throws SQLException {
        if(tfTimTenGiaoVien.getText().isEmpty()){
            loadTable();
        }
        else
            searchGiaoVien();
    }

    @FXML
    private void btnLuuClick(ActionEvent event) throws SQLException {
        
         if(tfMaGiaoVien.getText().isEmpty()==false){
            if(updateGiaoVien()==true)
                lbThongBao.setText("Cập nhật thành công!");
            else
                lbThongBao.setText("Cập nhật thất bại!");
       }
        loadTable();     
    }
    public boolean deleteGiaoVien() throws SQLException{
        String maGiaoVien=tfMaGiaoVien.getText();
        giaoVienDAO = new GiaoVienDAO();
        return giaoVienDAO.xoaGiaoVien(maGiaoVien);
    }
  public void showAlertDelete() throws SQLException{
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
          alert.setTitle("Confirmation");
          alert.setHeaderText("Bạn có chắc chắn muốn xóa?");
          alert.setContentText("");
          
          ButtonType buttontypeYes = new ButtonType("YES", ButtonBar.ButtonData.YES);
          ButtonType buttontypeNo = new ButtonType("NO", ButtonBar.ButtonData.NO);
          ButtonType buttontypeCancel = new ButtonType("CANCLE", ButtonBar.ButtonData.CANCEL_CLOSE);
            
          alert.getButtonTypes().setAll(buttontypeYes,buttontypeNo,buttontypeCancel);
          
          Optional<ButtonType> result = alert.showAndWait();
          
           Alert alert1=new Alert(Alert.AlertType.INFORMATION);
           alert1.setTitle("Thông báo");
                alert1.setHeaderText("");
          if(result.get()==buttontypeYes){
            if(deleteGiaoVien()==true){
                alert1.setContentText("Xóa thành công");
                alert1.show();
            }
            else{
                alert1.setContentText("Xóa that bai");
                alert1.show();
            }
        
        loadTable();
          }
          else if (result.get().getButtonData()==ButtonBar.ButtonData.NO)
              alert.close();
          else
              alert.close();
    }
    @FXML
    private void tbGiaoVienClick(MouseEvent e) {
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
            btnLuu.setVisible(true);
            btnXoa.setVisible(true);
            btnMoi.setVisible(true);
            btnThem.setVisible(false);
            GiaoVien giaoVien = tbGiaoVien.getSelectionModel().getSelectedItem();
            tfMaGiaoVien.setText(String.valueOf(giaoVien.getMaGV()));
            tfTenGiaoVien.setText(String.valueOf(giaoVien.getTenGV()));
            dpNgaySinh.setValue(LocalDate.parse(String.valueOf(giaoVien.getNgaySinh())));
            tfDiaChi.setText(String.valueOf(giaoVien.getDiaChi()));
            tfSDT.setText(String.valueOf(giaoVien.getSDT()));
            tfCMND.setText(String.valueOf(giaoVien.getCMND()));
            cmbLop.setValue(LopHoc.lopHoc(giaoVien.getMaLop(), listLopHoc));
        }
    }

    @FXML
    private void btnThemClick(ActionEvent event) throws SQLException {
        
            if(addGiaoVien()==true)
                lbThongBao.setText("Thêm thành công!");
            else
                lbThongBao.setText("Thêm thất bại!");
            loadTable();
        
    }

    
    
}
