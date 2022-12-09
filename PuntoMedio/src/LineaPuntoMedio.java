/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author retom
 */
public class LineaPuntoMedio extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        LineaPuntoMedio a = new LineaPuntoMedio();
        a.show();
    }

    public LineaPuntoMedio() {
        setTitle("Ventana");
        setSize(500, 500);
        setLayout(null);
        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        graPixel = (Graphics2D) buffer.createGraphics();
    }

    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());
        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void paint(Graphics g) {
        super.paint(g);
        // putPixel(100, 200, red);
        //linea(300,0,50,200);
        puntoMedio(100, 100, 300, 200);
    }

    public void puntoMedio(int X1, int Y1, int X2, int Y2) {
        int dx = X2 - X1;
        int dy = Y2 - Y1;

        int d = dy - (dx / 2);
        int x = X1, y = Y1;

        putPixel(x, y, Color.RED);

        while (x < X2) {
            x++;

            if (d < 0) {
                d = d + dy;
            } else {
                d += (dy - dx);
                y++;
            }
            System.out.println(x + " "+y);

            putPixel(x, y, Color.RED);
        }
    }

}