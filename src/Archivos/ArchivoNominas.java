package Archivos;

import Pojos.Colilla;
import Pojos.Departamentos;
import Pojos.Empleados;
import Pojos.Nomina;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo92
 */
public class ArchivoNominas {
    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    private final ArchivoEmpleados edao;
    private final ArchivoColillas cdao;
    
    public ArchivoNominas() throws IOException {
        raf = RandomConector.buildRandom("colillas.dat").getRandomAccessFile();
        edao = new ArchivoEmpleados();
        cdao = new ArchivoColillas();
    }
    
    public void guardar(Nomina n) throws IOException {
        raf.seek(0);
        int k = raf.readInt();
        long pos = 4 + (LENGTH * k);
        
        raf.seek(pos);
        raf.writeInt(k + 1);
        raf.writeInt(n.getEmpleados().getId());
        raf.writeInt(n.getColilla().getId());
        
        raf.seek(0);
        raf.writeInt(k + 1);
    }
    
    public List<Nomina> encontrar() throws IOException {
        raf.seek(0);
        int k = raf.readInt();
        List<Nomina> nom = new ArrayList<>();
        
        for (int i = 0; i < k; i++) {
            long pos = 4 + (LENGTH * i);
            Nomina n = new Nomina();
            raf.seek(pos);
            n.setId(raf.readInt());
            Empleados e = edao.buscarNombre(raf.readUTF());
            Colilla c = cdao.buscarId(raf.readInt());
            n.setEmpleados(e);
            n.setColilla(c);
            
            nom.add(n);
        }
        return nom;
    }
    
    public Nomina buscarId(int id) throws IOException {
        raf.seek(0);
        int k = raf.readInt();
        
        if (id <= 0 || id > k) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Nomina n = new Nomina();
        raf.seek(pos);
        n.setId(raf.readInt());
        Empleados e = edao.buscarNombre(raf.readUTF());
        Colilla c = cdao.buscarId(raf.readInt());
        n.setEmpleados(e);
        n.setColilla(c);
        
        return n;
    }
    
    public void modificar(Nomina n) throws IOException {
        int id = n.getId();

        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(n.getId());
        raf.writeUTF(n.getEmpleados().getNombre());
        raf.writeInt(n.getColilla().getId());
    }
    
    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }    
}
