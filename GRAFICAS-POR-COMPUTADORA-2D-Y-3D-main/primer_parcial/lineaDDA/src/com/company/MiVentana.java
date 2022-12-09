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


        Bresenham(100,100,0,0);



/*


*/
    }

    public void dda(int x1, int x2,int y1,int y2){

        float diferenciax=(x2-x1);
        float diferenciay=(y2-y1);
        int steps=0;

        float pendiente=diferenciay/diferenciax;

        if(Math.abs(diferenciax)>Math.abs(diferenciay)){
            steps= (int) Math.abs(diferenciax);
        }else{
            steps= (int) Math.abs(diferenciax);
        }
            int x=x1;
            int y=y1;

            putPixel(x,y,red);

            for (int i=0;i<steps;i++){
                x+=Math.round(diferenciax/steps);
                y+=Math.round(diferenciay/steps);
                putPixel(x,y,red);

                System.out.println("pinto x="+x+" y="+y);
            }



    }



    public void Bresenham(int x0, int y0,int x1,int y1){
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
        putPixel(x,y,red);
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
                putPixel(x,y,red);
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
                putPixel(x,y,red);
                System.out.println("x:"+x+" y:"+y);
            }
        }


    }
}

