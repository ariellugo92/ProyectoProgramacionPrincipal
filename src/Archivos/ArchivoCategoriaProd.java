/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Archivos;

import Pojos.CategoriaProd;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author IaraDenisse
 */
public class ArchivoCategoriaProd {
    
    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    
    public ArchivoCategoriaProd() throws IOException {
        raf = RandomConector.buildRandom("categoria de productos.dat").getRandomAccessFile();
    }
    
    public void guardar(CategoriaProd cp) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
        
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(cp.getNombre(), 30));
        raf.writeUTF(limitString(cp.getDescripcion(), 150));
        
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
    
    public List<CategoriaProd> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<CategoriaProd> catgProd = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            CategoriaProd cp = new CategoriaProd();
            raf.seek(pos);
            cp.setId(raf.readInt());
            cp.setNombre(raf.readUTF());
            cp.setDescripcion(raf.readUTF());
            
            catgProd.add(cp);
        }
        return catgProd;
    }
    
    public CategoriaProd buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        CategoriaProd cp = new CategoriaProd();
        raf.seek(pos);
        cp.setId(raf.readInt());
        cp.setNombre(raf.readUTF());
        cp.setDescripcion(raf.readUTF());
        
        return cp;
    }
    
    public CategoriaProd buscarNombre(String nombre) throws IOException {
        
        List<CategoriaProd> catgProd = encontrar();
        CategoriaProd cp = new CategoriaProd();
        for (int i = 0; i < catgProd.size(); i++) {
            if (catgProd.get(i).getNombre().trim().equals(nombre)) {
                cp =  catgProd.get(i);
            }
        }
        return cp;
    }
    
    public void modificar(CategoriaProd cp) throws IOException {
        int id = cp.getId();

        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(cp.getId());
        raf.writeUTF(cp.getNombre());
        raf.writeUTF(cp.getDescripcion());
    }
    
    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }    
}
