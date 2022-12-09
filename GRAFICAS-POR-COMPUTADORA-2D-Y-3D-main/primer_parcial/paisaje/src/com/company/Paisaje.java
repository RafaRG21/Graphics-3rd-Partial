package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

import static java.awt.Font.PLAIN;

public class Paisaje extends JFrame {


    public Paisaje(){

        setTitle("Ventana");
        setSize(1000, 800);

        setVisible(true);
    }



    @Override
    public void paint(Graphics g){
        super.paint(g);


        Graphics2D g2d = (Graphics2D) g;


        GradientPaint gp = new GradientPaint(350, 350, Color.orange, 450, 420, Color.cyan);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, 1000, 800);

        g2d.setColor(Color.YELLOW);
        g2d.fillOval(50,100 , 150, 150);
        g2d.setColor(Color.BLACK);
        g2d.drawOval(50, 100, 150, 150);

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





}
