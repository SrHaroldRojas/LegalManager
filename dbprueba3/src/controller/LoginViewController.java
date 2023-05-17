
package controller;

import dbprueba3.CConexion;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class LoginViewController implements Initializable {
    
    @FXML
    private TextField txtUser;
    
    @FXML
    private PasswordField txtPassword;
    
    @FXML
    private Button btnLogin;
    
    @FXML 
    private Button btnGoRegister;
    
    @FXML
    private Label lblAlert;
     
    @FXML
    private void eventKey(KeyEvent event){
        
        Object evt = event.getSource();
        
        if(evt.equals(txtUser)){
            
            if(event.getCharacter().equals(" ")){
                event.consume();
            }
        
        }else if(evt.equals(txtPassword)){

            if(event.getCharacter().equals(" ")){
                event.consume();
            }                    
        }                 
    }
    
    @FXML
    private void eventAction(ActionEvent event) throws IOException{
        CConexion conexion = new CConexion();
        Object evt = event.getSource();
        if(evt.equals(btnLogin)){
              
                if(!txtUser.getText().isEmpty() && !txtPassword.getText().isEmpty()){
                    if (conexion.verificarUsuario(txtUser.getText(), txtPassword.getText())) {
                     System.out.println("Los datos son correctos");
                     lblAlert.setText("Los datos ingresados son correctos...");
                     openViewPrincipal();

                    } 
                    else {
                    lblAlert.setText("Los datos ingresados son incorrectos...");
                    System.out.println("Los datos ingresados son incorrectos!");

                    }
                }else{
                    lblAlert.setText("Por favor rellene todos los campos...");
                }
            
        }
    }
    
    @FXML
    private void goRegister(ActionEvent event){
        Object evt = event.getSource();
        
        if(evt.equals(btnGoRegister)){
            openRegister();
        }
    }
    
    private void openRegister() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/registerView.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show(); 
        
        
        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();
        
    } catch (IOException e) {
        System.out.println("Error al abrir la ventana principal: " + e.getMessage());
    }
}
    
    private void openViewPrincipal() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/viewPrincipal.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
        //cierra la ventana al abrir la otra
        Stage currentStage = (Stage) btnLogin.getScene().getWindow();
        currentStage.close();

    } catch (IOException e) {
        System.out.println("Error al abrir la ventana principal: " + e.getMessage());
    }
}
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
}
