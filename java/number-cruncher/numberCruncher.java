package program;

import java.awt.Color;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.*;

/**
 *
 * @author Tyler Hippard - s0523192
 * 
 * It works, and it has some extra fancies in it. Enjoy!
 */

public class numberCruncher extends javax.swing.JFrame {

    public numberCruncher() {
        initComponents();
        updatePreview();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        userInput = new javax.swing.JPanel();
        inputPanel = new javax.swing.JPanel();
        inputLabel = new javax.swing.JLabel();
        inputFileName = new javax.swing.JTextField();
        browseFile = new javax.swing.JButton();
        widthPanel = new javax.swing.JPanel();
        widthLabel = new javax.swing.JLabel();
        widthInput = new javax.swing.JTextField();
        outputPanel = new javax.swing.JPanel();
        outputLabel = new javax.swing.JLabel();
        outputFileName = new javax.swing.JTextField();
        overwriteCheck = new javax.swing.JCheckBox();
        justifyLeft = new javax.swing.JCheckBox();
        precisionPanel = new javax.swing.JPanel();
        precisionLabel = new javax.swing.JLabel();
        precisionInput = new javax.swing.JTextField();
        columnPanel = new javax.swing.JPanel();
        columnLabel = new javax.swing.JLabel();
        columnInput = new javax.swing.JTextField();
        formatLayout = new javax.swing.JPanel();
        formatExample = new javax.swing.JScrollPane();
        preview = new javax.swing.JTextArea();
        footer = new javax.swing.JPanel();
        crunchNumbers = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Number Cruncher");
        setMinimumSize(new java.awt.Dimension(480, 520));
        setPreferredSize(new java.awt.Dimension(480, 520));
        setResizable(false);
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        layout.columnWeights = new double[] {1.0};
        layout.rowWeights = new double[] {1.0};
        getContentPane().setLayout(layout);

        java.awt.GridBagLayout userInputLayout = new java.awt.GridBagLayout();
        userInputLayout.columnWeights = new double[] {1.0};
        userInputLayout.rowWeights = new double[] {1.0};
        userInput.setLayout(userInputLayout);

        java.awt.GridBagLayout jPanel1Layout = new java.awt.GridBagLayout();
        jPanel1Layout.columnWeights = new double[] {1.0};
        jPanel1Layout.rowWeights = new double[] {1.0};
        inputPanel.setLayout(jPanel1Layout);

        inputLabel.setText("File to Read");
        inputLabel.setToolTipText("Input a file name to read from, or choose a file.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        inputPanel.add(inputLabel, gridBagConstraints);

        inputFileName.setToolTipText("Input a file name to read from, or choose a file.");
        inputFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputFileNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        inputPanel.add(inputFileName, gridBagConstraints);

        browseFile.setText("Choose file...");
        browseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseFile(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        inputPanel.add(browseFile, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        userInput.add(inputPanel, gridBagConstraints);

        java.awt.GridBagLayout jPanel2Layout = new java.awt.GridBagLayout();
        jPanel2Layout.columnWeights = new double[] {1.0};
        jPanel2Layout.rowWeights = new double[] {1.0};
        widthPanel.setLayout(jPanel2Layout);

        widthLabel.setText("Column Width");
        widthLabel.setToolTipText("Choose a new character width for each column, or keep it the default");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        widthPanel.add(widthLabel, gridBagConstraints);

        widthInput.setText("12");
        widthInput.setToolTipText("Choose a new character width for each column, or keep it the default");
        widthInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                widthInputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        widthPanel.add(widthInput, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        userInput.add(widthPanel, gridBagConstraints);

        java.awt.GridBagLayout jPanel3Layout = new java.awt.GridBagLayout();
        jPanel3Layout.columnWeights = new double[] {1.0};
        jPanel3Layout.rowWeights = new double[] {1.0};
        outputPanel.setLayout(jPanel3Layout);

        outputLabel.setText("Output File");
        outputLabel.setToolTipText("Choose a new name and/or path for the output file, or keep it the default.");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        outputPanel.add(outputLabel, gridBagConstraints);

        outputFileName.setToolTipText("Choose a new name and/or path for the output file, or keep it the default.");
        outputFileName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outputFileNameActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        outputPanel.add(outputFileName, gridBagConstraints);

        overwriteCheck.setText("Overwrite");
        overwriteCheck.setToolTipText("If enabled, allows the program to overwite existing files.");
        overwriteCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overwriteCheckActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        outputPanel.add(overwriteCheck, gridBagConstraints);

        justifyLeft.setText("Left-Align");
        justifyLeft.setToolTipText("Justifies the output against the left instead of the right. Decimal places will not line up. ");
        justifyLeft.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                justifyLeftActionPerformed(evt);
            }
        });
        outputPanel.add(justifyLeft, new java.awt.GridBagConstraints());

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 16);
        userInput.add(outputPanel, gridBagConstraints);

        java.awt.GridBagLayout jPanel4Layout = new java.awt.GridBagLayout();
        jPanel4Layout.columnWeights = new double[] {1.0};
        jPanel4Layout.rowWeights = new double[] {1.0};
        precisionPanel.setLayout(jPanel4Layout);

        precisionLabel.setText("Decimal Precision");
        precisionLabel.setToolTipText("Choose a new decimal precision number, or keep it the default");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        precisionPanel.add(precisionLabel, gridBagConstraints);

        precisionInput.setText("3");
        precisionInput.setToolTipText("Choose a new decimal precision number, or keep it the default");
        precisionInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precisionInputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        precisionPanel.add(precisionInput, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        userInput.add(precisionPanel, gridBagConstraints);

        java.awt.GridBagLayout jPanel6Layout = new java.awt.GridBagLayout();
        jPanel6Layout.columnWeights = new double[] {1.0};
        jPanel6Layout.rowWeights = new double[] {1.0};
        columnPanel.setLayout(jPanel6Layout);

        columnLabel.setText("Number of Columns");
        columnLabel.setToolTipText("Choose the number of columns to display in (1-5)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        columnPanel.add(columnLabel, gridBagConstraints);

        columnInput.setText("3");
        columnInput.setToolTipText("Choose the number of columns to display in (1-5)");
        columnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                columnInputActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        columnPanel.add(columnInput, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        userInput.add(columnPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.5;
        getContentPane().add(userInput, gridBagConstraints);

        formatLayout.setLayout(new java.awt.BorderLayout());

        preview.setEditable(false);
        preview.setColumns(20);
        preview.setRows(5);
        preview.setToolTipText("See how the file will output when you modify the values.");
        preview.setBorder(javax.swing.BorderFactory.createTitledBorder("Layout Preview"));
        formatExample.setViewportView(preview);

        formatLayout.add(formatExample, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(formatLayout, gridBagConstraints);

        footer.setLayout(new java.awt.BorderLayout());

        crunchNumbers.setText("Crunch the numbers!");
        crunchNumbers.setToolTipText("Do the work! Make the file!");
        crunchNumbers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crunchNumbers(evt);
            }
        });
        footer.add(crunchNumbers, java.awt.BorderLayout.CENTER);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        getContentPane().add(footer, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * updatePreview is the method called by the action events for many of the
     * program inputs. It uses the inputted data (or defaults) to generate
     * a fairly accurate preview of how the output file will look.
     * 
     * It also modifies column width to ensure it always looks nice in 
     * relation with the decimal precision, at least for numbers < 1000.
     */
    private void updatePreview() {
        int colWidth = Integer.parseInt(widthInput.getText());
        int colCount = Integer.parseInt(columnInput.getText());
        int precision = Integer.parseInt(precisionInput.getText());
        
        /* adding 7 makes the spacing between numbers < 1000 look nice, no matter
         * the precision. 
         */
        if (colWidth < precision+7) {
            colWidth = precision+7;
            widthInput.setText(Integer.toString(colWidth));
        }
        String colFormat = "%" + (leftJustified ? "-" : "") + colWidth + "." + precision + "f";
        preview.setText("");
        
        /*
         * This part could be replaced by similar output code to crunchNumbers(),
         * but then I would have to figure out how to use preview.append like
         * a PrintStream, and I am way too lazy to change something that works.
         * Besides, the preview was something I decided to do on a whim anyways
         * The actual meat and potatoes of the code technically is able to
         * handle column counts more than 5, as seen in this image:
         * ( https://i.imgur.com/ndG4m3G.png ), but doing the same for this 
         * feature is not necessary, as it was not even part of the requirements.
         */
        switch(colCount) {
            case 1: 
                for (int i = 0; i < 8; i++) {
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append("\n");
                }
                break;
            case 2:
                for (int i = 0; i < 8; i++) {
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append("\n");
                }
                break;
            case 3:
                for (int i = 0; i < 8; i++) {
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append("\n");
                }
                break;
            case 4:
                for (int i = 0; i < 8; i++) {
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append("\n");
                }
                break;
            case 5:
                for (int i = 0; i < 8; i++) {
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append(String.format(colFormat, 123.00000000));
                    preview.append("\n");
                }
                break;
        }
        
    }
    
    /**
     * chooseFile is the method for the "Choose file..." button, that displays
     * the standard JFileChooser. Not very pretty, but it works.
     * 
     * It updates the file name appropriately, and calls the function associated
     * with the filename input to trigger error-checking
     * 
     */
    private void chooseFile(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseFile
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputFileName.setText(chooser.getSelectedFile().getPath());
            inputFileNameActionPerformed(evt);
        }
    }//GEN-LAST:event_chooseFile

    /**
     * This event method performs error checking for the input filename,
     * detecting if the file exists, and displaying various error messages.
     * 
     * It also generates a default name for the output file based on the input.
     * 
     */
    private void inputFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputFileNameActionPerformed
        File testFile = new File(inputFileName.getText().trim());
        if (testFile.isFile() && !testFile.isDirectory()) {
            try {
                Paths.get(inputFileName.getText().trim());
            } catch (InvalidPathException | NullPointerException ex) {
                inputFileName.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "File does not exist!",
                                              "Error:", JOptionPane.ERROR_MESSAGE);
                
            }
            inputFileName.setBackground(Color.GREEN);
            verifiedInputPath = inputFileName.getText().trim();
            verifiedOutputPath = verifiedInputPath + "-crunched.txt";
            outputFileName.setText(verifiedOutputPath);
        }
        else {
            inputFileName.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "File does not exist!",
                                          "Error:", JOptionPane.ERROR_MESSAGE);
            
        }
    }//GEN-LAST:event_inputFileNameActionPerformed

