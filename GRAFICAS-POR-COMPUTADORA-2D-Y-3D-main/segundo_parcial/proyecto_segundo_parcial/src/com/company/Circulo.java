package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;


public class Circulo extends JFrame {
    public ArrayList<String> puntos_dibujados;
    public Image buffer;
    int  radio;
    int [] centro;
    Graphics g2;
    Graphics gbufer;
    Color color;


    private BufferedImage bufferImage;

    public Circulo( BufferedImage bufferImage, int radio, int [] centro,  Graphics gbufer,Image buffer, Graphics g2, Color color) {

        this.bufferImage = bufferImage;
        this.radio=radio;
        this.centro=centro;

        this.g2=g2;
        this.buffer=buffer;
        this.gbufer=gbufer;
        this.color=color;


        puntos_dibujados=new ArrayList<>();
    }

    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferImage.setRGB(0, 0, c.getRGB());

        g.drawImage(bufferImage, x, y,null);
    }


    void dibujar()
    {

        int x = radio;
        int y = 0;
        puntos_dibujados.clear();
        putPixel(centro[0],centro[1] + radio,Color.white,gbufer);
        puntos_dibujados.add((centro[0])+","+(centro[1]+radio));
        putPixel(centro[0],centro[1] - radio,Color.white,gbufer);
        puntos_dibujados.add((centro[0])+","+(centro[1]-radio));
        putPixel(centro[0]+ radio,centro[1] ,Color.white,gbufer);
        puntos_dibujados.add((centro[0]+radio)+","+(centro[1]));
        putPixel(centro[0]- radio,centro[1] ,Color.white,gbufer);
        puntos_dibujados.add((centro[0]-radio)+","+(centro[1]));

        putPixel(x + centro[0],y + centro[1],Color.white,gbufer);

        int incremento = 1 - radio;
        while (x > y) {
            y++;
            if (incremento <= 0)
                incremento = incremento + 2 * y + 1;
            else {
                x--;
                incremento = incremento + 2 * y - 2 * x + 1;
            }

            //CUADRANTE C
            putPixel(x+centro[0],y+centro[1],Color.white,gbufer);
            puntos_dibujados.add((x+centro[0])+","+(y+centro[1]));

            //CUADRANTE F
            putPixel(-x+centro[0],y+centro[1],Color.white,gbufer);
            puntos_dibujados.add((-x+centro[0])+","+(y+centro[1]));

            //CUADRANTE B
            putPixel(x+centro[0],-y+centro[1],Color.white,gbufer);
            puntos_dibujados.add((x+centro[0])+","+(-y+centro[1]));

            //CUADRANTE G
            putPixel(-x+centro[0],-y+centro[1],Color.white,gbufer);
            puntos_dibujados.add((-x+centro[0])+","+(-y+centro[1]));



            if (x != y) {

                //CUADRANTE A
                putPixel(y+centro[0],-x+centro[1],Color.white,gbufer);
                puntos_dibujados.add((y+centro[0])+","+(-x+centro[1]));


                //CUADRANTE D
                putPixel(y+centro[0],x+centro[1],Color.white,gbufer);
                puntos_dibujados.add((y+centro[0])+","+(x+centro[1]));

                //CUADRANTE E
                putPixel(-y+centro[0],x+centro[1],Color.white,gbufer);
                puntos_dibujados.add((-y+centro[0])+","+(x+centro[1]));

                //CUADRANTE H
                putPixel(-y+centro[0],-x+centro[1],Color.white,gbufer);
                puntos_dibujados.add((-y+centro[0])+","+(-x+centro[1]));



            }
        }
        g2.drawImage(buffer,0,0,null);

    }


    public void relleno(){

        relleno(centro);
        gbufer.drawImage(buffer,0,0,null);

    }

    public void relleno( int [] punto){

        if(!puntos_dibujados.contains(String.valueOf(punto[0])+","+String.valueOf(punto[1])) ) {
            putPixel(punto[0],punto[1],color,gbufer);
            puntos_dibujados.add(String.valueOf(punto[0])+","+String.valueOf(punto[1]));
            relleno( new int[]{punto[0], punto[1]-1});
            relleno( new int[]{punto[0]+1, punto[1]});
            relleno( new int[]{punto[0], punto[1]+1});
            relleno( new int[]{punto[0]-1, punto[1]});

        }
        return;

    }


}
