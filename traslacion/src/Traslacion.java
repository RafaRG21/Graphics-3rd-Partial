import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Traslacion extends JFrame implements Runnable{
    private Image fondo;
    private Image buffer;
    private Thread hilo;
    private BufferedImage bufferedImage;
    int[] p1 = {150, 150};
    int[] p2 = {200, 150};
    int[] p3 = {150, 200};
    int[] p4 = {200, 200};
    Point P1 = new Point(150, 150);
    Point P2 = new Point(200, 150);
    Point P3 = new Point(150, 200);
    Point P4 = new Point(200, 200);
    int dx = 160, dy = 140, sx = 2, sy = 2;
    int xc = 1, yc = 1, sxc=0,syc=0;
    List<Point> puntoFigura;


    @Override
    public void run() {
        while (true){
            try{
                while(xc<=dx||yc<=dy) {
                    repaint();
                    hilo.sleep(10);
                    if(xc<=dx)
                        xc++;
                    if(yc<=dy)
                        yc++;
                    System.out.println("x: "+xc+" yc: "+yc);

                }
                break;
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }//catch
        }//while
    }//run


    public Traslacion(){
        setTitle("Traslacion 20110374");
        setResizable(false);
        setSize(700,800);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        puntoFigura = new ArrayList<>();
        hilo =  new Thread(this);
        hilo.start();
    }//constructor


    public static void main(String[] args) {
        new Traslacion();
    }///Main


    @Override
    public void paint(Graphics g) {
        //g.drawLine(100,100,200,200);
        if(fondo == null){
            fondo  = createImage(getWidth(),getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());

            // gfondo.drawImage(fondito.getImage(),0,0,this);
           // gfondo.setColor(Color.black);
            //gfondo.drawOval((getWidth()-100)/2,(getHeight()-100)/2,200,200);

        }

        update(g);
    }//paint

    public void update(Graphics g){
        int x = 50,y=650;
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
                {    1,     1,     1,     1},
        };
        int[][] identity = {
                {1,0,xc},
                {0,1,yc},
                {0,0,1}
        };
        int[][] res = matrixMultiplication(identity,cuad);
        Point p = new Point(res[0][2]+2,res[0][2]+2);
        Point p2 = new Point(155,155);
        System.out.println("Res1 "+res[0][2]+"Res2 "+res[1][2]);

        lineaBresenham(res[0][2], res[1][2], res[0][3], res[1][3],gbuffer,Color.blue); // abajo
        lineaBresenham(res[0][3], res[1][3], res[0][1], res[1][1],gbuffer,Color.blue); //izq
        lineaBresenham(res[0][0], res[1][0], res[0][2], res[1][2],gbuffer,Color.blue); // der
        lineaBresenham(res[0][0], res[1][0], res[0][1], res[1][1],gbuffer,Color.blue);//superior
        floodFill(p,gbuffer,Color.blue);
        puntoFigura.clear();
        //rellenoScanLine(res,gbuffer,Color.magenta);



        /*
        //Cuadrado
        lineaBresenham(x+xc,y-yc,x+300+xc,y-yc,gbuffer,Color.black);
        lineaBresenham(x+300+xc,y-yc,x+300+xc,y-300-yc,gbuffer,Color.black);
        lineaBresenham(x+300+xc,y-300-yc,x+xc,y-300-yc,gbuffer,Color.black);
        lineaBresenham(x+xc,y-300-yc,x+xc,y-yc,gbuffer,Color.black);
        */
        g.drawImage(buffer,0,0,this); //doble buffer

    }//update
    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);

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
        puntoFigura.add(new Point(xk,yk));

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
                puntoFigura.add(new Point(xk,yk));

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
                puntoFigura.add(new Point(xk,yk));

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
    private void rellenoScanLine(int[][] figura,Graphics g,Color c){


        for (int i = 0; i <= figura[1][3] - figura[1][0]; i++) {
            lineaBresenham(figura[0][0], figura[1][0] + i, figura[0][1], figura[1][1] + i,g,c);
        }




    }//rellenoScanLine
}
