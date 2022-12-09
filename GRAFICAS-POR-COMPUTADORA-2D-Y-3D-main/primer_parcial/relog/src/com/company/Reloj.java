package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Calendar;

import static java.awt.Color.black;

public class Reloj extends JFrame implements Runnable {

    private BufferedImage buffer;
    private BufferedImage bufferSeconds;
    private BufferedImage bufferMinutes;
    private BufferedImage bufferHours;

    private Graphics fondo;
    private Graphics gsecond;
    private Graphics gminute;
    private Graphics ghour;



    public int xinicial=170;
    public int yinicial=230;

    public int radioHora = 60;
    public int radioMinuto = 90;
    public int radioSegundo = 120;



    private int hora ;
    private int minuto;
    private int segundo;


    private boolean newMinuete=true;
    private boolean newHour=true;

    private boolean fondoReady=false;


    @Override
    public void run() {

        while(true) {
            try {

                Thread.sleep(1000);
                segundo++;
                if(segundo>60){
                    segundo=1;
                    minuto++;
                    hora++;
                    newMinuete=true;

                }
                if(minuto>60){
                    minuto=1;

                    newHour=true;
                }
                if(hora>720){
                    hora=1;
                    newHour=true;
                }
                repaint();
            }catch(InterruptedException e) {}
        }

    }

    Reloj() {
        setTitle("Reloj");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(null);

        buffer = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        fondo= (Graphics2D) buffer.createGraphics();
        bufferSeconds = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        gsecond=(Graphics2D) bufferSeconds.createGraphics();
        bufferMinutes = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        gminute=(Graphics2D) bufferMinutes.createGraphics();
        bufferHours= new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        ghour=(Graphics2D) bufferHours.createGraphics();

        Calendar calendar=Calendar.getInstance();
        minuto=calendar.get(Calendar.MINUTE);
        hora=(calendar.get(Calendar.HOUR)*60)+minuto;
        segundo=calendar.get(Calendar.SECOND);

        //minuto=30;
        //hora=(5*60)+minuto;
        //segundo=1;


        System.out.println("hora de calendar: "+hora+" : "+minuto);




    }

    public void putPixel(int x, int y, Color c, BufferedImage buffer,Graphics graphics) {
        buffer.setRGB(0, 0, c.getRGB());

        graphics=this.getGraphics();

        graphics.drawImage(buffer, x, y, this);
    }

    public void paint(Graphics g) {
        super.paint(g);


          if(true){


              fondo=g;
              Graphics2D g2d= (Graphics2D) g;
              //fondo.drawImage(fondoImage.getImage(),50,50,400,400,this);
              //fondoReady=true;

              GradientPaint gp = new GradientPaint(350, 350, Color.orange, 450, 420, Color.cyan);
              g2d.setPaint(gp);
              g2d.fillRect(0, 0, 1000, 800);

              g2d.setColor(Color.YELLOW);
              g2d.fillOval(50,100 , 250, 250);
              g2d.setStroke(new BasicStroke(5));
              g2d.setColor(Color.BLACK);
              g2d.drawOval(50, 100, 250, 250);

              g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30));
              g2d.drawString("12",155,129);
              g2d.drawString("1",225,145);
              g2d.drawString("2",255,185);

              g2d.drawString("4",252,284);
              g2d.drawString("5",224,326);

              g2d.drawString("6",171,340);
              g2d.drawString("9",57,235);
              g2d.drawString("3",270,235);

              g2d.drawString("7",118,328);
              g2d.drawString("8",70,285);

              g2d.drawString("11",101,145);
              g2d.drawString("10",70,185);

              g2d.setColor(Color.YELLOW);
              g2d.setStroke(new BasicStroke(5));
              g2d.drawLine(205, 205, 215, 210);
              g2d.setStroke(new BasicStroke(5));
              g2d.drawLine(195, 230, 205, 240);
              g2d.setStroke(new BasicStroke(5));
              g2d.drawLine(160, 255, 170, 270);


              int [] triangulo_x = {300, 650, 1000};
              int [] triangulo_y = {1000, 400, 1000};
              g2d.setColor(Color.DARK_GRAY);
              g2d.fillPolygon (triangulo_x, triangulo_y, 3);
              int [] triangulo_x2 = {592, 650, 710};
              int [] triangulo_y2 = {500, 400, 500};
              g2d.setColor(Color.LIGHT_GRAY);
              g2d.fillPolygon (triangulo_x2, triangulo_y2, 3);


