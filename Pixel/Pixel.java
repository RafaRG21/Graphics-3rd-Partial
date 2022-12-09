package Pixel;

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
//Ruiz Gudino Jose Rafael 20110374
public class Pixel extends JFrame {

    static private BufferedImage buffer;
    static private Graphics graPixel;
    
     public Pixel(){

        setTitle("Pixel");
        setSize(500,500);
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
        putPixel(100,101,red);
        putPixel(101,101,red);
        putPixel(101,100,red);

    }
    
}
