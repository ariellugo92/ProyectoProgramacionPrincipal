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
public class CategoriaProductos extends javax.swing.JFrame {

    DefaultTableModel modelo;

    public CategoriaProductos() {
        initComponents();
        txtNombrecatgP.requestFocus();
        tabla();
        try{
        ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
        List<CategoriaProd> catg = cpdao.encontrar();
        agregarTabla(catg);
        txtIDcatgP.setText((catg.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(CategoriaProductos.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(CategoriaProductos.class.getName()).log(Level.SEVERE, null, ex);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(TablaCategoriasP);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 270, 500, 280));

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

        btnLimpiarVentanas.setText("Limpiar");
        btnLimpiarVentanas.setDescription("Ventana");
        btnLimpiarVentanas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarVentanasActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiarVentanas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 150, -1));

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
        jcMousePanel1.add(buttonTask3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 180, -1));

        buttonTask4.setForeground(new java.awt.Color(255, 255, 255));
        jcMousePanel1.add(buttonTask4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 180, -1));

        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Regresar");
        btnRegresar.setDescription(" ");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jcMousePanel1.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 180, -1));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 716, 563));

        setSize(new java.awt.Dimension(732, 602));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarCatgPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarCatgPActionPerformed
        agregarCategorias();
    }//GEN-LAST:event_btnAgregarCatgPActionPerformed

    private void btnLimpiarVentanasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarVentanasActionPerformed
        limpiarAgrega();
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catg = cpdao.encontrar();
            txtIDcatgP.setText((catg.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(CategoriaProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarVentanasActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
        Productos dialog = new Productos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

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
            java.util.logging.Logger.getLogger(CategoriaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CategoriaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CategoriaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CategoriaProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CategoriaProductos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXTable TablaCategoriasP;
    private org.edisoncor.gui.button.ButtonTask btnAgregarCatgP;
    private org.edisoncor.gui.button.ButtonTask btnLimpiarVentanas;
    private org.edisoncor.gui.button.ButtonTask btnRegresar;
    private org.edisoncor.gui.button.ButtonTask buttonTask3;
    private org.edisoncor.gui.button.ButtonTask buttonTask4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JTextArea txtDescripcioncatgP;
    private javax.swing.JTextField txtIDcatgP;
    private javax.swing.JTextField txtNombrecatgP;
    // End of variables declaration//GEN-END:variables
}
