import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
/**
   This class represents a possible traffic light view.
   In this case, it corresponds to a street traffic light 
   (standard red, green, and yellow light)
*/
public class StreetTrafficLight extends TrafficLight implements View {

   /**
    * StreetTrafficLight constructor
    * @param ft is follow time
    * @param tt is transition time
    * @param posx is the x coordinate of the origin of the traffic light
    * @param posy is the y coordinate od the origin of the traffic light
    */
   public StreetTrafficLight (int ft, int tt, int posx, int posy) {
      super(ft, tt);
      this.origen_x=posx;
      this.origen_y=posy;
      red_view = new Ellipse2D.Double(origen_x, origen_y, DIAMETER, DIAMETER);
      yellow_view = new Ellipse2D.Double(origen_x, origen_y+DIAMETER, DIAMETER, DIAMETER);
      green_view = new Ellipse2D.Double(origen_x, origen_y+2*DIAMETER, DIAMETER, DIAMETER);
      cabinet = red_view.getFrame();
      cabinet = cabinet.createUnion(yellow_view.getFrame());
      cabinet = cabinet.createUnion(green_view.getFrame());
   }  

   /**
    * Paints the traffic ligths turning each light on and off accordingly
    */
   public void paint_view (Graphics2D g2d) {
      g2d.setColor(Color.BLACK);
      g2d.fill(cabinet);
      switch (getState()) {
         case STOP: g2d.setColor(Color.RED); 
                    g2d.fill(red_view);
                    g2d.setColor(Color.GRAY); 
                    g2d.fill(yellow_view);
                    g2d.fill(green_view);
                    break;
         case TRANSITION: g2d.setColor(Color.GRAY);
                          g2d.fill(red_view);
                          g2d.fill(green_view);
                          g2d.setColor(Color.YELLOW); 
                          g2d.fill(yellow_view);
                          break;
         case FOLLOW: g2d.setColor(Color.GRAY);
                      g2d.fill(red_view);
                      g2d.fill(yellow_view);
                      g2d.setColor(Color.GREEN); 
                      g2d.fill(green_view);
                      break;
         default: g2d.setColor(Color.GRAY);
                  g2d.fill(red_view);
                  g2d.fill(yellow_view);
                  g2d.fill(green_view);
      }        
   }

   /**
    * Sets position of the traffic ligth
    * @param x is the x coordinate of the origin of the traffic light
    * @param y is the y coordinate of the origin of the traffic light
   */
   public void set_pos_trafficlight(int x, int y){
      this.origen_x=x;
      this.origen_y=y;
   }

   private TrafficLight model;
   private Ellipse2D red_view, yellow_view, green_view;
   private Rectangle2D cabinet;
   private int origen_x=20;
   private int origen_y=50;
   private static int DIAMETER=50;
}
