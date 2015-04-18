package Archivos;

import Pojos.Productos;
import Pojos.Ventas;
import Pojos.detalleVenta;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author IaraDenisse
 */
public class ArchivoVentaDetalle {

    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    private final ArchivoProductos pdao;

    public ArchivoVentaDetalle() throws IOException {
        raf = RandomConector.buildRandom("ventaDetalles.dat").getRandomAccessFile();
        pdao = new ArchivoProductos();
    }

    public void guardar(detalleVenta dv) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);

        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeInt(dv.getProductos().getId());
        raf.writeDouble(dv.getPrecio());
        raf.writeDouble(dv.getCantidad());
        raf.writeDouble(dv.getDescuento());

        raf.seek(0);
        raf.writeInt(n + 1);
    }

    public List<detalleVenta> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<detalleVenta> detv = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            detalleVenta dv = new detalleVenta();
            raf.seek(pos);
            dv.setId(raf.readInt());
            Productos p = pdao.buscarId(raf.readInt());
            dv.setProductos(p);
            dv.setPrecio(raf.readDouble());
            dv.setCantidad(raf.readDouble());
            dv.setDescuento(raf.readDouble());

            detv.add(dv);
        }
        return detv;
    }

    public detalleVenta buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();

        if (id <= 0 || id > n) {
            return null;
        }

        long pos = 4 + (id - 1) * LENGTH;
        detalleVenta dv = new detalleVenta();
        raf.seek(pos);
        dv.setId(raf.readInt());
            Productos p = pdao.buscarId(raf.readInt());
            dv.setProductos(p);
            dv.setPrecio(raf.readDouble());
            dv.setCantidad(raf.readDouble());
            dv.setDescuento(raf.readDouble());

        return dv;
    }

//    public detalleVenta buscarNombre(String nombre) throws IOException {
//
//        List<detalleVenta> dev = encontrar();
//        detalleVenta dv = new detalleVenta();
//        for (int i = 0; i < dev.size(); i++) {
//            if (dev.get(i).getNombre().trim().equals(nombre)) {
//                dv = dev.get(i);
//            }
//        }
//        return dv;
//    }

    public void modificar(detalleVenta dv) throws IOException {
        int id = dv.getId();

        long pos = 4 + (id - 1) * LENGTH;

        raf.seek(pos);
        raf.writeInt(dv.getId());
        raf.writeInt(dv.getProductos().getId());
        raf.writeDouble(dv.getPrecio());
        raf.writeDouble(dv.getCantidad());
        raf.writeDouble(dv.getDescuento());
    }

    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }
}
