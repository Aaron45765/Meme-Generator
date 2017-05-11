import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Created by Aaron on 5/9/2017.
 */
public class Meme {
    private String text;
    private BufferedImage canvas;
    private static final int BORDER_WIDTH = 10;
    private static final int TEXT_WIDTH = 60;
    Meme(String text, BufferedImage image){
        this.text = text;
        canvas = new BufferedImage(image.getWidth() + BORDER_WIDTH, image.getHeight() + BORDER_WIDTH+TEXT_WIDTH, BufferedImage.TYPE_INT_RGB);
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                canvas.setRGB(x,y, Color.white.getRGB());
            }
        }
        Graphics g = canvas.getGraphics();
        g.drawImage(image,4, TEXT_WIDTH,null);
    }

    Meme(String text, String path){
        this.text = text;
        try {
            BufferedImage image = ImageIO.read(new File(path));
            canvas = new BufferedImage(image.getWidth() + BORDER_WIDTH, image.getHeight() + BORDER_WIDTH+TEXT_WIDTH, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < canvas.getWidth(); x++) {
                for (int y = 0; y < canvas.getHeight(); y++) {
                    canvas.setRGB(x, y, Color.white.getRGB());
                }
            }

            Graphics g = canvas.getGraphics();
            g.drawImage(image,4, TEXT_WIDTH,null);
            g.setColor(Color.black);
            g.setFont(new Font("Calibri", Font.PLAIN, 20));

            g.drawString("im dumb", 4, TEXT_WIDTH/2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void saveAsFile(String filename){


        try {
            File output = new File(filename + ".jpg");
            System.out.println("file obj created!");
            ImageIO.write(canvas, "jpg", output);
            System.out.println("outputted!");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
