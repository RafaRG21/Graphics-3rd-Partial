package com.company;

import javax.swing.*;

public class Mov extends Thread{
    JLabel label;
    int velocidad;
    JLabel end;


    public Mov(JLabel label,int velocidad, JLabel end) {
    this.label=label;
    this.velocidad=velocidad;
    this.end=end;
    }

    @Override
    public void run() {
        while(true){
            try {
                this.label.setBounds(label.getX()+velocidad,label.getY(),label.getWidth(),label.getHeight());
                if(label.getX()>=800){
                    this.end.setVisible(true);
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
