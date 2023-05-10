
package controller;

import dbprueba3.CConexion;
import dbprueba3.Usuario;
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

public class RegisterViewController extends Usuario implements Initializable{
       
    @FXML
    private TextField txtName;
    
    @FXML
    private TextField txtLastname;
    
    @FXML
    private TextField txtEmail;
    
    @FXML
    private TextField txtPhone;
    
    @FXML
    private TextField txtUser;
    
    @FXML
    private PasswordField txtPass;
    
    @FXML 
    private Button btnRegister;  
    
    @FXML Label lblAlert;

    public RegisterViewController(String name, String lastname, String email, String phone, String user, String password) {
        super(name, lastname, email, phone, user, password);
    }
    
    
    
   @FXML
private void eventKey(KeyEvent event){
    Object evt = event.getSource();

    if(evt.equals(txtName) || evt.equals(txtLastname)){
        if(!event.getCharacter().matches("[a-zA-Z ]")){
            System.out.println("Solo caracteres");
            event.consume();
        }
    } else if(evt.equals(txtUser)){
        if(event.getCharacter().equals(" ")){
            System.out.println("no ingrese espacios");
            event.consume();
        }
    } else if(evt.equals(txtEmail)){
        if(event.getCharacter().equals(" ")){
            System.out.println("no ingrese espacios");
            event.consume();
        }
    } else if(evt.equals(txtPhone)){
        if(!event.getCharacter().matches("\\d")){
            System.out.println("ingrese numeros");
            event.consume();
        }
    } else if(evt.equals(txtPass)){
        if(txtPass.getText().length() >= 8){
            System.out.println("solo hasta 8 digitos");
            event.consume();
        }
    }
}    
   @FXML
    private void eventAction(ActionEvent event) throws IOException{
        Object evt = event.getSource();
        CConexion conecction = new CConexion();
        
        if(evt.equals(btnRegister)){
            
            if (txtName.getText().isEmpty() || txtLastname.getText().isEmpty() || txtEmail.getText().isEmpty() || 
            txtPhone.getText().isEmpty() || txtUser.getText().isEmpty() || txtPass.getText().isEmpty()) {
            lblAlert.setText("Por favor llene todos los campos.");
            return;
            }

            String name = txtName.getText();
            String lastname = txtLastname.getText();
            String email = txtEmail.getText();
            String phone = txtPhone.getText();
            String user = txtUser.getText();
            String password = txtPass.getText();                               
       
            boolean resultado = conecction.agregarUsuario(name, lastname, email, phone, user, password);
                
            if (resultado) {
                System.out.println("EL usuario se agrego correctamente ");
                openLogin();
            } else {
                System.out.println("Error al agregar al asuario");
            }
        }
    }
    
    private void openLogin() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/loginView.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        
        Stage currentStage = (Stage) btnRegister.getScene().getWindow();
        currentStage.close();
        
    } catch (IOException e) {
        System.out.println("Error al abrir la ventana principal: " + e.getMessage());
    }
}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

    }    
    
}
