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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
    @FXML
    private JFXTextField tfUsername;

    UserDAO userDAO;
    ObservableList<User> listUser = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userDAO = new UserDAO();
        try {
            listUser = userDAO.getListUser();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void btnLoginClick(ActionEvent event) throws IOException {
        User user = new User();
        user.setEmail(tfUsername.getText());
        user.setPassword(tfPassword.getText());
        int check = CheckUser(user, listUser);
        if(check == 0 )
        {
            Alert alert= new Alert(AlertType.INFORMATION);
            alert.setHeaderText("Error");
            alert.setContentText("Không đúng tên hoặc mật khẩu! Vui lòng nhập lại");
            alert.show();
        }
        else
        {
         ((Node)event.getSource()).getScene().getWindow().hide();
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
    
    private int CheckUser(User user, ObservableList<User> list){
        for(User u : list)
        {
            if(u.getEmail().equals(user.getEmail()) )
            {
                if(u.getPassword().equals(user.getPassword()) )
                    return 1;
                else
                    return 0;
            }
        }
        return 0;
    }
}
