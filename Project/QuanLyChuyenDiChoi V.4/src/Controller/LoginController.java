/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import model.User;
import model.UserDAO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author MyPC
 */
public class LoginController implements Initializable {
    
    @FXML
    private JFXPasswordField tfPassword;
    @FXML
    private JFXButton btnLogin;
    private JFXTextField tfUsername;
    
    UserDAO userDAO;
    ObservableList<User> listUser = FXCollections.observableArrayList();
    @FXML
    private Hyperlink LinkDangKy;
    @FXML
    private Hyperlink LinkPass;
    @FXML
    private JFXPasswordField tfPasswordDN;
    @FXML
    private JFXTextField tfUsernameDN;
    @FXML
    private AnchorPane paneDangKy;
    @FXML
    private JFXTextField tfUsername11;
    @FXML
    private AnchorPane paneDoiPass;
    @FXML
    private JFXPasswordField tfPasswordDK;
    @FXML
    private JFXButton btnDangKy;
    @FXML
    private JFXTextField tfUsernameDK;
    @FXML
    private JFXPasswordField tfPasswordConfirmDK;
    @FXML
    private JFXButton btnDoiMk;
    @FXML
    private JFXPasswordField tfConfirmpass1;
    private User userChung;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDAO = new UserDAO();
        try {
            listUser = userDAO.getListUser();
            tfUsernameDN.setText("16521409@gm.uit.edu.vn");
            tfPasswordDN.setText("1");
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnLoginClick(ActionEvent event) throws IOException {
        User user = new User();
        user.setEmail(tfUsernameDN.getText());
        user.setPassword(tfPasswordDN.getText());
        int check = CheckUser(user, listUser);
        if (check == 0) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText("Không đúng tên hoặc mật khẩu! Vui lòng nhập lại");
            alert.show();
        } else {
            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quanlychuyendichoi/Main.fxml"));
            Parent parent = fxmlLoader.load();
            MainController main = fxmlLoader.<MainController>getController();
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setTitle("Login");
            stage.setMaximized(true);
            stage.show();
        }
        
    }
    
    private int CheckUser(User user, ObservableList<User> list) {
        for (User u : list) {
            if (u.getEmail().equals(user.getEmail())) {
                if (u.getPassword().equals(user.getPassword())) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
        return 0;
    }
    
    @FXML
    private void DangKyClick(ActionEvent event) {
        paneDangKy.setVisible(true);
        tfUsernameDK.setText(null);
        tfPasswordDK.setText(null);
        tfPasswordConfirmDK.setText(null);
        btnDangKy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                User user = new User();
                if (!tfPasswordDK.getText().equals(tfPasswordConfirmDK.getText())) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Error");
                    alert.setContentText("Mật khẩu không khớp vui lòng nhập lại");
                    alert.show();
                } else {
                    user.setEmail(tfUsernameDK.getText());
                    user.setPassword(tfPasswordDK.getText());
                    int max = 0;
                    for (User u : listUser) {
                        if (u.getID() > max) {
                            max = u.getID();
                        }
                    }
                    user.setID(max + 1);
                    user.setLevel(1);
                    userDAO.AddUser(user);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Successfully");
                    alert.setContentText("Đã thêm thành công!");
                    alert.showAndWait();
                    
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quanlychuyendichoi/Main.fxml"));
                    Parent parent = null;
                    try {
                        parent = fxmlLoader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MainController main = fxmlLoader.<MainController>getController();
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.setMaximized(true);
                    stage.show();
                    
                }
                
            }
        });
        
    }
    
    @FXML
    private void LinkChangePassClick(ActionEvent event) {
        paneDangKy.setVisible(false);
        paneDoiPass.setVisible(true);
        tfUsername11.setText(tfUsernameDN.getText());
        tfPassword.setText(null);
        tfConfirmpass1.setText(null);
        
        btnDoiMk.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
                if (tfPassword.getText().length() > 50) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Message: ");
                    alert.setContentText("Mật khẩu quá dài. vui lòng nhập lại");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setHeaderText("Message: ");
                    alert.setContentText("Bạn chắc chắn đổi mật khẩu? ");
                    alert.showAndWait();
                    User user = new User();
                    user = User.getbyEmail(tfUsername11.getText(), listUser);
                    user.setPassword(tfConfirmpass1.getText());
                    userDAO.UpdateUser(user);
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/quanlychuyendichoi/Main.fxml"));
                    Parent parent = null;
                    try {
                        parent = fxmlLoader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    MainController main = fxmlLoader.<MainController>getController();
                    Stage stage = new Stage();
                    Scene scene = new Scene(parent);
                    stage.setScene(scene);
                    stage.setTitle("Login");
                    stage.setMaximized(true);
                    stage.show();
                }
            }
        }
        );
    }
    
    @FXML
    private void btnDangKyClick(ActionEvent event) {
    }
    
    @FXML
    private void btnDoiMkClick(ActionEvent event) {
    }
}
