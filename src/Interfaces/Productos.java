/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author IaraDenisse
 */
public class Productos extends javax.swing.JDialog {

    /**
     * Creates new form Productos
     */
    public Productos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        agregarProd_avatar();
    }

    public final void agregarProd_avatar() {

        panelAvatarChooser1.setAmount(3);
        panelAvatarChooser1.getTitulos().add("Gestionar Productos");
        panelAvatarChooser1.getTitulos().add("Gestionar Categorias");
        panelAvatarChooser1.getTitulos().add("Control de productos");
        
        List<org.edisoncor.gui.util.Avatar> avatares = new ArrayList<>();

        avatares.add(new org.edisoncor.gui.util.Avatar("Acerca del manejo de los productos", loadImage("/imagenes/Iconos/gestionProducts.png")));
        avatares.add(new org.edisoncor.gui.util.Avatar("Acerca de las categorias de los productos", loadImage("/imagenes/Iconos/categoriasProd.png")));
        avatares.add(new org.edisoncor.gui.util.Avatar("Niveles del stock de los productos", loadImage("/imagenes/Iconos/stock.png")));

        this.panelAvatarChooser1.setAvatars(avatares);
    }

    public static Image loadImage(String filename) {

        try {

            return ImageIO.read(JFrame.class.getResource(filename));

        } catch (IOException ex) {
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelAvatarChooser1 = new org.edisoncor.gui.panel.PanelAvatarChooser();
        jLabel1 = new javax.swing.JLabel();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelAvatarChooser1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelAvatarChooser1MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Elija una opcion");

        btnRegresar.setText("Regresar");
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelAvatarChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(btnRegresar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelAvatarChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRegresar)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(551, 388));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void panelAvatarChooser1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelAvatarChooser1MouseClicked
        int i = this.panelAvatarChooser1.getAvatarIndex();
        
        if(i==0){
            GestionarProductos dialog = new GestionarProductos(new javax.swing.JFrame(), true);
            dialog.setVisible(true);
            this.dispose();  
            System.out.println(i);
        }
        
        if(i==1){
            new CategoriaProductos().setVisible(true);
            this.dispose();
            System.out.println(i);
        }
        
        if(i==2){
            System.out.println(i);
        }
    }//GEN-LAST:event_panelAvatarChooser1MouseClicked

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Productos dialog = new Productos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnRegresar;
    private javax.swing.JLabel jLabel1;
    private org.edisoncor.gui.panel.PanelAvatarChooser panelAvatarChooser1;
    // End of variables declaration//GEN-END:variables
}
