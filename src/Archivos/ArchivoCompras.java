package Archivos;

import Pojos.Compras;
import Pojos.Productos;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo
 */
public class ArchivoCompras {
    
    private final RandomAccessFile raf;
    private static final int LENGTH = 1024;
    private final ArchivoProductos pdao;
    
    public ArchivoCompras() throws IOException{
        raf = RandomConector.buildRandom("compras.dat").getRandomAccessFile();
        pdao = new ArchivoProductos();
    }
    
    public void guardar(Compras c) throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
    
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeInt(c.getProductos().getId());
        raf.writeUTF(c.getFechaCompra());
        raf.writeDouble(c.getCantidadCompra());
        raf.writeDouble(c.getPrecioCompra());
        
        raf.seek(0);
        raf.writeInt(n + 1);
    }
    
    public List<Compras> encontrar() throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        List<Compras> compras = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Compras c = new Compras();
            raf.seek(pos);
            c.setId(raf.readInt());
            Productos p = pdao.buscarId(raf.readInt());
            c.setProductos(p);
            c.setFechaCompra(raf.readUTF());
            c.setCantidadCompra(raf.readDouble());
            c.setPrecioCompra(raf.readDouble());
            
            compras.add(c);
        }
        return compras;
    }
    
    public void modificar(Compras c) throws IOException {
        int id = c.getId();
      
        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(c.getId());
        raf.writeInt(c.getProductos().getId());
        raf.writeUTF(c.getFechaCompra());
        raf.writeDouble(c.getCantidadCompra());
        raf.writeDouble(c.getPrecioCompra());
    }
    
    public Compras buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Compras c = new Compras();
        raf.seek(pos);
        c.setId(raf.readInt());
        Productos p = pdao.buscarId(raf.readInt());
        c.setProductos(p);
        c.setFechaCompra(raf.readUTF());
        c.setCantidadCompra(raf.readDouble());
        c.setPrecioCompra(raf.readDouble());
        
        return c;
    }
    
    public void cerrar() throws IOException{
        if (raf != null) {
            raf.close();
        }
    }
}
