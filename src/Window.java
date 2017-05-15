/**
 * Created by Aaron on 5/15/2017.
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Window {

    private static String fileLocation;
    private static final JFrame frame = new JFrame("Meme Generator V4.20");
    public static void createWindow(){
        frame.getContentPane().setLayout(new GridBagLayout());
        JPanel pane = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel text = new JLabel("This is a test", SwingConstants.CENTER);
        //text.setPreferredSize(new Dimension(300,100));
        //frame.add(text);

        JButton imageButton = new JButton(new AbstractAction("Load"){
            public void actionPerformed(ActionEvent e) {
                FileDialog fd = new FileDialog(frame, "Test", FileDialog.LOAD);
                fd.setVisible(true);
                System.out.println(fd.getDirectory()+fd.getFile());
                fileLocation = fd.getDirectory()+fd.getFile();
            }
        });

        imageButton.setPreferredSize(new Dimension(100,25));
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(imageButton, c);

        frame.pack();
        frame.setVisible(true);

        try{
            BufferedImage img = ImageIO.read(new File(fileLocation));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            frame.add(label);
        } catch (IOException e){
            e.printStackTrace();
        }


    }

}
