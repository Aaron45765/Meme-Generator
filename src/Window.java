/**
 * Created by Aaron on 5/15/2017.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Window {

    private static final JFrame frame = new JFrame("Meme Generator V4.20");
    private static File image = null;
    private static JLabel label = null;
    private static Meme meme;
    private static Box box = Box.createVerticalBox();

    public static void createWindow() {
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        JButton imageButton = new JButton("Select Image");
        imageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int returnValue = fc.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    image = fc.getSelectedFile();
                    System.out.println(image.getName());
                    if (label != null)
                        box.remove(label);
                    try {
                        BufferedImage img = ImageIO.read(image);
                        img = Meme.getScaledImage(img, 500, 300);
                        ImageIcon icon = new ImageIcon(img);
                        label = new JLabel(icon);
                        label.setPreferredSize(new Dimension(300,400));
                        label.setAlignmentX(Component.CENTER_ALIGNMENT);
                        label.setAlignmentY(Component.TOP_ALIGNMENT);
                        box.add(label);
                        frame.setVisible(false);
                        frame.setVisible(true);
                    } catch (IOException arrg) {
                        arrg.printStackTrace();
                    }


                }
            }
        });

        final JTextField textField = new JTextField("Meme Text", 20);

        JButton memeButton = new JButton("Create Meme!");
        memeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (label != null)
                    box.remove(label);
                try {
                    meme = new Meme(textField.getText(), ImageIO.read(image));
                    ImageIcon icon = new ImageIcon(meme.getMeme());
                    label = new JLabel(icon);
                    label.setPreferredSize(new Dimension(300,400));
                    label.setAlignmentX(Component.CENTER_ALIGNMENT);
                    box.add(label);
                    box.updateUI();
                    frame.setVisible(false);
                    frame.setVisible(true);
                } catch (IOException x) {
                    x.printStackTrace();
                }
            }
        });

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fc = new JFileChooser();
                int rv = fc.showOpenDialog(null);
                if (rv == JFileChooser.APPROVE_OPTION) {
                    meme.saveAsFile(fc.getSelectedFile());
                }
            }
        });

        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        buttons.add(imageButton);
        buttons.add(textField);
        buttons.add(memeButton);
        buttons.add(saveButton);

        buttons.setAlignmentX(0.5f);
        box.add(buttons);

        frame.add(box);

        frame.setVisible(true);


    }

}
