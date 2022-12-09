package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MiVentana extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    int [] centro={300,300};
    int rx=200;
    int ry=100;




    public MiVentana(){
        setTitle("Elipse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
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

        int x=centro[0]-rx;
        int y=centro[1];
        putPixel(x,y,Color.red);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){


            x= (int) (centro[0] + (rx * Math.sin(i)));
            y= (int) (centro[1] + (ry * Math.cos(i)));
            putPixel(x,y,Color.blue);

        }


    }



}

