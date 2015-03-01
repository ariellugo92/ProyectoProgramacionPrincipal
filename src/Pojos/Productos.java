package Pojos;

/**
 *
 * @author ariellugo
 */
public class Productos {
    private int id;
    private String nombre;
    private CategoriaProductos categoriaProductos;

    public Productos() {
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

    public CategoriaProductos getCategoriaProductos() {
        return categoriaProductos;
    }

    public void setCategoriaProductos(CategoriaProductos categoriaProductos) {
        this.categoriaProductos = categoriaProductos;
    }
}
