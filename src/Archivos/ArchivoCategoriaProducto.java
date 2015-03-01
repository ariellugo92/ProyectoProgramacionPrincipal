package Archivos;

import Pojos.CategoriaProductos;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo
 */
public class ArchivoCategoriaProducto {
    
    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    
    public ArchivoCategoriaProducto() throws IOException {
        raf = RandomConector.buildRandom("Categorias.dat").getRandomAccessFile();
    }
    
    public void guardar(CategoriaProductos c) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
        
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(c.getNombre(), 30));
        raf.writeUTF(limitString(c.getDescripcion(), 150));
        
        raf.seek(0);
        raf.writeInt(n + 1);
    }
    
    private String limitString(String text, int length) {
        StringBuffer buffer;
        if (text == null) {
            buffer = new StringBuffer(length);
        } else {
            buffer = new StringBuffer(text);
            buffer.setLength(length);
        }
        return buffer.toString();
    }
    
    public List<CategoriaProductos> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<CategoriaProductos> catg = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            CategoriaProductos c = new CategoriaProductos();
            raf.seek(pos);
            c.setId(raf.readInt());
            c.setNombre(raf.readUTF());
            c.setDescripcion(raf.readUTF());
            
            catg.add(c);
        }
        return catg;
    }
    
    public CategoriaProductos buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        CategoriaProductos c = new CategoriaProductos();
        raf.seek(pos);
        c.setId(raf.readInt());
        c.setNombre(raf.readUTF());
        c.setDescripcion(raf.readUTF());
        
        return c;
    }
    
    public CategoriaProductos buscarNombre(String nombre) throws IOException {
        
        List<CategoriaProductos> dpto = encontrar();
        CategoriaProductos c = new CategoriaProductos();
        for (int i = 0; i < dpto.size(); i++) {
            if (dpto.get(i).getNombre().trim().equals(nombre)) {
                c = dpto.get(i);
            }
        }
        return c;
    }
    
    public void modificar(CategoriaProductos c) throws IOException {
        int id = c.getId();

        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(c.getId());
        raf.writeUTF(c.getNombre());
        raf.writeUTF(c.getDescripcion());
    }
    
    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }    
}