package medicalshop;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class DashboardController implements Initializable {

    @FXML
    private SplitPane split;
    @FXML
    private AnchorPane pane1;
    @FXML
    private ImageView imgdash;
    @FXML
    private MenuButton menu111;
    @FXML
    private MenuButton menu11111;
    @FXML
    private MenuButton menu11112;
    @FXML
    private MenuButton menu111121;
    @FXML
    private MenuItem i221;
    AnchorPane ap = new AnchorPane();
    @FXML
    private ScrollPane pn2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        pn2.prefHeightProperty().bind(split.heightProperty());
    }

    @FXML
    private void cateClick(ActionEvent event) {
        try {
            ap.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("category.fxml"));
            ap = loader.load();
            ap.getStylesheets().add(getClass().getResource("category_design.css").toExternalForm());
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
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
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
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
            pn2.setContent(ap);
             ap.prefHeightProperty().bind(pn2.heightProperty());
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
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void ReturnClick(ActionEvent event) {
       returnclic();
    }

    public void returnclic()
    {
         try {
            ap.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("returnStock.fxml"));
            ap = loader.load();
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
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
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
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
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
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
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void companyClick(ActionEvent event) {
        try {
            ap.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("company.fxml"));
            ap = loader.load();
            ap.getStylesheets().add(getClass().getResource("category_design.css").toExternalForm());
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void manageCompnay(ActionEvent event) {
        try {
            ap.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("viewCompany.fxml"));
            ap = loader.load();
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    private void StockReport(ActionEvent event) {
        try {
            ap.getChildren().clear();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("imageForm.fxml"));
            ap = loader.load();
            pn2.setContent(ap);
            ap.prefHeightProperty().bind(pn2.heightProperty());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
