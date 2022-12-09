import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Calendar;

public class Escalar extends JFrame implements Runnable{
    private Image fondo;
    private Image buffer;
    private Thread hilo;
    private BufferedImage bufferedImage;
    double[] p1 = {50.0, 50.0};
    double[] p2 = {100.0, 50.0};
    double[] p3 = {100.0, 100.0};
    double[] p4 = {50.0, 100.0};
    double sx = 7.0, sy = 7.0;
    double sxc=1.0,syc=1.0;


    @Override
    public void run() {
        while (true){
            try{
                while(sxc<=sx||syc<=sy) {
                    repaint();
                    hilo.sleep(50);
                    if(sxc<=sx)
                        sxc += 0.02;
                    if(syc<=sy)
                        syc += 0.02;
                    System.out.println("x: "+sxc+" yc: "+syc);

                }
                break;
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }//catch
        }//while
    }//run


    public Escalar(){
        setTitle("Escalar 20110374");
        setResizable(false);
        setSize(700,800);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        hilo =  new Thread(this);
        hilo.start();
    }//constructor


    public static void main(String[] args) {
        new Escalar();
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
        double[][] cuad = {
                {p1[0], p2[0], p3[0], p4[0]},
                {p1[1], p2[1], p3[1], p4[1]},
                {    1,     1,     1,     1},
        };
        double[][] identity = {
                {sxc,   0,   0},
                {  0, syc,   0},
                {  0,   0, 1.0}
        };
        double[][] resAux = matrixMultiplication(identity,cuad);
        int[][] res = new int[resAux.length][resAux[0].length];
        //convertir matriz a int
        for (int i = 0; i<resAux.length;i++)
            for (int j=0;j<resAux[0].length;j++)
                res[i][j] = (int) Math.round(resAux[i][j]);
        System.out.println(Arrays.deepToString(res));
        lineaBresenham(res[0][2], res[1][2], res[0][3], res[1][3],gbuffer,Color.blue);
        lineaBresenham(res[0][3], res[1][3], res[0][0], res[1][0],gbuffer,Color.blue);
        lineaBresenham(res[0][1], res[1][1], res[0][2], res[1][2],gbuffer,Color.blue);
        lineaBresenham(res[0][0], res[1][0], res[0][1], res[1][1],gbuffer,Color.blue);


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
    private double[][] matrixMultiplication(double[][] a, double[][] b){
        if(a[0].length==b.length) {
            System.out.println("Multiplicable");
            double[][] c = new double[a.length][b[0].length];
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
}
