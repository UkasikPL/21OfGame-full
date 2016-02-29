package org.ukasik;

import java.awt.Component;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class MainFrame extends javax.swing.JFrame {
   
    private ArrayList<javax.swing.JLabel> ListLabelPlayer = new ArrayList<javax.swing.JLabel>();
    private ArrayList<javax.swing.JLabel> ListLabelAI = new ArrayList<javax.swing.JLabel>();
    private ArrayList<Integer> ListOfMoveAI = new ArrayList<Integer>();
    private ArrayList<Integer> ListOfMovePlayer = new ArrayList<Integer>();
    private ArrayList<ImageIcon> ImageList = new ArrayList<ImageIcon>();
    private int PointsPlayer = 0;
    private int PointsAI = 0;

    private void InitializeGameComponents()
    {
        ListLabelPlayer.add(jLabel1);
        ListLabelPlayer.add(jLabel2);
        ListLabelPlayer.add(jLabel3);
        ListLabelAI.add(jLabel5);
        ListLabelAI.add(jLabel6);
        ListLabelAI.add(jLabel7);
        for(int i = 0; i < 14; i++)
        {
            try
            {
                if(i>=10)
                    ImageList.add(new ImageIcon(getClass().getResource("/org/ukasik/pic/deckcards_" + i + ".gif")));
                else
                    ImageList.add(new ImageIcon(getClass().getResource("/org/ukasik/pic/deckcards_0" + i + ".gif"))); 
            }
            catch (NullPointerException NPE)
            {
                
            }
            
        }
        RenderDefaultCardIcon();
        if(CardGenerator.ShowFirstCard)
            RenderFirstCard();
    }
    
    private void setScore(boolean SetPlayer, boolean SetAI)
    {
        PointsPlayer = 0;
        PointsAI = 0;
        for(int i : ListOfMovePlayer) 
            PointsPlayer += i + 2;
            
        for(int i : ListOfMoveAI)
            PointsAI += i + 2;
        
        if(SetPlayer)
            jLabel9.setText("Points: " + PointsPlayer);
        if(SetAI)
            jLabel8.setText("Points: " + PointsAI);
    }
    
    private void RenderFirstCard()
    {
        ListOfMoveAI.add(CardGenerator.AdjustCard());
        
        RenderCards(false, true);
        
        setScore(false, true);
    }
    
    private void RestartGame()
    {
        jButton3.setEnabled(false);
        jButton2.setEnabled(false);
        jButton1.setEnabled(true);
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Gamie is running");
        PointsPlayer = 0;
        PointsAI = 0;
        ListOfMovePlayer.clear();
        ListOfMoveAI.clear();
        RenderDefaultCardIcon();
        setScore(true, true);
        if(CardGenerator.ShowFirstCard)
            RenderFirstCard();
    }
    
    private void RenderDefaultCardIcon()
    {
        for(int i = 0; i < 3; i++)
        {
            ListLabelPlayer.get(i).setIcon(ImageList.get(12));
            ListLabelAI.get(i).setIcon(ImageList.get(12));
        }
    }
    
    private void AIMove()
    {
        if(CardGenerator.ShowFirstCard)
        {
            for(int i = 0; i < 2; i++)
            {
                if(PointsAI == 21)
                    break;
                else if(PointsAI < 21 && PointsAI+8<21)
                    ListOfMoveAI.add(CardGenerator.AdjustCard());
                else if(PointsAI < 21 && PointsAI+8>21)
                    break;
                else if(PointsAI > 21)
                    break;
                setScore(false, true);
            }
        }
        else
        {
            for(int i = 0; i < 3; i++)
            {
                if(PointsAI == 21)
                    break;
                else if(PointsAI < 21 && PointsAI+8<21)
                    ListOfMoveAI.add(CardGenerator.AdjustCard());
                else if(PointsAI < 21 && PointsAI+8>21)
                    break;
                else if(PointsAI > 21)
                    break;
                setScore(false, true);
            }
        }
    }
    
    private void PlayerMove()
    {
        if(ListOfMovePlayer.size() < 3)
        {
            ListOfMovePlayer.add(CardGenerator.AdjustCard());
        }
        RenderCards(true,false);
    }
    
    private void CheckCards()
    {
        RenderCards(true, true);
        if(PointsPlayer>PointsAI && PointsPlayer < 21)
        {
            jLabel4.setText("You Won !");
            jLabel4.setForeground(new java.awt.Color(128, 255, 0));
        }
        else if(PointsPlayer<PointsAI && PointsAI < 21)
        {
            jLabel4.setText("You Loose !");
            jLabel4.setForeground(new java.awt.Color(155, 0, 0));
        }
        else if(PointsPlayer==PointsAI && PointsPlayer < 21 && PointsAI < 21)
        {   
            jLabel4.setText("Draw !");
            jLabel4.setForeground(new java.awt.Color(255, 255, 51));
        }
        else if(PointsPlayer > 21 && PointsAI > 21)
        {
            jLabel4.setText("Draw !");
            jLabel4.setForeground(new java.awt.Color(255, 255, 51));
        }
        else if(PointsPlayer>21)
        {
            jLabel4.setText("You Loose !");
            jLabel4.setForeground(new java.awt.Color(155, 0, 0));
        }
        else if(PointsAI>21)
        {
            jLabel4.setText("You Won !");
            jLabel4.setForeground(new java.awt.Color(128, 255, 0));
        }
        else if(PointsAI==21)
        {
            jLabel4.setText("You Loose !");
            jLabel4.setForeground(new java.awt.Color(155, 0, 0));
        }
        else if(PointsPlayer==21)
        {
            jLabel4.setText("You Won !");
            jLabel4.setForeground(new java.awt.Color(128, 255, 0));
        }
        jButton3.setEnabled(true);
        jButton2.setEnabled(false);
    }
    
    private void RenderCards(boolean RenderPlayer, boolean RenderAI)
    {
        if(RenderPlayer)
        {
            for(int i = 0; i < ListOfMovePlayer.size(); i++)
                if(ListOfMovePlayer.get(i) < 10)
                    ListLabelPlayer.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ukasik/pic/deckcards_0"+ ListOfMovePlayer.get(i) +".gif")));
                else
                    ListLabelPlayer.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ukasik/pic/deckcards_"+ ListOfMovePlayer.get(i) +".gif")));     
        }   
        if(RenderAI)
        {
            for(int i = 0; i < ListOfMoveAI.size(); i++)
                if(ListOfMoveAI.get(i) < 10)
                    ListLabelAI.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ukasik/pic/deckcards_0"+ ListOfMoveAI.get(i) +".gif")));
                else
                    ListLabelAI.get(i).setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/ukasik/pic/deckcards_"+ ListOfMoveAI.get(i) +".gif")));
        }
    }
    
    public MainFrame() {
        initComponents();
        InitializeGameComponents();
        setLocationRelativeTo(null);
        setTitle("21OfGame by Kacper Łukasik");  
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 51, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.red);

        jButton1.setText("Adjust Card");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Check Card");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Restart");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("About");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setMaximumSize(new java.awt.Dimension(77, 62));
        jLabel1.setMinimumSize(new java.awt.Dimension(77, 62));
        jLabel1.setPreferredSize(new java.awt.Dimension(77, 62));

        jLabel2.setMaximumSize(new java.awt.Dimension(77, 62));
        jLabel2.setMinimumSize(new java.awt.Dimension(77, 62));
        jLabel2.setPreferredSize(new java.awt.Dimension(77, 62));

        jLabel3.setMaximumSize(new java.awt.Dimension(77, 62));
        jLabel3.setMinimumSize(new java.awt.Dimension(77, 62));
        jLabel3.setPreferredSize(new java.awt.Dimension(77, 62));

        jLabel5.setMaximumSize(new java.awt.Dimension(77, 62));
        jLabel5.setMinimumSize(new java.awt.Dimension(77, 62));
        jLabel5.setPreferredSize(new java.awt.Dimension(77, 62));

        jLabel6.setMaximumSize(new java.awt.Dimension(77, 62));
        jLabel6.setMinimumSize(new java.awt.Dimension(77, 62));
        jLabel6.setPreferredSize(new java.awt.Dimension(77, 62));

        jLabel7.setMaximumSize(new java.awt.Dimension(77, 62));
        jLabel7.setMinimumSize(new java.awt.Dimension(77, 62));
        jLabel7.setPreferredSize(new java.awt.Dimension(77, 62));

        jLabel8.setText("Points: 0");

        jLabel9.setText("Points: 0");

        jLabel4.setText("Game is running");

        jButton5.setText("Settings");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(66, 66, 66)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 47, Short.MAX_VALUE)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel8)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton2)
                            .addComponent(jButton5)))
                    .addComponent(jLabel9))
                .addContainerGap())
        );

        jLabel8.getAccessibleContext().setAccessibleName("jLabelAIPoints");
        jLabel9.getAccessibleContext().setAccessibleName("jLabelPlayerPoints");
        jLabel4.getAccessibleContext().setAccessibleName("");
        jLabel4.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PlayerMove();
        setScore(true, false);
        if(!jButton2.isEnabled())
            jButton2.setEnabled(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jButton1.setEnabled(false);
        setScore(true, false);
        AIMove();
        setScore(true, true);
        CheckCards();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RestartGame();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AboutFrom aboutForm = new AboutFrom();
        aboutForm.setVisible(true);
        aboutForm.setLocationRelativeTo(null);
        aboutForm.setTitle("About 21OfGame");
    }//GEN-LAST:event_jButton4ActionPerformed

     public static int okcancel(String theMessage) {
        int result = JOptionPane.showConfirmDialog((Component) null, theMessage,
            "EXIT", JOptionPane.OK_CANCEL_OPTION);
        return result;
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int JPANELOPTION = okcancel("Are you sure you want to go to the settings ? Your progress will not be saved!");
        System.out.println(JPANELOPTION);
        if(JPANELOPTION == 0)
        {
            SettingsForm settingsForm = new SettingsForm();
            settingsForm.setVisible(true);
            settingsForm.setLocationRelativeTo(null);
            dispose();
        }
    }//GEN-LAST:event_jButton5ActionPerformed
    
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
