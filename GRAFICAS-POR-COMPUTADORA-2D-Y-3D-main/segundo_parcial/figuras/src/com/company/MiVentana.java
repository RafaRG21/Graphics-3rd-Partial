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




    public MiVentana(){
        setTitle("Figuras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 600);
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


        //lineas
        Bresenham(50,50,200,200,gbufer);
        Bresenham(200,100,400,100,gbufer);
        Bresenham(400,200,550,50,gbufer);
        //Bresenham(600,100,800,100,gbufer);
        Bresenham(800,100,600,100,gbufer);

        //circulos

        circulo(50,new int []{100,400},gbufer);
        circulo(40,new int []{100,400},gbufer);
        circulo(30,new int []{100,400},gbufer);
        circulo(20,new int []{100,400},gbufer);
        circulo(10,new int []{100,400},gbufer);

        //cuadrados

        cuadrado(new  int[][]{new int[]{200,350},new int[]{400,450}},gbufer);
        cuadrado(new  int[][]{new int[]{220,370},new int[]{380,430}},gbufer);
        cuadrado(new  int[][]{new int[]{240,390},new int[]{360,410}},gbufer);

        //elipses

        elipse(100,50,new int[]{550,400},gbufer);
        elipse(60,30,new int[]{550,400},gbufer);
        elipse(30,15,new int[]{550,400},gbufer);
        elipse(15,7,new int[]{550,400},gbufer);





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

    public void cuadrado(int [][]puntos, Graphics gbufer){

        int [] [] punto=new int[][]{puntos[0],new int[]{puntos[1][0],puntos[0][1]},puntos[1],new int[] {puntos[0][0],puntos[1][1]} };

        Bresenham(punto[0][0],punto[0][1],punto[1][0],punto[1][1],gbufer);
        Bresenham(punto[1][0],punto[1][1],punto[2][0],punto[2][1],gbufer);
        Bresenham(punto[2][0],punto[2][1],punto[3][0],punto[3][1],gbufer);
        Bresenham(punto[3][0],punto[3][1],punto[0][0],punto[0][1],gbufer);

    }
    public void elipse(int rx, int ry, int [] centro,Graphics gbufer){

        int x=centro[0]-rx;
        int y=centro[1];
        putPixel(x,y,Color.red,gbufer);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){


            x= (int) (centro[0] + (rx * Math.sin(i)));
            y= (int) (centro[1] + (ry * Math.cos(i)));
            putPixel(x,y,Color.red,gbufer);

        }
    }
    public void circulo(int radio, int [] centro,Graphics gbufer) {

        int x = radio;
        int y = 0;

        putPixel(x + centro[0],y + centro[1],Color.red,gbufer);

        int incremento = 1 - radio;
        while (x > y) {
            y++;
            if (incremento <= 0)
                incremento = incremento + 2 * y + 1;
            else {
                x--;
                incremento = incremento + 2 * y - 2 * x + 1;
            }

            //CUADRANTE C
            putPixel(x+centro[0],y+centro[1],Color.red,gbufer);
            //CUADRANTE F
            putPixel(-x+centro[0],y+centro[1],Color.red,gbufer);
            //CUADRANTE B
            putPixel(x+centro[0],-y+centro[1],Color.red,gbufer);
            //CUADRANTE G
            putPixel(-x+centro[0],-y+centro[1],Color.red,gbufer);


            if (x != y) {

                //CUADRANTE A
                putPixel(y+centro[0],-x+centro[1],Color.red,gbufer);

                //CUADRANTE D
                putPixel(y+centro[0],x+centro[1],Color.red,gbufer);
                //CUADRANTE E
                putPixel(-y+centro[0],x+centro[1],Color.red,gbufer);
                //CUADRANTE H
                putPixel(-y+centro[0],-x+centro[1],Color.red,gbufer);


            }
        }
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

