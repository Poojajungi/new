
package medicalshop;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author poojajungi
 */
public class Medicalshop extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     
        Parent root = FXMLLoader.load(getClass().getResource("returnStock.fxml"));
        Scene scene = new Scene(root);
   //      scene.getStylesheets().add(getClass().getResource("stock_design.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
