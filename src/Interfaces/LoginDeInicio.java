/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoCuentas;
import Pojos.Cuentas;
import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXBusyLabel;
//import org.jdesktop.swingx.JXBusyLabel;

/**
 *
 * @author ariellugo92
 */
public class LoginDeInicio extends javax.swing.JFrame {

    private InicioSplash barraProgresoFrame;
    private LoginDeInicio GifProgreso = this;

    public LoginDeInicio(InicioSplash barraProgresoFrame) {
        this.barraProgresoFrame = barraProgresoFrame;
        setProgreso(0, "Iniciando aplicacion");
        initComponents();
        this.BusyGif.setVisible(false);
        txtUsuarioLogin.requestFocus();
        setProgreso(20, "Cargando componentes...");
        setProgreso(40, "Cargando archivos...");
        setProgreso(60, "Cargando empleados...");

        setProgreso(80, "Cargando departamentos...");
        setProgreso(90, "Complementos cargados...");
        setProgreso(100, "Aplicacion lista para usarse");
        this.setLocationRelativeTo(null);
    }

    /**
     * Creates new form LoginDeInicio
     */
    public LoginDeInicio() {
        initComponents();
        this.BusyGif.setVisible(false);
        txtUsuarioLogin.requestFocus();
    }

    private void inicioHilo() {

        Thread hilo = new Thread(new Runnable() {

            @Override
            public void run() {
                VentanaPrincipal prin = new VentanaPrincipal(GifProgreso);
                prin.setVisible(true);
                dispose();
            }
        });
        hilo.start();
    }
    
    static String usuario = "";
    public static String getUsuario(){
        return usuario;
    }
    static String tipo = "";
    public static String getTipos(){
        return tipo;
    }

    public JXBusyLabel getJBusy() {
        return BusyGif;
    }

    public JTextField getTxtField() {
        return txtUsuarioLogin;
    }

