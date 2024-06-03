
package medicalshop;

import java.io.IOException;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class ViewcategoryController implements Initializable {

    @FXML
    private Label title;
     Connection conn;
    ObservableList<tblcategory>list = FXCollections.observableArrayList();
    int c=0;
    String nm ,pro;
    crud cr = new crud();
    
    private Label total;
    @FXML
    private AnchorPane panel2;
    @FXML
    private TableView<tblcategory> tbl;
    @FXML
    private TableColumn<tblcategory, Integer> pid;
    @FXML
    private TextField name;
    @FXML
    private TextField product;
    @FXML
    private TableColumn<tblcategory,String> category_name;
    @FXML
    private TableColumn<tblcategory,String> product_name;
    
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
                    list.clear();
                   conn =  connect();
                    PreparedStatement pst = conn.prepareStatement("select p.pid,c.category_name,p.product_name from categorytbl c,productstbl p where c.id=p.category_id");
                    ResultSet r = pst.executeQuery();
                    while (r.next()) {                        
                        list.add(new tblcategory(r.getInt("pid"),
                                r.getString("category_name"), 
                                 r.getString("product_name")));
                        c++;
                    }
                  tbl.setItems(list);
            } catch (Exception e) {
                    System.out.println(e);
            }
        }
        public void load()
        {
                pid.setCellValueFactory(new PropertyValueFactory<>("pid"));
                category_name.setCellValueFactory(new PropertyValueFactory<>("category_name"));
                product_name.setCellValueFactory(new PropertyValueFactory<>("product_name"));
        }
        public void inform()
    {
        nm = name.getText();
        pro = product.getText();
    }
    public void clear()
    {
        name.setText(null);
        product.setText(null);
    }
    
    @FXML
    private void btnAdd(ActionEvent event) {
        
         String imagepath = "C:\\Users\\poojajungi\\Downloads\\check.png";
        ImageIcon icon = new ImageIcon(imagepath);
        try {
            inform();
            if (cr.CategoryUpdate(nm, pro)>0) {
                JOptionPane.showMessageDialog(null, "Category Updated Successfully.","View Category Message",JOptionPane.PLAIN_MESSAGE,icon);
                clear();
                display();
                panel2.setVisible(false);
            } else {
                 JOptionPane.showMessageDialog(null, "Try Again.","View Category Message",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"View Category Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void btnDetails(ActionEvent event) throws IOException {
        panel2.setVisible(true);
            name.setText(tbl.getSelectionModel().getSelectedItem().getCategory_name());
            product.setText(tbl.getSelectionModel().getSelectedItem().getProduct_name());
    }
}
