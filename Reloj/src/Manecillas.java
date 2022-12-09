import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Calendar;

public class Manecillas extends JFrame implements Runnable{
    private Image fondo;
    private Image buffer;
    private Thread hilo;
    private BufferedImage bufferedImage;


    int hora;
    int min;
    int sec;

    int manecillaSec = 100;
    int manecillaMin = 70;
    int manecillaHora = 50;
    int origenX;
    int origenY;


    public Manecillas(){
        setTitle("Manecillas 20110374");
        setResizable(false);
        setSize(700,700);
        setLayout(null);
        setVisible(true);
        origenX = (getWidth()-300)/2;
        origenY = (getHeight()-100)/2;
        bufferedImage = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        hilo =  new Thread(this);
        hilo.start();
    }//constructor


    public static void main(String[] args) {
        new Manecillas();
    }//main


    @Override
    public void paint(Graphics g) {
        //g.drawLine(100,100,200,200);
        if(fondo == null){
            fondo  = createImage(getWidth(),getHeight());
            //pintar el circulo del reloj
            Graphics gfondo = fondo.getGraphics();
            gfondo.setClip(0,0,getWidth(),getHeight());
            ImageIcon fondito =  new ImageIcon("src/img/clock.png");

           // gfondo.drawImage(fondito.getImage(),0,0,this);
            background(gfondo);
            //gfondo.setColor(Color.black);
            //gfondo.drawOval((getWidth()-100)/2,(getHeight()-100)/2,200,200);
            gfondo.drawImage(fondito.getImage(),origenX,origenY,300,300,this);

        }

        update(g);
    }//paint


    public void update(Graphics g){
        double xHora, yHora, anguloHora,xSec,ySec,angleSec,xMin,yMin,angleMin;
        g.setClip(0,0,getWidth(),getHeight());
        origenX = (getWidth())/2;
        origenY = (getHeight()+200)/2;
        //Hora actual
        Calendar cal = Calendar.getInstance();
            //regenerar la imagen de fondo
            hora = cal.get(Calendar.HOUR);
            min = cal.get(Calendar.MINUTE);
            sec = cal.get(Calendar.SECOND);

            //crear la imagen estatica
            buffer = createImage(getWidth(),getHeight());
            Graphics gbuffer = buffer.getGraphics();
            gbuffer.setClip(0,0,getWidth(),getHeight());
            gbuffer.drawImage(fondo,0,0,this);
            // Segundos
            System.out.println("segundos:  "+sec);
            angleSec = angulo12((double) sec);
            System.out.println("angle sec: "+angleSec);
            xSec = getX(angleSec,manecillaSec);
            ySec = getY(angleSec,manecillaSec);
            gbuffer.setColor(Color.RED);

            lineaBresenham(origenX,origenY,origenX+(int)xSec,origenY+(int)ySec,gbuffer,Color.RED);
            //gbuffer.drawLine(getWidth()/2,getHeight()/2,(getWidth()/2)+(int)xSec,(getHeight()/2)+(int)ySec);
            //Minutos
            double minAux = min + sec/60.00;
            System.out.println("minutos: "+minAux);

            angleMin = angulo12(minAux);
            System.out.println("angle min: "+angleSec);
            xMin = getX(angleMin,manecillaMin);
            yMin = getY(angleMin,manecillaMin);
            gbuffer.setColor(Color.GREEN);
            //gbuffer.drawLine(getWidth()/2,getHeight()/2,(getWidth()/2)+(int)xMin,(getHeight()/2)+(int) yMin);
            lineaBresenham(origenX,origenY,origenX+(int)xMin,origenY+(int)yMin,gbuffer,Color.GREEN);

        //horas
            double hourAux = hora + minAux/60.00;
            System.out.println("hora "+hourAux);
            anguloHora = angulo12(hourAux*5.00);
            //anguloHora = 1.55;
            System.out.println("angulo hora: "+anguloHora);
            xHora = getX(anguloHora,manecillaHora);
            yHora = getY(anguloHora,manecillaHora);
            gbuffer.setColor(Color.BLUE);
            //gbuffer.drawLine(getWidth()/2,getHeight()/2,(getWidth()/2)+(int)xHora,(getHeight()/2)+(int)yHora);
            lineaBresenham(origenX,origenY,origenX+(int)xHora,origenY+(int)yHora,gbuffer,Color.BLUE);
            g.drawImage(buffer,0,0,this); //doble buffer

    }//update

