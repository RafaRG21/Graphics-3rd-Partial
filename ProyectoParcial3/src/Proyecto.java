
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class Proyecto extends JPanel implements Runnable{
    double fps = 60.0;
    double time = 1000/fps;
    private Image fondo;
    private Thread hilo;
    private BufferedImage bufferedImage;
    private boolean firstTime = true;
    private Image buffer;

    private boolean horaAbducir = false, flagOvniX=false,flagOvniY=false,rotacionOvni=false,rotacionVueltaHecha=false;
    private boolean escalarOvni = false, escalarFin= false, ovniAbajo = false,ovniArriba=false, finished= false;
    private int contVueltaBus=0;
    private double xMov = 900, yMov = 0, zMov = 160;
    private double tx = -300, ty = 20, tz = 1;
    private double txOvni = 0, tyOvni=-170, tzOvni=1;
    private double tzOvniRot=0.5;
    private  double txOvniEsc=1, tyOvniEsc=1, tzOvniEsc=1;
    int yArriba = 0;

    double bus[][] = {
            //A  B    C   D  E   F    G   H
            {200, 300, 300, 200,   0, 100, 100,   0},//x
            {300, 300, 200, 200, 300, 300, 200, 200},//y
            {200, 200, 200, 200, 100, 100, 100, 100},//z
            {  1,   1,   1,   1,   1,   1,   1,   1,}

    };
    double busFlama[][] = {
            //A  B    C   D  E   F    G   H
            {150, 200, 200, 150,   0, 50, 50,   0},//x
            {200, 200, 150, 150, 200, 200, 150, 150},//y
            {150, 150, 150, 150,  50,  50, 50, 50},//z
            {  1,   1,   1,   1,   1,   1,   1,   1,}

    };
    double puntosOvni[][] = {
            //A  B    C   D  E   F    G   H
            /*{75, 100, 100, 75,   0,  25, 25,   0},//x
            {100, 100, 75, 75,   75, 75, 50, 50},//y
            {75, 75, 75, 75,  25,  25, 25, 25},//z
            {  1,   1,   1,   1,   1,   1,   1,   1,}*/
            {150, 200, 200, 150,   0, 50, 50,   0},//x
            {200, 200, 150, 150, 200, 200, 150, 150},//y
            {150, 150, 150, 150,  50,  50, 50, 50},//z
            {  1,   1,   1,   1,   1,   1,   1,   1,}

    };
    double rayoOvni[][] = {
            //A  B    C   D  E   F    G   H
            /*{75, 100, 100, 75,   0,  25, 25,   0},//x
            {100, 100, 75, 75,   75, 75, 50, 50},//y
            {75, 75, 75, 75,  25,  25, 25, 25},//z
            {  1,   1,   1,   1,   1,   1,   1,   1,}*/
            {150, 200, 200, 150,   0, 50, 50,   0},//x
            {200, 200, 100, 100, 200, 200, 100, 100},//y
            {150, 150, 150, 150,  50,  50, 50, 50},//z
            {  1,   1,   1,   1,   1,   1,   1,   1,}

    };




    @Override
    public void run() {
        while (true){
            try{
                while(true) {
                    repaint();
                    hilo.sleep((int) time);

                }
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }//catch
        }//while
    }//run


    public Proyecto(){

        setSize(900,600);
        setVisible(true);
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        hilo =  new Thread(this);
        hilo.start();

    }//constructor


    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Proyecto Parcial 3 20110374");
        frame.setResizable(false);
        frame.setBackground(new Color(113,220,240,255));
        Proyecto proyecto = new Proyecto();
        proyecto.setDoubleBuffered(true);
        frame.add(proyecto);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }///Main


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(fondo == null){
            fondo = createImage(getWidth(), getHeight());
            Graphics gbackground = fondo.getGraphics();
            gbackground.setClip(0, 0, getWidth(), getHeight());
            background(gbackground);
        }
        //g.drawLine(100,100,200,200);;
        update(g);
    }//paintComponent
    @Override
    public Dimension getPreferredSize() {

        return new Dimension(900, 600);

    }//getPreferrefSize

    public void update(Graphics g){
        //Coordenadas
        buffer = createImage(getWidth(), getHeight());
        Graphics gbuffer = buffer.getGraphics();
        gbuffer.setClip(0, 0, getWidth(), getHeight());
        g.drawImage(fondo, 0, 0, this);
        bus(g);
        ovni(g);
        if(finished){
            horaAbducir = false;
            flagOvniX=false;
            flagOvniY=false;
            rotacionOvni=false;
            rotacionVueltaHecha=false;
            escalarOvni = false;
            escalarFin= false;
            ovniAbajo = false;
            ovniArriba=false;
            finished= false;
            contVueltaBus=0;
            xMov = 900;
            yMov = 0;
            zMov = 160;
            tx = -300;
            ty = 20;
            tz = 1;
            txOvni = 0;
            tyOvni=-170;
            tzOvni=1;
            tzOvniRot=0.5;
            txOvniEsc=1;
            tyOvniEsc=1;
            tzOvniEsc=1;
            yArriba = 0;
        }


    }//update
    public void bus(Graphics g){
        double[][] identity = {
                {1,0,0,tx},
                {0,1,0,ty},
                {0,0,1,tz},
                {0,0,0,1}
        };
        int numhilos = Runtime.getRuntime().availableProcessors();
        double[][] busres = MatrixMultiplication.multiplicarExecutorService(identity,bus,numhilos);
        double[][] busFlamares = MatrixMultiplication.multiplicarExecutorService(identity,busFlama,numhilos);
        //llantas
        dibujarCirculoPuntoMedio((int) (100+tx), (int) (520+ty),30,Color.BLACK,g);
        dibujarCirculoPuntoMedio((int) (250+tx), (int) (535+ty),30,Color.BLACK,g);

        //bus
        Color blacktop = new Color(46, 37, 37);
        Color blackleft = new Color(28, 27, 27);
        Color blackcenter = new Color(64, 64, 64);
        Color blackLine = new Color(0, 0, 0);
        drawCube(busres,new double[]{0,-2,10},0,200,g,blackLine,blacktop,blackcenter,blackcenter,blackleft,true,false);
        //arriba del bus
        Color redtop = new Color(232, 32, 32);
        Color redleft = new Color(185, 34, 34);
        Color redcenter = new Color(154, 26, 26);
        Color redLine = new Color(129, 22, 22);
        drawCube(busFlamares,new double[]{0,-2,10},30,210,g,redLine,redleft,redcenter,redcenter,redtop,true,false);
        //ventana
        //drawCube(busVentana,new double[]{-3,-2,10},300,110,g,redLine,redleft,redcenter,redcenter,redtop);
        rellenoScanLine(new int[][]{{(int) (215+tx), (int) (ty+450)},{(int) (tx+290), (int) (ty+500)}},g,Color.cyan);
        //calcomania
        Color opaco = new Color(255, 255, 255, 0);
        drawCube(busFlamares,new double[]{0,-2,10},30,280,g,opaco,opaco,opaco,Color.YELLOW,opaco,false,false);
        //llantas delanteras
        dibujarCirculoPuntoMedio((int) (40+tx), (int) (530+ty),30,Color.BLACK,g);
        dibujarCirculoPuntoMedio((int) (160+tx), (int) (545+ty),30,Color.BLACK,g);
        if(!horaAbducir) {
            tx++;
            if (tx >= getHeight() + 300) {
                tx = -300;
                contVueltaBus++;
            }
            if(contVueltaBus==1&&tx==400)
                horaAbducir = true;
        }else if(horaAbducir){
            ty--;
        }

    }//bus
    private void ovni(Graphics g){

            double[][] identity = {
                    {1, 0, 0, txOvni},
                    {0, 1, 0, tyOvni},
                    {0, 0, 1, tzOvni},
                    {0, 0, 0, 1}
            };
            int numhilos = Runtime.getRuntime().availableProcessors();
            double[][] ovnires = MatrixMultiplication.multiplicarExecutorService(identity, puntosOvni, numhilos);
        Color dishOvni = new Color(143, 155, 197);

        if(!rotacionOvni&&!escalarOvni) {
            drawCube(ovnires, new double[]{13, -3, 10}, 51, 50, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, Color.DARK_GRAY, false, false);//dere lado de center
            drawCube(ovnires, new double[]{10, -7, 10}, -15, 30, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);//centro
            //dibujarCirculoPuntoMedio(50, 50, 20, Color.CYAN, g);
            //derecha
            drawCube(ovnires, new double[]{13, -3, 10}, 102, 50, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, dishOvni, false, false);
            //derecha arrriba abajo
            drawCube(ovnires, new double[]{13, -3, 10}, 102, 100, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);

            drawCube(ovnires, new double[]{13, -3, 10}, 102, 0, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            //izquierda
            drawCube(ovnires, new double[]{13, -3, 10}, -51, 50, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(ovnires, new double[]{13, -3, 10}, -102, 100, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);

            drawCube(ovnires, new double[]{13, -3, 10}, -102, 50, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            //izq arrriba abajo
            drawCube(ovnires, new double[]{13, -3, 10}, -102, 0, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            if (!flagOvniX)
                txOvni++;
            if (txOvni == 500 && !flagOvniX) {
                flagOvniX = true;
                flagOvniY = true;
            }
            if (flagOvniY)
                tyOvni++;
            if (tyOvni == 10) {
                flagOvniY = false;
                rotacionOvni = true;
            }
            //perspectiva
            //drawCube(ovnires,new double[]{30,30,30},0,50,g,Color.ORANGE,Color.red,Color.BLACK,Color.YELLOW,Color.DARK_GRAY,true,true);
        }else{
            lineaBresenham(0,0,0,0,g,Color.WHITE);
        }
        if(rotacionOvni&&escalarOvni==false){
            double[][] identityRot = {
                    {Math.cos(tzOvniRot),0,-Math.sin(tzOvniRot),0},
                    {0,1,0,0},
                    {Math.sin(tzOvniRot),0,Math.cos(tzOvniRot),0},
                    {0,0,0,1}
            };
            double[][] ovniresRot = MatrixMultiplication.multiplicarExecutorService(identityRot,ovnires,numhilos);
            drawCube(ovniresRot,new double[]{13,-3,10},51,50,g,Color.ORANGE,dishOvni,dishOvni,dishOvni,Color.DARK_GRAY,false,false);//dere lado de center
            drawCube(ovniresRot,new double[]{10,-7,10},-15,30,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,Color.DARK_GRAY,false,false);//centro
           // dibujarCirculoPuntoMedio(50,50,20,Color.CYAN,g);
            //derecha
            drawCube(ovniresRot,new double[]{13,-3,10},102,50,g,Color.ORANGE,dishOvni,dishOvni,dishOvni,dishOvni,false,false);
            //derecha arrriba abajo
            drawCube(ovniresRot,new double[]{13,-3,10},102,100,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,dishOvni,false,false);

            drawCube(ovniresRot,new double[]{13,-3,10},102,0,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,Color.DARK_GRAY,false,false);
            //izquierda
            drawCube(ovniresRot,new double[]{13,-3,10},-51,50,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,Color.DARK_GRAY,false,false);
            drawCube(ovniresRot,new double[]{13,-3,10},-102,100,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,dishOvni,false,false);

            drawCube(ovniresRot,new double[]{13,-3,10},-102,50,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,Color.DARK_GRAY,false,false);
            //izq arrriba abajo
            drawCube(ovniresRot,new double[]{13,-3,10},-102,0,g,Color.ORANGE,dishOvni,dishOvni,Color.YELLOW,Color.DARK_GRAY,false,false);
            if(!rotacionVueltaHecha)
                tzOvniRot += Math.toRadians(0.5);
            if(tzOvniRot== 6.268313177841316) {
                System.out.println(tzOvniRot);
                rotacionVueltaHecha = true;
                escalarOvni = true;
            }

        }
        if(escalarOvni){
            escalarOvni(ovnires,g);
        }

    }//ovni
    private void escalarOvni(double[][] res, Graphics g){
        double[][] identity = {
                {txOvniEsc,0,0,0},
                {0,tyOvniEsc,0,0},
                {0,0,tzOvniEsc,0},
                {0,0,0,1}
        };
        int numhilos = Runtime.getRuntime().availableProcessors();
        double[][] resEsc = MatrixMultiplication.multiplicarExecutorService(identity,res,numhilos);
        Color dishOvni = new Color(143, 155, 197);
        int aumento = 0;
        if(!escalarFin) {

            drawCube(resEsc, new double[]{13, -3, 10}, 51+aumento, 50+aumento, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, Color.DARK_GRAY, false, false);//dere lado de center
            //
            dibujarCirculoPuntoMedio(50, 50, 20, Color.CYAN, g);
            //derecha
            drawCube(resEsc, new double[]{13, -3, 10}, 102 + aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, dishOvni, false, false);
            //derecha arrriba abajo
            drawCube(resEsc, new double[]{13, -3, 10}, 102 + aumento, 100 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);

            drawCube(resEsc, new double[]{13, -3, 10}, 102 + aumento, 0 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            //izquierda
            drawCube(resEsc, new double[]{13, -3, 10}, -51 - aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(resEsc, new double[]{13, -3, 10}, -102 + aumento, 100 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);
            drawCube(resEsc, new double[]{10, -7, 10}, -15-aumento, 30+aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);//centro

            drawCube(resEsc, new double[]{13, -3, 10}, -102 - aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(resEsc, new double[]{13, -3, 10}, -102 - aumento, 0 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);

        }else{
            lineaBresenham(0,0,0,0,g,Color.WHITE);
        }
        if(txOvniEsc<=1.2) {
            txOvniEsc += 0.02;
            System.out.println("Se txOvniEsc: "+txOvniEsc);

            aumento++;
        }
        if(tyOvniEsc<=1.2)
            tyOvniEsc+=0.02;
        if(txOvniEsc==1.2000000000000002&&tyOvniEsc==1.2000000000000002){
            System.out.println("Se cumplio");
            escalarFin = true;
            ovniAbajo = true;
        }
        if(ovniAbajo&&!horaAbducir){
            double[][] identityTrasla = {
                    {1, 0, 0, 1},
                    {0, 1, 0, yArriba},
                    {0, 0, 1, 1},
                    {0, 0, 0, 1}
            };
            numhilos = Runtime.getRuntime().availableProcessors();
            double[][] ovnires = MatrixMultiplication.multiplicarExecutorService(identityTrasla, resEsc, numhilos);
            //head
            drawCube(ovnires, new double[]{13, -3, 10}, 51+aumento, 50+aumento, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, Color.DARK_GRAY, false, false);//dere lado de center
            //
            //derecha
            drawCube(ovnires, new double[]{13, -3, 10}, 102 + aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, dishOvni, false, false);
            //derecha arrriba abajo
            drawCube(ovnires, new double[]{13, -3, 10}, 102 + aumento, 100 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);

            drawCube(ovnires, new double[]{13, -3, 10}, 102 + aumento, 0 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            //izquierda
            drawCube(ovnires, new double[]{13, -3, 10}, -51 - aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(ovnires, new double[]{13, -3, 10}, -102 + aumento, 100 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);
            drawCube(ovnires, new double[]{10, -7, 10}, -15-aumento, 30+aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);//centro

            drawCube(ovnires, new double[]{13, -3, 10}, -102 - aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(ovnires, new double[]{13, -3, 10}, -102 - aumento, 0 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            if(yArriba<=40) {
                yArriba++;
            }else{
                ovniArriba = true;

            }
        }//ovniAbajo
        if(horaAbducir){
            Color rayoAbducir = new Color(196, 241, 236, 53);
            double[][] identityTrasla = {
                    {1, 0, 0, 0},
                    {0, 1, 0, yArriba},
                    {0, 0, 1, 0},
                    {0, 0, 0, 1}
            };
            double[][] orayores = MatrixMultiplication.multiplicarExecutorService(identityTrasla, rayoOvni, numhilos);

            drawCube(orayores, new double[]{15, -5, 10}, -102 , 0, g, Color.GRAY, Color.CYAN, Color.CYAN, Color.CYAN, Color.CYAN, true, false);
            rellenoScanLine(new int[][]{{(int) res[0][0], (int) (res[1][0]+yArriba+50)}, {(int) res[0][2], (int) (res[1][2] +yArriba+200)}}, g, rayoAbducir);
            numhilos = Runtime.getRuntime().availableProcessors();
            double[][] ovnires = MatrixMultiplication.multiplicarExecutorService(identityTrasla, resEsc, numhilos);
            //head
            drawCube(ovnires, new double[]{13, -3, 10}, 51+aumento, 50+aumento, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, Color.DARK_GRAY, false, false);//dere lado de center
            //
            //derecha
            drawCube(ovnires, new double[]{13, -3, 10}, 102 + aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, dishOvni, dishOvni, false, false);
            //derecha arrriba abajo
            drawCube(ovnires, new double[]{13, -3, 10}, 102 + aumento, 100 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);

            drawCube(ovnires, new double[]{13, -3, 10}, 102 + aumento, 0 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            //izquierda
            drawCube(ovnires, new double[]{13, -3, 10}, -51 - aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(ovnires, new double[]{13, -3, 10}, -102 + aumento, 100 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, dishOvni, false, false);
            drawCube(ovnires, new double[]{10, -7, 10}, -15-aumento, 30+aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);//centro

            drawCube(ovnires, new double[]{13, -3, 10}, -102 - aumento, 50 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);
            drawCube(ovnires, new double[]{13, -3, 10}, -102 - aumento, 0 + aumento, g, Color.ORANGE, dishOvni, dishOvni, Color.YELLOW, Color.DARK_GRAY, false, false);



            if(yArriba>=-650)
                yArriba--;
            else
                finished = true;
        }

    }//escalarOvni
    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);

    }//putPixel
    public void background(Graphics g){
        //Squidward house
        Color squidwardHouse = new Color(77, 121, 168);
        Color squidwardHouseFace = new Color(4,18,49);
        double squidwardHousePoints[][] = {
                //A     B    C   D     E   F    G   H
                {150, 330, 330, 150,   0, 180,  180,   0},//x
                {200, 200, -20, -20, 200, 200, -20, -20},//y
                {150, 150, 150, 150,  50,  50, 50, 50},//z
                {  1,   1,   1,   1,   1,   1,   1,   1,}
        };
        double squidwardHousePointsEars[][] = {
                //A     B    C   D     E   F    G   H
                {150, 190, 190, 150,   0, 40,  40,   0},//x
                {200, 200, 120, 120, 200, 200, 120, 120},//y
                {150, 150, 150, 150,  50,  50, 50, 50},//z
                {  1,   1,   1,   1,   1,   1,   1,   1,}
        };
        double squidwardHousePointsDoor[][] = {
                //A     B    C   D     E   F    G   H
                {150, 190, 190, 150,   0, 40,  40,   0},//x
                {200, 200, 160, 160, 200, 200, 160, 160},//y
                {150, 150, 150, 150,  50,  50, 50, 50},//z
                {  1,   1,   1,   1,   1,   1,   1,   1,}
        };
        double bobHousePointsRoof[][] = {
                //A     B    C   D     E   F    G   H
                {150, 370, 370, 150,   0, 220,  220,   0},//x
                {200, 200, 150, 150, 200, 200, 150, 150},//y
                {150, 150, 150, 150,  50,  50, 50, 50},//z
                {  1,   1,   1,   1,   1,   1,   1,   1,}
        };


        //rellenoScanLine(new int[][]{{350,200},{500,450}},g,squidwardHouse);
       // rellenoScanLine(new int[][]{{325,250},{350,350}},g,squidwardHouse);
       // rellenoScanLine(new int[][]{{500,250},{525,350}},g,squidwardHouse);
        Color dawn =  new Color(228, 151, 89);
        //Patrick's housen
        Color patrickStick = new Color(164, 157, 61);
        drawElipse(180,375,3,20,patrickStick,g);
        Color patrickHouse = new Color(50, 23, 28);
        dibujarCirculoPuntoMedio(180,430,45,patrickHouse,g);
        //street
        Color street = new Color(56,80,80);
        rellenoScanLine(new int[][]{{0,450},{getWidth(),getHeight()}},g,street);
        //Bob's house
        Color bobHouse = new Color(212,97,26);
        Color bobHouseLine = new Color(159, 86, 41);

        //rellenoScanLine(new int[][]{{650,250},{825,450}},g,bobHouse);
        drawCube(squidwardHousePoints, new double[]{13, -3, 10}, 700, 240, g, bobHouseLine, bobHouse, bobHouse, dawn, Color.DARK_GRAY, true, false);

        Color bobRoof = new Color(55,112,20);
        //rellenoScanLine(new int[][]{{630,200},{845,250}},g,bobRoof);
        drawCube(bobHousePointsRoof, new double[]{13, -3, 10}, 680, 19, g, Color.black, bobRoof, bobRoof, dawn, Color.DARK_GRAY, true, false);



        Color bobDoor = new Color(96,124,195);
       // rellenoScanLine(new int[][]{{715,400},{765,450}},g,bobDoor);
        dibujarCirculoPuntoMedio(695,300,25,bobDoor,g);
        dibujarCirculoPuntoMedio(795,380,25,bobDoor,g);
        drawCube(squidwardHousePointsDoor, new double[]{14.5, -.3, 10}, 800, 280, g, Color.BLACK, bobDoor, bobDoor, Color.DARK_GRAY, Color.DARK_GRAY, true, false);


        //Squidward
        drawCube(squidwardHousePointsEars, new double[]{13, -3, 10}, 580, 140, g, squidwardHouseFace, squidwardHouse, squidwardHouse, squidwardHouse, Color.DARK_GRAY, true, false);
        drawCube(squidwardHousePoints, new double[]{13, -3, 10}, 400, 240, g, squidwardHouseFace, squidwardHouse, squidwardHouse, dawn, Color.DARK_GRAY, true, false);
        drawCube(squidwardHousePointsEars, new double[]{13, -3, 10}, 360, 140, g, squidwardHouseFace, squidwardHouse, squidwardHouse, dawn, Color.DARK_GRAY, true, false);

        rellenoScanLine(new int[][]{{378,265},{508,290}},g,squidwardHouseFace);
        rellenoScanLine(new int[][]{{418,265},{468,390}},g,squidwardHouseFace);
        Color squidwardWindows = new Color(109,140,230);
        dibujarCirculoPuntoMedio(390,320,25,squidwardWindows,g);
        dibujarCirculoPuntoMedio(500,320,25,squidwardWindows,g);
        Color squidwardDoor = new Color(172,127,73);
        Color squidwardDoorDarker = new Color(110, 83, 53);
        //rellenoScanLine(new int[][]{{400,400},{450,450}},g,squidwardDoor);
        drawCube(squidwardHousePointsDoor, new double[]{14.5, -.3, 10}, 485, 280, g, Color.black, squidwardDoor, squidwardDoor, squidwardDoorDarker, Color.DARK_GRAY, true, false);

        //Sun
        Color sun = new Color(178,67,66);
        dibujarCirculoPuntoMedio(0,70,100,sun,g);

    }//background


    public void drawCube(double[][] puntos,double[] direccion,double origenX,double origenY,Graphics g, Color c,Color c1,Color c2,Color c3,Color c4,boolean lineas,boolean perspectiva){
        double [][] result ;
        if(!perspectiva)
            result = multiplicarMatriz(puntos,direccion,origenX,origenY);
        else
            result = multiplicarMatrizPerspectiva(puntos,direccion,origenX,origenY);

        fill(result,g,c,c1,c2,c3,c4);
        if(lineas){
            //Frente
            lineaBresenham(result[0][0], result[1][0], result[0][1], result[1][1], g, c);//AB
            lineaBresenham(result[0][1], result[1][1], result[0][2], result[1][2], g, c);////BD
            lineaBresenham(result[0][2], result[1][2], result[0][3], result[1][3], g, c);//DC
            lineaBresenham(result[0][3], result[1][3], result[0][0], result[1][0], g, c);//CA

            //Atras
            lineaBresenham(result[0][4], result[1][4], result[0][5], result[1][5], g, c);//EF
            lineaBresenham(result[0][5], result[1][5], result[0][6], result[1][6], g, c);//FG
            lineaBresenham(result[0][6], result[1][6], result[0][7], result[1][7], g, c);//GH
            lineaBresenham(result[0][7], result[1][7], result[0][4], result[1][4], g, c);//HE
            //lados
            lineaBresenham(result[0][0], result[1][0], result[0][4], result[1][4], g, c);//AE
            lineaBresenham(result[0][1], result[1][1], result[0][5], result[1][5], g, c);//BE
            lineaBresenham(result[0][2], result[1][2], result[0][6], result[1][6], g, c);//DG
            lineaBresenham(result[0][3], result[1][3], result[0][7], result[1][7], g, c); //CH
        }
    }//drawCube


    public double[][] multiplicarMatriz(double[][] matriz, double[] direccion,double origenX,double origenY){
        double [][] result = new double[2][matriz[0].length];
        for(int i=0;i<matriz[0].length;i++){
            result[0][i] = (matriz[0][i]+ direccion[0] * (-matriz[2][i]/direccion[2])) + origenX;
            result[1][i] = (matriz[1][i]+ direccion[1] * (-matriz[2][i]/direccion[2])) + origenY;

            /*if(result[0][i]<0)
                result[0][i] -=1;
            if(result[1][i]<0)
                result[1][i] -= 1;
*/
            result[1][i] = result[1][i];//abs
            result[0][i] = result[0][i];//abs

        }
        return  result;
    }//multiplicarMatriz
    public void relleno(int dif, double x1, double y1, double x2, double y2, Graphics g, Color c) {

        for (int i = 0; i <= dif; i++) {
            lineaBresenham(x1, (int) y1 + i, x2, (int) y1 + i, g, c);
        }

    }//relleno
    private void fill(double[][] result,Graphics g, Color c,Color c1,Color c2,Color c3,Color c4){
        g.setColor(c1);
        g.fillPolygon(new int[]{(int) Math.round(result[0][0]), (int) Math.round(result[0][1]),
                (int) Math.round(result[0][2]),(int) Math.round(result[0][3])},new int[]{(int) Math.round(result[1][0]), (int) Math.round(result[1][1]),
                (int) Math.round(result[1][2]),(int) Math.round(result[1][3])},4);

        g.setColor(c2);
        g.fillPolygon(new int[]{
                (int) Math.round(result[0][4]), (int) Math.round(result[0][5]), (int) Math.round(result[0][6]),(int) Math.round(result[0][7])},new int[]{
                (int) Math.round(result[1][4]), (int) Math.round(result[1][5]), (int) Math.round(result[1][6]),(int) Math.round(result[1][7])},4);
        g.setColor(c3);
        g.fillPolygon(new int[]{
                (int) Math.round(result[0][0]), (int) Math.round(result[0][4]), (int) Math.round(result[0][7]),(int) Math.round(result[0][3])},new int[]{
                (int) Math.round(result[1][0]), (int) Math.round(result[1][4]), (int) Math.round(result[1][7]),(int) Math.round(result[1][3])},4);

        g.setColor(c4);
        g.fillPolygon(new int[]{
                (int) Math.round(result[0][3]), (int) Math.round(result[0][2]), (int) Math.round(result[0][6]),(int) Math.round(result[0][7])},new int[]{
                (int) Math.round(result[1][3]), (int) Math.round(result[1][2]), (int) Math.round(result[1][6]),(int) Math.round(result[1][7])},4);
    }//fill
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
    public void dibujarCirculoPuntoMedio(int xc, int yc,int r,Color c,Graphics g) {
        // Punto inicial del círculo
        int x = 0;
        int y = r;
        // Cálcular el parámetro inicial de decisión
        int pk = 1-r;

        // verificar el pk para determinar las posiciones de pixel siguientes
        while (x<=y) {
            putPixel(xc+x,yc+y,c,g);
            putPixel(xc-x,yc-y,c,g);
            putPixel(xc+x,yc-y,c,g);
            putPixel(xc-x,yc+y,c,g);
            putPixel(xc+y,yc+x,c,g);
            putPixel(xc-y,yc-x,c,g);
            putPixel(xc+y,yc-x,c,g);
            putPixel(xc-y,yc+x,c,g);
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
        g.setColor(c);
        g.fillOval(xc-r,yc-r,r*2,r*2);
    }//dibujarCirculoPuntoMedio

    public double[][] multiplicarMatrizPerspectiva(double[][] matriz, double[] direccion,double origenX,double origenY){
        double [][] result = new double[2][matriz[0].length];
        for(int i=0;i<matriz[0].length;i++){
            result[0][i] = (direccion[0] + (matriz[0][i] - direccion[0]) * (-direccion[2] / (matriz[2][i]-direccion[2]))) + origenX;
            result[1][i] = (direccion[1] + (matriz[1][i] - direccion[1]) * (-direccion[2] / (matriz[2][i]-direccion[2]))) + origenY;

            result[1][i] = Math.abs(result[1][i]);
            result[0][i] = Math.abs(result[0][i]);

        }
        return  result;
    }
    public void drawElipse(int xc, int yc, int rx, int ry,Color c,Graphics g){
        int x=xc-rx;
        int y=yc;
        putPixel(x,y,c,g);
        g.setColor(c);
        g.fillOval(xc-rx,yc-ry,rx*2,ry*2);
        for (double i=0;i<=(2*Math.PI); i+=Math.PI/1000){
            x= (int) (xc + (rx * Math.sin(i)));
            y= (int) (yc + (ry * Math.cos(i)));
            putPixel(x,y,c,g);

        }//for
    }//drawElipse

    private void lineaBresenham(double xini, double yini, double xfin, double yfin, Graphics g, Color c){
        int x0 = (int) Math.round(xini);
        int y0 = (int) Math.round(yini);
        int x1 = (int) Math.round(xfin);
        int y1 = (int) Math.round(yfin);
        int dx = (x1 - x0);
        int dy = (y1 - y0);
        int stepY, stepX,pk;
        int xk = x0;
        int yk = y0;
        // determinar punto de partida y fin
        if (dy < 0) {
            dy = -dy;
            stepY = -1;
        }
        else
            stepY = 1;
        if (dx < 0) {
            dx = -dx; stepX = -1;
        }
        else
            stepX = 1;
        putPixel(xk,yk,c,g);

        /* se cicla hasta llegar al extremo de la línea */
        if(dx>dy){
            pk = 2*dy - dx;
            while (xk != x1){
                xk += stepX;
                if (pk < 0)
                    pk += 2*dy;
                else {
                    yk += stepY;
                    pk += 2*(dy-dx);
                }
                putPixel(xk,yk,c,g);

            }
        }
        else{
            pk = 2*dx - dy;
            while (yk != y1){
                yk += stepY;
                if (pk < 0)
                    pk += 2*dx;
                else {
                    xk += stepX;
                    pk += 2*(dx-dy);
                }
                putPixel(xk,yk,c,g);

            }
        }
    }//lineaBresenham



}