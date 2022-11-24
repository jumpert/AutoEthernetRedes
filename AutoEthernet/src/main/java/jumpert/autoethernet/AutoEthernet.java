/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package jumpert.autoethernet;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jpere
 */
public class AutoEthernet extends javax.swing.JFrame {

    public int luzAlerta = 0;
    public int frenosAlerta = 0;
    public int trenAlerta = 0;
    public int aireAlerta = 0;
    public int radarAlerta = 0;
    public int radarAlertaT = 0;
    public int camDAlerta = 0;
    public int camTAlerta = 0;
    private Timer tiempo;
    private int milis = 0, seg = 0, min = 0;

    public ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            milis = milis + 2;
            if (milis == 1000) {
                seg++;
                milis = 0;
            }
            if (seg == 60) {
                min++;
                milis = 0;
                seg = 0;
            }
            if (min == 10) {
                dispose();
            }
            actualizarTiempo();

            // cargar tabla
            Random r = new Random();

            //int ri = r.nextInt(600) + 1;
            int item = r.nextInt(7) + 1;

            delay(800);
            String[] items = {"Luces", "Frenos", "Radar Del.", "Radar Tras.", "Presion Aire", "Tren de poder", "Cam Del.", "Cam Tras."};

            String[] infoData = new String[8];
            infoData[0] = etiqueta_tiempo;
            infoData[1] = "AuE";

            infoData[2] = items[item];

