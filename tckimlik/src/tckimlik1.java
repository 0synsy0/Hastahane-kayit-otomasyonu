    import java.sql.Connection;
    import java.sql.DriverManager;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.sql.Statement;
    import javax.swing.JOptionPane;
    
    
    
public class tckimlik1 extends javax.swing.JFrame {

    public static Connection baglan;
     
    private ResultSet res;
    private String seri_no;
            
    public tckimlik1() {
        initComponents();
    }
    
    public static void baglantiAc()
    {
    try
    {
    Class.forName("com.mysql.jdbc.Driver");
    String bag="jdbc:mysql://localhost:3306/kimlikbilgileri";
    String kullaniciadi="root";
    String sifre="";
    baglan=DriverManager.getConnection(bag,kullaniciadi,sifre);
    JOptionPane.showMessageDialog(null,"Veri Tabanı Baglantısı Başarılı Oldu..");
    }
    catch(Exception e)
    {
    JOptionPane.showMessageDialog(null,"Veri Tabanı Baglantısı Başarısız Oldu.."+e);
    }
    }
    public static void baglantiKapat()
    {
    try
    {
    baglan.close();
    JOptionPane.showMessageDialog(null,"Veri Tabanı Bağlantısı Kapandı..");
    }
    catch(Exception e)
    {
    JOptionPane.showMessageDialog(null,"Veri Tabanı Bağlantısı Kapanmadı.."+e);
    }
    }
    
