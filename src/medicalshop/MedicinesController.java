package medicalshop;

import com.mysql.jdbc.MySQLConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javax.swing.JOptionPane;

public class MedicinesController implements Initializable {



    @FXML
    private AnchorPane body;
    @FXML
    private TableView<tbldata> tbl;

    @FXML
    private TextField id;
    @FXML
    private TextField nm;
    @FXML
    private TextField Qt;
    @FXML
    private TextField rat;
    @FXML
    private TextField amot;
    @FXML
    private TextField gs;
    @FXML
    private TextField tamot;
    @FXML
    private DatePicker mf;
    @FXML
    private DatePicker ex;
    @FXML
    private TextField cat;
    @FXML
    private TextField batc;

    crud cr = new crud();
    ObservableList<tbldata> listM = FXCollections.observableArrayList();
    int index = -1;
    Connection conn = null;
    ResultSet r = null;
    PreparedStatement pst = null;
    tbldata tbldata = null;
    @FXML
    private TableColumn<String, Button> action;
    
  

    int c;
    int newid, qtyy;
    String cate, name, b,comp;
    float rt, amount, gstt, totamt;
    Date m, expiry;
    @FXML
    private ImageView reload;
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
    private TableColumn<tbldata, Float> amt;
    @FXML
    private TableColumn<tbldata, String> batch;
    @FXML
    private TableColumn<tbldata, Float> gst;
    @FXML
    private TableColumn<tbldata, Float> tot_amt;
    @FXML
    private TableColumn<tbldata, String> category;
    @FXML
    private Button search;
    @FXML
    private TextField searchText;
    @FXML
    private TableColumn<String, Button> editcol;
    @FXML
    private Label title;
    @FXML
    private TableColumn<tbldata, String> company_name;
    @FXML
    private TextField company;
  
    
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
            searchText.setText(null);
            tamot.setText(null);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        conn = connect();
        display();
        load();

    }

    @FXML
    private void btnnew(ActionEvent event) {
        inform();
        try {
            if (cr.insertdata(newid, name, qtyy, rt, m, expiry, b,comp, cate, amount, gstt, totamt) > 0) {
                JOptionPane.showMessageDialog(null, "Medicines Added Successfully", "Adding ", JOptionPane.INFORMATION_MESSAGE);
                display();
                clear();
            } else {
                JOptionPane.showMessageDialog(null, "Try Again.","adding",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"adding",JOptionPane.ERROR_MESSAGE);
        }
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
            JOptionPane.showMessageDialog(null, ex,"Total",JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Total",JOptionPane.ERROR_MESSAGE);
        }

    }

    @FXML
    private void click(MouseEvent event) {
        id.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getMid()));
        nm.setText(tbl.getSelectionModel().getSelectedItem().getMname());
        Qt.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getQty()));
        rat.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getRate()));
        mf.setValue(tbl.getSelectionModel().getSelectedItem().getMfg_date().toLocalDate());
        ex.setValue(tbl.getSelectionModel().getSelectedItem().getExp_date().toLocalDate());
        batc.setText(tbl.getSelectionModel().getSelectedItem().getBatch());
        company_name.setText(tbl.getSelectionModel().getSelectedItem().getCompany_name());
        amot.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getAmt()));
        cat.setText(tbl.getSelectionModel().getSelectedItem().getCategory());
        gs.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getGst()));
        tamot.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getTot_amt()));
        
        c=tbl.getSelectionModel().getSelectedItem().getMid();
    }

    
    public void display() {
        try {
            conn = connect();
            listM.clear();
            PreparedStatement pst = conn.prepareStatement("select *from meditbl");
             r = pst.executeQuery();
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medical_store", "root", "");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Connection Alert",JOptionPane.WARNING_MESSAGE);
        }
        return conn;
    }


    @FXML
    private void btnRefresh(MouseEvent event) {
        clear();
        display();
    }

    @FXML
    private void btnSearch(ActionEvent event) {
        String s = searchText.getText();
        try {
            conn = connect();
            listM.clear();
           
            PreparedStatement pst = null;
            if (Character.isAlphabetic(s.charAt(0)) || Character.isAlphabetic(4)) {
                pst = conn.prepareStatement("select *from meditbl where mname like '%" + s + "%'  or mfg_date like '%" + s + "%'  or exp_date like '%" + s + "%' or batch like '%" + s + "%' or company_name like '%" + s + "%' or   category like '%" + s + "%' ");
            } else if (Character.isDigit(s.charAt(0))) {
                pst = conn.prepareStatement("select *from meditbl where mid=" + s + " or qty=" + s + " or rate=" + s + "  or amt=" + s + "  or gst=" + s + " or tot_amt=" + s);
            }

             r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tbldata(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"),r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt")));
                tbl.setItems(listM);
                 load();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Search Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void btnFirst(ActionEvent event) {
         try {
            listM.clear();
            pst = conn.prepareStatement("select *from meditbl where mid=1");
              r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tbldata(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt")));
                tbl.setItems(listM);
                load();
               
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e,"First Alert",JOptionPane.WARNING_MESSAGE);
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
                action.setCellFactory(col -> new TableCell<String, Button>() {
                    @Override
                    protected void updateItem(Button button, boolean empty) {
                        super.updateItem(button, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            Button deleteButton = new Button();
                            deleteButton.setId("btn");
                            deleteButton.setPrefWidth(27);
                            setGraphic(deleteButton);
                            deleteButton.setOnAction(event -> {
                                  int n = getIndex();
                                    try{
                                            if (cr.deletedata(n+1)>0) {
                                                JOptionPane.showMessageDialog(null, "Medicine Deleted SuccessFully","Medicines Deletion",JOptionPane.INFORMATION_MESSAGE);
                                                display();
                                                clear();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Try Again.","Medicines Deletion",JOptionPane.WARNING_MESSAGE);
                                        }
                                    }catch(Exception e)
                                    {
                                        JOptionPane.showMessageDialog(null, e);
                                    }
                                    
                            });
                        }
                    }
                });
                editcol.setCellFactory(col -> new TableCell<String, Button>() {
                    @Override
                    protected void updateItem(Button button, boolean empty) {
                        super.updateItem(button, empty);

                        if (empty) {
                            setGraphic(null);
                        } else {
                            Button editButton = new Button();
                            editButton.setId("btnedit");
                            editButton.setPrefWidth(30);
                            setGraphic(editButton);
                            editButton.setOnAction(event -> {
                                 inform();
                                    try{
                                            if (cr.updatedata(newid, name, qtyy, rt, m, expiry, b,comp, cate, amount, gstt, totamt) >0) {
                                                JOptionPane.showMessageDialog(null, "Changes are saved SuccessFully","Medicine Changes",JOptionPane.INFORMATION_MESSAGE);
                                                display();
                                                clear();
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Try Again.","Medicine Changes",JOptionPane.WARNING_MESSAGE);
                                        }
                                    }catch(Exception e)
                                    {
                                        JOptionPane.showMessageDialog(null, e);
                                    }
                                    display();
                            });
                        }
                    }
                });
               
    }

    @FXML
    private void btnLast(ActionEvent event) {
            try {
             int n = listM.size();
            listM.clear();
            pst = conn.prepareStatement("select *from meditbl where mid="+n);
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
                load();
            }
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e,"Last Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void btnNext(ActionEvent event) {
        try {
            listM.clear();
            pst = conn.prepareStatement("select *from meditbl where mid="+(c+1));
            r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tbldata(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt")));
                
                tbl.setItems(listM);
                load();
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Next Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void btnPrev(ActionEvent event) {
        try {
            listM.clear();
            pst = conn.prepareStatement("select *from meditbl where mid="+(c-1));
            r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tbldata(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt")));
                        
                tbl.setItems(listM);
                load();
            }
        
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Prev Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    private void btnExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void btnSearch(MouseEvent event) {
        String s = searchText.getText();
        try {
            conn = connect();
            listM.clear();
            
//            System.out.println("select *from meditbl where mid=" + s + " or mname='" + s + "' or qty=" + s + " or rate=" + s + " or mfg_date=" + s + " or exp_date=" + s + " or batch='" + s + "' or amt=" + s + " or category='" + s + "' or gst=" + s + " or tot_amt=" + s);
            PreparedStatement pst = null;
            if (Character.isAlphabetic(s.charAt(0)) || Character.isAlphabetic(4)) {
                pst = conn.prepareStatement("select *from meditbl where mname like '%" + s + "%'  or mfg_date like '%" + s + "%'  or exp_date like '%" + s + "%' or batch like '%" + s + "%' or category like '%" + s + "%' ");
            } else if (Character.isDigit(s.charAt(0))) {
                pst = conn.prepareStatement("select *from meditbl where mid=" + s + " or qty=" + s + " or rate=" + s + "  or amt=" + s + "  or gst=" + s + " or tot_amt=" + s);
            }
        //    JOptionPane.showMessageDialog(null, pst);

             r = pst.executeQuery();
            while (r.next()) {
                listM.add(new tbldata(
                        r.getInt("mid"), r.getString("mname"),
                        r.getInt("qty"), r.getFloat("rate"),
                        r.getDate("mfg_date"), r.getDate("exp_date"),
                        r.getString("batch"), r.getString("company_name"), r.getFloat("amt"),
                        r.getString("category"), r.getFloat("gst"),
                        r.getFloat("tot_amt")));
                tbl.setItems(listM);
                 load();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Search Alert",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        clear();
    }
     
}