            infoData[3] = "PHY";
            infoData[4] = "Message";
            infoData[5] = "Tx";
            infoData[6] = "8";
            infoData[7] = Integer.toHexString(ranNum()) + " 00 00 00 00 00 00";
            model.addRow(infoData);
            Tabla.setModel(model);
        }
    };

    private void delay(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String etiqueta_tiempo;
    public String timeSec;

    public void actualizarTiempo() {
        String texto = (min <= 9 ? "0" : "") + min + ":" + (seg <= 9 ? "0" : "") + seg + ":" + (milis <= 9 ? "0" : "") + milis;
        etiqueta_tiempo = texto;
    }
    DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form AutoMEthernet
     */
    public AutoEthernet() {
        initComponents();
        this.setTitle("AUTOMOTIVE ETHERNET");
        SetImageLabel(jLabel2, "src/main/java/images/fondo.jpg");
        panelAlerta();
        tiempo = new Timer(10, acciones);
        tiempo.start();
        setModelo();

    }

    public void panelAlerta() {
        SetImageLabel(luces, "src/main/java/images/luces.png");
        luces.setOpaque(true);
        luces.setBackground(Color.lightGray);
        SetImageLabel(frenos, "src/main/java/images/frenos.png");
        frenos.setOpaque(true);
        frenos.setBackground(Color.lightGray);
        SetImageLabel(tren, "src/main/java/images/tren.png");
        tren.setOpaque(true);
        tren.setBackground(Color.lightGray);
        SetImageLabel(aire, "src/main/java/images/aire.png");
        aire.setOpaque(true);
        aire.setBackground(Color.lightGray);
        SetImageLabel(radar, "src/main/java/images/radar.png");
        radar.setOpaque(true);
        radar.setBackground(Color.lightGray);
        SetImageLabel(radarT, "src/main/java/images/radarT.png");
        radarT.setOpaque(true);
        radarT.setBackground(Color.lightGray);
        SetImageLabel(camD, "src/main/java/images/camD.png");
        camD.setOpaque(true);
        camD.setBackground(Color.lightGray);
        SetImageLabel(camT, "src/main/java/images/camT.png");
        camT.setOpaque(true);
        camT.setBackground(Color.lightGray);
        luzAlerta = 0;
        frenosAlerta = 0;
        trenAlerta = 0;
        aireAlerta = 0;
        radarAlerta = 0;
        radarAlertaT = 0;
        camDAlerta = 0;
        camTAlerta = 0;
    }

    public void cargarTabla() {
        String[] items = {"Luces", "Frenos", "Radar Del.", "Radar Tras.", "Presion Aire", "Tren de poder", "Cam Del.", "Cam Tras."};
        actualizarTiempo();
        String[] infoData = new String[8];
        infoData[0] = etiqueta_tiempo;
        infoData[1] = "AuE";
        for (String item : items) {
            infoData[2] = item;
        }
        infoData[3] = "PHY";
        infoData[4] = "Message";
        infoData[5] = "Tx";
        infoData[6] = "8";
        infoData[7] = Integer.toHexString(ranNum()) + " 00 00 00 00 00 00";
        model.addRow(infoData);
        Tabla.setModel(model);
    }

    public void setModelo() {
        String[] header = {"Timestrap", "C", "ID", "Destination", "Name", "Dir", "D", "Data"};
        model.setColumnIdentifiers(header);
        Tabla.setModel(model);
        Tabla.getColumnModel().getColumn(0).setPreferredWidth(30);
        Tabla.getColumnModel().getColumn(1).setPreferredWidth(15);
        Tabla.getColumnModel().getColumn(2).setPreferredWidth(40);
        Tabla.getColumnModel().getColumn(3).setPreferredWidth(40);
        Tabla.getColumnModel().getColumn(4).setPreferredWidth(40);
        Tabla.getColumnModel().getColumn(5).setPreferredWidth(15);
        Tabla.getColumnModel().getColumn(6).setPreferredWidth(15);
        Tabla.getColumnModel().getColumn(7).setPreferredWidth(120);

    }
    

    public int ranNum() {
        Random r = new Random();
        int randomInt = r.nextInt(255) + 1;
        return randomInt;
    }

    public String randomData() {

        StringBuilder text = new StringBuilder();
        text.append(Integer.toHexString(ranNum()));
        for (int i = 0; i < 7; i++) {
            int num = ranNum();
            text.append(" " + (num <= 15 ? "0" : "") + Integer.toHexString(num));
        }
        return text.toString();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        luces = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new ColorCelda();
        frenos = new javax.swing.JLabel();
        tren = new javax.swing.JLabel();
        aire = new javax.swing.JLabel();
        radar = new javax.swing.JLabel();
        radarT = new javax.swing.JLabel();
        camD = new javax.swing.JLabel();
        camT = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        LUCES = new javax.swing.JButton();
        FRENOS = new javax.swing.JButton();
        AIRE = new javax.swing.JButton();
        TREN = new javax.swing.JButton();
        RADARD = new javax.swing.JButton();
        RADART = new javax.swing.JButton();
        RESET = new javax.swing.JButton();
        CAMT = new javax.swing.JButton();
        CAMD = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAlert = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);

        jPanel2.setPreferredSize(new java.awt.Dimension(1080, 360));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 48)); // NOI18N
        jLabel1.setText("Automotive Ethernet");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(339, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(303, 303, 303))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        luces.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(luces, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 70, 40));

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Tabla);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(364, 6, 696, 202));

        frenos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(frenos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 70, 40));

        tren.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(tren, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 70, 40));

        aire.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(aire, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 20, 70, 40));

        radar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(radar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, 40));

        radarT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(radarT, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 70, 40));

        camD.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(camD, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 70, 40));

        camT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel3.add(camT, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 70, 40));

        jPanel4.setLayout(null);

        LUCES.setText("LUCES");
        LUCES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LUCESActionPerformed(evt);
            }
        });
        jPanel4.add(LUCES);
        LUCES.setBounds(35, 10, 90, 22);

        FRENOS.setText("FRENOS");
        FRENOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FRENOSActionPerformed(evt);
            }
        });
        jPanel4.add(FRENOS);
        FRENOS.setBounds(35, 40, 90, 22);

        AIRE.setText("P. AIRE");
        AIRE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AIREActionPerformed(evt);
            }
        });
        jPanel4.add(AIRE);
        AIRE.setBounds(35, 100, 90, 22);

        TREN.setText("TREN");
        TREN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TRENActionPerformed(evt);
            }
        });
        jPanel4.add(TREN);
        TREN.setBounds(35, 70, 90, 22);

        RADARD.setText("RADARD");
        RADARD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RADARDActionPerformed(evt);
            }
        });
        jPanel4.add(RADARD);
        RADARD.setBounds(35, 130, 90, 22);

        RADART.setText("RADART");
        RADART.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RADARTActionPerformed(evt);
            }
        });
        jPanel4.add(RADART);
        RADART.setBounds(35, 160, 90, 22);

        RESET.setText("RESET");
        RESET.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RESETActionPerformed(evt);
            }
        });
        jPanel4.add(RESET);
        RESET.setBounds(40, 260, 90, 50);

        CAMT.setText("CAM. T");
        CAMT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAMTActionPerformed(evt);
            }
        });
        jPanel4.add(CAMT);
        CAMT.setBounds(35, 220, 90, 22);

        CAMD.setText("CAM. D");
        CAMD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CAMDActionPerformed(evt);
            }
        });
        jPanel4.add(CAMD);
        CAMD.setBounds(35, 190, 90, 20);
        jPanel4.add(jLabel2);
        jLabel2.setBounds(20, 0, 880, 370);

        tblAlert.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblAlert);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(850, 0, 200, 370);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 1092, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addGap(1092, 1092, 1092)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RESETActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RESETActionPerformed
        panelAlerta();

    }//GEN-LAST:event_RESETActionPerformed

    private void FRENOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FRENOSActionPerformed
        if (frenosAlerta == 0) {
            frenos.setBackground(Color.green);
            frenosAlerta = 1;
        } else {
            if (frenosAlerta == 1) {
                frenos.setBackground(Color.lightGray);
                frenosAlerta = 0;
            }
        }
        String[] infoFrenos = new String[8];
        infoFrenos[0] = etiqueta_tiempo;
        infoFrenos[1] = "AuE";
        infoFrenos[2] = "Frenos";
        infoFrenos[3] = "Tren de poder";
        infoFrenos[4] = "Alert";
        infoFrenos[5] = "Tx";
        infoFrenos[6] = "8";
        infoFrenos[7] = randomData();
        model.addRow(infoFrenos);
        Tabla.setModel(model);
    }//GEN-LAST:event_FRENOSActionPerformed


    private void LUCESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LUCESActionPerformed
        // TODO add your handling code here:
        if (luzAlerta == 0) {
            luces.setBackground(Color.green);
            luzAlerta = 1;
        } else {
            if (luzAlerta == 1) {
                luces.setBackground(Color.lightGray);
                luzAlerta = 0;
            }
        }

        String[] infoLuces = new String[8];
        infoLuces[0] = etiqueta_tiempo;
        infoLuces[1] = "AuE";
        infoLuces[2] = "Luces";
        infoLuces[3] = "PHY";
        infoLuces[4] = "Message";
        infoLuces[5] = "Tx";
        infoLuces[6] = "8";
        infoLuces[7] = Integer.toHexString(ranNum()) + " 00 00 00 00 00 00";
        model.addRow(infoLuces);
        Tabla.setModel(model);
    }//GEN-LAST:event_LUCESActionPerformed

    private void AIREActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AIREActionPerformed
        if (aireAlerta == 0) {
            aire.setBackground(Color.green);
            aireAlerta = 1;
        } else {
            if (aireAlerta == 1) {
                aire.setBackground(Color.lightGray);
                aireAlerta = 0;
            }
        }
        String[] infoLuces = new String[8];
        infoLuces[0] = etiqueta_tiempo;
        infoLuces[1] = "AuE";
        infoLuces[2] = "Presion Aire";
        infoLuces[3] = "Tren de poder";
        infoLuces[4] = "Alert";
        infoLuces[5] = "Tx";
        infoLuces[6] = "8";
        infoLuces[7] = randomData();
        model.addRow(infoLuces);
        Tabla.setModel(model);
    }//GEN-LAST:event_AIREActionPerformed

    private void TRENActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TRENActionPerformed
        if (trenAlerta == 0) {
            tren.setBackground(Color.green);
            trenAlerta = 1;
        } else {
            if (trenAlerta == 1) {
                tren.setBackground(Color.lightGray);
                trenAlerta = 0;
            }
        }
        String[] infoLuces = new String[8];
        infoLuces[0] = etiqueta_tiempo;
        infoLuces[1] = "AuE";
        infoLuces[2] = "Trem de poder";
        infoLuces[3] = "Frenos";
        infoLuces[4] = "Alert";
        infoLuces[5] = "Tx";
        infoLuces[6] = "8";
        infoLuces[7] = randomData();
        model.addRow(infoLuces);
        Tabla.setModel(model);
    }//GEN-LAST:event_TRENActionPerformed

    private void RADARDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RADARDActionPerformed
        if (radarAlerta == 0) {
            radar.setBackground(Color.green);
            radarAlerta = 1;
        } else {
            if (radarAlerta == 1) {
                radar.setBackground(Color.lightGray);
                radarAlerta = 0;
            }
        }
        String[] infoRadarD = new String[8];
        infoRadarD[0] = etiqueta_tiempo;
        infoRadarD[1] = "AuE";
        infoRadarD[2] = "Radar Del.";
        infoRadarD[3] = "Cam Del.";
        infoRadarD[4] = "Alert";
        infoRadarD[5] = "Tx";
        infoRadarD[6] = "8";
        infoRadarD[7] = randomData();
        model.addRow(infoRadarD);
        Tabla.setModel(model);
    }//GEN-LAST:event_RADARDActionPerformed

    private void RADARTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RADARTActionPerformed
        if (radarAlertaT == 0) {
            radarT.setBackground(Color.green);
            radarAlertaT = 1;
        } else {
            if (radarAlertaT == 1) {
                radarT.setBackground(Color.lightGray);
                radarAlertaT = 0;
            }
        }
        String[] infoRadarT = new String[8];
        infoRadarT[0] = etiqueta_tiempo;
        infoRadarT[1] = "AuE";
        infoRadarT[2] = "Radar Tras.";
        infoRadarT[3] = "Cam Tras.";
        infoRadarT[4] = "Alert";
        infoRadarT[5] = "Tx";
        infoRadarT[6] = "8";
        infoRadarT[7] = randomData();
        model.addRow(infoRadarT);
        Tabla.setModel(model);
    }//GEN-LAST:event_RADARTActionPerformed

    private void CAMDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAMDActionPerformed
        if (camDAlerta == 0) {
            camD.setBackground(Color.green);
            camDAlerta = 1;
        } else {
            if (camDAlerta == 1) {
                camD.setBackground(Color.lightGray);
                camDAlerta = 0;
            }
        }
        String[] infoCAMD = new String[8];
        infoCAMD[0] = etiqueta_tiempo;
        infoCAMD[1] = "AuE";
        infoCAMD[2] = "Cam. Del.";
        infoCAMD[3] = "InfoEnter.";
        infoCAMD[4] = "Alert";
        infoCAMD[5] = "Tx";
        infoCAMD[6] = "8";
        infoCAMD[7] = randomData();
        model.addRow(infoCAMD);
        Tabla.setModel(model);
    }//GEN-LAST:event_CAMDActionPerformed

    private void CAMTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CAMTActionPerformed
        if (camTAlerta == 0) {
            camT.setBackground(Color.green);
            camTAlerta = 1;
        } else {
            if (camTAlerta == 1) {
                camT.setBackground(Color.lightGray);
                camTAlerta = 0;
            }
        }
        String[] infoCAMT = new String[8];
        infoCAMT[0] = etiqueta_tiempo;
        infoCAMT[1] = "AuE";
        infoCAMT[2] = "Cam. Tras.";
        infoCAMT[3] = "InfoEnter.";
        infoCAMT[4] = "Alert";
        infoCAMT[5] = "Tx";
        infoCAMT[6] = "8";
        infoCAMT[7] = randomData();
        model.addRow(infoCAMT);
        Tabla.setModel(model);
    }//GEN-LAST:event_CAMTActionPerformed

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
            java.util.logging.Logger.getLogger(AutoEthernet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AutoEthernet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AutoEthernet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AutoEthernet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AutoEthernet().setVisible(true);

            }

        });

    }

    public void SetImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AIRE;
    private javax.swing.JButton CAMD;
    private javax.swing.JButton CAMT;
    private javax.swing.JButton FRENOS;
    private javax.swing.JButton LUCES;
    private javax.swing.JButton RADARD;
    private javax.swing.JButton RADART;
    private javax.swing.JButton RESET;
    private javax.swing.JButton TREN;
    private javax.swing.JTable Tabla;
    private javax.swing.JLabel aire;
    private javax.swing.JLabel camD;
    private javax.swing.JLabel camT;
    private javax.swing.JLabel frenos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel luces;
    private javax.swing.JLabel radar;
    private javax.swing.JLabel radarT;
    private javax.swing.JTable tblAlert;
    private javax.swing.JLabel tren;
    // End of variables declaration//GEN-END:variables
}
