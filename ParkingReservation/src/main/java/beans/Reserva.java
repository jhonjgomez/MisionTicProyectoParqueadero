package beans;

import java.util.Date;

public class Reserva {
    private int id;
    private String username;
    private Date fechaEntrada;
    private boolean disponible;
    private String tipo;

    public Reserva(int id, String username, Date fechaEntrada, boolean disponible, String tipo) {
        this.id = id;
        this.username = username;
        this.fechaEntrada = fechaEntrada;
        this.disponible = disponible;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Reserva{" + "id=" + id + ", username=" + username + ", fechaEntrada=" + fechaEntrada + ", disponible=" + disponible + ", tipo=" + tipo + '}';
    }

    
}
