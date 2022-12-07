import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Practica06 extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public static void main(String[] args) {
        new Practica06();
    }//main

    Practica06(){
        setTitle("Practica 06 20110374");
        setSize(700,600);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }//constructor

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFigure(300,300,g,Color.RED);
    }

    public void drawFigure(int xpos, int ypos, Graphics g, Color c){
        double x1 = xpos, y1= ypos, x2,y2,xopc1=0,xopc2=0,yopc1=0,yopc2=0;
        double inc = Math.PI / 700;
        for (double t=0; t<14*Math.PI; t+=inc){
            x2 = ((17)*Math.cos(t) + (7)*Math.cos(2.4285*t))*10 +xpos;
            y2 = ((17)*Math.sin(t) - (7)*Math.sin(2.4285*t))*10 +ypos;
            System.out.println("x: "+x2);
            System.out.println("y: "+y2);
            if(t!=0)
                lineaBresenham((int) x1, (int) y1, (int) x2, (int) y2,g,c);
            x1 = x2;
            y1 = y2;

        }
    }


    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    private void lineaBresenham(int x0, int y0, int x1, int y1, Graphics g, Color c){
        int dx = (x1 - x0);
        int dy = (y1 - y0);
        int stepY, stepX,pk;
        int xk = x0;
        int yk = y0;
        // determinar punto de partida y fin
        if (dy < 0) {
            dy = -dy;
            stepY = -1;
        }
        else
            stepY = 1;
        if (dx < 0) {
            dx = -dx; stepX = -1;
        }
        else
            stepX = 1;
        putPixel(xk,yk,c);
        /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            pk = 2*dy - dx;
            while (xk != x1){
                xk += stepX;
                if (pk < 0)
                    pk += 2*dy;
                else {
                    yk += stepY;
                    pk += 2*(dy-dx);
                }
                putPixel(xk,yk,c);
            }
        }
        else{
            pk = 2*dx - dy;
            while (yk != y1){
                yk += stepY;
                if (pk < 0)
                    pk += 2*dx;
                else {
                    xk += stepX;
                    pk += 2*(dx-dy);
                }
                putPixel(xk,yk,c);
            }
        }
    }//lineaBresenham

}