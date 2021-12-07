/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import model.ThongKeDAO;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Tung
 */
public class ThongKeController implements Initializable {

    @FXML
    private BarChart<?, ?> bcChiPhi;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    ThongKeDAO tkdao;
    @FXML
    private BarChart<?, ?> bcChiPhiTB;
    @FXML
    private NumberAxis xChiPhiTB;
    @FXML
    private CategoryAxis yChiPhiTB;
    @FXML
    private BarChart<?, ?> bcHSTB;
    @FXML
    private NumberAxis yHS;
    @FXML
    private CategoryAxis xHS;
    @FXML
    private BarChart<?, ?> bcSoCD;
    @FXML
    private NumberAxis yCD;
    @FXML
    private CategoryAxis xCD;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        try {
            ChiPhiChart(bcChiPhi);
            ChiPhiTB(bcChiPhiTB);
            HSTB(bcHSTB);
            TongChuyenDi(bcSoCD);
        } catch (Exception e) {
            System.out.println("Cant lay ChiPhiChart!");
        }
    }    
    
    private void ChiPhiChart(BarChart cp)throws SQLException{
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        
        tkdao = new ThongKeDAO();
        float nam1=0;
        float nam2=0;
        float nam3=0;
        float nam4=0;
        float nam5=0;
        try {
                  nam1=tkdao.tkTongChiPhi(2018);
                  nam2=tkdao.tkTongChiPhi(2019);
                  nam3=tkdao.tkTongChiPhi(2020);
                  nam4=tkdao.tkTongChiPhi(2021);
                  nam5=tkdao.tkTongChiPhi(2022);
        } catch (Exception e) {
            System.out.println("Cant lay Tong");
        }
        
        set1.setName("2018");
        set1.getData().add(new XYChart.Data<String, Number>("2018",nam1));
        
        XYChart.Series set2 = new XYChart.Series<String,Number>();
        set2.setName("2019");
        set2.getData().add(new XYChart.Data<String, Number>("2019",nam2));
        
        XYChart.Series set3 = new XYChart.Series<String,Number>();
        set3.setName("2020");
        set3.getData().add(new XYChart.Data<String, Number>("2020",nam3));
        
        XYChart.Series set4 = new XYChart.Series<String,Number>();
        set4.setName("2021");
        set4.getData().add(new XYChart.Data<String, Number>("2021",nam4));
        
        XYChart.Series set5 = new XYChart.Series<String,Number>();
        set5.setName("2022");
        set5.getData().add(new XYChart.Data<String, Number>("2022",nam5));
        
        cp.getData().add(set1);
        cp.getData().add(set2);
        cp.getData().add(set3);
        cp.getData().add(set4);
        cp.getData().add(set5);        
        cp.setLegendSide(Side.LEFT);
    }
    private void ChiPhiTB(BarChart cp)throws SQLException{
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        
        float nam1=0;
        float nam2=0;
        float nam3=0;
        float nam4=0;
        float nam5=0;
        tkdao = new ThongKeDAO();
        try {
                  nam1=tkdao.tkChiPhiTB(2018);
                  nam2=tkdao.tkChiPhiTB(2019);
                  nam3=tkdao.tkChiPhiTB(2020);
                  nam4=tkdao.tkChiPhiTB(2021);
                  nam5=tkdao.tkChiPhiTB(2022);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant lay TB");
        }
        
        set1.setName("2018");
        set1.getData().add(new XYChart.Data<String, Number>("2018",nam1));
        
        XYChart.Series set2 = new XYChart.Series<String,Number>();
        set2.setName("2019");
        set2.getData().add(new XYChart.Data<String, Number>("2019",nam2));
        
        XYChart.Series set3 = new XYChart.Series<String,Number>();
        set3.setName("2020");
        set3.getData().add(new XYChart.Data<String, Number>("2020",nam3));
        
        XYChart.Series set4 = new XYChart.Series<String,Number>();
        set4.setName("2021");
        set4.getData().add(new XYChart.Data<String, Number>("2021",nam4));
        
        XYChart.Series set5 = new XYChart.Series<String,Number>();
        set5.setName("2022");
        set5.getData().add(new XYChart.Data<String, Number>("2022",nam5));
        
        cp.getData().add(set1);
        cp.getData().add(set2);
        cp.getData().add(set3);
        cp.getData().add(set4);
        cp.getData().add(set5);        
        cp.setLegendSide(Side.LEFT);
    }
    
    private void HSTB(BarChart cp)throws SQLException{
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        
        float nam1=0;
        float nam2=0;
        float nam3=0;
        float nam4=0;
        float nam5=0;
        tkdao = new ThongKeDAO();
        try {
                  nam1=tkdao.tkHSTB(2018);
                  nam2=tkdao.tkHSTB(2019);
                  nam3=tkdao.tkHSTB(2020);
                  nam4=tkdao.tkHSTB(2021);
                  nam5=tkdao.tkHSTB(2022);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant lay HSTB");
        }
        
        set1.setName("2018");
        set1.getData().add(new XYChart.Data<String, Number>("2018",nam1));
        
        XYChart.Series set2 = new XYChart.Series<String,Number>();
        set2.setName("2019");
        set2.getData().add(new XYChart.Data<String, Number>("2019",nam2));
        
        XYChart.Series set3 = new XYChart.Series<String,Number>();
        set3.setName("2020");
        set3.getData().add(new XYChart.Data<String, Number>("2020",nam3));
        
        XYChart.Series set4 = new XYChart.Series<String,Number>();
        set4.setName("2021");
        set4.getData().add(new XYChart.Data<String, Number>("2021",nam4));
        
        XYChart.Series set5 = new XYChart.Series<String,Number>();
        set5.setName("2022");
        set5.getData().add(new XYChart.Data<String, Number>("2022",nam5));
        
        cp.getData().add(set1);
        cp.getData().add(set2);
        cp.getData().add(set3);
        cp.getData().add(set4);
        cp.getData().add(set5);        
        cp.setLegendSide(Side.LEFT);
    }
    private void TongChuyenDi(BarChart cp)throws SQLException{
        XYChart.Series set1 = new XYChart.Series<String,Number>();
        
        float nam1=0;
        float nam2=0;
        float nam3=0;
        float nam4=0;
        float nam5=0;
        tkdao = new ThongKeDAO();
        try {
                  nam1=tkdao.tkChuyenDi(2018);
                  nam2=tkdao.tkChuyenDi(2019);
                  nam3=tkdao.tkChuyenDi(2020);
                  nam4=tkdao.tkChuyenDi(2021);
                  nam5=tkdao.tkChuyenDi(2022);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cant lay Chuyen di");
        }
        
        set1.setName("2018");
        set1.getData().add(new XYChart.Data<String, Number>("2018",nam1));
        
        XYChart.Series set2 = new XYChart.Series<String,Number>();
        set2.setName("2019");
        set2.getData().add(new XYChart.Data<String, Number>("2019",nam2));
        
        XYChart.Series set3 = new XYChart.Series<String,Number>();
        set3.setName("2020");
        set3.getData().add(new XYChart.Data<String, Number>("2020",nam3));
        
        XYChart.Series set4 = new XYChart.Series<String,Number>();
        set4.setName("2021");
        set4.getData().add(new XYChart.Data<String, Number>("2021",nam4));
        
        XYChart.Series set5 = new XYChart.Series<String,Number>();
        set5.setName("2022");
        set5.getData().add(new XYChart.Data<String, Number>("2022",nam5));
        
        cp.getData().add(set1);
        cp.getData().add(set2);
        cp.getData().add(set3);
        cp.getData().add(set4);
        cp.getData().add(set5);        
        cp.setLegendSide(Side.LEFT);
    }
}
