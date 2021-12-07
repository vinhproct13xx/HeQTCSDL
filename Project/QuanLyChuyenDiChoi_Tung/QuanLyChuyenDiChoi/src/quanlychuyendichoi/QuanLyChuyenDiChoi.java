/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlychuyendichoi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Scoobydo
 */
public class QuanLyChuyenDiChoi extends Application {
    
    @Override
   public void start(Stage primaryStage) {
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("ThongKe.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
                        primaryStage.setTitle("Quan ly chuyen di choi");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
