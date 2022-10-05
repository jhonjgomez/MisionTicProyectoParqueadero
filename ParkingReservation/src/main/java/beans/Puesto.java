package beans;

public class Puesto {
    private int  id;
    private String tipo;
    private String zona;
    private double costo;
    private boolean disponible;

    public Puesto(int id, String tipo, String zona, double costo, boolean disponible) {
        this.id = id;
        this.tipo = tipo;
        this.zona = zona;
        this.costo = costo;
        this.disponible = disponible;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    @Override
    public String toString() {
        return "Puesto{" + "id=" + id + ", tipo=" + tipo + ", zona=" + zona + ", costo=" + costo + ", disponible=" + disponible + '}';
    }

    
}
