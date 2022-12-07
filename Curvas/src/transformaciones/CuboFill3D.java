package transformaciones;

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author retom
 */
public class CuboFill3D extends JFrame {

    int[][] cuboPuntos = {
            {100, 100, 100},
            {200, 100, 100},
            {200, 200, 100},
            {100, 200, 100},
            {100, 200, 200},
            {200, 200, 200},
            {200, 100, 200},
            {100, 100, 200}
    };
    int[][] cubo;
    int[] p;

    private BufferedImage buffer;
    private Graphics graPixel;

    public CuboFill3D() {
        setTitle("Cubo");
        setSize(550, 600);
        setLayout(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public static void main(String[] args) {
        CuboFill3D c = new CuboFill3D();
        c.show();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        p = new int[]{60, 50, 120};
        dibujarCubo(Color.BLACK, 100, 100, g);
        bordesCubo();

    }

    public void dibujarCubo(Color c, int movx, int movy, Graphics g) {
        int xp = p[0];
        int yp = p[1];
        int zp = p[2];
        int x = 0, y = 0;

        cubo = new int[8][2];

        for (int i = 0; i < 8; i++) {
            x = Math.abs(cuboPuntos[i][0] + xp * (-cuboPuntos[i][2] / zp)+movx);
            y = Math.abs(cuboPuntos[i][1] + yp * (-cuboPuntos[i][2] / zp)+movy);
            //putPixel(x, y, Color.BLUE);
            System.out.println("x " + x + " y " + y);
            cubo[i][0] = x;
            cubo[i][1] = y;

        }


        double dif = cubo[2][0] - cubo[0][0];
        relleno((int) dif, cubo[0][0], cubo[0][1], cubo[2][0], cubo[2][1], g, Color.RED);
        relleno((int) dif, cubo[7][0], cubo[7][1], cubo[5][0], cubo[5][1], g, Color.RED);
        relleno2((int) dif, cubo[0][0], cubo[0][1], cubo[7][0], cubo[7][1], g, Color.RED);
        relleno2((int) dif, cubo[3][0], cubo[3][1], cubo[4][0], cubo[4][1], g, Color.RED);
        //lineaDDA(cubo[3][0], cubo[3][1], cubo[4][0], cubo[4][1], Color.BLUE);


    }

    public void bordesCubo(){

        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 3 ->
                        lineaDDA(cubo[i][0], cubo[i][1], cubo[0][0], cubo[0][1], Color.BLACK);
                case 7 ->
                        lineaDDA(cubo[i][0], cubo[i][1], cubo[4][0], cubo[4][1], Color.BLACK);
                default ->
                        lineaDDA(cubo[i][0], cubo[i][1], cubo[i + 1][0], cubo[i + 1][1],Color.BLACK);
            }
            if (i < 4) {
                lineaDDA(cubo[i][0], cubo[i][1], cubo[7 - i][0], cubo[7 - i][1], Color.BLACK);
            }

        }


    }

    public void relleno2(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaDDA(x1+i, (int) y1, x2+i, (int) y2, c);
        }

    }

    public void relleno(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaDDA(x1, (int) y1 + i, x2, (int) y1 + i, c);
        }

    }


    public void lineaDDA(double x0, double y0, double x1, double y1, Color c) {
        double difx = 0, dify = 0, steps = 0;
        double xinc = 0, yinc = 0;
        double xf = 0, yf = 0;
        difx = x1 - x0;
        dify = y1 - y0;

        if (Math.abs(difx) > Math.abs(dify)) {
            steps = Math.abs(difx);
        } else {
            steps = Math.abs(dify);
        }

        try {
            xinc = difx / steps;
            yinc = (float) dify / steps;
        } catch (Exception e) {
            //h
        };

        xf = x0;
        yf = y0;
        putPixel((int) xf, (int) yf, c);
        for (int i = 1; i <= steps; i++) {
            xf = (xf + xinc);
            yf = (yf + yinc);

            putPixel((int) xf, (int) yf, c);
        }

    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
}