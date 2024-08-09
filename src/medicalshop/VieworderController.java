
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
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class VieworderController implements Initializable {

    @FXML
    public TableView<tblreorder> tbl;
    @FXML
    private TableColumn<tblreorder, Integer> mid;
    @FXML
    private TableColumn<tblreorder, String> mname;
    @FXML
    private TableColumn<tblreorder, Integer> qty;
    @FXML
    private TableColumn<tblreorder, Float> rate;
    @FXML
    private TableColumn<tblreorder,Date> mfg_date;
    @FXML
    private TableColumn<tblreorder,Date> exp_date;
    @FXML
    private TableColumn<tblreorder, String> batch;
    @FXML
    private TableColumn<tblreorder, String> company_name;
    @FXML
    private TableColumn<tblreorder, Float> amt;
    @FXML
    private TableColumn<tblreorder, String> category;
    @FXML
    private TableColumn<tblreorder, Float> gst;
    @FXML
    private TableColumn<tblreorder, Float> tot_amt;
      @FXML
    private TableColumn<tblreorder, Timestamp> order_date;
  
      crud cr = new crud();
        Connection conn;
       ObservableList<tblreorder> listM = FXCollections.observableArrayList() ;
     static ObservableList<tblreorder> da = FXCollections.observableArrayList();
     AnchorPane apn = new AnchorPane();
    @FXML
    private AnchorPane Viewbody;
    @FXML
    private Button btnsubmit;
       
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tbl.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        display();
        load();
         tbl.getSelectionModel().clearSelection();
    }    

    public Connection connect()
        {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store","root","");
            } catch (Exception e) {
                    System.out.println(e);
            }
                 return conn;
        }
    
      public void display() {
        try {
            conn = connect();
            listM.clear();
            PreparedStatement pst = conn.prepareStatement("select *from reordertbl");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tblreorder(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt"),r.getTimestamp("order_date")));
                tbl.setItems(listM);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
      
       public void load()
    {
                
                mid.setCellValueFactory(new PropertyValueFactory<>("mid"));
                mname.setCellValueFactory(new PropertyValueFactory<>("mname"));
                qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                rate.setCellValueFactory(new PropertyValueFactory<>("rate"));
                mfg_date.setCellValueFactory(new PropertyValueFactory<>("mfg_date"));
                exp_date.setCellValueFactory(new PropertyValueFactory<>("exp_date"));
                batch.setCellValueFactory(new PropertyValueFactory<>("batch"));
                company_name.setCellValueFactory(new PropertyValueFactory<>("company_name"));
                amt.setCellValueFactory(new PropertyValueFactory<>("amt"));
                category.setCellValueFactory(new PropertyValueFactory<>("category"));
                gst.setCellValueFactory(new PropertyValueFactory<>("gst"));
                tot_amt.setCellValueFactory(new PropertyValueFactory<>("tot_amt"));
                order_date.setCellValueFactory(new PropertyValueFactory<>("order_date"));
    }
       public void remove(String b)
       {
           int p = Integer.parseInt(b);
           try {
                if (cr.reOrderDelete(p)>0) {

                } else {
                    System.out.println("Try Again");
              }
           } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e);
           }
       }
      

    @FXML
    private void btnReturn(ActionEvent event) {
       String imagepath = "C:\\Users\\poojajungi\\Downloads\\hand.png";
       ImageIcon icon = new ImageIcon(imagepath);
        if (tbl.getSelectionModel().getSelectedItems().size()==0) {
            JOptionPane.showMessageDialog(null, "Please Select the Items.","Null Selection",JOptionPane.PLAIN_MESSAGE,icon);
        }
        else{
        try{
            da = tbl.getSelectionModel().getSelectedItems();
            Viewbody.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Dashboard.fxml"));
             Parent root = loader.load();
             DashboardController d = loader.getController();
             FXMLLoader loading = new FXMLLoader(getClass().getResource("returnStock.fxml"));
             Parent returnContent = loading.load();
            ReturnStockController  r = loading.getController();
         
             if (r == null) {
                System.out.println("null");
            } else {
                r.redisplayed(da);
              }
    
             apn.getChildren().add(returnContent);
            
            apn.prefHeightProperty().bind(d.pn2.heightProperty());
            d.pn2.setContent(apn);
           //  apn.prefHeightProperty().bind(Viewbody.heightProperty());
       //     apn.setPrefHeight(Double.MAX_VALUE);
            //apn.prefWidthProperty().bind(d.pn2.widthProperty());
          //  apn.prefHeight(d.pn2.getHeight());
//            apn.maxHeightProperty().bind(d.pn2.heightProperty());
//            apn.minHeightProperty().bind(d.pn2.heightProperty());
//            
//              AnchorPane.setTopAnchor(apn, 0.0);
//            AnchorPane.setLeftAnchor(apn , 0.0);
//            AnchorPane.setRightAnchor(apn, 0.0);
//            AnchorPane.setBottomAnchor(apn, 0.0);
             
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("dashboard_design.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMaximized(true);
            primaryStage.show();
        }catch(Exception e)
        {
            System.out.println(e);
        }
    }
    }

    
    
}
