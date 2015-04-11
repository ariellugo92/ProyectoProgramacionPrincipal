package Pojos;

/**
 *
 * @author ariellugo
 */
public class Productos {
    private int id;
    private String nombre;
    private CategoriaProd categoriaProd;
    private Proveedores proveedores;
    private String marca;
    private double precio;
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

    public Proveedores getProveedores() {
        return proveedores;
    }

    public void setProveedores(Proveedores proveedores) {
        this.proveedores = proveedores;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
