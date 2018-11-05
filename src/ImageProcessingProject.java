




import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

class ImageProcess {
    public BufferedImage current, redrawn;
    public int[] largeSquare;
    public int[] smallSquare;
    public int width, height;
    public int newpixelvalue;
    
    public ImageProcess(BufferedImage input){
        current = input;
        width = input.getWidth();
        height = input.getHeight();
        redrawn = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        smallSquare = new int[4];
        largeSquare = new int[9];
        
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
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i, j+1);
                    smallSquare[2] = current.getRGB(i+1, j+1);
                    smallSquare[3] = current.getRGB(i+1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //top right corner
                else if(i==width-1&&j==0)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j);
                    smallSquare[2] = current.getRGB(i-1, j+1);
                    smallSquare[3] = current.getRGB(i, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom left corner
                else if (i==0&&j==height-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i, j-1);
                    smallSquare[2] = current.getRGB(i+1, j-1);
                    smallSquare[3] = current.getRGB(i+1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom right corner
                else if (i==width-1&&j==height-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j-1);
                    smallSquare[2] = current.getRGB(i, j-1);
                    smallSquare[3] = current.getRGB(i-1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //left column
                else if(i==0)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i+1, j);
                    smallSquare[2] = current.getRGB(i, j+1);
                    smallSquare[3] = current.getRGB(i+1, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //top row
                else if(j==0)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j);
                    smallSquare[2] = current.getRGB(i-1, j+1);
                    smallSquare[3] = current.getRGB(i, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //bottom row
                else if(j==height-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i, j-1);
                    smallSquare[2] = current.getRGB(i-1, j-1);
                    smallSquare[3] = current.getRGB(i-1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //right column
                else if(i==width-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j);
                    smallSquare[2] = current.getRGB(i, j+1);
                    smallSquare[3] = current.getRGB(i-1, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                else
                {
                    largeSquare[0] = current.getRGB(i-1, j-1);
                    largeSquare[1] = current.getRGB(i, j-1);
                    largeSquare[2] = current.getRGB(i+1, j-1);
                    largeSquare[3] = current.getRGB(i-1, j);
                    largeSquare[4] = current.getRGB(i, j);
                    largeSquare[5] = current.getRGB(i+1, j);
                    largeSquare[6] = current.getRGB(i-1, j+1);
                    largeSquare[7] = current.getRGB(i, j+1);
                    largeSquare[8] = current.getRGB(i+1, j+1);


                    newpixelvalue = this.getAverage(largeSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
            
            
            }
        }
        
        saveFile();
        
    }
    
    public int getAverage(int[] array){
        int total=0;
        for(int n:array)
            total = total+n;
        
        total = total/array.length;
        
        return total;
    }
    
}



public class ImageProcessingProject {
    

    
    public static void main (String[] args){
        final long startTime = System.currentTimeMillis();
       // ImageProcess imageProcess = null;  
        BufferedImage current = null;
        try
        {
            current = ImageIO.read(new File("old.bmp"));
          //  imageProcess = new ImageProcess(current);
            
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
      //  imageProcess.processFile();
        ExecutorService executor = Executors.newFixedThreadPool(10);
        
        int height = current.getHeight();
        
        int width = current.getWidth();
       
        myThread.current = current;
        myThread.redrawn = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        int division =8;
        
        int hfactor, wfactor;
        
        
            hfactor = height/division;
        
            wfactor = width/division;
        
            
        
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
        try{
        executor.awaitTermination(20, TimeUnit.SECONDS);
        }catch (Exception e)
        {System.out.println(e.getLocalizedMessage());}
        
        myThread.saveFile();
        
        
        final long duration = System.currentTimeMillis() - startTime;
        
        System.out.println("Program duration in mili seconds: "+ duration);
        
        
    }
}
    
    class myThread implements Runnable{
    public static BufferedImage current,redrawn;
    public int[] largeSquare;
    public int[] smallSquare;
    public int width, height;
    public int newpixelvalue;
    public int wstart, hstart;
    @Override
    public void run() {
        processFile();
       
    }
    
    
    public myThread(int startwidth,int width, int startheight,int height){
        
        this.width = width;
        this.height = height;
        wstart = startwidth;
        hstart = startheight;
        
        smallSquare = new int[4];
        largeSquare = new int[9];
        
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
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i, j+1);
                    smallSquare[2] = current.getRGB(i+1, j+1);
                    smallSquare[3] = current.getRGB(i+1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //top right corner
                else if(i==width-1&&j==hstart)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j);
                    smallSquare[2] = current.getRGB(i-1, j+1);
                    smallSquare[3] = current.getRGB(i, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom left corner
                else if (i==wstart&&j==height-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i, j-1);
                    smallSquare[2] = current.getRGB(i+1, j-1);
                    smallSquare[3] = current.getRGB(i+1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //bottom right corner
                else if (i==width-1&&j==height-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j-1);
                    smallSquare[2] = current.getRGB(i, j-1);
                    smallSquare[3] = current.getRGB(i-1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //left column
                else if(i==wstart)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i+1, j);
                    smallSquare[2] = current.getRGB(i, j+1);
                    smallSquare[3] = current.getRGB(i+1, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
               // top row
                else if(j==hstart)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j);
                    smallSquare[2] = current.getRGB(i-1, j+1);
                    smallSquare[3] = current.getRGB(i, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                //bottom row
                else if(j==height-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i, j-1);
                    smallSquare[2] = current.getRGB(i-1, j-1);
                    smallSquare[3] = current.getRGB(i-1, j);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
                //right column
                else if(i==width-1)
                {
                    smallSquare[0] = current.getRGB(i, j);
                    smallSquare[1] = current.getRGB(i-1, j);
                    smallSquare[2] = current.getRGB(i, j+1);
                    smallSquare[3] = current.getRGB(i-1, j+1);
                    newpixelvalue = this.getAverage(smallSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                    
                }
                else
                {
                    largeSquare[0] = current.getRGB(i-1, j-1);
                    largeSquare[1] = current.getRGB(i, j-1);
                    largeSquare[2] = current.getRGB(i+1, j-1);
                    largeSquare[3] = current.getRGB(i-1, j);
                    largeSquare[4] = current.getRGB(i, j);
                    largeSquare[5] = current.getRGB(i+1, j);
                    largeSquare[6] = current.getRGB(i-1, j+1);
                    largeSquare[7] = current.getRGB(i, j+1);
                    largeSquare[8] = current.getRGB(i+1, j+1);


                    newpixelvalue = this.getAverage(largeSquare);
                    redrawn.setRGB(i, j, newpixelvalue);
                }
            
            
            }
        }
        
       // saveFile();
        
    }
    
    public int getAverage(int[] array){
        int total=0;
        for(int n:array)
            total = total+n;
        
        total = total/array.length;
        
        return total;
    }

    
    
}

      
    

