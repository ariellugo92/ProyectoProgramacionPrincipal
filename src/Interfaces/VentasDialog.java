/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoCategoriaProd;
import Archivos.ArchivoClientes;
import Archivos.ArchivoProductos;
import Archivos.ArchivoVentaDetalle;
import Archivos.ArchivoVentas;
import Pojos.CategoriaProd;
import Pojos.Cliente;
import Pojos.Productos;
import Pojos.Ventas;
import Pojos.detalleVenta;
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
public class VentasDialog extends javax.swing.JDialog {

    DefaultTableModel modelo;
    Ventas v;

    public void cargarCategorias(JComboBox jcombo) {
        try {
            ArchivoCategoriaProd pdao = new ArchivoCategoriaProd();
            List<CategoriaProd> catgProd = pdao.encontrar();
            for (CategoriaProd cp : catgProd) {
                jcombo.addItem(cp.getNombre().trim());
            }
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public VentasDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        try {
            ArchivoVentas vdao = new ArchivoVentas();
            txtClienteVenta.setText((vdao.encontrar().size() + 1) + "");
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        cargarCategorias(this.comboCategortiaProd);
        this.comboProducto.setEnabled(false);
        spinnerCantidad.setEnabled(false);
        txtPrecio.setEnabled(false);
        txtDescuento.setEnabled(false);
        btnEliminarProd.setVisible(false);
        tabla();
    }

    public void tabla() {
        String[] cabecera = {"Producto", "Cantidad", "Precio", "Descuento", "Total"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, cabecera);
        TablaVentas.setModel(modelo);
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

    public void agregarFactura() throws IOException {
        ArchivoProductos pdao = new ArchivoProductos();
        List<Productos> prod = pdao.encontrar();

        //------- Agregar producto a la tabla -------------------
        String producto = this.comboProducto.getSelectedItem().toString();
        double cant = (double) spinnerCantidad.getValue();
        double precio = Double.parseDouble(txtPrecio.getText());
        double desc;
        if (txtDescuento.getText().isEmpty()) {
            desc = 0.0;
        } else {
            desc = Double.parseDouble(txtDescuento.getText());
        }
        double total = (precio * cant) - desc;

        //----- VALIDAR CANTIDAD Y PRECIO ------------------------
        String prodVender = this.comboProducto.getSelectedItem().toString().trim();
        for (Productos p : prod) {
            String products = p.getNombre().trim();
            if (prodVender.equals(products)) {
                if (cant > p.getCantidad()) {
                    JOptionPane.showMessageDialog(this, "Lo sentimos solo hay disponible " + p.getCantidad() + " de este producto",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (precio < p.getPrecio()) {
                    JOptionPane.showMessageDialog(this, "No se puede facturar a un precio menor que el de inventario",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //---- Actualizar el producto ----------------
                p.setId(p.getId());
                p.setNombre(p.getNombre());
                p.setCategoriaProd(p.getCategoriaProd());
                p.setProveedores(p.getProveedores());
                p.setMarca(p.getMarca());
                p.setPrecio(p.getPrecio());
                double cantidad = p.getCantidad() - cant;
                p.setCantidad(cantidad);
                p.setUnidadMedida(p.getUnidadMedida());

//                pdao.modificar(p);
                //--------------------------------------------
            }
        }
        pdao.cerrar();
        //--------------------------------------------------------

        Object datos[] = {producto, cant, precio, desc, total};
        modelo.addRow(datos);
        //---------------------------------------------------------

        //----------- Pasar el subtotal al textfield -----------------
        DefaultTableModel mod = (DefaultTableModel) this.TablaVentas.getModel();
        double cont = 0, celdaTotal = 0;
        for (int i = 0; i < this.TablaVentas.getRowCount(); i++) {
            celdaTotal = (double) mod.getValueAt(i, 4);
            cont += celdaTotal;
        }

        String textTotal = Double.toString(cont);
        txtTotalFact.setText(textTotal);
        //---------------------------------------------------------

        //----------- Pasar la info a los label -------------------
        labelSubTotal.setText(textTotal);
        double cont2 = 0, descnt = 0;
        for (int i = 0; i < TablaVentas.getRowCount(); i++) {
            descnt = (double) mod.getValueAt(i, 3);
            cont2 += descnt;
        }
        String totalDesct = Double.toString(cont2);
        labelDescuento.setText(totalDesct);
        double iva = 0;
        if (radioIVASI.isSelected()) {
            iva = 0.0;
        } else if (radioIVANO.isSelected()) {
            iva = cont * 0.15;
        }
        String textIVA = Double.toString(iva);
        labelIVA.setText(textIVA);
        double totalNeto = cont + iva;
        String text_TotalNeto = Double.toString(totalNeto);
        labelTotal.setText(text_TotalNeto);
        //--------------------------------------------------------
    }

    public void agregarVenta() {
        try {
            ArchivoVentas vdao = new ArchivoVentas();
            ArchivoClientes cdao = new ArchivoClientes();
            List<Ventas> vts = vdao.encontrar();
            Ventas v = new Ventas();

            v.setId(vts.size() + 1);
            if (calendarFecha.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date fecha = calendarFecha.getDate();
            DateFormat df = DateFormat.getDateInstance();
            String f = df.format(fecha);
            v.setFechaVenta(f);
            if (radioCredito.isSelected()) {
                v.setTipoVenta(radioCredito.getLabel());
            } else if (radioContado.isSelected()) {
                v.setTipoVenta(radioContado.getLabel());
            }
            if (radioIVASI.isSelected()) {
                v.setTipoVenta(radioIVASI.getLabel());
            } else if (radioIVANO.isSelected()) {
                v.setTipoVenta(radioIVANO.getLabel());
            }
            Cliente c = cdao.buscarNombre(txtCliente.getText());
            v.setCliente(c);

            vdao.guardar(v);
            vdao.cerrar();
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoTipoPersona = new javax.swing.ButtonGroup();
        grupoTipoFactura = new javax.swing.ButtonGroup();
        grupoIVA = new javax.swing.ButtonGroup();
        jcMousePanel1 = new jcMousePanel.jcMousePanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTelfCliente = new javax.swing.JTextField();
        radioNormal = new javax.swing.JRadioButton();
        radioJuridiico = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        calendarFecha = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        radioCredito = new javax.swing.JRadioButton();
        radioContado = new javax.swing.JRadioButton();
        txtFactura = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        radioIVASI = new javax.swing.JRadioButton();
        radioIVANO = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        comboCategortiaProd = new javax.swing.JComboBox();
        comboProducto = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        spinnerCantidad = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtDescuento = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtClienteVenta = new javax.swing.JTextField();
        btnLimpiar = new javax.swing.JButton();
        btnRegistrarFact = new javax.swing.JButton();
        btnAgregarFact = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVentas = new org.jdesktop.swingx.JXTable();
        jLabel11 = new javax.swing.JLabel();
        txtTotalFact = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelSubTotal = new javax.swing.JLabel();
        labelDescuento = new javax.swing.JLabel();
        labelIVA = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        btnGenerarFact = new javax.swing.JButton();
        btnEliminarProd = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jcMousePanel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Fondos/fondoPrincipal.jpeg"))); // NOI18N
        jcMousePanel1.setVisibleLogo(false);
        jcMousePanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del cliente"));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Nombre o razon social");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 130, 25));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Tipo");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 130, 25));
        jPanel2.add(txtCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 200, 25));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Telefono");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 130, 25));
        jPanel2.add(txtTelfCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 200, 25));

        grupoTipoPersona.add(radioNormal);
        radioNormal.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        radioNormal.setText("Natural");
        jPanel2.add(radioNormal, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 100, -1, -1));

        grupoTipoPersona.add(radioJuridiico);
        radioJuridiico.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        radioJuridiico.setText("Juridica");
        jPanel2.add(radioJuridiico, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 370, 160));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Factura"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Fecha");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 100, 25));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Factura");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 100, 25));
        jPanel3.add(calendarFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 150, 25));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Tipo de venta");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 100, 25));

        grupoTipoFactura.add(radioCredito);
        radioCredito.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        radioCredito.setText("Credito");
        jPanel3.add(radioCredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        grupoTipoFactura.add(radioContado);
        radioContado.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        radioContado.setText("Contado");
        jPanel3.add(radioContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 90, -1, -1));
        jPanel3.add(txtFactura, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 150, 25));

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("Exento de IVA");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 75, 25));

        grupoIVA.add(radioIVASI);
        radioIVASI.setText("SI");
        jPanel3.add(radioIVASI, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, -1, -1));

        grupoIVA.add(radioIVANO);
        radioIVANO.setText("NO");
        jPanel3.add(radioIVANO, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 330, 160));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos del producto"));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboCategortiaProd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione la categoria del prod" }));
        comboCategortiaProd.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboCategortiaProdItemStateChanged(evt);
            }
        });
        jPanel4.add(comboCategortiaProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 200, 25));

        comboProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "--- Seleccione el prioducto ---" }));
        jPanel4.add(comboProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 200, 25));

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Precio");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 75, 25));

        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(Double.valueOf(1.0d), Double.valueOf(1.0d), null, Double.valueOf(1.0d)));
        jPanel4.add(spinnerCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 100, 25));

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel8.setText("Cantidad");
        jPanel4.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 75, 25));
        jPanel4.add(txtPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 80, 25));
        jPanel4.add(txtDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 80, 25));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Descuento");
        jPanel4.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 75, 25));

        txtClienteVenta.setText("jTextField1");
        jPanel4.add(txtClienteVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 110, -1, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 470, 170));

        btnLimpiar.setText("Limprar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        jPanel1.add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 310, 130, -1));

        btnRegistrarFact.setText("Registrar factura");
        btnRegistrarFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarFactActionPerformed(evt);
            }
        });
        jPanel1.add(btnRegistrarFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 260, 130, -1));

        btnAgregarFact.setText("Agregar Factura");
        btnAgregarFact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFactActionPerformed(evt);
            }
        });
        jPanel1.add(btnAgregarFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 215, 120, -1));

        jcMousePanel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 730, 370));

        TablaVentas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaVentasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaVentas);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 460, 170));

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Total");
        jcMousePanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, 75, 25));
        jcMousePanel1.add(txtTotalFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 570, 80, 25));

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("IVA");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 90, 75, 25));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("Total a pagar");
        jPanel5.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 75, 25));

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Sub total");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 75, 25));

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel15.setText("Descuento");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 60, 75, 25));

        labelSubTotal.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelSubTotal.setText("0.0");
        jPanel5.add(labelSubTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 75, 25));

        labelDescuento.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelDescuento.setForeground(new java.awt.Color(255, 0, 0));
        labelDescuento.setText("0.0");
        jPanel5.add(labelDescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, 75, 25));

        labelIVA.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        labelIVA.setText("0.0");
        jPanel5.add(labelIVA, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, 75, 25));

        labelTotal.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        labelTotal.setText("0.0");
        jPanel5.add(labelTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 75, 25));

        btnGenerarFact.setText("Generar Factura");
        jPanel5.add(btnGenerarFact, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, -1, -1));

        jcMousePanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, 260, 210));

        btnEliminarProd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/exitUser2.png"))); // NOI18N
        btnEliminarProd.setText(" Eliminar producto");
        btnEliminarProd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarProdActionPerformed(evt);
            }
        });
        jcMousePanel1.add(btnEliminarProd, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 570, -1, -1));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 640));

        setSize(new java.awt.Dimension(767, 655));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void comboCategortiaProdItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboCategortiaProdItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            this.comboProducto.setEnabled(true);
            spinnerCantidad.setEnabled(true);
            txtPrecio.setEnabled(true);
            txtDescuento.setEnabled(true);
            if (this.comboCategortiaProd.getSelectedIndex() > 0) {
                this.comboProducto.setModel(new DefaultComboBoxModel(getCategorias(this.comboCategortiaProd.getSelectedItem().toString())));
            }
        }
    }//GEN-LAST:event_comboCategortiaProdItemStateChanged

    private void btnAgregarFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFactActionPerformed
