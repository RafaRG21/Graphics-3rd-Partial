package Line;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
        
public class Line extends JFrame{

    static private BufferedImage buffer;
    static private Graphics graPixel;
    
     public Line(){

        setTitle("Line");
        setSize(300,300);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public static void main(String[] args){
        Line ventana = new Line();
    }

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        //putPixel(50,50,red);
        drawLine(80,80,100,100);
    }
    
    public void drawLine(float x1, float y1, float x2, float y2){
        float X = x2-x1;
        float Y = y2-y1;
        float m = Y/X;
        for(int i=(int)x1; i<(int)x2; i++){
            putPixel(i,(int)(m*(x2-i)+y2),red);
        }           
    }
    
}
