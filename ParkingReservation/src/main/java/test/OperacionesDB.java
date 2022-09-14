
package test;

import beans.Puesto;
import connection.DBConnection;
import java.sql.ResultSet;
import java.sql.Statement;


public class OperacionesDB {
    public static void main(String[] args) {
        listarPuesto();
       
    }
    
    public static void actualizarPuesto(int id, String tipo){
        DBConnection con = new DBConnection();
        String sql = "UPDATE puestos SET tipo = '" + tipo +"' WHERE id = "+ id;
        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            con.desconectar();
        }
    }
    
    public static void listarPuesto(){
        DBConnection con = new DBConnection();
        String sql = "SELECT * FROM puestos";
        try {
            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String zona = rs.getString("zona");
                String costo = rs.getString("costo");
                boolean disponible = rs.getBoolean("disponible");
                
                Puesto puesto = new Puesto(id, tipo, zona, costo, disponible);
                System.out.println(puesto.toString());
            }
            st.executeQuery(sql);
            
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        finally {
            con.desconectar();
        }
    }   
}

