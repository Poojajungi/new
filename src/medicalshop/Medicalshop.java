
package medicalshop;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Medicalshop extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
     
        Parent root = FXMLLoader.load(getClass().getResource("imageForm.fxml"));
        Scene scene = new Scene(root);
   //    scene.getStylesheets().add(getClass().getResource("stock_design.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
