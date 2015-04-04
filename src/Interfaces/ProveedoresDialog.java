/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoProveedores;
import Pojos.Proveedores;
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
public class ProveedoresDialog extends javax.swing.JDialog {

    DefaultTableModel modelo;
    
    public ProveedoresDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtRucProveedor.requestFocus();
        tabla();
        try {
            ArchivoProveedores pdao = new ArchivoProveedores();
            List<Proveedores> prov = pdao.encontrar();
            txtIDProveedor.setText((prov.size() + 1) + "");
            agregarTabla(prov);
        } catch (IOException ex) {
            Logger.getLogger(ProveedoresDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void tabla() {
        String[] cabecera = {"ID", "Cedula Ruc", "Razon social", "Direccion", "Telefono"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, cabecera);
        TablaProveedores.setModel(modelo);
    }

    public void agregarProveedores(){
    
        try {
            ArchivoProveedores pdao = new ArchivoProveedores();
            List<Proveedores> prov = pdao.encontrar();
            Proveedores p = new Proveedores();
            
            p.setId(prov.size() + 1);
            p.setRuc(txtRucProveedor.getText());
            p.setRazon_social(txtRazon_SocialProveedor.getText());
            p.setDireccion(txtDireccionProveedor.getText());
            p.setTelefono(txtTelefonoProveedor.getText());
            
            pdao.guardar(p);
            pdao.cerrar();
            
            JOptionPane.showMessageDialog(this, "El proveedor se ha agregado correctamente",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
            List<Proveedores> cargar = new ArrayList<>();
            cargar.add(p);
            
            agregarTabla(cargar);
        } catch (IOException ex) {
            Logger.getLogger(ProveedoresDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarTabla(List<Proveedores> prov){
    
        for (Proveedores p : prov) {
            int id = p.getId();
            String ruc = p.getRuc().trim();
            String razon = p.getRazon_social().trim();
            String direccion = p.getDireccion().trim();
            String telf = p.getTelefono().trim();
            
            Object [] datos = {id, ruc, razon, direccion, telf};
            modelo.addRow(datos);
        }
    }
    
    private void limpiarTabla() {
        for (int i = 0; i < TablaProveedores.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public void limpiarAgrega(){
        txtIDProveedor.setText("");
        txtRucProveedor.setText("");
        txtRazon_SocialProveedor.setText("");
        txtDireccionProveedor.setText("");
        txtTelefonoProveedor.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtIDProveedor = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtRucProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtRazon_SocialProveedor = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDireccionProveedor = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTelefonoProveedor = new javax.swing.JTextField();
        btnLimpiarAgrega = new org.edisoncor.gui.button.ButtonTask();
        btnAgregarProveedores = new org.edisoncor.gui.button.ButtonTask();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProveedores = new org.jdesktop.swingx.JXTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtBuscarTabla = new javax.swing.JTextField();
        btnBuscarTabla = new javax.swing.JButton();
        btnRestaurarTabla = new javax.swing.JButton();
        buttonTask2 = new org.edisoncor.gui.button.ButtonTask();
        buttonTask4 = new org.edisoncor.gui.button.ButtonTask();
        buttonTask5 = new org.edisoncor.gui.button.ButtonTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregue los datos del proveedor"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setText("ID");
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 25));

        txtIDProveedor.setEditable(false);
        jPanel4.add(txtIDProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 40, 25));

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Cedula Ruc");
        jPanel4.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 100, 25));
        jPanel4.add(txtRucProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setText("Razon social");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 25));
        jPanel4.add(txtRazon_SocialProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 200, 25));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setText("Direccion");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 130, 100, 25));
        jPanel4.add(txtDireccionProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 200, 25));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setText("Telefono");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 100, 25));
        jPanel4.add(txtTelefonoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 200, 25));

        btnLimpiarAgrega.setText("Limpiar");
        btnLimpiarAgrega.setDescription("Ventanas");
        btnLimpiarAgrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarAgregaActionPerformed(evt);
            }
        });
        jPanel4.add(btnLimpiarAgrega, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 110, 170, -1));

        btnAgregarProveedores.setText("Agregar");
        btnAgregarProveedores.setDescription("Proveedores");
        btnAgregarProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarProveedoresActionPerformed(evt);
            }
        });
        jPanel4.add(btnAgregarProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 40, 170, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 550, 200));

        jTabbedPane1.addTab("Agregar Proveedores", jPanel1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 755, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 233, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Modificar Proveedores", jPanel2);

        jcMousePanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 760, 260));

        jScrollPane1.setViewportView(TablaProveedores);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 350, 550, 280));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setText("Nombre del proveedor");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 130, 25));
        jPanel3.add(txtBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, 200, 25));

        btnBuscarTabla.setText("Buscar");
        btnBuscarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTablaActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, -1, -1));

        btnRestaurarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/atras.png"))); // NOI18N
        btnRestaurarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarTablaActionPerformed(evt);
            }
        });
        jPanel3.add(btnRestaurarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 33, 25));

        jcMousePanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 280, 550, 60));

        buttonTask2.setText("Regresar");
        buttonTask2.setDescription(" ");
        jcMousePanel1.add(buttonTask2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 170, -1));

        buttonTask4.setText("Agregar");
        buttonTask4.setDescription("Proveedores");
        jcMousePanel1.add(buttonTask4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, 170, -1));

        buttonTask5.setText("Agregar");
        buttonTask5.setDescription("Proveedores");
        jcMousePanel1.add(buttonTask5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 170, -1));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 640));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTablaActionPerformed
