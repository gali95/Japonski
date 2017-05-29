package GUI;

import main.Line;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;


class JavaPaintUI extends JFrame {

    private int tool = 1;
    int currentX=-100, currentY, oldX, oldY;

    public JavaPaintUI() {
        initComponents();
    }
    private List lines;

    private void initComponents() {
        // we want a custom Panel2, not a generic JPanel!
        jPanel2 = new Panel2();

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                jPanel2MousePressed(evt);
            }
            public void mouseReleased(MouseEvent evt) {
                jPanel2MouseReleased(evt);
            }
        });
        jPanel2.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent evt) {
                jPanel2MouseDragged(evt);
            }
        });

        lines = new LinkedList();

        // add the component to the frame to see it!
        this.setContentPane(jPanel2);
        // be nice to testers..
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
    }// </editor-fold>

    private void jPanel2MouseDragged(MouseEvent evt) {
        if (tool == 1) {

            if(currentX != -100) {
                oldX = currentX;
                oldY = currentY;
            }
            currentX = evt.getX();
            currentY = evt.getY();

            //System.out.println(currentX + " " + currentY);
            //System.out.println("PEN!!!!");
            lines.add(new Line(oldX,oldY,currentX,currentY));
            //jPanel2.getGraphics().drawString("nigga",50,50);
            DrawLines();
        }
    }

    private void jPanel2MousePressed(MouseEvent evt) {
        oldX = evt.getX();
        oldY = evt.getY();
        System.out.println(oldX + " " + oldY + "olaboga");
    }

    private void DrawLines()
    {
        for(int i=0;i<lines.size();i++)
        jPanel2.getGraphics().drawLine((int)((Line)lines.get(i)).a.x,(int)((Line)lines.get(i)).a.y,(int)((Line)lines.get(i)).b.x,(int)((Line)lines.get(i)).b.y);
    }


    //mouse released//
    private void jPanel2MouseReleased(MouseEvent evt) {
        if (tool == 2) {
            currentX = evt.getX();
            currentY = evt.getY();
            System.out.println("line!!!! from" + oldX + "to" + currentX);
        }
    }

    //set ui visible//
    public static void main(String args[]) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JavaPaintUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private JPanel jPanel2;
    // End of variables declaration

    // This class name is very confusing, since it is also used as the
    // name of an attribute!
    //class jPanel2 extends JPanel {
    class Panel2 extends JPanel {

        

        Panel2() {
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(420,420));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawString("BLAH", 20, 20);
            g.drawRect(200, 200, 200, 200);

        }
    }
}