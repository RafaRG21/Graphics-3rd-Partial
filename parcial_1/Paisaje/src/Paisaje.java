import org.w3c.dom.css.RGBColor;

import javax.swing.*;
import java.awt.*;
// Ruiz Gudiño Jose Rafael 20110374
public class Paisaje extends JFrame {

    public Paisaje(){
        setTitle("Mi paisaje 20110374");
        setSize(600,600);
        setLayout(null);
        setVisible(true);
    }
    public static void main(String[] args){
        Paisaje paisaje = new Paisaje();
    }
    public void paint(Graphics g){
        super.paint(g);
        int h = this.getHeight();
        int w = this.getWidth();
        Graphics2D graph = (Graphics2D) g;
        //graph.drawLine(w/2-30,0,w/2-20,h-1);
        //graph.drawLine(w/2+20,0,w/2+10,h-1);
        //Fondo atardecer
        Color sunset = new Color(246, 211, 101);
        Color sunset2 = new Color(253, 160, 133);
        GradientPaint bg = new GradientPaint(w, h, sunset, 0, 0, sunset2);
        graph.setPaint(bg);
        graph.fillRect(0, 0, w, h);
        //nube
        Color cloud = new Color(229, 228, 227);
        graph.setColor(cloud);
        graph.fillOval(20, 100, 300, 150);
        graph.fillOval(70, 80, 200, 200);
        //Montañas
        Color green_hill = new Color(70, 164, 78);
        Color green_hill_line = new Color(3, 51, 9);

        int [] hill_x = {-20,200, 400};
        int [] hill_y = {h, 400, h};
        int [] hill_x2 = {300,500, w+100};
        int [] hill_y2 = {h, 400, h};
        graph.setColor(green_hill);
        graph.fillPolygon (hill_x, hill_y, 3);
        graph.fillPolygon (hill_x2, hill_y2, 3);
        graph.setColor(green_hill_line);
        graph.drawPolygon (hill_x2, hill_y2, 3);
        graph.drawPolygon (hill_x, hill_y, 3);

        //Nevado Montaña
        graph.setColor(Color.BLACK);
        int [] snow_x = {155,200, 240};
        int [] snow_y = {440, 400, 440};
        int [] snow_x2 = {460,500, 540};
        int [] snow_y2 = {440, 400, 440};
        graph.drawPolygon (snow_x, snow_y, 3);
        graph.drawPolygon (snow_x2, snow_y2, 3);
        graph.setColor(Color.WHITE);
        graph.fillPolygon (snow_x, snow_y, 3);
        graph.fillPolygon (snow_x2, snow_y2, 3);
        //Arbol tronco
        Color brown = new Color(101,42,14);
        Color brown_dark = new Color(35, 23, 9);
        graph.setColor(brown);
        graph.fillRect(w/2-20, 100, 50, h-10);
        graph.setColor(brown_dark);
        graph.drawRect(w/2-20, 100, 50, h-10);
        //Arbol hojas
        Color green_leaf = new Color(59, 124, 8);
        graph.setColor(green_leaf);
        graph.fillOval(w/2-95, 200, 200, 200);
        graph.fillOval(w/2-95, 50, 200, 200);
        graph.fillOval(w/2-170, 150, 200, 200);
        graph.fillOval(w/2-20, 150, 200, 200);
        //Manzanas
        Color red_apple = new Color(199, 55, 47);
        graph.setColor(red_apple);
        graph.fillOval(w/2, h/2, 40, 40);
        graph.fillOval(w/2-60, h/2, 40, 40);
        graph.fillOval(w/2+60, h/2, 40, 40);
        for(int i = w/2-150;i<w/2+100;i+=60)
            graph.fillOval(i, h/2-50, 40, 40);
        for(int i = w/2-150;i<w/2+100;i+=60)
            graph.fillOval(i, h/2-110, 40, 40);
        graph.fillOval(w/2-10, h/2-180, 40, 40);
        graph.fillOval(w/2-70, h/2-180, 40, 40);
        graph.fillOval(w/2+50, h/2-180, 40, 40);
        //SOL
        Color sun =  new Color(227, 74, 43);
        graph.setColor(sun);
        graph.fillOval(w-150, 0, 200, 200);
        Color sun_border =  new Color(145, 38, 16);
        graph.setColor(sun_border);
        graph.drawOval(w-150, 0, 200, 200);





















    }
}
