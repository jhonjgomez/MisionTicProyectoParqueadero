package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    Connection connection;
    static String bd = "parking_reservation";
    static String port = "3306";
    static String login = "root";
    static String password = "admin";

    public DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:"+this.port + "/" + this.bd;
            connection = DriverManager.getConnection(url,this.login, this.password);
            System.out.println("Conexion Exitosa");
            
        } catch (Exception ex) {
            System.out.println("Error en la conexion" + ex);          
        }
    }

    public Connection getConnection() {
        return connection;
    }
    
    public void desconectar(){
        connection = null;
    }
}
