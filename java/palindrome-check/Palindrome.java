/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package palindrome;

import java.awt.Color;

/**
 *
 * @author Tyler Hippard - s0523192
 * 
 * This program takes an inputted string, checks to see if it is a palindrome
 * (ie the same forwards and backwards, e.g. 'racecar' backwards is 'racecar')
 * It also determines character types, and displays relevant stats
 * about the inputted string. Has support for detecting punctuation and
 * miscellaneous characters (foreign script, unknown letters, etc)
 * 
 */
public class Palindrome extends javax.swing.JFrame {

    /**
     * Creates new form Palindrome
     */
    public Palindrome() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        inputTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        outputTextArea = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        palinLabel = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        alphabetLabel = new javax.swing.JLabel();
        numericLabel = new javax.swing.JLabel();
        controlLabel = new javax.swing.JLabel();
        lowerLabel = new javax.swing.JLabel();
        upperLabel = new javax.swing.JLabel();
        whiteLabel = new javax.swing.JLabel();
        punctLabel = new javax.swing.JLabel();
        miscLabel = new javax.swing.JLabel();
        analyzeButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Palindrome Check");
        setPreferredSize(new java.awt.Dimension(749, 355));
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWeights = new double[] {1.0};
        layout.rowWeights = new double[] {1.0};
        getContentPane().setLayout(layout);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWeights = new double[] {0.0};
        jPanel1Layout.rowWeights = new double[] {0.0};
        jPanel1.setLayout(jPanel1Layout);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Input");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        jPanel1.add(jLabel1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reversed");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        jPanel1.add(jLabel2, gridBagConstraints);

        inputTextArea.setColumns(20);
        inputTextArea.setRows(5);
        jScrollPane1.setViewportView(inputTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.5;
        jPanel1.add(jScrollPane1, gridBagConstraints);

        outputTextArea.setEditable(false);
        outputTextArea.setColumns(20);
        outputTextArea.setRows(5);
        jScrollPane2.setViewportView(outputTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipadx = 10;
        gridBagConstraints.ipady = 10;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        jPanel1.add(jScrollPane2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(jPanel1, gridBagConstraints);

        jPanel3.setLayout(new java.awt.BorderLayout());

        palinLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        palinLabel.setText("Palindrome Check:");
        palinLabel.setOpaque(true);
        jPanel3.add(palinLabel, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 10;
        getContentPane().add(jPanel3, gridBagConstraints);

        jPanel2.setLayout(new java.awt.GridLayout(2, 4));

        alphabetLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        alphabetLabel.setText("0");
        alphabetLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Alphabetical Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        alphabetLabel.setOpaque(true);
        jPanel2.add(alphabetLabel);

        numericLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numericLabel.setText("0");
        numericLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Numerical Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        numericLabel.setOpaque(true);
        jPanel2.add(numericLabel);

        controlLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        controlLabel.setText("0");
        controlLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Control Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        controlLabel.setOpaque(true);
        jPanel2.add(controlLabel);

        lowerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lowerLabel.setText("0");
        lowerLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lower-case Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        lowerLabel.setOpaque(true);
        jPanel2.add(lowerLabel);

        upperLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        upperLabel.setText("0");
        upperLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Upper-case Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        upperLabel.setOpaque(true);
        jPanel2.add(upperLabel);

        whiteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        whiteLabel.setText("0");
        whiteLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Whitespace Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        whiteLabel.setOpaque(true);
        jPanel2.add(whiteLabel);

        punctLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        punctLabel.setText("0");
        punctLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Punctuation Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        punctLabel.setOpaque(true);
        jPanel2.add(punctLabel);

        miscLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        miscLabel.setText("0");
        miscLabel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Misc. Characters", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BELOW_TOP));
        miscLabel.setOpaque(true);
        jPanel2.add(miscLabel);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.ipady = 30;
        getContentPane().add(jPanel2, gridBagConstraints);

        analyzeButton.setText("Analyze text");
        analyzeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                analyzePalindrome(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(analyzeButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * 
     * analyzePalindrome does the work. It calls isPalindrome and stringStats
     * to determine palindromeness and information about the input string.
     * The label numbers will increase if that character type is found,
     * and background colors for the palindrome check area will change if 
     * its a palindrome or not.
     *
     */
    private void analyzePalindrome(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_analyzePalindrome
        boolean isPalin = isPalindrome(inputTextArea.getText());
        int[] strStats = stringStats(inputTextArea.getText());
        /* Making new colors here, Color.red and Color.green are too ugly*/
        Color betterRed = new Color(255,112,112);
        Color betterGreen = new Color(57, 168, 32);
        
        if (isPalin) {
            palinLabel.setText("Palindrome Check: PASSED");
            palinLabel.setBackground(betterGreen);
        }
        else {
            palinLabel.setText("Palindrome Check: FAILED");
            palinLabel.setBackground(betterRed);
        }
        /* set labels based on strStats array */
        alphabetLabel.setText(Integer.toString(strStats[0]));
        numericLabel.setText(Integer.toString(strStats[1]));
        controlLabel.setText(Integer.toString(strStats[2]));
        lowerLabel.setText(Integer.toString(strStats[3]));
        upperLabel.setText(Integer.toString(strStats[4]));
        whiteLabel.setText(Integer.toString(strStats[5]));
        punctLabel.setText(Integer.toString(strStats[6]));
        miscLabel.setText(Integer.toString(strStats[7]));
        
    }//GEN-LAST:event_analyzePalindrome

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
            java.util.logging.Logger.getLogger(Palindrome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Palindrome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Palindrome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Palindrome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Palindrome().setVisible(true);
            }
        });
    }
    /**
     * 
     * @param palinCheck -- user-inputted string to check palindromeness
     * @return true if the reverse of the string is the same as the input, else
     * false
     * 
     * Trims the string of leading/trailing whitespace, and sets all characters
     * in the string to lowercase
     */
    private boolean isPalindrome (String palinCheck) {
        palinCheck = palinCheck.toLowerCase();
        palinCheck = palinCheck.trim(); // trim whitespace
        
        StringBuilder palin = new StringBuilder();
        palin.append(palinCheck);
        
        for (int i = 0; i < palin.length(); i++) {
            if (!Character.isLetterOrDigit(palin.charAt(i))) {
                palin.deleteCharAt(i);
                i--;
                /* Deleting the char changes the index position of the next
                 * char in the string. Without this, only ~1/2 of the chars
                 * are actually deleted. 
                 */
            }
        }
        String palinReverse = palin.reverse().toString();
        outputTextArea.setText(palinReverse);
        return (palinReverse.equals(palinCheck));
        
    }
    /**
     * 
     * @param stringCheck -- user-inputted string to generate stats for
     * @return array of ints, for the stats of the string
     * 
     * Detects numeric, alphabetical (upper and lower), control characters,
     * whitespace characters (both spaces and control whitespace characters),
     * punctuation (according to POSIX character class), and
     * misc. characters (ie. any special characters not covered, like foreign
     * script, emojis, etc)
     */
    private int[] stringStats (String stringCheck) {
        int num = 0;
        int ctrl = 0;
        int lower = 0;
        int upper = 0;
        int white = 0;
        /* Decided to also check for punctuation and "misc" characters. Seeing
         * a zero when the input was "%*@#*$!*$&*^" was disappointing.
         */
        int punct = 0;
        int misc = 0;
        
        for (int i = 0; i < stringCheck.length(); i++) {
            char chk = stringCheck.charAt(i);
            int chkCode = (int)stringCheck.charAt(i); // ASCII Code
            
            if (Character.isUpperCase(chk)) {
                upper++;
            }
            else if (Character.isLowerCase(chk)) {
                lower++;
            }
            else if (Character.isDigit(chk)) {
                num++;
            }
            // next check includes ' ', simplifies detection of whitespace vs control
            else if (chkCode >= 0 && chkCode <= 32) {
                if (Character.isWhitespace(chk)) {
                    white++;
                }
                if (chk != ' ') {
                    ctrl++;
                }
            }
            // regex for matching against POSIX punctuation characters
            else if (Character.toString(chk).matches("^\\p{Punct}")) {
                punct++;
            }
            else {
                misc++;
            }
        }
        int[] array = { upper+lower, num, ctrl, lower, upper, white, punct
                      , misc };
        return array;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel alphabetLabel;
    private javax.swing.JButton analyzeButton;
    private javax.swing.JLabel controlLabel;
    private javax.swing.JTextArea inputTextArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lowerLabel;
    private javax.swing.JLabel miscLabel;
    private javax.swing.JLabel numericLabel;
    private javax.swing.JTextArea outputTextArea;
    private javax.swing.JLabel palinLabel;
    private javax.swing.JLabel punctLabel;
    private javax.swing.JLabel upperLabel;
    private javax.swing.JLabel whiteLabel;
    // End of variables declaration//GEN-END:variables
}
