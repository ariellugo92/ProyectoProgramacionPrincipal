package Archivos;

import Pojos.Productos;
import Pojos.Ventas;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo
 */
public class ArchivoVentas {
    
    private final RandomAccessFile raf;
    private static final int LENGTH = 1024;
    private final ArchivoProductos pdao;
    
    public ArchivoVentas() throws IOException{
        raf = RandomConector.buildRandom("ventas.dat").getRandomAccessFile();
        pdao = new ArchivoProductos();
    }
    
    public void guardar(Ventas v) throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
    
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(v.getCliente());
        raf.writeUTF(v.getTelfCliente());
        raf.writeUTF(v.getTipoCliente());
        raf.writeUTF(v.getFactura());
        raf.writeUTF(v.getFechaVenta());
        raf.writeUTF(v.getTipoVenta());
        raf.writeUTF(v.getEstadoIVA());
        raf.write(v.getProductos());
        raf.writeInt(v.getProductos().getId());
        raf.writeUTF(v.getFechaVenta());
        raf.writeDouble(v.getCantidadVenta());
        raf.writeDouble(v.getPrecioVenta());
        
        raf.seek(0);
        raf.writeInt(n + 1);
    }
    
    public List<Ventas> encontrar() throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        List<Ventas> Ventas = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Ventas v = new Ventas();
            raf.seek(pos);
            v.setId(raf.readInt());
            Productos p = pdao.buscarId(raf.readInt());
            v.setProductos(p);
            v.setFechaVenta(raf.readUTF());
            v.setCantidadVenta(raf.readDouble());
            v.setPrecioVenta(raf.readDouble());
            
            Ventas.add(v);
        }
        return Ventas;
    }
    
    public void modificar(Ventas v) throws IOException {
        int id = v.getId();
      
        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(v.getId());
        raf.writeInt(v.getProductos().getId());
        raf.writeUTF(v.getFechaVenta());
        raf.writeDouble(v.getCantidadVenta());
        raf.writeDouble(v.getPrecioVenta());
    }
    
    public Ventas buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Ventas v = new Ventas();
        raf.seek(pos);
        v.setId(raf.readInt());
        Productos p = pdao.buscarId(raf.readInt());
        v.setProductos(p);
        v.setFechaVenta(raf.readUTF());
        v.setCantidadVenta(raf.readDouble());
        v.setPrecioVenta(raf.readDouble());
        
        return v;
    }
    
    public void cerrar() throws IOException{
        if (raf != null) {
            raf.close();
        }
    }
}
