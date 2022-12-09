import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CirculoPolar extends JFrame {
    static private BufferedImage buffer;
    static private Graphics graPixel;

    public CirculoPolar(){
        setTitle("Circulo Coordenadas Polares 20110374");
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }//Constructor

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }//putPixel

    public static void main(String[] args) {
        new CirculoPolar();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dibujarCirculoPolar(200,200,100);


    }//paint


    public void dibujarCirculoPolar(int xc, int yc, int r){
        // Angulo de variación
        double t = Math.toRadians(0);
        // Punto inicial
        int x = r;
        int y = 0;
        // Mientras el angulo no exceda a 2PI dibujar puntos
        while (t <= 2*Math.PI) {
            putPixel(x + xc, y + yc,Color.BLUE);
            // Incrementar el ángulo
            t=t+Math.toRadians(0.5);
            // Cálcular los valores x e y
            double xd = r * Math.cos(t);
            x = (int) Math.round(xd);
            double yd = r * Math.sin(t);
            y = (int) yd;
        }

    }//dibujarCirculoPolar




}//class
