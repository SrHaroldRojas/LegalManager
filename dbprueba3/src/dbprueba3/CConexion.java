
package dbprueba3;

import controller.Lawyer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
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
    
    public Connection estableceConexion(){
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena, userdb, passdb);
            System.out.println("SE CONECTO A LA BASE DE DATOS CORRECTAMENTE");
            
        } catch (Exception e) {
            System.out.println("NO SE CONECTO A LA BASE DE DATOS, ERROR"+e.toString());
        }
        
        return conectar;
    }
    
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

    

    public void mostrarDatos() {
    try {
        Connection conn = estableceConexion();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM usuarios";
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String lastname = rs.getString("lastname");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String user = rs.getString("user");
            String password = rs.getString("password");
            System.out.println(id + " | " + name + " | " + lastname + " | " + email + " | " + phone + " | " + user + " | " + password);
        }

        rs.close();
        stmt.close();
        cierraConexion();
    } catch (SQLException e) {
        System.out.println("Error al mostrar los datos: " + e.getMessage());
    }
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