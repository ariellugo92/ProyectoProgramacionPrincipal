package Pojos;

/**
 *
 * @author ariellugo92
 */
public class Compras {
    private int id;
    private Productos productos;
    private String fechaCompra;
    private double cantidadCompra;
    private double precioCompra;

    public Compras() {
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

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public double getCantidadCompra() {
        return cantidadCompra;
    }

    public void setCantidadCompra(double cantidadCompra) {
        this.cantidadCompra = cantidadCompra;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }
}
