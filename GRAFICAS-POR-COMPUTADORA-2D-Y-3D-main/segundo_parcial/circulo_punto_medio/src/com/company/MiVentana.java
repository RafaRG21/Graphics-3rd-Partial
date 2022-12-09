package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MiVentana extends JFrame {

    private BufferedImage buffer;
    private Graphics graPixel;

    int [] centro={300,300};

    int radio=100;

    public ArrayList<String> puntos_dibujados;




    public MiVentana(){
        setTitle("Circulo Basico");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(null);
        buffer= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        graPixel=(Graphics2D)buffer.createGraphics();
        puntos_dibujados= new ArrayList<>();


    }


    public void putPixel(int x, int y, Color c) {
        buffer.setRGB(0, 0, c.getRGB());

        this.getGraphics().drawImage(buffer, x, y, this);
    }

    public void paint (Graphics g) {
        super.paint(g);


            puntos_dibujados.clear();
            circulo_punto_medio(centro,radio);
            ///llenado_uno(centro);
            int a=0;


        System.out.println("uno");



    }

     void circulo_punto_medio(int [] centro, int radio)
    {

        int x = radio;
        int y = 0;

        putPixel(centro[0],centro[1] + radio,Color.red);
        puntos_dibujados.add((centro[0])+","+(centro[1]+radio));
        putPixel(centro[0],centro[1] - radio,Color.red);
        puntos_dibujados.add((centro[0])+","+(centro[1]-radio));
        putPixel(centro[0]+ radio,centro[1] ,Color.red);
        puntos_dibujados.add((centro[0]+radio)+","+(centro[1]));
        putPixel(centro[0]- radio,centro[1] ,Color.red);
        puntos_dibujados.add((centro[0]-radio)+","+(centro[1]));

        putPixel(x + centro[0],y + centro[1],Color.red);

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
            putPixel(x+centro[0],y+centro[1],Color.red);
            puntos_dibujados.add((x+centro[0])+","+(y+centro[1]));
            //puntos_dibujados.add(x+centro[0]-1+","+y+centro[1]);
            //puntos_dibujados.add(x+centro[0]+1+","+y+centro[1]);
            //CUADRANTE F
            putPixel(-x+centro[0],y+centro[1],Color.red);
            puntos_dibujados.add((-x+centro[0])+","+(y+centro[1]));
            //puntos_dibujados.add(-x+centro[0]-1+","+y+centro[1]);
            //puntos_dibujados.add(-x+centro[0]+1+","+y+centro[1]);
            //CUADRANTE B
            putPixel(x+centro[0],-y+centro[1],Color.red);
            puntos_dibujados.add((x+centro[0])+","+(-y+centro[1]));
            //puntos_dibujados.add(x+centro[0]-1+","+-y+centro[1]);
            //puntos_dibujados.add(x+centro[0]+1+","+-y+centro[1]);
            //CUADRANTE G
            putPixel(-x+centro[0],-y+centro[1],Color.red);
            puntos_dibujados.add((-x+centro[0])+","+(-y+centro[1]));
            //puntos_dibujados.add(-x+centro[0]-1+","+-y+centro[1]);
            //puntos_dibujados.add(-x+centro[0]+1+","+-y+centro[1]);


            if (x != y) {

                //CUADRANTE A
                putPixel(y+centro[0],-x+centro[1],Color.red);
                puntos_dibujados.add((y+centro[0])+","+(-x+centro[1]));
                //puntos_dibujados.add(y+centro[0]-1+","+-x+centro[1]);
                //puntos_dibujados.add(y+centro[0]+1+","+-x+centro[1]);

                //CUADRANTE D
                putPixel(y+centro[0],x+centro[1],Color.red);
                puntos_dibujados.add((y+centro[0])+","+(x+centro[1]));
                //puntos_dibujados.add(y+centro[0]-1+","+x+centro[1]);
                //puntos_dibujados.add(y+centro[0]+1+","+x+centro[1]);
                //CUADRANTE E
                putPixel(-y+centro[0],x+centro[1],Color.red);
                puntos_dibujados.add((-y+centro[0])+","+(x+centro[1]));
                //puntos_dibujados.add(-y+centro[0]-1+","+x+centro[1]);
                //puntos_dibujados.add(-y+centro[0]+1+","+x+centro[1]);
                //CUADRANTE H
                putPixel(-y+centro[0],-x+centro[1],Color.red);
                puntos_dibujados.add((-y+centro[0])+","+(-x+centro[1]));
                //puntos_dibujados.add(-y+centro[0]+1+","+-x+centro[1]);
                //puntos_dibujados.add(-y+centro[0]-1+","+-x+centro[1]);


            }
        }
    }

    public void Bresenham(int x0, int y0, int x1, int y1){
        int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);
        /* determinar que punto usar para empezar, cual para terminar */
        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        }
        else
            stepy = 1;
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        }
        else
            stepx = 1;
        x = x0;
        y = y0;
        putPixel(x,y,Color.red);
        puntos_dibujados.add(String.valueOf(x)+","+String.valueOf(y));
        /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            p = 2*dy - dx;
            incE = 2*dy;
            incNE = 2*(dy-dx);
            while (x != x1){
                x = x + stepx;
                if (p < 0){
                    p = p + incE;
                }
                else {
                    y = y + stepy;
                    p = p + incNE;
                }
                putPixel(x,y,Color.red);
                puntos_dibujados.add(String.valueOf(x)+","+String.valueOf(y));
            }
        }
        else{
            p = 2*dx - dy;
            incE = 2*dx;
            incNE = 2*(dx-dy);
            while (y != y1){
                y = y + stepy;
                if (p < 0){
                    p = p + incE;
                }
                else {
                    x = x + stepx;
                    p = p + incNE;
                }
                putPixel(x,y,Color.red);
                puntos_dibujados.add(String.valueOf(x)+","+String.valueOf(y));

            }
        }


    }

    public void llenado_uno( int [] punto)  {

        if(!puntos_dibujados.contains(String.valueOf(punto[0])+","+String.valueOf(punto[1])) ) {
            putPixel(punto[0],punto[1],Color.green);
            puntos_dibujados.add(String.valueOf(punto[0])+","+String.valueOf(punto[1]));

            //System.out.println(String.valueOf(punto[0])+","+String.valueOf(punto[1]));
            llenado_uno( new int[]{punto[0], punto[1]-1});
            llenado_uno( new int[]{punto[0]+1, punto[1]});
            llenado_uno( new int[]{punto[0], punto[1]+1});
            llenado_uno( new int[]{punto[0]-1, punto[1]});

        }
        return;

    }






}

