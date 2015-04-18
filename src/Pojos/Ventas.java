package Pojos;


/**
 *
 * @author ariellugo
 */
public class Ventas {
    private int id;
    private String fechaVenta;
    private String tipoVenta;
    private String estadoIVA;
    private Cliente cliente;
    private detalleVenta dventa;

    public Ventas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getEstadoIVA() {
        return estadoIVA;
    }

    public void setEstadoIVA(String estadoIVA) {
        this.estadoIVA = estadoIVA;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public detalleVenta getDventa() {
        return dventa;
    }

    public void setDventa(detalleVenta dventa) {
        this.dventa = dventa;
    }

}
