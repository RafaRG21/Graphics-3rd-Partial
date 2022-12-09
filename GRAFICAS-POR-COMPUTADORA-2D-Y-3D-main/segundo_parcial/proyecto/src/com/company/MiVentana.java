package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class MiVentana extends JFrame implements Runnable {

    private Image fondo;
    private Image buffer;

    private BufferedImage bufferImage;
    private Graphics graPixel;
    public ArrayList<String> puntos_dibujados;

    int[] punto1={50,50};
    int[] punto2={100,50};
    int[] punto3={100,100};
    int[] punto4={50,100};
    int [] centro={50,50};
    int radio=20;
    boolean firstime=true;

    int maxX=1000;
    int mayY=100;
    int incX=0;
    int incY=0;



    public MiVentana(){
        setTitle("Indundacion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(null);
        bufferImage= new BufferedImage(1,1, BufferedImage.TYPE_INT_RGB);
        puntos_dibujados= new ArrayList<>();





    }


    public void putPixel(int x, int y, Color c,Graphics g) {
        bufferImage.setRGB(0, 0, c.getRGB());

        g.drawImage(bufferImage, x, y, this);
    }

    public void paint (Graphics g) {
        if(fondo==null){
            fondo=createImage(getWidth(),getHeight());
            Graphics gfondo=fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
        }

        update(g);
    }

    @Override
    public void update(Graphics g) {
        g.setClip(0,0,getWidth(),getHeight());
        buffer=createImage(getWidth(),getHeight());
        Graphics gbufer=buffer.getGraphics();
        gbufer.setClip(0,0,getWidth(),getHeight());
        gbufer.drawImage(fondo,0,0,this);




        Circulo circulo2=new Circulo(bufferImage,10,new int []{300,300},gbufer,buffer,  g);
        circulo2.setName("dos");

        Circulo circulo1=new Circulo(bufferImage,10,new int []{100,100},gbufer,buffer,  g);
        circulo1.setName("uno");
        Thread uno=new Thread(circulo1);
        uno.start();











    }




    public static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[b.length][a[0].length];
        // se comprueba si las matrices se pueden multiplicar

        if (a[0].length == b[0].length) {

            for (int i=0;i<b.length;i++){

                for(int z=0;z<a.length;z++){
                    int aux=0;
                    for(int j=0;j<b[0].length;j++){
                        aux+=b[i][j]*a[z][j];
                    }

                    c[i][z]=aux;
                }
            }
        }
        return c;
    }


    @Override
    public void run() {

      //  while (incX<maxX || incY<mayY){
        while (incX<maxX){

            try {


                repaint();




                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }catch (Exception e){
                System.out.println("ERROR GENERAL");
            }
        }
    }
}

