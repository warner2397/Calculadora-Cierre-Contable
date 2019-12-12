package calculatorarqui;

import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Warner
 */
public class Operations extends javax.swing.JFrame {

    /**
     * Creates new form Operations
     *
     */
    String ruta;
    String valor;
    String tipo;
    double valueCurrency;

    double total;

    public Operations() {
        initComponents();
        this.setLocationRelativeTo(null);

        //Leo el archivo moneda para imprimir en el comboBox las monedas que tengo
        File archivo1;
        FileReader leer1;
        BufferedReader almacenamiento1;
        String cadena;

        archivo1 = new File(concatenar(MainWindow.dato, ".txt", null));

        try {
            leer1 = new FileReader(archivo1);
            almacenamiento1 = new BufferedReader(leer1);
            cadena = "";
            while (cadena != null) {

                try {
                    cadena = almacenamiento1.readLine();
                    if (cadena == null) {
                        almacenamiento1.close();
                        leer1.close();
                        return;
                    } else {
                    }
                    ruta = cadena;
                    cadena = almacenamiento1.readLine();
                    valor = cadena;
                    cadena = almacenamiento1.readLine();
                    tipo = cadena;

                    JButton btn = new JButton();
                    panelOperations.add(btn);
                    panelOperations.updateUI();

                    //Le seteo el valor del botón en el nombre
                    btn.setName(concatenar(String.valueOf(valor), " ", String.valueOf(tipo)));
                    btn.setText(valor);

                    ImageIcon imagen = new ImageIcon(ruta);
                    Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(80, 110, Image.SCALE_DEFAULT));
                    btn.setIcon(icono);
                    btn.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {

                            int cantIngre;   // La cantidad de monedas ingresadas

                            cantIngre = (int) solicitarCanti(5);

                            String[] bar = btn.getName().split(" ");

                            valueCurrency = Double.parseDouble(bar[0]);// Lo que vale el botón o la moneda, estoy reutilizando codigo

                            if (cantIngre == -1) {

                                return;
                            }

                            total += (valueCurrency * cantIngre);
                            resultado1.setText(String.valueOf(total));
                            resultado2.setText(String.valueOf(valueCurrency * cantIngre));

                            CrearEscribirArchivo("cierreDecaja", "\n");
                            CrearEscribirArchivo("cierreDecaja", concatenar("Fecha: ", obtenFecha(), null));
                            CrearEscribirArchivo("cierreDecaja", concatenar("Moneda: ", MainWindow.dato, null));
                            CrearEscribirArchivo("cierreDecaja", concatenar("Valor: ", bar[0], null));
                            CrearEscribirArchivo("cierreDecaja", concatenar("Tipo: ", bar[1], null));
                            CrearEscribirArchivo("cierreDecaja", concatenar("Cantidad: ", String.valueOf(cantIngre), null));
                            CrearEscribirArchivo("cierreDecaja", concatenar("Total: ", String.valueOf(valueCurrency * cantIngre), null));
                            CrearEscribirArchivo("cierreDecaja", "\n-------------------------------------\n");

                            StringBuffer n = new StringBuffer();
                            n.append(concatenar("Moneda: ", MainWindow.dato, null));
                            n.append(concatenar("\nValor: ", bar[0], null));
                            n.append(concatenar("\nTipo: ", bar[1], null));
                            n.append(concatenar("\nCantidad: ", String.valueOf(cantIngre), null));
                            n.append(concatenar("\nTotal: ", String.valueOf(valueCurrency * cantIngre), null));

                            JOptionPane.showMessageDialog(rootPane, n);
                        }
                    });

                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                almacenamiento1.close();
                leer1.close();

            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static String concatenar(String a, String b, String c) {
        StringBuffer n = new StringBuffer();
        if (c == null) {

            n.append(a);
            n.append(b);
        } else {
            n.append(a);
            n.append(b);
            n.append(c);

        }

        return n.toString();
    }

    //Metodo para solicitar catidades
    public double solicitarCanti(int n) { //Si el para metro es = 1 es para el valor de la moneda

        try {
            if (n == 1) {
                valueCurrency = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el valor de la moneda"));
            } else {
                valueCurrency = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad"));
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un número");
            return -1;

        }
        return valueCurrency;

    }

    //Metodo para crear y escribir en archivos
    public static void CrearEscribirArchivo(String nomArchi, String nuevoDato) {

        File archivo;                                  //Para manipular el archivo, abrir o cerrarlo

        archivo = new File(nomArchi + ".txt");   //Creando el archivo

        FileWriter escribir;
        PrintWriter linea;

        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                if (nuevoDato == null) {
                    return;
                }
            } catch (IOException ex) {
                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            escribir = new FileWriter(archivo, true);
            linea = new PrintWriter(escribir);

            linea.println(nuevoDato);
            linea.close();
            escribir.close();

        } catch (IOException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Metodo para obtener la fecha
    public String obtenFecha() {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = sdf.format(new Date());

        return fecha;

    }

    //Metodo para obtener la hora
    public String obtenHora() {

        Date date = new Date();
        SimpleDateFormat hour = new SimpleDateFormat("HH:mm:ss");
        String hora = hour.format(date);

        return hora;

    }

    //Metodo para seleccionar el tipo de moneda
    //La declaro estatica para usarla en otras clases
    public static String tipoMoneda() {

        String[] monedas = {
            "Billete",
            "Moneda"
        };

        String tipMoneda = (String) JOptionPane.showInputDialog(null, "Seleccione un tipo", "Moneda", JOptionPane.DEFAULT_OPTION, null, monedas, monedas[0]);

        return tipMoneda;
    }

    // Metodo para seleccionar foto
    public String selecFoto() {
        ruta = "";
        //Metodo para seleccionar una imagen
        try {
            JFileChooser jfilecho = new JFileChooser();
            jfilecho.showOpenDialog(this);
            File archivo = jfilecho.getSelectedFile();
            ruta = archivo.getAbsolutePath();

            if (archivo == null) {
                JOptionPane.showMessageDialog(null, "Debe ingresar una imagen");

            }
        } catch (Exception e) {

        }
        return ruta;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        resultado = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        newCurrency = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnEditMoneda = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelOperations = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        resultado1 = new javax.swing.JLabel();
        resultado2 = new javax.swing.JLabel();

        jButton1.setText("Nuva moneda");

        resultado.setBackground(new java.awt.Color(255, 255, 255));
        resultado.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resultado.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(153, 153, 255));

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        newCurrency.setText("Nueva moneda");
        newCurrency.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newCurrencyActionPerformed(evt);
            }
        });

