package medicalshop;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.swing.plaf.RootPaneUI;

/**
 * FXML Controller class
 *
 * @author poojajungi
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane loginbody;
    @FXML
    private TextField uname;
    @FXML
    private PasswordField upassw;
    @FXML
    private Button btn1;
    crud cr = new crud();
    @FXML
    private TextField textpass;
    static int temp = 1;
    @FXML
    private ImageView img;
    @FXML
    private ImageView img2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void buttonpress(ActionEvent event) {
        try {
            String un = String.valueOf(uname.getText());
            String ps = String.valueOf(upassw.getText());
            if (cr.log(un, ps) == true) {
                JOptionPane.showMessageDialog(null, "login success", "Login Messages", JOptionPane.INFORMATION_MESSAGE);

                loginbody.getScene().getWindow().hide(); // Hide login window

                Stage primaryStage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("medicines.fxml"));
                Scene scene = new Scene(root);
                scene.getStylesheets().add(getClass().getResource("medi_design.css").toExternalForm());
                primaryStage.setScene(scene);
                primaryStage.show();

            } else {
                JOptionPane.showMessageDialog(null, "try again", "Login Messages", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error : " + e, "Login Messages", JOptionPane.ERROR_MESSAGE);
        }
    }

    @FXML
    private void mouseclick(MouseEvent event) {
        img2.setVisible(false);
        img.setVisible(true);
        upassw.setVisible(false);
        textpass.setVisible(true);
        textpass.setText(upassw.getText());
    }

    @FXML
    private void mclick(MouseEvent event) {
        img2.setVisible(true);
        img.setVisible(false);
        upassw.setVisible(true);
        textpass.setVisible(false);
        upassw.setText(textpass.getText());
    }

}
