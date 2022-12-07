package transformaciones;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import static java.lang.Math.round;

/**
 *
 * @author retom
 */
public class Escalar3D extends JFrame implements Runnable {

    private Image fondo;
    private Image buffer;
    private Thread hilo;
    private BufferedImage bufferedImage;



    double difx = 0, dify = 0, steps = 0;
    double xinc = 0, yinc = 0;
    double xf = 0, yf = 0;

    double xc = 0, yc = 0;
    int sx = 2, sy = 2;

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

    @Override
    public void run() {
        while (true) {
            try {
                while (xc <= sx || yc <= sy) {
                    repaint();
                    hilo.sleep(20);
                    xc = xc + .01;
                    yc = yc + .01;
                }

                break;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Escalar3D() {
        setTitle("Cuadrado moviendose");
        setResizable(false);
        setSize(650, 650);
        setLayout(null);
        setVisible(true);
        //setBackground(Color.BLACK);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        hilo = new Thread(this);
        hilo.start();
    }

    public static void main(String[] args) {
        new Escalar3D();
    }

    @Override
    public void paint(Graphics g) {
        if (fondo == null) {
            fondo = createImage(getWidth(), getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0, 0, getWidth(), getHeight());

        }
        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.setClip(0, 0, getWidth(), getHeight());
        buffer = createImage(getWidth(), getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0, 0, getWidth(), getHeight());
        gbuffer.drawImage(fondo, 0, 0, this);

        p = new int[]{30, 50, 120};
        dibujarCubo(Color.BLUE, 100, 100, gbuffer);

        g.drawImage(buffer, 0, 0, this);

    }

    public void dibujarCubo(Color c, int movx, int movy, Graphics g) {
        int xp = p[0];
        int yp = p[1];
        int zp = p[2];
        int x = 0, y = 0;

        cubo = new int[8][2];

        for (int i = 0; i < 8; i++) {
            x = Math.abs(cuboPuntos[i][0] + xp * (-cuboPuntos[i][2] / zp) + movx);
            y = Math.abs(cuboPuntos[i][1] + yp * (-cuboPuntos[i][2] / zp) + movy);
            cubo[i][0] = x;
            cubo[i][1] = y;
        }

        double[][] rect = {
                {cubo[0][0], cubo[1][0], cubo[3][0], cubo[2][0]},
                {cubo[0][1], cubo[1][1], cubo[2][1], cubo[3][1]},
                {1, 1, 1, 1},};

        double[][] rect2 = {
                {cubo[4][0], cubo[5][0], cubo[7][0], cubo[6][0]},
                {cubo[4][1], cubo[5][1], cubo[6][1], cubo[7][1]},
                {1, 1, 1, 1},};


        double[][] rect3 = {
                {cubo[0][0], cubo[1][0], cubo[7][0], cubo[6][0]},
                {cubo[0][1], cubo[1][1], cubo[7][1], cubo[6][1]},
                {1, 1, 1, 1},};

        double[][] rect4 = {
                {cubo[2][0], cubo[3][0], cubo[5][0], cubo[4][0]},
                {cubo[2][1], cubo[3][1], cubo[5][1], cubo[4][1]},
                {1, 1, 1, 1},};

        double[][] identidad = {
                {xc, 0, 0},
                {0, yc, 0},
                {0, 0, 1},};

        double[][] resultado;
        double dif;

        resultado = multiplicar(identidad, rect);
        dif = resultado[0][1] - resultado[0][0];
        relleno((int) dif, resultado[0][0], resultado[1][0], resultado[0][1], resultado[1][1], g, Color.RED);

        resultado = multiplicar(identidad, rect3);
        dif = resultado[0][3] - resultado[0][2];
        relleno((int) dif, resultado[0][2], resultado[1][2], resultado[0][3], resultado[1][3], g, Color.RED);
        relleno2((int) dif, resultado[0][0], resultado[1][0], resultado[0][2], resultado[1][2], g, Color.RED);


        resultado = multiplicar(identidad, rect4);
        dif = resultado[0][1] - resultado[0][3];
        relleno2((int) dif, resultado[0][1], resultado[1][1], resultado[0][3], resultado[1][3], g, Color.RED);



        //PintarLineas
        resultado = multiplicar(identidad, rect);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][1], resultado[1][1], g, Color.BLACK);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][2], resultado[1][2], g, Color.BLACK);
        lineaDDA(resultado[0][1], resultado[1][1], resultado[0][3], resultado[1][3], g, Color.BLACK);

