
package transformaciones;

//@author Alberto Rivero <alberto.rg94@hotmail.com>

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Translation3D extends JPanel implements Runnable {
          
    static private BufferedImage pixelBuffer; 
    static private Color background; 
    private final Thread thread;     
   
    double fps = 120.0; 
    double time = 1000/fps;
    
    double cube3D[][] = {   {100, 200, 200, 100, 100, 200, 200, 100},
                            {100, 100, 200, 200, 100, 100, 200, 200},
                            {100, 100, 100, 100, 200, 200, 200, 200},
                            {  1,   1,   1,   1,   1,   1,   1,   1}
    };
    
    double stepx = cube3D[0][0];
    double stepy = cube3D[1][0];
    double stepz = cube3D[2][0];
    
    double proyectionPoint[] = {500, 400 , 800};
    
    double[][] cubeProyection;
    
    public Translation3D() {                        
        
        pixelBuffer = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        background = new Color (36, 37, 39);
        thread = new Thread(this);
        thread.start();
             
    }//contructor
    
    public static void main(String[] args) {
        
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setTitle("Translation3D");
        frame.setResizable(false);
        Translation3D cube = new Translation3D(); 
        cube.setDoubleBuffered(true);
        cube.setBackground(background);
        frame.add(cube);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
           
    }//main       
    
    @Override
    public Dimension getPreferredSize() {
               
        return new Dimension(500, 500);
        
    }//getPreferrefSize 
    
    @Override
    public void run() {
        
        while(true) {
            
            try {  
                                                                                     
                    Thread.sleep((int) time); 
                    repaint();
                                                   
            } catch (InterruptedException e) {}
                         
        }
        
    }//run      
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        translation(400, 400, 300, cube3D);
        drawCube3D(cubeProyection, Color.ORANGE, g);
        
    }//paintComponent 
    
    public void translation(int x, int y, int z, double[][] figure){       
        
        double dx = x/fps;
        double dy = y/fps;
        double dz = z/fps;
        
        double Translation[][] = {  {1.0,   0,   0,  dx},
                                    {  0, 1.0,   0,  dy},
                                    {  0,   0, 1.0,  dz},
                                    {  0,   0,   0, 1.0}}; 
        
        stepx+=dx;
        stepy+=dy;
        stepz+=dz;
        
        //System.out.println("stepx: " + stepx);
        //System.out.println("stepy: " + stepy);
        //System.out.println("stepz: " + stepz);
        
        if(stepx<=x && stepy<=y && stepz<=z){
            cube3D = multiplyMatrix(Translation, figure);
        }
        
        cubeProyection = proyection(cube3D, proyectionPoint);
                           
    }
    
    // Function to multiply two matrices A[][] and B[][]
    static double[][] multiplyMatrix(double transformation[][], double figure[][]){
        
        // Print the matrices A and B
        System.out.println("\nMatrix A:");
        printMatrixD(transformation, transformation.length, transformation[0].length);
        System.out.println("\nMatrix B:");
        printMatrixD(figure, figure.length, figure[0].length); 
        
        double[][] result = new double[transformation.length][figure[0].length];

        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(transformation, figure, row, col);
            }
        }
        
        // Print the result
        System.out.println("\nResultant Matrix:");
        printMatrixD(result, transformation.length, figure[0].length);
        
        return result;
        
    }//multiplyMatrix
    
    static double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        
        double cell = 0;
        
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        
        return cell;
        
    }//multiplyMatricesCell
    
    static void printMatrixD(double M[][], int rowSize, int colSize){
        
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++)
                System.out.print(M[i][j] + " ");
 
            System.out.println();
        }
    }//printMatrix
    
    public double[][] proyection(double[][] figure, double[] point){
        
        double[][] result = new double[2][8];
        
        //x = x1 + (xp(-z1/zp))
        //y = y1 + (yp(-z1/zp))
        //[row_index][column_index]
        //array.length--returns the number of rows
        //array[0].length--returns the number of columns
        
        for(int i = 0; i < figure[0].length; i++){
            //Calculate X's
            result[0][i] = figure[0][i] + (point[0] * ((-1*figure[2][i])/point[2])) + 150;
            //Calculate Y'S
            result[1][i] = figure[1][i] + (point[1] * ((-1*figure[2][i])/point[2])) + 150;
        }
        
        System.out.println("\nResult Proyection:");
        printMatrixD(result, result.length, result[0].length);
        
        return result;
    }
    
    public void drawCube3D(double[][] proyectionMatrix, Color c, Graphics g){
        
        //FRONT
        drawLineBresenham(proyectionMatrix[0][0], proyectionMatrix[1][0], proyectionMatrix[0][1], proyectionMatrix[1][1], c, g);
        drawLineBresenham(proyectionMatrix[0][1], proyectionMatrix[1][1], proyectionMatrix[0][2], proyectionMatrix[1][2], c, g);
        drawLineBresenham(proyectionMatrix[0][2], proyectionMatrix[1][2], proyectionMatrix[0][3], proyectionMatrix[1][3], c, g);
        drawLineBresenham(proyectionMatrix[0][3], proyectionMatrix[1][3], proyectionMatrix[0][0], proyectionMatrix[1][0], c, g);
        
        //BACK
        drawLineBresenham(proyectionMatrix[0][4], proyectionMatrix[1][4], proyectionMatrix[0][5], proyectionMatrix[1][5], c, g);
        drawLineBresenham(proyectionMatrix[0][5], proyectionMatrix[1][5], proyectionMatrix[0][6], proyectionMatrix[1][6], c, g);
        drawLineBresenham(proyectionMatrix[0][6], proyectionMatrix[1][6], proyectionMatrix[0][7], proyectionMatrix[1][7], c, g);
        drawLineBresenham(proyectionMatrix[0][7], proyectionMatrix[1][7], proyectionMatrix[0][4], proyectionMatrix[1][4], c, g);
        
        //LATERAL
        drawLineBresenham(proyectionMatrix[0][0], proyectionMatrix[1][0], proyectionMatrix[0][4], proyectionMatrix[1][4], c, g);
        drawLineBresenham(proyectionMatrix[0][1], proyectionMatrix[1][1], proyectionMatrix[0][5], proyectionMatrix[1][5], c, g);
        drawLineBresenham(proyectionMatrix[0][2], proyectionMatrix[1][2], proyectionMatrix[0][6], proyectionMatrix[1][6], c, g);
        drawLineBresenham(proyectionMatrix[0][3], proyectionMatrix[1][3], proyectionMatrix[0][7], proyectionMatrix[1][7], c, g);
        
    }
    
    public void drawLineBresenham(double ax, double ay, double bx, double by, Color c, Graphics g){
        
        int x = (int) Math.round(ax);
        int y = (int) Math.round(ay);
        int x2 = (int) Math.round(bx);
        int y2 = (int) Math.round(by);
        
        
        int w = x2 - x;
        int h = y2 - y;
        int dx1 = 0, dy1 = 0, dx2 = 0, dy2 = 0;
        if (w<0) dx1 = -1; else if (w>0) dx1 = 1;
        if (h<0) dy1 = -1; else if (h>0) dy1 = 1;
        if (w<0) dx2 = -1; else if (w>0) dx2 = 1;
        int longest = Math.abs(w);
        int shortest = Math.abs(h);
        
        if (!(longest>shortest)){
            longest = Math.abs(h);
            shortest = Math.abs(w);
            
            if (h<0) 
                dy2 = -1; 
            else if (h>0) 
                dy2 = 1;
                dx2 = 0;            
        }
        
        int numerator = longest >> 1;
        
        for (int i=0;i<=longest;i++){
            putPixel(x,y,c,g) ;
            numerator += shortest ;
            
            if (!(numerator<longest)) {
                numerator -= longest;
                x += dx1;
                y += dy1;
            } else {
                x += dx2;
                y += dy2;
            }
        }  
        
    }//drawLineBresenham 
    
    public void putPixel(int x, int y, Color c, Graphics g){
        
        pixelBuffer.setRGB(0,0,c.getRGB());
        g.drawImage(pixelBuffer, x, y, this);
        
    }//putPixel    
    
}