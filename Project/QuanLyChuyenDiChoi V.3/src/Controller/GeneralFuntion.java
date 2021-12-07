/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import com.jfoenix.controls.*;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author stari
 */
public final class GeneralFuntion {
    public static void FitChildContent(Node child){
        AnchorPane.setBottomAnchor(child, 0.0);
        AnchorPane.setTopAnchor(child, 0.0);
        AnchorPane.setLeftAnchor(child, 0.0);
        AnchorPane.setRightAnchor(child, 0.0);
    }
    public static void createNotifyDialog(AnchorPane rootPane,String header,String text,boolean loadTheFirstPage){
        rootPane.setVisible(true);
        JFXDialogLayout content = new JFXDialogLayout();
        content.setHeading(new Text(header));
        JFXButton button = new JFXButton("Close");
        content.setPrefSize(500, 250);
        Label lbNotify = new Label(text);
        lbNotify.setWrapText(true);
        lbNotify.setStyle("-fx-font-weight: bold");
        lbNotify.setFont(Font.font("Arial", 20));
        lbNotify.setTextFill(Color.BLACK);
        content.setBody(lbNotify);
        StackPane stackPane = new StackPane();
        stackPane.autosize();
        JFXDialog confirmModal = new JFXDialog(stackPane, content, JFXDialog.DialogTransition.TOP);

        button.setFont(new Font(15));
        button.setStyle("-fx-background-color: #000000;-fx-font-weight: bold");
        button.setTextFill(Color.WHITE);
        button.setPrefSize(100, 50);
        button.setOnAction(e -> {
            confirmModal.close();
            if(loadTheFirstPage){
                AnchorPane scene = new AnchorPane();
                MainController.getMainController().createPage(scene, "/ChuyenDi.fxml");
            }

        });
        content.setActions(button);
        rootPane.getChildren().add(stackPane);
        FitChildContent(stackPane);
        confirmModal.show();
        confirmModal.setOnDialogClosed(e -> {
            rootPane.getChildren().remove(rootPane.getChildren().size() - 1);
            rootPane.setVisible(false);
        });
    }
}
