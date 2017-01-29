package com.c3ry.watermarker;

import com.c3ry.watermarker.gui.Form1;

import java.io.*;
import java.util.Properties;

public class settings {

    private final String path = System.getProperty("user.home") + File.separator +".watermarker";
    private final String fileName = "wm_config.properties";
    private final String f = path+File.separator+fileName;

    private int fileExist=0;

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
        } catch (Exception ignored) {}

        String a = p.getProperty("lastFolderPath");
        String b = p.getProperty("lastImagePath");
        String c = p.getProperty("resizeFactor");
        String d = p.getProperty("offsetX");
        String e = p.getProperty("offsetY");
        String f = p.getProperty("position");

        form.updateSetting(a, b, c, d, e, f);

    }


    public boolean getExistence() {
        return fileExist == 1;
    }

    public void saveConfig(String lastFolderPath, String lastImagePath, String resizeFactor, String offsetX, String offsetY, String position) {

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
            p.setProperty("offsetX", offsetX);
            p.setProperty("offsetY", offsetY);
            p.setProperty("position", position);

            OutputStream out = new FileOutputStream(f);

            p.store(out, null);
        }
        catch (Exception e ) {
            e.printStackTrace();
        }
    }
}
