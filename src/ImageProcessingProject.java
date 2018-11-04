




import java.awt.image.BufferedImage;
import java.io.File;
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
            //System.out.println("\n"+xd);
            
            
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
        ImageProcess imageProcess = null;  
        BufferedImage current = null;
        try
        {
            current = ImageIO.read(new File("old.bmp"));
            imageProcess = new ImageProcess(current);
            
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
        
        imageProcess.processFile();
    }
      
    
}
