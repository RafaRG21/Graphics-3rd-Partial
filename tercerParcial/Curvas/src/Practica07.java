import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Practica07  extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    int[] a = {100, 200, 300, 400, 500};
    int[] b = {100, 200, 300, 400, 500};
    public static void main(String[] args) {
        new Practica07();
    }

    Practica07(){
        setTitle("Practica 07 20110374");
        setSize(700,700);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }//constructor

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawMalla(a,b,g,Color.BLUE,Color.RED);
    }
    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }


    public void drawMalla(int[] a, int[] b,Graphics g,Color c1, Color c2){
        int tam = a.length;
        //lineas X
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam - 1; j++) {
                lineaBresenham(a[i],b[j],a[i],b[j+1],g,c1);
            }
        }
        //lineas y
        for (int i = 0; i < tam-1; i++) {
            for (int j = 0; j < tam; j++) {
                lineaBresenham(a[i],b[j],a[i+1],b[j],g,c1);
            }
        }
        //puntos
        for (int i = 0; i < tam; i++) {
            for (int j = 0; j < tam; j++) {
                putPixel( a[i],b[j],c2);
                putPixel(a[i],b[j]+1, c2);
                putPixel( a[i]+1,b[j],c2);
                putPixel(a[i]-1,b[j],c2);
                putPixel(a[i],b[j]-1, c2);
            }
        }

    }//drawMalla

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
