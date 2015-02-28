package Archivos;

import Pojos.Departamentos;
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
public class ArchivoEmpleados {

    private final RandomAccessFile raf;
    private static final int LENGTH = 1024;
    private final ArchivoDepartamentos ddao;
    
    public ArchivoEmpleados() throws IOException{
        raf = RandomConector.buildRandom("empleados.dat").getRandomAccessFile();
        ddao = new ArchivoDepartamentos();
    }
    
    public void guardar(Empleados e) throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);
    
        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(e.getNombre(),20));
        raf.writeUTF(limitString(e.getApellido(), 25));
        raf.writeUTF(limitString(e.getCedula(), 30));
        raf.writeUTF(e.getTelefono());
        raf.writeUTF(e.getCelular());
        raf.writeDouble(e.getSalario());
        raf.writeInt(e.getDepartamentos().getId());
        raf.writeUTF(e.getSexo());
        raf.writeUTF(e.getFecha());
        
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
    
    public List<Empleados> encontrar() throws IOException{
        raf.seek(0);
        int n = raf.readInt();
        List<Empleados> emp = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Empleados e = new Empleados();
            raf.seek(pos);
            e.setId(raf.readInt());
            e.setNombre(raf.readUTF());
            e.setApellido(raf.readUTF());
            e.setCedula(raf.readUTF());
            e.setTelefono(raf.readUTF());
            e.setCelular(raf.readUTF());
            e.setSalario(raf.readDouble());
            Departamentos d = ddao.buscarId(raf.readInt());
            e.setDepartamentos(d);
            e.setSexo(raf.readUTF());
            e.setFecha(raf.readUTF());
            
            emp.add(e);
        }
        return emp;
    }
    
    public void modificar(Empleados e) throws IOException {
        int id = e.getId();
      
        long pos = 4 + (id - 1) * LENGTH;
        
        raf.seek(pos);
        raf.writeInt(e.getId());
        raf.writeUTF(limitString(e.getNombre(),20));
        raf.writeUTF(limitString(e.getApellido(), 25));
        raf.writeUTF(limitString(e.getCedula(), 30));
        raf.writeUTF(e.getTelefono());
        raf.writeUTF(e.getCelular());
        raf.writeDouble(e.getSalario());
        raf.writeInt(e.getDepartamentos().getId());
        raf.writeUTF(e.getSexo());
        raf.writeUTF(e.getFecha());
    }
    
    public Empleados buscarNombre(String nombre) throws IOException {
        
        List<Empleados> emp = encontrar();
        Empleados e = new Empleados();
        for (int i = 0; i < emp.size(); i++) {
            if (emp.get(i).getNombre().trim().equals(nombre)) {
                e = emp.get(i);
            }
        }
        return e;
    }
    
    public Empleados buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        
        if (id <= 0 || id > n) {
            return null;
        }
        
        long pos = 4 + (id - 1) * LENGTH;
        Empleados e = new Empleados();
        raf.seek(pos);
        e.setId(raf.readInt());
        e.setNombre(raf.readUTF());
        e.setApellido(raf.readUTF());
        e.setCedula(raf.readUTF());
        e.setTelefono(raf.readUTF());
        e.setCelular(raf.readUTF());
        Departamentos d = ddao.buscarId(raf.readInt());
        e.setDepartamentos(d);
        e.setSalario(raf.readDouble());
        e.setSexo(raf.readUTF());
        e.setFecha(raf.readUTF());
        
        return e;
    }
    
    public void cerrar() throws IOException{
        if (raf != null) {
            raf.close();
        }
    }
}
