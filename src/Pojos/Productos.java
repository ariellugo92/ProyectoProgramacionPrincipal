package Pojos;

/**
 *
 * @author ariellugo
 */
public class Productos {
    private int id;
    private String nombre;
    private CategoriaProd categoriaProd;
    private String marca;
    private double cantidad;
    private String unidadMedida;

    public Productos() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public CategoriaProd getCategoriaProd() {
        return categoriaProd;
    }

    public void setCategoriaProd(CategoriaProd categoriaProd) {
        this.categoriaProd = categoriaProd;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
