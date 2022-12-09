import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Practica03 extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public static void main(String[] args) {
        new Practica03();
    }
    Practica03(){
        setTitle("Practica 03 20110374");
        setSize(800,600);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawCurve(100,g,Color.BLUE);
    }


    public void drawCurve(int cantP, Graphics g, Color c) {
        double puntos = 350 / cantP;
        double x1 = 0,x2 = 0,y1 = 0;

        for (double y2=0; y2<=300; y2+=puntos){
            x2 = 400 + y2*Math.cos(4*y2);
            System.out.println("x: "+x2);
            System.out.println("y: "+y2);
            lineaBresenham((int) x1, (int) y1, (int) x2, (int) y2,g,c);
            x1 = x2;
            y1 = y2;

        }

    }//drawCurve

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
