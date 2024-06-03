
package medicalshop;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ViewCompanyController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private Label title1;
    @FXML
    private TextField name;
    @FXML
    private TextArea addr;
    @FXML
    private TextField pin;
    @FXML
    private TextField citytxt;
    
    @FXML
    private TableView<tblcompany> tbl;
    @FXML
    private TableColumn<tblcompany,String> city;
    @FXML
    private TableColumn<tblcompany,String> cname;
    @FXML
    private TableColumn<tblcompany,String> caddress;
    @FXML
    private TableColumn<tblcompany,Integer> pincode;
    
    crud cr = new crud();
    Connection conn;
    ObservableList<tblcompany> list = FXCollections.observableArrayList();
    int p;
    String nm,addre,ci;
    @FXML
    private AnchorPane panel2;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();
        load();
    }    

    @FXML
    private void btnDetails(ActionEvent event) {
            panel2.setVisible(true);
            name.setText(tbl.getSelectionModel().getSelectedItem().getCname());
            addr.setText(tbl.getSelectionModel().getSelectedItem().getCaddress());
            citytxt.setText(tbl.getSelectionModel().getSelectedItem().getCity());
            pin.setText(String.valueOf(tbl.getSelectionModel().getSelectedItem().getPincode()));
            tbl.getSelectionModel().clearSelection();
    }

    @FXML
    private void btnAdd(ActionEvent event) {
        try {
            inform();
             String imagepath = "C:\\Users\\poojajungi\\Downloads\\check.png";
        ImageIcon icon = new ImageIcon(imagepath);
            if (cr.companyUpdate(nm, addre, ci, p)>0) {
                 JOptionPane.showMessageDialog(null, "Company Updated Successfully.","View Company Message",JOptionPane.PLAIN_MESSAGE,icon);
                clear();
                display();
                panel2.setVisible(false);
            } else {
                 JOptionPane.showMessageDialog(null, "Try Again.","View Company Message",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"View Company Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        clear();
    }
    
    public void display()
    {
        try {
            list.clear();
            conn = connect();
            PreparedStatement pst =conn.prepareStatement("select *from company");
            ResultSet r = pst.executeQuery();
            while (r.next()) {                
                list.add(new tblcompany(r.getString("cname"), 
                                                                   r.getString("caddress"),
                                                                   r.getString("city"), 
                                                                   r.getInt("pincode")));
            }
            tbl.setItems(list);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Display Messages",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void load()
    {
        cname.setCellValueFactory(new PropertyValueFactory<>("cname"));
        caddress.setCellValueFactory(new PropertyValueFactory<>("caddress"));
        city.setCellValueFactory(new PropertyValueFactory<>("city"));
        pincode.setCellValueFactory(new PropertyValueFactory<>("pincode"));
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
     public void inform()
     {
         nm = name.getText();
         addre = addr.getText();
         ci = citytxt.getText();
         p = Integer.parseInt(pin.getText());
     }
     public void clear()
     {
         name.setText(null);
         addr.setText(null);
         citytxt.setText(null);
         pin.setText(null);
         tbl.getSelectionModel().clearSelection();
     }
}
