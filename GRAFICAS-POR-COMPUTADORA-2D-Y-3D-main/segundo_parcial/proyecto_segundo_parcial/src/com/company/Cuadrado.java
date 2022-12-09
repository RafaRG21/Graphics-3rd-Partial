package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;


public class Cuadrado extends JFrame {
    public ArrayList<String> puntos_dibujados;
    public Image buffer;
    int [] centro;
    int size;
    int [][] puntos;
    Graphics g2;
    Graphics gbufer;
    Color color;


    private BufferedImage bufferImage;

    public Cuadrado(BufferedImage bufferImage, int [] centro,int size, Graphics gbufer, Image buffer, Graphics g2, Color color) {

        this.bufferImage = bufferImage;

        this.centro=centro;
        this.size=size;
        this.puntos=new int[][]{{0,0},{0,0},{0,0},{0,0}};
        puntos[0]=new int[]{centro[0]-size,centro[1]-size};
        puntos[1]=new int[]{centro[0]+size,centro[1]-size};
        puntos[2]=new int[]{centro[0]+size,centro[1]+size};
        puntos[3]=new int[]{centro[0]-size,centro[1]+size};

        this.g2=g2;
        this.buffer=buffer;
        this.gbufer=gbufer;
        this.color=color;


        puntos_dibujados=new ArrayList<>();
    }

    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferImage.setRGB(0, 0, c.getRGB());

        g.drawImage(bufferImage, x, y,null);
    }


    void dibujar()
    {

        Bresenham(puntos[0][0],puntos[0][1],puntos[1][0],puntos[1][1],gbufer);
        Bresenham(puntos[1][0],puntos[1][1],puntos[2][0],puntos[2][1],gbufer);
        Bresenham(puntos[2][0],puntos[2][1],puntos[3][0],puntos[3][1],gbufer);
        Bresenham(puntos[3][0],puntos[3][1],puntos[0][0],puntos[0][1],gbufer);
        g2.drawImage(buffer,0,0,null);

    }

    public static int[][] multiply(double[][] a, int[][] b) {
        int[][] c = new int[b.length][a[0].length];
        // se comprueba si las matrices se pueden multiplicar

        if (a[0].length == b[0].length) {

            for (int i=0;i<b.length;i++){

                for(int z=0;z<a.length;z++){
                    int aux=0;
                    for(int j=0;j<b[0].length;j++){
                        aux+=b[i][j]*a[z][j];
                    }

                    c[i][z]=aux;
                }
            }
        }
        return c;
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
        putPixel(x,y,color,g);
        puntos_dibujados.add(x+","+y);

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
                putPixel(x,y,color,g);
                puntos_dibujados.add(x+","+y);

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
                putPixel(x,y,color,g);
                puntos_dibujados.add(x+","+y);


            }
        }


    }



    public void relleno(){

        int []centro={puntos[0][0]+((puntos[1][0]-puntos[0][0])/2),puntos[0][1]+((puntos[2][1]-puntos[0][1])/2)};
        relleno(centro);
        gbufer.drawImage(buffer,0,0,null);

    }

    public void relleno( int [] punto){

        if(!puntos_dibujados.contains(String.valueOf(punto[0])+","+String.valueOf(punto[1])) ) {
            putPixel(punto[0],punto[1],color,gbufer);
            puntos_dibujados.add(String.valueOf(punto[0])+","+String.valueOf(punto[1]));
            relleno( new int[]{punto[0], punto[1]-1});
            relleno( new int[]{punto[0]+1, punto[1]});
            relleno( new int[]{punto[0], punto[1]+1});
            relleno( new int[]{punto[0]-1, punto[1]});

        }
        return;

    }


}
