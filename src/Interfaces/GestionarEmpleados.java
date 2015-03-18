/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoDepartamentos;
import Archivos.ArchivoEmpleados;
import Pojos.CopiarImagenes;
import Pojos.Departamentos;
import Pojos.Empleados;
import Pojos.ExportarExcel;
import Validaciones.Validador;
import Validaciones.limitarCaracter;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author IaraDenisse
 */
public class GestionarEmpleados extends javax.swing.JDialog {

    ImageIcon iconOriginal = new ImageIcon("src/Imagenes/Iconos/fotoEmp.png");
    String foto = iconOriginal.toString();
    Path p = Paths.get(foto);
    Path p2 = Paths.get(foto);
    
    DefaultTableModel modelo;
    private FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de imagen", "jpg");
    String ruta;
    CopiarImagenes c = new CopiarImagenes();
    List<Empleados> empleados;
    ArchivoEmpleados cc;

    public void cargar(JComboBox jcbElegir) throws IOException {

        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        List<Departamentos> dpto = ddao.encontrar();

        for (Departamentos d : dpto) {
            jcbElegir.addItem(d.getNombre().trim());
        }
    }

    public void tabla() {
        String encabezados[] = {"Id", "Nombre", "Apellido", "Cedula", "Salario", "Departamento", "Sexo", "Fecha de ingresp"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, encabezados);
        TablaEmpleados.setModel(modelo);
        tamañoColumnas();
    }

    public void tamañoColumnas() {

        TableColumnModel columnas = TablaEmpleados.getColumnModel();
        columnas.getColumn(0).setPreferredWidth(15);
        columnas.getColumn(1).setPreferredWidth(100);
        columnas.getColumn(2).setPreferredWidth(100);
        columnas.getColumn(3).setPreferredWidth(100);
        columnas.getColumn(4).setPreferredWidth(40);
        columnas.getColumn(5).setPreferredWidth(110);
        columnas.getColumn(6).setPreferredWidth(40);
        columnas.getColumn(0).setPreferredWidth(25);
    }

