/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoCategoriaProd;
import Pojos.CategoriaProd;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IaraDenisse
 */
public class CategoriasDeProductos extends javax.swing.JDialog {

    DefaultTableModel modelo;

    public CategoriasDeProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtNombrecatgP.requestFocus();
        btnModificarCatgP.setVisible(false);
        btnLimpiarVentanas_Modificar.setVisible(false);
        tabla();
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catg = cpdao.encontrar();
            agregarTabla(catg);
            txtIDcatgP.setText((catg.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(CategoriasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void tabla() {
        String encabezados[] = {"Id", "Nombre", "Descripcion"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, encabezados);
        TablaCategoriasP.setModel(modelo);
    }

    public void agregarCategorias() {

        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catgProd = cpdao.encontrar();
            CategoriaProd cp = new CategoriaProd();

            cp.setId(catgProd.size() + 1);
            cp.setNombre(txtNombrecatgP.getText());
            cp.setDescripcion(txtDescripcioncatgP.getText());

            cpdao.guardar(cp);
            cpdao.cerrar();

            JOptionPane.showMessageDialog(this, "La categoria se agrego correctamente",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);

            List<CategoriaProd> tabla = new ArrayList<>();
            tabla.add(cp);
            agregarTabla(tabla);

        } catch (IOException ex) {
            Logger.getLogger(CategoriasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarTabla(List<CategoriaProd> catgProd) {

        for (CategoriaProd cp : catgProd) {
            int id = cp.getId();
            String nombre = cp.getNombre().trim();
            String descripcion = cp.getDescripcion().trim();

            Object[] datos = {id, nombre, descripcion};
            modelo.addRow(datos);
        }
    }

    public void limpiarAgrega() {
        txtIDcatgP.setText("");
        txtNombrecatgP.setText("");
        txtDescripcioncatgP.setText("");
    }

    public void limpiarModifica(){
        txtIDcatgP_Modificar.setText("");
        txtNombrecatgP_Modificar.setText("");
        txtDescripcioncatgP_Modificar.setText("");
    }
    
    public void limpiarTabla() {
        for (int i = 0; i < this.TablaCategoriasP.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }

    public void buscarTabla() {
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catgProd = cpdao.encontrar();
            String buscar = txtBuscarTabla.getText();
            for (CategoriaProd cp : catgProd) {
                String nombre = cp.getNombre().trim();
                if (buscar.equalsIgnoreCase(nombre)) {
                    List<CategoriaProd> catg = new ArrayList<>();
                    catg.add(cp);

                    agregarTabla(catg);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CategoriasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarCatg(){
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catgP = cpdao.encontrar();
            
            int sel = this.TablaCategoriasP.getSelectedRow();
            DefaultTableModel mod = (DefaultTableModel) this.TablaCategoriasP.getModel();
            String seleccionado = this.TablaCategoriasP.getValueAt(sel, 1).toString();
            
            for (CategoriaProd cp : catgP) {
                String nombre = cp.getNombre().trim();
                if (seleccionado.equals(nombre)) {
                    
                    cp.setId(cp.getId());
                    cp.setNombre(txtNombrecatgP_Modificar.getText());
                    cp.setDescripcion(txtDescripcioncatgP_Modificar.getText());
                    
                    JOptionPane.showMessageDialog(this, "El empleado se ha modificado correctamente");
                    
                    cpdao.modificar(cp);
                    cpdao.cerrar();
                    
                    List<CategoriaProd> catg = new ArrayList<>();
                    catg.add(cp);
                    
                    agregarTabla(catg);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(CategoriasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCategoriasP = new org.jdesktop.swingx.JXTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtNombrecatgP = new javax.swing.JTextField();
        txtIDcatgP = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtDescripcioncatgP = new javax.swing.JTextArea();
        btnLimpiarVentanas = new org.edisoncor.gui.button.ButtonTask();
        btnAgregarCatgP = new org.edisoncor.gui.button.ButtonTask();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        jPanel6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtNombrecatgP_Modificar = new javax.swing.JTextField();
        txtIDcatgP_Modificar = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtDescripcioncatgP_Modificar = new javax.swing.JTextArea();
        btnLimpiarVentanas_Modificar = new org.edisoncor.gui.button.ButtonTask();
        btnModificarCatgP = new org.edisoncor.gui.button.ButtonTask();
        buttonTask3 = new org.edisoncor.gui.button.ButtonTask();
        buttonTask4 = new org.edisoncor.gui.button.ButtonTask();
        btnRegresar = new org.edisoncor.gui.button.ButtonTask();
        jPanel5 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtBuscarTabla = new javax.swing.JTextField();
        btnBuscarTabla = new javax.swing.JButton();
        btnRestaurarTabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Categoria de productos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablaCategoriasP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaCategoriasPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaCategoriasP);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 340, 500, 280));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregue los datos de la categoria"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Nombre");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Descripcion");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 25));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("ID");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, 25));
        jPanel4.add(txtNombrecatgP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 25));

        txtIDcatgP.setEditable(false);
        jPanel4.add(txtIDcatgP, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 40, 25));

        txtDescripcioncatgP.setColumns(20);
        txtDescripcioncatgP.setRows(5);
        jScrollPane2.setViewportView(txtDescripcioncatgP);

        jPanel4.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 200, -1));

        btnLimpiarVentanas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        btnLimpiarVentanas.setText("Limpiar");
        btnLimpiarVentanas.setDescription("Ventana");
        btnLimpiarVentanas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarVentanasActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiarVentanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 150, -1));

        btnAgregarCatgP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/NuevoProducto.png"))); // NOI18N
        btnAgregarCatgP.setText("Agregar");
        btnAgregarCatgP.setDescription("Categoria");
        btnAgregarCatgP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarCatgPActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarCatgP, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 150, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 506, 199));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/categoriasP.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 130, 170));

        jTabbedPane1.addTab("Agregar categorias", jPanel2);

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelTask1.setText("Modificar categoria de productos");
        labelTask1.setDescription("Seleccione en la tabla la categoria que desea modificar");
        jPanel1.add(labelTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 440, -1));

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Edite los campos que va a modificar"));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Nombre");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 100, 25));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setText("Descripcion");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 100, 25));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setText("ID");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 100, 25));
        jPanel6.add(txtNombrecatgP_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 200, 25));

        txtIDcatgP_Modificar.setEditable(false);
        jPanel6.add(txtIDcatgP_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 40, 25));

        txtDescripcioncatgP_Modificar.setColumns(20);
        txtDescripcioncatgP_Modificar.setRows(5);
        jScrollPane4.setViewportView(txtDescripcioncatgP_Modificar);

        jPanel6.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 200, -1));

        btnLimpiarVentanas_Modificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        btnLimpiarVentanas_Modificar.setText("Limpiar");
        btnLimpiarVentanas_Modificar.setDescription("Ventana");
        btnLimpiarVentanas_Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarVentanas_ModificarActionPerformed(evt);
            }
        });
        jPanel6.add(btnLimpiarVentanas_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 150, -1));

        btnModificarCatgP.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/Modificar.png"))); // NOI18N
        btnModificarCatgP.setText("Modificar");
        btnModificarCatgP.setDescription("Categoria");
        btnModificarCatgP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarCatgPActionPerformed(evt);
            }
        });
        jPanel6.add(btnModificarCatgP, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 150, -1));

        jPanel1.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 506, 199));

        jScrollPane3.setViewportView(jPanel1);

        jPanel3.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 695, 220));

        jTabbedPane1.addTab("Modificar categorias", jPanel3);

        jcMousePanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 700, 250));

        buttonTask3.setForeground(new java.awt.Color(255, 255, 255));
        jcMousePanel1.add(buttonTask3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 180, -1));

        buttonTask4.setForeground(new java.awt.Color(255, 255, 255));
        jcMousePanel1.add(buttonTask4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 180, -1));

        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/regresar.png"))); // NOI18N
        btnRegresar.setText("Regresar");
        btnRegresar.setDescription(" ");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jcMousePanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 180, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("Nombre de la catg");
        jPanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, 25));

        txtBuscarTabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarTablaKeyPressed(evt);
            }
        });
        jPanel5.add(txtBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 200, 25));

        btnBuscarTabla.setText("Buscar");
        btnBuscarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTablaActionPerformed(evt);
            }
        });
        jPanel5.add(btnBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        btnRestaurarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/atras.png"))); // NOI18N
        btnRestaurarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarTablaActionPerformed(evt);
            }
        });
        jPanel5.add(btnRestaurarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 33, 25));

        jcMousePanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 500, 60));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 716, 630));

        setSize(new java.awt.Dimension(732, 669));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarVentanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarVentanasActionPerformed
        limpiarAgrega();
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catg = cpdao.encontrar();
            txtIDcatgP.setText((catg.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(CategoriasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarVentanasActionPerformed

    private void btnAgregarCatgPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCatgPActionPerformed
        if (txtNombrecatgP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregue un nombre a la categoria");
            txtNombrecatgP.requestFocus();
            return;
        }
        if (txtDescripcioncatgP.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Agregue un nombre a la categoria");
            txtDescripcioncatgP.requestFocus();
            return;
        }
        agregarCategorias();
    }//GEN-LAST:event_btnAgregarCatgPActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        OpcionesDeProductos dialog = new OpcionesDeProductos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void btnBuscarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTablaActionPerformed
        limpiarTabla();
        buscarTabla();
    }//GEN-LAST:event_btnBuscarTablaActionPerformed

    private void btnRestaurarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarTablaActionPerformed
        try {
            ArchivoCategoriaProd pdao = new ArchivoCategoriaProd();
            List<CategoriaProd> prod = pdao.encontrar();

            limpiarTabla();
            agregarTabla(prod);
            txtBuscarTabla.setText("");
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRestaurarTablaActionPerformed

    private void txtBuscarTablaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarTablaKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            limpiarTabla();
            buscarTabla();
        }
    }//GEN-LAST:event_txtBuscarTablaKeyPressed

    private void btnLimpiarVentanas_ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarVentanas_ModificarActionPerformed
        try {
            limpiarModifica();
            btnLimpiarVentanas_Modificar.setVisible(false);
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catg = cpdao.encontrar();
            
            txtIDcatgP_Modificar.setText((catg.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(CategoriasDeProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarVentanas_ModificarActionPerformed

    private void btnModificarCatgPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarCatgPActionPerformed
        if (txtNombrecatgP_Modificar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre a la categoria");
            return;
        }
        if (txtDescripcioncatgP_Modificar.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese una descripcion a la categoria");
            return;
        }
        
        limpiarTabla();
        modificarCatg();
        btnModificarCatgP.setVisible(false);
        btnLimpiarVentanas_Modificar.setVisible(true);
    }//GEN-LAST:event_btnModificarCatgPActionPerformed

    private void TablaCategoriasPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaCategoriasPMouseClicked
        if (this.TablaCategoriasP.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Esta tabla no contiene ningun elemento");
            return;
        }
        int sel = this.TablaCategoriasP.getSelectedRow();
        DefaultTableModel mod = (DefaultTableModel) this.TablaCategoriasP.getModel();
        
        txtIDcatgP_Modificar.setText(this.TablaCategoriasP.getValueAt(sel, 0).toString());
        txtNombrecatgP_Modificar.setText(this.TablaCategoriasP.getValueAt(sel, 1).toString());
        txtDescripcioncatgP_Modificar.setText(this.TablaCategoriasP.getValueAt(sel, 2).toString());
        btnModificarCatgP.setVisible(true);
    }//GEN-LAST:event_TablaCategoriasPMouseClicked

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
            java.util.logging.Logger.getLogger(CategoriasDeProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriasDeProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriasDeProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriasDeProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CategoriasDeProductos dialog = new CategoriasDeProductos(new javax.swing.JFrame(), true);
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
    private org.jdesktop.swingx.JXTable TablaCategoriasP;
    private org.edisoncor.gui.button.ButtonTask btnAgregarCatgP;
    private javax.swing.JButton btnBuscarTabla;
    private org.edisoncor.gui.button.ButtonTask btnLimpiarVentanas;
    private org.edisoncor.gui.button.ButtonTask btnLimpiarVentanas_Modificar;
    private org.edisoncor.gui.button.ButtonTask btnModificarCatgP;
    private org.edisoncor.gui.button.ButtonTask btnRegresar;
    private javax.swing.JButton btnRestaurarTabla;
    private org.edisoncor.gui.button.ButtonTask buttonTask3;
    private org.edisoncor.gui.button.ButtonTask buttonTask4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    private javax.swing.JTextField txtBuscarTabla;
    private javax.swing.JTextArea txtDescripcioncatgP;
    private javax.swing.JTextArea txtDescripcioncatgP_Modificar;
    private javax.swing.JTextField txtIDcatgP;
    private javax.swing.JTextField txtIDcatgP_Modificar;
    private javax.swing.JTextField txtNombrecatgP;
    private javax.swing.JTextField txtNombrecatgP_Modificar;
    // End of variables declaration//GEN-END:variables
}
