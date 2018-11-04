




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
        int pixels;
        int h = img.getHeight();
        int w= img.getWidth();
        BufferedImage nu = new BufferedImage(img.getWidth(),img.getHeight(),BufferedImage.TYPE_INT_RGB);
        
        for(int i =0; i<w; i++)
        {
            for(int j=0; j<h; j++)
            {
             pixels = img.getRGB(i, j);
             nu.setRGB(i, j, pixels);
            }
        }
        
        
        try{
            String[] names = ImageIO.getWriterFormatNames();
            f = new File("C://Users/Family/Desktop/Project1/ImageProcessing/new.bmp");
            
//            for(String s: names)
//            {
//                System.out.print(s+ " ");
//            }
            boolean xd = ImageIO.write(nu, "bmp", f);
            System.out.println("\n"+xd);
            
            
        }catch (Exception e)
        {
            e.getLocalizedMessage();
        }
    }
    
}
