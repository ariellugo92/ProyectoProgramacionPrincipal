package Archivos;

import Pojos.Cliente;
import Pojos.Ventas;
import Pojos.detalleVenta;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import random.RandomConector;

/**
 *
 * @author ariellugo
 */
public class ArchivoVentas {

    private final RandomAccessFile raf;
    private static final int LENGTH = 1024;
    private final ArchivoClientes cdao;
    private final ArchivoVentaDetalle vddao;

    public ArchivoVentas() throws IOException {
        raf = RandomConector.buildRandom("ventas.dat").getRandomAccessFile();
        cdao = new ArchivoClientes();
        vddao = new ArchivoVentaDetalle();
    }

    public void guardar(Ventas v) throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        long pos = 4 + (LENGTH * n);

        raf.seek(pos);
        raf.writeInt(n + 1);
        raf.writeUTF(v.getFechaVenta());
        raf.writeUTF(v.getTipoVenta());
        raf.writeUTF(v.getEstadoIVA());
        raf.writeInt(v.getCliente().getId());
        raf.writeInt(v.getDventa().getId());

        raf.seek(0);
        raf.writeInt(n + 1);
    }

    public List<Ventas> encontrar() throws IOException {
        raf.seek(0);
        int n = raf.readInt();
        List<Ventas> Ventas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            long pos = 4 + (LENGTH * i);
            Ventas v = new Ventas();
            raf.seek(pos);
            v.setId(raf.readInt());
            v.setFechaVenta(raf.readUTF());
            v.setTipoVenta(raf.readUTF());
            v.setEstadoIVA(raf.readUTF());
            Cliente c = cdao.buscarId(raf.readInt());
            v.setCliente(c);
            detalleVenta dv = vddao.buscarId(raf.readInt());
            v.setDventa(dv);

            Ventas.add(v);
        }
        return Ventas;
    }

    public void modificar(Ventas v) throws IOException {
        int id = v.getId();

        long pos = 4 + (id - 1) * LENGTH;

        raf.seek(pos);
        raf.writeInt(v.getId());
        raf.writeUTF(v.getFechaVenta());
        raf.writeUTF(v.getTipoVenta());
        raf.writeUTF(v.getEstadoIVA());
        raf.writeInt(v.getCliente().getId());
        raf.writeInt(v.getDventa().getId());
    }

    public Ventas buscarId(int id) throws IOException {
        raf.seek(0);
        int n = raf.readInt();

        if (id <= 0 || id > n) {
            return null;
        }

        long pos = 4 + (id - 1) * LENGTH;
        Ventas v = new Ventas();
        raf.seek(pos);
        v.setId(raf.readInt());
        v.setFechaVenta(raf.readUTF());
        v.setTipoVenta(raf.readUTF());
        v.setEstadoIVA(raf.readUTF());
        Cliente c = cdao.buscarId(raf.readInt());
        v.setCliente(c);
        detalleVenta dv = vddao.buscarId(raf.readInt());
        v.setDventa(dv);

        return v;
    }

    public void cerrar() throws IOException {
        if (raf != null) {
            raf.close();
        }
    }
}
