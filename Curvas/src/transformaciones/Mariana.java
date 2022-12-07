package transformaciones;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 * @author Clau
 * Claudia Manuela Pérez Cortés - 19310150 - 6P
 * La clase no contine una función main, ya que durante el desarrollo las practicas se contienen en un mismo proyecto
 * con la finalidad de reducir la cantidad de proyectos de java creados por netbeans*
 *
 */
public class Mariana extends JFrame implements Runnable{
    private BufferedImage bufferPixel, imagenCompleta;
    private Graphics graPixel;
    private Color miColor, miRosa, unknown, miRojo, negroAbeja, amarilloAbeja, ala1Abeja, ala2Abeja, miPasto, miCielo, miFlor, miSol, miCentro;
    private Thread hilo;
    private String nombreClase = "Foco";
    private int ancho = 800, alto = 800;
    private float cubo[][], cuboX[][], carasX[][], centrosX[][], rellenosX[][], printCuboX[][], printCentrosX[][];
    private float cuboE[][], carasE[][], centrosE[][], printCuboE[][], printCentrosE[][];
    private float cuboT[][], carasT[][], centrosT[][], printCuboT[][], printCentrosT[][];
    private float figX[], figY[], fig2X[], fig2Y[];
    private final int X = 0, Y = 1, Z = 2;

