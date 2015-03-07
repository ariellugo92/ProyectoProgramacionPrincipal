/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoDepartamentos;
import Pojos.Departamentos;
import Pojos.ExportarExcel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author IaraDenisse
 */
public final class GestionarDptos extends javax.swing.JDialog {

    /**
     * Creates new form GestionarDptos
     */
    DefaultTableModel modelo;
    int var = 0;

    public GestionarDptos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        /*-----------------------------------
         Componentes para el panel de agregar
         ------------------------------------*/
        txtNombreDpto.requestFocus();
        tabla();
        try {
            ArchivoDepartamentos d = new ArchivoDepartamentos();
            List<Departamentos> def = d.encontrar();
            agregarDatostabla(def);
            txtIdAgrega.setText((def.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(GestionarDptos.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*-------------------------------------
         Componentes para el panel de modificar
         --------------------------------------*/
        this.botonModificar.setVisible(false);
    }

    public void tabla() {
        String encabezados[] = {"Id", "Nombre", "Descripcion"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, encabezados);
        TablaPrincipal.setModel(modelo);
    }

    boolean flag = false;

    public void agregarDpto() throws IOException {

        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        List<Departamentos> dpto = ddao.encontrar();

        Departamentos d = new Departamentos();

        d.setId(dpto.size() + 1);
        d.setNombre(txtNombreDpto.getText());
        d.setDescripcion(txtDescripcionDpto.getText());

        ddao.guardar(d);
        ddao.cerrar();
        
        List<Departamentos> mandar = new ArrayList<>();
            mandar.add(d);

        this.agregarDatostabla(mandar);
    }

    public void limpiar() {
        if (txtNombreDpto.getText().length() == 0 && txtDescripcionDpto.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Los campos ya estan vacios", "Aviso",
                    JOptionPane.INFORMATION_MESSAGE);
        } else {
            txtNombreDpto.setText("");
            txtDescripcionDpto.setText("");
            txtNombreDpto.requestFocus();
        }
    }

    public void limpiarMod() {
        if (txtIdModifica.getText().length() == 0 && txtNombre_a_ModificarDpto.getText().length() == 0
            && txtDescripcion_a_ModificarDpto.getText().length() == 0) {
            JOptionPane.showMessageDialog(null, "Los campos ya estan vacios");
            return;
        }
        txtIdModifica.setText("");
        txtNombre_a_ModificarDpto.setText("");
        txtDescripcion_a_ModificarDpto.setText("");
    }

    public void agregarDatostabla(List<Departamentos> dpto) throws IOException {

        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        
        for (Departamentos d : dpto) {
            
            int id = d.getId();
            String nombre = d.getNombre().trim();
            String descripcion = d.getDescripcion().trim();
            Object datos[] = {id, nombre, descripcion};
            modelo.addRow(datos);
        }
    }
    
    public void generarReporte() throws IOException{
    
        try {
            ArchivoDepartamentos ddao = new ArchivoDepartamentos();
            List<Departamentos> dpto = ddao.encontrar();
            
            JDialog verReporte = new JDialog(new javax.swing.JFrame(),"Visualizando reportes", true);
            verReporte.setSize(1000, 750);
            verReporte.setLocationRelativeTo(null);
            
            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("src/Reportes/ReporteDepartamentos.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, new JRBeanCollectionDataSource(dpto));
            JasperViewer ventana = new JasperViewer(jasperPrint, false);
            ventana.setTitle("Reporte de departamentos");
            verReporte.getContentPane().add(ventana.getContentPane());
            verReporte.setVisible(true);
            
//            Exporter exporter = new JRPdfExporter();
//            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput("Reporte de departamentos.pdf"));
//            exporter.exportReport();
                    } catch (JRException ex) {
            Logger.getLogger(GestionarDptos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarDpto() throws IOException{
    
        ArchivoDepartamentos ddao = new ArchivoDepartamentos();
        List<Departamentos> dpto = ddao.encontrar();
        
        for (Departamentos d : dpto) {
            int tablaClick = this.TablaPrincipal.getSelectedRow();
            DefaultTableModel mod = (DefaultTableModel) this.TablaPrincipal.getModel();
            String nombreElegido = mod.getValueAt(tablaClick, 1).toString();
            String nombreDpto = d.getNombre().trim();
            if (nombreDpto.equals(nombreElegido)) {
                d.setId(d.getId());
                d.setNombre(txtNombre_a_ModificarDpto.getText());
                d.setDescripcion(txtDescripcion_a_ModificarDpto.getText());
                
                ddao.modificar(d);
                List<Departamentos> mandar = new ArrayList<>();
                mandar.add(d);

                this.agregarDatostabla(mandar);
                JOptionPane.showMessageDialog(null, "El departamento se ha modificado");
            }
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

        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        tabbedSelector21 = new org.edisoncor.gui.tabbedPane.TabbedSelector2();
        PanelAgrega = new org.edisoncor.gui.panel.PanelNice();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        txtNombreDpto = new org.edisoncor.gui.textField.TextFieldRectBackground();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcionDpto = new org.jdesktop.swingx.JXTextArea();
        botonAgregarDpto = new org.edisoncor.gui.button.ButtonTask();
        botonLimpiarVtnaAgregar = new org.edisoncor.gui.button.ButtonTask();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtIdAgrega = new javax.swing.JTextField();
        panelNice2 = new org.edisoncor.gui.panel.PanelNice();
        PanelModifica = new org.edisoncor.gui.panel.PanelNice();
        labelTask2 = new org.edisoncor.gui.label.LabelTask();
        txtNombre_a_ModificarDpto = new org.edisoncor.gui.textField.TextFieldRectBackground();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtDescripcion_a_ModificarDpto = new org.jdesktop.swingx.JXTextArea();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        botonModificar = new org.edisoncor.gui.button.ButtonTask();
        jLabel6 = new javax.swing.JLabel();
        txtIdModifica = new javax.swing.JTextField();
        botonLimpiar = new org.edisoncor.gui.button.ButtonTask();
        PanelBorrar = new org.edisoncor.gui.panel.PanelNice();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaPrincipal = new org.jdesktop.swingx.JXTable();
        botonReporte = new org.edisoncor.gui.button.ButtonTask();
        botonRegresar = new org.edisoncor.gui.button.ButtonTask();
        botonExportarExcel = new org.edisoncor.gui.button.ButtonTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabbedSelector21.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N

        PanelAgrega.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTask1.setForeground(new java.awt.Color(255, 255, 255));
        labelTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/add departamento.png"))); // NOI18N
        labelTask1.setText("Agregando Departamentos");
        labelTask1.setDescription("Llene los datos del nuevo departamento");
        PanelAgrega.add(labelTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 60));

        txtNombreDpto.setColorDeTextoBackground(new java.awt.Color(0, 0, 0));
        txtNombreDpto.setDescripcion("Ingrese el nombre del departamento");
        txtNombreDpto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        PanelAgrega.add(txtNombreDpto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 270, 30));

        txtDescripcionDpto.setColumns(20);
        txtDescripcionDpto.setRows(5);
        jScrollPane2.setViewportView(txtDescripcionDpto);

        PanelAgrega.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 270, 70));

        botonAgregarDpto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonAgregarDpto.setForeground(new java.awt.Color(255, 255, 255));
        botonAgregarDpto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/agregarBoton.png"))); // NOI18N
        botonAgregarDpto.setText("Agregar");
        botonAgregarDpto.setDescription("Departamento");
        botonAgregarDpto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarDptoActionPerformed(evt);
            }
        });
        PanelAgrega.add(botonAgregarDpto, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, 180, -1));

