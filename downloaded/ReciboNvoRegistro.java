
package Sistema;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReciboNvoRegistro extends javax.swing.JFrame {
private SimpleDateFormat f;
    
    public ReciboNvoRegistro() {
        initComponents();
        this.setLocationRelativeTo(null); // poner el formulario al centro de la pantalla
        Calendar cal = Calendar.getInstance();
        String hora = cal.get(Calendar.HOUR_OF_DAY) + " : " + cal.get(Calendar.MINUTE) + " : " + cal.get(Calendar.SECOND); // Poner la hora del sistema de la conputadora
        jLabel14.setText(hora);//depende de donde se quiera visualizar
        f = new SimpleDateFormat("dd-MM-yyyy"); //FECHA
        jLabel12.setText(f.format(new Date()));//depende de donde se quiera visualizar
        
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Garamond", 3, 36)); // NOI18N
        jLabel1.setText("Recibo De Pago");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 11, 240, -1));

        jLabel2.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel2.setText("El Sr.(A)");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 59, -1, -1));

        jLabel3.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel3.setText("nombre");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 59, 160, -1));

        jLabel4.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel4.setText("realizo el pago para poder");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(257, 59, 160, -1));

        jLabel5.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel5.setText("colocar un nuevo registro de toma de agua en la calle :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 94, 374, -1));

        jLabel6.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel6.setText("calle");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 129, 212, -1));

        jLabel7.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel7.setText("con las calle de referencia ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(228, 129, 177, -1));

        jLabel8.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel8.setText("Calle 2");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 157, 201, -1));

        jLabel9.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel9.setText("y");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 157, 119, -1));

        jLabel10.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel10.setText("Calle 3");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 192, 166, -1));

        jLabel11.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel11.setText("El pago se realizo el dia");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 192, 156, -1));

        jLabel12.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel12.setText("Fecha");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 192, 85, -1));

        jLabel13.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel13.setText("a las");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 227, 47, -1));

        jLabel14.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel14.setText("Hora");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(63, 227, 82, -1));

        jLabel15.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel15.setText("HRS, con un monto pagado de:");
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(151, 227, 196, -1));

        jLabel16.setFont(new java.awt.Font("Garamond", 3, 14)); // NOI18N
        jLabel16.setText("Monto");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 227, 64, -1));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Sitka Heading", 3, 14)); // NOI18N
        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(164, 271, -1, -1));
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 440, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
NvoRegistro registro = new NvoRegistro();
registro.setVisible(true);
this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(ReciboNvoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReciboNvoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReciboNvoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReciboNvoRegistro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ReciboNvoRegistro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    public static javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    public static javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    public static javax.swing.JLabel jLabel15;
    public static javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    public static javax.swing.JLabel jLabel8;
    public static javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
