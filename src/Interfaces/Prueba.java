/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoCategoriaProd;
import Archivos.ArchivoProductos;
import Archivos.ArchivoVentaDetalle;
import Archivos.ArchivoVentas;
import Pojos.CategoriaProd;
import Pojos.Productos;
import Pojos.Ventas;
import Pojos.detalleVenta;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IaraDenisse
 */
public class Prueba extends javax.swing.JDialog {

    DefaultTableModel modelo;

    public void cargar(JComboBox jcombo) {
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catgP = cpdao.encontrar();

            for (CategoriaProd cp : catgP) {
                jcombo.addItem(cp.getNombre().trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Prueba(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        cargar(comboCategoriaProd);
        tabla();

    }

    public void tabla() {
        String[] cabecera = {"Producto", "Cantidad", "Precio", "Descuento", "Cliente"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, cabecera);
        TablaVentaProd.setModel(modelo);
    }

//    public void fecha() throws ParseException{
//    
//        if (Jcalendar.getDate() == null) {
//            JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error", 
//                    JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        
//        Date fecha = Jcalendar.getDate();
//        DateFormat df = DateFormat.getDateInstance();
//        String s = df.format(fecha);
//        labelFecha.setText("La fecha elejida es: " + s);
//        
//    }
    public void agregarTabla() {
        try {
            ArchivoVentas vdao = new ArchivoVentas();
            ArchivoProductos pdao = new ArchivoProductos();
            List<Ventas> vts = vdao.encontrar();
            List<Productos> prod = pdao.encontrar();

            Productos p = pdao.buscarNombre(this.comboProductos.getSelectedItem().toString().trim());
            for (int i = 0; i < vts.size(); i++) {
                if (p.getId() == vts.get(i).getDventa().getProductos().getId()) {
                    String products = vts.get(i).getDventa().getProductos().getNombre().trim();
                    double cant = vts.get(i).getDventa().getCantidad();
                    double precio = vts.get(i).getDventa().getPrecio();
                    double desc = vts.get(i).getDventa().getDescuento();
                    String cliente = vts.get(i).getCliente().getNombre().trim();
                    
                    Object [] datos = {products, cant, precio, desc, cliente};
                    modelo.addRow(datos);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//            txtAreaDetalle.append("========================================\n");
//            for (Ventas v : vts) {
//                String produts = v.getDventa().getProductos().getNombre().trim();
//                if (prodelegido.equals(produts)) {
//                    String prod = v.getDventa().getProductos().getNombre().trim();
//                    double cant = v.getDventa().getCantidad();
//                    double precio = v.getDventa().getPrecio();
//                    double desc = v.getDventa().getDescuento();
//                    
//                    String cliente = v.getCliente().getNombre().trim();
//                    
//                    Object [] datos = {prod, cant, precio, desc, cliente};
//                    modelo.addRow(datos);
//                    
//                    String detalle = "Producto: " + prod + "\n"
//                                   + "Cantidad: " + String.valueOf(cant) + "\n" 
//                                   + "Precio: " + String.valueOf(precio) + "\n"
//                                   + "Descuento: " + String.valueOf(desc) + "\n"
//                                   + "Cliente: " + cliente + "\n";
//                    txtAreaDetalle.append(detalle);
//                    txtAreaDetalle.append("========================================\n");
    public String[] getCategorias(String categoria) {
        String[] pro = null;
        try {
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();

            pro = new String[prod.size()];
            int j = 0;

            for (Productos p : prod) {

                String catg = p.getCategoriaProd().getNombre().trim();
                if (categoria.equals(catg)) {
                    String products = p.getNombre().trim();

                    pro[j] = products;
                    j++;
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(GestionarCompras.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToggleButton1 = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVentaProd = new javax.swing.JTable();
        comboProductos = new javax.swing.JComboBox();
        comboCategoriaProd = new javax.swing.JComboBox();
        btnAgregarTabla = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaDetalle = new javax.swing.JTextArea();

        jToggleButton1.setText("jToggleButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TablaVentaProd.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaVentaProd);

        comboCategoriaProd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione categoria" }));
        comboCategoriaProd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCategoriaProdItemStateChanged(evt);
            }
        });

        btnAgregarTabla.setText("Agregar Tabla");
        btnAgregarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarTablaActionPerformed(evt);
            }
        });

        txtAreaDetalle.setColumns(20);
        txtAreaDetalle.setRows(5);
        jScrollPane2.setViewportView(txtAreaDetalle);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(comboCategoriaProd, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAgregarTabla))
                        .addComponent(jScrollPane1)))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboCategoriaProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarTabla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboCategoriaProdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCategoriaProdItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.comboProductos.setEnabled(true);
            if (this.comboCategoriaProd.getSelectedIndex() > 0) {
                this.comboProductos.setModel(new DefaultComboBoxModel(getCategorias(this.comboCategoriaProd.getSelectedItem().toString())));
            }
        }
    }//GEN-LAST:event_comboCategoriaProdItemStateChanged

    private void btnAgregarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarTablaActionPerformed
        agregarTabla();
    }//GEN-LAST:event_btnAgregarTablaActionPerformed

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
            java.util.logging.Logger.getLogger(Prueba.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Prueba dialog = new Prueba(new javax.swing.JFrame(), true);
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
    private javax.swing.JTable TablaVentaProd;
    private javax.swing.JButton btnAgregarTabla;
    private javax.swing.JComboBox comboCategoriaProd;
    private javax.swing.JComboBox comboProductos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JTextArea txtAreaDetalle;
    // End of variables declaration//GEN-END:variables
}
