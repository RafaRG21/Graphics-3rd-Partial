import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//Ruiz Gudiño Jose Rafael 20110374
public class dda extends JFrame {
    private BufferedImage buffer;
    dda(){
        setTitle("Linea DDA");
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        buffer =  new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
    }//constructor

    public static void main(String[] args) {
        new dda();
    }

    public void putPixel(int x, int y, Color c){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    @Override
    public void paint(Graphics g) {
        int w = getWidth(), h = getHeight();
        super.paint(g);
        ddaLine(w/2+3,w/2+1,w/2+64,w/2+32);
        ddaLine(w/2+3,w/2+3,w/2+64,w/2+64);

    }//paint

    public void ddaLine(int x0,int y0,int x1,int y1){
        Color color = new Color(0,0,128);
        int dx = x1 - x0;
        int dy = y1 -y0;
        // Returns the greater of two integers
        int steps = Math.max(Math.abs(dx), Math.abs(dy));
        /*System.out.println("dx "+dx+" dy "+dy);
        System.out.println("steps "+steps);*/
        float xinc = dx / steps, yinc = (float) dy / steps;
        float x = x0, y = y0;
      /*  System.out.println("xinc: "+xinc+", yinc: "+yinc);
        System.out.println("float x: "+x+" y: "+y);
        System.out.println("round x: "+Math.round(x)+", y: "+Math.round(y));*/
        putPixel((int)Math.round(x),(int)Math.round(y),Color.blue);
        for(int k=1;k<=steps;k++){
            x += xinc;
            y += yinc;
            //System.out.println("float x: "+x+", y: "+y);
            putPixel((int)Math.round(x),(int)Math.round(y),Color.blue);
            //System.out.println("round x: "+Math.round(x)+", y: "+Math.round(y));
        }
    }//ddaLine
}//class
