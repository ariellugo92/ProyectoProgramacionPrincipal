package Pojos;

import java.util.List;

/**
 *
 * @author ariellugo
 */
public class Ventas {
    private int id;
    private String cliente;
    private String telfCliente;
    private String tipoCliente;
    private String factura;
    private String fechaVenta;
    private String tipoVenta;
    private String estadoIVA;
    private List<Productos> productos;
    private double descuento;
    private double iva;
    private double totalVenta;

    public Ventas() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getTelfCliente() {
        return telfCliente;
    }

    public void setTelfCliente(String telfCliente) {
        this.telfCliente = telfCliente;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
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

    public List<Productos> getProductos() {
        return productos;
    }

    public void setProductos(List<Productos> productos) {
        this.productos = productos;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }
}
