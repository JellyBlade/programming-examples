/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import javax.swing.*;

/**
 *
 * @author JellyBlade
 */
public class ConnectFour extends javax.swing.JFrame {
    
    public ConnectFour() {
        initComponents();
        
        board = new JButton[][]{
        {jButton1, jButton2, jButton3, jButton4, jButton5, jButton6, jButton7, jButton8},
        {jButton9, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15, jButton16},
        {jButton17, jButton18, jButton19, jButton20, jButton21, jButton22, jButton23, jButton24},
        {jButton25, jButton26, jButton27, jButton28, jButton29, jButton30, jButton31, jButton32},
        {jButton33, jButton34, jButton35, jButton36, jButton37, jButton38, jButton39, jButton40},
        {jButton41, jButton42, jButton43, jButton44, jButton45, jButton46, jButton47, jButton48},
        {jButton49, jButton50, jButton51, jButton52, jButton53, jButton54, jButton55, jButton56},
        {jButton57, jButton58, jButton59, jButton60, jButton61, jButton62, jButton63, jButton64}
        };
    }
    
    private JButton[][] board;
    private String[] playerNames = { "", "", ""  };
    private String placement;
    private int playerCount;
    private int turn = 0;
    private int moveCount = 0;
    private static int MAX_NAME_LENGTH = 64;
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
            java.util.logging.Logger.getLogger(ConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConnectFour.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConnectFour().setVisible(true);
            }
        });
    }
    
    /* 
     * checkName function: Checks the user-inputted string to determine
     *                     whether or not the name is unique, and modifies it
     *                     so that it is.
     *                     Also truncates very long names.
     *                     
     *                     NOTE: Yes, this is technically not what you
     *                     asked for, but I figured just adding a number
     *                     to the end of the name would suffice for most
     *                     cases.
     */
    String checkName(String nameToCheck) {
        int sameCount = 0;
        for (int i = 0; i < playerNames.length; i++) {
            if (nameToCheck.equals(playerNames[i])) {
                sameCount++;
            }
        }
        if (nameToCheck.length() > MAX_NAME_LENGTH) {
            nameToCheck = nameToCheck.substring(0, MAX_NAME_LENGTH);
        }
        switch(sameCount) {
            case 1:
                break;
            case 2:
                nameToCheck += "2";
                break;
            case 3:
                nameToCheck += "3";
                break;
        }
        return nameToCheck;
    }
    
    /*
     * checkWin function: Performs horizontal, vertical, and diagonal checks
     *                    to determine whether or not a player has won.
     *                    If the player has won, it displays a message.
     *                    If the game ends in a draw, it will also display a
     *                    message.
     */  
    void checkWin() {
        // Horizontal check
        placement = "\t";
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                placement += getMove(row,col);
            }
            placement += "\t";
        }
        
        // Vertical Check
        placement += "\t";
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                placement += getMove(row,col);
            }
            placement += "\t";
        }
        
        // Backslash lower diagonal region including middle
        placement += "\t";
        for (int rowStart = 0; rowStart < 5; rowStart++) {
            for (int row = rowStart, col = 0; row < 8 && col < 8; row++, col++) {
                placement += getMove(row,col);
            }
            placement += "\t";
        }
        
        // Backslash upper diagonal region
        placement += "\t";
        for (int colStart = 1; colStart < 5; colStart++) {
            for (int row = 0, col = colStart; row < 8 && col < 8; row++, col++) {
                placement += getMove(row,col);
            }
            placement += "\t";
        }
        
        // Forward-slash upper diagonal region including middle
        placement += "\t";
        for (int rowStart = 7; rowStart > 2; rowStart--) {
            for (int row = rowStart, col = 0; row > -1 && col < 8; row--, col++) {
                placement += getMove(row,col);
            }
            placement += "\t";
        }
        
        // Forward-slash lower diagonal region
        placement += "\t";
        for (int colStart = 1; colStart < 5; colStart++) {
            for (int row = 7, col = colStart; row > -1 && col < 8; row--, col++) {
                placement += getMove(row, col);
            }
            placement += "\t";
        }
        
        
        
        // saving time with badly-named variables
        String a = player1Label.getText();
        String b = player2Label.getText();
        String c = player3Label.getText();
        if (placement.contains(a + a + a + a)) {
            player1Label.setBorder(player2Label.getBorder());
            player2Label.setBorder(player3Label.getBorder());
            JOptionPane.showMessageDialog(this, "Winner!\n" + a + " has taken the cake!"
                                                + "\nPress \"Start a new game\" to restart.");
            playerCount = 0; // Setting playerCount to 0 locks the game board.
        }
        else if (placement.contains(b + b + b + b)) {
            player1Label.setBorder(player3Label.getBorder());
            player3Label.setBorder(player2Label.getBorder());
            JOptionPane.showMessageDialog(this, "Winner!\n" + b + " wears the crown!"
                                                + "\nPress \"Start a new game\" to restart.");
            playerCount = 0;
        }
        else if (placement.contains(c + c + c + c)) {
            JOptionPane.showMessageDialog(this, "Winner!\n" + c + " is the king now!"
                                                + "\nPress \"Start a new game\" to restart.");
            playerCount = 0;
        }
        else if (moveCount == 64) {
            JOptionPane.showMessageDialog(this, "Draw!\nYou all have failed to impress me."
                                                + "\nPress \"Start a new game\" to restart.");
            playerCount = 0;
        }
    }
    
    // method returns a player's token if played on
    // returns a simple space if the button has not been played on yet
    // thank you Barry
    private String getMove(int row, int col) {
      String play = board[row][col].getText();

    if (play.equals("")) return " ";

    return play;
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

        header = new javax.swing.JPanel();
        player1Label = new javax.swing.JLabel();
        player2Label = new javax.swing.JLabel();
        player3Label = new javax.swing.JLabel();
        player1Name = new javax.swing.JTextField();
        player2Name = new javax.swing.JTextField();
        player3Name = new javax.swing.JTextField();
        buttons = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        jButton56 = new javax.swing.JButton();
        jButton57 = new javax.swing.JButton();
        jButton58 = new javax.swing.JButton();
        jButton59 = new javax.swing.JButton();
        jButton60 = new javax.swing.JButton();
        jButton61 = new javax.swing.JButton();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        footer = new javax.swing.JPanel();
        Reset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Connect Four");
        setPreferredSize(new java.awt.Dimension(500, 500));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        header.setOpaque(false);
        header.setLayout(new java.awt.GridLayout(2, 3));

        player1Label.setBackground(new java.awt.Color(255, 153, 153));
        player1Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player1Label.setText("Player 1");
        player1Label.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 51), 3));
        player1Label.setMinimumSize(new java.awt.Dimension(10, 10));
        player1Label.setOpaque(true);
        header.add(player1Label);

        player2Label.setBackground(new java.awt.Color(153, 255, 153));
        player2Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player2Label.setText("Player 2");
        player2Label.setMinimumSize(new java.awt.Dimension(10, 10));
        player2Label.setOpaque(true);
        header.add(player2Label);

        player3Label.setBackground(new java.awt.Color(102, 153, 255));
        player3Label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        player3Label.setText("Player 3");
        player3Label.setMinimumSize(new java.awt.Dimension(10, 10));
        player3Label.setOpaque(true);
        header.add(player3Label);

        player1Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        player1Name.setMinimumSize(new java.awt.Dimension(10, 10));
        player1Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player1NameActionPerformed(evt);
            }
        });
        header.add(player1Name);

        player2Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        player2Name.setMinimumSize(new java.awt.Dimension(10, 10));
        player2Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player2NameActionPerformed(evt);
            }
        });
        header.add(player2Name);

        player3Name.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        player3Name.setMinimumSize(new java.awt.Dimension(10, 10));
        player3Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                player3NameActionPerformed(evt);
            }
        });
        header.add(player3Name);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(header, gridBagConstraints);

        buttons.setLayout(new java.awt.GridLayout(8, 8));

        jButton1.setAlignmentY(0.0F);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton1.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton1.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton1);

        jButton2.setAlignmentY(0.0F);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton2.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton2.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton2);

        jButton3.setAlignmentY(0.0F);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton3.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton3.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton3);

        jButton4.setAlignmentY(0.0F);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton4.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton4.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton4);

        jButton5.setAlignmentY(0.0F);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton5.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton5.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton5);

        jButton6.setAlignmentY(0.0F);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton6.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton6.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton6);

        jButton7.setAlignmentY(0.0F);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton7.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton7.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton7);

        jButton8.setAlignmentY(0.0F);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton8.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton8.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton8);

        jButton9.setAlignmentY(0.0F);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton9.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton9.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton9);

        jButton10.setAlignmentY(0.0F);
        jButton10.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton10.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton10.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton10.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton10);

        jButton11.setAlignmentY(0.0F);
        jButton11.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton11.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton11.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton11.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton11);

        jButton12.setAlignmentY(0.0F);
        jButton12.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton12.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton12.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton12.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton12);

        jButton13.setAlignmentY(0.0F);
        jButton13.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton13.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton13.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton13.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton13);

        jButton14.setAlignmentY(0.0F);
        jButton14.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton14.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton14.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton14.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton14);

        jButton15.setAlignmentY(0.0F);
        jButton15.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton15.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton15.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton15.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton15);

        jButton16.setAlignmentY(0.0F);
        jButton16.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton16.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton16.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton16.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton16);

        jButton17.setAlignmentY(0.0F);
        jButton17.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton17.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton17.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton17.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton17);

        jButton18.setAlignmentY(0.0F);
        jButton18.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton18.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton18.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton18.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton18);

        jButton19.setAlignmentY(0.0F);
        jButton19.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton19.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton19.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton19.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton19);

        jButton20.setAlignmentY(0.0F);
        jButton20.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton20.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton20.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton20.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton20);

        jButton21.setAlignmentY(0.0F);
        jButton21.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton21.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton21.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton21.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton21);

        jButton22.setAlignmentY(0.0F);
        jButton22.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton22.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton22.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton22.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton22);

        jButton23.setAlignmentY(0.0F);
        jButton23.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton23.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton23.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton23.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton23);

        jButton24.setAlignmentY(0.0F);
        jButton24.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton24.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton24.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton24.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton24);

        jButton25.setAlignmentY(0.0F);
        jButton25.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton25.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton25.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton25.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton25);

        jButton26.setAlignmentY(0.0F);
        jButton26.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton26.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton26.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton26.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton26);

        jButton27.setAlignmentY(0.0F);
        jButton27.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton27.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton27.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton27.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton27);

        jButton28.setAlignmentY(0.0F);
        jButton28.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton28.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton28.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton28.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton28);

        jButton29.setAlignmentY(0.0F);
        jButton29.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton29.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton29.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton29.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton29);

        jButton30.setAlignmentY(0.0F);
        jButton30.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton30.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton30.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton30.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton30);

        jButton31.setAlignmentY(0.0F);
        jButton31.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton31.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton31.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton31.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton31);

        jButton32.setAlignmentY(0.0F);
        jButton32.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton32.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton32.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton32.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton32);

        jButton33.setAlignmentY(0.0F);
        jButton33.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton33.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton33.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton33.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton33);

        jButton34.setAlignmentY(0.0F);
        jButton34.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton34.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton34.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton34.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton34);

        jButton35.setAlignmentY(0.0F);
        jButton35.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton35.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton35.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton35.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton35);

        jButton36.setAlignmentY(0.0F);
        jButton36.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton36.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton36.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton36.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton36);

        jButton37.setAlignmentY(0.0F);
        jButton37.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton37.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton37.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton37.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton37);

        jButton38.setAlignmentY(0.0F);
        jButton38.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton38.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton38.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton38.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton38);

        jButton39.setAlignmentY(0.0F);
        jButton39.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton39.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton39.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton39.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton39);

        jButton40.setAlignmentY(0.0F);
        jButton40.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton40.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton40.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton40.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton40);

        jButton41.setAlignmentY(0.0F);
        jButton41.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton41.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton41.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton41.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton41);

        jButton42.setAlignmentY(0.0F);
        jButton42.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton42.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton42.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton42.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton42);

        jButton43.setAlignmentY(0.0F);
        jButton43.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton43.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton43.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton43.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton43);

        jButton44.setAlignmentY(0.0F);
        jButton44.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton44.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton44.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton44.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton44);

        jButton45.setAlignmentY(0.0F);
        jButton45.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton45.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton45.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton45.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton45);

        jButton46.setAlignmentY(0.0F);
        jButton46.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton46.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton46.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton46.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton46);

        jButton47.setAlignmentY(0.0F);
        jButton47.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton47.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton47.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton47.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton47);

        jButton48.setAlignmentY(0.0F);
        jButton48.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton48.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton48.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton48.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton48);

        jButton49.setAlignmentY(0.0F);
        jButton49.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton49.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton49.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton49.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton49);

        jButton50.setAlignmentY(0.0F);
        jButton50.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton50.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton50.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton50.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton50);

        jButton51.setAlignmentY(0.0F);
        jButton51.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton51.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton51.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton51.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton51);

        jButton52.setAlignmentY(0.0F);
        jButton52.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton52.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton52.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton52.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton52);

        jButton53.setAlignmentY(0.0F);
        jButton53.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton53.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton53.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton53.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton53);

        jButton54.setAlignmentY(0.0F);
        jButton54.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton54.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton54.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton54.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton54);

        jButton55.setAlignmentY(0.0F);
        jButton55.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton55.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton55.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton55.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton55);

        jButton56.setAlignmentY(0.0F);
        jButton56.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton56.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton56.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton56.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton56);

        jButton57.setAlignmentY(0.0F);
        jButton57.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton57.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton57.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton57.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton57);

        jButton58.setAlignmentY(0.0F);
        jButton58.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton58.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton58.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton58.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton58);

        jButton59.setAlignmentY(0.0F);
        jButton59.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton59.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton59.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton59.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton59);

        jButton60.setAlignmentY(0.0F);
        jButton60.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton60.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton60.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton60.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton60);

        jButton61.setAlignmentY(0.0F);
        jButton61.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton61.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton61.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton61.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton61);

        jButton62.setAlignmentY(0.0F);
        jButton62.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton62.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton62.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton62.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton62);

        jButton63.setAlignmentY(0.0F);
        jButton63.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton63.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton63.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton63.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton63);

        jButton64.setAlignmentY(0.0F);
        jButton64.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton64.setMaximumSize(new java.awt.Dimension(8, 8));
        jButton64.setMinimumSize(new java.awt.Dimension(1, 1));
        jButton64.setPreferredSize(new java.awt.Dimension(8, 8));
        jButton64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPress(evt);
            }
        });
        buttons.add(jButton64);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(1, 1, 1, 1);
        getContentPane().add(buttons, gridBagConstraints);

        footer.setLayout(new java.awt.GridLayout(1, 0));

        Reset.setText("Start a new game");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newGame(evt);
            }
        });
        footer.add(Reset);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        getContentPane().add(footer, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void player1NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player1NameActionPerformed
        player1Name.setEnabled(false);
        playerNames[0] = player1Name.getText();
        player1Label.setText(checkName(player1Name.getText()));
        playerCount++;
    }//GEN-LAST:event_player1NameActionPerformed

    private void player2NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player2NameActionPerformed
        player2Name.setEnabled(false);
        playerNames[1] = player2Name.getText();
        player2Label.setText(checkName(player2Name.getText()));
        playerCount++;
    }//GEN-LAST:event_player2NameActionPerformed

    private void player3NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_player3NameActionPerformed
        player3Name.setEnabled(false);
        playerNames[2] = player3Name.getText();
        player3Label.setText(checkName(player3Name.getText()));
        playerCount++;
    }//GEN-LAST:event_player3NameActionPerformed

    private void newGame(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newGame
        player1Label.setText("Player 1");
        player2Label.setText("Player 2");
        player3Label.setText("Player 3");
        
        player1Name.setText("");
        player1Name.setEnabled(true);
        
        player2Name.setText("");
        player2Name.setEnabled(true);
        
        player3Name.setText("");
        player3Name.setEnabled(true);
        
        for (int rows = 0; rows < 8; rows++) {
            for (int cols = 0; cols < 8; cols++) {
                board[rows][cols].setText("");
                board[rows][cols].setBackground(buttons.getBackground());
            }
        }
        playerCount = 0;
        moveCount = 0;
        placement = "";
        turn = 0;
    }//GEN-LAST:event_newGame

    private void buttonPress(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPress
        JButton pressed = (JButton)evt.getSource();
        if (pressed.getText().equals("") && playerCount == 3) {
            switch(turn) {
                case 0:
                    pressed.setText(player1Label.getText());
                    pressed.setBackground(player1Label.getBackground());
                    player2Label.setBorder(player1Label.getBorder());
                    player1Label.setBorder(player3Label.getBorder());
                    turn = 1;
                    break;
                case 1:
                    pressed.setText(player2Label.getText());
                    pressed.setBackground(player2Label.getBackground());
                    player3Label.setBorder(player2Label.getBorder());
                    player2Label.setBorder(player1Label.getBorder());
                    turn = 2;
                    break;
                case 2:
                    pressed.setText(player3Label.getText());
                    pressed.setBackground(player3Label.getBackground());
                    player1Label.setBorder(player3Label.getBorder());
                    player3Label.setBorder(player2Label.getBorder());
                    turn = 0;
                    break;
            }
            moveCount++;
            checkWin();
        }
    }//GEN-LAST:event_buttonPress

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Reset;
    private javax.swing.JPanel buttons;
    private javax.swing.JPanel footer;
    private javax.swing.JPanel header;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton56;
    private javax.swing.JButton jButton57;
    private javax.swing.JButton jButton58;
    private javax.swing.JButton jButton59;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton60;
    private javax.swing.JButton jButton61;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel player1Label;
    private javax.swing.JTextField player1Name;
    private javax.swing.JLabel player2Label;
    private javax.swing.JTextField player2Name;
    private javax.swing.JLabel player3Label;
    private javax.swing.JTextField player3Name;
    // End of variables declaration//GEN-END:variables
}
