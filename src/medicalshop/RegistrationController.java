package medicalshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JOptionPane;

public class RegistrationController implements Initializable {

    @FXML
    private TextField usernm;
    @FXML
    private PasswordField passw;
    @FXML
    private PasswordField cpassw;
    @FXML
    private TextField contact;
    @FXML
    private TextField em;
    @FXML
    private TextField passwText;
    @FXML
    private TextField cpasswText;
    @FXML
    private ImageView closepw;
    @FXML
    private ImageView closeCpw;
    @FXML
    private ImageView openpw;
    @FXML
    private ImageView openCpw;

    crud cr = new crud();
   String unm,ps,cont,email;
   
    public void information()
    {
        unm = usernm.getText();
        ps = passw.getText();
        cont = contact.getText();
        email = em.getText();
    }
    public void clear()
    {
        usernm.setText(null);
        passw.setText(null);
        passwText.setText(null);
        cpassw.setText(null);
        cpasswText.setText(null);
         contact.setText(null);
         em.setText(null);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnRegister(ActionEvent event) {
        try {
            information();
            if (cr.RegisterInsert(unm, ps, cont, email)>0) {
                JOptionPane.showMessageDialog(null, "New User Registered Successfully.","New User",JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Try Again.","New User",JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
             JOptionPane.showMessageDialog(null, e,"New User",JOptionPane.WARNING_MESSAGE);
        }
    }

    @FXML
    private void btnCancel(ActionEvent event) {
        clear();
    }

    @FXML
    private void psCloseClick(MouseEvent event) {
        openpw.setVisible(true);
        closepw.setVisible(false);
        passwText.setVisible(true);
        passwText.setText(passw.getText());
    }

    @FXML
    private void psOpenClick(MouseEvent event) {
         openpw.setVisible(false);
         closepw.setVisible(true);
        passwText.setVisible(false);
        passw.setText(passwText.getText());
    }

    @FXML
    private void cpsCloseClick(MouseEvent event) {
        openCpw.setVisible(true);
        closeCpw.setVisible(false);
        cpasswText.setVisible(true);
        cpasswText.setText(cpassw.getText());
    }

    @FXML
    private void cpsOpenClick(MouseEvent event) {
        openCpw.setVisible(false);
        closeCpw.setVisible(true);
        cpasswText.setVisible(false);
        cpassw.setText(cpasswText.getText());
    }
    
}
