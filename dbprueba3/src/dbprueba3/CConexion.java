
package dbprueba3;

import controller.Lawyer;
import controller.Client;
import controller.Matter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */
public class CConexion {
    
    Connection conectar = null;
    String userdb="Harold";
    String passdb = "root";
    String db = "dblegalmanager";
    String ip = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+db;
 //----------ESTABLECE CONEXION -----------------------------------------------------------   
    public Connection estableceConexion(){
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");            conectar = DriverManager.getConnection(cadena, userdb, passdb);
            System.out.println("SE CONECTO A LA BASE DE DATOS CORRECTAMENTE");
            
        } catch (Exception e) {
            System.out.println("NO SE CONECTO A LA BASE DE DATOS, ERROR"+e.toString());
        }
        
        return conectar;
    }
  //-----------GESTIONA USUARIOS-------------------------------------------------------------------  
    public boolean agregarUsuario(String name, String lastname, String email, String phone, String user, String password) {
   
        boolean resultado = false;
        
            try {
                Connection conn = estableceConexion();
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO usuarios (name, lastname, email, phone, user, password) VALUES ('" + name + "', '" + lastname + "', '" + email + "', '" + phone + "', '" + user + "', '" + password + "')";
                int filasAfectadas = stmt.executeUpdate(sql);

                if (filasAfectadas > 0) {
                    System.out.println("AGREGADO CON EXITO");
                    resultado = true;
                }

                stmt.close();
                cierraConexion();
            } catch (SQLException e) {
                System.out.println("ERROR AL AGREGAR AL USUARIO: " + e.getMessage());
            }
            return resultado;
    }
    
    public boolean verificarUsuario(String usuario, String contrasena) {
    boolean resultado = false;
    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "SELECT COUNT(*) as count FROM usuarios WHERE user='" + usuario + "' AND password='" + contrasena + "'";
        ResultSet rs = stmt.executeQuery(sql);

        if (rs.next() && rs.getInt("count") > 0) {
            resultado = true;
        }
        
        rs.close();
        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("Error al verificar el usuario: " + e.getMessage());
    }
    return resultado;
}   
//--------------GESTIONA ABOGADOS-----------------------------------------------------------
    public boolean agregarAbogado(String name, String lastname, String email, String phone, String address) {
    boolean resultado = false;

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO abogados (name_lawyer, lastname_lawyer, email, num_telephone, address) VALUES ('" + name + "', '" + lastname + "', '" + email + "', '" + phone + "', '" + address + "')";
        int filasAfectadas = stmt.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("AGREGADO CON ÉXITO");
            resultado = true;
        }

        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL AGREGAR EL ABOGADO: " + e.getMessage());
    }
    return resultado;
}
        public List<Lawyer> obtenerDatosDesdeLaBaseDeDatos() {
        List<Lawyer> lawyers = new ArrayList<>();

        try {
            Connection conn = estableceConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM abogados");

            while (rs.next()) {
                int id = rs.getInt("id_lawyer");
                String idString = String.valueOf(id);
                String name = rs.getString("name_lawyer");
                String lastname = rs.getString("lastname_lawyer");
                String address = rs.getString("address");
                String phone = rs.getString("num_telephone");
                String email = rs.getString("email");

                Lawyer lawyer = new Lawyer(id, name, lastname, address, email, phone);
                lawyers.add(lawyer);
            }

            rs.close();
            stmt.close();
            cierraConexion();
        } catch (SQLException e) {
            System.out.println("ERROR AL OBTENER DATOS DESDE LA BASE DE DATOS: " + e.getMessage());
        }

        return lawyers;
    }
        
        public List<String> obtenerNombresAbogados() {
    List<String> nombresAbogados = new ArrayList<>();

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name_lawyer FROM abogados");

        while (rs.next()) {
            String nombreAbogado = rs.getString("name_lawyer");
            nombresAbogados.add(nombreAbogado);
        }

        rs.close();
        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("Error al obtener los nombres de los abogados: " + e.getMessage());
    }

    return nombresAbogados;
}    
        
    public boolean actualizarAbogado(int lawyerId, String newName, String newLastname, String newEmail, String newPhone, String newAddress) {
    boolean resultado = false;
    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "UPDATE abogados SET name_lawyer = '" + newName + "', lastname_lawyer = '" + newLastname + "', email = '" + newEmail + "', num_telephone = '" + newPhone + "', address = '" + newAddress + "' WHERE id_lawyer = " + lawyerId;
        int filasAfectadas = stmt.executeUpdate(sql);

        if (filasAfectadas > 0) {
            resultado = true;
        }

        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL ACTUALIZAR LOS DATOS DEL ABOGADO: " + e.getMessage());
    }
    return resultado;
}
    public boolean eliminarAbogado(int id) {
    boolean resultado = false;

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "DELETE FROM abogados WHERE id_lawyer = " + id;
        int filasAfectadas = stmt.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("AL ABOGADO SE ELIMINO CORRECTAMENTE");
            resultado = true;
        } else {
            System.out.println("NO SE ENCONTRO EL ABOGADO CON ID: " + id);
        }

        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL ELIMINAR EL ABOGADO: " + e.getMessage());
    }

    return resultado;
}
    
    //-----GESTIONAR CLIENTES-------------------------------------
    
    public boolean agregarCliente(String name, String lastname, String address, String numTelephone, String email) {
    boolean resultado = false;

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "INSERT INTO clientes (name_client, lastname_client, address, num_telephone, email) VALUES ('" + name + "', '" + lastname + "', '" + address + "', '" + numTelephone + "', '" + email + "')";
        int filasAfectadas = stmt.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("CLIENTE AGREGADO CON ÉXITO");
            resultado = true;
        }

        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL AGREGAR EL CLIENTE: " + e.getMessage());
    }
    return resultado;
}
    
    public boolean actualizarCliente(int clientId, String newName, String newLastname, String newAddress, String newNumTelephone, String newEmail) {
    boolean resultado = false;

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "UPDATE clientes SET name_client = '" + newName + "', lastname_client = '" + newLastname + "', address = '" + newAddress + "', num_telephone = '" + newNumTelephone + "', email = '" + newEmail + "' WHERE id_client = " + clientId;
        int filasAfectadas = stmt.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("DATOS DEL CLIENTE ACTUALIZADOS CON ÉXITO");
            resultado = true;
        }

        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL ACTUALIZAR LOS DATOS DEL CLIENTE: " + e.getMessage());
    }
    return resultado;
}
    
    public List<Client> obtenerClientes() {
    List<Client> clientes = new ArrayList<>();

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM clientes");

        while (rs.next()) {
            int id = rs.getInt("id_client");
            String name = rs.getString("name_client");
            String lastname = rs.getString("lastname_client");
            String address = rs.getString("address");
            String numTelephone = rs.getString("num_telephone");
            String email = rs.getString("email");

            Client cliente = new Client(id, name, lastname, address, email, numTelephone);
            clientes.add(cliente);
        }

        rs.close();
        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL OBTENER LOS DATOS DE LOS CLIENTES DESDE LA BASE DE DATOS: " + e.getMessage());
    }

    return clientes;
}
    public List<String> obtenerNombresClientes() {
    List<String> nombresClientes = new ArrayList<>();

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT name_client FROM clientes");

        while (rs.next()) {
            String nombreCliente = rs.getString("name_client");
            nombresClientes.add(nombreCliente);
        }

        rs.close();
        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("Error al obtener los nombres de los clientes: " + e.getMessage());
    }

    return nombresClientes;
}

    public boolean eliminarCliente(int clientId) {
        boolean resultado = false;

        try {
            Connection conn = estableceConexion();
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM clientes WHERE id_client = " + clientId;
            int filasAfectadas = stmt.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("CLIENTE ELIMINADO CORRECTAMENTE");
                resultado = true;
            } else {
                System.out.println("NO SE ENCONTRÓ EL CLIENTE CON ID: " + clientId);
            }

            stmt.close();
            cierraConexion();
        } catch (SQLException e) {
            System.out.println("ERROR AL ELIMINAR EL CLIENTE: " + e.getMessage());
        }

        return resultado;
    }
