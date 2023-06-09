
package dbprueba3;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Este es la clase Main

public class Dbprueba3 extends Application{
    
    @Override
    public void start(Stage primaryStage) throws IOException{
      
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginView.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        CConexion objetoConexion = new CConexion();
        objetoConexion.estableceConexion();
        //objetoConexion.mostrarDatos();
        launch();
        objetoConexion.cierraConexion();   
        
    }


    
}