    private void setProgreso(int porcentaje, String informacion) {
        barraProgresoFrame.getJLabel().setText(informacion);
        barraProgresoFrame.getJProgressBar().setValue(porcentaje);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "HUBO UN ERROR AL EJECUTAR LA APLICACION");
        }
    }

    int cont = 1;

    public void ingresar() throws IOException {

        boolean flag = false;
        if (cont < 3) {
            
            if (txtUsuarioLogin.getText().length() == 0 && txtContraseñaLogin.getText().length() == 0) {
                JOptionPane.showMessageDialog(this, "No ha ingresado ningun dato", "Error",
                        JOptionPane.ERROR_MESSAGE);
            } else {

                ArchivoCuentas cdao = new ArchivoCuentas();
                List<Cuentas> ctas = cdao.encontrar();

                for (Cuentas c : ctas) {
                    if (txtUsuarioLogin.getText().equals(c.getUsuario().trim())
                            && txtContraseñaLogin.getText().equals(c.getContraseña().trim())) {
                        
                        usuario = this.txtUsuarioLogin.getText();
                        tipo = c.getTipo().trim();
                        
                        botonIngresar.setVisible(false);
                        BusyGif.setVisible(true);
                        BusyGif.setBusy(true);
                        inicioHilo();
                        flag = false;
                        break;
                    } else {
                        flag = true;
                    }
                }

                if (flag) {
                    JOptionPane.showMessageDialog(this, "Error, usuario o contraseña incorrecto", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    txtUsuarioLogin.setText("");
                    txtContraseñaLogin.setText("");
                    cont++;
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Los sentimos usted ya lleva " + cont
                    + " intentos erroneos, por seguridad vuelva a iniciar la aplicacion");
            System.exit(0);
        }
    }

    public void registrar() throws IOException {

        ArchivoCuentas cdao = new ArchivoCuentas();
        List<Cuentas> ctas = cdao.encontrar();
        Cuentas c = new Cuentas();

        c.setId(ctas.size() + 1);
        c.setUsuario(txtNuevoUsuario.getText());
        c.setContraseña(txtNuevaContraseña.getText());
        if (ctas.isEmpty()) {
            c.setTipo("Administrador");
        }else {
            c.setTipo("Empleado");
        }

        cdao.guardar(c);
        cdao.cerrar();

        JOptionPane.showMessageDialog(null, "Usted se ha registrado correctamente");
        txtNuevoUsuario.setText("");
        txtNuevaContraseña.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtContraseñaLogin = new org.edisoncor.gui.passwordField.PasswordFieldRectBackground();
        txtUsuarioLogin = new org.edisoncor.gui.textField.TextFieldRectBackground();
        botonIngresar = new org.edisoncor.gui.button.ButtonTask();
        BusyGif = new org.jdesktop.swingx.JXBusyLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtNuevoUsuario = new org.edisoncor.gui.textField.TextFieldRectBackground();
        txtNuevaContraseña = new org.edisoncor.gui.textField.TextFieldRectBackground();
        jLabel6 = new javax.swing.JLabel();
        txtConfirmContra = new org.edisoncor.gui.passwordField.PasswordFieldRectBackground();
        botonRegistrar = new org.edisoncor.gui.button.ButtonTask();
        labelInform = new javax.swing.JLabel();
        labelInform1 = new javax.swing.JLabel();
        labelInform2 = new javax.swing.JLabel();
        clockDigital1 = new org.edisoncor.gui.varios.ClockDigital();
        botonSalirApp = new org.edisoncor.gui.button.ButtonTask();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoDelLogin.png")).getImage());
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Inicio de secion"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Usuario");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 100, 25));

        jLabel2.setText("Contraseña");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 100, 25));

        txtContraseñaLogin.setDescripcion("ingrese su contraseña aqui");
        txtContraseñaLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseñaLoginKeyPressed(evt);
            }
        });
        jPanel3.add(txtContraseñaLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, 200, 25));

        txtUsuarioLogin.setToolTipText("");
        txtUsuarioLogin.setDescripcion("Ingrese su usuario aqui");
        jPanel3.add(txtUsuarioLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 200, 25));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 360, 170));

        botonIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/iconoUser.png"))); // NOI18N
        botonIngresar.setText("Ingresar");
        botonIngresar.setDescription(" ");
        botonIngresar.setName(""); // NOI18N
        botonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIngresarActionPerformed(evt);
            }
        });
        jPanel1.add(botonIngresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 150, -1));

        BusyGif.setText("Ingresando");
        jPanel1.add(BusyGif, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, -1, -1));

        jTabbedPane1.addTab("Inicio de secion", jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Registro de usuarion"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Usuario");
        jLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 100, 25));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Contraseña");
        jLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel4.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 25));

        txtNuevoUsuario.setDescripcion("Nuevo usuario");
        jPanel4.add(txtNuevoUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 30, 200, 25));

        txtNuevaContraseña.setDescripcion("Nueva contraseña");
        jPanel4.add(txtNuevaContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 200, 25));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Confirmar contraseña");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 110, 25));

        txtConfirmContra.setDescripcion("Confirme su contraseña");
        txtConfirmContra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfirmContraKeyPressed(evt);
            }
        });
        jPanel4.add(txtConfirmContra, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 130, 200, 25));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 360, 170));

        botonRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/icononuevouser.png"))); // NOI18N
        botonRegistrar.setText("Registrar");
        botonRegistrar.setDescription(" ");
        botonRegistrar.setName(""); // NOI18N
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });
        jPanel2.add(botonRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 140, -1));

        labelInform.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInform.setText("empleado, para obtener un mayor permiso");
        jPanel2.add(labelInform, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 230, 250, 20));

        labelInform1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInform1.setText("informele a un administrador");
        jPanel2.add(labelInform1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 230, 20));

        labelInform2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelInform2.setText("Este usuario se crea con permisos de");
        jPanel2.add(labelInform2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 230, 20));

        jTabbedPane1.addTab("Registro de usuarios", jPanel2);

        getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 420, 310));

        clockDigital1.setForeground(new java.awt.Color(0, 0, 0));
        getContentPane().add(clockDigital1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 80, -1, -1));

        botonSalirApp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botonSalirApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/salirIcon.png"))); // NOI18N
        botonSalirApp.setText("Salir");
        botonSalirApp.setDescription(" ");
        botonSalirApp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirAppActionPerformed(evt);
            }
        });
        getContentPane().add(botonSalirApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 520, 120, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoManagua.jpg"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 600));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIngresarActionPerformed
        try {
            ingresar();
        } catch (IOException ex) {
            Logger.getLogger(LoginDeInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonIngresarActionPerformed

    private void botonSalirAppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSalirAppActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_botonSalirAppActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        try {
            if (txtNuevaContraseña.getText().equals(txtConfirmContra.getText())) {
                registrar();
            }else{
                JOptionPane.showMessageDialog(this, "Lo sentimos verifique nuevamente su contraseña",
                                "Error", JOptionPane.ERROR_MESSAGE);
                txtConfirmContra.setText("");
                txtConfirmContra.requestFocus();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(LoginDeInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonRegistrarActionPerformed

    private void txtContraseñaLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaLoginKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            try {
                ingresar();
            } catch (IOException ex) {
                Logger.getLogger(LoginDeInicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtContraseñaLoginKeyPressed

    private void txtConfirmContraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmContraKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtConfirmContraKeyPressed

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
            java.util.logging.Logger.getLogger(LoginDeInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginDeInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginDeInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginDeInicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginDeInicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdesktop.swingx.JXBusyLabel BusyGif;
    private org.edisoncor.gui.button.ButtonTask botonIngresar;
    private org.edisoncor.gui.button.ButtonTask botonRegistrar;
    private org.edisoncor.gui.button.ButtonTask botonSalirApp;
    private org.edisoncor.gui.varios.ClockDigital clockDigital1;
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
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel labelInform;
    private javax.swing.JLabel labelInform1;
    private javax.swing.JLabel labelInform2;
    private org.edisoncor.gui.passwordField.PasswordFieldRectBackground txtConfirmContra;
    private org.edisoncor.gui.passwordField.PasswordFieldRectBackground txtContraseñaLogin;
    private org.edisoncor.gui.textField.TextFieldRectBackground txtNuevaContraseña;
    private org.edisoncor.gui.textField.TextFieldRectBackground txtNuevoUsuario;
    private org.edisoncor.gui.textField.TextFieldRectBackground txtUsuarioLogin;
    // End of variables declaration//GEN-END:variables
}
