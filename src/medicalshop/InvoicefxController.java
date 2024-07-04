/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package medicalshop;

import java.awt.Component;
import java.awt.Frame;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;
import java.util.Random;
import java.math.*;

/**
 * FXML Controller class
 *
 * @author mahek
 */
public class InvoicefxController extends Frame implements Initializable {

    @FXML
    private TextField txtinvoiceno;
    @FXML
    private TextField txtcustomernm;
    @FXML
    private TextField txtnameofitem;
    @FXML
    private TextField txtquantity;
    @FXML
    private TextField txtrate;
    @FXML
    private TextField txtamount;
    @FXML
    private DatePicker mfgdate;
    @FXML
    private DatePicker expiredate;
    @FXML
    private TextField txtbatchno;
    @FXML
    private TextField txtmobileno;
    @FXML
    private TextField txtdiscountno;
    @FXML
    private TextField txtgst;
    @FXML
    private TextField txttotalamount;
    @FXML
    private Button btnfirst;
    @FXML
    private Button btnlast;
    @FXML
    private Button btnnext;
    @FXML
    private Button btnprev;

    /**
     * Initializes the controller class.
     */
    static int no;
    Connection c;
    int i, q, dis;
    Date d, ed;
    String cm, im, b, m, p;
    static float total_amount = 0;
    private static JasperReport jr;
    private static JasperPrint jp;
    static String NO;

    float total, r, a, g;
    @FXML
    private TextField txtsearch;
    @FXML
    private Button btnsearch;

    @FXML
    private Button btncancel;
    @FXML
    private ImageView ref;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnsave1;

    @FXML
    private AnchorPane invoice_anchorpane;
    @FXML
    private TableView<medicine_variable> tableview;
    @FXML
    private TableColumn<medicine_variable, String> nm;
    @FXML
    private TableColumn<medicine_variable, Integer> qty;
    @FXML
    private TableColumn<medicine_variable, Float> rate;
    @FXML
    private TableColumn<medicine_variable, Float> amount;
    @FXML
    private TableColumn<medicine_variable, Date> date;
    @FXML
    private TableColumn<medicine_variable, Date> expiredate1;
    @FXML
    private TableColumn<medicine_variable, Integer> ino;
    @FXML
    private RadioButton p_case;
    @FXML
    private ToggleGroup pay;
    @FXML
    private RadioButton p_online;
    @FXML
    private Button print;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        getdatelist();
        display();
        
