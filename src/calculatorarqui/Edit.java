package calculatorarqui;

import static calculatorarqui.Operations.CrearEscribirArchivo;
import static calculatorarqui.Operations.concatenar;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Warner
 */
public class Edit extends javax.swing.JFrame {

    public static ArrayList<String> monedas = new ArrayList<>();//El array lo utilizo en todos los metodos

    /**
     * Creates new form Edit
     */
    public Edit() {
        initComponents();
        this.setLocationRelativeTo(null);

        //Leo el archivo moneda para imprimir en el comboBox las monedas que tengo
        File archivo;
        FileReader leer;
        BufferedReader almacenamiento;
        String cadena;

        archivo = new File(concatenar(MainWindow.dato, ".txt", null));

        try {
            leer = new FileReader(archivo);
            almacenamiento = new BufferedReader(leer);
            cadena = "";
            while (cadena != null) {

                try {
                    cadena = almacenamiento.readLine();
                    cadena = almacenamiento.readLine();
                    if (cadena == null) {
                        almacenamiento.close();
                        leer.close();
                        return;
                    }
                    comboBoxMonedas.addItem(cadena);
                    almacenamiento.readLine();
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                almacenamiento.close();
                leer.close();

            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void eliminarArchivo(String name) {
        File archivo;
        archivo = new File(concatenar(name, ".txt", null));
        archivo.delete();
    }

    public void refrescarVentana() {

        //Metodo para refrescar ventana
        Edit mW = new Edit();
        mW.show();
        this.dispose();
    }

    public static void quemarDatos(String fot, String tip, String valo, String dataCombo, String punt) {

        monedas.clear();

        File archivo;
        FileReader leer;
        BufferedReader almacenamiento;
        String cadena;
        if (punt != null) {
            archivo = new File("Moneda.txt");

        } else {
            archivo = new File(concatenar(MainWindow.dato, ".txt", null));
        }
        String foto, valor;

        try {
            leer = new FileReader(archivo);
            almacenamiento = new BufferedReader(leer);
            cadena = "";
            if (fot != null) {

                while (cadena != null) {

                    try {
                        cadena = almacenamiento.readLine();
                        foto = cadena;
                        if (cadena == null) {
                            almacenamiento.close();
                            leer.close();
                            break;
                        }

                        cadena = almacenamiento.readLine();
                        valor = cadena;
                        if (valor.equals(dataCombo)) {

                            cadena = almacenamiento.readLine();
                            monedas.add(fot);
                            monedas.add(valor);
                            monedas.add(cadena);

                        } else {
                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valor);
                            monedas.add(cadena);

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (valo != null) {

                while (cadena != null) {

                    try {
                        cadena = almacenamiento.readLine();
                        foto = cadena;
                        if (cadena == null) {
                            almacenamiento.close();
                            leer.close();
                            break;
                        }

                        cadena = almacenamiento.readLine();
                        valor = cadena;
                        if (valor.equals(dataCombo)) {

                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valo);
                            monedas.add(cadena);

                        } else {
                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valor);
                            monedas.add(cadena);

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else if (tip != null) {
                while (cadena != null) {

                    try {
                        cadena = almacenamiento.readLine();
                        foto = cadena;
                        if (cadena == null) {
                            almacenamiento.close();
                            leer.close();
                            break;
                        }

                        cadena = almacenamiento.readLine();
                        valor = cadena;
                        if (valor.equals(dataCombo)) {
                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valor);
                            monedas.add(Operations.tipoMoneda());

                        } else {
                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valor);
                            monedas.add(cadena);

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else if (punt != null) {
                while (cadena != null) {

                    try {
                        cadena = almacenamiento.readLine();
                        if (cadena == null) {
                            almacenamiento.close();
                            leer.close();
                            break;
                        }
                        if (cadena.equals(dataCombo)) {

                        } else {
                            monedas.add(cadena);
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            } else {
                while (cadena != null) {

                    try {
                        cadena = almacenamiento.readLine();
                        foto = cadena;
                        if (cadena == null) {
                            almacenamiento.close();
                            leer.close();
                            break;
                        }

                        cadena = almacenamiento.readLine();
                        valor = cadena;
                        if (valor.equals(dataCombo)) {

                            cadena = almacenamiento.readLine();

                        } else {
                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valor);
                            monedas.add(cadena);

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
            try {
                almacenamiento.close();
                leer.close();

            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        archivo.delete();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        btnVolver = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        comboBoxMonedas = new javax.swing.JComboBox();
        eliminar = new javax.swing.JButton();
        imagen = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        tipo = new javax.swing.JButton();
        valor = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Editor de monedas");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(147, 147, 147)
                .addComponent(btnVolver)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        comboBoxMonedas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMonedasActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        imagen.setText("Imagen");
        imagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imagenActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Editar moneda");

        tipo.setText("Tipo");
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        valor.setText("Valor");
        valor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valorActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 8)); // NOI18N
        jLabel2.setText("buho");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Lucida Sans", 0, 8)); // NOI18N
        jLabel3.setText("Calculator");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(imagen)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(69, 69, 69)
                                .addComponent(jLabel1)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(valor)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                .addComponent(tipo)
                                .addGap(125, 125, 125))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(264, 264, 264))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(173, 173, 173)
                .addComponent(comboBoxMonedas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(eliminar)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMonedas))
                .addGap(62, 62, 62)
                .addComponent(jLabel1)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imagen)
                    .addComponent(valor)
                    .addComponent(tipo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 134, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxMonedasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMonedasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMonedasActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed

        Operations nO = new Operations();
        nO.show();
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        if ((String) comboBoxMonedas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay archivos para editar!", "Datos", WIDTH);
            return;
        }
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente editar la imagen?",
                "Editar imagen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            monedas.clear();
            File archivo;
            FileReader leer;
            BufferedReader almacenamiento;
            String cadena;
            archivo = new File(concatenar(MainWindow.dato, ".txt", null));
            String foto, valor;

            try {
                leer = new FileReader(archivo);
                almacenamiento = new BufferedReader(leer);
                cadena = "";
                while (cadena != null) {

                    try {
                        cadena = almacenamiento.readLine();
                        foto = cadena;
                        if (cadena == null) {
                            almacenamiento.close();
                            leer.close();
                            break;
                        }

                        cadena = almacenamiento.readLine();
                        valor = cadena;
                        if (valor.equals((String) comboBoxMonedas.getSelectedItem())) {
                            JOptionPane.showMessageDialog(rootPane, "La moneda fue eliminada!", "Eliminando moneda", WIDTH);
                            cadena = almacenamiento.readLine();

                        } else {
                            cadena = almacenamiento.readLine();
                            monedas.add(foto);
                            monedas.add(valor);
                            monedas.add(cadena);

                        }

                    } catch (IOException ex) {
                        Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                try {
                    almacenamiento.close();
                    leer.close();

                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }

            archivo.delete();
            if (monedas.size() == 0) {
                CrearEscribirArchivo(MainWindow.dato, null);
            }

            for (String moneda : monedas) {

                CrearEscribirArchivo(MainWindow.dato, moneda);

            }
            refrescarVentana();
        }
    }//GEN-LAST:event_eliminarActionPerformed

    private void imagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imagenActionPerformed
        if ((String) comboBoxMonedas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay archivos para editar!", "Datos", WIDTH);
            return;
        }

        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente editar la imagen?",
                "Editar imagen", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String ruta;

            //Metodo para seleccionar una imagen
            JFileChooser jfilecho = new JFileChooser();
            jfilecho.showOpenDialog(this);
            File archivo = jfilecho.getSelectedFile();
            ruta = archivo.getAbsolutePath();

            String data = (String) comboBoxMonedas.getSelectedItem();

            quemarDatos(ruta, null, null, data, null);

            for (String moneda : monedas) {
                Operations.CrearEscribirArchivo(MainWindow.dato, moneda);
            }
            refrescarVentana();
            JOptionPane.showMessageDialog(rootPane, "La moneda fue editada!", "Editando moneda", WIDTH);

        }
    }//GEN-LAST:event_imagenActionPerformed

    private void valorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valorActionPerformed

        if ((String) comboBoxMonedas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay archivos para editar!", "Datos", WIDTH);
            return;
        }
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente editar el valor?",
                "Editar valor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            String mone;
            try {
                mone = JOptionPane.showInputDialog("Ingrese el nuevo valor de la moneda");
                double p = Double.parseDouble(mone);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Debe ingresar numeros!");
                return;
            }

            String data = (String) comboBoxMonedas.getSelectedItem();

            quemarDatos(null, null, mone, data, null);

            for (String moneda : monedas) {
                Operations.CrearEscribirArchivo(MainWindow.dato, moneda);
            }
            JOptionPane.showMessageDialog(rootPane, "La moneda fue edita!", "Editando moneda", WIDTH);
            refrescarVentana();
        }


    }//GEN-LAST:event_valorActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        if ((String) comboBoxMonedas.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(rootPane, "No hay archivos para editar!", "Datos", WIDTH);
            return;
        }
        if (JOptionPane.showConfirmDialog(rootPane, "¿Desea realmente editar el tipo?",
                "Editar tipo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            String data = (String) comboBoxMonedas.getSelectedItem();

            quemarDatos(null, data, null, data, null);

            for (String moneda : monedas) {

                Operations.CrearEscribirArchivo(MainWindow.dato, moneda);
            }
            JOptionPane.showMessageDialog(rootPane, "La moneda fue editada!", "Editando moneda", WIDTH);
            refrescarVentana();

        }
    }//GEN-LAST:event_tipoActionPerformed

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
            java.util.logging.Logger.getLogger(Edit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edit.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Edit().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnVolver;
    private javax.swing.JComboBox comboBoxMonedas;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton imagen;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton tipo;
    private javax.swing.JButton valor;
    // End of variables declaration//GEN-END:variables
}
