package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Rombo extends JFrame {
    public ArrayList<String> puntos_dibujados;
    public Image buffer;

    Graphics g2;
    Graphics gbufer;
    Color color;
    float center;
    float [] eje_x;
    float [] eje_y;
    int factor=1;
    int direcion;



    private BufferedImage bufferImage;

    public Rombo(BufferedImage bufferImage, Graphics gbufer, Image buffer, Graphics g2, Color color, float [] eje_x,float [] eje_y,int direcion,int factor) {

        this.bufferImage = bufferImage;
        this.eje_x=eje_x;
        this.eje_y=eje_y;
    this.center=400;
        this.g2=g2;
        this.buffer=buffer;
        this.gbufer=gbufer;
        this.color=color;
        this.factor=factor;
        this.direcion=direcion;


        puntos_dibujados=new ArrayList<>();
    }

    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferImage.setRGB(0, 0, c.getRGB());

        g.drawImage(bufferImage, x, y,null);

    }


    float[][] dibujar()
    {
        float aux[] = new float[2];
        float aX[] = new float[eje_x.length];
        float aY[] = new float[eje_y.length];
        for (int i = 0; i < eje_y.length; i++) {
            aux = multy(eje_x[i], eje_y[i], direcion*factor);
            eje_x[i] = aux[0];
            eje_y[i] = aux[1]; }
        for (int i = 0; i < eje_y.length; i++) {
            aX[i] = eje_x[i] + center;
            aY[i] = eje_y[i] + center; }

        cuadrado(aX, aY, color);


        g2.drawImage(buffer,0,0,null);
        return  new float [][] {eje_x,eje_y};

    }



    public float[] multy(float x, float y, int angulo) {
        double origen[] = {x, y, 1};
        double matriz [][]  = { {Math.cos(Math.toRadians(angulo)), (0 - Math.sin(Math.toRadians(angulo))), 0}, {Math.sin(Math.toRadians(angulo)), Math.cos(Math.toRadians(angulo)), 0},{0, 0, 1}};
        origen[0] = (origen[0] * matriz[0][0]) + (origen[1] * matriz[0][1]) + (origen[2] * matriz[0][2]);
        origen[1] = (origen[0] * matriz[1][0]) + (origen[1] * matriz[1][1]) + (origen[2] * matriz[1][2]);
        origen[2] = (origen[0] * matriz[2][0]) + (origen[1] * matriz[2][1]) + (origen[2] * matriz[2][2]);
        float matrizResultante []= {(float) origen[0], (float) origen[1]};
        return matrizResultante;
    }

    public void cuadrado(float x[], float y[], Color c) {
        int lado = x.length;
        puntos_dibujados.clear();
        for (int i = 0; i < lado - 1; i++) {
            linea(x[i], y[i], x[i + 1], y[i + 1], c);
        }
        linea(x[lado - 1], y[lado - 1], x[0], y[0], color);
        g2.drawImage(buffer,0,0,null);
    }

    public void linea(float x1, float y1, float x2, float y2, Color c) {
        float dx = x2 - x1, dy = y2 - y1, steps;
        float xinc, yinc, x, y;
        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        xinc = dx / steps;
        yinc = dy / steps;
        x = x1;
        y = y1;
        putPixel(Math.round(x), Math.round(y),color,gbufer);
        puntos_dibujados.add(Math.round(x)+","+Math.round(y));
        for (int k = 1; k <= steps; k++) {
            x += xinc;
            y += yinc;
            putPixel(Math.round(x), Math.round(y), color,gbufer);
            puntos_dibujados.add(Math.round(x)+","+Math.round(y));

        }
        g2.drawImage(buffer,0,0,null);
    }


}
