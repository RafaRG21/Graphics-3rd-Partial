import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Inundacion extends JFrame {
    private Image fondo;
    private Image buffer;
    private BufferedImage bufferedImage;
    int[] p1 = {150, 150};
    int[] p2 = {200, 150};
    int[] p3 = {150, 200};
    int[] p4 = {200, 200};
    Robot r;
    Point P1 = new Point(150, 150);
    Point P2 = new Point(200, 150);
    Point P3 = new Point(150, 200);
    Point P4 = new Point(200, 200);
    Color old_color, new_color;
    List<Point> puntoFigura;


    public Inundacion() {
        setTitle("Inundacion 20110374");
        setResizable(false);
        setSize(700, 700);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        puntoFigura = new ArrayList<>();
        try {
            r=new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }//constructor


    public static void main(String[] args) {
        new Inundacion();
    }


    @Override
    public void paint(Graphics g) {
        if (fondo == null) {
            fondo = createImage(getWidth(), getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0, 0, getWidth(), getHeight());
            //  g.drawLine(P1.x,P1.y,P2.x,P2.y);
            //  g.drawLine(P3.x,P3.y,P4.x,P4.y);
            // g.drawLine(P1.x,P1.y,P3.x,P3.y);
        }

        update(g);
    }//paint

    public void update(Graphics g) {
        g.setClip(0, 0, getWidth(), getHeight());

        //regenerar la imagen de fondo
        //crear la imagen estatica
        buffer = createImage(getWidth(), getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0, 0, getWidth(), getHeight());
        gbuffer.drawImage(fondo, 0, 0, this);
        lineaBresenham(P1.x, P1.y, P2.x, P2.y,gbuffer,Color.blue);
        lineaBresenham(P3.x, P3.y, P4.x, P4.y,gbuffer,Color.blue);
        lineaBresenham(P1.x, P1.y, P3.x, P3.y,gbuffer,Color.blue);
        lineaBresenham(P2.x, P2.y, P4.x, P4.y,gbuffer,Color.blue);
        Color col = new Color(50,50,50);
        Point p = new Point(300,300);
        Point p2 = new Point(155,155);
        dibujarCirculoPuntoMedio(300,300,35,Color.blue,gbuffer);

        floodFill(p,gbuffer,col);
        floodFill(p2,gbuffer,col);

        g.drawImage(buffer, 0, 0, this); //doble buffer

    }//update

    public void dibujarCirculoPuntoMedio(int xc, int yc,int r,Color c,Graphics g) {
        // Punto inicial del círculo
        int x = 0;
        int y = r;
        // Cálcular el parámetro inicial de decisión
        int pk = 1-r;

        // verificar el pk para determinar las posiciones de pixel siguientes
        while (x<=y) {
            putPixel(xc+x,yc+y,c,g);
            puntoFigura.add(new Point(xc+x, yc+y));
            putPixel(xc-x,yc-y,c,g);
            puntoFigura.add(new Point(xc-x, yc-y));
            putPixel(xc+x,yc-y,c,g);
            puntoFigura.add(new Point(xc+x, yc-y));
            putPixel(xc-x,yc+y,c,g);
            puntoFigura.add(new Point(xc-x, yc+y));
            putPixel(xc+y,yc+x,c,g);
            puntoFigura.add(new Point(xc+y, yc+x));
            putPixel(xc-y,yc-x,c,g);
            puntoFigura.add(new Point(xc-y, yc-x));
            putPixel(xc+y,yc-x,c,g);
            puntoFigura.add(new Point(xc+y, yc-x));
            putPixel(xc-y,yc+x,c,g);
            puntoFigura.add(new Point(xc-y, yc+x));


            if (pk<0){
                pk+=2*(x+1)+1;
                x++;
            } // pk>=0
            else {
                pk+=2*(x+1)+1 - 2*(y-1);
                x++;
                y--;
            }
        }//while
    }//dibujarCirculoPuntoMedio


    public void dibujarCirculoPolar(int xc, int yc, int r,Color c, Graphics g){
        // Angulo de variación
        double t = Math.toRadians(0);
        // Punto inicial
        int x = r;
        int y = 0;
        // Mientras el angulo no exceda a 2PI dibujar puntos
        while (t <= 2*Math.PI) {
            putPixel(x + xc, y + yc,c,g);
            puntoFigura.add(new Point(x+xc,y+yc));

            // Incrementar el ángulo
            t=t+Math.toRadians(0.5);
            // Cálcular los valores x e y
            double xd = r * Math.cos(t);
            x = (int) Math.round(xd);
            double yd = r * Math.sin(t);
            y = (int) yd;
        }

    }//dibujarCirculoPolar



    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferedImage.setRGB(0, 0, c.getRGB());
        g.drawImage(bufferedImage, x, y, this);
    }//putPixel


    public void floodFill( Point punto, Graphics g,Color c){

        if(!puntoFigura.contains(punto) ) {
            putPixel(punto.x,punto.y,c,g);
            puntoFigura.add(punto);

            //System.out.println(String.valueOf(punto[0])+","+String.valueOf(punto[1]));
            floodFill( new Point(punto.x,punto.y-1),g,c);
            floodFill( new Point(punto.x+1,punto.y),g,c);
            floodFill( new Point(punto.x,punto.y+1),g,c);
            floodFill( new Point(punto.x-1,punto.y),g,c);

        }
        return;


    }//floodFill

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
        putPixel(xk, yk, c, g);
        puntoFigura.add(new Point(xk,yk));
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
                putPixel(xk, yk, c, g);
                puntoFigura.add(new Point(xk,yk));

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
                putPixel(xk, yk, c, g);
                puntoFigura.add(new Point(xk,yk));

            }
        }
    }//lineaBresenham
}