              g2d.setColor(Color.white);
              g2d.fillOval(700, 100, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(740, 120, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(760, 60, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(799, 90, 100, 100);



              g2d.setColor(Color.white);
              g2d.fillOval(790, 350, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(830, 370, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(850, 310, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(899, 340, 100, 100);


              g2d.setColor(Color.white);
              g2d.fillOval(490, 350, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(530, 370, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(550, 310, 100, 100);
              g2d.setColor(Color.white);
              g2d.fillOval(599, 340, 100, 100);


              g2d.setColor(Color.darkGray);
              g2d.setStroke(new BasicStroke(80));
              g2d.drawLine(400, 600, 400, 650);


              g2d.setColor(Color.green);
              g2d.fillOval(290, 470, 100, 100);
              g2d.setColor(Color.green);
              g2d.fillOval(330, 490, 100, 100);
              g2d.setColor(Color.red);
              g2d.fillOval(330, 490, 10, 10);
              g2d.setColor(Color.green);
              g2d.fillOval(350, 430, 100, 100);

              g2d.setColor(Color.red);
              g2d.fillOval(400, 550, 10, 10);

              g2d.setColor(Color.red);
              g2d.fillOval(460, 520, 10, 10);
              g2d.setColor(Color.green);
              g2d.fillOval(399, 470, 100, 100);

              g2d.setColor(Color.red);
              g2d.fillOval(399, 470, 10, 10);

              g2d.setColor(Color.GREEN);
              g2d.fillOval(0, 650, 700, 500);








          }

        int[] aux = getCordHora(hora, new int[]{xinicial, yinicial});

        if(true) {
            Bresenham(xinicial, yinicial, aux[0], aux[1], bufferHours,ghour);
            newHour=false;

        }
        aux = getCordMinuto(minuto, new int[]{xinicial, yinicial});

        if(true) {
            Bresenham(xinicial, yinicial, aux[0], aux[1], bufferMinutes,gminute);
            newMinuete=false;
        }
        aux = getCordSegundo(segundo, new int[]{xinicial, yinicial});

        Bresenham(xinicial, yinicial, aux[0], aux[1],bufferSeconds,gsecond);


    }

    public void Bresenham(int x0, int y0, int x1, int y1, BufferedImage buffer, Graphics graphics) {
        int x, y, dx, dy, p, incE, incNE, stepx, stepy;
        dx = (x1 - x0);
        dy = (y1 - y0);
        /* determinar que punto usar para empezar, cual para terminar */
        if (dy < 0) {
            dy = -dy;
            stepy = -1;
        } else
            stepy = 1;
        if (dx < 0) {
            dx = -dx;
            stepx = -1;
        } else
            stepx = 1;
        x = x0;
        y = y0;
        putPixel(x, y, black, buffer,graphics);
        /* se cicla hasta llegar al extremo de la línea */
        if (dx > dy) {
            p = 2 * dy - dx;
            incE = 2 * dy;
            incNE = 2 * (dy - dx);
            while (x != x1) {
                x = x + stepx;
                if (p < 0) {
                    p = p + incE;
                } else {
                    y = y + stepy;
                    p = p + incNE;
                }
                putPixel(x, y, black, buffer,graphics);
            }
        } else {
            p = 2 * dx - dy;
            incE = 2 * dx;
            incNE = 2 * (dx - dy);
            while (y != y1) {
                y = y + stepy;
                if (p < 0) {
                    p = p + incE;
                } else {
                    x = x + stepx;
                    p = p + incNE;
                }
                putPixel(x, y, black,buffer,graphics);
                // System.out.println("x:"+x+" y:"+y);
            }
        }


    }

    public int[] getCordHora(int hora, int[] inicial) {

        int x = 0;
        int y = 0;

        System.out.println(hora);
        float angulo2 = 0.5F;

        if (hora <= 180) {
            //cuadrante 1 x+;y-
            float maxHora = 180;
            float  anguloAux = (maxHora - hora) * (angulo2);
            float subX = (float) ((float) radioHora * Math.cos(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioHora * Math.sin(Math.toRadians(anguloAux)));
            x = Math.round(inicial[0] + subX);
            y = Math.round(inicial[1] - subY);

        } else if (hora <= 360) {
            //cuadrante 2 x+;y+
            int maxHora = 360;
            float anguloAux = (maxHora - hora) * (angulo2);
            float subX = (float) ((float) radioHora * Math.sin(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioHora * Math.cos(Math.toRadians(anguloAux)));
            x = Math.round(inicial[0] + subX);
            y = Math.round(inicial[1] + subY);


        } else if (hora <= 540) {
            //cuadrante 3 x-;y+
            int maxHora = 540;
            float anguloAux = (maxHora - hora) * (angulo2);

            float subX = (float) ((float) radioHora * Math.cos(Math.toRadians(360 - anguloAux)));
            float subY = (float) ((float) radioHora * Math.sin(Math.toRadians(360 - anguloAux)));
            x = Math.round(inicial[0] - subX);
            y = Math.round(inicial[1] - subY);

        } else {

            int maxHora = 720;
            float anguloAux = (maxHora - hora) * (angulo2);
            float subX = (float) ((float) radioHora * Math.sin(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioHora * Math.cos(Math.toRadians(anguloAux)));
            x = Math.round(inicial[0] - subX);
            y = Math.round(inicial[1] - subY);
        }
        return new int[]{x, y};
    }

    public int[] getCordMinuto(int minuto, int[] inicial) {

        int x = 0;
        int y = 0;
        int angulo2 = 6;

        if (minuto <= 3) {
            //cuadrante 1 x+;y-
            int maxMinuto = 15;
            int anguloAux = (maxMinuto - minuto) * (angulo2);
            //System.out.println("angulo: " + anguloAux);
            //System.out.println("cos: " + Math.cos(Math.toRadians(anguloAux)));
            //System.out.println("sin: " + Math.sin(Math.toRadians(anguloAux)));

            float subX = (float) ((float) radioMinuto * Math.cos(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioMinuto * Math.sin(Math.toRadians(anguloAux)));
            //System.out.println("subX: " + subX);
            //System.out.println("suby: " + subY);
            x = Math.round(inicial[0] + subX);
            y = Math.round(inicial[1] - subY);

            //System.out.println("x: " + x + " y: " + y);


        } else if (minuto <= 6) {
            //cuadrante 2 x+;y+


            int maxMinuto = 30;
            int anguloAux = (maxMinuto - minuto) * (angulo2);
            //System.out.println("angulo: " + anguloAux);
            //System.out.println("cos: " + Math.cos(Math.toRadians(anguloAux)));
            //System.out.println("sin: " + Math.sin(Math.toRadians(anguloAux)));

            float subX = (float) ((float) radioMinuto * Math.sin(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioMinuto * Math.cos(Math.toRadians(anguloAux)));
            //System.out.println("subX: " + subX);
            //System.out.println("suby: " + subY);
            x = Math.round(inicial[0] + subX);
            y = Math.round(inicial[1] + subY);

            //System.out.println("x: " + x + " y: " + y);

        } else if (minuto <= 9) {
            //cuadrante 3 x-;y+
            int maxMinuto = 45;
            int anguloAux = (maxMinuto - minuto) * (angulo2);
            //System.out.println("angulo: " + anguloAux);
            //System.out.println("cos: " + Math.cos(Math.toRadians(anguloAux)));
            //System.out.println("sin: " + Math.sin(Math.toRadians(anguloAux)));

            float subX = (float) ((float) radioMinuto * Math.cos(Math.toRadians(360 - anguloAux)));
            float subY = (float) ((float) radioMinuto * Math.sin(Math.toRadians(360 - anguloAux)));
            //System.out.println("subX: " + subX);
            //System.out.println("suby: " + subY);
            x = Math.round(inicial[0] - subX);
            y = Math.round(inicial[1] - subY);

            //System.out.println("x: " + x + " y: " + y);

        } else {
            //cuadrante 4 x-;y-
            int maxMinuto = 60;
            int anguloAux = (maxMinuto - minuto) * (angulo2);
            //System.out.println("angulo: " + anguloAux);
            //System.out.println("cos: " + Math.cos(Math.toRadians(anguloAux)));
            //System.out.println("sin: " + Math.sin(Math.toRadians(anguloAux)));

            float subX = (float) ((float) radioMinuto * Math.sin(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioMinuto * Math.cos(Math.toRadians(anguloAux)));
            //System.out.println("subX: " + subX);
            //System.out.println("suby: " + subY);
            x = Math.round(inicial[0] - subX);
            y = Math.round(inicial[1] - subY);

            //System.out.println("x: " + x + " y: " + y);


        }


        return new int[]{x, y};
    }

    public int[] getCordSegundo(int segundo, int[] inicial) {

        int x = 0;
        int y = 0;
        int angulo2 = 6;

        if (segundo <= 3) {
            //cuadrante 1 x+;y-
            int maxSegundo = 15;
            int anguloAux = (maxSegundo - segundo) * (angulo2);

            float subX = (float) ((float) radioSegundo * Math.cos(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioSegundo * Math.sin(Math.toRadians(anguloAux)));
            x = Math.round(inicial[0] + subX);
            y = Math.round(inicial[1] - subY);


        } else if (segundo <= 6) {
            //cuadrante 2 x+;y+


            int maxSegundo = 30;
            int anguloAux = (maxSegundo - segundo) * (angulo2);

            float subX = (float) ((float) radioSegundo * Math.sin(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioSegundo * Math.cos(Math.toRadians(anguloAux)));
            x = Math.round(inicial[0] + subX);
            y = Math.round(inicial[1] + subY);


        } else if (segundo <= 9) {
            //cuadrante 3 x-;y+
            int maxSegundo = 45;
            int anguloAux = (maxSegundo - segundo) * (angulo2);

            float subX = (float) ((float) radioSegundo * Math.cos(Math.toRadians(360 - anguloAux)));
            float subY = (float) ((float) radioSegundo * Math.sin(Math.toRadians(360 - anguloAux)));
            x = Math.round(inicial[0] - subX);
            y = Math.round(inicial[1] - subY);

        } else {
            //cuadrante 4 x-;y-
            int maxSegundo = 60;
            int anguloAux = (maxSegundo - segundo) * (angulo2);

            float subX = (float) ((float) radioSegundo * Math.sin(Math.toRadians(anguloAux)));
            float subY = (float) ((float) radioSegundo * Math.cos(Math.toRadians(anguloAux)));
            x = Math.round(inicial[0] - subX);
            y = Math.round(inicial[1] - subY);

        }
        return new int[]{x, y};
    }

}