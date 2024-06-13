package medicalshop;

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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class CompanyReturnController implements Initializable {

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

    Connection conn;
    ObservableList<tblreturn> listM = FXCollections.observableArrayList();
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            display();
            load();
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
            PreparedStatement pst = conn.prepareStatement("select *from companyreceived");
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
}
