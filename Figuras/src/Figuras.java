import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Figuras extends JFrame{
    private Image background;
    private Image buffer;
    private BufferedImage bufferedImage;


    public Figuras(){
        setTitle("Figuras 20110374");
        setResizable(false);
        setSize(800,800);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);

    }//constructor


    public static void main(String[] args) {
        new Figuras();
    }///Main


    @Override
    public void paint(Graphics g) {
        if(background == null){
            background  = createImage(getWidth(),getHeight());
            Graphics gfondo = background.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
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
        gbuffer.drawImage(background,0,0,this);

        //Rectangulo
        int[][] coordenades1 = {
                {230, 600},
                {430,700}
                };
        realizarCuadrado(coordenades1,Color.blue,gbuffer);
        int[][] coordenades2 = {
                {255,625},
                {405,675}
        };
        realizarCuadrado(coordenades2,Color.blue,gbuffer);

        //Circulo
        dibujarCirculoPolar(120,600,100,Color.RED,gbuffer);
        dibujarCirculoPolar(120,600,75,Color.RED,gbuffer);
        dibujarCirculoPolar(120,600,50,Color.RED,gbuffer);
        dibujarCirculoPolar(120,600,25,Color.RED,gbuffer);

        //elipse
        drawElipse(620,620,170,80,Color.MAGENTA,gbuffer);
        drawElipse(620,620,130,50,Color.MAGENTA,gbuffer);
        drawElipse(620,620,90,30,Color.MAGENTA,gbuffer);
        drawElipse(620,620,70,10,Color.MAGENTA,gbuffer);
        //lineas
        lineaBresenham(20,100,150,200,gbuffer,Color.darkGray); //der - izq
        lineaBresenham(100,100, 200,100,gbuffer,Color.darkGray);//der - izq
        lineaBresenham(300,100,200,200,gbuffer,Color.darkGray); //izq - der
        lineaBresenham(600,100,400,100,gbuffer,Color.darkGray);//izq - der
        g.drawImage(buffer,0,0,this); //doble buffer

    }//update


    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);
    }//putPixel


    private void realizarCuadrado(int[][] coordenades, Color c, Graphics g){
        lineaBresenham(coordenades[0][0],coordenades[0][1],coordenades[1][0],coordenades[0][1],g,c); //up
        lineaBresenham(coordenades[0][0],coordenades[1][1],coordenades[1][0],coordenades[1][1],g,c); //down
        lineaBresenham(coordenades[0][0],coordenades[0][1],coordenades[0][0],coordenades[1][1],g,c); //left
        lineaBresenham(coordenades[1][0],coordenades[0][1],coordenades[1][0],coordenades[1][1],g,c); //right
    }


    public void dibujarCirculoPolar(int xc, int yc, int r,Color c, Graphics g){
        // Angulo de variación
        double t = Math.toRadians(0);
        // Punto inicial
        int x = r;
        int y = 0;
        // Mientras el angulo no exceda a 2PI dibujar puntos
        while (t <= 2*Math.PI) {
            putPixel(x + xc, y + yc,c,g);
            // Incrementar el ángulo
            t=t+Math.toRadians(0.5);
            // Cálcular los valores x e y
            double xd = r * Math.cos(t);
            x = (int) Math.round(xd);
            double yd = r * Math.sin(t);
            y = (int) yd;
        }

    }//dibujarCirculoPolar

    public void drawElipse(int xc, int yc, int rx, int ry,Color c, Graphics g){
        int x=xc-rx;
        int y=yc;
        putPixel(x,y,c,g);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){
            x= (int) (xc + (rx * Math.sin(i)));
            y= (int) (yc + (ry * Math.cos(i)));
            putPixel(x,y,c, g);

        }//for
    }//drawElipse


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


    private void rellenoScanLine(int[][] figura,Color c,Graphics g){
        for (int i = 0; i <= figura[1][3] - figura[1][0]; i++) {
            lineaBresenham(figura[0][0], figura[1][0] + i, figura[0][1], figura[1][1] + i,g,c);
        }
    }//rellenoScanLine


}