    private double angulo12(double time) {
        //circunferencia  angulo manecilla = 2pi / 60 secs
        double angle = 3*Math.PI - ( (Math.PI/30.00) *time);
        System.out.println(angle);
        return angle;

    }//angulo12


    @Override
    public void run() {
        while (true){
            try{
                repaint();
                hilo.sleep(1000);
            }catch (InterruptedException ex){
                ex.printStackTrace();
            }
        }
    }//run


    public void putPixel(int x, int y,Color c, Graphics g){
        bufferedImage.setRGB(0,0,c.getRGB());
        g.drawImage(bufferedImage,x,y,this);

    }//putPixel

    //metodos para angulos de horas, min y sec
    private double getX(double angulo, int radio){
        double x = (double)radio * (Math.sin(angulo));
        System.out.println("IncX: "+x);
        return x;
    }//getX


    private double getY(double angulo, int radio){
        double y = (double)radio * (Math.cos(angulo));
        System.out.println("IncY: "+y);
        return y;
    }//getY


    private void lineaBresenham(int x0,int y0,int x1, int y1,Graphics g,Color c){
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


    private void background(Graphics g){
        int w  = getWidth();
        int h = getHeight();
        Graphics2D graph = (Graphics2D) g;
        //graph.drawLine(w/2-30,0,w/2-20,h-1);
        //graph.drawLine(w/2+20,0,w/2+10,h-1);
        //Fondo atardecer
        Color sunset = new Color(246, 211, 101);
        Color sunset2 = new Color(253, 160, 133);
        GradientPaint bg = new GradientPaint(w, h, sunset, 0, 0, sunset2);
        graph.setPaint(bg);
        graph.fillRect(0, 0, w, h);
        //SOL
        Color sun =  new Color(227, 74, 43);
        graph.setColor(sun);
        graph.fillOval(-10, -10, 200, 200);
        Color sun_border =  new Color(145, 38, 16);
        graph.setColor(sun_border);
        graph.drawOval(-10, -10, 200, 200);
        //nube
        Color cloud = new Color(229, 228, 227);
        graph.setColor(cloud);
        graph.fillOval(20, 100, 300, 150);
        graph.fillOval(70, 80, 200, 200);
        //Montañas
        Color green_hill = new Color(70, 164, 78);
        Color green_hill_line = new Color(3, 51, 9);

        int [] hill_x = {-100,200, 400};
        int [] hill_y = {h, 400, h};
        int [] hill_x2 = {300,500, w+100};
        int [] hill_y2 = {h, 400, h};
        graph.setColor(green_hill);
        graph.fillPolygon (hill_x, hill_y, 3);
        graph.fillPolygon (hill_x2, hill_y2, 3);
        graph.setColor(green_hill_line);
        graph.drawPolygon (hill_x2, hill_y2, 3);
        graph.drawPolygon (hill_x, hill_y, 3);

        //Nevado Montaña
        graph.setColor(Color.BLACK);
        int [] snow_x = {155,200, 240};
        int [] snow_y = {440, 400, 440};
        int [] snow_x2 = {460,500, 540};
        int [] snow_y2 = {440, 400, 440};
        graph.drawPolygon (snow_x, snow_y, 3);
        graph.drawPolygon (snow_x2, snow_y2, 3);
        graph.setColor(Color.WHITE);
        graph.fillPolygon (snow_x, snow_y, 3);
        graph.fillPolygon (snow_x2, snow_y2, 3);
        //Arbol tronco
        Color brown = new Color(101,42,14);
        Color brown_dark = new Color(35, 23, 9);
        graph.setColor(brown);
        graph.fillRect(w/2+170, 100, 50, h-10);
        graph.setColor(brown_dark);
        graph.drawRect(w/2+170, 100, 50, h-10);
        //Arbol hojas
        Color green_leaf = new Color(59, 124, 8);
        graph.setColor(green_leaf);
        graph.fillOval(w/2+95, 200, 200, 200);
        graph.fillOval(w/2+95, 50, 200, 200);
        graph.fillOval(w/2+170, 150, 200, 200);
        graph.fillOval(w/2+20, 150, 200, 200);
        //pasto
        Color grass = new Color(72,111,56);
        graph.setColor(grass);
        graph.fillRect(0,600,700,200);
        //piedra
        graph.setColor(Color.darkGray);
        graph.fillArc(600,600,60,50,180,-180);
        //Snorlax
        Color belly =  new Color(225, 214, 204);
        Color hair = new Color(47, 98, 118);
        //cuerpo tronco
        graph.setColor(hair);
        graph.fillArc((w/5)+10,(h/4)+90,400,600,180,-180);
        graph.fillRoundRect((w/5)+3,(h/4)+250,415,200,250,200);
        //patas
        graph.fillOval((w/5)+15,(h/4)+400,138,70);
        graph.fillOval((w/5)+250,(h/4)+400,138,70);
        //graph. fillRoundRect((w/5)+3,(h/4)+250,415,200,250,200);
        //graph.setColor(Color.BLUE);
        //Manos
        graph.fillOval((w/5)-40,(h/4)+200,185,90);
        graph.fillOval((w/5)+280,(h/4)+200,185,90);
        //panza
        graph.setColor(belly);
        graph.fillOval((getWidth()-300)/2,(getHeight()-100)/2,300,300);
        //cabeza
        graph.setColor(hair);
        graph.fillOval((getWidth()-243)/2,(getHeight()-280)/3,250,250);
        graph.setColor(belly);
        graph.fillOval((getWidth()-195)/2,(getHeight()-230)/3,200,200);
        //reloj panza
        graph.setColor(belly);
        graph.fillOval((getWidth()-300)/2,(getHeight()-100)/2,300,300);
        //pico cara
        int [] caraX = {300, 350, 400};
        int [] caraY = {155, 180, 155};
        graph.setColor(hair);
        graph.fillPolygon(caraX, caraY, 3);
        //orejas
        //izq
        int [] orejaIzqX = {250, 250, 300};
        int [] orejaIzqY = {195, 120, 155};
        graph.fillPolygon(orejaIzqX, orejaIzqY, 3);
        //der
        int [] orejaDerX = {400, 450, 450};
        int [] orejaDerY = {155, 120, 195};
        graph.fillPolygon(orejaDerX, orejaDerY, 3);
        //garras
        //izq
        int [] pataIzqX = {165, 143, 167};
        int [] pataIzqY = {605, 617, 630};
        graph.setColor(Color.WHITE);
        graph.fillPolygon(pataIzqX, pataIzqY, 3);
        graph.fillArc(170,610,30,45,180,-180);
        graph.fillArc(210,610,30,45,180,-180);
        //der
        int [] pataDerX = {520, 543, 517};
        int [] pataDerY = {605, 617, 630};
        graph.fillPolygon(pataDerX, pataDerY, 3);
        graph.fillArc(480,610,30,45,180,-180);
        graph.fillArc(440,610,30,45,180,-180);
        //cara
        graph.setColor(hair);
        graph.drawArc(280,205,50,10,200,-180);
        graph.drawArc(370,205,50,10,200,-180);
        graph.drawArc(315,235,70,10,-180,190);
        //dientes
        //izq
        int [] dienteIzqX = {315, 325, 335};
        int [] dienteIzqY = {242, 233, 244};
        graph.setColor(Color.WHITE);
        graph.fillPolygon(dienteIzqX, dienteIzqY, 3);
        //der
        int [] dienteDerX = {365, 375, 385};
        int [] dienteDerY = {244, 233, 242};
        graph.fillPolygon(dienteDerX, dienteDerY, 3);

        //sombrero
        ImageIcon hat =  new ImageIcon("src/img/mexican_hat.png");
        graph.drawImage(hat.getImage(),175,10,350,190,this);
        //mostacho
        ImageIcon mustache =  new ImageIcon("src/img/mexican_mustache.png");
        graph.drawImage(mustache.getImage(),315,230,65,20,this);
    }//background


}//class
