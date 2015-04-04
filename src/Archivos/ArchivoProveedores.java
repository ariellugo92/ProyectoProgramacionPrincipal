package Archivos;

import Pojos.Proveedores;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author IaraDenisse
 */
public class ArchivoProveedores {

    private final RandomAccessFile raf;
    private static final int LENGTH = 300;
    
    public ArchivoProveedores() throws IOException {
        raf = RandomConector.buildRandom("proveedores.dat").getRandomAccessFile();
    }
    
    public void guardar(Proveedores p) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
        
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(p.getRuc(), 30));
        raf.writeUTF(limitString(p.getRazon_social(), 30));
        raf.writeUTF(limitString(p.getDireccion(), 50));
        raf.writeUTF(limitString(p.getTelefono(), 20));
        
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
    
    public List<Proveedores> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<Proveedores> prov = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Proveedores p = new Proveedores();
            raf.seek(pos);
            p.setId(raf.readInt());
            p.setRuc(raf.readUTF());
            p.setRazon_social(raf.readUTF());
            p.setDireccion(raf.readUTF());
            p.setTelefono(raf.readUTF());
            
            prov.add(p);
        }
        return prov;
    }
    
    public Proveedores buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Proveedores p = new Proveedores();
        raf.seek(pos);
        p.setId(raf.readInt());
        p.setRuc(raf.readUTF());
        p.setRazon_social(raf.readUTF());
        p.setDireccion(raf.readUTF());
        p.setTelefono(raf.readUTF());
        
        return p;
    }
    
    public Proveedores buscarRazon_Social(String nombre) throws IOException {
        
        List<Proveedores> prov = encontrar();
        Proveedores p = new Proveedores();
        for (int i = 0; i < prov.size(); i++) {
            if (prov.get(i).getRazon_social().trim().equals(nombre)) {
                p = prov.get(i);
            }
        }
        return p;
    }
    
    public void modificar(Proveedores p) throws IOException {
        int id = p.getId();

        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(p.getId());
        raf.writeUTF(limitString(p.getRuc(), 30));
        raf.writeUTF(limitString(p.getRazon_social(), 30));
        raf.writeUTF(limitString(p.getDireccion(), 50));
        raf.writeUTF(limitString(p.getTelefono(), 20));
    }
    
    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    } 
}
