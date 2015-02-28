package Pojos;

/**
 *
 * @author ariellugo92
 */
public class Nomina {
    private int id;
    private Empleados empleados;
    private Colilla colilla;

    public Nomina() {
    }

    public Nomina(int id, Empleados empleados, Colilla colilla) {
        this.id = id;
        this.empleados = empleados;
        this.colilla = colilla;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public Colilla getColilla() {
        return colilla;
    }

    public void setColilla(Colilla colilla) {
        this.colilla = colilla;
    }
    
    
}
