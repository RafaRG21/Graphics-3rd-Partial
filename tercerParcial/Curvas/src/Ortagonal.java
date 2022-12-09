import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Ortagonal extends JFrame {
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
        new Ortagonal();
    }

    Ortagonal() {
        setTitle("Ortagonal 20110374");
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
        //drawCube(coordenadas,new float[]{50.0f,10.0f,30.0f},300,200,g,Color.BLUE);
       drawCube(coordenadas,new float[]{0f,0f,120.0f},200,200,g,Color.BLUE);
        drawCube(coordenadas,new float[]{0f,0f,100.0f},500,300,g,Color.BLUE);


    }



    public float[][] multiplicarMatriz(float[][] matriz, float[] direccion,int origenX,int origenY){
        float [][] cube = new float[matriz.length][2];
        for(int i=0;i<matriz.length;i++){
            cube[i][0] = (matriz[i][0]+ direccion[0] * (-matriz[i][2]/direccion[2])) + origenX;
            cube[i][1] = (matriz[i][1]+ direccion[1] * (-matriz[i][2]/direccion[2])) + origenY;

            if(cube[i][0]<0)
                cube[i][0] -=1.0f;
            if(cube[i][1]<0)
                cube[i][1] -= 1.0f;

            cube[i][1] = Math.abs(cube[i][1]);
            cube[i][0] = Math.abs(cube[i][0]);

        }
        return  cube;
    }
    public void drawCube(float[][] puntos,float[] direccion,int origenX,int origenY,Graphics g, Color c){
        float [][] cube1, cube2, cube3, cube4;
        cube1 = multiplicarMatriz(puntos,direccion,origenX,origenY);
        cube2 = multiplicarMatriz(puntos,direccion,origenX,origenY);
        cube3 = multiplicarMatriz(puntos,direccion,origenX,origenY);
        cube4 = multiplicarMatriz(puntos,direccion,origenX,origenY);
        for(int i=0; i<8; i++){
            cube2[i][1] -= 100.0f;
            System.out.println("antes: "+cube3[i][1]);
            cube3[i][1] += 100.0f;
            System.out.println("despues: "+cube3[i][1]);
            cube4[i][0] += 100.0f;
        }
        drawFigure(cube1,g,c);
        drawFigure(cube2,g,c);
        drawFigure(cube3,g,c);
        drawFigure(cube4,g,c);

    }//drawCube
    public void drawFigure(float[][] cube, Graphics g, Color c){
        lineaBresenham(Math.round(cube[0][0]), Math.round(cube[0][1]), Math.round(cube[1][0]), Math.round(cube[1][1]),g,c);//AB
        lineaBresenham(Math.round(cube[3][0]), Math.round(cube[3][1]), Math.round(cube[2][0]), Math.round(cube[2][1]),g,c);//dc
        lineaBresenham(Math.round(cube[0][0]), Math.round(cube[0][1]), Math.round(cube[3][0]), Math.round(cube[3][1]),g,c);//AD
        lineaBresenham(Math.round(cube[1][0]), Math.round(cube[1][1]), Math.round(cube[2][0]), Math.round(cube[2][1]),g,c);//CB

        lineaBresenham(Math.round(cube[4][0]), Math.round(cube[4][1]), Math.round(cube[5][0]), Math.round(cube[5][1]),g,c);//EF
        lineaBresenham(Math.round(cube[7][0]), Math.round(cube[7][1]), Math.round(cube[6][0]), Math.round(cube[6][1]),g,c);//HG
        lineaBresenham(Math.round(cube[4][0]), Math.round(cube[4][1]), Math.round(cube[7][0]), Math.round(cube[7][1]),g,c);//EH
        lineaBresenham(Math.round(cube[5][0]), Math.round(cube[5][1]), Math.round(cube[6][0]), Math.round(cube[6][1]),g,c);//FG

        lineaBresenham(Math.round(cube[0][0]), Math.round(cube[0][1]), Math.round(cube[7][0]), Math.round(cube[7][1]),g,c);//AH
        lineaBresenham(Math.round(cube[1][0]), Math.round(cube[1][1]), Math.round(cube[6][0]), Math.round(cube[6][1]),g,c);//BG
        lineaBresenham(Math.round(cube[2][0]), Math.round(cube[2][1]), Math.round(cube[5][0]), Math.round(cube[5][1]),g,c);//CF
        lineaBresenham(Math.round(cube[3][0]), Math.round(cube[3][1]), Math.round(cube[4][0]), Math.round(cube[4][1]),g,c);//DE

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