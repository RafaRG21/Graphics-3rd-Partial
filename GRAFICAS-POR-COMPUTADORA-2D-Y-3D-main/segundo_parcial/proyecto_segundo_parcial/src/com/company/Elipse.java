package com.company;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Elipse {

    public ArrayList<String> puntos_dibujados;
    public Image buffer;
    int  radiox;
    int radioy;
    int [] centro;
    Graphics g2;
    Graphics gbufer;
    private BufferedImage bufferImage;

    public Elipse( Image buffer, int radiox, int radioy, int[] centro, Graphics g2, Graphics gbufer, BufferedImage bufferImage) {
        this.puntos_dibujados = puntos_dibujados;
        this.buffer = buffer;
        this.radiox = radiox;
        this.radioy = radioy;
        this.centro = centro;
        this.g2 = g2;
        this.gbufer = gbufer;
        this.bufferImage = bufferImage;

        puntos_dibujados=new ArrayList<>();
    }

    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferImage.setRGB(0, 0, c.getRGB());

        g.drawImage(bufferImage, x, y,null);
    }
    public void dibujar(){

        int x=centro[0]-radiox;
        int y=centro[1];
        puntos_dibujados.clear();
        putPixel(x,y,Color.red,gbufer);
        puntos_dibujados.add(x+","+y);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){


            x= (int) (centro[0] + (radiox * Math.sin(i)));
            y= (int) (centro[1] + (radioy * Math.cos(i)));
            putPixel(x,y,Color.white,gbufer);
            puntos_dibujados.add(x+","+y);

        }

    }
}
