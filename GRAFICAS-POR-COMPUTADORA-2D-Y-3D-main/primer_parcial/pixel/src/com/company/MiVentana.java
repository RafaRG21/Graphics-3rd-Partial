package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.red;

    public class MiVentana extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    public  MiVentana(){
        setTitle("Ventana");
        setSize(500, 500);
        setLayout(null);
        buffer= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        graPixel=(Graphics2D)buffer.createGraphics();

    }


    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());

        this.getGraphics().drawImage(buffer, x, y, this);
    }

        public void paint (Graphics g) {
            super.paint(g);




            for (int x=0;x<10000;x++){
                putPixel(x, 100, red);
            }
            recta(100,300,200,300);



        }

        public void recta(float x1, float x2,float y1,float y2){

        float diferenciax=(x2-x1);
        float diferenciay=(y2-y1);

            float pendiente=diferenciay/diferenciax;

        for(int i=(int)x1; i<(int)x2; i++){
            putPixel(i,(int)((pendiente*(x2-i))+y2) , red);
        }


        }
}

