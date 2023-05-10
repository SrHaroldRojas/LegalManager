
package dbprueba3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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