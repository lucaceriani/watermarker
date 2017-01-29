package com.c3ry.watermarker.gui;

import com.c3ry.watermarker.*;

import javax.swing.*;
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
    private JPanel jpOpzioni;
    private JPanel jpImmagini;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;
    private JRadioButton radioButton3;
    private JRadioButton radioButton4;
    private JRadioButton radioButton5;
    private JRadioButton radioButton6;
    private JRadioButton radioButton7;
    private JRadioButton radioButton8;
    private JRadioButton radioButton9;
    private JTextField txtOffsetX;
    private JTextField txtOffsetY;
    private JButton button1;

    private int i;
    private int j;

    private final String aiuto = "I nuovi file saranno salvati nella cartella 'WM' all'interno di quella delle immagini originali." +
                   "\n\nIl fattore di scala deve essere un numero compreso tra 0.01 e 1.";
    private final String offset = "L'offset in pixel dall'angolo in alto a sinistra rispetto alla posizione. L'offset può essere negativo.";

    public Form1() {

        groupButton();

        applicaWatermarkButton.addActionListener(actionEvent -> {

            if (immOriginale.getText().equals("seleziona una cartella...")) {
                JOptionPane.showMessageDialog(null, "Selezionare una cartella!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (immWatermark.getText().equals("seleziona un'immagine...")) {
                JOptionPane.showMessageDialog(null, "Selezionare un'immagine di watermark!", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (getResizeFactor()<0.01 || getResizeFactor()>1) {
                JOptionPane.showMessageDialog(null, "Selezionare un fattore di scala valido! (0.01 - 1)", "Errore", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Save the setting
            settings set = new settings();
            set.checkExistence();
            set.saveConfig(immOriginale.getText(), immWatermark.getText(), fattoreScala.getText() , txtOffsetX.getText(), txtOffsetY.getText(), getPosition()+"");

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
                w.applyWatermark(getInputOriginalImage(), allFiles[i], getInputWatermarkImage(), getResizeFactor(), getPosition(), getOffsetX(), getOffsetY());
            }
        });

        sfogliaButton.addActionListener(actionEvent -> {
            JFileChooser j = new JFileChooser();
            j.setDialogTitle("Cartella immagini originali");
            j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            j.showOpenDialog(null);

            immOriginale.setText(j.getSelectedFile().getPath());
        });


        sfogliaButton1.addActionListener(actionEvent -> {
            JFileChooser jj = new JFileChooser();
            jj.setDialogTitle("Immagine watermark");
            //j.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jj.showOpenDialog(null);

            immWatermark.setText(jj.getSelectedFile().getPath());
        });

        buttonHelp.addActionListener(actionEvent -> JOptionPane.showMessageDialog(null, aiuto, "Aiuto", JOptionPane.INFORMATION_MESSAGE));

        button1.addActionListener(actionEvent -> JOptionPane.showMessageDialog(null, offset, "Aiuto", JOptionPane.INFORMATION_MESSAGE));
    }

    private void groupButton( ) {

        ButtonGroup bg1 = new ButtonGroup( );

        bg1.add(radioButton1);
        bg1.add(radioButton2);
        bg1.add(radioButton3);
        bg1.add(radioButton4);
        bg1.add(radioButton5);
        bg1.add(radioButton6);
        bg1.add(radioButton7);
        bg1.add(radioButton8);
        bg1.add(radioButton9);

    }

    public JPanel getPanel() {
        return rootPanel;
    }

    private String getInputOriginalImage() {
        return immOriginale.getText();
    }

    private String getInputWatermarkImage() {
        return immWatermark.getText();
    }

    private double getResizeFactor() {
        if (fattoreScala.getText().equals("")) {
            fattoreScala.setText("1");
        }
        return Double.parseDouble(fattoreScala.getText());
    }

    public void rep() {
        labelProgress.repaint();
    }

    public void updateSetting(String a, String b, String c, String d, String e, String f) {
        immOriginale.setText(a);
        immWatermark.setText(b);
        fattoreScala.setText(c);
        txtOffsetX.setText(d);
        txtOffsetY.setText(e);
        setPosition(Integer.parseInt(f));
    }

    public int getPosition() {
        if (radioButton1.isSelected()) {return 1;}
        if (radioButton2.isSelected()) {return 2;}
        if (radioButton3.isSelected()) {return 3;}
        if (radioButton4.isSelected()) {return 4;}
        if (radioButton5.isSelected()) {return 5;}
        if (radioButton6.isSelected()) {return 6;}
        if (radioButton7.isSelected()) {return 7;}
        if (radioButton8.isSelected()) {return 8;}
        if (radioButton9.isSelected()) {return 9;}
        return -1;
    }

    public int getOffsetX() {
        if (txtOffsetX.getText().equals("")) {
            txtOffsetX.setText("0");
        }
        return Integer.parseInt(txtOffsetX.getText());
    }

    public int getOffsetY() {
        if (txtOffsetY.getText().equals("")) {
            txtOffsetY.setText("0");
        }
        return Integer.parseInt(txtOffsetY.getText());
    }

    private void setPosition(int f) {
        if (f==1) {radioButton1.setSelected(true);}
        if (f==2) {radioButton2.setSelected(true);}
        if (f==3) {radioButton3.setSelected(true);}
        if (f==4) {radioButton4.setSelected(true);}
        if (f==5) {radioButton5.setSelected(true);}
        if (f==6) {radioButton6.setSelected(true);}
        if (f==7) {radioButton7.setSelected(true);}
        if (f==8) {radioButton8.setSelected(true);}
        if (f==9) {radioButton9.setSelected(true);}
    }

}
