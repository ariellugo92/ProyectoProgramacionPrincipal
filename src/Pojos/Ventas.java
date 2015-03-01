package Pojos;

/**
 *
 * @author ariellugo
 */
public class Ventas {
    private int id;
    private Productos productos;
    private String fechaVenta;
    private double cantidadVenta;
    private double precioVenta;

    public Ventas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Productos getProductos() {
        return productos;
    }

    public void setProductos(Productos productos) {
        this.productos = productos;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public double getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(double cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public double getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {
        this.precioVenta = precioVenta;
    }
}
