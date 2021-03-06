/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unscrambler;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author JellyBlade
 */
public class Unscrambler extends javax.swing.JFrame {

    /**
     * Creates new form Unscrambler
     */
    public Unscrambler() {
        initComponents();
        try {
            loadPokemon();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Unscrambler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        input = new javax.swing.JTextField();
        outputLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(2, 1));

        input.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputActionPerformed(evt);
            }
        });
        getContentPane().add(input);
        getContentPane().add(outputLabel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputActionPerformed

            for (String poke: pokeList) {
                if (poke.length() == input.getText().length()) {
     
                    char[] ch1 = input.getText().toCharArray();
                    char[] ch2 = poke.toCharArray();
                    String test = new String();
                    
                    Arrays.sort(ch1);
                    Arrays.sort(ch2);
                    
                    for (int i = 0; i < poke.length(); i++) {
                        if (ch1[i] == ch2[i]) {
                            test += ch1[i];
                        }
                    }
                    if (test.length() == poke.length()) {
                        StringSelection select = new StringSelection(poke);
                        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
                        outputLabel.setText(poke);
                        clip.setContents(select, select);
                    }
                    
                }
            }
    }//GEN-LAST:event_inputActionPerformed

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
            java.util.logging.Logger.getLogger(Unscrambler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Unscrambler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Unscrambler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Unscrambler.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Unscrambler().setVisible(true);
            }
        });
    }
    
    List<String> pokeList = new ArrayList<String>();
    String output = new String();
    
    private void loadPokemon() throws FileNotFoundException {
        File pokeFile = new File("pokemon.txt");
        Scanner scanny = new Scanner(pokeFile);
        scanny.useDelimiter(",");
        while (scanny.hasNext()) {
            String grab = scanny.next().trim().toLowerCase();
            grab = grab.replaceAll("\\s","");
            pokeList.add(grab);
        }
        for (String poke: pokeList) {
            System.out.println(poke);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField input;
    private javax.swing.JLabel outputLabel;
    // End of variables declaration//GEN-END:variables
}
