package Interfaces;

import Archivos.ArchivoCategoriaProd;
import Archivos.ArchivoCompras;
import Archivos.ArchivoEmpleados;
import Archivos.ArchivoProductos;
import Archivos.ArchivoProveedores;
import Pojos.CategoriaProd;
import Pojos.Compras;
import Pojos.Empleados;
import Pojos.Productos;
import Pojos.Proveedores;
import java.awt.event.ItemEvent;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IaraDenisse
 */
public class GestionarCompras extends javax.swing.JDialog {

    DefaultTableModel modelo;
    
    public void tabla() {
        String encabezados[] = {"id", "Factura", "Producto", "Proveedor", "Empleado", "Fecha", "Cantidad", "Precio", "Total", "Tipo"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, encabezados);
        TablaCompras.setModel(modelo);
    }
    
    private void limpiarTabla() {
        for (int i = 0; i < TablaCompras.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public void cargarCategorias(JComboBox jcombo) {
        try {
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catgProd = cpdao.encontrar();
            for (CategoriaProd cp : catgProd) {
                jcombo.addItem(cp.getNombre().trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
            Logger.getLogger(GestionarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pro;
    }

    public void cargarEmp(JComboBox jcombo) {
        try {
            ArchivoEmpleados edao = new ArchivoEmpleados();
            List<Empleados> emp = edao.encontrar();
            for (Empleados e : emp) {
                if (e.getDepartamentos().getNombre().trim().equals("compras")) {
                    jcombo.addItem(e.getNombre().trim());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public GestionarCompras(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.comboProductos.setEnabled(false);
        cargarCategorias(comboCategoriasProd);
        cargarEmp(comboEmpleados);
        tabla();
        try {
            ArchivoCompras cdao = new ArchivoCompras();
            List<Compras> comp = cdao.encontrar();
            
            agregarTabla(comp);
        } catch (IOException ex) {
            Logger.getLogger(GestionarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void agregarCompras() {
        try {
            ArchivoCompras cdao = new ArchivoCompras();
            ArchivoProductos pdao = new ArchivoProductos();
            ArchivoEmpleados edao = new ArchivoEmpleados();
            List<Compras> compras = cdao.encontrar();
            Compras c = new Compras();

            c.setId(compras.size() + 1);
            Productos p = pdao.buscarNombre(this.comboProductos.getSelectedItem().toString().trim());
            if (comboCategoriasProd.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Por favor seleccione un producto",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
            c.setProductos(p);
            List<Productos> prod = pdao.encontrar();
            for (Productos pp : prod) {
                String produts = pp.getNombre().trim();
                if (this.comboProductos.getSelectedItem().toString().trim().equals(produts)) {
                    c.setProveedores(pp.getProveedores().getRazon_social().trim());
                }
            }
            Empleados e = edao.buscarNombre(this.comboEmpleados.getSelectedItem().toString());
            if (this.comboEmpleados.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Elija un empleado");
                return;
            }
            c.setEmpleados(e);
            //----------------------------------------------------------
            if (this.calendarFecha.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            Date fecha = this.calendarFecha.getDate();
            DateFormat df = DateFormat.getDateInstance();
            String fechaCompra = df.format(fecha);
            //-----------------------------------------------------------
            c.setFechaCompra(fechaCompra);
            txtCantidad.getValue();
            c.setCantidadCompra((double) txtCantidad.getValue());
            c.setPrecioCompra(Double.parseDouble(txtPrecio.getText()));
            if (radioCredito.isSelected()) {
                c.setTipoCompra(radioCredito.getLabel());
            }else if (radioContado.isSelected()) {
                c.setTipoCompra(radioContado.getLabel());
            }
            c.setFactura(txtFactura.getText());
            double total = c.getCantidadCompra() * c.getPrecioCompra();
            c.setTotalCompra(total);
            
            cdao.guardar(c);
            cdao.cerrar();
            
            JOptionPane.showMessageDialog(this, "La compra se registro correctamente",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            
            List<Compras> cargar = new ArrayList<>();
            cargar.add(c);
            
            agregarTabla(cargar);
            
            List<Productos> prods = pdao.encontrar();
            for (Productos pr : prods) {
                String products = pr.getNombre().trim();
                
                if (c.getProductos().getNombre().trim().equals(products)) {
                    pr.setId(pr.getId());
                    pr.setNombre(pr.getNombre());
                    pr.setCategoriaProd(pr.getCategoriaProd());
                    pr.setProveedores(pr.getProveedores());
                    pr.setMarca(pr.getMarca());
                    pr.setPrecio(c.getPrecioCompra());
                    double cantidad = pr.getCantidad() + c.getCantidadCompra();
                    pr.setCantidad(cantidad);
                    pr.setUnidadMedida(pr.getUnidadMedida());
                    
                    pdao.modificar(pr);
                }
            }
            pdao.cerrar();
        } catch (IOException ex) {
            Logger.getLogger(GestionarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void agregarTabla(List<Compras> comp){
        for (Compras c : comp) {
            int id = c.getId();
            String prod = c.getProductos().getNombre().trim();
            String prov = c.getProveedores().trim();
            String emp  = c.getEmpleados().getNombre().trim();
            String fecha = c.getFechaCompra();
            double cant = c.getCantidadCompra();
            double precio = c.getPrecioCompra();
            String tipo = c.getTipoCompra();
            String fact = c.getFactura();
            double total = c.getTotalCompra();
            
            Object datos [] = {id, fact, prod, prov, emp, fecha, cant, precio, total, tipo};
            modelo.addRow(datos);
        }
    }
    
    public void limpiarAgrega(){
        comboCategoriasProd.setSelectedIndex(0);
        comboProductos.setEnabled(false);
        txtCantidad.setValue(1);
        txtPrecio.setText("");
        RadioTIpoDeCompra.clearSelection();
        txtProveedor.setText("");
        txtRuc.setText("");
        txtFactura.setText("");
        comboEmpleados.setSelectedIndex(0);
    } 

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        RadioTIpoDeCompra = new javax.swing.ButtonGroup();
        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jPanel1 = new javax.swing.JPanel();
        btnLimprar = new org.edisoncor.gui.button.ButtonTask();
        jPanel5 = new javax.swing.JPanel();
        comboCategoriasProd = new javax.swing.JComboBox();
        comboProductos = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        labelUnidad = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        radioCredito = new javax.swing.JRadioButton();
        radioContado = new javax.swing.JRadioButton();
        calendarFecha = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JSpinner();
        btnAgregar = new org.edisoncor.gui.button.ButtonTask();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtFactura = new javax.swing.JTextField();
        txtRuc = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        comboEmpleados = new javax.swing.JComboBox();
        btnNuevoProveedor = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCompras = new org.jdesktop.swingx.JXTable();
        botonRegresarProducto = new org.edisoncor.gui.button.ButtonTask();
        botonExportar_a_ExcelProduct = new org.edisoncor.gui.button.ButtonTask();
        botonReporteProductos = new org.edisoncor.gui.button.ButtonTask();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestionar compras");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnLimprar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        btnLimprar.setText("Limpiar");
        btnLimprar.setDescription("Ventana");
        btnLimprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimprarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimprar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 260, 176, -1));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Agregue los datos de la compra"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboCategoriasProd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Categoria del producto ---" }));
        comboCategoriasProd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCategoriasProdItemStateChanged(evt);
            }
        });
        jPanel5.add(comboCategoriasProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 25));

        comboProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione el producto" }));
        comboProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comboProductosMouseClicked(evt);
            }
        });
        jPanel5.add(comboProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 30, 180, 25));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Fecha de compra");
        jPanel5.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 100, 25));
        jPanel5.add(labelUnidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 65, 25));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Precio   C$");
        jPanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 100, 25));
        jPanel5.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 75, 25));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Tipo de compra");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 100, 25));

        RadioTIpoDeCompra.add(radioCredito);
        radioCredito.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        radioCredito.setText("Credito");
        jPanel5.add(radioCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 200, 75, 25));

        RadioTIpoDeCompra.add(radioContado);
        radioContado.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        radioContado.setText("Contado");
        jPanel5.add(radioContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 75, 25));
        jPanel5.add(calendarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 90, 140, 25));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Cantidad");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 100, 25));

        txtCantidad.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        jPanel5.add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 120, 90, 25));

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 240));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/agregar compra.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setDescription("a la factura");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 176, -1));

        jcMousePanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 450, 330));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del proveedor"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Factura");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 100, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Numero Ruc");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 100, 25));
        jPanel2.add(txtFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 150, 25));

        txtRuc.setEditable(false);
        jPanel2.add(txtRuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 80, 150, 25));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Empleado que realizo la compra"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboEmpleados.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione al empleado" }));
        jPanel4.add(comboEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 220, -1));

        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 310, 80));

        btnNuevoProveedor.setText("Si el producto es nuevo, presione aqui");
        btnNuevoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoProveedorActionPerformed(evt);
            }
        });
        jPanel2.add(btnNuevoProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, 290, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Proveedor");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 25));

        txtProveedor.setEditable(false);
        jPanel2.add(txtProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 150, 25));

        jcMousePanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(473, 12, 330, 330));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle de la compra"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setViewportView(TablaCompras);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 770, 190));

        jcMousePanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 348, 791, 230));

        botonRegresarProducto.setForeground(new java.awt.Color(255, 255, 255));
        botonRegresarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/regresar.png"))); // NOI18N
        botonRegresarProducto.setText("Regresar");
        botonRegresarProducto.setDescription(" ");
        botonRegresarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegresarProductoActionPerformed(evt);
            }
        });
        jcMousePanel1.add(botonRegresarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 590, 200, -1));

        botonExportar_a_ExcelProduct.setForeground(new java.awt.Color(255, 255, 255));
        botonExportar_a_ExcelProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/excel.png"))); // NOI18N
        botonExportar_a_ExcelProduct.setText("Exportar");
        botonExportar_a_ExcelProduct.setDescription("a Miscrosoft Excel");
        jcMousePanel1.add(botonExportar_a_ExcelProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 590, 200, -1));

        botonReporteProductos.setForeground(new java.awt.Color(255, 255, 255));
        botonReporteProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/reportes.png"))); // NOI18N
        botonReporteProductos.setText("Reporte");
        botonReporteProductos.setDescription("de productos");
        jcMousePanel1.add(botonReporteProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 590, 200, -1));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 820, 690));

        setSize(new java.awt.Dimension(835, 697));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboCategoriasProdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCategoriasProdItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.comboProductos.setEnabled(true);
            if (this.comboCategoriasProd.getSelectedIndex() > 0) {
                this.comboProductos.setModel(new DefaultComboBoxModel(getCategorias(this.comboCategoriasProd.getSelectedItem().toString())));
            }
        }
    }//GEN-LAST:event_comboCategoriasProdItemStateChanged

    private void comboProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comboProductosMouseClicked
        try {
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();

            String product = this.comboProductos.getSelectedItem().toString();
            for (Productos p : prod) {
                String prodt = p.getNombre().trim();
                if (product.equals(prodt)) {
                    if (p.getUnidadMedida().equals("unidad")) {
                        this.labelUnidad.setText(p.getUnidadMedida().trim() + "es");
                    } else if (p.getUnidadMedida().equals("libras")) {
                        this.labelUnidad.setText(p.getUnidadMedida().trim());
                    }
                    txtProveedor.setText(p.getProveedores().getRazon_social().trim());
                    txtRuc.setText(p.getProveedores().getRuc().trim());
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(GestionarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboProductosMouseClicked

    private void botonRegresarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarProductoActionPerformed
        this.dispose();
    }//GEN-LAST:event_botonRegresarProductoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        if (txtPrecio.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el precio del producto",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (!radioCredito.isSelected() && !radioContado.isSelected()) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione el tipo de la compra",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (txtFactura.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese el numero de factura que le proporciono su proveedor",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        agregarCompras();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnLimprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimprarActionPerformed
        limpiarAgrega();
    }//GEN-LAST:event_btnLimprarActionPerformed

    private void btnNuevoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoProveedorActionPerformed
        OpcionesDeProductos dialog = new OpcionesDeProductos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnNuevoProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(GestionarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GestionarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GestionarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GestionarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GestionarCompras dialog = new GestionarCompras(new javax.swing.JFrame(), true);
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
    private javax.swing.ButtonGroup RadioTIpoDeCompra;
    private org.jdesktop.swingx.JXTable TablaCompras;
    private org.edisoncor.gui.button.ButtonTask botonExportar_a_ExcelProduct;
    private org.edisoncor.gui.button.ButtonTask botonRegresarProducto;
    private org.edisoncor.gui.button.ButtonTask botonReporteProductos;
    private org.edisoncor.gui.button.ButtonTask btnAgregar;
    private org.edisoncor.gui.button.ButtonTask btnLimprar;
    private javax.swing.JButton btnNuevoProveedor;
    private com.toedter.calendar.JDateChooser calendarFecha;
    private javax.swing.JComboBox comboCategoriasProd;
    private javax.swing.JComboBox comboEmpleados;
    private javax.swing.JComboBox comboProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JLabel labelUnidad;
    private javax.swing.JRadioButton radioContado;
    private javax.swing.JRadioButton radioCredito;
    private javax.swing.JSpinner txtCantidad;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtProveedor;
    private javax.swing.JTextField txtRuc;
    // End of variables declaration//GEN-END:variables
}