    public GestionarEmpleados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtNombreEmp.requestFocus();
        validaciones();
        ruta = "";
        botonModificarEmp.setVisible(false);
        botonLimpiarModifica.setVisible(false);
        labelInform.setVisible(false);
        tabla();
        try {
            ArchivoEmpleados edao = new ArchivoEmpleados();
            List<Empleados> emp = edao.encontrar();
            agregarDatostabla(emp);
            cargar(this.comboListaDptos);
            cargar(this.comboDptoModifica);
//             cc  = new ArchivoEmpleados();
//            empleados = cc.encontrar();
//            for (int i = 0; i < empleados.size(); i++) {
//                jcbEmpleados.addItem(empleados.get(i).getNombre().trim()+" "+empleados.get(i).getApellido().trim()+"|"+empleados.get(i).getCedula().trim());
//            }
        } catch (IOException ex) {
            Logger.getLogger(GestionarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void validaciones() {
        Validador v = new Validador();
        v.soloLetras(txtNombreEmp, labelInform);
        v.soloLetras(txtApellidoEmp, labelInform);
        v.soloNumeros(txtTelefonoEmp, labelInform);
        v.soloNumeros(txtCelularEmp, labelInform);
        v.soloNumeros(txtSalarioEmp, labelInform);
        //---------------------------------------
        v.soloLetras(txtNombreEmp_Modicando, labelInform);
        v.soloLetras(txtApellidoEmp_Modicando, labelInform);
        v.soloNumeros(txtTelefonoEmp_Modicando, labelInform);
        v.soloNumeros(txtCelularEmp_Modicando, labelInform);
        v.soloNumeros(txtSalarioEmp_Modicando, labelInform);
        //---------------------------------------
        txtTelefonoEmp.setDocument(new limitarCaracter(txtTelefonoEmp, 8));
        txtCelularEmp.setDocument(new limitarCaracter(txtCelularEmp, 8));
        txtCelularEmp_Modicando.setDocument(new limitarCaracter(txtCelularEmp_Modicando, 8));
        txtTelefonoEmp_Modicando.setDocument(new limitarCaracter(txtTelefonoEmp_Modicando, 8));
    }

    public void limpiarAgrega() {

        txtNombreEmp.setText("");
        txtApellidoEmp.setText("");
        txtCedulaEmp.setText("");
        txtTelefonoEmp.setText("");
        txtCelularEmp.setText("");
        txtSalarioEmp.setText("");
        txtFechaIngresoEmp.setText("");
        comboListaDptos.setSelectedIndex(0);
        txtNombreEmp.requestFocus();
        sexoItem.clearSelection();
        ImageIcon iconOriginal = new ImageIcon("src/Imagenes/Iconos/fotoEmp.png");
        labelFotoEmp.setIcon(iconOriginal);
    }

    public void limpiarModifica() {

        txtNombreEmp_Modicando.setText("");
        txtApellidoEmp_Modicando.setText("");
        txtCedulaEmp_Modifica.setText("");
        txtTelefonoEmp_Modicando.setText("");
        txtCelularEmp_Modicando.setText("");
        comboDptoModifica.setSelectedIndex(0);
        txtSalarioEmp_Modicando.setText("");
        txtFechaEmp_Modifica.setText("");
        ImageIcon iconOriginal = new ImageIcon("src/Imagenes/Iconos/fotoEmp.png");
        labelFotoEmp1.setIcon(iconOriginal);
    }

    public void agregarEmpleados() throws IOException {

        ArchivoEmpleados edao = new ArchivoEmpleados();
        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        List<Empleados> emp = edao.encontrar();
        Empleados e = new Empleados();
        Validador v = new Validador();

        JOptionPane.showMessageDialog(this, "Paso 1");

        e.setNombre(txtNombreEmp.getText());
        e.setApellido(txtApellidoEmp.getText());
        e.setCedula(txtCedulaEmp.getText());
        if (v.validarTel(txtTelefonoEmp)) {
            JOptionPane.showMessageDialog(null, "Numero de telefono ingresado, incorrecto!");
            txtTelefonoEmp.setText("");
            txtTelefonoEmp.requestFocus();
            return;
        } else {
            e.setTelefono(txtTelefonoEmp.getText());
        }
        if (v.validarCel(txtCelularEmp)) {
            JOptionPane.showMessageDialog(null, "Numero de celular ingresado, incorrecto!");
            txtCelularEmp.setText("");
            txtCelularEmp.requestFocus();
            return;
        } else {
            e.setCelular(txtCelularEmp.getText());
        }
        e.setSalario(Double.parseDouble(txtSalarioEmp.getText()));
        
        Departamentos d = ddao.buscarNombre(this.comboListaDptos.getSelectedItem().toString().trim());
        if (comboListaDptos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Lo sentimos ese no es un departamento");
            System.out.println(comboListaDptos.getSelectedIndex());
            return;
        } else {
            e.setDepartamentos(d);
        }
        if (itemHombreElegidoEmp.isSelected()) {
            e.setSexo(itemHombreElegidoEmp.getLabel());
        } else if (itemMujerElegidoEmp.isSelected()) {
            e.setSexo(itemMujerElegidoEmp.getLabel());
        }
        e.setFecha(txtFechaIngresoEmp.getText());

        c.copy(p, txtCedulaEmp.getText());

        if (emp.isEmpty()) {

            edao.guardar(e);
            edao.cerrar();

            List<Empleados> agrega = new ArrayList<>();
            agrega.add(e);

            JOptionPane.showMessageDialog(null, "El empleado se ha agregado correctamente");
            agregarDatostabla(agrega);
        } else {
            boolean flag = false;
            for (Empleados e2 : emp) {
                String cedulaE = e.getCedula();
                String cedulaE2 = e2.getCedula().trim();
                if (cedulaE.equals(cedulaE2)) {
                    JOptionPane.showMessageDialog(this, "Lo sentimos esta cedula ya pertenece a un empleado, le recordamos que este dato es unico e irrepetible");
                    txtCedulaEmp.setText("");
                    txtCedulaEmp.requestFocus();
                    flag = false;
                    break;
                } else {
                    flag = true;
                }
            }

            if (flag) {

                edao.guardar(e);
                edao.cerrar();

                List<Empleados> agrega = new ArrayList<>();
                agrega.add(e);

                JOptionPane.showMessageDialog(null, "El empleado se ha agregado correctamente");
                agregarDatostabla(agrega);
            }
        }
    }

    public void agregarDatostabla(List<Empleados> emp) throws IOException {

        ArchivoEmpleados edao = new ArchivoEmpleados();

        for (Empleados e : emp) {

            int id = e.getId();
            String nombre = e.getNombre().trim();
            String apellido = e.getApellido().trim();
            String cedula = e.getCedula().trim();
            double salario = e.getSalario();
            String dpto = e.getDepartamentos().getNombre().trim();
            String sexo = e.getSexo();
            String fecha = e.getFecha();

            Object datos[] = {id, nombre, apellido, cedula, salario, dpto, sexo, fecha};
            modelo.addRow(datos);
        }
    }

    public void mostrarFotoEmp() {

        int sel = this.TablaEmpleados.getSelectedRow();
        DefaultTableModel mod = (DefaultTableModel) this.TablaEmpleados.getModel();

        String cedula = mod.getValueAt(sel, 3).toString().trim();
//        int letra =0;
//        char[] letras = empleado.toCharArray();
//        for (int i = 0; i < letras.length; i++) {
//            if (letras[i]== '|') {
//                letra = i;
//            }
//        }
//        String cedula = empleado.substring(letra+1);
        ImageIcon icon = new ImageIcon("src/Empleados_Image/" + cedula + ".jpeg");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
        ImageIcon newIcon = new ImageIcon(newImg);
        labelFotoEmp1.setIcon(newIcon);
    }

    public void modificarEmp() throws IOException {

        ArchivoEmpleados edao = new ArchivoEmpleados();
        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        List<Empleados> emp = edao.encontrar();
        List<Departamentos> dpto = ddao.encontrar();

        int sel = this.TablaEmpleados.getSelectedRow();
        DefaultTableModel mod = (DefaultTableModel) this.TablaEmpleados.getModel();
        String nombre = mod.getValueAt(sel, 1).toString();
        for (Empleados e : emp) {
            String nombreEmp = e.getNombre().trim();
            if (nombre.equals(nombreEmp)) {
                e.setId(e.getId());
                e.setNombre(txtNombreEmp_Modicando.getText());
                e.setApellido(txtApellidoEmp_Modicando.getText());
                e.setCedula(txtCedulaEmp_Modifica.getText());
                e.setTelefono(txtTelefonoEmp_Modicando.getText());
                e.setCelular(txtCelularEmp_Modicando.getText());
                e.setSalario(Double.parseDouble(txtSalarioEmp_Modicando.getText()));
                Departamentos d = ddao.buscarNombre(this.comboDptoModifica.getSelectedItem().toString().trim());
                if (comboDptoModifica.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "Lo sentimos ese no es un departamento");
                    return;
                }
                e.setDepartamentos(d);
                if (itemHombreElegidoEmp_Modicando.isSelected()) {
                    e.setSexo(itemHombreElegidoEmp_Modicando.getLabel());
                } else if (itemMujerElegidoEmp_Modicando.isSelected()) {
                    e.setSexo(itemMujerElegidoEmp_Modicando.getLabel());
                }
                e.setFecha(txtFechaEmp_Modifica.getText());

                c.copy(p2, txtCedulaEmp_Modifica.getText());
                edao.modificar(e);
                JOptionPane.showMessageDialog(this, "El empleado se ha modificado correctamente!!!",
                        "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void limpiarTabla() {
        for (int i = 0; i < TablaEmpleados.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexoItem = new javax.swing.ButtonGroup();
        GrupoSexoModifica = new javax.swing.ButtonGroup();
        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaEmpleados = new org.jdesktop.swingx.JXTable();
        botonReporte = new org.edisoncor.gui.button.ButtonTask();
        botonRegresar = new org.edisoncor.gui.button.ButtonTask();
        botonExportarExcel = new org.edisoncor.gui.button.ButtonTask();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtApellidoEmp = new javax.swing.JTextField();
        txtCelularEmp = new javax.swing.JTextField();
        txtNombreEmp = new javax.swing.JTextField();
        txtTelefonoEmp = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        itemHombreElegidoEmp = new javax.swing.JRadioButton();
        itemMujerElegidoEmp = new javax.swing.JRadioButton();
        txtCedulaEmp = new org.jdesktop.swingx.JXFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        comboListaDptos = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtSalarioEmp = new javax.swing.JTextField();
        txtFechaIngresoEmp = new org.jdesktop.swingx.JXFormattedTextField();
        botonLimpiarAgrega = new org.edisoncor.gui.button.ButtonTask();
        labelInform = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        labelFotoEmp = new javax.swing.JLabel();
        botonTomarFotoEmp = new javax.swing.JButton();
        botonSubirFotoEmp = new javax.swing.JButton();
        botonAgregarEmp = new org.edisoncor.gui.button.ButtonTask();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        jLabel10 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtApellidoEmp_Modicando = new javax.swing.JTextField();
        txtCelularEmp_Modicando = new javax.swing.JTextField();
        txtNombreEmp_Modicando = new javax.swing.JTextField();
        txtTelefonoEmp_Modicando = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        itemHombreElegidoEmp_Modicando = new javax.swing.JRadioButton();
        itemMujerElegidoEmp_Modicando = new javax.swing.JRadioButton();
        txtCedulaEmp_Modifica = new org.jdesktop.swingx.JXFormattedTextField();
        jPanel8 = new javax.swing.JPanel();
        labelFotoEmp1 = new javax.swing.JLabel();
        botonElegirFotoModificando = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        botonModificarEmp = new org.edisoncor.gui.button.ButtonTask();
        jPanel9 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtSalarioEmp_Modicando = new javax.swing.JTextField();
        txtFechaEmp_Modifica = new org.jdesktop.swingx.JXFormattedTextField();
        comboDptoModifica = new javax.swing.JComboBox();
        botonLimpiarModifica = new org.edisoncor.gui.button.ButtonTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(810, 700));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaEmpleados);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 790, 210));

        botonReporte.setForeground(new java.awt.Color(255, 255, 255));
        botonReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportes.png"))); // NOI18N
        botonReporte.setText("Reporte");
        botonReporte.setDescription("de empleados");
        botonReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 620, 170, -1));

