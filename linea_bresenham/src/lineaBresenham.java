import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

//Ruiz Gudiño Jose Rafael 20110374
//Linea de Bresenham
public class lineaBresenham extends JFrame {
    private BufferedImage buffer;

    lineaBresenham(){
        setSize(500,500);
        setTitle("Linea Bresenham");
        setLayout(null);
        setVisible(true);
        buffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
    }//Constructor

    public static void main(String[] args) {
        new lineaBresenham();
    }//main

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        int h = getHeight(), w = getWidth();
        //bresenham(110,115,210,165);
        bresenham(210,150,300,100);
    }//paint

    
    public void putPixel(int x, int y, Color c ){
        buffer.setRGB(0,0,c.getRGB());
        this.getGraphics().drawImage(buffer,x,y,this);

    }//putPixel

   private void bresenham(int x0, int y0, int x1, int y1){
        Color col = new Color(85,107,47);
        int dy = Math.abs(y1-y0);
        int dx = Math.abs(x1-x0);
        int xk = x0, yk = y0;
        putPixel(xk,yk,col);
        System.out.println(xk+","+yk);
        int pk = 2*dy - dx;
        while(xk<x1){
            xk++;
            if(pk<0) {
                pk += 2 * dy;
            }
            else{
                pk = pk + 2*dy -2*dx;
                yk++;
            }
        putPixel(xk,yk,col);
            System.out.println(xk+","+yk);

        }
    }
    /*private void lineaBresenham(int x0,int y0,int x1, int y1){
        int dx = (x1 - x0);
        int dy = (y1 - y0);
        int stepY, stepX,pk;
        int xk = x0;
        int yk = y0;
        /* determinar que punto usar para empezar, cual para terminar
        if (dy < 0) {
            dy = -dy;
             stepY = -1;
        }
        else
            stepY = 1;
        if (dx < 0) {
            dx = -dx; stepX = -1;
        }
        else
            stepX = 1;
        putPixel(xk,yk,Color.BLUE);
        /* se cicla hasta llegar al extremo de la línea
        if(dx>dy){
            pk = 2*dy - dx;
            while (xk != x1){
                xk += stepX;
                if (pk < 0){
                    pk += 2*dy;
                }
                else {
                    yk += stepY;
                    pk += 2*(dy-dx);
                }
                putPixel(xk,yk,Color.BLUE);
            }
        }
        else{
            pk = 2*dx - dy;
            while (yk != y1){
                yk += stepY;
                if (pk < 0){
                    pk += 2*dx;                }
                else {
                    xk += stepX;
                    pk += 2*(dx-dy);
                }
                putPixel(xk,yk,Color.BLUE);
            }
        }
    }//lineaBresenham
*/
}//class
