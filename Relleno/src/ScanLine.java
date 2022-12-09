import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class ScanLine extends JFrame {
    private Image fondo;
    private Image buffer;
    private Thread hilo;
    private BufferedImage bufferedImage;
    int[] p1 = {150, 150};
    int[] p2 = {250, 150};
    int[] p3 = {150, 250};
    int[] p4 = {250, 250};
    int[][] lienzo;
    int xmin, xmax = 0, ymin, ymax = 0;
    Point P1 = new Point(150,150);
    Point P2 = new Point(200,150);
    Point P3 = new Point(150,200);
    Point P4 = new Point(200,200);


    public ScanLine(){
        setTitle("Rotacion 20110374");
        setResizable(false);
        setSize(700,700);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        lienzo = new int[getWidth()][getHeight()];
        xmin = getWidth();
        ymin = getHeight();
        for (int i=0; i<lienzo.length; i++)
            for (int j=0; j<lienzo[0].length;j++)
                lienzo[i][j] = 0;
    }//constructor



    public static void main(String[] args) {
        new ScanLine();
    }


    @Override
    public void paint(Graphics g) {
        if(fondo == null){
            fondo  = createImage(getWidth(),getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
          //  g.drawLine(P1.x,P1.y,P2.x,P2.y);
          //  g.drawLine(P3.x,P3.y,P4.x,P4.y);
           // g.drawLine(P1.x,P1.y,P3.x,P3.y);


        }

        update(g);
    }//paint
    public void update(Graphics g){
        g.setClip(0,0,getWidth(),getHeight());

        //regenerar la imagen de fondo
        //crear la imagen estatica
        buffer = createImage(getWidth(),getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0,0,getWidth(),getHeight());
        gbuffer.drawImage(fondo,0,0,this);

        //Coordenadas
        int[][] cuad = {
                {p1[0], p2[0], p3[0], p4[0]},
                {p1[1], p2[1], p3[1], p4[1]},
        };
        int[][] identity = {
                {1,0,0},
                {0,1,0},
                {0,0,1}
        };
        int[][] res = matrixMultiplication(identity,cuad);
        System.out.println(Arrays.deepToString(res));

        lineaBresenham(P1.x, P1.y, P2.x, P2.y,gbuffer,Color.blue);
        lineaBresenham(P3.x, P3.y, P4.x, P4.y,gbuffer,Color.blue);
        lineaBresenham(P1.x, P1.y, P3.x, P3.y,gbuffer,Color.blue);
        lineaBresenham(P2.x, P2.y, P4.x, P4.y,gbuffer,Color.blue);

        rellenoScanLine(cuad,gbuffer,Color.MAGENTA);
        /*
        lineaBresenham(P1.x, P1.y, P2.x, P2.y,gbuffer,Color.blue);
        lineaBresenham(P3.x, P3.y, P4.x, P4.y,gbuffer,Color.blue);
        lineaBresenham(P1.x, P1.y, P3.x, P3.y,gbuffer,Color.blue);
        lineaBresenham(P2.x, P2.y, P4.x, P4.y,gbuffer,Color.blue);
        */


        g.drawImage(buffer,0,0,this); //doble buffer

    }//update


    private void limites(int[][] puntos){
        //Punto minimo x de las coordenadas del poligono
        for (int i = 0; i<puntos[0].length;i++){
            if(puntos[0][i] < xmin)
                xmin = puntos[0][i];

            if(puntos[0][i] > xmax)
                xmax = puntos[0][i];
        //Punto minimo y de las coordenadas del poligono

            if(puntos[1][i] < ymin)
                ymin = puntos[1][i];

            if(puntos[1][i] > ymax)
                ymax = puntos[1][i];
        }

    }//limites

    private void rellenoScanLine(int[][] figura,Graphics g,Color c){
            for (int i = 0; i <= p4[1] - p1[1]; i++) {
                lineaBresenham(p1[0], p1[1] + i, p2[0], p2[1] + i,g,c);
            }


    }//rellenoScanLine

    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);
        lienzo[x][y] = 1;

    }//putPixel


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
        putPixel(xk,yk,c,g);
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
                putPixel(xk,yk,c,g);
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
                putPixel(xk,yk,c,g);
            }
        }
    }//lineaBresenham




    private int[][] matrixMultiplication(int[][] a, int[][] b){
        if(a[0].length==b.length) {
            System.out.println("Multiplicable");
            int[][] c = new int[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < b.length; k++)
                        c[i][j] += a[i][k] * b[k][j];
                }
            }
            return c;
        } else {
            System.out.println("No Multiplicable");
            return a;
        }
    }//matrixMultiplication
}//ScanLine
