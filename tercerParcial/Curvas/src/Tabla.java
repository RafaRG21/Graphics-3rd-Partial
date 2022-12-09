import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Tabla extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;

    public static void main(String[] args) {
        new Tabla();
    }

    Tabla() {
        setTitle("Tabla Resorte 20110374");
        setSize(800, 600);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawFigure(200,100, g, Color.BLUE);
    }


    public void drawFigure(int xpos, int ypos, Graphics g, Color c){
        double x1 = xpos, y1= ypos, x2,y2,xopc1=0,xopc2=0,yopc1=0,yopc2=0;
        double inc = Math.PI / 500;
        for (double t=0; t<50; t+=inc){
            x2 = (t-3*Math.sin(t))*10+xpos;
            y2 = (4 - 3*Math.cos(t))*10 +ypos;
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

    private void lineaBresenham(int x0, int y0, int x1, int y1, Graphics g, Color c) {
        int dx = (x1 - x0);
        int dy = (y1 - y0);
        int stepY, stepX, pk;
        int xk = x0;
        int yk = y0;
        // determinar punto de partida y fin
        if (dy < 0) {
            dy = -dy;
            stepY = -1;
        } else
            stepY = 1;
        if (dx < 0) {
            dx = -dx;
            stepX = -1;
        } else
            stepX = 1;
        putPixel(xk, yk, c);
        /* se cicla hasta llegar al extremo de la línea */
        if (dx > dy) {
            pk = 2 * dy - dx;
            while (xk != x1) {
                xk += stepX;
                if (pk < 0)
                    pk += 2 * dy;
                else {
                    yk += stepY;
                    pk += 2 * (dy - dx);
                }
                putPixel(xk, yk, c);
            }
        } else {
            pk = 2 * dx - dy;
            while (yk != y1) {
                yk += stepY;
                if (pk < 0)
                    pk += 2 * dx;
                else {
                    xk += stepX;
                    pk += 2 * (dx - dy);
                }
                putPixel(xk, yk, c);
            }
        }
    }//lineaBresenham
}