//------GESTIONAR ASUNTOS-------------------------------
    public boolean agregarAsunto(String nombreAsunto, LocalDate fechaInicio, LocalDate fechaFin, String estado, String clientName, String lawyerName) {
    boolean resultado = false;

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();

        String sql = "INSERT INTO asuntos (nombre_asunto, fecha_inicio, fecha_fin, estado, client_name, lawyer_name) VALUES ('"
                + nombreAsunto + "', '" + fechaInicio + "', '" + fechaFin + "', '"
                + estado +"', '"+clientName+"', '"+lawyerName+ "')";

        int filasAfectadas = stmt.executeUpdate(sql);

        if (filasAfectadas > 0) {
            System.out.println("Asunto agregado con éxito");
            resultado = true;
        }

        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("Error al agregar el asunto: " + e.getMessage());
    }

    return resultado;
}
    
    public List<Matter> obtenerAsuntos() {
    List<Matter> asuntos = new ArrayList<>();

    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM asuntos");

        while (rs.next()) {
            int id = rs.getInt("id_asunto");
            String startDate = rs.getString("fecha_inicio");
            String endDate = rs.getString("fecha_fin");
            String status = rs.getString("estado");
            String name = rs.getString("nombre_asunto");
            String clientName = rs.getString("client_name");
            String lawyerName = rs.getString("lawyer_name");

            Matter asunto = new Matter(id, startDate, endDate, status, name, clientName, lawyerName);
            asuntos.add(asunto);
        }

        rs.close();
        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("ERROR AL OBTENER LOS DATOS DE LOS ASUNTOS DESDE LA BASE DE DATOS: " + e.getMessage());
    }

    return asuntos;
}
    
    public boolean actualizarAsunto(int id, String nuevoNombreAsunto, LocalDate nuevoFechaInicio, LocalDate nuevoFechaFin, String nuevoEstado, String newClientName, String newLawyerName) {
        boolean resultado = false;
        
        try{
            Connection conn = estableceConexion();
            Statement stmt = conn.createStatement();
            String sql ="UPDATE asuntos SET nombre_asunto ='" + nuevoNombreAsunto + "', fecha_inicio ='" + nuevoFechaInicio + "', fecha_fin ='" + nuevoFechaFin + "', estado ='" + nuevoEstado + "', client_name ='" + newClientName + "', lawyer_name ='" + newLawyerName + "'WHERE id_asunto = '"+ id  + "'";
            int filasAfectadas = stmt.executeUpdate(sql);
            
             if (filasAfectadas > 0) {
            System.out.println("DATOS DEL ASUNTO ACTUALIZADOS CON ÉXITO");
            resultado = true;
        }

        stmt.close();
        cierraConexion();
            
        }catch(SQLException e){
            System.out.println("ERROR AL ACTUALIZAR LOS DATOS DEL ASUNTO: " + e.getMessage());
        }
        
        
        return resultado;
}
        public boolean eliminarAsunto(int matterId) {
        boolean resultado = false;

        try {
            Connection conn = estableceConexion();
            Statement stmt = conn.createStatement();
            String sql = "DELETE FROM asuntos WHERE id_asunto = " + matterId;
            int filasAfectadas = stmt.executeUpdate(sql);

            if (filasAfectadas > 0) {
                System.out.println("ASUNTO ELIMINADO CORRECTAMENTE");
                resultado = true;
            } else {
                System.out.println("NO SE ENCONTRÓ EL ASUNTO CON ID: " + matterId);
            }

            stmt.close();
            cierraConexion();
        } catch (SQLException e) {
            System.out.println("ERROR AL ELIMINAR EL ASUNTO: " + e.getMessage());
        }

        return resultado;
    }



       
    public void cierraConexion() {
    try {
        if (conectar != null) {
            conectar.close();
           System.out.println("CONEXIÓN CERRADA");
        }
    } catch (Exception e) {
        System.out.println("ERROR AL CERRAR LA CONEXIÓN: " + e.toString());
    }
}

}