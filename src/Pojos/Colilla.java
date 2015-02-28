package Pojos;

/**
 *
 * @author ariellugo92
 */
public class Colilla {
    private int id;
    private double comision;
    private double antiguedad;
    private double inss;
    private double ir;
    private double sal_neto;
    private Empleados empleados;

    public Colilla() {
    }

    public Colilla(int id, double comision, double antiguedad, double inss, double ir, Empleados empleados,
                   double sal_neto) {
        this.id = id;
        this.comision = comision;
        this.antiguedad = antiguedad;
        this.inss = inss;
        this.ir = ir;
        this.empleados = empleados;
        this.sal_neto = sal_neto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    public double getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(double antiguedad) {
        this.antiguedad = antiguedad;
    }

    public double getInss() {
        return inss;
    }

    public void setInss(double inss) {
        this.inss = inss;
    }

    public double getIr() {
        return ir;
    }

    public void setIr(double ir) {
        this.ir = ir;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Empleados empleados) {
        this.empleados = empleados;
    }

    public double getSal_neto() {
        return sal_neto;
    }

    public void setSal_neto(double sal_neto) {
        this.sal_neto = sal_neto;
    }
}
