package GUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created by gali95 on 30.08.16.
 */
public class CustomJPanel extends JPanel {

    public CustomJPanel()
    {
        setPreferredSize(new Dimension(420,420));
        JLabel lab1 = new JLabel("User Name", JLabel.LEFT);
        setLayout(new FlowLayout());
        add(lab1 = new JLabel("add JLabel"));

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawString("BLAH", 20, 20);
        g.drawRect(200, 200, 200, 200);

    }


}
