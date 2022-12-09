package Pixel;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class Pixel extends JFrame {

    static private BufferedImage buffer;
    static private Graphics graPixel;
    
     public Pixel(){

        setTitle("Pixel");
        setSize(300,300);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public static void main(String[] args){
        Pixel ventana = new Pixel();
    }

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        putPixel(100,100,red);
    }
    
}
