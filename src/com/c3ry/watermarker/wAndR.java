package com.c3ry.watermarker;

import com.c3ry.watermarker.gui.Form1;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class wAndR {
    public void applyWatermark(String folderO, String fileO, String pathW, double r, int position, int offsetX, int offsetY) {


        Form1 f = new Form1();

        String s = File.separator;

        File originale = new File(folderO+ s +fileO);
        File watermark = new File(pathW);

        ImageIcon oIcon = new ImageIcon(originale.getPath());
        ImageIcon wIcon = new ImageIcon(watermark.getPath());

        int oScaledW = scale(oIcon.getIconWidth(), r);
        int oScaledH = scale(oIcon.getIconHeight(), r);

        int posX = 0;
        int posY = 0;

        final int left = offsetX; // offset + 0
        final int center = offsetX + ((oIcon.getIconWidth()/2)-(wIcon.getIconWidth()/2));
        final int right = offsetX + (oIcon.getIconWidth()-wIcon.getIconWidth());

        final int top = offsetY; // offset + 0
        final int middle = offsetY + ((oIcon.getIconHeight()/2)-(wIcon.getIconHeight()/2));
        final int bottom = offsetY + (oIcon.getIconHeight()-wIcon.getIconHeight());

        switch (position) {
            case 1:
                posX = left;
                posY = top;
                break;
            case 2:
                posX = center;
                posY = top;
                break;
            case 3:
                posX = right;
                posY = top;
                break;
            case 4:
                posX = left;
                posY = middle;
                break;
            case 5:
                posX = center;
                posY= middle;
                break;
            case 6:
                posX = right;
                posY = middle;
                break;
            case 7:
                posX = left;
                posY = bottom;
                break;
            case 8:
                posX = center;
                posY = bottom;
                break;
            case 9:
                posX = right;
                posY = bottom;
                break;
        }

        // create BufferedImage object of same width and height as of original image
        BufferedImage bOriginal = new BufferedImage(oIcon.getIconWidth(), oIcon.getIconHeight(), BufferedImage.TYPE_INT_RGB);

        // create graphics object and add original image to it
        Graphics g = bOriginal.getGraphics();
        g.drawImage(oIcon.getImage(), 0, 0, null);
        g.drawImage(wIcon.getImage(), posX, posY, null);

        g.dispose();

        // Resizing process

            BufferedImage outputImage = new BufferedImage(oScaledW, oScaledH, bOriginal.getType());

            Graphics2D g2d = outputImage.createGraphics();
            g2d.drawImage(bOriginal, 0, 0, oScaledW, oScaledH, null);
            g2d.dispose();

        // End Resizing

        File theDir = new File(folderO+ s +"WM");

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            theDir.mkdir();
        }

        File newFile = new File(folderO+ s +"WM"+ s +fileO+"_w.jpg");
        try {
            ImageIO.write(outputImage, "jpg", newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        f.rep();
        System.out.println(newFile.getPath() + " created successfully!");

    }

    private static int scale(int l, double r) {
        return (int) (l*r);
    }


}
