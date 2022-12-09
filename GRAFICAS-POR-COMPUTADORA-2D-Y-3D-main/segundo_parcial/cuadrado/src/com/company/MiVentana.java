package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MiVentana extends JFrame implements Runnable {

    private Image fondo;
    private Image buffer;

    private BufferedImage bufferImage;
    private Graphics graPixel;


    int[] punto1={100,100};
    int[] punto2={400,600};




    public MiVentana(){
        setTitle("Cuadrado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(null);
        bufferImage= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);






    }


    public void putPixel(int x, int y, Color c,Graphics g) {
        bufferImage.setRGB(0, 0, c.getRGB());

        g.drawImage(bufferImage, x, y, this);
    }

    public void paint (Graphics g) {
        if(fondo==null){
            fondo=createImage(getWidth(),getHeight());
            Graphics gfondo=fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
        }

        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.setClip(0,0,getWidth(),getHeight());
        buffer=createImage(getWidth(),getHeight());
        Graphics gbufer=buffer.getGraphics();
        gbufer.setClip(0,0,getWidth(),getHeight());
        gbufer.drawImage(fondo,0,0,this);




        int [][] resultado=cuadrado(punto1,punto2);



        Bresenham(resultado[0][0],resultado[0][1],resultado[1][0],resultado[1][1],gbufer);
        Bresenham(resultado[1][0],resultado[1][1],resultado[2][0],resultado[2][1],gbufer);
        Bresenham(resultado[2][0],resultado[2][1],resultado[3][0],resultado[3][1],gbufer);
        Bresenham(resultado[3][0],resultado[3][1],resultado[0][0],resultado[0][1],gbufer);

        g.drawImage(buffer,0,0,this);

    }



    public void Bresenham(int x0, int y0, int x1, int y1,Graphics g){
        int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);
        /* determinar que punto usar para empezar, cual para terminar */
        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        }
        else
            stepy = 1;
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        }
        else
            stepx = 1;
        x = x0;
        y = y0;
        putPixel(x,y,Color.red,g);

        /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            p = 2*dy - dx;
            incE = 2*dy;
            incNE = 2*(dy-dx);
            while (x != x1){
                x = x + stepx;
                if (p < 0){
                    p = p + incE;
                }
                else {
                    y = y + stepy;
                    p = p + incNE;
                }
                putPixel(x,y,Color.red,g);

            }
        }
        else{
            p = 2*dx - dy;
            incE = 2*dx;
            incNE = 2*(dx-dy);
            while (y != y1){
                y = y + stepy;
                if (p < 0){
                    p = p + incE;
                }
                else {
                    x = x + stepx;
                    p = p + incNE;
                }
                putPixel(x,y,Color.red,g);


            }
        }


    }

    public int [][] cuadrado(int [] punto1,int []punto2){

        return new int[][]{punto1,new int[]{punto2[0],punto1[1]},punto2,new int[] {punto1[0],punto2[1]} };
    }

    @Override
    public void run() {

      //  while (incX<maxX || incY<mayY){
        while (true){

            try {



                repaint();
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

