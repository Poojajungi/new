package medicalshop;

import java.awt.Frame;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class ImageFormController extends Frame implements Initializable {

    @FXML
    private ImageView imgview;
    @FXML
    private Label textDispl;
     @FXML
    private AnchorPane body;

     
    crud cr = new crud();
     FileChooser fc = new FileChooser();
     File selectedfile ;
     String path;
     Connection conn = cr.connect();
     
     private static JasperReport jr;
     private static JasperPrint jp;
   
   
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String text = "  You must have to upload the image of your signature"+"\n"+" for printing and generating the report.";
        textDispl.setText(text);
     }    

    @FXML
    private void btnChoose(ActionEvent event) {
      selectedfile = fc.showOpenDialog(null);
        path = selectedfile.toURI().toString();
        Image img = new Image(path);
        imgview.setImage(img);
    }

    @FXML
    private void btnGenerate(ActionEvent event){
        try {
            if (selectedfile==null) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Upload Signature");
                    alert.setContentText("Please Choose Image.");
                    alert.setHeaderText(null);
                    DialogPane d = alert.getDialogPane();
                    d.getStylesheets().add(getClass().getResource("alertDesign.css").toString());
                    d.getStyleClass().add("dialog");
                    alert.showAndWait();    
            } 
            else{
            jr = JasperCompileManager.compileReport("E:\\javaprograms\\medicalshop\\src\\Reports\\simple.jrxml");
            Map<String,Object> m = new HashMap<>();
            m.put("imgExp", path);
             jp = JasperFillManager.fillReport(jr, m, conn);
             JRViewer jrv = new JRViewer(jp);
             jrv.setOpaque(true);
             jrv.setVisible(true);
             this.add(jrv);
             body.getScene().getWindow().hide();
             this.setSize(1200,700);
             this.setVisible(true); 
        }
        }catch (Exception e) {
            System.out.println(e);
        }
       
    }
  
}
