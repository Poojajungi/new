
package medicalshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class CategoryController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private TextField name;
    @FXML
    private VBox vb;

    String nm,pro;
    crud cr = new crud();
    
    public void inform()
    {
        nm = name.getText();
    }
    public void clear()
    {
        name.setText(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void btnAdd(ActionEvent event) {
        String imagepath = "C:\\Users\\poojajungi\\Downloads\\check.png";
        ImageIcon icon = new ImageIcon(imagepath);
        try {
            inform();
            if (cr.categoryAdd(nm)>0) {
                JOptionPane.showMessageDialog(null, "New Category Added Successfully.","Category Messages",JOptionPane.PLAIN_MESSAGE,icon);
                clear();
            } else {
                JOptionPane.showMessageDialog(null, "Try Again.","Category Message",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e,"Company Details Message",JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        clear();
    }
    
}
