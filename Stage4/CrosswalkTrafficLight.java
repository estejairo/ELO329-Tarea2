/**
   @version 1.0 2019-05-03
   @author Paula Amigo
   @author Luis Bahamondes
   @author Jairo Gonzalez
*/

import java.awt.*;
import java.awt.geom.*;
//import java.util.*;
import java.awt.event.*;
import javax.swing.*;
/**
   This class represents a possible traffic light view.
   In this case, it corresponds to a pedestrian crosswalk traffic light 
   (red, green, and green blinking light)
*/
public class CrosswalkTrafficLight extends TrafficLight implements ActionListener, View{

   /**
    * CrosswalkTrafficLight constructor
    * @param ft is follow time
    * @param tt is transition time
    * @param posx is the x coordinate of the origin of the traffic light
    * @param posy is the y coordinate of the origin of the traffic light
    */
   public CrosswalkTrafficLight (int ft, int tt, int posx,int posy) {
      super(ft, tt);
      this.origen_x=posx;
      this.origen_y=posy;
      red_view = new Ellipse2D.Double(origen_x, origen_y, DIAMETER, DIAMETER);
      green_view = new Ellipse2D.Double(origen_x, origen_y+DIAMETER, DIAMETER, DIAMETER);
      cabinet = red_view.getFrame();
      cabinet = cabinet.createUnion(green_view.getFrame());

      timer = new Timer(ON_OFF_TIME, this);
      green_on=false; 
   }

   /**
    * Paints each of the lights on the traffic ligth, turning them on and off, according to the state of it
    */
   public void paint_view (Graphics2D g2d) {
      g2d.setColor(Color.BLACK);
      g2d.fill(cabinet);
      switch (getState()) {
         case STOP: if (timer.isRunning()) timer.stop();
                    g2d.setColor(Color.RED); 
                    g2d.fill(red_view);
                    g2d.setColor(Color.GRAY); 
                    g2d.fill(green_view);
                    break;
         case TRANSITION: if (!timer.isRunning()) timer.start();
                          g2d.setColor(Color.GRAY);
                          g2d.fill(red_view);
                          if(green_on)
                             g2d.setColor(Color.GREEN); 
                          g2d.fill(green_view);
                          break;
         case FOLLOW: g2d.setColor(Color.GRAY);
                      g2d.fill(red_view);
                      g2d.setColor(Color.GREEN); 
                      g2d.fill(green_view);
      }        
   }
   /**
    * Called on intervals to turn green light on and off repeatedly
    * to make it blink in transition mode
    */
   public void actionPerformed (ActionEvent event) {
      green_on = !green_on;
      panel.repaint();      
   }
   private Ellipse2D red_view, green_view;
   private Rectangle2D cabinet;
   private Timer timer;
   private boolean green_on;
   private int origen_x=100;
   private int origen_y=50;
   private static int DIAMETER=50;
   private static int ON_OFF_TIME = 500; //[ms]
}