    public void ekle()
    {
    String soyad,ad,d_tarihi,seeri_no,uyruk="",cinsiyet=null,son_g_tarihi;
    long tc_kimlik;
    
    tc_kimlik=Long.parseLong(jTextField1.getText());
    soyad=jTextField2.getText();
    ad=jTextField3.getText();
    d_tarihi=jTextField4.getText();
    seri_no=jTextField5.getText();
    son_g_tarihi=jTextField6.getText();
    
    if(jRadioButton1.isSelected()==true)
        cinsiyet="E";
    if(jRadioButton2.isSelected()==true)
        cinsiyet="K";
    if(jComboBox1.getSelectedIndex()==0)
        uyruk="TÜRKİYE";
    if(jComboBox1.getSelectedIndex()==1)
        uyruk="DİĞER";
    
    String eklesorgusu="insert into bilgiler(tc_kimlik,soyad,ad,d_tarihi,seri_no,son_g_tarihi,cinsiyet,uyruk)values ("+tc_kimlik+",'"+soyad+"','"+ad+"','"+d_tarihi+"','"+seri_no+"','"+son_g_tarihi+"','"+cinsiyet+"','"+uyruk+"')";
        
    try
    {
    Statement calistir=baglan.createStatement();
    calistir.execute(eklesorgusu);
    
    JOptionPane.showMessageDialog(null,"Kayıt Ekleme Başarılı Oldu..");
    
    }
    catch(Exception e)
            {
            JOptionPane.showMessageDialog(null,"Kayıt Ekleme Başarısız Oldu.."+e);
            System.out.println(e);
            }
    
    
    }
    public void bul()
    {
    try
    {
    Statement sta = baglan.createStatement();
    String bulkomutu="select*from bilgiler where tc_kimlik="+jTextField1.getText();
    String cins;
    String uyr;
    
    res=sta.executeQuery(bulkomutu);
    res.next();
    
    jTextField2.setText(res.getString("soyad"));
    jTextField3.setText(res.getString("ad"));
    jTextField4.setText(res.getString("d_tarihi"));
    jTextField5.setText(res.getString("seri_no"));
    jTextField6.setText(res.getString("son_g_tarihi"));
    
    cins=res.getString("cinsiyet");
    uyr=res.getString("uyruk");
    
    if(cins.equals("E"))
    {
    jRadioButton1.setSelected(true);
    }
    if(cins.equals("K"))
    {
    jRadioButton2.setSelected(true);
    }
    if("TÜRKİYE".equals(uyr))
    {
    jComboBox1.setSelectedIndex(0);
    }
    if("DİĞER".equals(uyr))
    {
    jComboBox1.setSelectedIndex(1);
    }
    }
    catch(Exception e)
    {
    System.out.println(e.toString());
    JOptionPane.showConfirmDialog(null,e.toString(),"bilgilertablosu",JOptionPane.PLAIN_MESSAGE);
    }
    }
public void sil()
{
try
{
String silmekomutu="Delete from bilgiler where tc_kimlik="+jTextField1.getText();
Statement sta= baglan.createStatement();
sta.execute(silmekomutu);
JOptionPane.showConfirmDialog(null,"Kayıt Başarıyla Silindi","bilgilertablosu",JOptionPane.PLAIN_MESSAGE);
}
catch(Exception e)
{
System.out.println(e.toString());

JOptionPane.showConfirmDialog(null,e.toString(),"bilgilertablosu",JOptionPane.PLAIN_MESSAGE);
}
}

public void guncelle()
{
try
{
long tc_kimlik;
String soyad,ad,d_tarihi,seri_no,son_g_tarihi,cinsiyet="",uyruk="";
Long.parseLong(jTextField1.getText());
soyad=jTextField2.getText();
ad=jTextField3.getText();
d_tarihi=jTextField4.getText();
seri_no=jTextField5.getText();
son_g_tarihi=jTextField6.getText();

if(jRadioButton1.isSelected()==true)
    cinsiyet="E";

if(jRadioButton2.isSelected()==true)
    cinsiyet="K";

if(jComboBox1.getSelectedIndex()==0)
    uyruk="TÜRKİYE";

if(jComboBox1.getSelectedIndex()==1)
    uyruk="DİĞER";

String guncellekomutu="update bilgiler set soyad='"+jTextField2.getText()+"',ad='"+jTextField3.getText()+"',d_tarihi='"+jTextField4.getText()+"',seri_no='"+jTextField5.getText()+"',son_g_tarihi='"+jTextField6.getText()+"',cinsiyet='"+cinsiyet+"',uyruk='"+uyruk+"'where tc_kimlik='"+jTextField1.getText()+"'";

Statement sta=baglan.createStatement();
sta.execute(guncellekomutu);
JOptionPane.showMessageDialog(null,"Kayıt Başarıyla Güncellendi..","bilgilertablosu",JOptionPane.PLAIN_MESSAGE);

}
catch(Exception e)
{
System.out.println(e.toString());
JOptionPane.showMessageDialog(null, e.toString(),"bilgilertablosu",JOptionPane.PLAIN_MESSAGE);
}
}

public void temizle()
{
jTextField1.setText("");
jTextField2.setText("");
jTextField3.setText("");
jTextField4.setText("");
jTextField5.setText("");
jTextField6.setText("");

buttonGroup1.clearSelection();
}

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("T.C. KİMLİK KARTI");

        jLabel1.setText("T.C. KİMLİK NO");

        jLabel2.setText("SOYAD");

        jLabel3.setText("AD");

        jLabel4.setText("DOĞUM TARİHİ");

        jLabel5.setText("SERİ NO");

        jLabel6.setText("SON GEÇERLİLİK TARİHİ");

        jLabel7.setText("CİNSİYET");

        jLabel8.setText("UYRUK");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("BAY");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("BAYAN");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "TÜRKİYE", "DİĞER" }));

        jButton1.setText("AÇ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("KAPAT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("EKLE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("TEMİZLE");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("SİL");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("BUL");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("GÜNCELLE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton2)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1)
                                .addComponent(jTextField2)
                                .addComponent(jTextField3)
                                .addComponent(jTextField4)
                                .addComponent(jTextField5)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(3, 3, 3)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(121, 121, 121))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jButton7)))
                .addGap(0, 30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jRadioButton1)
                    .addComponent(jRadioButton2))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addGap(18, 18, 18)
                .addComponent(jButton7)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        baglantiAc();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        baglantiKapat();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        ekle();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        bul();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        sil();
        temizle();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        guncelle();
        temizle();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        temizle();
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(tckimlik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tckimlik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tckimlik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tckimlik1.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tckimlik1().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
