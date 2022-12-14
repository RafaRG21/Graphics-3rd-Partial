package transformaciones;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Traslacion extends JPanel implements Runnable{
    double fps = 60.0;
    double time = 1000/fps;
    private Thread hilo;
    private BufferedImage bufferedImage;

    double coordenades[][] = {
            {100, 200, 200, 100, 100, 200, 200, 100},
            {100, 100, 200, 200, 100, 100, 200, 200},
            {100, 100, 100, 100, 200, 200, 200, 200},
            {  1,   1,   1,   1,   1,   1,   1,   1}
    };
    private double xMov = 160, yMov = 160, zMov = 160;
    private double tx = 1, ty = 1, tz = 1;


    @Override
    public void run() {
        while (true){
            try{
                while(tx<=xMov||ty<=yMov) {
                    repaint();
                    hilo.sleep((int) time);
                    if(tx<=xMov)
                        tx++;
                    if(ty<=yMov)
                        ty++;
                    System.out.println("x: "+tx+" yc: "+ty);

                }
                break;
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }//catch
        }//while
    }//run


    public Traslacion(){

        setSize(800,800);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        hilo =  new Thread(this);
        hilo.start();
    }//constructor


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Traslacion 20110374");
        frame.setResizable(false);

        Traslacion cube = new Traslacion();
        cube.setDoubleBuffered(true);
        frame.add(cube);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }///Main


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        update(g);
    }//paintComponent
    @Override
    public Dimension getPreferredSize() {

        return new Dimension(700, 700);

    }//getPreferrefSize

    public void update(Graphics g){
        //Coordenadas

        double[][] identity = {
                {1,0,0,tx},
                {0,1,0,ty},
                {0,0,1,tz},
                {0,0,0,1}
        };
        int numhilos = Runtime.getRuntime().availableProcessors();
        double[][] res = MatrixMultiplication.multiplicarExecutorService(identity,coordenades,numhilos);
        drawCube(res,new double[]{30,50,120},300,300,g,Color.blue);



    }//update
    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);

    }//putPixel


    public void drawCube(double[][] puntos,double[] direccion,double origenX,double origenY,Graphics g, Color c){
        double [][] result;
        result = multiplicarMatriz(puntos,direccion,origenX,origenY);
        int rest = (int) (result[0][1] - result[0][0]);
        fill(rest, result[0][0], result[1][0], result[0][1], result[1][1], g, Color.RED);

        rest = (int) (result[0][3] - result[0][2]);
        fill( rest, result[0][2], result[1][2], result[0][3], result[1][3], g, Color.RED);
        fill2(rest, result[0][0], result[1][0], result[0][2], result[1][2], g, Color.RED);
        line(result,g,Color.BLACK,Color.blue,Color.RED,Color.darkGray,Color.GREEN);

        //Frente
        lineaBresenham(result[0][0], result[1][0], result[0][1], result[1][1], g, c);//AB
        lineaBresenham(result[0][1], result[1][1], result[0][2], result[1][2], g,c);////BD
        lineaBresenham(result[0][2], result[1][2], result[0][3], result[1][3], g, c);//CD
        lineaBresenham(result[0][3], result[1][3], result[0][0], result[1][0], g, c);//AC
        relleno((int) (result[1][3]-result[1][0]),result[0][0],result[1][0],result[0][3],result[1][3],g,c);
        //Atras
        lineaBresenham(result[0][4], result[1][4], result[0][5], result[1][5], g, c);//EF
        lineaBresenham(result[0][5], result[1][5], result[0][6], result[1][6], g, c);//FH
        lineaBresenham(result[0][6], result[1][6], result[0][7], result[1][7], g, c);//GH
        lineaBresenham(result[0][7], result[1][7], result[0][4], result[1][4], g, c);//EG
        //lados
        lineaBresenham(result[0][0], result[1][0], result[0][4], result[1][4], g, c);//AE
        lineaBresenham(result[0][1], result[1][1], result[0][5], result[1][5], g, c);//BE
        lineaBresenham(result[0][2], result[1][2], result[0][6], result[1][6], g, c);//DH
        lineaBresenham(result[0][3], result[1][3], result[0][7], result[1][7], g, c); //CG

    }//drawCube


    public double[][] multiplicarMatriz(double[][] matriz, double[] direccion,double origenX,double origenY){
        double [][] result = new double[2][matriz[0].length];
        for(int i=0;i<matriz[0].length;i++){
            result[0][i] = (matriz[0][i]+ direccion[0] * (-matriz[2][i]/direccion[2])) + origenX;
            result[1][i] = (matriz[1][i]+ direccion[1] * (-matriz[2][i]/direccion[2])) + origenY;

            /*if(result[0][i]<0)
                result[0][i] -=1;
            if(result[1][i]<0)
                result[1][i] -= 1;
*/
            result[1][i] = Math.abs(result[1][i]);
            result[0][i] = Math.abs(result[0][i]);

        }
        return  result;
    }//multiplicarMatriz
    public void fill2(int rest, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= rest; i++) {
            lineaBresenham(x1 + i, (int) y1, x2 + i, (int) y2, g, c);
        }

    }

    public void fill(int rest, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= rest; i++) {
            lineaBresenham(x1, (int) y1 + i, x2, (int) y1 + i, g, c);
        }

    }
    public void relleno(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaBresenham(x1, (int) y1 + i, x2, (int) y1 + i, g, c);
        }

    }




    private void lineaBresenham(double xini, double yini, double xfin, double yfin, Graphics g, Color c){
        int x0 = (int) Math.round(xini);
        int y0 = (int) Math.round(yini);
        int x1 = (int) Math.round(xfin);
        int y1 = (int) Math.round(yfin);
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

        /* se cicla hasta llegar al extremo de la l??nea */
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
    private  void line(double[][] result,Graphics g, Color c,Color c1,Color c2,Color c3,Color c4) {
        filling(result, g, c1, c2, c3, c4);
    }
    private void filling(double[][] result,Graphics g,Color c1,Color c2,Color c3,Color c4){
        g.setColor(c1);
        g.fillPolygon(new int[]{(int) Math.round(result[0][0]), (int) Math.round(result[0][1]),
                (int) Math.round(result[0][2]),(int) Math.round(result[0][3])},new int[]{(int) Math.round(result[1][0]), (int) Math.round(result[1][1]),
                (int) Math.round(result[1][2]),(int) Math.round(result[1][3])},4);


        g.setColor(c3);
        g.fillPolygon(new int[]{
                (int) Math.round(result[0][0]), (int) Math.round(result[0][4]), (int) Math.round(result[0][7]),(int) Math.round(result[0][3])},new int[]{
                (int) Math.round(result[1][0]), (int) Math.round(result[1][4]), (int) Math.round(result[1][7]),(int) Math.round(result[1][3])},4);

        g.setColor(c4);
        g.fillPolygon(new int[]{
                (int) Math.round(result[0][0]), (int) Math.round(result[0][1]), (int) Math.round(result[0][5]),(int) Math.round(result[0][4])},new int[]{
                (int) Math.round(result[1][0]), (int) Math.round(result[1][1]), (int) Math.round(result[1][5]),(int) Math.round(result[1][4])},4);


        /* g.setColor(c2);
        g.fillPolygon(new int[]{
                (int) Math.round(result[0][4]), (int) Math.round(result[0][5]), (int) Math.round(result[0][6]),(int) Math.round(result[0][7])},new int[]{
                (int) Math.round(result[1][4]), (int) Math.round(result[1][5]), (int) Math.round(result[1][6]),(int) Math.round(result[1][7])},4);
*/

    }//fill


}
