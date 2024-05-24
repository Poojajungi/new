package medicalshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class StockController implements Initializable {

    @FXML
    private ListView<String> listview;
    Connection conn;

    private TextField search;
    
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private Label qtyText;
    @FXML
    private TableView<tbldata> tbl;
    @FXML
    private TableColumn<tbldata, Integer> mid;
    @FXML
    private TableColumn<tbldata, String> mname;
    @FXML
    private TableColumn<tbldata, Integer> qty;
    @FXML
    private TableColumn<tbldata, Float> rate;
    @FXML
    private TableColumn<tbldata, Date> mfg_date;
    @FXML
    private TableColumn<tbldata, Date> exp_date;
    @FXML
    private TableColumn<tbldata, String> batch;
    @FXML
    private TableColumn<tbldata, String> company_name;
    @FXML
    private TableColumn<tbldata, Float> amt;
    @FXML
    private TableColumn<tbldata, String> category;
    @FXML
    private TableColumn<tbldata, Float> gst;
    @FXML
    private TableColumn<tbldata, Float> tot_amt;
   
    ObservableList<tbldata> listM = FXCollections.observableArrayList();
    @FXML
    private TableColumn<String, Button> reorder;
    @FXML
    private TextField nm;
    @FXML
    private TextField Qt;
    @FXML
    private TextField rat;
    @FXML
    private DatePicker mf;
    @FXML
    private DatePicker ex;
    @FXML
    private TextField batc;
    @FXML
    private TextField amot;
    @FXML
    private TextField gs;
    @FXML
    private TextField tamot;
    @FXML
    private Label title;
    @FXML
    private TextField id;
    @FXML
    private TextField company;
    @FXML
    private TextField cat;
    @FXML
    private TextField searchText;
    @FXML
    private Label reorderCount;
    
    crud cr = new crud();
    MedicinesController mc = new MedicinesController();
     int newid, qtyy;
    String cate, name, b,comp;
    float rt, amount, gstt, totamt;
    Date m, expiry;
    @FXML
    private AnchorPane panel;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            display();
            load();
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
        
        public void display()
        {
                try {
                   conn =  connect();
                    PreparedStatement pst = conn.prepareStatement("select mname from meditbl");
                    ResultSet r = pst.executeQuery();
                    while (r.next()) {                        
                        list.add(r.getString("mname"));
                    }
                    listview.setItems(list);
                    listview.requestLayout();
            } catch (Exception e) {
                    System.out.println(e);
            }
        }
        
      public void inform() {
            newid = Integer.parseInt(id.getText());
            name = nm.getText();
            qtyy = Integer.parseInt(Qt.getText());
            rt = Float.parseFloat(rat.getText());
            m = Date.valueOf(mf.getValue());
            expiry = Date.valueOf(ex.getValue());
            b = batc.getText();
            comp=company.getText();
            amount = Float.parseFloat(amot.getText());
            cate = cat.getText();
            gstt = Float.parseFloat(gs.getText());
            totamt = Float.parseFloat(tamot.getText());
    }
          
        String data,test;
        int c = 0;
        public boolean Redisplay()
        {
                try {
                   conn =  connect();
                    PreparedStatement pst = conn.prepareStatement("select mname from reordertbl where mname like '%"+test+"%'");
                    c=0;
                    ResultSet r = pst.executeQuery();
                    while (r.next()) {                        
                      data = r.getString("mname") + " ";
                       c++;
                    }
                    return true;
            } catch (Exception e) {
                    System.out.println(e);
                    return false;
            }
        }

         public void clear()
    {
            id.setText(null);
            nm.setText(null);
            Qt.setText(null);
            rat.setText(null);
            mf.setValue(null);
            ex.setValue(null);
            batc.setText(null);
            company.setText(null);
            cat.setText(null);
            gs.setText(null);
            amot.setText(null);
            tamot.setText(null);
    }
         
    @FXML
    private void TextKey(KeyEvent event) {
        if (!searchText.getText().isEmpty()) {
            conn = connect();
             String s = searchText.getText();
            if (Character.isAlphabetic(s.charAt(0))) {
                try {
                    list.clear();
                     PreparedStatement pst = conn.prepareStatement("select mname from meditbl where mname  like '%"+s+"%'");
                     ResultSet r = pst.executeQuery();
                     while (r.next()) {                        
                        list.add(r.getString("mname"));
                    }
                    listview.setItems(list);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
       
    }

    @FXML
    private void listviewMouseClick(MouseEvent event) {
         test = listview.getSelectionModel().getSelectedItem();
        try {
            PreparedStatement pst = conn.prepareStatement("select qty from meditbl where mname like '%"+test+"%'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {                
                qtyText.setText(String.valueOf(r.getInt("qty")));
            }
      
                    if (Redisplay()==true) {
                        reorderCount.setText(String.valueOf(c));
                    } else  {
                      reorderCount.setText("0");
                    }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"quantity",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnDetails(ActionEvent event) {
        tbl.setVisible(true);
        String test = listview.getSelectionModel().getSelectedItem();
        listM.clear();
      
        try {
            PreparedStatement pst = conn.prepareStatement("select * from meditbl where mname like '%"+test+"%'");
            ResultSet r = pst.executeQuery();
            while (r.next()) {                
               listM.add(new tbldata(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt")));
                tbl.setItems(listM);
            }
            listview.getSelectionModel().clearSelection();
          //  c=0;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"See more details ",JOptionPane.ERROR_MESSAGE);
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
                
                reorder.setCellFactory(col -> new TableCell<String,Button>(){
                      @Override
                    protected void updateItem(Button button, boolean empty) {
                        super.updateItem(button, empty);
                        
                          if (empty) {
                              setGraphic(null);
                          } else {
                              Button reorderbtn = new Button("Reorder");
                              reorderbtn.setPrefSize(80,15);
                              reorderbtn.setId("btnReorder");
                              DropShadow ds = new DropShadow();
                              reorderbtn.setEffect(ds);
                              setGraphic(reorderbtn);
                              reorderbtn.setOnAction(event -> {
                                  panel.setVisible(true);
                                  id.setText(mid.getCellData(0).toString());
                                 nm.setText(mname.getCellData(0));
                                 Qt.setText(qty.getCellData(0).toString());
                                 rat.setText(rate.getCellData(0).toString());
                                 mf.setValue(mfg_date.getCellData(0).toLocalDate());
                                 ex.setValue(exp_date.getCellData(0).toLocalDate());
                                 batc.setText(batch.getCellData(0));
                                 company.setText(company_name.getCellData(0));
                                 amot.setText(amt.getCellData(0).toString());
                                 cat.setText(category.getCellData(0));
                                 gs.setText(gst.getCellData(0).toString());
                                 tamot.setText(tot_amt.getCellData(0).toString());
                              });
                          }
                    }
                });
    }

    @FXML
    private void multiply(KeyEvent event) {
         try {
            if (!Qt.getText().isEmpty() && !rat.getText().isEmpty()) {

                int a = Integer.parseInt(Qt.getText());
                float b = Float.parseFloat(rat.getText());
                amot.setText(String.valueOf(a * b));
                float at = Float.parseFloat(amot.getText());
                gs.setText(String.valueOf((at * 15) / 100));
                float gss = Float.parseFloat(gs.getText());
                tamot.setText(String.valueOf(at + gss));
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    @FXML
    private void btnnew(ActionEvent event) {
            try {
                  String imagepath = "C:\\Users\\poojajungi\\Downloads\\check.png";
                  ImageIcon  icon = new ImageIcon(imagepath);
                  inform();
                if (cr.reOrderAdd( name, qtyy, rt, m, expiry, b, comp, cate, amount, gstt, totamt)>0) {
                    JOptionPane.showMessageDialog(null, "Reorder Successfully.", "Reorder ", JOptionPane.PLAIN_MESSAGE,icon);
                            try {
                              conn =  connect();
                               PreparedStatement pst = conn.prepareStatement("select mname from reordertbl where mname like '%"+name+"%' ");
                               c=0;
                               ResultSet r = pst.executeQuery();
                               while (r.next()) {                        
                                 data = r.getString("mname") + " ";
                                  c++;
                               }
                               reorderCount.setText(String.valueOf(c));
                               listview.getSelectionModel().clearSelection();
                       } catch (Exception e) {
                               System.out.println(e);
                       }
                    clear();
                    listM.clear();
                } else {
                    JOptionPane.showMessageDialog(null, "Try Again.","Reorder",JOptionPane.WARNING_MESSAGE);
                }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e,"Reorder",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
       clear();
    }

     
}
