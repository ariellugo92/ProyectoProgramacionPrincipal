/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoDepartamentos;
import Pojos.Departamentos;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ariellugo92
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private LoginDeInicio GifProgreso;
    /**
     * Creates new form VentanaPrincipal
     */
    public VentanaPrincipal() {
        initComponents();
        this.setExtendedState(VentanaPrincipal.MAXIMIZED_BOTH); 
        LoginDeInicio login = new LoginDeInicio();
    }

    VentanaPrincipal(LoginDeInicio GifProgreso) {
        this.GifProgreso = GifProgreso;
        setProgreso();
        GifProgreso.getJBusy().setVisible(false);
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setLocationRelativeTo(null);
    }
    
    private void setProgreso(){
          GifProgreso.getJBusy();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "HUBO UN ERROR AL EJECUTAR LA APLICACION");
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
        java.awt.GridBagConstraints gridBagConstraints;

        panelPrin = new jcMousePanel.jcMousePanel();
        btonDptos = new javax.swing.JButton();
        botonProductos = new javax.swing.JButton();
        botonVentas = new javax.swing.JButton();
        botonCompras = new javax.swing.JButton();
        botonEmpleados = new javax.swing.JButton();
        botonRRHH = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoPrin.png")).getImage());
        setPreferredSize(null);

        panelPrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        panelPrin.setVisibleLogo(false);
        panelPrin.setLayout(new java.awt.GridBagLayout());

        btonDptos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/1425016208_companies.png"))); // NOI18N
        btonDptos.setText(" Gestionar departamentos");
        btonDptos.setBorder(null);
        btonDptos.setPreferredSize(new java.awt.Dimension(170, 70));
        btonDptos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/1425016208_companies_precionado.png"))); // NOI18N
        btonDptos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btonDptosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 57;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(199, 316, 0, 0);
        panelPrin.add(btonDptos, gridBagConstraints);

        botonProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/productos.png"))); // NOI18N
        botonProductos.setText(" Gestionar productos");
        botonProductos.setBorder(null);
        botonProductos.setBorderPainted(false);
        botonProductos.setPreferredSize(new java.awt.Dimension(170, 70));
        botonProductos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/productos_precionado.png"))); // NOI18N
        botonProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonProductosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 81;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 316, 0, 0);
        panelPrin.add(botonProductos, gridBagConstraints);

        botonVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/ventas.png"))); // NOI18N
        botonVentas.setText(" Gestionar Ventas");
        botonVentas.setBorder(null);
        botonVentas.setPreferredSize(new java.awt.Dimension(170, 70));
        botonVentas.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/ventas_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 94;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 316, 165, 0);
        panelPrin.add(botonVentas, gridBagConstraints);

        botonCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/agregar cmpras.png"))); // NOI18N
        botonCompras.setText("Gestionar Compras");
        botonCompras.setBorder(null);
        botonCompras.setPreferredSize(new java.awt.Dimension(170, 70));
        botonCompras.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/agregar cmpras_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 89;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 24, 0, 245);
        panelPrin.add(botonCompras, gridBagConstraints);

        botonEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/empleados.png"))); // NOI18N
        botonEmpleados.setText(" Gestionar empleados");
        botonEmpleados.setBorder(null);
        botonEmpleados.setBorderPainted(false);
        botonEmpleados.setPreferredSize(new java.awt.Dimension(170, 70));
        botonEmpleados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/empleados_precionado.png"))); // NOI18N
        botonEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpleadosActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 77;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(199, 24, 0, 245);
        panelPrin.add(botonEmpleados, gridBagConstraints);

        botonRRHH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/rrhh.png"))); // NOI18N
        botonRRHH.setText("Recursos humanos");
        botonRRHH.setBorder(null);
        botonRRHH.setPreferredSize(new java.awt.Dimension(170, 70));
        botonRRHH.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/rrhh_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 88;
        gridBagConstraints.ipady = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(24, 24, 165, 245);
        panelPrin.add(botonRRHH, gridBagConstraints);

        getContentPane().add(panelPrin, java.awt.BorderLayout.CENTER);

        setBounds(0, 0, 841, 550);
    }// </editor-fold>//GEN-END:initComponents

    private void botonEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpleadosActionPerformed
        try {
            ArchivoDepartamentos ddao = new ArchivoDepartamentos();
            List<Departamentos> dpto = ddao.encontrar();
            
            if (dpto.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Primero introduzca departmaentos para ubicar a sus empleados");
                return;
            }
            
            GestionarEmpleados dialog = new GestionarEmpleados(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonEmpleadosActionPerformed

    private void botonProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonProductosActionPerformed
        GestionarProductos dialog = new GestionarProductos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_botonProductosActionPerformed

    private void btonDptosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btonDptosActionPerformed
        GestionarDptos dialog = new GestionarDptos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btonDptosActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonCompras;
    private javax.swing.JButton botonEmpleados;
    private javax.swing.JButton botonProductos;
    private javax.swing.JButton botonRRHH;
    private javax.swing.JButton botonVentas;
    private javax.swing.JButton btonDptos;
    private jcMousePanel.jcMousePanel panelPrin;
    // End of variables declaration//GEN-END:variables
}