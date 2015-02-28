package Pojos;

/**
 *
 * @author ariellugo92
 */
public class Departamentos {
    private int id;
    private String nombre;
    private String Descripcion;

    public Departamentos() {
    }

    public Departamentos(int id, String nombre, String Descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.Descripcion = Descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
}
