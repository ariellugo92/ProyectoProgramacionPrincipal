package Archivos;

import Pojos.Cuentas;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo92
 */
public class ArchivoCuentas {

    private final RandomAccessFile raf;
    private static final int LENGTH = 512;
    
    public ArchivoCuentas() throws IOException{
        raf = RandomConector.buildRandom("usuarios.dat").getRandomAccessFile();
    }
    
    public void guardar(Cuentas c) throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
        
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(c.getUsuario(),30));
        raf.writeUTF(limitString(c.getContraseña(),50));
        
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
    
    public List<Cuentas> encontrar() throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        List<Cuentas> cta = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Cuentas c = new Cuentas();
            raf.seek(pos);
            c.setId(raf.readInt());
            c.setUsuario(raf.readUTF());
            c.setContraseña(raf.readUTF());
            
            cta.add(c);
        }
        return cta;
    }
    
    public void cerrar() throws IOException{
        if (raf != null) {
            raf.close();
        }
    }
}
