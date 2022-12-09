/*
    Referencias para crear esta peculiar inundacion
    Explicacion de Hilos; como ya se hereda (extends) de JFrame, fue necesario implementar (implements) Runnable
        https://jarroba.com/multitarea-e-hilos-en-java-con-ejemplos-thread-runnable/
    Method returns runnable object
        https://stackoverflow.com/questions/14215424/method-returns-runnable-object
*/
package p2_practica12_rellenoinun;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 * @author juanc
 */
public class RellenoInundacion extends JFrame{
    private BufferedImage buffer;
    private Graphics pixel;
    
    public RellenoInundacion(){
        setTitle("Relleno Inundacion");
        setSize(500,500);
        setLayout(null);
        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //BufferedImage(int width, int height, int imageType)
        buffer=new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        pixel=(Graphics2D) buffer.createGraphics();
    }
    
    public void putPixel(int x, int y, Color col){
        buffer.setRGB(0,0,col.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }
    
    public void paint(Graphics g){
        super.paint(g);
        
//Pintando la figura a rellenar, sera un rectangulo de 110px de altura y anchura, empezando en las coordenadas 100px de color azul
        g.setColor(Color.BLACK);
        g.drawOval(100, 100, 210, 210);
        g.fillOval(100,100,200,200);
        
        //Coordenadas de arranque de la inundacion
        int contX=155, contY=155;
        putPixel(contX,contY,Color.GREEN);//Punto arranque
        
        //Creacion de los procesos
        Runnable inun1=this.inunInfeDer(contX, contY);
        Runnable inun2=this.inunInfeIzq(contX, contY);
        Runnable inun3=this.inunSupIzq(contX, contY);
        Runnable inun4=this.inunSupDer(contX, contY);
        
        //Arranque de los procesos
        new Thread(inun1).start();
       new Thread(inun2).start();
        new Thread(inun3).start();
       // new Thread(inun4).start();
    }
    
    public Runnable inunInfeDer(int contX, int contY){
        Runnable ejec1=new Runnable(){
            public void run(){
                try {
                    //Inundacion hacia abajo derecha del rectangulo
                    for (int contYRelleno=contY; contYRelleno<210; contYRelleno++){
                        for (int contXRelleno=contX; contXRelleno<210; contXRelleno++){
                            putPixel(contXRelleno,contYRelleno,Color.RED);
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        return ejec1;
    }
    
    public Runnable inunInfeIzq(int contX, int contY){
        Runnable ejec2=new Runnable(){
            public void run(){
                try {
                    //Inundacion hacia abajo izquierda del rectangulo
                    for (int contYRelleno=contY; contYRelleno<210; contYRelleno++){
                        for (int contXRelleno=contX-1; contXRelleno>100; contXRelleno--){
                            putPixel(contXRelleno,contYRelleno,Color.CYAN);
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        return ejec2;
    }
    
    public Runnable inunSupIzq(int contX, int contY){
        Runnable ejec3=new Runnable(){
            public void run(){
                try {
                    //Inundacion hacia arriba izquierda del rectangulo
                    for (int contYRelleno=contY; contYRelleno>100; contYRelleno--){
                        for (int contXRelleno=contX; contXRelleno>100; contXRelleno--){
                            putPixel(contXRelleno,contYRelleno,Color.BLUE);
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        return ejec3;
    }
    
    public Runnable inunSupDer(int contX, int contY){
        Runnable ejec4=new Runnable(){
            public void run(){
                try {
                    //Inundacion hacia arriba derecha del rectangulo
                    for (int contYRelleno=contY; contYRelleno>100; contYRelleno--){
                        for (int contXRelleno=contX+1; contXRelleno<210; contXRelleno++){
                            putPixel(contXRelleno,contYRelleno,Color.MAGENTA);
                        }
                    }
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                }
            }
        };
        return ejec4;
    }
}