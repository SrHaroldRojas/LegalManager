
package controller;

import dbprueba3.CConexion;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class LawyerViewController implements Initializable{
     @FXML
    private TableColumn<?, ?> IDColumn;

    @FXML
    private TableColumn<?, ?> adressColumn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> emailColumn;

    @FXML
    private TableColumn<?, ?> lastnameColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> phoneColumn;

    /*@FXML
    private TableView<?> table;*/

    @FXML
    private TextField txtAdress;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtLastname;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;
    
    @FXML
    private TableView<Lawyer> table;
    
    @FXML 
    private Label lblAlert;


   @FXML
    private void Add(ActionEvent event) throws IOException{
        Object evt = event.getSource();
        CConexion conecction = new CConexion();
        
        if(evt.equals(btnAdd)){
            
            if (txtName.getText().isEmpty() || txtLastname.getText().isEmpty() || txtEmail.getText().isEmpty() || 
            txtPhone.getText().isEmpty() || txtAdress.getText().isEmpty()) {
            lblAlert.setText("Por favor llene todos los campos...");
            return;
            }

            String name = txtName.getText();
            String lastname = txtLastname.getText();
            String address = txtAdress.getText();
            String phone = txtPhone.getText();
            String email = txtEmail.getText();
                                           
       
            boolean resultado = conecction.agregarAbogado(name, lastname, email, phone, address);
                
            if (resultado) {
                System.out.println("EL Abogado se agrego correctamente ");
                lblAlert.setText("EL Abogado se agrego correctamente ");
                loadLawyersData();
            } else {
                System.out.println("Error al agregar el abogado");
                lblAlert.setText("Error al agregar el abogado");
            }
        }
    }
    
    public void loadLawyersData() {
        CConexion con = new CConexion();
    List<Lawyer> lawyers = con.obtenerDatosDesdeLaBaseDeDatos();

    table.setItems(FXCollections.observableArrayList(lawyers));

    IDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    adressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    
        table.setOnMouseClicked(event -> {
        Lawyer selectedLawyer = table.getSelectionModel().getSelectedItem();
        if (selectedLawyer != null) {
            txtName.setText(selectedLawyer.getName());
            txtLastname.setText(selectedLawyer.getLastname());
            txtEmail.setText(selectedLawyer.getEmail());
            txtPhone.setText(selectedLawyer.getPhone());
            txtAdress.setText(selectedLawyer.getAddress());
        }
    });
}
    
    @FXML
    void Delete(ActionEvent event) {
         Lawyer selectedLawyer = table.getSelectionModel().getSelectedItem();
    if (selectedLawyer != null) {
        int id = selectedLawyer.getId();
        CConexion conecction = new CConexion();
        boolean resultado = conecction.eliminarAbogado(id);
        if (resultado) {
            System.out.println("El Abogado se eliminó correctamente");
            lblAlert.setText("El abogado se elimino correctamente");
            loadLawyersData();
        } else {
            System.out.println("Error al eliminar el Abogado");
            lblAlert.setText("Error al eliminar los datos del abogado");
        }
    }
    }

    @FXML

    private void Update(ActionEvent event) {
        Lawyer selectedLawyer = table.getSelectionModel().getSelectedItem();
        CConexion con = new CConexion();
        if (selectedLawyer != null) {
            String newName = txtName.getText();
            String newLastname = txtLastname.getText();
            String newEmail = txtEmail.getText();
            String newPhone = txtPhone.getText();
            String newAddress = txtAdress.getText();

            boolean resultado = con.actualizarAbogado(selectedLawyer.getId(), newName, newLastname, newEmail, newPhone, newAddress);

            if (resultado) {
                System.out.println("Datos del abogado actualizados correctamente");
                lblAlert.setText("Datos de l abogado actualizados correctamente");
                loadLawyersData();
            } else {
                System.out.println("Error al actualizar los datos del abogado");
                lblAlert.setText("Error al actualizar los datos del abogado");

            }
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLawyersData();
    } 
}