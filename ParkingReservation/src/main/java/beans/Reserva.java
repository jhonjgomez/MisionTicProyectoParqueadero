package beans;

import java.util.Date;

public class Reserva {
    private int id;
    private String tipo;
    private String zona;
    private Date fechaEntrada;
    

    public Reserva(int id, String tipo, String zona, Date fechaEntrada) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.tipo = tipo;
        this.zona = zona;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", tipo=" + tipo + ", zona=" + zona + ", fechaEntrada=" + fechaEntrada + '}';
    }
    
 
}
