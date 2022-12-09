//Ruiz Gudino Jose Rafael

import java.awt.Color;
import static java.awt.Color.red;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
        
public class Linea extends JFrame{

    static private BufferedImage buffer;
    static private Graphics graPixel;
    
     public Linea(){

        setTitle("Linea Recta 20110374");
        setSize(500,500);
        setLayout(null);
        setVisible(true);

        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();

    }

    public static void main(String[] args){
        Linea ventana = new Linea();
    }

    public void dibujarLinea(float x1, float y1, float x2, float y2){
        float restaX = x2-x1, restaY = y2-y1;
        float m = restaY/restaX;
        for(int i=(int)x1; i<(int)x2; i++){
            putPixel(i,(int)(m*(x2-i)+y2),red);
        }           
    }//dibujarLinea

    @Override
    public void paint(Graphics g){
        super.paint(g);
        dibujarLinea(150,200,300,300);
    }//paint
    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }///putPixel
    
}//class
