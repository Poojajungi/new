package medicalshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DashboardController implements Initializable {

    @FXML
    private SplitPane split;
    @FXML
    private AnchorPane pane1;
    @FXML
    private AnchorPane pane2;
    @FXML
    private ImageView imgdash;
    @FXML
    private MenuButton menu11;
    @FXML
    private MenuItem p1;
    @FXML
    private MenuButton menu111;
    @FXML
    private MenuItem c1;
    @FXML
    private MenuItem c2;
    @FXML
    private MenuItem i1;
    @FXML
    private MenuItem i2;
    @FXML
    private MenuItem i3;
    @FXML
    private MenuButton menu11111;
    @FXML
    private MenuItem i21;
    @FXML
    private MenuItem i31;
    @FXML
    private MenuItem i311;
    @FXML
    private MenuItem i3111;
    @FXML
    private MenuItem i31111;
    @FXML
    private MenuButton menu11112;
    @FXML
    private MenuItem i12;
    @FXML
    private MenuItem i22;
    @FXML
    private MenuButton menu111121;
    @FXML
    private MenuItem i121;
    @FXML
    private MenuItem i221;
     AnchorPane ap = new AnchorPane();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
    }    

    @FXML
    private void cateClick(ActionEvent event) {
          try {
              ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("category.fxml"));
                ap = loader.load();
                ap.getStylesheets().add(getClass().getResource("category_design.css").toExternalForm());
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void viewCateClick(ActionEvent event) {
          try {
              ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewcategory.fxml"));
                ap = loader.load();
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void AddStockClick(ActionEvent event) {
         try {
             ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("medicines.fxml"));
                ap = loader.load();
                ap.getStylesheets().add(getClass().getResource("medi_design.css").toExternalForm());
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void reorderclick(ActionEvent event) {
        try {
              ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("stock.fxml"));
                ap = loader.load();
                ap.getStylesheets().add(getClass().getResource("stock_design.css").toExternalForm());
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void ReturnClick(ActionEvent event) {
        try {
              ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("returnStock.fxml"));
                ap = loader.load();
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void ViewReorderClick(ActionEvent event) {
             try {
                ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("vieworder.fxml"));
                ap = loader.load();
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void ViewReturnStockClick(ActionEvent event) {
         try {
              ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("viewReturn.fxml"));
                ap = loader.load();
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }

    @FXML
    private void CompanyReceived(ActionEvent event) {
         try {
              ap.getChildren().clear();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("companyReturn.fxml"));
                ap = loader.load();
                pane2.getChildren().addAll(ap);
            } catch (Exception e) {
                System.out.println(e);
            }
    }
    
}
