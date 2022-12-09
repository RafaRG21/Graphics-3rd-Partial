import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.red;

public class PuntoMedio extends JFrame {
    static private BufferedImage buffer;
    static private Graphics graPixel;

    public PuntoMedio(){
        setTitle("Circulo Punto Medio 20110374");
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
        new PuntoMedio();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dibujarCirculoPuntoMedio(getWidth()/2,getHeight()/2,100);


    }//paint


    public void dibujarCirculoPuntoMedio(int xc, int yc,int r) {
        // Punto inicial del círculo
        int x = 0;
        int y = r;
        // Cálcular el parámetro inicial de decisión
        int pk = 1-r;

        // verificar el pk para determinar las posiciones de pixel siguientes
        while (x<=y) {
            System.out.println("(x,y)= "+x+","+y+" pk="+pk);
            putPixel(xc+x,yc+y,Color.GREEN);
            putPixel(xc-x,yc-y,Color.GREEN);
            putPixel(xc+x,yc-y,Color.GREEN);
            putPixel(xc-x,yc+y,Color.GREEN);
            putPixel(xc+y,yc+x,Color.GREEN);
            putPixel(xc-y,yc-x,Color.GREEN);
            putPixel(xc+y,yc-x,Color.GREEN);
            putPixel(xc-y,yc+x,Color.GREEN);

            if (pk<0){
                pk+=2*(x+1)+1;
                x++;
            } // pk>=0
            else {
                pk+=2*(x+1)+1 - 2*(y-1);
                x++;
                y--;
            }
        }//while
    }//dibujarCirculoPuntoMedio


}//class

