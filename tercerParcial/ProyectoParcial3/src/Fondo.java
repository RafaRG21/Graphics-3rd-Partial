
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Fondo extends JPanel{
    private BufferedImage bufferedImage;


    public Fondo(){
        setSize(900, 600);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        setVisible(true);

    }//constructor


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //g.drawLine(100,100,200,200);;
        background(g);
    }//paintComponent


    @Override
    public Dimension getPreferredSize() {

        return new Dimension(900, 600);

    }//getPreferrefSize


    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);

    }//putPixel


    public void drawCube(double[][] puntos,double[] direccion,double origenX,double origenY,Graphics g, Color c){
        double [][] result;
        result = multiplicarMatriz(puntos,direccion,origenX,origenY);
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
    public void relleno(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaBresenham(x1, (int) y1 + i, x2, (int) y1 + i, g, c);
        }

    }//relleno
    public void background(Graphics g){
        //Squidward house
        Color squidwardHouse = new Color(77, 121, 168);
        rellenoScanLine(new int[][]{{350,200},{500,450}},g,squidwardHouse);
        rellenoScanLine(new int[][]{{325,250},{350,350}},g,squidwardHouse);
        rellenoScanLine(new int[][]{{500,250},{525,350}},g,squidwardHouse);
        Color squidwardHouseFace = new Color(4,18,49);
        rellenoScanLine(new int[][]{{360,225},{490,250}},g,squidwardHouseFace);
        rellenoScanLine(new int[][]{{400,225},{450,350}},g,squidwardHouseFace);
        Color squidwardWindows = new Color(109,140,230);

        Color squidwardDoor = new Color(172,127,73);
        rellenoScanLine(new int[][]{{400,400},{450,450}},g,squidwardDoor);
        //Patrick's housen
        Color patrickStick = new Color(164, 157, 61);

        Color patrickHouse = new Color(50, 23, 28);

        //Bob's house
        Color bobHouse = new Color(212,97,26);
        rellenoScanLine(new int[][]{{650,250},{825,450}},g,bobHouse);
        Color bobRoof = new Color(55,112,20);
        rellenoScanLine(new int[][]{{630,200},{845,250}},g,bobRoof);


        Color bobDoor = new Color(96,124,195);
        rellenoScanLine(new int[][]{{715,400},{765,450}},g,bobDoor);

        //street
        Color street = new Color(56,80,80);
        rellenoScanLine(new int[][]{{0,450},{getWidth(),getHeight()}},g,street);
        //Sun
        Color sun = new Color(249, 231, 42);

    }//background
    private void rellenoScanLine(int[][] figura, Graphics g, Color c) {
        int [][] figure = new int[][]{
                {figura[0][0],figura[1][0],figura[0][0],figura[1][0]},
                {figura[0][1],figura[0][1],figura[1][1],figura[1][1]},
                {1,1,1,1}
        };

        for (int i = 0; i <= figure[1][3] - figure[1][0]; i++) {
            lineaBresenham(figure[0][0], figure[1][0] + i, figure[0][1], figure[1][1] + i, g, c);
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



}