    public Mariana(){

        setTitle(nombreClase);
        setSize(800,800);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        this.bufferPixel = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        this.imagenCompleta = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);
        this.hilo = new Thread(this);
        this.hilo.start();
        this.asignarColores();
        this.crearFiguras();
        setVisible(true);
    }

    public static void main(String[] args) {
        new Mariana();
    }

    public void paint(Graphics g){
        imagenCompleta = new BufferedImage(800,800,BufferedImage.TYPE_INT_RGB);

        float minZ, punto = 0;

        cuboX = rotacionX(cuboX, 5);
        centrosX = rotacionX(centrosX,5);
        cuboX = rotacionY(cuboX, 5);
        centrosX = rotacionY(centrosX,5);
        cuboX = rotacionZ(cuboX, 5);
        centrosX = rotacionZ(centrosX,5);
        printCuboX = puntos3Da2D(cuboX,0,0,1);
        printCentrosX = puntos3Da2D(centrosX,0,0,1);

        minZ = cuboX[0][Z];
        for(int i = 0; i < cuboX.length; i++){
            if(minZ>cuboX[i][Z]){
                minZ = cuboX[i][Z];
                punto = i;
            }
        }

        printPoliProfundo2(printCuboX, punto, 200, 200, 3, 3, Color.red);
        printPoliProfundo(printCuboX, 300, 200, 3, 3, Color.red);

        RellenarCubo(printCentrosX, carasX, punto, 200,200, 3,3,new Color[]{Color.cyan,Color.white,Color.magenta,Color.blue,Color.yellow, Color.green});

        cuboE = Escalar3D(cuboE, 1.01 ,1.01,1.01);
        centrosE = Escalar3D(centrosE,1.01,1.01,1.01);
        printCuboE = puntos3Da2D(cuboE,0,0,1);
        printCentrosE = puntos3Da2D(centrosE,0,0,1);

        minZ = cuboE[0][Z];
        for(int i = 0; i < cuboE.length; i++){
            if(minZ>cuboE[i][Z]){
                minZ = cuboE[i][Z];
                punto = i;
            }
        }

        printPoliProfundo2(printCuboE, punto, 200, 350, 3, 3, Color.red);
        RellenarCubo(printCentrosE, carasE, punto, 200, 350, 3,3,new Color[]{Color.cyan,Color.white,Color.magenta,Color.blue,Color.yellow, Color.green});

        cuboT = Trasladar3D(cuboT, 2,0,0);
        centrosT = Trasladar3D(centrosT, 2 ,0,0);
        printCuboT = puntos3Da2D(cuboT,0,0,1);
        printCentrosT = puntos3Da2D(centrosT,0,0,1);

        minZ = cuboT[0][Z];
        for(int i = 0; i < cuboT.length; i++){
            if(minZ>cuboT[i][Z]){
                minZ = cuboT[i][Z];
                punto = i;
            }
        }

        printPoliProfundo2(printCuboT, punto, 200, 500, 3, 3, Color.red);
        RellenarCubo(printCentrosT, carasT, punto, 200, 500, 3,3,new Color[]{Color.cyan,Color.white,Color.magenta,Color.blue,Color.yellow, Color.green});




        g.drawImage(imagenCompleta,0,0,null);

    }

    public void RellenarCubo(float[][] centros, float[][] caras, float omitir, int desX, int desY, int ampX, int ampY, Color[] c){
        for(int i = caras.length-1; i >= 0 ; i--){
            if(caras[i][0]==omitir||caras[i][1]==omitir||caras[i][2]==omitir||caras[i][3]==omitir){

            }else{
                //Punto(new float[]{centros[i][X]*ampX,centros[i][Y]*ampY},desX, desY, Color.red);
                rellenar((int)(centros[i][X]*ampX)+desX, (int)(centros[i][Y]*ampY)+desY, unknown, c[i]);
            }
        }
    }

    public void Punto(float [] p, int desX, int desY,Color c){

        int x = (int)p[X], y = (int)p[Y];

        putPixel(x+desX,y+desY,c);
        putPixel(x+1+desX,y+desY,c);
        putPixel(x-1+desX,y+desY,c);
        putPixel(x+desX,y+1+desY,c);
        putPixel(x+desX,y-1+desY,c);

    }

    public float [][] Trasladar3D(float[][] fig3D, float dx, float dy, float dz){
        float temp [] = new float[3];
        float fig [][] = new float[fig3D.length][3];

        for(int i = 0; i<fig3D.length; i++){
            temp = trasladarPunto(fig3D[i][X], fig3D[i][Y], fig3D[i][Z], dx, dy, dz);
            fig[i][X] = temp[X];
            fig[i][Y] = temp[Y];
            fig[i][Z] = temp[Z];

        }

        return fig;
    }

    public void rellenar(int x,int y, Color fondo, Color cambio){
        if(fondo.getRGB()==imagenCompleta.getRGB(x, y)){
            this.putPixel(x, y, cambio);
            this.rellenar(x-1,y,fondo,cambio);
            this.rellenar(x+1,y,fondo, cambio);
            this.rellenar(x,y-1,fondo,cambio);
            this.rellenar(x,y+1,fondo,cambio);
        }
    }


    public float[] trasladarPunto(float x, float y, float z, float dx, float dy, float dz){ //Arreglo puntos, movimiento en x, movimiento en y
        float[] P={x, y, z, 1};

        float[][] T={
                {1,0,0,dx},
                {0,1,0,dy},
                {0,0,1,dz},
                {0,0,0,1}
        };

        P[0]=P[0]*T[0][0]+P[1]*T[0][1]+P[2]*T[0][2]+P[3]*T[0][3];
        P[1]=P[0]*T[1][0]+P[1]*T[1][1]+P[2]*T[1][2]+P[3]*T[1][3];
        P[2]=P[0]*T[2][0]+P[1]*T[2][1]+P[2]*T[2][2]+P[3]*T[2][3];
        P[3]=P[0]*T[3][0]+P[1]*T[3][1]+P[3]*T[3][2]+P[3]*T[3][3];
        //System.out.println("P[0]:"+P[0]+" P[1]:"+P[1]+"P[2]:"+P[2]);
        float end[]={(float)P[0],(float)P[1],(float)P[2]};

        return end;

    }


    public float [][] Escalar3D(float[][] fig3D, double sx, double sy, double sz){
        float temp [] = new float[3];
        float fig [][] = new float[fig3D.length][3];

        for(int i = 0; i<fig3D.length; i++){
            temp = escalarPunto(fig3D[i][X], fig3D[i][Y], fig3D[i][Z], sx, sy, sz);
            fig[i][X] = temp[X];
            fig[i][Y] = temp [Y];
            fig[i][Z] = temp [Z];

        }

        return fig;
    }

    public float[] escalarPunto(float x, float y, float z, double sx, double sy, double sz){ //Arreglo puntos, movimiento en x, movimiento en y
        double[] P={x, y, z, 1};

        double[][] T={
                {sx,0,0,0},
                {0,sy,0,0},
                {0,0,sz,0},
                {0,0,0,1}
        };

        P[0]=P[0]*T[0][0]+P[1]*T[0][1]+P[2]*T[0][2]+P[3]*T[0][3];
        P[1]=P[0]*T[1][0]+P[1]*T[1][1]+P[2]*T[1][2]+P[3]*T[1][3];
        P[2]=P[0]*T[2][0]+P[1]*T[2][1]+P[2]*T[2][2]+P[3]*T[2][3];
        P[3]=P[0]*T[3][0]+P[1]*T[3][1]+P[3]*T[3][2]+P[3]*T[3][3];
        //System.out.println("P[0]:"+P[0]+" P[1]:"+P[1]);
        float end[]={(float)P[0],(float)P[1],(float)P[2]};

        return end;

    }


    public float [][] rotacionX(float fig3D[][], int angulo){
        float[][] fig = new float[fig3D.length][3];


        for(int i = 0; i<fig3D.length; i++){

            fig[i][X] = fig3D[i][X];
            fig[i][Y] = (float)((fig3D[i][Y]*Math.cos(Math.toRadians(angulo)))-(fig3D[i][Z]*(Math.sin(Math.toRadians(angulo)))));
            fig[i][Z] = (float)((fig3D[i][Y]*Math.sin(Math.toRadians(angulo)))+(fig3D[i][Z]*(Math.cos(Math.toRadians(angulo)))));
        }

        return fig;
    }

    public float [][] rotacionY(float fig3D[][], int angulo){
        float[][] fig = new float[fig3D.length][3];


        for(int i = 0; i<fig3D.length; i++){

            fig[i][X] = (float)((fig3D[i][X]*Math.cos(Math.toRadians(angulo)))+(fig3D[i][Z]*(Math.sin(Math.toRadians(angulo)))));
            fig[i][Y] = fig3D[i][Y];
            fig[i][Z] = (float)((-fig3D[i][X]*Math.sin(Math.toRadians(angulo)))+(fig3D[i][Z]*(Math.cos(Math.toRadians(angulo)))));
        }

        return fig;
    }

    public float [][] rotacionZ(float fig3D[][], int angulo){
        float[][] fig = new float[fig3D.length][3];


        for(int i = 0; i<fig3D.length; i++){

            fig[i][Z] = fig3D[i][Z];
            fig[i][X] = (float)((fig3D[i][X]*Math.cos(Math.toRadians(angulo)))-(fig3D[i][Y]*(Math.sin(Math.toRadians(angulo)))));
            fig[i][Y] = (float)((fig3D[i][X]*Math.sin(Math.toRadians(angulo)))+(fig3D[i][Y]*(Math.cos(Math.toRadians(angulo)))));
        }

        return fig;
    }

    public float [] fugaPuntos(float puntos3D[], float cx, float cy, float cz){

        float[] puntos2D = new float[2];
        int neg;
        if(cz >= 0){
            neg = 1;
        }else{
            neg = -1;
        }


        //puntos2D[X]=puntos3D[X]+((puntos3D[X]-cx)*(puntos3D[Z]/(cz-puntos3D[Z])));
        //puntos2D[Y]=puntos3D[Y]+((puntos3D[Y]-cy)*(puntos3D[Z]/(cz-puntos3D[Z])));
        puntos2D[X]=(((cz*puntos3D[X])-(cx*puntos3D[Z]))/(cz-puntos3D[Z]));
        puntos2D[Y]=neg*((cz*puntos3D[Y])-(cy*puntos3D[Z]))/(cz-puntos3D[Z]);


        return puntos2D;
    }

    public float [][] puntos3Da2D(float fig3D[][], int dx, int dy, int dz){
        float[][] fig2D = new float[fig3D.length][2];

        for(int i = 0; i<fig3D.length; i++){
            fig2D[i][X] = (fig3D[i][X]-((dx/dz)*fig3D[i][Z]));
            fig2D[i][Y] = (fig3D[i][Y]-((dy/dz)*fig3D[i][Z]));
            System.out.println("x:"+fig2D[i][X]+" y:"+fig2D[i][Y]);
        }

        return fig2D;
    }

    public void printProyeccionRect(float fig3D[][], float fig2D[][], int desX, int desY, int ampX, int ampY, Color c){

        for(int i = 0; i<fig3D.length-1; i++){
            for(int j=i+1; j<fig3D.length; j++){
                System.out.println("i:"+i);
                if((fig3D[i][X]==fig3D[j][X])&&(fig3D[i][Y]==fig3D[j][Y])){
                    Linea((fig2D[i][X]*ampX)+desX,(fig2D[i][Y]*ampY)+desY,(fig2D[j][X]*ampX)+desX, (fig2D[j][Y]*ampY)+desY, c);
                    System.out.println("punto A:"+i+" punto B:"+j);
                }

                if((fig3D[i][X]==fig3D[j][X])&&(fig3D[i][Z]==fig3D[j][Z])){
                    Linea((fig2D[i][X]*ampX)+desX,(fig2D[i][Y]*ampY)+desY,(fig2D[j][X]*ampX)+desX, (fig2D[j][Y]*ampY)+desY, c);
                    System.out.println("punto A:"+i+" punto B:"+j);
                }

                if((fig3D[i][Y]==fig3D[j][Y])&&(fig3D[i][Z]==fig3D[j][Z])){
                    Linea((fig2D[i][X]*ampX)+desX,(fig2D[i][Y]*ampY)+desY,(fig2D[j][X]*ampX)+desX, (fig2D[j][Y]*ampY)+desY, c);
                    System.out.println("punto A:"+i+" punto B:"+j);
                }
            }
        }
    }

    public void printPoliProfundo(float[][] fig2D, int desX, int desY, int ampX, int ampY, Color c){
        int n = fig2D.length/2;
        int nn = n;
        for(int i = 0; i<n-1;i++){
            Linea((fig2D[i][X]*ampX)+desX, (fig2D[i][Y]*ampY)+desY, (fig2D[i+1][X]*ampX)+desX, (fig2D[i+1][Y]*ampY)+desY, c);
            //System.out.println("Rotación"+i);
        }
        Linea((fig2D[n-1][X]*ampX)+desX, (fig2D[n-1][Y]*ampY)+desY, (fig2D[0][X]*ampX)+desX, (fig2D[0][Y]*ampY)+desY, c);

        for(int i = nn; i<fig2D.length-1;i++){
            Linea((fig2D[i][X]*ampX)+desX, (fig2D[i][Y]*ampY)+desY, (fig2D[i+1][X]*ampX)+desX, (fig2D[i+1][Y]*ampY)+desY, c);
            //System.out.println("Rotación"+i);
        }
        Linea((fig2D[fig2D.length-1][X]*ampX)+desX, (fig2D[fig2D.length-1][Y]*ampY)+desY, (fig2D[nn][X]*ampX)+desX, (fig2D[nn][Y]*ampY)+desY, c);

        for(int i = 0; i <n;i++){
            Linea((fig2D[i][X]*ampX)+desX, (fig2D[i][Y]*ampY)+desY, (fig2D[i+n][X]*ampX)+desX, (fig2D[i+n][Y]*ampY)+desY, c);
        }
    }

    public void printPoliProfundo2(float[][] fig2D, float omitir, int desX, int desY, int ampX, int ampY, Color c){
        int n = fig2D.length/2;
        int nn = n;
        for(int i = 0; i<n-1;i++){
            if(i!=omitir&&i+1!=omitir){
                Linea((fig2D[i][X]*ampX)+desX, (fig2D[i][Y]*ampY)+desY, (fig2D[i+1][X]*ampX)+desX, (fig2D[i+1][Y]*ampY)+desY, c);
            }
            //System.out.println("Rotación"+i);
        }
        if(n-1!=omitir&&0!=omitir)
            Linea((fig2D[n-1][X]*ampX)+desX, (fig2D[n-1][Y]*ampY)+desY, (fig2D[0][X]*ampX)+desX, (fig2D[0][Y]*ampY)+desY, c);

        for(int i = nn; i<fig2D.length-1;i++){
            if(i!=omitir&&i+1!=omitir)
                Linea((fig2D[i][X]*ampX)+desX, (fig2D[i][Y]*ampY)+desY, (fig2D[i+1][X]*ampX)+desX, (fig2D[i+1][Y]*ampY)+desY, c);
            //System.out.println("Rotación"+i);
        }
        if(fig2D.length-1!=omitir&&nn!=omitir)
            Linea((fig2D[fig2D.length-1][X]*ampX)+desX, (fig2D[fig2D.length-1][Y]*ampY)+desY, (fig2D[nn][X]*ampX)+desX, (fig2D[nn][Y]*ampY)+desY, c);

        for(int i = 0; i<n;i++){
            if(i!=omitir&&i+n!=omitir)
                Linea((fig2D[i][X]*ampX)+desX, (fig2D[i][Y]*ampY)+desY, (fig2D[i+n][X]*ampX)+desX, (fig2D[i+n][Y]*ampY)+desY, c);
        }
    }


    public float [][] PoliProfundo(float[] x, float[] y, int z){
        float[][] fig3D = new float[(x.length)*2][3];
        int n = x.length;

        for(int i = 0; i<x.length; i++){
            fig3D[i][X] = x[i];
            fig3D[i+n][X] = x[i];
            fig3D[i][Y] = y[i];
            fig3D[i+n][Y] = y[i];
            fig3D[i][Z] = 0;
            fig3D[i+n][Z] = z;
        }

        return fig3D;
    }

    public float [][] PoliCaras(float[][] fig3D){
        return new float[][]{
                {0,1,2,3},
                {4,5,6,7},
                {0,1,5,4},
                {3,2,6,7},
                {0,3,7,4},
                {1,2,6,5}
        };
    }

    /*public float [][] PoliCentro(float[][] fig3D, float[][] caras){
        float[][] centros = new float[6][3];
        float a, b, c;
        //float n = a/(float)2;
        for(int i = 0; i<centros.length; i++){
            a = (Math.abs(fig3D[(int)(caras[i][0])][X]) + Math.abs(fig3D[(int)(caras[i][3])][X]));
            b = (Math.abs(fig3D[(int)(caras[i][0])][Y]) + Math.abs(fig3D[(int)(caras[i][3])][Y]));
            c = (Math.abs(fig3D[(int)(caras[i][0])][Z]) + Math.abs(fig3D[(int)(caras[i][3])][Z]));
            /*a = fig3D[(int)(caras[i][0])][X] - fig3D[(int)(caras[i][3])][X];
            b = fig3D[(int)(caras[i][0])][Y] - fig3D[(int)(caras[i][3])][Y];
            c = fig3D[(int)(caras[i][0])][Z] - fig3D[(int)(caras[i][3])][Z];
            centros[i][X] = fig3D[(int)(caras[i][0])][X] + ((a)/(float)2.0);
            centros[i][Y] = fig3D[(int)(caras[i][0])][Y]  + ((b)/(float)2.0);
            centros[i][Z] = fig3D[(int)(caras[i][0])][Z]  + ((c)/(float)2.0);
            System.out.println("hi");
        }
        return centros;
    }*/

    public float [][] PoliCentro(float[][] fig3D, float z){
        float n = z/(float)2;
        return new float[][]{
                {fig3D[0][X]+n,fig3D[0][Y]+n,fig3D[0][Z]},
                {fig3D[4][X]+n,fig3D[4][Y]+n,fig3D[4][Z]},
                {fig3D[0][X]+n,fig3D[0][Y], fig3D[0][Z]+n},
                {fig3D[3][X]+n,fig3D[3][Y], fig3D[3][Z]+n},
                {fig3D[0][X],fig3D[0][Y]+n, fig3D[0][Z]+n},
                {fig3D[1][X],fig3D[0][Y]+n, fig3D[0][Z]+n},
        };
    }

    public void RellenoCubo(float[][] cubo3D, float[][] cubo2D, float[][] caras, float[][] centros, float minZ, Color[] cs, int desX, int desY, int ampX, int ampY){
        for(int i = 0; i<cubo2D.length;i++){
            if(cubo3D[i][Z]==minZ){

            }else{
                //Linea((cubo2D[i][X]*ampX)+desX, (cubo[i][Y]*ampY)+desY, (cubo[i+1][X]*ampX)+desX, (cubo2D[i+1][Y]*ampY)+desY, color);
            }
        }

    }


    public void putPixel(int x, int y, Color c){
        bufferPixel.setRGB(0, 0, c.getRGB());
        imagenCompleta.getGraphics().drawImage(bufferPixel, x, y, this);

    }

    public Color getPixel(int x, int y){
        return new Color(imagenCompleta.getRGB(x, y));
    }

    public void Poligono(float[] x, float[] y, Color c){
        if(x.length != y.length){
            //System.out.println("Error");
        }else{
            //System.out.println("Iniciar Dibujo x");
            int nVertices = x.length;
            for(int i = 0; i<nVertices-1;i++){
                Linea(x[i], y[i], x[i+1], y[i+1], c);
                //System.out.println("Rotación"+i);
            }
            Linea(x[nVertices-1], y[nVertices-1], x[0], y[0], c);
        }
    }

    public void Rectangulo(float x1,float y1, float x2, float y2, Color c){
        float[] rectX= {x1,x1,x2,x2};
        float[] rectY= {y1,y2,y2,y1};

        Poligono(rectX, rectY, c);
    }


    public void Circulo(float centroX, float centroY, float radio,Color c){
        Elipse(centroX, centroY, radio, radio, c);
    }

    public void Elipse(float centroX, float centroY,float radioX, float radioY, Color color){
        putPixel(Math.round(centroX),Math.round(centroY+radioY),color);
        putPixel(Math.round(centroX),Math.round(centroY-radioY),color);

        float tempRadioX,tempRadioY,p1,p2;

        float x=0;
        float y=radioY;

        tempRadioX=(float) Math.pow(radioX,2);
        tempRadioY=(float) Math.pow(radioY,2);

        p1=(float) (tempRadioY-(tempRadioX*radioY)+(0.25*tempRadioX));

        while((tempRadioY*x)<(tempRadioX*y)){
            if(p1<0){
                x++;
                p1=p1+(2*tempRadioY*x)+tempRadioY;
            }else{
                x++;
                y--;
                p1=p1+(2*tempRadioY*x)-(2*tempRadioX*y)+tempRadioY;
            }
            seccionElipse(centroX,centroY,x,y,color);
        }
        p2=(float) ((tempRadioY)*Math.pow((x+0.5),2)+(tempRadioX)*Math.pow((y-1),2)-(tempRadioX*tempRadioY));
        while(y>0){
            if (p2>0){
                y--;
                p2=p2-(2*tempRadioX*y) +tempRadioX;
            }else{
                x++;
                y--;
                p2=p2+ (2*tempRadioY*x)-(2*tempRadioX*y)+tempRadioX;
            }
            seccionElipse(centroX,centroY,x,y,color);
        }
    }


    public void seccionElipse(float centroX, float centroY,float x, float y, Color c){
        putPixel(Math.round(centroX+x),Math.round(centroY+y),c);
        putPixel(Math.round(centroX-x),Math.round(centroY+y),c);
        putPixel(Math.round(centroX+x),Math.round(centroY-y),c);
        putPixel(Math.round(centroX-x),Math.round(centroY-y),c);
    }


    public void Linea(float x1, float y1, float x2, float y2, Color c){
        //LINEA DDA
        float dx = dif(x1, x2), dy = dif(y1,y2), steps;
        float xinc, yinc, x,y;

        if(Math.abs(dx)>Math.abs(dy)){
            steps = Math.abs(dx);
        }else{
            steps = Math.abs(dy);
        }

        xinc = dx/steps;
        yinc = dy/steps;
        x = x1;
        y = y1;
        putPixel(Math.round(x),Math.round(y), c);
        for(int k = 1; k<=steps; k++){
            x+=xinc;
            y+=yinc;
            putPixel(Math.round(x),Math.round(y), c);
        }

    }

    public float dif(float a1, float a2){
        return a2-a1;
    }

    private void asignarColores(){
        //miColor = new Color(66,0,161);
        int colorDesconocido = imagenCompleta.getRGB(0, 0);
        unknown = new Color(colorDesconocido);
        miColor = Color.red;

    }
    private void crearFiguras(){
        /*cubo = new float[][]{
            {-10,-10,-10},
            {10,10,-10},
            {-10,10,-10},
            {10,-10,-10},
            {-10,-10,10},
            {-10,10,10},
            {10,10,10},
            {10,-10,10}
        };
        diapositiva9 = new float[][]{
            {-10,-10,-30},//A
            {10,10,-10},//B
            {-10,10,-30},//C
            {10,-10,-10},//D
            {-10,-10,30},//E
            {-10,10,30},//F
            {10,10,10},//G
            {10,-10,10},//H
            {10,10,30},//I
            {10,-10,30},//J
            {10,-10,-30},//K
            {10,10,-30},//L
            {30,10,10},//M
            {30,-10,10},
            {30,10,-10},
            {30,-10,-10}
        }; */

        figX = new float[]{0,30,30,60,60,30,30,0};
        figY = new float[]{0,0,30,30,60,60,90,90};


        fig2X = new float[]{0,20,20,0};
        fig2Y = new float[]{0,0,20,20};

        //fig2X = new float[]{297,318,371,437,490,512,490,437,371,318};
        //fig2Y = new float[]{525,461,423,423,461,525,587,627,627,587};

        cuboX = PoliProfundo(fig2X, fig2Y, 20);
        carasX = PoliCaras(cuboX);
        centrosX = PoliCentro(cuboX, 20);

        cuboE = PoliProfundo(fig2X, fig2Y, 20);
        carasE = PoliCaras(cuboE);
        centrosE = PoliCentro(cuboE, 20);

        cuboE = rotacionX(cuboE, 10);
        centrosE = rotacionX(centrosE,10);
        cuboE = rotacionY(cuboE, 10);
        centrosE = rotacionY(centrosE,10);
        cuboE = rotacionZ(cuboE, 5);
        centrosE = rotacionZ(centrosE,5);

        cuboT = PoliProfundo(fig2X, fig2Y, 20);
        carasT = PoliCaras(cuboT);
        centrosT = PoliCentro(cuboT, 20);

        cuboT = rotacionX(cuboT, 10);
        centrosT = rotacionX(centrosT,10);
        cuboT = rotacionY(cuboT, 10);
        centrosT = rotacionY(centrosT,10);
        cuboT = rotacionZ(cuboT, 5);
        centrosT = rotacionZ(centrosT,5);

        int nc = 0;

    }

    @Override
    public void run() {
        while(true){
            try{
                repaint();
                hilo.sleep(100);
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }
}