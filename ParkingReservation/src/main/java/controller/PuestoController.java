/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;


import java.sql.ResultSet;
import java.sql.Statement;
import beans.Puesto ;
import connection.DBConnection;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class PuestoController implements IPuestoController {

    @Override
    public String listar(boolean ordenar, String orden) {

        Gson gson = new Gson();

        DBConnection con = new DBConnection();
        String sql = "Select * from puestos";

        if (ordenar == true) {
            sql += " order by zona " + orden;
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
  
}