//        try {
//            if (!radioIVANO.isSelected() && !radioIVASI.isSelected()) {
//                JOptionPane.showMessageDialog(this, "Seleccione si la factura llevara IVA");
//                return;
//            }
//            agregarFactura();
//        } catch (IOException ex) {
//            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
//        }

        try {

//            ArchivoClientes ac = new ArchivoClientes();
//            Cliente cliente = null;
//            Ventas v = new Ventas();
//            ArchivoVentas venta = new ArchivoVentas();
//            detalleVenta dv = new detalleVenta();
//            ArchivoVentaDetalle av = new ArchivoVentaDetalle();
//            ArchivoProductos pdao = new ArchivoProductos();
//            List<Cliente> clientes = ac.encontrar();
//            
//            for (Cliente c:clientes) {
//                if(c.getNombre().trim().equals(txtCliente.getText().trim())){
//                    cliente = ac.buscarNombre(c.getNombre().trim());
//                }
//            }
//            
//            //----------------------------------------------------------
//            v.setId(venta.encontrar().size() + 1);
//            v.setCliente(cliente);
//            if (calendarFecha.getDate() == null) {
//                JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error",
//                        JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//            Date fecha = calendarFecha.getDate();
//            DateFormat df = DateFormat.getDateInstance();
//            String f = df.format(fecha);
//            v.setFechaVenta(f);
//
//            if (radioCredito.isSelected()) {
//                v.setTipoVenta(radioCredito.getLabel());
//            } else if (radioContado.isSelected()) {
//                v.setTipoVenta(radioContado.getLabel());
//            }
//            if (radioIVASI.isSelected()) {
//                v.setEstadoIVA(radioIVASI.getLabel());
//            } else if (radioIVANO.isSelected()) {
//                v.setEstadoIVA(radioIVANO.getLabel());
//            }
//
//            venta.guardar(v);
//
//            ///--------------------------------------------
//            dv.setId(av.encontrar().size() + 1);
//            dv.setVentas(v);
//            dv.setCantidad(Double.parseDouble(spinnerCantidad.getValue().toString()));
//            dv.setDescuento(Double.parseDouble(txtDescuento.getText()));
//            Productos p = pdao.buscarNombre(this.comboProducto.getSelectedItem().toString().trim());
//            dv.setProductos(p);
//            dv.setPrecio(Double.parseDouble(txtPrecio.getText()));
//
//            av.guardar(dv);
            ArchivoVentaDetalle vddao = new ArchivoVentaDetalle();
            ArchivoVentas vdao = new ArchivoVentas();
            ArchivoProductos pdao = new ArchivoProductos();
            List<detalleVenta> detv = vddao.encontrar();
            List<Ventas> vts = vdao.encontrar();
            detalleVenta dv = new detalleVenta();
            
            dv.setId(vts.size() + 1);
            Productos p = pdao.buscarNombre(this.comboProducto.getSelectedItem().toString().trim());
            dv.setProductos(p);
            dv.setPrecio(Double.parseDouble(txtPrecio.getText()));
            dv.setCantidad(Double.parseDouble(spinnerCantidad.getValue().toString()));
            dv.setDescuento(Double.parseDouble(txtDescuento.getText()));
            
            vddao.guardar(dv);
            vddao.cerrar();

            String producto = this.comboProducto.getSelectedItem().toString();
            double cant = (double) spinnerCantidad.getValue();
            double precio = Double.parseDouble(txtPrecio.getText());
            double desc;
            if (txtDescuento.getText().isEmpty()) {
                desc = 0.0;
            } else {
                desc = Double.parseDouble(txtDescuento.getText());
            }
            double total = (precio * cant) - desc;

            Object datos[] = {producto, cant, precio, desc, total};
            modelo.addRow(datos);//ya cn eso agrega a la tabla.
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

            

    }//GEN-LAST:event_btnAgregarFactActionPerformed

    private void TablaVentasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaVentasMouseClicked
        if (this.TablaVentas.getRowCount() == 0) {
            return;
        }
        btnEliminarProd.setVisible(true);
    }//GEN-LAST:event_TablaVentasMouseClicked

    private void btnEliminarProdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarProdActionPerformed
        try {
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();

            int sel = this.TablaVentas.getSelectedRow();
            DefaultTableModel mod = (DefaultTableModel) this.TablaVentas.getModel();

            //--- Removiendo el prod seleccionado --------------
            mod.removeRow(this.TablaVentas.getSelectedRow());

            //--- Actualizando el prod seleccionado ------------
            String seleccion = mod.getValueAt(sel, 0).toString();
            for (Productos p : prod) {
                String products = p.getNombre().trim();
                if (seleccion.equals(products)) {
                    p.setId(p.getId());
                    p.setNombre(p.getNombre());
                    p.setCategoriaProd(p.getCategoriaProd());
                    p.setProveedores(p.getProveedores());
                    p.setMarca(p.getMarca());
                    p.setPrecio(p.getPrecio());
                    double cantTable = Double.parseDouble(mod.getValueAt(sel, 1).toString());
                    double cantidad = p.getCantidad() + cantTable;
                    p.setCantidad(cantidad);
                    p.setUnidadMedida(p.getUnidadMedida());

//                pdao.modificar(p);
                }
            }
            pdao.cerrar();

            btnEliminarProd.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEliminarProdActionPerformed

    private void btnRegistrarFactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarFactActionPerformed
        try {
            ArchivoClientes cdao = new ArchivoClientes();
            List<Cliente> clnt = cdao.encontrar();
            Cliente c = new Cliente();
            
            c.setId(clnt.size() + 1);
            c.setNombre(txtCliente.getText());
            c.setTelefono(txtTelfCliente.getText());
            if (radioJuridiico.isSelected()) {
                c.setTipoCliente(radioJuridiico.getLabel());
            }else if (radioNormal.isSelected()) {
                c.setTipoCliente(radioNormal.getLabel());
            }
            
            cdao.guardar(c);
            cdao.cerrar();
            
            //---------------------------------------------------
            
            ArchivoVentas vdao = new ArchivoVentas();
            ArchivoVentaDetalle vddao = new ArchivoVentaDetalle();
            List<Ventas> vts = vdao.encontrar();
            v = new Ventas();
            
            v.setId(vts.size() + 1);
            v.setCliente(c);
            int idvd = Integer.parseInt(txtClienteVenta.getText());
            detalleVenta dv = vddao.buscarId(idvd);
            v.setDventa(dv);
            if (calendarFecha.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Seleccione una fecha", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
            Date fecha = calendarFecha.getDate();
            DateFormat df = DateFormat.getDateInstance();
            String f = df.format(fecha);
            v.setFechaVenta(f);

            if (radioCredito.isSelected()) {
                v.setTipoVenta(radioCredito.getLabel());
            } else if (radioContado.isSelected()) {
                v.setTipoVenta(radioContado.getLabel());
            }
            if (radioIVASI.isSelected()) {
                v.setEstadoIVA(radioIVASI.getLabel());
            } else if (radioIVANO.isSelected()) {
                v.setEstadoIVA(radioIVANO.getLabel());
            }
            
            vdao.guardar(v);
            vdao.cerrar();
            
            JOptionPane.showMessageDialog(this, "Su venta se registro correctamete");
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRegistrarFactActionPerformed

    public void limpiarTabla() {
        for (int i = 0; i < this.TablaVentas.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }
    
    public void limpiarTodo(){
        txtCliente.setText("");
        txtTelfCliente.setText("");
        grupoTipoPersona.clearSelection();
        txtFactura.setText("");
        grupoTipoFactura.clearSelection();
        grupoIVA.clearSelection();
        spinnerCantidad.setValue(1);
        txtPrecio.setText("");
        txtDescuento.setText("");
        try {
            ArchivoVentas vdao = new ArchivoVentas();
            txtClienteVenta.setText((vdao.encontrar().size()+1) + "");
        } catch (IOException ex) {
            Logger.getLogger(VentasDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiarTabla();
    }
    
    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarTodo();
    }//GEN-LAST:event_btnLimpiarActionPerformed

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
            java.util.logging.Logger.getLogger(VentasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentasDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                VentasDialog dialog = new VentasDialog(new javax.swing.JFrame(), true);
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
    private org.jdesktop.swingx.JXTable TablaVentas;
    private javax.swing.JButton btnAgregarFact;
    private javax.swing.JButton btnEliminarProd;
    private javax.swing.JButton btnGenerarFact;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrarFact;
    private com.toedter.calendar.JDateChooser calendarFecha;
    private javax.swing.JComboBox comboCategortiaProd;
    private javax.swing.JComboBox comboProducto;
    private javax.swing.ButtonGroup grupoIVA;
    private javax.swing.ButtonGroup grupoTipoFactura;
    private javax.swing.ButtonGroup grupoTipoPersona;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
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
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private javax.swing.JLabel labelDescuento;
    private javax.swing.JLabel labelIVA;
    private javax.swing.JLabel labelSubTotal;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JRadioButton radioContado;
    private javax.swing.JRadioButton radioCredito;
    private javax.swing.JRadioButton radioIVANO;
    private javax.swing.JRadioButton radioIVASI;
    private javax.swing.JRadioButton radioJuridiico;
    private javax.swing.JRadioButton radioNormal;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtClienteVenta;
    private javax.swing.JTextField txtDescuento;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtPrecio;
    private javax.swing.JTextField txtTelfCliente;
    private javax.swing.JTextField txtTotalFact;
    // End of variables declaration//GEN-END:variables
}
