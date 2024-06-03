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

public class CompanyController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private TextField name;
    @FXML
    private TextArea addr;
    @FXML
    private TextField city;
    @FXML
    private TextField pin;

    int p;
    String nm,address,cityy;
    crud cr = new crud();
    @FXML
    private VBox vb;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inform()
    {
            nm = name.getText();
            address = addr.getText();
            cityy = city.getText();
            p = Integer.parseInt(pin.getText());
    }
    public void clear()
    {
        name.setText(null);
        addr.setText(null);
        city.setText(null);
        pin.setText(null);
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void btnAdd(ActionEvent event) {
      String imagepath = "C:\\Users\\poojajungi\\Downloads\\check.png";
        ImageIcon icon = new ImageIcon(imagepath);
        try {
            inform();
            if (cr.companyAdd(nm, address, cityy, p)>0) {
                JOptionPane.showMessageDialog(null, "Company Add Successfully.","Company Details Message",JOptionPane.PLAIN_MESSAGE,icon);
                clear();
            } else {
                JOptionPane.showMessageDialog(null, "Try Again.","Company Details Message",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e,"Company Details Message",JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
