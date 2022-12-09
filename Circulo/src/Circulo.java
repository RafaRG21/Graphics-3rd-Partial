import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static java.awt.Color.red;

public class Circulo extends JFrame {
    static private BufferedImage buffer;
    static private Graphics graPixel;

    public Circulo(){
        setTitle("Circulo Normal 20110374");
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
        new Circulo();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        dibujarCirculo(getWidth()/2,getHeight()/2,100);


    }//paint

    public void dibujarCirculo(int xc, int yc, int r){
        int x,y;
        for(x=xc-r;x<xc+r;x++){
            y = (int) (yc + Math.sqrt(Math.pow(r,2.00) - Math.pow(x-xc,2)));
            putPixel(x,Math.round(y),Color.black);
            System.out.println("UP: "+x+","+Math.round(y));
        }
        for(x=xc-r;x<xc+r;x++){
            y = (int) (yc - Math.sqrt(Math.pow(r,2.00) - Math.pow(x-xc,2)));
            putPixel(x,Math.round(y),Color.black);
            System.out.println("Down: "+x+","+Math.round(y));

        }

    }//dibujarCirculo




}//class
