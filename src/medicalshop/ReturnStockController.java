/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ReturnStockController implements Initializable {

    Connection conn;
       ObservableList<tblreorder> listM = FXCollections.observableArrayList() ;
    @FXML
    private TableView<tblreorder> newtbl;
    @FXML
    private TableColumn<tblreorder, Integer> mid1;
    @FXML
    private TableColumn<tblreorder, String> mname1;
    @FXML
    private TableColumn<tblreorder, Integer> qty1;
    @FXML
    private TableColumn<tblreorder, Float> rate1;
    @FXML
    private TableColumn<tblreorder,Date> mfg_date1;
    @FXML
    private TableColumn<tblreorder,Date> exp_date1;
    @FXML
    private TableColumn<tblreorder, String> batch1;
    @FXML
    private TableColumn<tblreorder, String> company_name1;
    @FXML
    private TableColumn<tblreorder, Float> amt1;
    @FXML
    private TableColumn<tblreorder, String> category1;
    @FXML
    private TableColumn<tblreorder, Float> gst1;
    @FXML
    private TableColumn<tblreorder, Float> tot_amt1;
    @FXML
    private TableColumn<tblreorder, Timestamp> order_date1;
   VieworderController v = new VieworderController();
    @FXML
    private AnchorPane body;
    @FXML
    private Label total;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
   
            public void redisplayed(ObservableList<tblreorder> list)
       {
           
           newtbl.getItems().addAll(list);
        mid1.setCellValueFactory(new PropertyValueFactory<>("mid"));
        mname1.setCellValueFactory(new PropertyValueFactory<>("mname"));
        qty1.setCellValueFactory(new PropertyValueFactory<>("qty"));
         rate1.setCellValueFactory(new PropertyValueFactory<>("rate"));
         mfg_date1.setCellValueFactory(new PropertyValueFactory<>("mfg_date"));
        exp_date1.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
        batch1.setCellValueFactory(new PropertyValueFactory<>("batch"));
        company_name1.setCellValueFactory(new PropertyValueFactory<>("company_name"));
        amt1.setCellValueFactory(new PropertyValueFactory<>("amt"));
        category1.setCellValueFactory(new PropertyValueFactory<>("category"));
        gst1.setCellValueFactory(new PropertyValueFactory<>("gst"));
        tot_amt1.setCellValueFactory(new PropertyValueFactory<>("tot_amt"));
        order_date1.setCellValueFactory(new PropertyValueFactory<>("order_date"));
        total.setText(String.valueOf(newtbl.getItems().size()));
       }

    @FXML
    private void btnReturn(ActionEvent event) throws IOException {
        body.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("vieworder.fxml"));
             Parent root = loader.load();
             VieworderController r = loader.getController();
              Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        
    }
            
}
