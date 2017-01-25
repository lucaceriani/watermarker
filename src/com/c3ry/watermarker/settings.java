package com.c3ry.watermarker;

import com.c3ry.watermarker.gui.Form1;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class settings {

    public String path = System.getProperty("user.home") + File.separator +".watermarker";
    public String fileName = "wm_config.properties";
    public String f = path+File.separator+fileName;

    public int fileExist=0;

    public void checkExistence() {
        File fi = new File(f);
        if(fi.exists() && !fi.isDirectory()) {
            fileExist=1;
        }
    }

    public void loadConfig(Form1 form) {

        Properties p = new Properties();
        InputStream is;

        // First try loading from the current directory
        try {
            is = new FileInputStream(f);
        } catch (Exception e) {
            is=null;
        }

        try {
            p.load(is);
        } catch (Exception e) {}

        String a = p.getProperty("lastFolderPath");
        String b = p.getProperty("lastImagePath");
        String c = p.getProperty("resizeFactor");

        form.updateSetting(a, b, c);

    }


    public boolean getExistence() {
        if (fileExist==1) {
            return true;
        } else {
            return false;
        }
    }

    public void saveConfig(String lastFolderPath, String lastImagePath, String resizeFactor) {

        File theDir = new File(path);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            theDir.mkdir();
        }
        try {
            Properties p = new Properties();
            p.setProperty("lastFolderPath", lastFolderPath);
            p.setProperty("lastImagePath", lastImagePath);
            p.setProperty("resizeFactor", resizeFactor);

            OutputStream out = new FileOutputStream(f);

            p.store(out, null);
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
