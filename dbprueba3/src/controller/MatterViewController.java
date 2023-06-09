
package controller;

import dbprueba3.CConexion;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class MatterViewController implements Initializable {
    

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private DatePicker endDate;

    @FXML
    private DatePicker startDate;

    @FXML
    private ComboBox<String> statusBox;
    
    @FXML
    private ComboBox<String> lawyerBox;
        
        @FXML
    private ComboBox<String> clientBox;    

    @FXML
    private TextField txtName;
    
    @FXML
    private TableView<Matter> table; 
    
    @FXML 
    private TableColumn<?, ?> statusColumn;
    @FXML
    private TableColumn<?, ?> clientNameColumn;

    @FXML
    private TableColumn<?, ?> endDateColumn;

    @FXML
    private TableColumn<?, ?> startDateColumn;
    
        @FXML
    private TableColumn<?, ?> lawyerNameColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;
    
    @FXML
    private TableColumn<?, ?> idColumn;    
       
    @FXML 
    private void comboBox(){
        
        statusBox.getItems().add("No iniciado");     
        statusBox.getItems().add("En trámite");
        statusBox.getItems().add("Archivado");
        statusBox.getItems().add("Terminado");
        
       CConexion conexion = new CConexion();
       List<String> nombresClientes = conexion.obtenerNombresClientes();
       List<String> nombresAbogados = conexion.obtenerNombresAbogados();
       
       if (nombresClientes != null && nombresAbogados != null) {
           clientBox.getItems().addAll(nombresClientes);
           lawyerBox.getItems().addAll(nombresAbogados);
       }
    }
    
    public void loadAsuntosData() {
    CConexion con = new CConexion();
    List<Matter> asuntos = con.obtenerAsuntos();

    table.setItems(FXCollections.observableArrayList(asuntos));

    idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
    startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
    endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
    statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    clientNameColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
    lawyerNameColumn.setCellValueFactory(new PropertyValueFactory<>("lawyerName"));

    table.setOnMouseClicked(event -> {
        Matter selectedAsunto = table.getSelectionModel().getSelectedItem();
        if (selectedAsunto != null) {
            startDate.setValue(LocalDate.parse(selectedAsunto.getStartDate()));
            endDate.setValue(LocalDate.parse(selectedAsunto.getEndDate()));
            statusBox.setValue(selectedAsunto.getStatus());
            txtName.setText(selectedAsunto.getName());
            clientBox.setValue(selectedAsunto.getClientName());
            lawyerBox.setValue(selectedAsunto.getLawyerName());
        }
    });
}


    @FXML
 private void add(ActionEvent event) {
    String nombreAsunto = txtName.getText();
    LocalDate fechaInicio = startDate.getValue();
    LocalDate fechaFin = endDate.getValue();
    String estado = statusBox.getValue();
    String nameClient = clientBox.getValue();
    String nameLawyer = lawyerBox.getValue();// Obtén el valor del estado del asunto

    // Validación de campos obligatorios
    if (nombreAsunto.isEmpty() || fechaInicio == null || fechaFin == null || estado.isEmpty() || nameClient.isEmpty() || nameLawyer.isEmpty()) {
        System.out.println("algun campo esta vacio");
// Mostrar mensaje de error o realizar alguna acción de validación
        return;
    }

    // Llamada al método de la clase CConexion para agregar el asunto
    CConexion conexion = new CConexion();
    boolean resultado = conexion.agregarAsunto(nombreAsunto, fechaInicio, fechaFin, estado, nameClient, nameLawyer);

    if (resultado) {
            System.out.println("Asunto registrado con exito");
            loadAsuntosData();
        } else {
        System.out.println("Error al registrar este asunto");
    }
}
 
 
    @FXML
    void Update(ActionEvent event) {
        Matter selectedMatter = table.getSelectionModel().getSelectedItem();
         CConexion con = new CConexion();
        if(selectedMatter != null){
            String nuevoNombreAsunto = txtName.getText();
            LocalDate nuevoFechaInicio = startDate.getValue();
            LocalDate nuevoFechaFin = endDate.getValue();
            String nuevoEstado = statusBox.getValue();
            String newClientName = clientBox.getValue(); 
            String newLawyerName =lawyerBox.getValue();
            
            boolean resultado = con.actualizarAsunto(selectedMatter.getId(), nuevoNombreAsunto, nuevoFechaInicio, nuevoFechaFin, nuevoEstado, newClientName, newLawyerName);
            
            if(resultado){
               System.out.println("Datos del asunto actualizados correctamente"); 
               loadAsuntosData();
            }else{
                System.out.println("Error al actualizar los datos del asunto");
            }
        }
    }
    
        @FXML
    void Delete(ActionEvent event) {
         Matter selectedMatter = table.getSelectionModel().getSelectedItem();
    if (selectedMatter != null) {
        int id = selectedMatter.getId();
        CConexion conecction = new CConexion();
        boolean resultado = conecction.eliminarAsunto(id);
        if (resultado) {
            System.out.println("El Abogado se eliminó correctamente");
            //lblAlert.setText("El abogado se elimino correctamente");
            loadAsuntosData();
            
        } else {
            System.out.println("Error al eliminar el Abogado");
            //lblAlert.setText("Error al eliminar los datos del abogado");
        }
    }
    }

       @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox();
        loadAsuntosData();
    } 
}

