package Archivos;

import Pojos.Colilla;
import Pojos.Empleados;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo92
 */
public class ArchivoColillas {

    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    private final ArchivoEmpleados edao;
    
    public ArchivoColillas() throws IOException {
        raf = RandomConector.buildRandom("colillas.dat").getRandomAccessFile();
        edao = new ArchivoEmpleados();
    }
    
    public void guardar(Colilla c) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
        
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeDouble(c.getComision());
        raf.writeDouble(c.getAntiguedad());
        raf.writeDouble(c.getInss());
        raf.writeDouble(c.getIr());
        raf.writeDouble(c.getSal_neto());
        raf.writeInt(c.getEmpleados().getId());
        
        raf.seek(0);
        raf.writeInt(n + 1);
    }
    
    public List<Colilla> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<Colilla> col = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Colilla c = new Colilla();
            raf.seek(pos);
            c.setId(raf.readInt());
            c.setComision(raf.readDouble());
            c.setAntiguedad(raf.readDouble());
            c.setInss(raf.readDouble());
            c.setIr(raf.readDouble());
            c.setSal_neto(raf.readDouble());
            Empleados e = edao.buscarId(raf.readInt());
            c.setEmpleados(e);
            
            col.add(c);
        }
        return col;
    }
    
    public Colilla buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Colilla c = new Colilla();
        raf.seek(pos);
        c.setId(raf.readInt());
        c.setComision(raf.readDouble());
        c.setAntiguedad(raf.readDouble());
        c.setInss(raf.readDouble());
        c.setIr(raf.readDouble());
        c.setSal_neto(raf.readDouble());
        Empleados e = edao.buscarId(raf.readInt());
        c.setEmpleados(e);
        
        return c;
    }
    
    public void modificar(Colilla c) throws IOException {
        int id = c.getId();
         System.out.println(id);
        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(c.getId());
        raf.writeDouble(c.getComision());
        raf.writeDouble(c.getAntiguedad());
        raf.writeDouble(c.getInss());
        raf.writeDouble(c.getIr());
        raf.writeDouble(c.getSal_neto());
        raf.writeInt(c.getEmpleados().getId());
    }
    
    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }    
}
