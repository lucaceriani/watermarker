package com.c3ry.watermarker.gui;

import com.c3ry.watermarker.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Form1 {
    private JPanel rootPanel;
    private JButton sfogliaButton;
    private JButton applicaWatermarkButton;
    private JTextField immOriginale;
    private JTextField immWatermark;
    private JButton sfogliaButton1;
    private JTextField fattoreScala;
    private JButton buttonHelp;
    private JLabel labelProgress;

    private int i;
    private int j;

    private String aiuto = "I nuovi file saranno salvati nella cartella 'WM' all'interno di quella delle immagini originali." +
                   "\n\nIl fattore di scala deve essere un numero compreso tra 0.01 e 1";

    public Form1() {
        applicaWatermarkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if (immOriginale.getText().equals("seleziona una cartella...")) {
                    JOptionPane.showMessageDialog(null, "Selezionare una cartella!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (immWatermark.getText().equals("seleziona un'immagine...")) {
                    JOptionPane.showMessageDialog(null, "Selezionare un'immagine di watermark!", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                } else if (Double.parseDouble(fattoreScala.getText())<0.01 || Double.parseDouble(fattoreScala.getText())>1) {
                    JOptionPane.showMessageDialog(null, "Selezionare un fattore di scala valido! (0.01 - 1)", "Errore", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Save the setting
                settings set = new settings();
                set.checkExistence();
                set.saveConfig(immOriginale.getText(), immWatermark.getText(), fattoreScala.getText());

                File folder = new File(getInputOriginalImage());
                File[] listOfFiles = folder.listFiles();

                String[] allFiles = new String[1000];
                j=0;
                i=0;

                for (i=0; i<listOfFiles.length; i++) {
                    if (listOfFiles[i].isFile()) {
                        allFiles[j] = listOfFiles[i].getName();
                        j++;
                    }
                }

                wAndR w = new wAndR();

                for (i=0; i<j; i++) {
                    labelProgress.setText((i+1)+"/"+j);
                    w.applyWatermark(getInputOriginalImage(), allFiles[i], getInputWatermarkImage(), getResizeFactor());
                }
            }
        });

        sfogliaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser j = new JFileChooser();
                j.setDialogTitle("Cartella immagini originali");
                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                j.showOpenDialog(null);

                immOriginale.setText(j.getSelectedFile().getPath());
            }
        });


        sfogliaButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFileChooser jj = new JFileChooser();
                jj.setDialogTitle("Immagine watermark");
                //j.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jj.showOpenDialog(null);

                immWatermark.setText(jj.getSelectedFile().getPath());
            }
        });
        buttonHelp.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, aiuto, "Aiuto", JOptionPane.INFORMATION_MESSAGE);
            }
        });

    }

    public JPanel getPanel() {
        return rootPanel;
    }

    public String getInputOriginalImage() {
        return immOriginale.getText();
    }

    public String getInputWatermarkImage() {
        return immWatermark.getText();
    }

    public double getResizeFactor() {
        return Double.parseDouble(fattoreScala.getText());
    }

    public void rep() {
        labelProgress.repaint();
    }

    public void updateSetting(String a, String b, String c) {
        immOriginale.setText(a);
        immWatermark.setText(b);
        fattoreScala.setText(c);
    }
}
