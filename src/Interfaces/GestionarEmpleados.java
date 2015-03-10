/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoDepartamentos;
import Archivos.ArchivoEmpleados;
import Pojos.Departamentos;
import Pojos.Empleados;
import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author IaraDenisse
 */
public class GestionarEmpleados extends javax.swing.JDialog {

    /**
     * Creates new form GestionarEmpleados
     */
    DefaultTableModel modelo;
    private FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de imagen", "jpg");
    String ruta;
    
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
    
    public void tamañoColumnas(){
    
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
        ruta = "";
        txtNombreEmp.requestFocus();
        tabla();
        try {
            ArchivoEmpleados edao = new ArchivoEmpleados();
            List<Empleados> emp = edao.encontrar();
            agregarDatostabla(emp);
            cargar(this.comboListaDptos);
        } catch (IOException ex) {
            Logger.getLogger(GestionarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void limpiarAgrega(){
    
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
    }
    
    public void agregarEmpleados() throws IOException{
    
        ArchivoEmpleados edao = new ArchivoEmpleados();
        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        List<Empleados> emp = edao.encontrar();
        Empleados e = new Empleados();
        
        e.setId(emp.size() + 1);
        e.setNombre(txtNombreEmp.getText());
        e.setApellido(txtApellidoEmp.getText());
        e.setCedula(txtCedulaEmp.getText());
        e.setTelefono(txtTelefonoEmp.getText());
        e.setCelular(txtCelularEmp.getText());
        e.setSalario(Double.parseDouble(txtSalarioEmp.getText()));
        Departamentos d = ddao.buscarNombre(this.comboListaDptos.getSelectedItem().toString().trim());
        if (comboListaDptos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Lo sentimos ese no es un departamento");
            System.out.println(comboListaDptos.getSelectedIndex());
            return;
        }
        e.setDepartamentos(d);
        if (itemHombreElegidoEmp.isSelected()) {
            e.setSexo(itemHombreElegidoEmp.getLabel());
        }else if (itemMujerElegidoEmp.isSelected()) {
            e.setSexo(itemMujerElegidoEmp.getLabel());
        }
        e.setFecha(txtFechaIngresoEmp.getText());
        
        edao.guardar(e);
        edao.cerrar();
        
        List<Empleados> agrega = new ArrayList<>();
        agrega.add(e);
        
        JOptionPane.showMessageDialog(null, "El empleado se ha agregado correctamente");
        agregarDatostabla(agrega);
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sexoItem = new javax.swing.ButtonGroup();
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
        jPanel5 = new javax.swing.JPanel();
        labelFotoEmp = new javax.swing.JLabel();
        botonTomarFotoEmp = new javax.swing.JButton();
        botonSubirFotoEmp = new javax.swing.JButton();
        botonAgregarEmp = new org.edisoncor.gui.button.ButtonTask();
        jPanel2 = new javax.swing.JPanel();

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
        jPanel4.add(botonLimpiarAgrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, 160, -1));

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
        jTabbedPane1.addTab("Modificar Empleados", jPanel2);

        jcMousePanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 790, 370));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 700));

        setSize(new java.awt.Dimension(826, 739));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TablaEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaEmpleadosMouseClicked
        
    }//GEN-LAST:event_TablaEmpleadosMouseClicked

    private void botonReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteActionPerformed
        
    }//GEN-LAST:event_botonReporteActionPerformed

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarExcelActionPerformed

    }//GEN-LAST:event_botonExportarExcelActionPerformed

    private void botonLimpiarAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarAgregaActionPerformed
        limpiarAgrega();
    }//GEN-LAST:event_botonLimpiarAgregaActionPerformed

    private void botonAgregarEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarEmpActionPerformed
        try {
            agregarEmpleados();
        } catch (IOException ex) {
            Logger.getLogger(GestionarEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonAgregarEmpActionPerformed

    private void botonSubirFotoEmpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSubirFotoEmpActionPerformed
        //crear un objeto de JFileChooser
        JFileChooser chooser = new JFileChooser();
        //del objeto creado vamos a llamar al metodo setFIleFilter
        chooser.setFileFilter(filtro);
        //abrir una ventana del chooser
        int foto = chooser.showOpenDialog(this);
        //validaciones
        if (foto == JFileChooser.APPROVE_OPTION) {
            //obtener el nombre del archivo seleccionado
            String file = chooser.getSelectedFile().getPath();
            //obtener la direccion de la imagen
            String files = chooser.getSelectedFile().toString();
            labelFotoEmp.setIcon(new ImageIcon(file));
            //modificando la imagen
            ImageIcon icon = new ImageIcon(file);
            //extraer la imagen del icon
            Image img = icon.getImage();
            //cambiando el tamaño d ela imagen
            Image nuevaImg = img.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH);
            //se genera el imageicon cn la nueva imagen
            ImageIcon nuevoIcono = new ImageIcon(nuevaImg);
            labelFotoEmp.setIcon(nuevoIcono);
            ruta = "";
        }
        
    }//GEN-LAST:event_botonSubirFotoEmpActionPerformed

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
    private org.jdesktop.swingx.JXTable TablaEmpleados;
    private org.edisoncor.gui.button.ButtonTask botonAgregarEmp;
    private org.edisoncor.gui.button.ButtonTask botonExportarExcel;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarAgrega;
    private org.edisoncor.gui.button.ButtonTask botonRegresar;
    private org.edisoncor.gui.button.ButtonTask botonReporte;
    private javax.swing.JButton botonSubirFotoEmp;
    private javax.swing.JButton botonTomarFotoEmp;
    private javax.swing.JComboBox comboListaDptos;
    private javax.swing.JRadioButton itemHombreElegidoEmp;
    private javax.swing.JRadioButton itemMujerElegidoEmp;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JLabel labelFotoEmp;
    private javax.swing.ButtonGroup sexoItem;
    private javax.swing.JTextField txtApellidoEmp;
    private org.jdesktop.swingx.JXFormattedTextField txtCedulaEmp;
    private javax.swing.JTextField txtCelularEmp;
    private org.jdesktop.swingx.JXFormattedTextField txtFechaIngresoEmp;
    private javax.swing.JTextField txtNombreEmp;
    private javax.swing.JTextField txtSalarioEmp;
    private javax.swing.JTextField txtTelefonoEmp;
    // End of variables declaration//GEN-END:variables
}
