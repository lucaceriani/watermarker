package com.c3ry.watermarker;

import com.c3ry.watermarker.gui.*;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {


        JFrame frame = new JFrame("Form1");
        Form1 form = new Form1();

        frame.setContentPane(form.getPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Watermarker - c3ry 2017 - v 0.3");

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setSize(470, 300);
        frame.setResizable(false);

        frame.setVisible(true);

        settings s = new settings();
        s.checkExistence();
        if (s.getExistence()) {
            s.loadConfig(form);
        }

    }
}