        invoiceno();
        txtinvoiceno.setText(NO);
    }

        public void invoiceno()
        {
            String[] number={"0","1","2","3","4","5","6","7","8","9"};
            Random random=new Random();
            int randomindex1=random.nextInt(number.length);
            String randomelement1 =number[randomindex1];
             int randomindex2=random.nextInt(number.length);
            String randomelement2 =number[randomindex2];
            int randomindex3=random.nextInt(number.length);
            String randomelement3 =number[randomindex3];
             int randomindex4=random.nextInt(number.length);
            String randomelement4 =number[randomindex4];
             int randomindex5=random.nextInt(number.length);
            String randomelement5 =number[randomindex5];
          
           NO=randomelement1+randomelement2+randomelement3+randomelement4+randomelement5;
           // System.out.println(NO);
            
//            
//        double n1 =number[Math.floor(Math.random())*62];
        }
    public void clear() {
        txtinvoiceno.setText(null);
        txtinvoiceno.setEditable(true);
        txtcustomernm.setText(null);
        txtcustomernm.setEditable(true);
        txtnameofitem.setText(null);
        txtquantity.setText(null);
        txtrate.setText(null);
        txtamount.setText(null);
        mfgdate.setValue(null);
        expiredate.setValue(null);
        txtbatchno.setText(null);
        txtmobileno.setText(null);
        txtmobileno.setEditable(true);
        txtdiscountno.setText(null);
        txtgst.setText(null);
        txttotalamount.setText(null);
        total_amount = 0;
    }

    //display tableview
    ObservableList<medicine_variable> getdatelist() {
        ObservableList<medicine_variable> medicineslist = FXCollections.observableArrayList();

        try {
            int i = InvoicefxController.no;
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jtb", "root", "");
            PreparedStatement p = c.prepareStatement("select *from medicines");
            ResultSet rs = p.executeQuery();
            medicine_variable m;

            while (rs.next()) {
                m = new medicine_variable(rs.getString("name_of_item"), rs.getInt("qty"), rs.getFloat("rate"), rs.getFloat("amount"), rs.getDate("mfg_date"), rs.getDate("Expire_date"), rs.getInt("invoice_no"));
                medicineslist.add(m);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return medicineslist;

    }

    public void display() {
        try {
            ObservableList<medicine_variable> list = getdatelist();

            nm.setCellValueFactory(new PropertyValueFactory<medicine_variable, String>("name_of_item"));
            qty.setCellValueFactory(new PropertyValueFactory<medicine_variable, Integer>("qty"));
            rate.setCellValueFactory(new PropertyValueFactory<medicine_variable, Float>("rate"));
            amount.setCellValueFactory(new PropertyValueFactory<medicine_variable, Float>("amount"));
            date.setCellValueFactory(new PropertyValueFactory<medicine_variable, Date>("date"));
            expiredate1.setCellValueFactory(new PropertyValueFactory<medicine_variable, Date>("edate"));
            ino.setCellValueFactory(new PropertyValueFactory<medicine_variable, Integer>("invoice_no"));

            tableview.setItems(list);
        } catch (Exception e) {
            System.out.println(e + "not display");
        }
    }

    ObservableList<medicine_variable> getdatasearch() {
        ObservableList<medicine_variable> invoicesearch = FXCollections.observableArrayList();
        String se = txtsearch.getText();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jtb", "root", "");
            PreparedStatement p = null;
            if (Character.isAlphabetic(se.charAt(0)) || Character.isAlphabetic(4)) {
                p = c.prepareStatement("select *from medicines where name_of_item like '%" + se + "%'or batch_no like'%" + se + "%'");
// or nmi like '%" + se + "%' or   blno like '%" + se + "%'");
            } else if (Character.isDigit(se.charAt(0))) {
                p = c.prepareStatement("select *from medicines where invoice_no=  " + se);
//                          + "or qty="+se+" or rate="+se+" or amo="+se+"or mno="+se+" or drs="+se+" or gst="+se+" or tam="+se); 
            }
            ResultSet rs = p.executeQuery();

            medicine_variable m;

            while (rs.next()) {
                m = new medicine_variable(rs.getString("name_of_item"), rs.getInt("qty"), rs.getFloat("rate"), rs.getFloat("amount"), rs.getDate("mfg_date"), rs.getDate("Expire_date"), rs.getInt("invoice_no"));
                invoicesearch.add(m);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return invoicesearch;
    }

    public void search() {
        try {
            ObservableList<medicine_variable> l = getdatasearch();
            nm.setCellValueFactory(new PropertyValueFactory<medicine_variable, String>("name_of_item"));
            qty.setCellValueFactory(new PropertyValueFactory<medicine_variable, Integer>("qty"));
            rate.setCellValueFactory(new PropertyValueFactory<medicine_variable, Float>("rate"));
            amount.setCellValueFactory(new PropertyValueFactory<medicine_variable, Float>("amount"));
            date.setCellValueFactory(new PropertyValueFactory<medicine_variable, Date>("date"));
            expiredate1.setCellValueFactory(new PropertyValueFactory<medicine_variable, Date>("edate"));
            ino.setCellValueFactory(new PropertyValueFactory<medicine_variable, Integer>("invoice_no"));

            tableview.setItems(l);
        } catch (Exception e) {
            System.out.println(e + "not display");
        }

    }

    @FXML
    private void btnsave(ActionEvent event) {
        i = Integer.parseInt(txtinvoiceno.getText());
        cm = txtcustomernm.getText();
        im = txtnameofitem.getText();
        q = Integer.parseInt(txtquantity.getText());
        r = Float.parseFloat(txtrate.getText());
        a = Float.parseFloat(txtamount.getText());
        d = Date.valueOf(mfgdate.getValue());
        ed = Date.valueOf(expiredate.getValue());
        b = txtbatchno.getText();
        m = txtmobileno.getText();
        dis = Integer.parseInt(txtdiscountno.getText());
        g = Float.parseFloat(txtgst.getText());
        total = Float.parseFloat(txttotalamount.getText());
        if (p_case.isSelected()) {
            p = p_case.getText();
        } else {
            p = p_online.getText();
        }

        iobj o = new iobj();

        if (txtcustomernm.isEditable()) {
            try {
                total_amount = total_amount + a;
               // System.out.println(total_amount);

                if (o.insertdat(i, cm, m, dis, g, total, p) > 0) {
                    Component rootPane = null;
                    JOptionPane.showMessageDialog(rootPane, "insert record", "insert", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    Component rootPane = null;

                    JOptionPane.showMessageDialog(rootPane, "try again", "try", JOptionPane.ERROR_MESSAGE);
                }
                if (o.insertmedicine(im, q, r, a, d, ed, b, i) > 0) {
                    System.out.println("insert record in medicines table ");
                    display();
                } else {
                    System.out.println("not inserted");
                }
            } catch (Exception e) {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, e, "try", JOptionPane.ERROR_MESSAGE);
            }
        } else {

            total_amount = total_amount + a;

            String a_gst = String.valueOf(total_amount * 18 / 100);
            //System.out.println(a_gst);
            txtgst.setText(null);
            txtgst.setText(a_gst);
            int discount = Integer.parseInt(txtdiscountno.getText());
            float dc = total_amount * discount / 100;
            float a_g = Float.parseFloat(txtgst.getText());
          //  System.out.println(a_g);
            String t_amount = String.valueOf(total_amount + a_g - dc);
            txttotalamount.setText(t_amount);
            //System.out.println(t_amount);
            float tm = Float.parseFloat(txttotalamount.getText());

            if (o.insertmedicine(im, q, r, a, d, ed, b, i) > 0) {

                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, "insert record", "insert", JOptionPane.INFORMATION_MESSAGE);
                display();
            } else {
                Component rootPane = null;
                JOptionPane.showMessageDialog(rootPane, "try again", "try", JOptionPane.ERROR_MESSAGE);
            }

            if (o.updateinvoice(a_g, tm, i) > 0) {
                System.out.println("update record");
            } else {
                System.out.println("try again");
            }

        }

    }

    @FXML
    private void txtratekeyreleased(KeyEvent event) {
        int qt = Integer.parseInt(txtquantity.getText());
        float rt = Float.parseFloat(txtrate.getText());
        String answer = String.valueOf(qt * rt);
        txtamount.setText(answer);

    }

    @FXML
    private void txtdiscountkeyreleased(KeyEvent event) {
        a = Float.parseFloat(txtamount.getText());
        int disa = Integer.parseInt(txtdiscountno.getText());
        float d = a * disa / 100;
        String gstc = String.valueOf(a * 18 / 100);
        txtgst.setText(gstc);
        float tc = Float.parseFloat(txtgst.getText());
        String total = String.valueOf(a + tc - d);
        txttotalamount.setText(total);

    }

    @FXML
    private void btnsearch(ActionEvent event) {
        getdatasearch();
        search();

    }

    private void btnref(ActionEvent event) {
        display();
    }

    @FXML
    private void btnfirstt(ActionEvent event) {

        try {
            TableView.TableViewSelectionModel<medicine_variable> s = tableview.getSelectionModel();

            s.selectFirst();

        } catch (Exception e) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e, "no data", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    @FXML
    private void btnlast(ActionEvent event) {
        TableView.TableViewSelectionModel<medicine_variable> s = tableview.getSelectionModel();
        s.selectLast();

    }

    @FXML
    private void btnnext(ActionEvent event) {
        try {

            TableView.TableViewSelectionModel<medicine_variable> s = tableview.getSelectionModel();

            s.selectNext();

        } catch (Exception e) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, e, "not date", JOptionPane.ERROR_MESSAGE);

        }
    }

    @FXML
    private void btnprev(ActionEvent event) {
        TableView.TableViewSelectionModel<medicine_variable> s = tableview.getSelectionModel();
        s.selectPrevious();

    }

    @FXML
    private void btncancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void btnref(MouseEvent event) {
        display();
    }

    @FXML
    private void btnadd(ActionEvent event) {
        if (txtnameofitem.getText() != null) {
            txtinvoiceno.setEditable(false);
            txtcustomernm.setEditable(false);
            txtnameofitem.setText(null);
            txtquantity.setText(null);
            txtrate.setText(null);
            txtamount.setText(null);
            mfgdate.setValue(null);
            expiredate.setValue(null);
            txtmobileno.setEditable(false);
            txtbatchno.setText(null);
            txtdiscountno.setEditable(false);
            txtgst.setEditable(false);
            txttotalamount.setEditable(false);

        }
    }

    @FXML
    private void amount(InputMethodEvent event) {

    }

    @FXML
    private void mrp(KeyEvent event) {

    }

    @FXML
    private void txtchanges(InputMethodEvent event) {

    }

    public Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/jtb", "root", "");
            return c;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }

    @FXML
    private void btnprint(ActionEvent event) throws JRException {
        c = connection();
        Map<String, Object> m = new HashMap<>();
         m.put("invoiceno", Integer.parseInt(txtinvoiceno.getText()));
        m.put("customername", txtcustomernm.getText());
        m.put("mobileno", txtmobileno.getText());
        if(p_case.isSelected())
              m.put("payment", p_case.getText());
        else
            m.put("payment", p_online.getText());
        m.put("discount", txtdiscountno.getText());
        m.put("gst",txtgst.getText());
        m.put("total", txttotalamount.getText());
        

        jr = JasperCompileManager.compileReport("D:\\java\\.net\\projectfx\\src\\projectfx\\report1.jrxml");
        jp = JasperFillManager.fillReport(jr, m, c);
        JRViewer jrv = new JRViewer(jp);
        jrv.setOpaque(true);
        jrv.setVisible(true);
        this.add(jrv);
        this.setSize(300, 300);
        this.setVisible(true);

    }

}
