
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

class ImageProcess {
    public BufferedImage current, redrawn;
    public int[] largeSquare;
    public int [][] largeRGB;
    public int [][] smallRGB;
    public int width, height;
    public int newpixelvalue;
    public Color c;
    
    public ImageProcess(BufferedImage input){
        current = input;
        width = input.getWidth();
        height = input.getHeight();
        redrawn = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        
        smallRGB = new int[3][4];
        largeRGB = new int[3][9];
        
        
    }
    public void saveFile(){
        try{
         
            File f = new File("new.bmp");
            

            ImageIO.write(redrawn, "bmp", f);
            
            
            
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
    
    public void processFile()
    {
        for(int i =0; i<width; i++)
            
        {
            
            for(int j=0;j<height ;j++ )
            {   
                //top left corner
                if(i==0 &&j==0)
                { 
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //top right corner
                else if(i==width-1&&j==0)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom left corner
                else if (i==0&&j==height-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j-1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom right corner
                else if (i==width-1&&j==height-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j-1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //left column
                else if(i==0)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //top row
                else if(j==0)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //bottom row
                else if(j==height-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j-1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //right column
                else if(i==width-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                else
                {
                    c = new Color(current.getRGB(i-1, j-1));
                    largeRGB[0][0] = c.getRed();
                    largeRGB[1][0] = c.getGreen();
                    largeRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    largeRGB[0][1] = c.getRed();
                    largeRGB[1][1] = c.getGreen();
                    largeRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j-1));
                    largeRGB[0][2] = c.getRed();
                    largeRGB[1][2] = c.getGreen();
                    largeRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    largeRGB[0][3] = c.getRed();
                    largeRGB[1][3] = c.getGreen();
                    largeRGB[2][3] = c.getBlue();
                    c = new Color(current.getRGB(i, j));
                    largeRGB[0][4] = c.getRed();
                    largeRGB[1][4] = c.getGreen();
                    largeRGB[2][4] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    largeRGB[0][5] = c.getRed();
                    largeRGB[1][5] = c.getGreen();
                    largeRGB[2][5] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    largeRGB[0][6] = c.getRed();
                    largeRGB[1][6] = c.getGreen();
                    largeRGB[2][6] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    largeRGB[0][7] = c.getRed();
                    largeRGB[1][7] = c.getGreen();
                    largeRGB[2][7] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j+1));
                    largeRGB[0][8] = c.getRed();
                    largeRGB[1][8] = c.getGreen();
                    largeRGB[2][8] = c.getBlue();


                    newpixelvalue = this.getSAverage(largeRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
            
            
            }
        }
        
        
        
    }
    
    public int getSAverage(int[][] smallRGB){
        int color=0;
        int length = smallRGB[0].length;
        int red =0 , green =0 , blue= 0;
        for(int i= 0; i<3; i++){
            //for each color
            for(int j=0; j<length; j++){
                //for each index
                if(i==0){
                    red = red+smallRGB[i][j];
                }
                else if(i==1){
                    green = green+smallRGB[i][j];
                }else{
                    blue = blue+smallRGB[i][j];
                }
                
            }
        }
        red = red/length;
        green = green/length;
        blue=blue/length;
        
        color = new Color(red,green,blue).getRGB();
        
        return color;
    }
    
}



public class ImageProcessingProject {
    

    
    public static void main (String[] args){
        long startTime =0;
        BufferedImage current = null;
        
        ImageProcess imageProcess = null;  
        try
        {
            current = ImageIO.read(new File("old.bmp"));
            startTime= System.currentTimeMillis();
            imageProcess = new ImageProcess(current);
            
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
        imageProcess.processFile();
        long duration = System.currentTimeMillis() - startTime;
        
        System.out.println("Single threaded Program duration in mili seconds: "+ duration);
        imageProcess.saveFile();
        
        
        myThread.current = current;
        
        startTime= System.currentTimeMillis();
        ExecutorService executor = Executors.newCachedThreadPool();
        
        int height = current.getHeight();
        int width = current.getWidth();
       
        
        myThread.redrawn = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        int division =2;
        
        int hfactor = height/division;
        int wfactor = width/division;
        
            
        
        int heightStartIndex, heightEndIndex, widthStartIndex, widthEndIndex;
        
//        Runnable run = new mThread(0, width, 0, height);
//                
//                executor.execute(run);
      
        
        for (int i =0; i<division; i++)
        {  
            
            widthStartIndex = i*wfactor;
            widthEndIndex = widthStartIndex + wfactor;
            
            if(i==division -1){
                widthStartIndex =i*wfactor;
                widthEndIndex = width;
            }
           
            for(int j=0; j<division; j++)
            {
                heightStartIndex =j*hfactor;
                heightEndIndex = heightStartIndex + hfactor;
                
                if(j==division -1){
                heightStartIndex =j*hfactor;
                heightEndIndex = height;
                }
                
                Runnable run = new myThread(widthStartIndex, widthEndIndex, heightStartIndex, heightEndIndex);
                executor.execute(run);
                
            }
        }
        
        executor.shutdown();
        
        try
        {
            executor.awaitTermination(20, TimeUnit.SECONDS);
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
        duration = System.currentTimeMillis() - startTime;
        
        System.out.println("Program duration for multithread in mili seconds: "+ duration);
        
        myThread.saveFile();
        
        
        
        
        
    }
}
class myThread implements Runnable
{
    public int count =0;
    public static BufferedImage current,redrawn;
    public int [][] largeRGB;
    public int [][] smallRGB;
    public int width, height;
    public int newpixelvalue;
    public int wstart, hstart;
    Color c;
    @Override
    public void run() {
        count++;
       
        processFile();
        
    }
    
    
    public myThread(int startwidth,int width, int startheight,int height){
        
        this.width = width;
        this.height = height;
        wstart = startwidth;
        hstart = startheight;
        smallRGB = new int[3][4];
        largeRGB = new int[3][9];
        
    }
    public static void saveFile(){
        try{
         
            File f = new File("new.bmp");
            

            ImageIO.write(redrawn, "bmp", f);
           
            
            
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
    }
    
    public void processFile()
    {
        for(int i =wstart; i<width; i++)
            
        {
            
            for(int j=hstart;j<height ;j++ )
            {   
                //top left corner
                if(i==wstart &&j==hstart)
                { 
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //top right corner
                else if(i==width-1&&j==hstart)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom left corner
                else if (i==wstart&&j==height-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j-1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom right corner
                else if (i==width-1&&j==height-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j-1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //left column
                else if(i==wstart)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
               // top row
                else if(j==hstart)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //bottom row
                else if(j==height-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j-1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //right column
                else if(i==width-1)
                {
                    c = new Color(current.getRGB(i, j));
                    smallRGB[0][0] = c.getRed();
                    smallRGB[1][0] = c.getGreen();
                    smallRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    smallRGB[0][1] = c.getRed();
                    smallRGB[1][1] = c.getGreen();
                    smallRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    smallRGB[0][2] = c.getRed();
                    smallRGB[1][2] = c.getGreen();
                    smallRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    smallRGB[0][3] = c.getRed();
                    smallRGB[1][3] = c.getGreen();
                    smallRGB[2][3] = c.getBlue();
                    newpixelvalue = this.getSAverage(smallRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                else
                {
                     c = new Color(current.getRGB(i-1, j-1));
                    largeRGB[0][0] = c.getRed();
                    largeRGB[1][0] = c.getGreen();
                    largeRGB[2][0] = c.getBlue();
                    c = new Color(current.getRGB(i, j-1));
                    largeRGB[0][1] = c.getRed();
                    largeRGB[1][1] = c.getGreen();
                    largeRGB[2][1] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j-1));
                    largeRGB[0][2] = c.getRed();
                    largeRGB[1][2] = c.getGreen();
                    largeRGB[2][2] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j));
                    largeRGB[0][3] = c.getRed();
                    largeRGB[1][3] = c.getGreen();
                    largeRGB[2][3] = c.getBlue();
                    c = new Color(current.getRGB(i, j));
                    largeRGB[0][4] = c.getRed();
                    largeRGB[1][4] = c.getGreen();
                    largeRGB[2][4] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j));
                    largeRGB[0][5] = c.getRed();
                    largeRGB[1][5] = c.getGreen();
                    largeRGB[2][5] = c.getBlue();
                    c = new Color(current.getRGB(i-1, j+1));
                    largeRGB[0][6] = c.getRed();
                    largeRGB[1][6] = c.getGreen();
                    largeRGB[2][6] = c.getBlue();
                    c = new Color(current.getRGB(i, j+1));
                    largeRGB[0][7] = c.getRed();
                    largeRGB[1][7] = c.getGreen();
                    largeRGB[2][7] = c.getBlue();
                    c = new Color(current.getRGB(i+1, j+1));
                    largeRGB[0][8] = c.getRed();
                    largeRGB[1][8] = c.getGreen();
                    largeRGB[2][8] = c.getBlue();


                    newpixelvalue = this.getSAverage(largeRGB);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
            
            
            }
        }
        
       // saveFile();
        
    }
    
    public int getSAverage(int[][] smallRGB){
        int color=0;
        int length = smallRGB[0].length;
        int red =0 , green =0 , blue= 0;
        for(int i= 0; i<3; i++){
            //for each color
            for(int j=0; j<length; j++){
                //for each index
                if(i==0){
                    red = red+smallRGB[i][j];
                }
                else if(i==1){
                    green = green+smallRGB[i][j];
                }else{
                    blue = blue+smallRGB[i][j];
                }
                
            }
        }
        red = red/length;
        green = green/length;
        blue=blue/length;
        
        color = new Color(red,green,blue).getRGB();
        
        return color;
    }

    
    
}
    
    

      
    

