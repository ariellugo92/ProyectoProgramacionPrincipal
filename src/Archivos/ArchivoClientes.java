package Archivos;

import Pojos.Cliente;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author IaraDenisse
 */
public class ArchivoClientes {

    private final RandomAccessFile raf;
    private static final int LENGTH = 512;

    public ArchivoClientes() throws IOException {
        raf = RandomConector.buildRandom("Clientes.dat").getRandomAccessFile();
    }

    public void guardar(Cliente c) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);

        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(limitString(c.getNombre(), 30));
        raf.writeUTF(limitString(c.getTelefono(), 30));
        raf.writeUTF(limitString(c.getTipoCliente(), 30));

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

    public List<Cliente> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<Cliente> cli = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Cliente c = new Cliente();
            raf.seek(pos);
            c.setId(raf.readInt());
            c.setNombre(raf.readUTF());
            c.setTelefono(raf.readUTF());
            c.setTipoCliente(raf.readUTF());

            cli.add(c);
        }
        return cli;
    }

    public Cliente buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();

        if (id <= 0 || id > n) {
            return null;
        }

        long pos = 4 + (id - 1) * LENGTH;
        Cliente c = new Cliente();
        raf.seek(pos);
        c.setId(raf.readInt());
        c.setNombre(raf.readUTF());
        c.setTelefono(raf.readUTF());
        c.setTipoCliente(raf.readUTF());

        return c;
    }

    public Cliente buscarNombre(String nombre) throws IOException {

        List<Cliente> cli = encontrar();
        Cliente c = new Cliente();
        for (int i = 0; i < cli.size(); i++) {
            if (cli.get(i).getNombre().trim().equals(nombre)) {
                c = cli.get(i);
            }
        }
        return c;
    }

    public void modificar(Cliente c) throws IOException {
        int id = c.getId();

        long pos = 4 + (id - 1) * LENGTH;

        raf.seek(pos);
        raf.writeInt(c.getId());
        raf.writeUTF(limitString(c.getNombre(), 30));
        raf.writeUTF(limitString(c.getTelefono(), 30));
        raf.writeUTF(limitString(c.getTipoCliente(), 30));
    }

    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }
}
