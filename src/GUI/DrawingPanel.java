package GUI;

import main.Line;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by gali95 on 18.09.16.
 */


 public class DrawingPanel extends JPanel {


    private int tool = 1;
    int currentX=-100, currentY, oldX, oldY;
    public java.util.List<Line> lines;
    int drawnLines;
    Boolean clearIt;

        public DrawingPanel()
        {
            drawnLines = 0;
            setPreferredSize(new Dimension(300,300));
            setBackground(new java.awt.Color(255, 255, 255));
            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent evt) {
                    drawPanelMousePressed(evt);
                }
                public void mouseReleased(MouseEvent evt) {
                    drawPanelMouseReleased(evt);
                }
            });
            addMouseMotionListener(new MouseMotionAdapter() {
                public void mouseDragged(MouseEvent evt) {
                    drawPanelMouseDragged(evt);
                }
            });

            lines = new ArrayList<Line>();
            drawnLines = lines.size()+1;
            WipeIt();
            if(isFocusable()) System.out.println("dziauaaaafasfas");

        }
        @Override
        public void paintComponent(Graphics g) {


            try {

                if (drawnLines > lines.size()) {
                    paintBackground(g);
                    paintLines(g, 0, lines.size() - 1);

                } else if (drawnLines < lines.size()) {
                    paintLines(g, drawnLines, lines.size() - 1);
                }
                drawnLines = lines.size();
                //super.paintComponent(g);

            } catch (java.lang.NullPointerException exc) {

            }



            return;
            //DrawLines();
            //
        }
        public void DrawIt()
        {

             paintComponent(getGraphics());

        }
        public void WipeIt()
        {
            drawnLines = lines.size()+1;
            DrawIt();
        }
        private void paintBackground(Graphics g)
        {
            Color temp = g.getColor();
            g.setColor(Color.white);
            g.fillRect(0, 0, 300, 300);
            g.setColor(temp);
            drawnLines = 0;
        }
        private void paintLines(Graphics g, int from, int to)
        {
            if(from > to) return;
            if(from < 0) return;
            if(from >= lines.size()) return;
            if(to >= lines.size()) to=lines.size()-1;

            for(int i=from;i<=to;i++)
                g.drawLine((int)((Line)lines.get(i)).a.x,(int)((Line)lines.get(i)).a.y,(int)((Line)lines.get(i)).b.x,(int)((Line)lines.get(i)).b.y);
            return;
        }
    private void drawPanelMouseDragged(MouseEvent evt) {

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
            paintComponent(getGraphics());
            //DrawLines();

        }
    }

    private void drawPanelMousePressed(MouseEvent evt) {

        tool = evt.getButton();
        currentX = evt.getX();
        currentY = evt.getY();
        //System.out.println(oldX + " " + oldY + "olaboga");
    }


    //mouse released//
    private void drawPanelMouseReleased(MouseEvent evt) {

        //System.out.println(evt.getButton());
        if(tool==3)
        {
            ResetLines();
            paintComponent(getGraphics());
            return;

        }
            if(currentX != -100) {
                oldX = currentX;
                oldY = currentY;
            }

            currentX = evt.getX();
            currentY = evt.getY();
            lines.add(new Line(oldX,oldY,currentX,currentY));
            //System.out.println("line!!!! from" + oldX + "to" + currentX);
            paintComponent(getGraphics());
            //DrawLines();

    }

    private void ResetLines()
    {
        lines = new LinkedList();
    }


}