        jButton3.setText("Volver");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnEditMoneda.setText("Editar moneda");
        btnEditMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMonedaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(newCurrency)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 367, Short.MAX_VALUE)
                        .addComponent(btnEditMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(newCurrency))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnEditMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOperations.setLayout(new java.awt.GridLayout(0, 3));
        jScrollPane1.setViewportView(panelOperations);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Lucida Sans", 0, 8)); // NOI18N
        jLabel2.setText("buho");

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Lucida Sans", 0, 8)); // NOI18N
        jLabel3.setText("Calculator");

        btnLimpiar.setText("AC");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        resultado1.setBackground(new java.awt.Color(255, 255, 255));
        resultado1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resultado1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        resultado2.setBackground(new java.awt.Color(255, 255, 255));
        resultado2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        resultado2.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(resultado2, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(107, 107, 107)
                .addComponent(btnLimpiar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar)
                .addGap(150, 150, 150))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(261, 261, 261))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(resultado1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(resultado2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(49, 49, 49))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        MainWindow nn = new MainWindow();
        nn.show();
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void newCurrencyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newCurrencyActionPerformed

        double valueCurrency;
        try {
            valueCurrency = solicitarCanti(1);
        } catch (Exception e) {
            return;
        }

        if (valueCurrency == -1) {
            return;
        }

//Escribo en el archivo la nueva moneda
        CrearEscribirArchivo(MainWindow.dato, selecFoto());
        CrearEscribirArchivo(MainWindow.dato, Double.toString(valueCurrency));
        CrearEscribirArchivo(MainWindow.dato, tipoMoneda());

        JOptionPane.showMessageDialog(null, "Moneda agregada correctamente");

        //Metodo para refrescar ventana
        Operations n = new Operations();
        n.show();
        this.dispose();
    }//GEN-LAST:event_newCurrencyActionPerformed

    private void btnEditMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMonedaActionPerformed
        Edit nE = new Edit();
        nE.show();
        this.dispose();
    }//GEN-LAST:event_btnEditMonedaActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        resultado1.setText("");
        total = 0;
        resultado2.setText("");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed

        CrearEscribirArchivo("cierreDecaja", "\n*_*_*_*CIERRE DE CAJA*_*_*_*_");
        CrearEscribirArchivo("cierreDecaja", concatenar("Fecha: ", obtenFecha(), null));
        CrearEscribirArchivo("cierreDecaja", concatenar("Hora: ", obtenHora(), null));
        CrearEscribirArchivo("cierreDecaja", concatenar("El total del cierre de caja es de: ", String.valueOf(total), null));
        CrearEscribirArchivo("cierreDecaja", "*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_*_\n");

        StringBuffer n = new StringBuffer();
        n.append("*_*_*_*CIERRE DE CAJA*_*_*_*_");
        n.append(concatenar("\nFecha: ", obtenFecha(), null));
        n.append(concatenar("\nHora: ", obtenHora(), null));
        n.append(concatenar("\nEl total del cierre de caja es de: ", String.valueOf(total), null));

        JOptionPane.showMessageDialog(rootPane, n);


    }//GEN-LAST:event_btnGuardarActionPerformed

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
            java.util.logging.Logger.getLogger(Operations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Operations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Operations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Operations.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Operations().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditMoneda;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newCurrency;
    private javax.swing.JPanel panelOperations;
    private javax.swing.JLabel resultado;
    private javax.swing.JLabel resultado1;
    private javax.swing.JLabel resultado2;
    // End of variables declaration//GEN-END:variables
}
