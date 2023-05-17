
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ViewPrincipalController implements Initializable {
 
    @FXML
    private Button btnLawyer;
    
    @FXML
    private Button btnClient;
    
    @FXML
    private Button btnMatter;
    
    @FXML
    private BorderPane borderPane;
  
    @FXML
    private void eventAction(ActionEvent event){
        Object evt = event.getSource();
        
        if(evt.equals(btnLawyer)){
            openLawyerView();
        }
        else if(evt.equals(btnClient)){
            openClientView();
        }
        else if(evt.equals(btnMatter)){
            openmatterView();
        }
        
    }
    
    private void openLawyerView() {

            try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/lawyerView.fxml"));
                    Node lawyerView = loader.load();
                    borderPane.setCenter(lawyerView);
                } catch (IOException e) {
                    System.out.println("Error al abrir la ventana lawyerView: " + e.getMessage());
                }
            }

    private void openClientView() {

            try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/clientView.fxml"));
                    Node lawyerView = loader.load();
                    borderPane.setCenter(lawyerView);
                } catch (IOException e) {
                    System.out.println("Error al abrir la ventana ClientView: " + e.getMessage());
                }
            }
    
    private void openmatterView() {

        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/matterView.fxml"));
                Node lawyerView = loader.load();
                borderPane.setCenter(lawyerView);
            } catch (IOException e) {
                System.out.println("Error al abrir la ventana matterView: " + e.getMessage());
            }
        }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
}
