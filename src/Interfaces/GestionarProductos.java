/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Archivos.ArchivoCategoriaProd;
import Archivos.ArchivoDepartamentos;
import Archivos.ArchivoProductos;
import Pojos.CategoriaProd;
import Pojos.Departamentos;
import Pojos.Productos;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author IaraDenisse
 */
public class GestionarProductos extends javax.swing.JDialog {

    DefaultTableModel modelo;

    public void tabla() {
        String[] cabecera = {"ID", "Nombre", "Categoria", "Cantidad", "Precio", "Marca", "Medida"};
        String datos[][] = {};
        modelo = new DefaultTableModel(datos, cabecera);
        TablaProductos.setModel(modelo);
    }

    public void cargar(JComboBox jcombo) throws IOException {
        ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
        List<CategoriaProd> catg = cpdao.encontrar();

        for (CategoriaProd cp : catg) {
            jcombo.addItem(cp.getNombre().trim());
        }
    }
    
    public void cargarTree(){
        
    }

    public GestionarProductos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        txtNombreProduct.requestFocus();
        botonLimpiarProduct.setVisible(false);
        botonLimpiarModificaProduct.setVisible(false);
        botonModificarProducto.setVisible(false);
        try {
            cargar(this.comboCatgProductos);
            cargar(this.comboCatgProducto_Modificar);
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
        List<Productos> prod = pdao.encontrar();
        Productos p = new Productos();

        p.setId(prod.size() + 1);
        p.setNombre(txtNombreProduct.getText());
        CategoriaProd cp = cpdao.buscarNombre(this.comboCatgProductos.getSelectedItem().toString());
        if (this.comboCatgProductos.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Elija una categoria");
            return;
        }
        p.setCategoriaProd(cp);
        p.setMarca(txtMarcaProduct.getText());
        p.setPrecio(0.0);
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
            String categ = pp.getCategoriaProd().getNombre().trim();
            Double precio = pp.getPrecio();
            String marca = pp.getMarca().trim();
            double cant = pp.getCantidad();
            String unidMed = pp.getUnidadMedida();

            Object datos[] = {id, nombre, categ, cant, precio, marca, unidMed};
            modelo.addRow(datos);
        }
    }

    public void limpiarAgrega() {

        txtIDProduct.setText("");
        txtNombreProduct.setText("");
        comboCatgProductos.setSelectedIndex(0);
        txtMarcaProduct.setText("");
        txtUnidadMedidaProduct.setText("");
    }

    public void limpiarModifica() {

        txtIDProduct_Modificar.setText("");
        txtNombreProduct_Modificar.setText("");
        comboCatgProducto_Modificar.setSelectedIndex(0);
        txtMarcaProduct_Modificar.setText("");
        txtUnidadMedidaProduct_Modificar.setText("");
    }

    private void limpiarTabla() {
        for (int i = 0; i < TablaProductos.getRowCount(); i++) {
            modelo.removeRow(i);
            i -= 1;
        }
    }

    public void modificarProd() {

        try {
            ArchivoProductos pdao = new ArchivoProductos();
            ArchivoCategoriaProd cpdao = new ArchivoCategoriaProd();
            List<Productos> prod = pdao.encontrar();

            int sel = this.TablaProductos.getSelectedRow();
            DefaultTableModel mod = (DefaultTableModel) this.TablaProductos.getModel();
            String nombre = mod.getValueAt(sel, 1).toString();
            String marca = mod.getValueAt(sel, 4).toString();

            for (Productos p : prod) {
                String producto = p.getNombre().trim();
                String marcas2 = p.getMarca().trim();
                if (nombre.equals(producto) && marca.equals(marcas2)) {
                    p.setId(p.getId());
                    p.setNombre(txtNombreProduct_Modificar.getText());
                    CategoriaProd cp = cpdao.buscarNombre(this.comboCatgProducto_Modificar.getSelectedItem().toString());
                    if (this.comboCatgProducto_Modificar.getSelectedIndex() == 0) {
                        JOptionPane.showMessageDialog(this, "Elija una categoria");
                        return;
                    }
                    p.setCategoriaProd(cp);
                    p.setMarca(txtMarcaProduct_Modificar.getText());
                    p.setPrecio(p.getPrecio());
                    p.setCantidad(p.getCantidad());
                    p.setUnidadMedida(txtUnidadMedidaProduct_Modificar.getText());

                    pdao.modificar(p);
                    JOptionPane.showMessageDialog(this, "El producto se ha modificado correctamente",
                            "Aviso", JOptionPane.INFORMATION_MESSAGE);
                }
            }

            pdao.cerrar();
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void buscarTabla() throws IOException {

        ArchivoProductos pdao = new ArchivoProductos();
        List<Productos> prod = pdao.encontrar();

        String buscando = txtBuscarTabla.getText();
        boolean flag = false;

        for (Productos p : prod) {
            String encontrado = p.getNombre().trim();

            if (buscando.equalsIgnoreCase(encontrado)) {
                List<Productos> cargar = new ArrayList<>();
                cargar.add(p);

                limpiarTabla();
                agregarTabla(cargar);
                flag = false;
                break;
            }else{
                flag = true;
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(this, "El producta que busca no existe o lo ingreso incorrectamente");
        }
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
        txtIDProduct = new javax.swing.JTextField();
        botonAgregarProducto = new org.edisoncor.gui.button.ButtonTask();
        botonLimpiarProduct = new org.edisoncor.gui.button.ButtonTask();
        jLabel14 = new javax.swing.JLabel();
        txtUnidadMedidaProduct = new javax.swing.JTextField();
        comboCatgProductos = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        treeProductos = new javax.swing.JTree();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtMarcaProduct_Modificar = new javax.swing.JTextField();
        txtNombreProduct_Modificar = new javax.swing.JTextField();
        txtIDProduct_Modificar = new javax.swing.JTextField();
        botonModificarProducto = new org.edisoncor.gui.button.ButtonTask();
        botonLimpiarModificaProduct = new org.edisoncor.gui.button.ButtonTask();
        jLabel24 = new javax.swing.JLabel();
        txtUnidadMedidaProduct_Modificar = new javax.swing.JTextField();
        comboCatgProducto_Modificar = new javax.swing.JComboBox();
        labelTask1 = new org.edisoncor.gui.label.LabelTask();
        botonBorrar = new org.edisoncor.gui.button.ButtonTask();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProductos = new org.jdesktop.swingx.JXTable();
        botonReporteProductos = new org.edisoncor.gui.button.ButtonTask();
        botonExportar_a_ExcelProduct = new org.edisoncor.gui.button.ButtonTask();
        botonRegresarProducto = new org.edisoncor.gui.button.ButtonTask();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscarTabla = new javax.swing.JTextField();
        btnBuscarTabla = new javax.swing.JButton();
        btnRestaurarTabla = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gestionar Productos");
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

        comboCatgProductos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---- Seleccione la categoria ----" }));
        jPanel4.add(comboCatgProductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, 25));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 550, 230));

        jScrollPane3.setViewportView(treeProductos);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 20, 190, 220));

        jTabbedPane1.addTab("Agregar Productos", jPanel1);

        jPanel2.setAutoscrolls(true);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Edite los campos a modificar"));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel20.setText("Nombre");
        jPanel7.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 115, 25));

        jLabel21.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel21.setText("Categoria");
        jPanel7.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 115, 25));

        jLabel22.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel22.setText("Marca del producto");
        jPanel7.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 115, 25));

        jLabel23.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel23.setText("ID");
        jPanel7.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 115, 25));
        jPanel7.add(txtMarcaProduct_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 200, 25));
        jPanel7.add(txtNombreProduct_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 200, 25));

        txtIDProduct_Modificar.setEditable(false);
        jPanel7.add(txtIDProduct_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 40, 25));

        botonModificarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/editProd.png"))); // NOI18N
        botonModificarProducto.setText("Modificar");
        botonModificarProducto.setDescription("Productos");
        botonModificarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarProductoActionPerformed(evt);
            }
        });
        jPanel7.add(botonModificarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 160, -1));

        botonLimpiarModificaProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/limpiarBoton.png"))); // NOI18N
        botonLimpiarModificaProduct.setText("Limpiar");
        botonLimpiarModificaProduct.setDescription("Ventanas");
        botonLimpiarModificaProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarModificaProductActionPerformed(evt);
            }
        });
        jPanel7.add(botonLimpiarModificaProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 120, 160, -1));

        jLabel24.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel24.setText("Unidad de medida");
        jPanel7.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 115, 25));
        jPanel7.add(txtUnidadMedidaProduct_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 200, 25));

        comboCatgProducto_Modificar.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---- Seleccione una categoria ----" }));
        jPanel7.add(comboCatgProducto_Modificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 110, 200, 25));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 550, 230));

        labelTask1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/updateProd.png"))); // NOI18N
        labelTask1.setText("Modificar Productos");
        labelTask1.setDescription("Elija en la tabla el producto que desea modificar");
        jPanel6.add(labelTask1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 590, -1));

        jScrollPane2.setViewportView(jPanel6);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 740, 230));

        jTabbedPane1.addTab("Modificar Productos", jPanel2);

        botonBorrar.setForeground(new java.awt.Color(255, 255, 255));
        botonBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/borrar.png"))); // NOI18N
        botonBorrar.setText("Borrar");
        botonBorrar.setDescription("Departamentos");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });
        jTabbedPane1.addTab("tab3", botonBorrar);

        jcMousePanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 770, 280));

        TablaProductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProductosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProductos);

        jcMousePanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 380, 510, 270));

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Buscar"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel1.setText("Nombre del producto");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 25));
        jPanel3.add(txtBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 200, 25));

        btnBuscarTabla.setText("Buscar");
        btnBuscarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarTablaActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        btnRestaurarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Iconos/atras.png"))); // NOI18N
        btnRestaurarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarTablaActionPerformed(evt);
            }
        });
        jPanel3.add(btnRestaurarTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 33, 25));

        jcMousePanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 310, 510, 60));

        getContentPane().add(jcMousePanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 790, 660));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegresarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegresarProductoActionPerformed
        this.dispose();
        OpcionesDeProductos dialog = new OpcionesDeProductos(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
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

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed

        try {

            ArchivoDepartamentos ddao = new ArchivoDepartamentos();
            List<Departamentos> dpto = ddao.encontrar();

            File f = new File("departamentos.dat");

            f.delete();

            //            int i=0;//buscar el indice
            //
            //            for (Departamentos d:dpto) {
            //
            //                if(d.getId() == Integer.parseInt(txtIdBorrar.getText().trim())){   //ver los ids
            //                    dpto.remove(i); //remover el q este en el indice i
            //                    break;//salir dl bucle
            //                }
            //
            //                i++;//incremetar
            //            }
            //
            //            for (Departamentos d:dpto) {
            //                ddao.guardar(d);     //guardar la nueva lista con los objetos
            //            }
            //
            //actualizar
            JOptionPane.showMessageDialog(this, "Borrado departamento", "BORRADO", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void botonModificarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarProductoActionPerformed
        try {
            modificarProd();
            limpiarTabla();
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();
            agregarTabla(prod);
            botonLimpiarModificaProduct.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonModificarProductoActionPerformed

    private void botonLimpiarModificaProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarModificaProductActionPerformed
        limpiarModifica();
    }//GEN-LAST:event_botonLimpiarModificaProductActionPerformed

    private void TablaProductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProductosMouseClicked
        if (this.TablaProductos.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "Esta tabla no contiene ningun producto",
                    "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int sel = this.TablaProductos.getSelectedRow();
        DefaultTableModel mod = (DefaultTableModel) this.TablaProductos.getModel();

        txtIDProduct_Modificar.setText(mod.getValueAt(sel, 0).toString());
        txtNombreProduct_Modificar.setText(mod.getValueAt(sel, 1).toString());
        txtMarcaProduct_Modificar.setText(mod.getValueAt(sel, 4).toString());
        txtUnidadMedidaProduct_Modificar.setText(mod.getValueAt(sel, 5).toString());
        botonModificarProducto.setVisible(true);
    }//GEN-LAST:event_TablaProductosMouseClicked

    private void btnBuscarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarTablaActionPerformed
        try {
            buscarTabla();
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnBuscarTablaActionPerformed

    private void btnRestaurarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarTablaActionPerformed
        try {
            ArchivoProductos pdao = new ArchivoProductos();
            List<Productos> prod = pdao.encontrar();
            
            limpiarTabla();
            agregarTabla(prod);
            txtBuscarTabla.setText("");
        } catch (IOException ex) {
            Logger.getLogger(GestionarProductos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnRestaurarTablaActionPerformed

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
    private org.edisoncor.gui.button.ButtonTask botonBorrar;
    private org.edisoncor.gui.button.ButtonTask botonExportar_a_ExcelProduct;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarModificaProduct;
    private org.edisoncor.gui.button.ButtonTask botonLimpiarProduct;
    private org.edisoncor.gui.button.ButtonTask botonModificarProducto;
    private org.edisoncor.gui.button.ButtonTask botonRegresarProducto;
    private org.edisoncor.gui.button.ButtonTask botonReporteProductos;
    private javax.swing.JButton btnBuscarTabla;
    private javax.swing.JButton btnRestaurarTabla;
    private javax.swing.JComboBox comboCatgProducto_Modificar;
    private javax.swing.JComboBox comboCatgProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private jcMousePanel.jcMousePanel jcMousePanel1;
    private org.edisoncor.gui.label.LabelTask labelTask1;
    private javax.swing.JTree treeProductos;
    private javax.swing.JTextField txtBuscarTabla;
    private javax.swing.JTextField txtIDProduct;
    private javax.swing.JTextField txtIDProduct_Modificar;
    private javax.swing.JTextField txtMarcaProduct;
    private javax.swing.JTextField txtMarcaProduct_Modificar;
    private javax.swing.JTextField txtNombreProduct;
    private javax.swing.JTextField txtNombreProduct_Modificar;
    private javax.swing.JTextField txtUnidadMedidaProduct;
    private javax.swing.JTextField txtUnidadMedidaProduct_Modificar;
    // End of variables declaration//GEN-END:variables
}
