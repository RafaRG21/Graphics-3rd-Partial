import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cubo extends JFrame {
    private BufferedImage buffer;
    private Graphics graPixel;
    private float[][] coordenadas = {
            {100,100,100}, //A
            {200,100,100}, //B
            {200,200,100}, //C
            {100,200,100}, //D
            {100,200,200}, //E
            {200,200,200}, //F
            {200,100,200}, //G
            {100,100,200} //H
    };
    public static void main(String[] args) {
        new Cubo();
    }

    Cubo() {
        setTitle("Cubo 20110374");
        setSize(800, 600);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //drawFigure(200,100, g, Color.BLUE);
        drawCube(coordenadas,new float[]{50.0f,10.0f,30.0f},300,200,g,Color.BLUE);
        drawCube(coordenadas,new float[]{30.0f,50.0f,120.0f},200,200,g,Color.BLUE);
        drawCube(coordenadas,new float[]{60.0f,10.0f,100.0f},400,400,g,Color.BLUE);


    }



    public float[][] multiplicarMatriz(float[][] matriz, float[] direccion,int origenX,int origenY){
        float [][] result = new float[matriz.length][2];
        for(int i=0;i<matriz.length;i++){
                result[i][0] = (matriz[i][0]+ direccion[0] * (-matriz[i][2]/direccion[2])) + origenX;
                result[i][1] = (matriz[i][1]+ direccion[1] * (-matriz[i][2]/direccion[2])) + origenY;

                if(result[i][0]<0)
                    result[i][0] -=1.0f;
                if(result[i][1]<0)
                    result[i][1] -= 1.0f;

                result[i][1] = Math.abs(result[i][1]);
                result[i][0] = Math.abs(result[i][0]);

        }
        return  result;
    }
    public void drawCube(float[][] puntos,float[] direccion,int origenX,int origenY,Graphics g, Color c){
        float [][] result;
        result = multiplicarMatriz(puntos,direccion,origenX,origenY);
        lineaBresenham(Math.round(result[0][0]), Math.round(result[0][1]), Math.round(result[1][0]), Math.round(result[1][1]),g,c);//AB
        lineaBresenham(Math.round(result[3][0]), Math.round(result[3][1]), Math.round(result[2][0]), Math.round(result[2][1]),g,c);//dc
        lineaBresenham(Math.round(result[0][0]), Math.round(result[0][1]), Math.round(result[3][0]), Math.round(result[3][1]),g,c);//AD
        lineaBresenham(Math.round(result[1][0]), Math.round(result[1][1]), Math.round(result[2][0]), Math.round(result[2][1]),g,c);//CB

        lineaBresenham(Math.round(result[4][0]), Math.round(result[4][1]), Math.round(result[5][0]), Math.round(result[5][1]),g,c);//EF
        lineaBresenham(Math.round(result[7][0]), Math.round(result[7][1]), Math.round(result[6][0]), Math.round(result[6][1]),g,c);//HG
        lineaBresenham(Math.round(result[4][0]), Math.round(result[4][1]), Math.round(result[7][0]), Math.round(result[7][1]),g,c);//EH
        lineaBresenham(Math.round(result[5][0]), Math.round(result[5][1]), Math.round(result[6][0]), Math.round(result[6][1]),g,c);//FG

        lineaBresenham(Math.round(result[0][0]), Math.round(result[0][1]), Math.round(result[7][0]), Math.round(result[7][1]),g,c);//AH
        lineaBresenham(Math.round(result[1][0]), Math.round(result[1][1]), Math.round(result[6][0]), Math.round(result[6][1]),g,c);//BG
        lineaBresenham(Math.round(result[2][0]), Math.round(result[2][1]), Math.round(result[5][0]), Math.round(result[5][1]),g,c);//CF
        lineaBresenham(Math.round(result[3][0]), Math.round(result[3][1]), Math.round(result[4][0]), Math.round(result[4][1]),g,c);//DE

    }//drawCube
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