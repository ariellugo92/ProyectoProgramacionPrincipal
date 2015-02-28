package random;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomCreador {

    private RandomAccessFile raf;
    private final String prestamo;

    public RandomCreador(String prestamo) {
        this.prestamo = prestamo;
    }

    public RandomAccessFile getRandomAccessFile()
            throws IOException {

        File file = new File(prestamo);
        if (!file.exists()) {
            file.createNewFile();
            raf = new RandomAccessFile(file, "rw");
            raf.writeInt(0);
            raf.writeInt(0);
            return raf;
        } else if (raf == null) {
            raf = new RandomAccessFile(file, "rw");
            return raf;
        } else {
            return raf;
        }
    }
}
