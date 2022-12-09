import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class Animacion extends JFrame {
    private Image fondo;
    private Image buffer;
    private Thread hilo;
    private BufferedImage bufferedImage;

    int xc = 0, yc =0;
    public int xBus=-250,yBus = 400;
    double xOvni = 0.5,xExc=1.0,yEsc=1.0;
    int radioSun = 15;
    int xCntr = 0, yCntr = 0;
    int yArriba = 0;
    int contVueltaBus =0;
    boolean flagSun = false,flagOvniX= false, flagOvniY=false, rotacionOvni=false,rotacionVueltaHecha=false;
    boolean escalarOvni = false,escalarFin=false,ovniAbajo = false,ovniArriba=false;
    boolean horaAbducir = false, finished = false;
    List<Point> puntoFigura;



    public Animacion() {
        setTitle("Animacion 20110374");
        setResizable(false);
        setSize(900, 600);
        setLayout(null);
        setVisible(true);
        bufferedImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        puntoFigura = new ArrayList<>();

        hilo = new Thread(() -> {

            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                
                try {
                   // while (xc <= dx || yc <= dy) {
                        repaint();
                        sleep(1);
                      /*  if (xc <= dx)
                            xc++;
                        if (yc <= dy)
                            yc++;
                    }
                    break;*/
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }//catch
            }//while
        });
    }//constructor


    public static void main(String[] args) {
        new Animacion();
    }///Main


    @Override
    public void paint(Graphics g) {
        //g.drawLine(100,100,200,200);
        if (fondo == null) {
            fondo = createImage(getWidth(), getHeight());
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0, 0, getWidth(), getHeight());
            ImageIcon fondito =new ImageIcon(new ImageIcon(getClass().getResource("img/BikiniBottom.jpg")).getImage());
            gfondo.drawImage(fondito.getImage(),0,0,getWidth(),getHeight(),this);
            background(gfondo);
            hilo.start();

        }

        update(g);
    }//paint

    public void background(Graphics g){
        //Squidward house
        Color squidwardHouse = new Color(77, 121, 168);
        rellenoScanLine(new int[][]{{350,200},{500,450}},g,squidwardHouse);
        rellenoScanLine(new int[][]{{325,250},{350,350}},g,squidwardHouse);
        rellenoScanLine(new int[][]{{500,250},{525,350}},g,squidwardHouse);
        Color squidwardHouseFace = new Color(4,18,49);
        rellenoScanLine(new int[][]{{360,225},{490,250}},g,squidwardHouseFace);
        rellenoScanLine(new int[][]{{400,225},{450,350}},g,squidwardHouseFace);
        Color squidwardWindows = new Color(109,140,230);
        dibujarCirculoPuntoMedio(370,280,25,squidwardWindows,g);
        dibujarCirculoPuntoMedio(480,280,25,squidwardWindows,g);
        floodFill(new Point(370,280),g,squidwardWindows);
        floodFill(new Point(480,280),g,squidwardWindows);
        Color squidwardDoor = new Color(172,127,73);
        rellenoScanLine(new int[][]{{400,400},{450,450}},g,squidwardDoor);
        //Patrick's housen
        Color patrickStick = new Color(164, 157, 61);
        drawElipse(180,375,3,20,patrickStick,g);
        floodFill(new Point(180,375),g,patrickStick);
        puntoFigura.clear();
        Color patrickHouse = new Color(50, 23, 28);
        dibujarCirculoPuntoMedio(180,430,45,patrickHouse,g);
        floodFill(new Point(180,420),g,patrickHouse);
        //Bob's house
        Color bobHouse = new Color(212,97,26);
        rellenoScanLine(new int[][]{{650,250},{825,450}},g,bobHouse);
        Color bobRoof = new Color(55,112,20);
        rellenoScanLine(new int[][]{{630,200},{845,250}},g,bobRoof);
        drawElipse(650,192,20,25,bobRoof,g);
        floodFill(new Point(650,192),g,bobRoof);
        drawElipse(710,192,20,25,bobRoof,g);
        floodFill(new Point(710,192),g,bobRoof);
        drawElipse(770,192,20,25,bobRoof,g);
        floodFill(new Point(770,192),g,bobRoof);
        drawElipse(826,192,20,25,bobRoof,g);
        floodFill(new Point(826,192),g,bobRoof);

        Color bobDoor = new Color(96,124,195);
        rellenoScanLine(new int[][]{{715,400},{765,450}},g,bobDoor);
        dibujarCirculoPuntoMedio(685,280,25,bobDoor,g);
        floodFill(new Point(685,280),g,bobDoor);
        dibujarCirculoPuntoMedio(785,360,25,bobDoor,g);
        floodFill(new Point(785,360),g,bobDoor);
        //street
        Color street = new Color(56,80,80);
        rellenoScanLine(new int[][]{{0,450},{getWidth(),getHeight()}},g,street);
        //Sun
        Color sun = new Color(249, 231, 42);
        dibujarCirculoPuntoMedio(500,100,radioSun,sun,g);
        floodFill(new Point(500,100),g,sun);
    }//background

    public void busmarine(Graphics g){
        puntoFigura.clear();
        /*Color bus = new Color(233,80,34);
        rellenoScanLine(new int[][]{{50+xBus,475},{200+xBus,550}},g,bus);
        rellenoScanLine(new int[][]{{175+xBus,485},{200+xBus,520}},g,Color.cyan);
        //wheels
        Color busWheels = new Color(237,238,241);
        dibujarCirculoPuntoMedio(75+xBus,550,20,busWheels,g);
        floodFill(new Point(75+xBus,550),g,busWheels);
        dibujarCirculoPuntoMedio(175+xBus,550,20,busWheels,g);
        floodFill(new Point(175+xBus,550),g,busWheels);*/
        //Sun
        Color sun = new Color(249, 231, 42);
        dibujarCirculoPuntoMedio(500,100,radioSun,sun,g);
        //floodFill(new Point(500,100),g,sun);
        ImageIcon fondito =new ImageIcon(new ImageIcon(getClass().getResource("img/Bus.png")).getImage());
        rellenoScanLine(new int[][]{{xBus+120,yBus+55},{xBus+280,yBus+80}},g,Color.RED);//Rojo
        rellenoScanLine(new int[][]{{xBus+120,yBus+85},{xBus+260,yBus+90}},g,Color.ORANGE);//Rojo
        rellenoScanLine(new int[][]{{xBus+100,yBus+75},{xBus+300,yBus+175}},g,Color.BLACK);
        dibujarCirculoPuntoMedio(xBus+150,yBus+175,15,Color.GRAY,g);
        dibujarCirculoPuntoMedio(xBus+275,yBus+175,15,Color.GRAY,g);
        rellenoScanLine(new int[][]{{xBus+120,yBus+85},{xBus+260,yBus+90}},g,Color.ORANGE);//Rojo
        rellenoScanLine(new int[][]{{xBus+120,yBus+95},{xBus+230,yBus+100}},g,Color.ORANGE);//Rojo
        rellenoScanLine(new int[][]{{xBus+120,yBus+105},{xBus+230,yBus+110}},g,Color.ORANGE);//Rojo
        rellenoScanLine(new int[][]{{xBus+120,yBus+115},{xBus+230,yBus+120}},g,Color.ORANGE);//Rojo
        rellenoScanLine(new int[][]{{xBus+120,yBus+125},{xBus+230,yBus+130}},g,Color.ORANGE);//Rojo
        rellenoScanLine(new int[][]{{xBus+120,yBus+135},{xBus+230,yBus+140}},g,Color.ORANGE);//Rojo





        floodFill(new Point(xBus+150,yBus+175),g,Color.gray);
        floodFill(new Point(xBus+275,yBus+175),g,Color.GRAY);
        rellenoScanLine(new int[][]{{xBus+250,yBus+100},{xBus+300,yBus+140}},g,Color.cyan);

        //g.drawImage(fondito.getImage(),xBus,yBus,300,200,this);
        if(!horaAbducir) {
            xBus++;
            if (xBus >= getHeight() + 250) {
                xBus = -250;
                contVueltaBus++;
            }
            if(contVueltaBus==1&&xBus==400)
                horaAbducir = true;
        }else if(horaAbducir){
            yBus--;
        }


        if(!flagSun)
            radioSun++;
        else
            radioSun--;
        if(radioSun==45)
            flagSun = true;
        if(radioSun == 15)
            flagSun = false;

    }//busmarine


    public void ovni(Graphics g){
        Color ovni = new Color(151, 163, 203);
        //Traslation
        int[][] identityTraslation = {
                {1, 0, xc},
                {0, 1, yc},
                {0, 0, 1}
        };
        //Coordenadas
        int[][] cuadTraslation = {
                {50, 100, 100, 50},
                {50, 50, 100, 100},
                {1, 1, 1, 1},
        };
        int[][] cuadDish = {
                {25, 125, 125, 25},
                {70, 70, 100, 100},
                {1, 1, 1, 1},
        };
        int[][] res = matrixMultiplication(identityTraslation, cuadTraslation);
        int[][] resDish = matrixMultiplication(identityTraslation, cuadDish);
        Color headOvni = new Color(2, 231, 200);
        Color dishOvni = new Color(143, 155, 197);

        if(!rotacionOvni&&!escalarOvni) {

            //head
            lineaBresenham(res[0][2], res[1][2], res[0][3], res[1][3], g, headOvni); //down
            lineaBresenham(res[0][3], res[1][3], res[0][0], res[1][0], g, headOvni); //izq
            lineaBresenham(res[0][1], res[1][1], res[0][2], res[1][2], g, headOvni); // der
            lineaBresenham(res[0][0], res[1][0], res[0][1], res[1][1], g, headOvni); // up
            rellenoScanLine(new int[][]{{res[0][0], res[1][0]}, {res[0][2], res[1][2]}}, g, headOvni);
            //dish
            lineaBresenham(resDish[0][0], resDish[1][0], resDish[0][1], resDish[1][1], g, dishOvni);//up
            lineaBresenham(resDish[0][2], resDish[1][2], resDish[0][3], resDish[1][3], g, dishOvni);//down
            lineaBresenham(resDish[0][3], resDish[1][3], resDish[0][0], resDish[1][0], g, dishOvni);//izq
            lineaBresenham(resDish[0][1], resDish[1][1], resDish[0][2], resDish[1][2], g, dishOvni);//der
            rellenoScanLine(new int[][]{{resDish[0][0], resDish[1][0]}, {resDish[0][2], resDish[1][2]}}, g, dishOvni);
        }else{
            //head
            lineaBresenham(0, 0, 0,0 , g, Color.white); //down

        }
        if(!flagOvniX)
            xc++;
        if(xc==500&&!flagOvniX){
            flagOvniX= true;
            flagOvniY = true;
        }
        if(flagOvniY)
            yc++;
        if(yc == 195) {
            flagOvniY = false;
            rotacionOvni = true;
        }

        if(rotacionOvni) {
            //
            double[][] identityRotation = {
                    {Math.cos(xOvni), -Math.sin(xOvni), 0},
                    {Math.sin(xOvni), Math.cos(xOvni), 0},
                    {0, 0, 1.0}
            };
            double[][] cuadTraslationDouble = {
                    {(double) res[0][0], (double)res[0][1],(double) res[0][2],(double) res[0][3]},
                    {(double)res[1][0], (double)res[1][1],(double) res[1][2], (double)res[1][3]},
                    {    1,     1,     1,     1},
            };
            for (int i = 0; i<cuadTraslation.length;i++)
                for (int j=0;j<cuadTraslation[0].length;j++)
                    cuadTraslationDouble[i][j] = (double) cuadTraslation[i][j];

            double[][] cuadDishDouble = {
                    {(double) resDish[0][0], (double) resDish[0][1],(double) resDish[0][2], (double) resDish[0][3]},
                    {(double) resDish[1][0], (double) resDish[1][1],(double) resDish[1][2], (double) resDish[1][3]},
                    {    1,     1,     1,     1},
            };
            for (int i = 0; i<cuadDish.length;i++)
                for (int j=0;j<cuadDish[0].length;j++)
                    cuadDishDouble[i][j] = (double) cuadDish[i][j];

            double[][] cuadRotationDouble = matrixMultiplicationDouble(identityRotation,cuadTraslationDouble);
            double[][] dishRotationDouble = matrixMultiplicationDouble(identityRotation,cuadDishDouble);
            int[][] resRot = new int[cuadRotationDouble.length][cuadRotationDouble[0].length];
            int[][] resRotDish = new int[cuadDish.length][cuadDish[0].length];

            for (int i = 0; i<cuadRotationDouble.length;i++)
                for (int j=0;j<cuadRotationDouble[0].length;j++)
                    resRot[i][j] = (int) Math.round(cuadRotationDouble[i][j]);

            for (int i = 0; i<dishRotationDouble.length;i++)
                for (int j=0;j<dishRotationDouble[0].length;j++)
                    resRotDish[i][j] = (int) Math.round(dishRotationDouble[i][j]);

            //head
            int origenX=495, origenY=200;
            /*lineaBresenham(resRot[0][2]+origenX, resRot[1][2]+origenY, resRot[0][3]+origenX, resRot[1][3]+origenY,g,headOvni); //down
            lineaBresenham(resRot[0][3]+origenX, resRot[1][3]+origenY, resRot[0][0]+origenX, resRot[1][0]+origenY,g,headOvni); //izq
            lineaBresenham(resRot[0][1]+origenX, resRot[1][1]+origenY, resRot[0][2]+origenX, resRot[1][2]+origenY,g,headOvni); // der
            lineaBresenham(resRot[0][0]+origenX, resRot[1][0]+origenY, resRot[0][1]+origenX, resRot[1][1]+origenY,g,headOvni); // up
            rellenoScanLine(new int[][]{{resRot[0][0]+origenX,resRot[1][0]+origenY},{resRot[0][2]+origenX,resRot[1][2]+origenY}},g,headOvni);
            rellenoScanLine(new int[][]{{resRot[0][2]+origenX,resRot[1][2]+origenY},{resRot[0][0]+origenX,resRot[1][0]+origenY}},g,headOvni);*/
            if(!rotacionVueltaHecha) {
                lineaBresenham(resRot[0][2]+origenX, resRot[1][2]+origenY, resRot[0][3]+origenX, resRot[1][3]+origenY,g,headOvni); //down
                lineaBresenham(resRot[0][3]+origenX, resRot[1][3]+origenY, resRot[0][0]+origenX, resRot[1][0]+origenY,g,headOvni); //izq
                lineaBresenham(resRot[0][1]+origenX, resRot[1][1]+origenY, resRot[0][2]+origenX, resRot[1][2]+origenY,g,headOvni); // der
                lineaBresenham(resRot[0][0]+origenX, resRot[1][0]+origenY, resRot[0][1]+origenX, resRot[1][1]+origenY,g,headOvni); // up
                //rellenoScanLine(new int[][]{{resRot[0][0]+origenX,resRot[1][0]+origenY},{resRot[0][2]+origenX,resRot[1][2]+origenY}},g,headOvni);
                g.setColor(headOvni);
                g.fillPolygon(new int[]{resRot[0][0] + origenX, resRot[0][1] + origenX, resRot[0][2] + origenX, resRot[0][3] + origenX}, new int[]{resRot[1][0] + origenY, resRot[1][1] + origenY, resRot[1][2] + origenY, resRot[1][3] + origenY}, 4);
                lineaBresenham(resRotDish[0][2]+origenX, resRotDish[1][2]+origenY, resRotDish[0][3]+origenX, resRotDish[1][3]+origenY,g,dishOvni); //down
                lineaBresenham(resRotDish[0][3]+origenX, resRotDish[1][3]+origenY, resRotDish[0][0]+origenX, resRotDish[1][0]+origenY,g,dishOvni); //izq
                lineaBresenham(resRotDish[0][1]+origenX, resRotDish[1][1]+origenY, resRotDish[0][2]+origenX, resRotDish[1][2]+origenY,g,dishOvni); // der
                lineaBresenham(resRotDish[0][0]+origenX, resRotDish[1][0]+origenY, resRotDish[0][1]+origenX, resRotDish[1][1]+origenY,g,dishOvni); // up
                //rellenoScanLine(new int[][]{{resRotDish[0][0]+origenX,resRotDish[1][0]+origenY},{resRotDish[0][2]+origenX,resRotDish[1][2]+origenY}},g,dishOvni);
                g.setColor(dishOvni);
                g.fillPolygon(new int[]{resRotDish[0][0] + origenX, resRotDish[0][1] + origenX, resRotDish[0][2] + origenX, resRotDish[0][3] + origenX}, new int[]{resRotDish[1][0] + origenY, resRotDish[1][1] + origenY, resRotDish[1][2] + origenY, resRotDish[1][3] + origenY}, 4);
            }
            if(!rotacionVueltaHecha)
                xOvni += Math.toRadians(0.5);
            System.out.println("xOvni: "+xOvni);
            if(xOvni== 6.268313177841316) {
                rotacionVueltaHecha = true;
                escalarOvni = true;
            }

        }//rotacion Ovni
        if(escalarOvni){
            escalarOvni(res,resDish,g);
        }//escalarOvni

        /*
        double[][] resAux = matrixMultiplicationDouble(identityRotation,cuad);
        int[][] res = new int[resAux.length][resAux[0].length];
        //convertir matriz a int
        for (int i = 0; i<resAux.length;i++)
            for (int j=0;j<resAux[0].length;j++)
                res[i][j] = (int) Math.round(resAux[i][j]);

        lineaBresenham(res[0][2]+200, res[1][2]+200, res[0][3]+400, res[1][3]+400,g,Color.RED);
        lineaBresenham(res[0][3]+200, res[1][3]+200, res[0][0]+400, res[1][0]+400,g,Color.RED);
        lineaBresenham(res[0][1]+200, res[1][1]+200, res[0][2]+400, res[1][2]+400,g,Color.RED);
        lineaBresenham(res[0][0]+200, res[1][0]+200, res[0][1]+400, res[1][1]+400,g,Color.RED);
        rellenoScanLine(new int[][]{{res[0][2]+400,res[1][2]+400},{res[0][3]+200,res[1][1]+200}},g,Color.GRAY);

        realizarCuadrado(new int[][]{{res[0][2]+400,res[1][2]+400},{res[0][3]+200,res[1][1]+200}},Color.GRAY,g);
        g.drawRect(res[0][2]+400,res[1][2]+400,50,50);
        g.fillRect(res[0][2]+200, res[1][2]+200,100,100);
        */
    }//ovni

    private void escalarOvni(int[][] res, int[][] resDish, Graphics g) {
        Color headOvni = new Color(2, 231, 200);
        Color dishOvni = new Color(143, 155, 197);

        //Escalar
        double[][] identityEsc = {
                {xExc,   0,   0},
                {  0, yEsc,   0},
                {  0,   0, 1.0}
        };
        double[][] cuadTraslationDouble = {
                {(double) res[0][0], (double)res[0][1],(double) res[0][2],(double) res[0][3]},
                {(double)res[1][0], (double)res[1][1],(double) res[1][2], (double)res[1][3]},
                {    1,     1,     1,     1},
        };
        for (int i = 0; i<res.length;i++)
            for (int j=0;j<res[0].length;j++)
                cuadTraslationDouble[i][j] = (double) res[i][j];

        double[][] cuadDishDouble = {
                {(double) resDish[0][0], (double) resDish[0][1],(double) resDish[0][2], (double) resDish[0][3]},
                {(double) resDish[1][0], (double) resDish[1][1],(double) resDish[1][2], (double) resDish[1][3]},
                {    1,     1,     1,     1},
        };
        for (int i = 0; i<resDish.length;i++)
            for (int j=0;j<resDish[0].length;j++)
                cuadDishDouble[i][j] = (double) resDish[i][j];

        double[][] cuadRotationDouble = matrixMultiplicationDouble(identityEsc,cuadTraslationDouble);
        double[][] dishRotationDouble = matrixMultiplicationDouble(identityEsc,cuadDishDouble);
        int[][] resHeadEsc = new int[res.length][res[0].length];
        int[][] resDishEsc = new int[resDish.length][resDish[0].length];

        for (int i = 0; i<cuadRotationDouble.length;i++)
            for (int j=0;j<cuadRotationDouble[0].length;j++)
                resHeadEsc[i][j] = (int) Math.round(cuadRotationDouble[i][j]);

        for (int i = 0; i<dishRotationDouble.length;i++)
            for (int j=0;j<dishRotationDouble[0].length;j++)
                resDishEsc[i][j] = (int) Math.round(dishRotationDouble[i][j]);
        if(!escalarFin) {
            //head
            lineaBresenham(res[0][0] - xCntr, res[1][0] - yCntr, res[0][1] + xCntr, res[1][1] + yCntr, g, headOvni);//up
            lineaBresenham(res[0][2] + xCntr, res[1][2] + yCntr, res[0][3] + xCntr, res[1][3] + yCntr, g, headOvni);//down
            lineaBresenham(res[0][3] - xCntr, res[1][3] - yCntr, res[0][0] - xCntr, res[1][0] - yCntr, g, headOvni);//izq
            lineaBresenham(res[0][1] + xCntr, res[1][1] + yCntr, res[0][2] + xCntr, res[1][2] + yCntr, g, headOvni);//der
            rellenoScanLine(new int[][]{{res[0][0] - xCntr, res[1][0] - yCntr}, {res[0][2] + xCntr, res[1][2] + yCntr}}, g, headOvni);
            //dish
            lineaBresenham(resDish[0][0] - xCntr, resDish[1][0] + yCntr, resDish[0][1] + xCntr, resDish[1][1] + yCntr, g, dishOvni);//up
            lineaBresenham(resDish[0][2] + xCntr, resDish[1][2] + yCntr, resDish[0][3] + xCntr, resDish[1][3] + yCntr, g, dishOvni);//down
            lineaBresenham(resDish[0][3] - xCntr, resDish[1][3] + yCntr, resDish[0][0] - xCntr, resDish[1][0] + yCntr, g, dishOvni);//izq
            lineaBresenham(resDish[0][1] + xCntr, resDish[1][1] + yCntr, resDish[0][2] + xCntr, resDish[1][2] + yCntr, g, dishOvni);//der
            rellenoScanLine(new int[][]{{resDish[0][0] - xCntr, resDish[1][0] - yCntr}, {resDish[0][2] + xCntr, resDish[1][2] + yCntr}}, g, dishOvni);
        }else{
            lineaBresenham(0,0,0,0, g, dishOvni);//up

        }
        if(xCntr<=20)
            xCntr++;
        if(yCntr<=20)
            yCntr++;
        if(xCntr==20&&yCntr==20){
            escalarFin = true;
            ovniAbajo = true;
        }
        if(ovniAbajo&&!horaAbducir){
            //head
            rellenoScanLine(new int[][]{{res[0][0] - xCntr, res[1][0] - yCntr+yArriba}, {res[0][2] + xCntr, res[1][2] + yCntr+yArriba}}, g, headOvni);
            //dish
            rellenoScanLine(new int[][]{{resDish[0][0] - xCntr, resDish[1][0] - yCntr+yArriba}, {resDish[0][2] + xCntr, resDish[1][2] + yCntr+yArriba}}, g, dishOvni);
            if(yArriba<=40) {
                yArriba++;
            }else{
                ovniArriba = true;

            }
        }//ovniAbajo
        if(horaAbducir){
            Color rayoAbducir = new Color(196, 241, 236, 53);
            //rayo
            rellenoScanLine(new int[][]{{res[0][0] - xCntr, res[1][0] - yCntr+yArriba+50}, {res[0][2] + xCntr, res[1][2] + yCntr+yArriba+200}}, g, rayoAbducir);
            //head
            rellenoScanLine(new int[][]{{res[0][0] - xCntr, res[1][0] - yCntr+yArriba}, {res[0][2] + xCntr, res[1][2] + yCntr+yArriba}}, g, headOvni);
            //dish
            rellenoScanLine(new int[][]{{resDish[0][0] - xCntr, resDish[1][0] - yCntr+yArriba}, {resDish[0][2] + xCntr, resDish[1][2] + yCntr+yArriba}}, g, dishOvni);


            if(yArriba>=-650)
                yArriba--;
            else
                finished = true;
        }


    }//escalarOvni

    public void dibujarCirculoPuntoMedio(int xc, int yc,int r,Color c,Graphics g) {
        // Punto inicial del círculo
        int x = 0;
        int y = r;
        // Cálcular el parámetro inicial de decisión
        int pk = 1-r;

        // verificar el pk para determinar las posiciones de pixel siguientes
        while (x<=y) {
            putPixel(xc+x,yc+y,c,g);
            puntoFigura.add(new Point(xc+x, yc+y));
            putPixel(xc-x,yc-y,c,g);
            puntoFigura.add(new Point(xc-x, yc-y));
            putPixel(xc+x,yc-y,c,g);
            puntoFigura.add(new Point(xc+x, yc-y));
            putPixel(xc-x,yc+y,c,g);
            puntoFigura.add(new Point(xc-x, yc+y));
            putPixel(xc+y,yc+x,c,g);
            puntoFigura.add(new Point(xc+y, yc+x));
            putPixel(xc-y,yc-x,c,g);
            puntoFigura.add(new Point(xc-y, yc-x));
            putPixel(xc+y,yc-x,c,g);
            puntoFigura.add(new Point(xc+y, yc-x));
            putPixel(xc-y,yc+x,c,g);
            puntoFigura.add(new Point(xc-y, yc+x));


            if (pk<0){
                pk+=2*(x+1)+1;
                x++;
            } // pk>=0
            else {
                pk+=2*(x+1)+1 - 2*(y-1);
                x++;
                y--;
            }
        }//while
    }//dibujarCirculoPuntoMedio


    public void drawElipse(int xc, int yc, int rx, int ry,Color c,Graphics g){
        int x=xc-rx;
        int y=yc;
        putPixel(x,y,c,g);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){
            x= (int) (xc + (rx * Math.sin(i)));
            y= (int) (yc + (ry * Math.cos(i)));
            putPixel(x,y,c,g);
            puntoFigura.add(new Point(x,y));

        }//for
    }//drawElipse


    public void update(Graphics g) {

        g.setClip(0, 0, getWidth(), getHeight());
        //regenerar la imagen de fondo
        //crear la imagen estatica
        buffer = createImage(getWidth(), getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0, 0, getWidth(), getHeight());
        gbuffer.drawImage(fondo, 0, 0, this);

        busmarine(gbuffer);
        ovni(gbuffer);
        g.drawImage(buffer, 0, 0, this); //doble buffer
        if(finished) {
            xc = 0;
            yc =0;
            xBus=-250;
            yBus = 400;
            xOvni = 0.5;
            xExc=1.0; yEsc=1.0;
            radioSun = 15;
            xCntr = 0;
            yCntr = 0;
            yArriba = 0;
            contVueltaBus =0;
            flagSun = false;
            flagOvniX= false;
            flagOvniY=false;
            rotacionOvni=false;
            rotacionVueltaHecha=false;
            escalarOvni = false;
            escalarFin=false;
            ovniAbajo = false;
            ovniArriba=false;
            horaAbducir = false;
            finished = false;
        }

    }//update

    public void putPixel(int x, int y, Color c, Graphics g) {
        bufferedImage.setRGB(0, 0, c.getRGB());
        g.drawImage(bufferedImage, x, y, this);

    }//putPixel

    private void lineaBresenham(int x0, int y0, int x1, int y1, Graphics g, Color c) {
        int dx = (x1 - x0);
        int dy = (y1 - y0);
        int stepY, stepX, pk;
        int xk = x0;
        int yk = y0;
        // determinar punto de partida y fin
        if (dy < 0) {
            dy = -dy;
            stepY = -1;
        } else
            stepY = 1;
        if (dx < 0) {
            dx = -dx;
            stepX = -1;
        } else
            stepX = 1;
        putPixel(xk, yk, c, g);
        /* se cicla hasta llegar al extremo de la línea */
        if (dx > dy) {
            pk = 2 * dy - dx;
            while (xk != x1) {
                xk += stepX;
                if (pk < 0)
                    pk += 2 * dy;
                else {
                    yk += stepY;
                    pk += 2 * (dy - dx);
                }
                putPixel(xk, yk, c, g);
            }
        } else {
            pk = 2 * dx - dy;
            while (yk != y1) {
                yk += stepY;
                if (pk < 0)
                    pk += 2 * dx;
                else {
                    xk += stepX;
                    pk += 2 * (dx - dy);
                }
                putPixel(xk, yk, c, g);

            }
        }
    }//lineaBresenham

    private int[][] matrixMultiplication(int[][] a, int[][] b) {
        if (a[0].length == b.length) {
            int[][] c = new int[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < b.length; k++)
                        c[i][j] += a[i][k] * b[k][j];
                }
            }
            return c;
        } else {
            System.out.println("No Multiplicable");
            return a;
        }
    }//matrixMultiplication

    private double[][] matrixMultiplicationDouble(double[][] a, double[][] b) {
        if (a[0].length == b.length) {
            double[][] c = new double[a.length][b[0].length];
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < b[0].length; j++) {
                    for (int k = 0; k < b.length; k++)
                        c[i][j] += a[i][k] * b[k][j];
                }
            }
            return c;
        } else {
            System.out.println("No Multiplicable");
            return a;
        }
    }//matrixMultiplication

    public void floodFill(Point punto, Graphics g, Color c) {

        if (!puntoFigura.contains(punto)) {
            putPixel(punto.x, punto.y, c, g);
            puntoFigura.add(punto);

            //System.out.println(String.valueOf(punto[0])+","+String.valueOf(punto[1]));
            floodFill(new Point(punto.x, punto.y - 1), g, c);
            floodFill(new Point(punto.x + 1, punto.y), g, c);
            floodFill(new Point(punto.x, punto.y + 1), g, c);
            floodFill(new Point(punto.x - 1, punto.y), g, c);

        }
        return;


    }//floodFill

    private void realizarCuadrado(int[][] coordenades, Color c, Graphics g){
        lineaBresenham(coordenades[0][0],coordenades[0][1],coordenades[1][0],coordenades[0][1],g,c); //up
        lineaBresenham(coordenades[0][0],coordenades[1][1],coordenades[1][0],coordenades[1][1],g,c); //down
        lineaBresenham(coordenades[0][0],coordenades[0][1],coordenades[0][0],coordenades[1][1],g,c); //left
        lineaBresenham(coordenades[1][0],coordenades[0][1],coordenades[1][0],coordenades[1][1],g,c); //right
    }


    private void rellenoScanLine(int[][] figura, Graphics g, Color c) {
        int [][] figure = new int[][]{
                {figura[0][0],figura[1][0],figura[0][0],figura[1][0]},
                {figura[0][1],figura[0][1],figura[1][1],figura[1][1]},
                {1,1,1,1}
        };

        for (int i = 0; i <= figure[1][3] - figure[1][0]; i++) {
            lineaBresenham(figure[0][0], figure[1][0] + i, figure[0][1], figure[1][1] + i, g, c);
        }


    }
}//rellenoScanLine
