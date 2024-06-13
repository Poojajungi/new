package medicalshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ViewReturnController implements Initializable {

     @FXML
    private AnchorPane body;
    @FXML
    private TableView<tblreturn>tbl;
    @FXML
    private TableColumn<tblreturn,Integer> mid;
    @FXML
    private TableColumn<tblreturn,String> mname;
    @FXML
    private TableColumn<tblreturn,Integer> qty;
    @FXML
    private TableColumn<tblreturn,Float> rate;
    @FXML
    private TableColumn<tblreturn,Date> mfg_date;
    @FXML
    private TableColumn<tblreturn,Date> exp_date;
    @FXML
    private TableColumn<tblreturn,String> batch;
    @FXML
    private TableColumn<tblreturn,String> company_name;
    @FXML
    private TableColumn<tblreturn,Float> amt;
    @FXML
    private TableColumn<tblreturn,String> category;
    @FXML
    private TableColumn<tblreturn,Float> gst;
    @FXML
    private TableColumn<tblreturn,Float> tot_amt;
    @FXML
    private TableColumn<tblreorder,Timestamp> order_date;
    @FXML
    private TableColumn<tblreorder,Timestamp> return_date;
    @FXML
    private Label total;
    crud cr = new crud();
    Connection conn;
    ObservableList<tblreturn> listM = FXCollections.observableArrayList();
   ObservableList<tblreturn> data = FXCollections.observableArrayList();
   
    ArrayList id = new ArrayList();
    ArrayList name = new ArrayList();
    ArrayList bat = new ArrayList();
     ArrayList comp = new ArrayList();
     ArrayList cate = new ArrayList();
     ArrayList rat = new ArrayList();
     ArrayList gs = new ArrayList();
     ArrayList am = new ArrayList();
     ArrayList tam = new ArrayList();
     ArrayList qt = new ArrayList();
     ArrayList or = new ArrayList();
     ArrayList retu = new ArrayList();
     ArrayList mfg = new ArrayList();
     ArrayList exp = new ArrayList();
     
    String nm,b,com,cat;
    float t,a,g,r;
   int q,idd;
   Timestamp order,retun;
   Date mfgd,expd;
    int res = 0,ty = 0;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();
        load();
        total.setText(String.valueOf(tbl.getItems().size()));
        tbl.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
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
    
     public void inform(int i)
     {
            idd = Integer.parseInt(id.get(i).toString());
            nm = name.get(i).toString();
            q =Integer.parseInt(qt.get(i).toString());
            r = Float.parseFloat(rat.get(i).toString());
            mfgd = Date.valueOf(mfg.get(i).toString());
            expd = Date.valueOf(exp.get(i).toString());
            b = bat.get(i).toString();
            com = comp.get(i).toString();
             a = Float.parseFloat(am.get(i).toString());
             cat = cate.get(i).toString();
             g = Float.parseFloat(gs.get(i).toString());
             t = Float.parseFloat(tam.get(i).toString());
             order = Timestamp.valueOf(or.get(i).toString());
             retun = Timestamp.valueOf(retu.get(i).toString());
     }
      public void selected()
      {
          try {
              String path = "E:\\javaprograms\\medicalshop\\src\\images\\stockicon.png";
              ImageIcon icon = new ImageIcon(path);
              int n = name.size();
             for (int i = 0; i < n; i++) {
                inform(i);
                if (cr.CompnayReturnStock(nm, q, r, mfgd, expd, b, com, cat, a, g, t, order, retun)>0) {
                  res++;
                  
              } 
          }
              if (res>0) {
                   JOptionPane.showMessageDialog(null, "Company Successfully Received the Stock","ReturnStock Messages",JOptionPane.PLAIN_MESSAGE,icon);
                   removeItems(n);
                   display();
                   total.setText(String.valueOf(tbl.getItems().size()));
                   tbl.getSelectionModel().clearSelection();
              }
              else{
                   JOptionPane.showMessageDialog(null, "Try Again","ReturnStock Messages",JOptionPane.WARNING_MESSAGE);
              }
          } catch (Exception e) {
               JOptionPane.showMessageDialog(null, e,"ReturnStock Messages",JOptionPane.ERROR_MESSAGE);
          }
         
      }
      public void removeItems(int n)
      {
          try {
               for (int i = 0; i < n; i++) {
              inform(i);
                if (cr.ReturnStockDelete(idd)>0) {
              } else {
                    System.out.println("Delete is not Done.");
              }
          }
          } catch (Exception e) {
              System.out.println(e);
          }
         
      }
      public void display() {
        try {
            conn = connect();
            listM.clear();
            PreparedStatement pst = conn.prepareStatement("select *from returnstock");
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tblreturn(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt"),r.getTimestamp("order_date"),r.getTimestamp("return_date")));
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
                return_date.setCellValueFactory(new PropertyValueFactory<>("return_date"));
    }

    @FXML
    private void btnDetails(ActionEvent event) {
        String imagepath = "C:\\Users\\poojajungi\\Downloads\\hand.png";
       ImageIcon icon = new ImageIcon(imagepath);
        if (tbl.getSelectionModel().getSelectedItems().size()==0) {
            JOptionPane.showMessageDialog(null, "Please Select the Items.","Null Selection",JOptionPane.PLAIN_MESSAGE,icon);
        }
        else{
            try {
                data = tbl.getSelectionModel().getSelectedItems();
                
                    selected();

            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    @FXML
    private void mouseClick(MouseEvent event) {
        id.add(tbl.getSelectionModel().getSelectedItem().getMid());
         name.add(tbl.getSelectionModel().getSelectedItem().getMname());
         bat.add(tbl.getSelectionModel().getSelectedItem().getBatch());
         comp.add(tbl.getSelectionModel().getSelectedItem().getCompany_name());
         cate.add(tbl.getSelectionModel().getSelectedItem().getCategory());
         qt.add(tbl.getSelectionModel().getSelectedItem().getQty());
         gs.add(tbl.getSelectionModel().getSelectedItem().getGst());
         rat.add(tbl.getSelectionModel().getSelectedItem().getRate());
         am.add(tbl.getSelectionModel().getSelectedItem().getAmt());
         tam.add(tbl.getSelectionModel().getSelectedItem().getTot_amt());
         mfg.add(tbl.getSelectionModel().getSelectedItem().getMfg_date());
         exp.add(tbl.getSelectionModel().getSelectedItem().getExp_date());
         or.add(tbl.getSelectionModel().getSelectedItem().getOrder_date());
         retu.add(tbl.getSelectionModel().getSelectedItem().getReturn_date());
    }
}
