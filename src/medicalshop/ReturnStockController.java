package medicalshop;

import com.sun.jnlp.ApiDialog;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


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
    @FXML
    private AnchorPane body;
    @FXML
    private Label total;
    
     
     int newid;
     int  qtyy;
    String cate, name, b,comp;
    float rt, amount, gstt, totamt;
    Date m, expiry;
    Timestamp ord;
    String a = "";
    crud cr = new crud();
    ObservableList<tblreorder> d = FXCollections.observableArrayList();
   
       
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
    private void btnReturn(ActionEvent event){
         String imagepath = "C:\\Users\\poojajungi\\Downloads\\delivery.png";
         String path ="C:\\Users\\poojajungi\\Downloads\\hand.png";
       ImageIcon icon = new ImageIcon(imagepath);
       ImageIcon icon2 = new ImageIcon(path);
       int  n = newtbl.getItems().size();
        if (n==0) {
            JOptionPane.showMessageDialog(null, "Please Select the Stocks.","Null Selection",JOptionPane.PLAIN_MESSAGE,icon2);
        }
       
        try {
            int res=0;
            for (int i = 0; i < n; i++) {
                newid = newtbl.getItems().get(i).mid;
                name = newtbl.getItems().get(i).mname;
                qtyy = newtbl.getItems().get(i).qty;
                rt = newtbl.getItems().get(i).rate;
                m = newtbl.getItems().get(i).mfg_date;
                expiry = newtbl.getItems().get(i).exp_date;
                b = newtbl.getItems().get(i).batch;
                comp = newtbl.getItems().get(i).company_name;
                amount = newtbl.getItems().get(i).amt;
                cate = newtbl.getItems().get(i).category;
                gstt = newtbl.getItems().get(i).gst;
                totamt = newtbl.getItems().get(i).tot_amt;
                ord = newtbl.getItems().get(i).order_date;
                                
             a = String.valueOf(newid) ;
          //   cr.returnAdd(name, qtyy, rt, m, expiry, b, comp, cate, amount, gstt, totamt, ord);
              if (cr.returnAdd(name, qtyy, rt, m, expiry, b, comp, cate, amount, gstt, totamt, ord)>0) {
                             res++;
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("vieworder.fxml"));
                            Parent root = loader.load();
                            VieworderController r = loader.getController();
                            r.remove(a);
                            newtbl.getItems().remove(i);
                            total.setText("-");
                } else {
                    JOptionPane.showMessageDialog(null, "Try Again","ReturnStock Message",JOptionPane.WARNING_MESSAGE);
                }
            }    
            if(res>0)
            {
                         JOptionPane.showMessageDialog(null, "Stock Return Successfully.","ReturnStock Message",JOptionPane.PLAIN_MESSAGE,icon);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"ReturnStock Message",JOptionPane.ERROR_MESSAGE);
        }
    }

  
    @FXML
    private void btnSelect(ActionEvent event) throws IOException {
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
