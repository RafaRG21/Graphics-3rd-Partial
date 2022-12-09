import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Rotacion extends JFrame implements Runnable {

    private BufferedImage buffer;
    private Image fondo;
    private Thread hilo;
    public float[] ancho, largo;

    public Rotacion() {
        setTitle("ROTACI[ON");
        setSize(700, 700);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        hilo = new Thread();
        hilo.start();
        ancho = new float[]{-100, -100, 100, 100};
        largo = new float[]{-100, 100, 100, -100};
        setVisible(true);
    }

    public void paint(Graphics g) {
        fondo = createImage(getWidth(), getHeight());
        Graphics gfondo = fondo.getGraphics();
        gfondo.setClip(0, 0, getWidth(), getHeight());
        try {
            rotar(ancho, largo, 2, getHeight()/2, Color.BLACK);
            repaint();
            hilo.sleep(20);
        } catch (InterruptedException ex) {
            Logger.getLogger(Rotacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        g.drawImage(fondo, 0, 0, this);
    }

    public void rotar(float x[],float y[],int angulo,float limit,Color c) {
        float aux[] = new float[2];
        float aX[] = new float[x.length];
        float aY[] = new float[y.length];
        for (int i = 0; i < x.length; i++) {
            aux = Multiplicacion(x[i], y[i], angulo);
            x[i] = aux[0];
            y[i] = aux[1]; }
        for (int i = 0; i < x.length; i++) {
            aX[i] = x[i] + limit;
            aY[i] = y[i] + limit; }
        cuadrado(aX, aY, c);
    }

    public float[] Multiplicacion(float x, float y, int angulo) {
        double origen[] = {x, y, 1};
        double matriz [][]  = { {Math.cos(Math.toRadians(angulo)), (0 - Math.sin(Math.toRadians(angulo))), 0}, {Math.sin(Math.toRadians(angulo)), Math.cos(Math.toRadians(angulo)), 0},{0, 0, 1}};
        origen[0] = (origen[0] * matriz[0][0]) + (origen[1] * matriz[0][1]) + (origen[2] * matriz[0][2]);
        origen[1] = (origen[0] * matriz[1][0]) + (origen[1] * matriz[1][1]) + (origen[2] * matriz[1][2]);
        origen[2] = (origen[0] * matriz[2][0]) + (origen[1] * matriz[2][1]) + (origen[2] * matriz[2][2]);
        float matrizResultante []= {(float) origen[0], (float) origen[1]};
        return matrizResultante;
    }

    public void cuadrado(float x[], float y[], Color c) {
        int lado = x.length;
        for (int i = 0; i < lado - 1; i++) {
            linea(x[i], y[i], x[i + 1], y[i + 1], c);
        }
        linea(x[lado - 1], y[lado - 1], x[0], y[0], c);
    }

    public void linea(float x1, float y1, float x2, float y2, Color c) {
        float dx = x2 - x1, dy = y2 - y1, steps;
        float xinc, yinc, x, y;
        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
        } else {
            steps = Math.abs(dy);
        }
        xinc = dx / steps;
        yinc = dy / steps;
        x = x1;
        y = y1;
        putPixel(Math.round(x), Math.round(y), c);
        for (int k = 1; k <= steps; k++) {
            x += xinc;
            y += yinc;
            putPixel(Math.round(x), Math.round(y), c);
        }
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        fondo.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void run() {

    }

    public static void main(String[] args) {
        new Rotacion();
    }
}