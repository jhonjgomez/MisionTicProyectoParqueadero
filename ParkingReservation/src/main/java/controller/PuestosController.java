/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import beans.Puesto;
import connection.DBConnection;
import com.google.gson.Gson;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PuestosController implements IPuestoController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from puestos LIMIT 100";

        if (ordenar == true) {
            sql += " order by genero " + orden;
        }

        List<String> puestos = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {

                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String zona = rs.getString("zona");
                double costo = rs.getDouble("costo");
                boolean disponible = rs.getBoolean("disponible");

                Puesto puesto = new Puesto(id, tipo, zona, costo, disponible);

                puestos.add(gson.toJson(puesto));

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }

        return gson.toJson(puestos);

    }

    @Override
    public String reservar(int id, String username) {

        Timestamp fecha = new Timestamp(new Date().getTime());
        DBConnection con = new DBConnection();

        String sql = "Insert into reserva values ('" + id + "', '" + username + "', '" + fecha + "')";

        try {

            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            String modificar = modificar(id);
            if (modificar.equals("true")) {
                return "true";
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }
        return "false";

    }

    @Override
    public String modificar(int id) {

        DBConnection con = new DBConnection();
        String sql = "Update puestos set disponible = (disponible - 1) where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";

    }

    @Override
    public String retirar(int id, String username) {
        DBConnection con = new DBConnection();
        String sql = "Delete from reserva where id= " + id + " and username = '"
                + username + "' limit 1";

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            this.sumarCantidad(id);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

    @Override
    public String sumarCantidad(int id) {
    DBConnection con = new DBConnection();
    
    // Falta boolean 
        String sql = "Update puestos set disponible = (Select disponible from puestos where id = " 
                + id + ") + 1 where id = " + id;

        try {
            Statement st = con.getConnection().createStatement();
            st.executeUpdate(sql);

            return "true";
        } catch (Exception ex) {
            System.out.println(ex.toString());
        } finally {
            con.desconectar();
        }

        return "false";
    }

}