        botonLimpiarVtnaAgregar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonLimpiarVtnaAgregar.setForeground(new java.awt.Color(255, 255, 255));
        botonLimpiarVtnaAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        botonLimpiarVtnaAgregar.setText("Limpiar");
        botonLimpiarVtnaAgregar.setDescription("Ventanas");
        botonLimpiarVtnaAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarVtnaAgregarActionPerformed(evt);
            }
        });
        PanelAgrega.add(botonLimpiarVtnaAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 170, 180, -1));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre");
        PanelAgrega.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripcion");
        PanelAgrega.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, 25));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ID");
        PanelAgrega.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 80, 20));

        txtIdAgrega.setEditable(false);
        PanelAgrega.add(txtIdAgrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 30, 30));

        tabbedSelector21.addTab("Agregar", PanelAgrega);

        PanelModifica.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTask2.setForeground(new java.awt.Color(255, 255, 255));
        labelTask2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/update departamentos.png"))); // NOI18N
        labelTask2.setText("Modificar Departamentos");
        labelTask2.setDescription("Seleccione en la tabla el departamento a modificar");
        PanelModifica.add(labelTask2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 400, 60));

        txtNombre_a_ModificarDpto.setColorDeTextoBackground(new java.awt.Color(0, 0, 0));
        txtNombre_a_ModificarDpto.setDescripcion("Ingrese el nombre del departamento");
        txtNombre_a_ModificarDpto.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        PanelModifica.add(txtNombre_a_ModificarDpto, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 270, 30));

        txtDescripcion_a_ModificarDpto.setColumns(20);
        txtDescripcion_a_ModificarDpto.setRows(5);
        jScrollPane3.setViewportView(txtDescripcion_a_ModificarDpto);

        PanelModifica.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 170, 270, 70));

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");
        PanelModifica.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, 25));

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Descripcion");
        PanelModifica.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 100, 25));

        botonModificar.setForeground(new java.awt.Color(255, 255, 255));
        botonModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Modificar.png"))); // NOI18N
        botonModificar.setText("Modificar");
        botonModificar.setDescription("Departamentos");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });
        PanelModifica.add(botonModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 160, 180, -1));

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ID");
        PanelModifica.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 80, 20));

        txtIdModifica.setEditable(false);
        PanelModifica.add(txtIdModifica, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 80, 30, 30));

        botonLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        botonLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        botonLimpiar.setText("Limpiar");
        botonLimpiar.setDescription("Ventana");
        botonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarActionPerformed(evt);
            }
        });
        PanelModifica.add(botonLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 80, 180, -1));

        panelNice2.add(PanelModifica, java.awt.BorderLayout.CENTER);

        tabbedSelector21.addTab("Modificar", panelNice2);
        tabbedSelector21.addTab("Borrar", PanelBorrar);

        jcMousePanel1.add(tabbedSelector21, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 780, 280));

        TablaPrincipal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaPrincipalMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaPrincipal);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 780, 210));

        botonReporte.setForeground(new java.awt.Color(255, 255, 255));
        botonReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportes.png"))); // NOI18N
        botonReporte.setText("Reporte");
        botonReporte.setDescription("Departamentos");
        botonReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReporteActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonReporte, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 540, 170, -1));

        botonRegresar.setForeground(new java.awt.Color(255, 255, 255));
        botonRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/regresar.png"))); // NOI18N
        botonRegresar.setText("Regresar");
        botonRegresar.setDescription(" ");
        botonRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 540, 170, -1));

        botonExportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        botonExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/excel.png"))); // NOI18N
        botonExportarExcel.setText("Exportar");
        botonExportarExcel.setDescription("a Microsoft Excel");
        botonExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExportarExcelActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonExportarExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 540, 180, -1));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 810, 620));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarActionPerformed

    private void botonAgregarDptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarDptoActionPerformed
        try {
            if (txtNombreDpto.getText().length() == 0 && txtDescripcionDpto.getText().length() == 0) {
                JOptionPane.showMessageDialog(null, "No ha agregado ningun dato");
            } else {
                if (txtNombreDpto.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No ha ingresado un nombre al departamento");
                    txtNombreDpto.requestFocus();
                } else if (txtDescripcionDpto.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "No ha ingresado una descripcion al departamento");
                    txtDescripcionDpto.requestFocus();
                } else {
                    agregarDpto();
                    JOptionPane.showMessageDialog(this, "El departamento se agrego correctamente", "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                            txtNombreDpto.requestFocus();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionarDptos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonAgregarDptoActionPerformed

    private void botonLimpiarVtnaAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarVtnaAgregarActionPerformed
        limpiar();
    }//GEN-LAST:event_botonLimpiarVtnaAgregarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        
        try {
            ArchivoDepartamentos ddao = new ArchivoDepartamentos();
            List<Departamentos> dpto = ddao.encontrar();
            
            if (dpto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lo sentimos, el reporte no se puede generar ya que no hay departamentos toadavia",
                        "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            modificarDpto();
        } catch (IOException ex) {
            Logger.getLogger(GestionarDptos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void TablaPrincipalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaPrincipalMouseClicked
        try {
            // TODO add your handling code here:
            ArchivoDepartamentos ddao = new ArchivoDepartamentos();
            List<Departamentos> dpto = ddao.encontrar();
            
            if (dpto.isEmpty()) {
                return;
            }
            this.botonModificar.setVisible(true);
            int sel = this.TablaPrincipal.getSelectedRow();
            DefaultTableModel mod = (DefaultTableModel) this.TablaPrincipal.getModel();
            this.txtIdModifica.setText(mod.getValueAt(sel, 0).toString());
            this.txtNombre_a_ModificarDpto.setText(mod.getValueAt(sel, 1).toString().trim());
            this.txtDescripcion_a_ModificarDpto.setText(mod.getValueAt(sel, 2).toString().trim());
        } catch (IOException ex) {
            Logger.getLogger(GestionarDptos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_TablaPrincipalMouseClicked

    private void botonExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExportarExcelActionPerformed

        if (this.TablaPrincipal.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "No hay datos en la tabla para expotar a excel");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de excel", "xls");
        chooser.setFileFilter(filtro);
        chooser.setDialogTitle("Guardar Archivo");
        chooser.setMultiSelectionEnabled(false);
        if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            List<JTable> tb = new ArrayList<>();
            List<String> nom = new ArrayList<>();
            tb.add(TablaPrincipal);
            nom.add("Departamentos");
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

    private void botonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarActionPerformed
        limpiarMod();
        this.botonModificar.setVisible(false);
    }//GEN-LAST:event_botonLimpiarActionPerformed

    private void botonReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReporteActionPerformed
        try {
            ArchivoDepartamentos ddao = new ArchivoDepartamentos();
            List<Departamentos> dpto = ddao.encontrar();
            
            if (dpto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lo sentimos, el reporte no se puede generar ya que no hay departamentos toadavia",
                        "Error",JOptionPane.ERROR_MESSAGE);
                return;
            }
            generarReporte();
        } catch (IOException ex) {
            Logger.getLogger(GestionarDptos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonReporteActionPerformed

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
            java.util.logging.Logger.getLogger(GestionarDptos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarDptos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarDptos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarDptos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarDptos dialog = new GestionarDptos(new javax.swing.JFrame(), true);
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
    private org.edisoncor.gui.panel.PanelNice PanelAgrega;
    private org.edisoncor.gui.panel.PanelNice PanelBorrar;
    private org.edisoncor.gui.panel.PanelNice PanelModifica;
    private org.jdesktop.swingx.JXTable TablaPrincipal;
    private org.edisoncor.gui.button.ButtonTask botonAgregarDpto;
    private org.edisoncor.gui.button.ButtonTask botonExportarExcel;
    private org.edisoncor.gui.button.ButtonTask botonLimpiar;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarVtnaAgregar;
    private org.edisoncor.gui.button.ButtonTask botonModificar;
    private org.edisoncor.gui.button.ButtonTask botonRegresar;
    private org.edisoncor.gui.button.ButtonTask botonReporte;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    private org.edisoncor.gui.label.LabelTask labelTask2;
    private org.edisoncor.gui.panel.PanelNice panelNice2;
    private org.edisoncor.gui.tabbedPane.TabbedSelector2 tabbedSelector21;
    private org.jdesktop.swingx.JXTextArea txtDescripcionDpto;
    private org.jdesktop.swingx.JXTextArea txtDescripcion_a_ModificarDpto;
    private javax.swing.JTextField txtIdAgrega;
    private javax.swing.JTextField txtIdModifica;
    private org.edisoncor.gui.textField.TextFieldRectBackground txtNombreDpto;
    private org.edisoncor.gui.textField.TextFieldRectBackground txtNombre_a_ModificarDpto;
    // End of variables declaration//GEN-END:variables
}
