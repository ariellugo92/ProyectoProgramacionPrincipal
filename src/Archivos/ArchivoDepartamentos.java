package Archivos;

import Pojos.Departamentos;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo92
 */
public class ArchivoDepartamentos {
    
    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    
    public ArchivoDepartamentos() throws IOException {
        raf = RandomConector.buildRandom("departamentos.dat").getRandomAccessFile();
    }
    
    public void guardar(Departamentos d) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
        
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(d.getNombre(), 30));
        raf.writeUTF(limitString(d.getDescripcion(), 150));
        
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
    
    public List<Departamentos> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<Departamentos> dpto = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Departamentos d = new Departamentos();
            raf.seek(pos);
            d.setId(raf.readInt());
            d.setNombre(raf.readUTF());
            d.setDescripcion(raf.readUTF());
            
            dpto.add(d);
        }
        return dpto;
    }
    
    public Departamentos buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Departamentos d = new Departamentos();
        raf.seek(pos);
        d.setId(raf.readInt());
        d.setNombre(raf.readUTF());
        d.setDescripcion(raf.readUTF());
        
        return d;
    }
    
    public Departamentos buscarNombre(String nombre) throws IOException {
        
        List<Departamentos> dpto = encontrar();
        Departamentos d = new Departamentos();
        for (int i = 0; i < dpto.size(); i++) {
            if (dpto.get(i).getNombre().trim().equals(nombre)) {
                d = dpto.get(i);
            }
        }
        return d;
    }
    
    public void modificar(Departamentos d) throws IOException {
        int id = d.getId();

        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(d.getId());
        raf.writeUTF(d.getNombre());
        raf.writeUTF(d.getDescripcion());
    }
    
    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }    
}
