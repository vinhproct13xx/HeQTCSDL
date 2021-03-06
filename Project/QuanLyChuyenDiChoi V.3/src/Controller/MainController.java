/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.User;
import model.UserDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class MainController implements Initializable {

    @FXML
    private JFXButton btnChuyenDi;
    @FXML
    private JFXButton btnCTyDuLich;
    @FXML
    private JFXButton btnDiaDiem;
    public static MainController mainController;
    UserDAO userDAO;
    @FXML
    private JFXNodesList NodeList;
    @FXML
    private AnchorPane rootPane;

        public MainController(){
        mainController = this;
    }
    public static MainController getMainController() {
        return mainController;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createNhanSu();
        // TODO
    }

    @FXML
    private void btnChuyenDiClick(ActionEvent event) throws SQLException, IOException {
        LoadChuyenDi(event);
    }

    @FXML
    private void btnCTyDuLichClick(ActionEvent event) throws IOException {
        LoadCongTy(event);
    }

    @FXML
    private void btnDiaDiemClick(ActionEvent event) {
    }

    public FXMLLoader createPage(AnchorPane pane, String loc) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(loc));
        try {
            pane = fxmlLoader.load();
            rootPane.getChildren().add((Node) pane);
            GeneralFuntion.FitChildContent(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fxmlLoader;
    }

    public void createNhanSu() {
        JFXButton btnNhanSu = CreateButton("Nh??n s???");
        btnNhanSu.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView NhanSuMngIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT);
        NhanSuMngIcon.setId(".glyph-icon");
        btnNhanSu.setGraphic(NhanSuMngIcon);

        JFXButton btnHocSinh = CreateButton("H???c Sinh");
        btnHocSinh.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView HocSinhMngIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT);
        HocSinhMngIcon.setId(".glyph-icon");
        btnHocSinh.setGraphic(HocSinhMngIcon);
        btnHocSinh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    loadHocSinh(event);
                } catch (Exception e) {
                    System.out.print("Can't show hoc sinh");
                }
            }
        });
        JFXButton btnGiaoVien = CreateButton("Gi??o vi??n");
        btnGiaoVien.setButtonType(JFXButton.ButtonType.RAISED);
        MaterialDesignIconView GiaoVienMngIcon = new MaterialDesignIconView(MaterialDesignIcon.ACCOUNT_STAR_VARIANT);
        GiaoVienMngIcon.setId(".glyph-icon");
        btnGiaoVien.setGraphic(GiaoVienMngIcon);
        btnGiaoVien.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    LoadGiaoVien(event);
                } catch (Exception e) {
                    System.out.print("Can't show hoc sinh");
                }
            }
        } );
        
        NodeList.addAnimatedNode(btnNhanSu);
        NodeList.addAnimatedNode(btnHocSinh);
        NodeList.addAnimatedNode(btnGiaoVien);
    }

    private JFXButton CreateButton(String text) {
        JFXButton btn = new JFXButton(text);
        btn.setMinSize(376, 79);
        btn.setMaxSize(376, 79);
        btn.setAlignment(Pos.CENTER_LEFT);
        btn.setWrapText(true);
        btn.setFont(new Font(30));
        btn.setTextFill(Color.WHITE);
        return btn;
    }

   public  void loadHocSinh(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/quanlychuyendichoi/HopDong.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
    public  void LoadGiaoVien(ActionEvent event) throws  IOException{
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/quanlychuyendichoi/GiaoVien.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
     public  void LoadChuyenDi(ActionEvent event) throws  IOException{
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/quanlychuyendichoi/ChuyenDi.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
    public  void LoadCongTy(ActionEvent event) throws  IOException{
          AnchorPane pane = FXMLLoader.load(getClass().getResource("/quanlychuyendichoi/CongTy.fxml"));
        rootPane.getChildren().setAll(pane);
        GeneralFuntion.FitChildContent(pane);
    }
}
