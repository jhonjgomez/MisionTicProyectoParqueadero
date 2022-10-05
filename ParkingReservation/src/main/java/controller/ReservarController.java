package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

import com.google.gson.Gson;

import beans.Reserva;
import connection.DBConnection;

public class ReservarController implements IReservarController {

    @Override
    public String listarReservas(String username) {
        Gson gson = new Gson();

        DBConnection con = new DBConnection();

        String sql = "Select l.id, l.tipo, l.zona, a.fechaEntrada from puestos l "
                + "inner join reserva a on l.id = a.id inner join usuarios u on a.username = u.username "
                + "where a.username = '" + username + "'";
        
        List<String> reservas = new ArrayList<String>();

        try {

            Statement st = con.getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("id");
                String tipo = rs.getString("tipo");
                String zona = rs.getString("zona");
                Date fechaEntrada = rs.getDate("fechaEntrada");

                Reserva reserva = new Reserva(id, tipo, zona, fechaEntrada);

                reservas.add(gson.toJson(reserva));
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            con.desconectar();
        }
        return gson.toJson(reservas);
    }
}
