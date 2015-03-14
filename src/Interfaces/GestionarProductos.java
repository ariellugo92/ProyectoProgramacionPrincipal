/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoProductos;
import Pojos.Productos;
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
public class GestionarProductos extends javax.swing.JDialog {

    DefaultTableModel modelo;
    
    public void tabla(){
    String[] cabecera = {"ID", "Nombre", "Categoria", "Cantidad", "Marca", "Medida"};
    String datos[][] = {};
    modelo  = new DefaultTableModel(datos, cabecera);
    TablaProductos.setModel (modelo);
    }

    public GestionarProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtNombreProduct.requestFocus();
        botonLimpiarProduct.setVisible(false);
        tabla();
        try {
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();
            txtIDProduct.setText((prod.size() + 1) + "");
            agregarTabla(prod);
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void agregarProductos() throws IOException {

        ArchivoProductos pdao = new ArchivoProductos();
        List<Productos> prod = pdao.encontrar();
        Productos p = new Productos();

        p.setId(prod.size() + 1);
        p.setNombre(txtNombreProduct.getText());
        p.setCategoriaProductos(txtCategoriaProduct.getText());
        p.setMarca(txtMarcaProduct.getText());
        p.setCantidad(0.00);
        p.setUnidadMedida(txtUnidadMedidaProduct.getText());

        pdao.guardar(p);
        pdao.cerrar();
        
        JOptionPane.showMessageDialog(null, "El producto se agrego correctamente");
        
        List<Productos> tabla = new ArrayList<>();
        tabla.add(p);
        
        agregarTabla(tabla);
    }

    public void agregarTabla(List<Productos> p) {

        for (Productos pp : p) {
            int id = pp.getId();
            String nombre = pp.getNombre().trim();
            String categ = pp.getCategoriaProductos().trim();
            String marca = pp.getMarca().trim();
            double cant = pp.getCantidad();
            String unidMed = pp.getUnidadMedida();
            
            Object datos [] = {id, nombre, categ, cant, marca, unidMed};
            modelo.addRow(datos);
        }
    }
    
    public void limpiarAgrega(){
    
        txtIDProduct.setText("");
        txtNombreProduct.setText("");
        txtCategoriaProduct.setText("");
        txtMarcaProduct.setText("");
        txtUnidadMedidaProduct.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMarcaProduct = new javax.swing.JTextField();
        txtNombreProduct = new javax.swing.JTextField();
        txtCategoriaProduct = new javax.swing.JTextField();
        txtIDProduct = new javax.swing.JTextField();
        botonAgregarProducto = new org.edisoncor.gui.button.ButtonTask();
        botonLimpiarProduct = new org.edisoncor.gui.button.ButtonTask();
        jLabel14 = new javax.swing.JLabel();
        txtUnidadMedidaProduct = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new org.jdesktop.swingx.JXTable();
        botonReporteProductos = new org.edisoncor.gui.button.ButtonTask();
        botonExportar_a_ExcelProduct = new org.edisoncor.gui.button.ButtonTask();
        botonRegresarProducto = new org.edisoncor.gui.button.ButtonTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregue los datos del producto"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setText("Nombre");
        jPanel4.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 115, 25));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setText("Categoria");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 115, 25));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setText("Marca del producto");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 115, 25));

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setText("ID");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 115, 25));
        jPanel4.add(txtMarcaProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 200, 25));
        jPanel4.add(txtNombreProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 25));
        jPanel4.add(txtCategoriaProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, 25));

        txtIDProduct.setEditable(false);
        jPanel4.add(txtIDProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 40, 25));

        botonAgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/NuevoProducto.png"))); // NOI18N
        botonAgregarProducto.setText("Agregar");
        botonAgregarProducto.setDescription("Productos");
        botonAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProductoActionPerformed(evt);
            }
        });
        jPanel4.add(botonAgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 160, -1));

        botonLimpiarProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        botonLimpiarProduct.setText("Limpiar");
        botonLimpiarProduct.setDescription("Ventanas");
        botonLimpiarProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarProductActionPerformed(evt);
            }
        });
        jPanel4.add(botonLimpiarProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 160, -1));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setText("Unidad de medida");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 115, 25));
        jPanel4.add(txtUnidadMedidaProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 200, 25));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 550, 230));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/CamionProdcutos.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 140, 120));

        jTabbedPane1.addTab("Agregar Productos", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 765, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Modificar Productos", jPanel2);

        jcMousePanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 770, 280));

        jScrollPane1.setViewportView(TablaProductos);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 510, 270));

        botonReporteProductos.setForeground(new java.awt.Color(255, 255, 255));
        botonReporteProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportes.png"))); // NOI18N
        botonReporteProductos.setText("Reporte");
        botonReporteProductos.setDescription("de productos");
        jcMousePanel1.add(botonReporteProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 200, -1));

        botonExportar_a_ExcelProduct.setForeground(new java.awt.Color(255, 255, 255));
        botonExportar_a_ExcelProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/excel.png"))); // NOI18N
        botonExportar_a_ExcelProduct.setText("Exportar");
        botonExportar_a_ExcelProduct.setDescription("a Miscrosoft Excel");
        jcMousePanel1.add(botonExportar_a_ExcelProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 200, -1));

        botonRegresarProducto.setForeground(new java.awt.Color(255, 255, 255));
        botonRegresarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/regresar.png"))); // NOI18N
        botonRegresarProducto.setText("Regresar");
        botonRegresarProducto.setDescription(" ");
        botonRegresarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarProductoActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonRegresarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 510, 200, -1));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 610));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarProductoActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarProductoActionPerformed

    private void botonAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProductoActionPerformed
        try {
            agregarProductos();
            botonLimpiarProduct.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonAgregarProductoActionPerformed

    private void botonLimpiarProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarProductActionPerformed
        try {
            limpiarAgrega();
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();
            txtIDProduct.setText((prod.size() + 1) + "");
            botonLimpiarProduct.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonLimpiarProductActionPerformed

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
            java.util.logging.Logger.getLogger(GestionarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarProductos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarProductos dialog = new GestionarProductos(new javax.swing.JFrame(), true);
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
    private org.jdesktop.swingx.JXTable TablaProductos;
    private org.edisoncor.gui.button.ButtonTask botonAgregarProducto;
    private org.edisoncor.gui.button.ButtonTask botonExportar_a_ExcelProduct;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarProduct;
    private org.edisoncor.gui.button.ButtonTask botonRegresarProducto;
    private org.edisoncor.gui.button.ButtonTask botonReporteProductos;
    private org.edisoncor.gui.button.ButtonTask buttonTask4;
    private org.edisoncor.gui.button.ButtonTask buttonTask5;
    private org.edisoncor.gui.button.ButtonTask buttonTask6;
    private org.edisoncor.gui.button.ButtonTask buttonTask7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JTextField txtCategoriaProduct;
    private javax.swing.JTextField txtIDProduct;
    private javax.swing.JTextField txtMarcaProduct;
    private javax.swing.JTextField txtNombreProduct;
    private javax.swing.JTextField txtUnidadMedidaProduct;
    // End of variables declaration//GEN-END:variables
}
