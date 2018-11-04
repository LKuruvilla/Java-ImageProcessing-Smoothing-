




import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;





public class ImageProcessingProject {
    
    public static void main (String[] args){
        File f =null;    
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("old.bmp"));
        }catch (Exception e)
        {
            e.getLocalizedMessage();
        }
        int pixels =0;
        int[]p = new int[9];
        int h = img.getHeight();
        int w= img.getWidth();
        BufferedImage nu = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        int newpixelvalue =0, total =0;
        int[]x = new int[4];
        
        for(int i =0; i<w; i++)
            
        {
            
            for(int j=0;j<h ;j++ )
            {   
                //top left corner
                if(i==0 &&j==0)
                { 
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i, j+1);
                    x[2] = img.getRGB(i+1, j+1);
                    x[3] = img.getRGB(i+1, j);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                    
                }
                //top right corner
                else if(i==w-1&&j==0)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i-1, j);
                    x[2] = img.getRGB(i-1, j+1);
                    x[3] = img.getRGB(i, j+1);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                }
                //bottom left corner
                else if (i==0&&j==h-1)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i, j-1);
                    x[2] = img.getRGB(i+1, j-1);
                    x[3] = img.getRGB(i+1, j);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                }
                //bottom right corner
                else if (i==w-1&&j==h-1)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i-1, j-1);
                    x[2] = img.getRGB(i, j-1);
                    x[3] = img.getRGB(i-1, j);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                    
                }
                //left column
                else if(i==0)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i+1, j);
                    x[2] = img.getRGB(i, j+1);
                    x[3] = img.getRGB(i+1, j+1);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                    
                }
                //top row
                else if(j==0)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i-1, j);
                    x[2] = img.getRGB(i-1, j+1);
                    x[3] = img.getRGB(i, j+1);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                    
                }
                //bottom row
                else if(j==h-1)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i, j-1);
                    x[2] = img.getRGB(i-1, j-1);
                    x[3] = img.getRGB(i-1, j);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                }
                //right column
                else if(i==w-1)
                {
                    x[0] = img.getRGB(i, j);
                    x[1] = img.getRGB(i-1, j);
                    x[2] = img.getRGB(i, j+1);
                    x[3] = img.getRGB(i-1, j+1);
                    newpixelvalue = (x[0]+x[1]+x[2]+x[3])/4;
                    nu.setRGB(i, j, newpixelvalue);
                    
                }
                else
                {
                    p[0] = img.getRGB(i-1, j-1);
                    p[1] = img.getRGB(i, j-1);
                    p[2] = img.getRGB(i+1, j-1);
                    p[3] = img.getRGB(i-1, j);
                    p[4] = img.getRGB(i, j);
                    p[5] = img.getRGB(i+1, j);
                    p[6] = img.getRGB(i-1, j+1);
                    p[7] = img.getRGB(i, j+1);
                    p[8] = img.getRGB(i+1, j+1);


                    newpixelvalue = (p[0]+p[1]+p[2]+p[3]+p[4]+p[5]+p[6]+p[7]+p[8])/9;
                    nu.setRGB(i, j, newpixelvalue);
                }
            
            
            }
        }
        
        
        try{
            String[] names = ImageIO.getWriterFormatNames();
            f = new File("new.bmp");
            
//            for(String s: names)
//            {
//                System.out.print(s+ " ");
//            }
            boolean xd = ImageIO.write(nu, "bmp", f);
            System.out.println("\n"+xd);
            
            
        }catch (Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
}
