package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by gali95 on 12.08.16.
 */
public class ImagePanel extends JPanel {

    private BufferedImage image;

    public ImagePanel()
    {
        setPreferredSize(new Dimension(300,300));
    }

    public void SetImage(String path)
    {
        try {
            image = ImageIO.read(new File(path));

        } catch (IOException ex) {

        }
        try
        {
            getGraphics().drawImage(image, 0, 0, null);
        }
        catch (java.lang.NullPointerException ex)
        {

        }
        //paintComponent(getGraphics());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null); // see javadoc for more info on the parameters
    }

}
