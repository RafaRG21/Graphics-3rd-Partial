package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.Line2D;

public class Carrera extends JFrame{
    private ImageIcon elefante;
    private ImageIcon hiena;
    private ImageIcon jirafa;

    private JLabel elefanteL;
    private JLabel hienaL;
    private JLabel jirafaL;

    Carrera(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel=new JPanel();
        panel.setLayout(null);

        getContentPane().add(panel);
        setSize(1010,550);

        JButton button =new JButton("Iniciar");
        JLabel meta=new JLabel("meta 800m");
        JLabel inicio=new JLabel("inicio 0m");
        JLabel velocidad1=new JLabel("Velocidad : elefante 18.5m/s");
        JLabel velocidad2=new JLabel("Velocidad : hiena 11.1m/s");
        JLabel velocidad3=new JLabel("Velocidad : jirafa 16.6m/s");
        JLabel primer=new JLabel("Primer lugar");
        JLabel segundo=new JLabel("Segundo lugar");
        JLabel tercero=new JLabel("Tercer lugar");


        elefante =new ImageIcon("/home/bonnesbaby14/Escritorio/CETI/GRAFICASPORCOMPUTADORA2DY3D/primer_parcial/carrera/src/com/company/elefante.png");
        hiena =new ImageIcon("/home/bonnesbaby14/Escritorio/CETI/GRAFICASPORCOMPUTADORA2DY3D/primer_parcial/carrera/src/com/company/hiena.png");
        jirafa =new ImageIcon("/home/bonnesbaby14/Escritorio/CETI/GRAFICASPORCOMPUTADORA2DY3D/primer_parcial/carrera/src/com/company/jirafa.png");



        elefanteL=new JLabel();
        hienaL=new JLabel();
        jirafaL=new JLabel();

        elefanteL.setBounds(15,90,50,50);
        jirafaL.setBounds(15,140,50,50);
        hienaL.setBounds(15,200,50,50);

        primer.setBounds(880,90,150,20);
        segundo.setBounds(880,140,150,20);
        tercero.setBounds(880,200,150,20);

        primer.setVisible(false);
        segundo.setVisible(false);
        tercero.setVisible(false);


        hienaL.setIcon(new ImageIcon(hiena.getImage().getScaledInstance(hienaL.getWidth(),hienaL.getHeight(),Image.SCALE_SMOOTH)));
        elefanteL.setIcon(new ImageIcon(elefante.getImage().getScaledInstance(elefanteL.getWidth(),elefanteL.getHeight(),Image.SCALE_SMOOTH)));
        jirafaL.setIcon(new ImageIcon(jirafa.getImage().getScaledInstance(jirafaL.getWidth(),jirafaL.getHeight(),Image.SCALE_SMOOTH)));







        meta.setBounds(875,355,100,10);
        inicio.setBounds(35,355,100,10);
        velocidad1.setBounds(300,10,200,10);
        velocidad2.setBounds(300,30,200,10);
        velocidad3.setBounds(300,50,200,10);

        panel.add(button);
        panel.add(meta);
        panel.add(inicio);
        panel.add(velocidad1);
        panel.add(velocidad2);
        panel.add(velocidad3);
        panel.add(elefanteL);
        panel.add(hienaL);
        panel.add(jirafaL);

        panel.add(primer);
        panel.add(segundo);
        panel.add(tercero);


        Mov mov=new Mov(elefanteL,19,primer);
        Mov mov2=new Mov(hienaL,11,tercero);
        Mov mov3=new Mov(jirafaL,16,segundo);
        mov.start();
        mov2.start();
        mov3.start();

    }


    public void paint(Graphics g) {
        super.paint(g);  // fixes the immediate problem.
        Graphics2D g2 = (Graphics2D) g;
        Line2D lin = new Line2D.Float(40, 350, 920, 350);
        g2.setStroke(new BasicStroke(10));
        g2.draw(lin);
    }
}
