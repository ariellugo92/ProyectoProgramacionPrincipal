/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

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
        botonDptos1 = new javax.swing.JButton();
        botonEmpleados1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        botonDptos = new javax.swing.JButton();
        botonEmpleados = new javax.swing.JButton();
        botonRRHH = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ventana Principal");
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoPrin.png")).getImage());
        setPreferredSize(null);

        panelPrin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        panelPrin.setVisibleLogo(false);
        panelPrin.setLayout(new java.awt.GridBagLayout());

        botonDptos1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/1425016208_companies.png"))); // NOI18N
        botonDptos1.setText(" Gestionar departamentos");
        botonDptos1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/1425016208_companies_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.ipadx = 23;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(200, 320, 0, 0);
        panelPrin.add(botonDptos1, gridBagConstraints);

        botonEmpleados1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/productos.png"))); // NOI18N
        botonEmpleados1.setText(" Gestionar productos");
        botonEmpleados1.setBorder(null);
        botonEmpleados1.setBorderPainted(false);
        botonEmpleados1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/productos_precionado.png"))); // NOI18N
        botonEmpleados1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEmpleados1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 81;
        gridBagConstraints.ipady = 17;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 320, 0, 0);
        panelPrin.add(botonEmpleados1, gridBagConstraints);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/ventas.png"))); // NOI18N
        jButton5.setText(" Gestionar Ventas");
        jButton5.setRolloverEnabled(true);
        jButton5.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/ventas_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 60;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 320, 172, 0);
        panelPrin.add(jButton5, gridBagConstraints);

        botonDptos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/agregar cmpras.png"))); // NOI18N
        botonDptos.setText("Gestionar Compras");
        botonDptos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/agregar cmpras_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 55;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 0, 245);
        panelPrin.add(botonDptos, gridBagConstraints);

        botonEmpleados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/empleados.png"))); // NOI18N
        botonEmpleados.setText(" Gestionar empleados");
        botonEmpleados.setBorder(null);
        botonEmpleados.setBorderPainted(false);
        botonEmpleados.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/empleados_60.png"))); // NOI18N
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
        gridBagConstraints.insets = new java.awt.Insets(200, 20, 0, 245);
        panelPrin.add(botonEmpleados, gridBagConstraints);

        botonRRHH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/rrhh.png"))); // NOI18N
        botonRRHH.setText("Recursos humanos");
        botonRRHH.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/rrhh_precionado.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 54;
        gridBagConstraints.ipady = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.insets = new java.awt.Insets(20, 20, 172, 245);
        panelPrin.add(botonRRHH, gridBagConstraints);

        getContentPane().add(panelPrin, java.awt.BorderLayout.CENTER);

        setLocation(new java.awt.Point(0, 0));
    }// </editor-fold>//GEN-END:initComponents

    private void botonEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpleadosActionPerformed
        GestionarEmpleados dialog = new GestionarEmpleados(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_botonEmpleadosActionPerformed

    private void botonEmpleados1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEmpleados1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_botonEmpleados1ActionPerformed

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
    private javax.swing.JButton botonDptos;
    private javax.swing.JButton botonDptos1;
    private javax.swing.JButton botonEmpleados;
    private javax.swing.JButton botonEmpleados1;
    private javax.swing.JButton botonRRHH;
    private javax.swing.JButton jButton5;
    private jcMousePanel.jcMousePanel panelPrin;
    // End of variables declaration//GEN-END:variables
}