package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MiVentana extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    int [] centro={300,300};

    int radio=200;



    public MiVentana(){
        setTitle("Circulo Basico");
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

        int x=centro[0]-radio;
        int y=centro[1];
        putPixel(x,y,Color.red);
        for (int i=radio-(2*radio);i<=radio;i++){

            if(i<0){
                x=centro[0]+Math.abs(i);
            }else{
                x=centro[0]-Math.abs(i);
            }
            y= (int) Math.round((centro[1] - Math.sqrt((Math.pow(radio,2)-(Math.pow((x-centro[0]),2))))));
            putPixel(x,y,Color.red);
            y= (int) Math.round((centro[1] + Math.sqrt((Math.pow(radio,2)-(Math.pow((x-centro[0]),2))))));
            putPixel(x,y,Color.red);
        }


    }



}

