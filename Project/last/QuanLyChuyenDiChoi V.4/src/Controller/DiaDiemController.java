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
import com.sun.javafx.scene.control.skin.TableViewSkin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.text.Text;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import model.*;


/**
 * FXML Controller class
 *
 * @author Tung
 */
public class DiaDiemController implements Initializable {

    @FXML
    private TableView<DiaDiem> tbDiaDiem;
    @FXML
    private TableColumn<DiaDiem, String > colMaDiaDiem;
    @FXML
    private TableColumn<DiaDiem, String > colTenDiaDiem;
    @FXML
    private TableColumn<DiaDiem, String > colDiaChi;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnXoa;
    @FXML
    private JFXButton btnSua;
    @FXML
    private JFXTextField tfMaDiaDiem;
    @FXML
    private JFXTextField tfTenDiaDiem;
    @FXML
    private JFXTextField tfDiaChi;
    private ObservableList<DiaDiem> listDiaDiem = FXCollections.observableArrayList();
    private DiaDiemDAO ddDao;
    private int max = 0;
    @FXML
    private JFXButton btnNhapMoi;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        tfMaDiaDiem.setEditable(false);
        btnNhapMoi.setVisible(false);
        try {
            Loaddata();
        } catch (SQLException ex) {
            System.out.println("Can't load data in initialize");
        }
    }    

    void Loaddata() throws SQLException{
        ddDao = new DiaDiemDAO();
        listDiaDiem = ddDao.getlistDiaDiem();
        setCellValueFactory();
        tbDiaDiem.setItems(listDiaDiem);
        max = ddDao.MaxMaDiaDiem();
    }
    void  setCellValueFactory(){
        colMaDiaDiem.setCellValueFactory(new PropertyValueFactory<DiaDiem, String>("MaDiaDiem"));
        colTenDiaDiem.setCellValueFactory(new PropertyValueFactory<DiaDiem, String>("TenDiaDiem"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<DiaDiem, String>("DiaChi"));
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
    private void tbDiaDiemClick(MouseEvent e) {
        if(MouseButton.PRIMARY == e.getButton() && e.getClickCount() == 1){
            btnAdd.setVisible(false);
            btnSua.setVisible(true);
            btnXoa.setVisible(true);
            btnNhapMoi.setVisible(true);
            DiaDiem dd = tbDiaDiem.getSelectionModel().getSelectedItem();
            tfMaDiaDiem.setText(dd.getMaDiaDiem());
            tfTenDiaDiem.setText(dd.getTenDiaDiem());
            tfDiaChi.setText(dd.getDiaChi());
        }
    }
    @FXML
    private void btnAddClick(ActionEvent event) throws SQLException {
        if (tfTenDiaDiem.getText().isEmpty()){
            System.out.print("Vui l??ng nh???p t??n ?????a ??i???m!!!");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Th??ng b??o");
            alert.setHeaderText("L???i nh???p d??? li???u");
            alert.setContentText("Vui l??ng nh???p t??n ?????a ??i???m!!");
            alert.showAndWait();
        }
        else if (tfDiaChi.getText().isEmpty()){
           Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Th??ng b??o");
            alert1.setHeaderText("Vui l??ng nh???p ?????a ch???");
            alert1.show();
        }
        else {
        
        DiaDiem dd = new DiaDiem();
      
        dd.setTenDiaDiem(tfTenDiaDiem.getText());
        dd.setDiaChi(tfDiaChi.getText());
        btnAdd.setVisible(true);
        try{
            ddDao.Them(dd);
            Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
            alert1.setTitle("Th??ng b??o");
            alert1.setHeaderText("G??c ch??c m???ng:");
            alert1.setContentText("Th??nh c??ng yeah yeah!!");
            alert1.showAndWait();
        }catch(Exception e){
            System.out.println("Kh??ng th??? th??m d??? li???u!!!");
            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Th??ng b??o");
            alert2.setHeaderText("G??c chia bu???n:");
            alert2.setContentText("L???i c???p nh???t d??? li???u!!");
            alert2.showAndWait();
        };
        Loaddata();
        tfMaDiaDiem.setText(null);
        tfTenDiaDiem.setText(null);
        tfDiaChi.setText(null);
        
        }
    }

    @FXML
    private void btnXoaClick(ActionEvent event) throws SQLException{
        DiaDiem dd =new DiaDiem();
        dd.setMaDiaDiem(tfMaDiaDiem.getText());
        dd.setTenDiaDiem(tfTenDiaDiem.getText());
        dd.setDiaChi(tfDiaChi.getText());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("C???nh b??o");
        alert.setHeaderText("G??c h???i ????p:");
        alert.setContentText("B???n c?? th???t s??? mu???n x??a kh??ng?");
        ButtonType buttonTypeYes = new ButtonType("?????ng ??");
        ButtonType buttonTypeNo = new ButtonType("H???y");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        switch (result.get().getText()) {
        case "?????ng ??":{
            try{
                ddDao.Xoa(dd);
            }catch(Exception e){
                System.out.println("Kh??ng th??? x??a d??? li???u!!!");
            };
            Loaddata();
            tfMaDiaDiem.setText(null);
            tfTenDiaDiem.setText(null);
            tfDiaChi.setText(null);   
            btnAdd.setVisible(true);
            btnSua.setVisible(false);
            btnXoa.setVisible(false);
            btnNhapMoi.setVisible(false);
            break;
        }
        case "H???y":
            break;
        default:
            break;
        }
        
    }

    @FXML
    private void btnSuaClick(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("C???nh b??o");
        alert.setHeaderText("G??c h???i ????p:");
        alert.setContentText("B???n c?? th???t s??? mu???n s???a kh??ng?");
        ButtonType buttonTypeYes = new ButtonType("?????ng ??");
        ButtonType buttonTypeNo = new ButtonType("H???y");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = alert.showAndWait();
        switch (result.get().getText()) {
        case "?????ng ??":{
            if (tfTenDiaDiem.getText().isEmpty()){
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Th??ng b??o");
            alert1.setHeaderText("Vui l??ng nh???p t??n ?????a ??i???m");
            alert1.show();
            }
            else if (tfDiaChi.getText().isEmpty()){
                  Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Th??ng b??o");
            alert1.setHeaderText("Vui l??ng nh???p t??n ?????a ch???");
            alert1.show();
            }
            else {
                DiaDiem dd = new DiaDiem();
                dd.setMaDiaDiem(tfMaDiaDiem.getText());
                dd.setTenDiaDiem(tfTenDiaDiem.getText());
                dd.setDiaChi(tfDiaChi.getText());
                btnAdd.setVisible(true);
            try{
                ddDao.Sua(dd);
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Th??ng b??o");
                alert1.setHeaderText("G??c ch??c m???ng:");
                alert1.setContentText("Th??nh c??ng yeah yeah!!");
                alert1.showAndWait();
            
            }catch(Exception e){
                System.out.println("Kh??ng th??? th??m d??? li???u!!!");
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Th??ng b??o");
                alert2.setHeaderText("G??c chia bu???n:");
                alert2.setContentText("L???i c???p nh???t d??? li???u!!");
                alert2.showAndWait();
            };
            Loaddata();
            tfMaDiaDiem.setText(null);
            tfTenDiaDiem.setText(null);
            tfDiaChi.setText(null);
            }
            break;
        }
        case "H???y":
            break;
        default:
            break;
    }
    }

    @FXML
    private void tfMaDiaDiemClick(ActionEvent event) {
    }

    @FXML
    private void tfTenDiaDiemClick(ActionEvent event) {
    }

    @FXML
    private void tfDiaChiClick(ActionEvent event) {
    }

    @FXML
    private void btnNhapMoiClick(ActionEvent event) throws SQLException{
        tfMaDiaDiem.setEditable(true);
        btnAdd.setVisible(true);
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        btnNhapMoi.setVisible(false);
        tfMaDiaDiem.setText(null);
        tfTenDiaDiem.setText(null);
        tfDiaChi.setText(null);
    }
    
}