//        try {
//            buscarTabla();
//        } catch (IOException ex) {
//            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnBuscarTablaActionPerformed

    private void btnRestaurarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarTablaActionPerformed
//        try {
//            ArchivoProductos pdao = new ArchivoProductos();
//            List<Productos> prod = pdao.encontrar();
//
//            limpiarTabla();
//            agregarTabla(prod);
//            txtBuscarTabla.setText("");
//        } catch (IOException ex) {
//            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnRestaurarTablaActionPerformed

    private void btnAgregarProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarProveedoresActionPerformed
        agregarProveedores();
    }//GEN-LAST:event_btnAgregarProveedoresActionPerformed

    private void btnLimpiarAgregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarAgregaActionPerformed
        try {
            limpiarAgrega();
            ArchivoProveedores pdao = new ArchivoProveedores();
            List<Proveedores> prov = pdao.encontrar();
            txtIDProveedor.setText((prov.size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(ProveedoresDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnLimpiarAgregaActionPerformed

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
            java.util.logging.Logger.getLogger(ProveedoresDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProveedoresDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProveedoresDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProveedoresDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create andtxtIDProveedore dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ProveedoresDialog dialog = new ProveedoresDialog(new javax.swing.JFrame(), true);
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
    private org.jdesktop.swingx.JXTable TablaProveedores;
    private org.edisoncor.gui.button.ButtonTask btnAgregarProveedores;
    private javax.swing.JButton btnBuscarTabla;
    private org.edisoncor.gui.button.ButtonTask btnLimpiarAgrega;
    private javax.swing.JButton btnRestaurarTabla;
    private org.edisoncor.gui.button.ButtonTask buttonTask2;
    private org.edisoncor.gui.button.ButtonTask buttonTask4;
    private org.edisoncor.gui.button.ButtonTask buttonTask5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JTextField txtBuscarTabla;
    private javax.swing.JTextField txtDireccionProveedor;
    private javax.swing.JTextField txtIDProveedor;
    private javax.swing.JTextField txtRazon_SocialProveedor;
    private javax.swing.JTextField txtRucProveedor;
    private javax.swing.JTextField txtTelefonoProveedor;
    // End of variables declaration//GEN-END:variables
}
