package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MiVentana extends JFrame implements Runnable {


    public int pasos=0;
    private Image fondo;
    private Image buffer;
    public int sizeElipse1;
    public int sizeElipse2;
    public int sizeOrbita2;
    public int sizeOrbita1;
    public int indice1;
    public int indice2;
    public int indice3;
    public int indice4;

    public int indiceplaneta1;
    public int indiceplaneta2;
    public int indiceplaneta3;
    public int indiceplaneta4;




    public int radio1;
    public int radio2;
    public int radio1max;
    public int radioExplosion=2;
    public boolean incremento;
    public boolean firstime=true;

    public int [][] puntos_planeta1={{850,450},{870,450},{870,470},{850,470}};
    public float [][] puntos_orbita1={{-250, -250, 250, 250},{-250,250, 250,-250}};
    public float [][] puntos_orbita2={{-230, -230, 230, 230},{-230,230, 230,-230}};
    public  int angulo_planeta1=1;
    int explosionColor=1;







    private BufferedImage bufferImage;


    public MiVentana(){
        setTitle("proyecto");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 800);
        setLayout(null);

        bufferImage= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        sizeElipse1=2002;
        sizeElipse2=2002;

        indice1=400;
        indice2=1700;
        indice3=1000;
        indice4=300;

        indiceplaneta1=900;
        indiceplaneta2=100;
        indiceplaneta3=100;
        indiceplaneta4=700;
        radio1=300;
        radio2=100;
        incremento=true;




    }




    public void paint (Graphics g) {


        update(g);
    }

    @Override
    public void update(Graphics g) {

        g.setClip(0,0,getWidth(),getHeight());
        buffer=createImage(getWidth(),getHeight());
        Graphics gbufer=buffer.getGraphics();
        gbufer.setClip(0,0,getWidth(),getHeight());
        gbufer.drawImage(fondo,0,0,this);


        particula1(g,gbufer);












    }



    public void particula1(Graphics g, Graphics gbufer) {


        if(pasos<1000){
        Elipse elipse1 = new Elipse(buffer, radio1, radio2, new int[]{400, 400}, g, gbufer, bufferImage);
        elipse1.dibujar();
        Elipse elipse2 = new Elipse(buffer, radio2, radio1, new int[]{400, 400}, g, gbufer, bufferImage);
        elipse2.dibujar();


        Circulo centro = new Circulo(bufferImage, 15, new int[]{385, 385}, gbufer, buffer, g, Color.green);
        centro.dibujar();
        centro.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));
        Circulo centro2 = new Circulo(bufferImage, 15, new int[]{410, 385}, gbufer, buffer, g, Color.blue);
        centro2.dibujar();
        centro2.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));
        Circulo centro3 = new Circulo(bufferImage, 15, new int[]{385, 410}, gbufer, buffer, g, Color.blue);
        centro3.dibujar();
        centro3.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));
        Circulo centro4 = new Circulo(bufferImage, 15, new int[]{410, 410}, gbufer, buffer, g, Color.red);
        centro4.dibujar();
        centro4.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));
        Circulo centro5 = new Circulo(bufferImage, 15, new int[]{397, 398}, gbufer, buffer, g, Color.yellow);
        centro5.dibujar();
        centro5.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));


        int[] newcentro = new int[]{Integer.parseInt(elipse1.puntos_dibujados.get(indice1).split(",")[0]), Integer.parseInt(elipse1.puntos_dibujados.get(indice1).split(",")[1])};
        Circulo circulo1 = new Circulo(bufferImage, 10, newcentro, gbufer, buffer, g, Color.cyan);
        circulo1.dibujar();
        circulo1.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));
        int[] newcentro2 = new int[]{Integer.parseInt(elipse1.puntos_dibujados.get(indice2).split(",")[0]), Integer.parseInt(elipse1.puntos_dibujados.get(indice2).split(",")[1])};
        Circulo circulo2 = new Circulo(bufferImage, 10, newcentro2, gbufer, buffer, g, Color.cyan);
        circulo2.dibujar();
        circulo2.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));


        int[] newcentro3 = new int[]{Integer.parseInt(elipse2.puntos_dibujados.get(indice3).split(",")[0]), Integer.parseInt(elipse2.puntos_dibujados.get(indice3).split(",")[1])};
        Circulo circulo3 = new Circulo(bufferImage, 10, newcentro3, gbufer, buffer, g, Color.cyan);
        circulo3.dibujar();
        circulo3.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));

        int[] newcentro4 = new int[]{Integer.parseInt(elipse2.puntos_dibujados.get(indice4).split(",")[0]), Integer.parseInt(elipse2.puntos_dibujados.get(indice4).split(",")[1])};
        Circulo circulo4 = new Circulo(bufferImage, 10, newcentro4, gbufer, buffer, g, Color.cyan);
        circulo4.dibujar();
        circulo4.relleno();
        this.setBackground(new java.awt.Color(12, 22, 56));
        g.drawImage(buffer, 0, 0, null);


        Rombo orbita1 = new Rombo(bufferImage, gbufer, buffer, g, Color.orange, puntos_orbita1[0], puntos_orbita1[1], -1, 5);
        puntos_orbita1 = orbita1.dibujar();
        Rombo orbita2 = new Rombo(bufferImage, gbufer, buffer, g, Color.orange, puntos_orbita2[0], puntos_orbita2[1], 1, 4);
        puntos_orbita2 = orbita2.dibujar();
        sizeOrbita1 = orbita1.puntos_dibujados.size();
        sizeOrbita2 = orbita2.puntos_dibujados.size();
        indiceplaneta1 += 10;
        if (indiceplaneta1 >= sizeOrbita1) {
            indiceplaneta1 = 0;
        }
        indiceplaneta2 += 10;
        if (indiceplaneta2 >= sizeOrbita1) {
            indiceplaneta2 = 0;
        }
        indiceplaneta3 += 10;
        if (indiceplaneta3 >= sizeOrbita2) {
            indiceplaneta3 = 0;
        }
        indiceplaneta4 += 10;
        if (indiceplaneta4 >= sizeOrbita2) {
            indiceplaneta4 = 0;
        }
        int[] planeta1centro = new int[]{Integer.parseInt(orbita1.puntos_dibujados.get(indiceplaneta1).split(",")[0]), Integer.parseInt(orbita1.puntos_dibujados.get(indiceplaneta1).split(",")[1])};

        Cuadrado planeta1 = new Cuadrado(bufferImage, planeta1centro, 10, gbufer, buffer, g, Color.green);
        planeta1.dibujar();
        planeta1.relleno();

        int[] planeta2centro = new int[]{Integer.parseInt(orbita1.puntos_dibujados.get(indiceplaneta2).split(",")[0]), Integer.parseInt(orbita1.puntos_dibujados.get(indiceplaneta2).split(",")[1])};

        Cuadrado planeta2 = new Cuadrado(bufferImage, planeta2centro, 10, gbufer, buffer, g, Color.white);
        planeta2.dibujar();
        planeta2.relleno();

        int[] planeta3centro = new int[]{Integer.parseInt(orbita2.puntos_dibujados.get(indiceplaneta3).split(",")[0]), Integer.parseInt(orbita2.puntos_dibujados.get(indiceplaneta3).split(",")[1])};

        Cuadrado planeta3 = new Cuadrado(bufferImage, planeta3centro, 10, gbufer, buffer, g, Color.red);
        planeta3.dibujar();
        planeta3.relleno();

        int[] planeta4centro = new int[]{Integer.parseInt(orbita2.puntos_dibujados.get(indiceplaneta4).split(",")[0]), Integer.parseInt(orbita2.puntos_dibujados.get(indiceplaneta4).split(",")[1])};

        Cuadrado planeta4 = new Cuadrado(bufferImage, planeta4centro, 10, gbufer, buffer, g, Color.pink);
        planeta4.dibujar();
        planeta4.relleno();

        g.drawImage(buffer, 0, 0, null);


        sizeElipse1 = elipse1.puntos_dibujados.size();
        sizeElipse2 = elipse2.puntos_dibujados.size();


        angulo_planeta1++;
        if (angulo_planeta1 >= 360) {
            angulo_planeta1 = 1;
        }

        indice1 += 10;
        if (indice1 >= sizeElipse1) {
            indice1 = 0;
        }
        indice2 += 10;
        if (indice2 >= sizeElipse1) {
            indice2 = 0;
        }
        indice3 += 10;
        if (indice3 >= sizeElipse2) {
            indice3 = 0;
        }
        indice4 += 10;
        if (indice4 >= sizeElipse2) {
            indice4 = 0;
        }


        if (radio1 == radio1max) {
            incremento = false;

        }
        if (radio1 == 255) {
            incremento = true;
        }
        if (incremento) {

            radio1 -= 5;
            radio2 -= 5;

        } else {
            radio1 += 5;
            radio2 += 5;


        }

    }else if(pasos<1060){
            Circulo explosion = new Circulo(bufferImage, radioExplosion, new int[]{385, 385}, gbufer, buffer, g, Color.white);
            explosion.dibujar();
            explosion.relleno();
            radioExplosion++;
            g.drawImage(buffer, 0, 0, null);

        }else{


            switch (explosionColor){
                case 1:
                    this.setBackground(new java.awt.Color(255, 253, 253));
                    g.drawImage(buffer, 0, 0, null);
                    explosionColor=2;
                    break;

                case 2:
                    this.setBackground(new java.awt.Color(84, 99, 159));
                    g.drawImage(buffer, 0, 0, null);
                    explosionColor=3;
                    break;
                case 3:
                    this.setBackground(new java.awt.Color(34, 46, 91));
                    g.drawImage(buffer, 0, 0, null);
                    explosionColor=4;
                    break;
                case 4:
                    this.setBackground(new java.awt.Color(5, 11, 26));
                    g.drawImage(buffer, 0, 0, null);
                    break;
            }




        }

    }


    @Override
    public void run() {

        while (true){

            try {


                repaint();
                pasos++;
                System.out.println(pasos);




                sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (Exception e){
                System.out.println("ERROR GENERAL");
            }
        }
    }
}

