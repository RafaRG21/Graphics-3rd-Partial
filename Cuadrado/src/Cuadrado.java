import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Cuadrado extends JFrame{
    private Image background;
    private Image buffer;
    private BufferedImage bufferedImage;
    int[] point1 = {50,50};
    int[] point2 = {200,200};


    public Cuadrado(){
        setTitle("Cuadrado 20110374");
        setResizable(false);
        setSize(700,800);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);

    }//constructor


    public static void main(String[] args) {
        new Cuadrado();
    }///Main


    @Override
    public void paint(Graphics g) {
        if(background == null){
            background  = createImage(getWidth(),getHeight());
            Graphics gfondo = background.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
        }

        update(g);
    }//paint


    public void update(Graphics g){
        g.setClip(0,0,getWidth(),getHeight());
        //regenerar la imagen de fondo
        //crear la imagen estatica
        buffer = createImage(getWidth(),getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0,0,getWidth(),getHeight());
        gbuffer.drawImage(background,0,0,this);
        //Coordenades
        int[][] coordenades = {point1,point2};
        realizarCuadrado(coordenades,Color.blue,gbuffer);

        g.drawImage(buffer,0,0,this); //doble buffer

    }//update


    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);
    }//putPixel


    private void realizarCuadrado(int[][] coordenades, Color c, Graphics g){
        lineaBresenham(coordenades[0][0],coordenades[0][1],coordenades[1][0],coordenades[0][1],g,c); //up
        lineaBresenham(coordenades[0][0],coordenades[1][1],coordenades[1][0],coordenades[1][1],g,c); //down
        lineaBresenham(coordenades[0][0],coordenades[0][1],coordenades[0][0],coordenades[1][1],g,c); //left
        lineaBresenham(coordenades[1][0],coordenades[0][1],coordenades[1][0],coordenades[1][1],g,c); //right
    }


    private void lineaBresenham(int x0, int y0, int x1, int y1, Graphics g, Color c){
        int dx = (x1 - x0);
        int dy = (y1 - y0);
        int stepY, stepX,pk;
        int xk = x0;
        int yk = y0;
        // determinar punto de partida y fin
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
        putPixel(xk,yk,c,g);
        /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            pk = 2*dy - dx;
            while (xk != x1){
                xk += stepX;
                if (pk < 0)
                    pk += 2*dy;
                else {
                    yk += stepY;
                    pk += 2*(dy-dx);
                }
                putPixel(xk,yk,c,g);
            }
        }
        else{
            pk = 2*dx - dy;
            while (yk != y1){
                yk += stepY;
                if (pk < 0)
                    pk += 2*dx;
                else {
                    xk += stepX;
                    pk += 2*(dx-dy);
                }
                putPixel(xk,yk,c,g);
            }
        }
    }//lineaBresenham


    private void rellenoScanLine(int[][] figura,Graphics g,Color c){


        for (int i = 0; i <= figura[1][3] - figura[1][0]; i++) {
            lineaBresenham(figura[0][0], figura[1][0] + i, figura[0][1], figura[1][1] + i,g,c);
        }




    }//rellenoScanLine
}