    /**
     * widthInput's event method to update the format preview window with new
     * information.
     * 
     */
    private void widthInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_widthInputActionPerformed
        updatePreview();
    }//GEN-LAST:event_widthInputActionPerformed

    /**
     * outputFileName's event method to perform error correction on the output
     * filename. 
     * 
     * Also appends ".txt" to the end of a file if it is a not a .txt file.
     * 
     */
    private void outputFileNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputFileNameActionPerformed
        File testFile = new File(outputFileName.getText().trim());
        
        if ((testFile.isFile() || testFile.isDirectory()) && !overwriteEnabled) {
            outputFileName.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "Cannot overwrite existing file,"
                    + " enable overwrite if this is intentional.",
                                          "Error:", JOptionPane.ERROR_MESSAGE);
        }
        else if (testFile.getPath().equals("")) {
            outputFileName.setBackground(Color.RED);
            JOptionPane.showMessageDialog(this, "Invalid file path!",
                                          "Error:", JOptionPane.ERROR_MESSAGE);
        }
        else {
            try {
                Paths.get(outputFileName.getText().trim());
            } catch (InvalidPathException | NullPointerException ex) {
                outputFileName.setBackground(Color.RED);
                JOptionPane.showMessageDialog(this, "Invalid file path!", "Error:",
                                          JOptionPane.ERROR_MESSAGE);
                
            }
            if (!outputFileName.getText().contains(".txt") && !overwriteEnabled) {
                outputFileName.setText(outputFileName.getText() + ".txt");
            }
            outputFileName.setBackground(Color.GREEN);
            verifiedOutputPath = outputFileName.getText().trim();
        }
    }//GEN-LAST:event_outputFileNameActionPerformed

    /**
     * precisionInput's event method for triggering an update to the
     * format preview window.
     * 
     */
    private void precisionInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precisionInputActionPerformed
        if (Integer.parseInt(precisionInput.getText()) < 0) {
            precisionInput.setText("0");
        }
        updatePreview();
    }//GEN-LAST:event_precisionInputActionPerformed

    /**
     * Number of Columns event method for triggering a format preview update.
     * 
     * It also sets any value out of range back closest in-range value (1 or 5)
     * 
     */
    private void columnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_columnInputActionPerformed
        if (Integer.parseInt(columnInput.getText()) > 5) {
            columnInput.setText("5");
        }
        else if (Integer.parseInt(columnInput.getText()) < 1) {
            columnInput.setText("1");
        }
        updatePreview();
    }//GEN-LAST:event_columnInputActionPerformed

    /**
     * crunchNumbers performs a last-minute error check, before doing all of the
     * work.
     * 
     * It opens up the file to read, reads each double number in it, adds it
     * to an ArrayList, then outputs the formatted list to the output txt file.
     * 
     */
    private void crunchNumbers(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crunchNumbers
        // last-minute error checks
        inputFileNameActionPerformed(evt);
        outputFileNameActionPerformed(evt);
        columnInputActionPerformed(evt);
        
        List<Double> fileNums = new ArrayList<>();
        int colWidth = Integer.parseInt(widthInput.getText());
        int colCount = Integer.parseInt(columnInput.getText());
        int precision = Integer.parseInt(precisionInput.getText());
        
        // format string default is "%12.3f", displaying: "123.000     "
        String colFormat = "%" + (leftJustified ? "-" : "") + colWidth + "." + precision + "f";
        
        try {
// top-level try catch prevents NullPointerException from escaping error checks
            if (!verifiedOutputPath.isEmpty() && !verifiedInputPath.isEmpty()) {
                File inFile = new File(verifiedInputPath);

                try {
                    Scanner scanny = new Scanner(inFile);
                    PrintStream of = new PrintStream(verifiedOutputPath); 

                    while (scanny.hasNextDouble()) { // get all doubles from file
                        fileNums.add(scanny.nextDouble());
                    }

                    int cycle = 0;
                    for (double num: fileNums) { // foreach! woo!
                        of.printf(colFormat, num);
                        if (++cycle % colCount == 0) {
                            of.println();
                        }

                    }
                    of.close();
                    
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Something really broke. You"
                                              + " should never see this.", "Error:",
                                              JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (NullPointerException uClickedBeforeDoingAnythingU1F61E) {} //:(
    }//GEN-LAST:event_crunchNumbers

    /**
     * overwriteCheck is the action event for the overwrite checkbox. It displays
     * a warning message to the user, disabling it if they do not want it.
     * 
     * It also "remembers" if the user previously selected "Yes" in the confirmation
     * to prevent the spamming of the confirmation messages. 
     * (Not persistent through future uses of the program though, obviously)
     * 
     */
    private void overwriteCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_overwriteCheckActionPerformed
        if (overwriteWarnedYet == false) {
            int warning = JOptionPane.showConfirmDialog(this, "Enabling overwrite may cause loss"
                    + " of data, are you sure?", "Warning", JOptionPane.YES_NO_OPTION);
            if (warning == 1) {
                overwriteCheck.setSelected(false);
            }
            else if (warning == 0) {
                overwriteEnabled = true;
                overwriteWarnedYet = true;
            }
        }
        else { // if warned already, just set overwrite to state of checkbox
            overwriteEnabled = overwriteCheck.isSelected();
        }
    }//GEN-LAST:event_overwriteCheckActionPerformed
    /**
     * Left-justification checkbox with a quick and simple bool value swap and
     * preview update.
     * 
     */
    private void justifyLeftActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_justifyLeftActionPerformed
        leftJustified = !leftJustified;
        updatePreview();
    }//GEN-LAST:event_justifyLeftActionPerformed

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
            java.util.logging.Logger.getLogger(numberCruncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(numberCruncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(numberCruncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(numberCruncher.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new numberCruncher().setVisible(true);            }
        });
    }
    
    String verifiedInputPath;
    String verifiedOutputPath;
    boolean overwriteEnabled = false;
    boolean overwriteWarnedYet = false;
    boolean leftJustified = false;
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseFile;
    private javax.swing.JTextField columnInput;
    private javax.swing.JLabel columnLabel;
    private javax.swing.JPanel columnPanel;
    private javax.swing.JButton crunchNumbers;
    private javax.swing.JPanel footer;
    private javax.swing.JScrollPane formatExample;
    private javax.swing.JPanel formatLayout;
    private javax.swing.JTextField inputFileName;
    private javax.swing.JLabel inputLabel;
    private javax.swing.JPanel inputPanel;
    private javax.swing.JCheckBox justifyLeft;
    private javax.swing.JTextField outputFileName;
    private javax.swing.JLabel outputLabel;
    private javax.swing.JPanel outputPanel;
    private javax.swing.JCheckBox overwriteCheck;
    private javax.swing.JTextField precisionInput;
    private javax.swing.JLabel precisionLabel;
    private javax.swing.JPanel precisionPanel;
    private javax.swing.JTextArea preview;
    private javax.swing.JPanel userInput;
    private javax.swing.JTextField widthInput;
    private javax.swing.JLabel widthLabel;
    private javax.swing.JPanel widthPanel;
    // End of variables declaration//GEN-END:variables
}
