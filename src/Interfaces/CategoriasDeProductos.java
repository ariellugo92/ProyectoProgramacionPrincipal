/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoCategoriaProd;
import Pojos.CategoriaProd;
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
        tabla();
        try{
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
    
    public void limpiarAgrega(){
        txtIDcatgP.setText("");
        txtNombrecatgP.setText("");
        txtDescripcioncatgP.setText("");
    }
    
    public void limpiarTabla(){
        for (int i = 0; i < this.TablaCategoriasP.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 695, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 223, Short.MAX_VALUE)
        );

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
        try {
            buscarTabla();
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    private org.edisoncor.gui.button.ButtonTask btnRegresar;
    private javax.swing.JButton btnRestaurarTabla;
    private org.edisoncor.gui.button.ButtonTask buttonTask3;
    private org.edisoncor.gui.button.ButtonTask buttonTask4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JTextField txtBuscarTabla;
    private javax.swing.JTextArea txtDescripcioncatgP;
    private javax.swing.JTextField txtIDcatgP;
    private javax.swing.JTextField txtNombrecatgP;
    // End of variables declaration//GEN-END:variables
}