        botonRegresar.setForeground(new java.awt.Color(255, 255, 255));
        botonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/regresar.png"))); // NOI18N
        botonRegresar.setText("Regresar");
        botonRegresar.setDescription(" ");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 620, 170, -1));

        botonExportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        botonExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/excel.png"))); // NOI18N
        botonExportarExcel.setText("Exportar");
        botonExportarExcel.setDescription("a Microsoft Excel");
        botonExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExportarExcelActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 620, 180, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Apellido");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 75, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel2.setText("Sexo");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 75, 25));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel3.setText("Nombre");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 75, 25));

        txtApellidoEmp.setToolTipText("Agregue un apellido al empleado");
        jPanel3.add(txtApellidoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 150, 25));

        txtCelularEmp.setToolTipText("Celular movil");
        jPanel3.add(txtCelularEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 150, 25));

        txtNombreEmp.setToolTipText("Agregue un nombre al empleado");
        jPanel3.add(txtNombreEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 150, 25));

        txtTelefonoEmp.setToolTipText("Telefono convencional");
        jPanel3.add(txtTelefonoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 150, 25));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel4.setText("Celular");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 75, 25));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel5.setText("Cedula");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 75, 25));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel6.setText("Telefono");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 75, 25));

        sexoItem.add(itemHombreElegidoEmp);
        itemHombreElegidoEmp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        itemHombreElegidoEmp.setText("Hombre");
        jPanel3.add(itemHombreElegidoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        sexoItem.add(itemMujerElegidoEmp);
        itemMujerElegidoEmp.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        itemMujerElegidoEmp.setText("Mujer");
        jPanel3.add(itemMujerElegidoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        txtCedulaEmp.setToolTipText("Agregue la cedula del empleado");
        try {
            txtCedulaEmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel3.add(txtCedulaEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 150, 25));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 520, 160));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Laborales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboListaDptos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Departamentos --" }));
        comboListaDptos.setToolTipText("Elija un departamento");
        jPanel4.add(comboListaDptos, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 200, 25));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel7.setText("Fecha de ingreso");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 25));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel8.setText("Salario");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 75, 25));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel9.setText("Departamento");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 25));

        txtSalarioEmp.setToolTipText("Ingrese el salario en moneda nacional");
        jPanel4.add(txtSalarioEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 25));

        txtFechaIngresoEmp.setToolTipText("");
        try {
            txtFechaIngresoEmp.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel4.add(txtFechaIngresoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 200, 25));

        botonLimpiarAgrega.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        botonLimpiarAgrega.setText("Limpiar");
        botonLimpiarAgrega.setDescription("Ventanas");
        botonLimpiarAgrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarAgregaActionPerformed(evt);
            }
        });
        jPanel4.add(botonLimpiarAgrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 30, 160, -1));

        labelInform.setFont(new java.awt.Font("Dialog", 1, 15)); // NOI18N
        labelInform.setForeground(new java.awt.Color(204, 0, 0));
        labelInform.setText("Dato incorrecto");
        jPanel4.add(labelInform, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 120, 20));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 520, 150));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelFotoEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/fotoEmp.png"))); // NOI18N
        labelFotoEmp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel5.add(labelFotoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 150, 150));

        botonTomarFotoEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/camara.png"))); // NOI18N
        botonTomarFotoEmp.setText(" Tomar foto");
        jPanel5.add(botonTomarFotoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        botonSubirFotoEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/subirPic.png"))); // NOI18N
        botonSubirFotoEmp.setText(" Elegir foto");
        botonSubirFotoEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSubirFotoEmpActionPerformed(evt);
            }
        });
        jPanel5.add(botonSubirFotoEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 240, 220));

        botonAgregarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/AgregarEmp.png"))); // NOI18N
        botonAgregarEmp.setText("Agregar");
        botonAgregarEmp.setDescription("Empleados");
        botonAgregarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarEmpActionPerformed(evt);
            }
        });
        jPanel1.add(botonAgregarEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 260, 170, -1));

        jTabbedPane1.addTab("Agregar Empleados", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/EditarEmpleado.png"))); // NOI18N
        labelTask1.setText("Editar Empleados");
        labelTask1.setDescription("Por favor!, Elija en la tabla el empleado que va a modificar");
        jPanel6.add(labelTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 520, 60));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setText("    Edite los campos a modificar, una vez realizado los cambios seleccione el boton de modificar y siga las istrucciones");
        jLabel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 660, 40));

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel11.setText("Apellido");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 40, 75, 25));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel12.setText("Sexo");
        jPanel7.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 75, 25));

        jLabel13.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel13.setText("Nombre");
        jPanel7.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 75, 25));

        txtApellidoEmp_Modicando.setToolTipText("Agregue un apellido al empleado");
        jPanel7.add(txtApellidoEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 40, 150, 25));

        txtCelularEmp_Modicando.setToolTipText("Celular movil");
        jPanel7.add(txtCelularEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 150, 25));

        txtNombreEmp_Modicando.setToolTipText("Agregue un nombre al empleado");
        jPanel7.add(txtNombreEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 150, 25));

        txtTelefonoEmp_Modicando.setToolTipText("Telefono convencional");
        jPanel7.add(txtTelefonoEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 80, 150, 25));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel14.setText("Celular");
        jPanel7.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 75, 25));

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel15.setText("Cedula");
        jPanel7.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 75, 25));

        jLabel16.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel16.setText("Telefono");
        jPanel7.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 75, 25));

        GrupoSexoModifica.add(itemHombreElegidoEmp_Modicando);
        itemHombreElegidoEmp_Modicando.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        itemHombreElegidoEmp_Modicando.setText("Hombre");
        jPanel7.add(itemHombreElegidoEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 120, -1, -1));

        GrupoSexoModifica.add(itemMujerElegidoEmp_Modicando);
        itemMujerElegidoEmp_Modicando.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        itemMujerElegidoEmp_Modicando.setText("Mujer");
        jPanel7.add(itemMujerElegidoEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, -1));

        try {
            txtCedulaEmp_Modifica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###-######-####U")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        jPanel7.add(txtCedulaEmp_Modifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 80, 150, 25));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 520, 160));

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Foto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelFotoEmp1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/fotoEmp.png"))); // NOI18N
        labelFotoEmp1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.add(labelFotoEmp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 150, 150));

        botonElegirFotoModificando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/subirPic.png"))); // NOI18N
        botonElegirFotoModificando.setText(" Elegir Foto");
        botonElegirFotoModificando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonElegirFotoModificandoActionPerformed(evt);
            }
        });
        jPanel8.add(botonElegirFotoModificando, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, -1, -1));

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/camara.png"))); // NOI18N
        jButton2.setText(" Tomar Foto");
        jPanel8.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 170, 240, 220));

        botonModificarEmp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/EmpModificado.png"))); // NOI18N
        botonModificarEmp.setText("Modificar");
        botonModificarEmp.setDescription("Empleados");
        botonModificarEmp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarEmpActionPerformed(evt);
            }
        });
        jPanel6.add(botonModificarEmp, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 410, 150, -1));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Laborales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 12))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel17.setText("Fecha de ingreso");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 120, 25));

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel18.setText("Salario");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 75, 25));

        jLabel19.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel19.setText("Departamento");
        jPanel9.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 25));

        txtSalarioEmp_Modicando.setToolTipText("Ingrese el salario en moneda nacional");
        jPanel9.add(txtSalarioEmp_Modicando, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 25));

        txtFechaEmp_Modifica.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));
        jPanel9.add(txtFechaEmp_Modifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 200, 25));

        comboDptoModifica.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- Departamentos --" }));
        jPanel9.add(comboDptoModifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 200, 25));

        botonLimpiarModifica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        botonLimpiarModifica.setText("Limpiar");
        botonLimpiarModifica.setDescription("Ventanas");
        botonLimpiarModifica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarModificaActionPerformed(evt);
            }
        });
        jPanel9.add(botonLimpiarModifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 140, -1));

        jPanel6.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 520, 150));

        jScrollPane2.setViewportView(jPanel6);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 350));

        jTabbedPane1.addTab("Modificar Empleados", jPanel2);

        jcMousePanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 790, 370));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 700));

        setSize(new java.awt.Dimension(826, 739));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaEmpleadosMouseClicked

        try {
            ArchivoEmpleados edao = new ArchivoEmpleados();
            List<Empleados> emp = edao.encontrar();

            int sel = this.TablaEmpleados.getSelectedRow();
            DefaultTableModel mod = (DefaultTableModel) this.TablaEmpleados.getModel();

            String nombre = mod.getValueAt(sel, 1).toString();

            for (Empleados e : emp) {
                String nombreEmp = e.getNombre().trim();
                if (nombre.equals(nombreEmp)) {
                    txtNombreEmp_Modicando.setText(e.getNombre().trim());
                    txtApellidoEmp_Modicando.setText(e.getApellido().trim());
                    txtCedulaEmp_Modifica.setText(e.getCedula());
                    txtTelefonoEmp_Modicando.setText(e.getTelefono());
                    txtCelularEmp_Modicando.setText(e.getCelular());
                    if (e.getSexo().equals(itemHombreElegidoEmp.getLabel())) {
                        itemHombreElegidoEmp_Modicando.getSelectedIcon();
                    } else if (e.getSexo().equals(itemMujerElegidoEmp.getLabel())) {
                        itemMujerElegidoEmp_Modicando.getSelectedIcon();
                    }
                    txtSalarioEmp_Modicando.setText(Double.toString(e.getSalario()));
                    txtFechaEmp_Modifica.setText(e.getFecha());
                    mostrarFotoEmp();
                    botonModificarEmp.setVisible(true);
                    botonLimpiarModifica.setVisible(true);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TablaEmpleadosMouseClicked

    private void botonReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteActionPerformed

    }//GEN-LAST:event_botonReporteActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarExcelActionPerformed

        if (this.TablaEmpleados.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay datos en la tabla para expotar a excel");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filt = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filt);
        chooser.setDialogTitle("Guardar Archivo");
        chooser.setMultiSelectionEnabled(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            List<JTable> tb = new ArrayList<>();
            List<String> nom = new ArrayList<>();
            tb.add(TablaEmpleados);
            nom.add("Empleados");
            String file = chooser.getSelectedFile().toString().concat(".xls");
            try {
                Pojos.ExportarExcel e = new ExportarExcel(new File(file), tb, nom);
                if (e.export()) {
                    JOptionPane.showMessageDialog(null, "La tabla fue guardada en excel");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Hubo un error " + e.getMessage());
            }
        }
    }//GEN-LAST:event_botonExportarExcelActionPerformed

    private void botonLimpiarAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarAgregaActionPerformed
        limpiarAgrega();
    }//GEN-LAST:event_botonLimpiarAgregaActionPerformed

    private void botonAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarEmpActionPerformed
        try {
            agregarEmpleados();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error " + ex);
        }
    }//GEN-LAST:event_botonAgregarEmpActionPerformed

    private void botonSubirFotoEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSubirFotoEmpActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filtro);
        int foto = chooser.showOpenDialog(this);
        if (foto == JFileChooser.APPROVE_OPTION) {
            String file = chooser.getSelectedFile().getPath();
            //obtener la direccion de la imagen
            String files = chooser.getSelectedFile().toString();
            labelFotoEmp.setIcon(new ImageIcon(file));
            ImageIcon icon = new ImageIcon(file);
            //extraer la imagen del icon
            Image img = icon.getImage();
            //cambiando el tamaño d ela imagen
            Image nuevaImg = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
            //se genera el imageicon cn la nueva imagen
            ImageIcon nuevoIcono = new ImageIcon(nuevaImg);
            labelFotoEmp.setIcon(nuevoIcono);
            p = Paths.get(file);
        }

    }//GEN-LAST:event_botonSubirFotoEmpActionPerformed

    private void botonLimpiarModificaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarModificaActionPerformed
        limpiarModifica();
        botonLimpiarModifica.setVisible(false);
        botonModificarEmp.setVisible(false);
    }//GEN-LAST:event_botonLimpiarModificaActionPerformed

    private void botonModificarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarEmpActionPerformed
        try {
            modificarEmp();
            limpiarTabla();
            ArchivoEmpleados edao = new ArchivoEmpleados();
            List<Empleados> emp = edao.encontrar();
            agregarDatostabla(emp);
            botonModificarEmp.setVisible(false);
            botonLimpiarModifica.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(GestionarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonModificarEmpActionPerformed

    private void botonElegirFotoModificandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonElegirFotoModificandoActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(filtro);
        int foto = chooser.showOpenDialog(this);
        if (foto == JFileChooser.APPROVE_OPTION) {
            String file = chooser.getSelectedFile().getPath();
            //obtener la direccion de la imagen
            String files = chooser.getSelectedFile().toString();
            labelFotoEmp1.setIcon(new ImageIcon(file));
            ImageIcon icon = new ImageIcon(file);
            //extraer la imagen del icon
            Image img = icon.getImage();
            //cambiando el tamaño d ela imagen
            Image nuevaImg = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
            //se genera el imageicon cn la nueva imagen
            ImageIcon nuevoIcono = new ImageIcon(nuevaImg);
            labelFotoEmp1.setIcon(nuevoIcono);
            p2 = Paths.get(file);
        }
    }//GEN-LAST:event_botonElegirFotoModificandoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GestionarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarEmpleados dialog = new GestionarEmpleados(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoSexoModifica;
    private org.jdesktop.swingx.JXTable TablaEmpleados;
    private org.edisoncor.gui.button.ButtonTask botonAgregarEmp;
    private javax.swing.JButton botonElegirFotoModificando;
    private org.edisoncor.gui.button.ButtonTask botonExportarExcel;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarAgrega;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarModifica;
    private org.edisoncor.gui.button.ButtonTask botonModificarEmp;
    private org.edisoncor.gui.button.ButtonTask botonRegresar;
    private org.edisoncor.gui.button.ButtonTask botonReporte;
    private javax.swing.JButton botonSubirFotoEmp;
    private javax.swing.JButton botonTomarFotoEmp;
    private javax.swing.JComboBox comboDptoModifica;
    private javax.swing.JComboBox comboListaDptos;
    private javax.swing.JRadioButton itemHombreElegidoEmp;
    private javax.swing.JRadioButton itemHombreElegidoEmp_Modicando;
    private javax.swing.JRadioButton itemMujerElegidoEmp;
    private javax.swing.JRadioButton itemMujerElegidoEmp_Modicando;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JLabel labelFotoEmp;
    private javax.swing.JLabel labelFotoEmp1;
    private javax.swing.JLabel labelInform;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    private javax.swing.ButtonGroup sexoItem;
    private javax.swing.JTextField txtApellidoEmp;
    private javax.swing.JTextField txtApellidoEmp_Modicando;
    private org.jdesktop.swingx.JXFormattedTextField txtCedulaEmp;
    private org.jdesktop.swingx.JXFormattedTextField txtCedulaEmp_Modifica;
    private javax.swing.JTextField txtCelularEmp;
    private javax.swing.JTextField txtCelularEmp_Modicando;
    private org.jdesktop.swingx.JXFormattedTextField txtFechaEmp_Modifica;
    private org.jdesktop.swingx.JXFormattedTextField txtFechaIngresoEmp;
    private javax.swing.JTextField txtNombreEmp;
    private javax.swing.JTextField txtNombreEmp_Modicando;
    private javax.swing.JTextField txtSalarioEmp;
    private javax.swing.JTextField txtSalarioEmp_Modicando;
    private javax.swing.JTextField txtTelefonoEmp;
    private javax.swing.JTextField txtTelefonoEmp_Modicando;
    // End of variables declaration//GEN-END:variables
}
