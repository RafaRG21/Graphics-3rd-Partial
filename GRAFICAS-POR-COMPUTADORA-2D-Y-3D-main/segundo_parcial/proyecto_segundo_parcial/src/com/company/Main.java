package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	MiVentana miVentana=new MiVentana();

    Thread uno=new Thread(miVentana);
miVentana.getContentPane().setBackground(new java.awt.Color(24, 34, 75));
        miVentana.show();
    uno.start();

    }
}