        resultado = multiplicar(identidad, rect2);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][1], resultado[1][1], g, Color.BLACK);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][2], resultado[1][2], g, Color.BLACK);
        lineaDDA(resultado[0][1], resultado[1][1], resultado[0][3], resultado[1][3], g, Color.BLACK);

        resultado = multiplicar(identidad, rect3);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][2], resultado[1][2], g, Color.BLACK); // this
        lineaDDA(resultado[0][2], resultado[1][2], resultado[0][3], resultado[1][3], g, Color.BLACK); //this
        lineaDDA(resultado[0][1], resultado[1][1], resultado[0][3], resultado[1][3], g, Color.BLACK);

        resultado = multiplicar(identidad, rect4);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][1], resultado[1][1], g, Color.BLACK);
        lineaDDA(resultado[0][0], resultado[1][0], resultado[0][2], resultado[1][2], g, Color.BLACK);
        lineaDDA(resultado[0][1], resultado[1][1], resultado[0][3], resultado[1][3], g, Color.BLACK); //THIS


    }

    public void bordesCubo(Graphics g) {

        for (int i = 0; i < 8; i++) {
            switch (i) {
                case 3 ->
                        lineaDDA(cubo[i][0], cubo[i][1], cubo[0][0], cubo[0][1], g, Color.BLACK);
                case 7 ->
                        lineaDDA(cubo[i][0], cubo[i][1], cubo[4][0], cubo[4][1], g, Color.BLACK);
                default ->
                        lineaDDA(cubo[i][0], cubo[i][1], cubo[i + 1][0], cubo[i + 1][1], g, Color.BLACK);
            }
            if (i < 4) {
                lineaDDA(cubo[i][0], cubo[i][1], cubo[7 - i][0], cubo[7 - i][1], g, Color.BLACK);
            }

        }

    }

    public void relleno2(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaDDA(x1 + i, (int) y1, x2 + i, (int) y2, g, c);
        }

    }

    public void relleno(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaDDA(x1, (int) y1 + i, x2, (int) y1 + i, g, c);
        }

    }

    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferedImage.setRGB(0, 0, c.getRGB());
        g.drawImage(bufferedImage, x, y, this);

    }

    public void lineaDDA(double x0, double y0, double x1, double y1, Graphics g, Color c) {
        difx = x1 - x0;
        dify = y1 - y0;
        //System.out.println("difx "+difx+" dify "+ dify);
        if (Math.abs(difx) > Math.abs(dify)) {
            steps = Math.abs(difx);
        } else {
            steps = Math.abs(dify);
        }
        //System.out.println("steps "+steps);
        try {
            xinc = difx / steps;
            yinc = (float) dify / steps;
        } catch (Exception e) {

        }
        // System.out.println("incx "+xinc+" incy "+ yinc);
        xf = x0;
        yf = y0;
        putPixel((int) xf, (int) yf, c, g);
        for (int i = 1; i <= steps; i++) {
            xf = (xf + xinc);
            yf = (yf + yinc);
            // System.out.println("x "+xf+" y "+ yf);
            putPixel((int) xf, (int) yf, c, g);
        }

    }

    public static double[][] multiplicar(double[][] a, double[][] b) {
        double[][] c = new double[a.length][b[0].length];
        // se comprueba si las matrices se pueden multiplicar
        if (a[0].length == b.length) {
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < a[0].length; k++) {
                        // aquí se multiplica la matriz
                        c[i][j] += a[i][k] * b[k][j];

                    }
                }
            }
        }

        return c;
    }

}