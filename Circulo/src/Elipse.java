import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Elipse extends JFrame {
    static private BufferedImage buffer;
    static private Graphics graPixel;
    int[] origin = {200,200};
    int rx=50,ry=150;
    Color color = new Color(22,174,118);
    public Elipse(){
        setTitle("Elipse 20110374");
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
        new Elipse();
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawElipse(origin[0],origin[1],rx,ry,color);

    }//paint

    public void drawElipse(int xc, int yc, int rx, int ry,Color c){
        int x=xc-rx;
        int y=yc;
        putPixel(x,y,c);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){
            x= (int) (xc + (rx * Math.sin(i)));
            y= (int) (yc + (ry * Math.cos(i)));
            putPixel(x,y,c);

        }//for
    }//drawElipse


}//class