package com.company;

public class Main {

    public static void main(String[] args) {
	MiVentana miVentana=new MiVentana();

    Thread uno=new Thread(miVentana);
    miVentana.show();
    uno.start();

    }
}
