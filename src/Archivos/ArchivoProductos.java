package Archivos;

import Pojos.CategoriaProd;
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
public class ArchivoProductos {
    
    private final RandomAccessFile raf;
    private static final int LENGTH = 1024;
    private final ArchivoCategoriaProd cpdao;
    
    public ArchivoProductos() throws IOException{
        raf = RandomConector.buildRandom("productos.dat").getRandomAccessFile();
        cpdao = new ArchivoCategoriaProd();
    }
    
    public void guardar(Productos p) throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
    
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(p.getNombre(),20));
        raf.writeInt(p.getCategoriaProd().getId());
        raf.writeUTF(limitString(p.getMarca(), 20));
        raf.writeDouble(p.getPrecio());
        raf.writeDouble(p.getCantidad());
        raf.writeUTF(p.getUnidadMedida());
        
        raf.seek(0);
        raf.writeInt(n + 1);
    }
    
    private String limitString(String text, int length){
        StringBuffer buffer;
        if (text == null) {
            buffer = new StringBuffer(length);
        }else{
            buffer = new StringBuffer(text);
            buffer.setLength(length);
        }
        return buffer.toString();
    }
    
    public List<Productos> encontrar() throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        List<Productos> prod = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Productos p = new Productos();
            raf.seek(pos);
            p.setId(raf.readInt());
            p.setNombre(raf.readUTF());
            CategoriaProd cp = cpdao.buscarId(raf.readInt());
            p.setCategoriaProd(cp);
            p.setMarca(raf.readUTF());
            p.setPrecio(raf.readDouble());
            p.setCantidad(raf.readDouble());
            p.setUnidadMedida(raf.readUTF());
            
            prod.add(p);
        }
        return prod;
    }
    
    public void modificar(Productos p) throws IOException {
        int id = p.getId();
      
        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(p.getId());
        raf.writeUTF(limitString(p.getNombre(),20));
        raf.writeInt(p.getCategoriaProd().getId());
        raf.writeUTF(limitString(p.getMarca(), 20));
        raf.writeDouble(p.getPrecio());
        raf.writeDouble(p.getCantidad());
        raf.writeUTF(p.getUnidadMedida());
    }
    
    public Productos buscarNombre(String nombre) throws IOException {
        
        List<Productos> prod = encontrar();
        Productos p = new Productos();
        for (int i = 0; i < prod.size(); i++) {
            if (prod.get(i).getNombre().trim().equals(nombre)) {
                p = prod.get(i);
            }
        }
        return p;
    }
    
    public Productos buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Productos p = new Productos();
        raf.seek(pos);
        p.setId(raf.readInt());
        p.setNombre(raf.readUTF());
        CategoriaProd cp = cpdao.buscarId(raf.readInt());
        p.setCategoriaProd(cp);
        p.setMarca(raf.readUTF());
        p.setPrecio(raf.readDouble());
        p.setCantidad(raf.readDouble());
        p.setUnidadMedida(raf.readUTF());
        
        return p;
    }
    
    public void cerrar() throws IOException{
        if (raf != null) {
            raf.close();
        }
    }
}
