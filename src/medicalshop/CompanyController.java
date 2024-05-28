package medicalshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class CompanyController implements Initializable {

    @FXML
    private Label title;
    @FXML
    private TextField name;
    @FXML
    private TextField sname;
    @FXML
    private TextArea addr;
    @FXML
    private TextField city;
    @FXML
    private TextField pin;
    @FXML
    private TextField stockist;

    int p;
    String nm,snm,address,cityy,st;
    crud cr = new crud();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inform()
    {
            nm = name.getText();
            snm = sname.getText();
            address = addr.getText();
            cityy = city.getText();
            p = Integer.parseInt(pin.getText());
            st = stockist.getText();
    }
    public void clear()
    {
        name.setText(null);
        sname.setText(null);
        addr.setText(null);
        city.setText(null);
        pin.setText(null);
        stockist.setText(null);
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
            if (cr.companyAdd(nm, snm, address, cityy, p, st)>0) {
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
