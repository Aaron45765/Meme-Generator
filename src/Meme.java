import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aaron on 5/9/2017.
 */
public class Meme {
    private String text;
    private BufferedImage canvas;
    private static final int BORDER_WIDTH = 10;
    private int TEXT_WIDTH = 60;

    Meme(String text, BufferedImage image) {
        TEXT_WIDTH = 30 * (1 + (text.length() / 50)); // lol you thought this was a final field
        this.text = text;
        try {
            image = getScaledImage(image,500,300);
            canvas = new BufferedImage(image.getWidth() + BORDER_WIDTH, image.getHeight() + BORDER_WIDTH + TEXT_WIDTH, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < canvas.getWidth(); x++) {
                for (int y = 0; y < canvas.getHeight(); y++) {
                    canvas.setRGB(x, y, Color.white.getRGB());
                }
            }

            Graphics g = canvas.getGraphics();
            g.drawImage(image, 4, TEXT_WIDTH, null);
            g.setColor(Color.black);
            g.setFont(new Font("Calibri", Font.PLAIN, 20));

            drawString(g, text, 4, g.getFontMetrics().getHeight() + 2, image.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Meme(String text, String path) {
        TEXT_WIDTH = 30 * (1 + (text.length() / 50)); // lol you thought this was a final field
        this.text = text;
        try {
            BufferedImage image = getScaledImage(ImageIO.read(new File(path)), 500, 300);
            canvas = new BufferedImage(image.getWidth() + BORDER_WIDTH, image.getHeight() + BORDER_WIDTH + TEXT_WIDTH, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < canvas.getWidth(); x++) {
                for (int y = 0; y < canvas.getHeight(); y++) {
                    canvas.setRGB(x, y, Color.white.getRGB());
                }
            }

            Graphics g = canvas.getGraphics();
            g.drawImage(image, 4, TEXT_WIDTH, null);
            g.setColor(Color.black);
            g.setFont(new Font("Calibri", Font.PLAIN, 20));

            drawString(g, text, 4, g.getFontMetrics().getHeight() + 2, image.getWidth());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void drawString(Graphics g, String s, int x, int y, int width) {
        // FontMetrics gives us information about the width,
        // height, etc. of the current Graphics object's Font.
        FontMetrics fm = g.getFontMetrics();

        int lineHeight = fm.getHeight();

        int curX = x;
        int curY = y;

        String[] words = s.split(" ");

        for (String word : words) {
            // Find out thw width of the word.
            int wordWidth = fm.stringWidth(word + " ");

            // If text exceeds the width, then move to next line.
            if (curX + wordWidth >= x + width) {
                curY += lineHeight;
                curX = x;
            }

            g.drawString(word, curX, curY);

            // Move over to the right for next word.
            curX += wordWidth;
        }
    }

    public void drawStringTabs(Graphics g, String s, int x, int y) {
        for (String line : s.split("\n")) {
            g.drawString(line, x -= g.getFontMetrics().getHeight(), y);
        }
    }

    public void saveAsFile(String filename) {


        try {
            File output = new File(filename + ".jpg");
            System.out.println("file obj created!");
            ImageIO.write(canvas, "jpg", output);
            System.out.println("outputted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveAsFile(File f) {
        try {
            File output = f;
            System.out.println("file obj created!");
            ImageIO.write(canvas, "jpg", output);
            System.out.println("outputted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static BufferedImage getScaledImage(BufferedImage image, int width, int height) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) width / imageWidth;
        double scaleY = (double) height / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
                image,
                new BufferedImage(width, height, image.getType()));
    }

    public BufferedImage getMeme(){
        return canvas;
    }
}
