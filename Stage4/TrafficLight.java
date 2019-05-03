/**
   @version 1.00 2019-04-18
   @author Paula Amigo
   @author Luis Bahamondes
   @author Jairo Gonzalez  

*/


import java.awt.Graphics2D;
import javax.swing.JPanel;
/**  
General class for a Traffic Light. 
It is the parent class for different traffic light views.
*/
public class TrafficLight {    // store the model, it needs a view in a subclass

   /**
    * TrafficLight constructor
    * @param ft is the follow time
    * @param tt is the transition time
    */
   public TrafficLight (int ft, int tt){
      followTime = ft;
      transitionTime = tt;
      state = TrafficLightState.STOP;
   }
   /**
    * Changes state of traffic light to STOP and turns ligths accordingly
    */
   public void turnStop() {
      state = TrafficLightState.STOP;
      repaint();
   }

  
   /**
    * Changes state of traffic light to  TRANSITION and turns ligths accordingly
    */
   public void turnTransition() {
      state = TrafficLightState.TRANSITION;
      repaint();
   }

   /**
    * Changes state of traffic light to  FOLLOW and turns ligths accordingly
    */
  public void turnFollow() {
      state = TrafficLightState.FOLLOW;
      repaint();
   }

  /**
   * Gets the follow time code
   * @return a <code> int </code> 
   * specifying the follow time
   */
   public int getFollowTime() {
      return followTime;
   }

/**
   * Gets the transition time code
   * @return a <code> int </code> 
   * specifying the transition time
   */
   public int getTransitionTime() {
      return transitionTime;
   }

/**
   * Gets the state of the traffic ligth code
   * @return a <code> TrafficLigthState </code> 
   * specifying the current state of the traffic light
   */

   public TrafficLightState getState() {
      return state;
   }
   /** 
   Every method that changes an attribute that affects the
   object view must call repaint.
   This is an indirect and only way to call paintComponent 
   on this panel.
   */
   public void repaint() {
      panel.repaint();
   }

   /**
    * Sets JPanel to display
    * @param p is panel to display
    */
   public void setPanel(JPanel p){
      panel=p;
   }

   /**
    * Maps the state of traffic ligth to a signal in string format
    * @return a <code> string </code> representing
    * state of the traffic light
    */
   public String toString() {
      switch(state) { 
         case STOP: return "0";
         case TRANSITION: return "1";
         case FOLLOW: return "3";
         default: return "!!";
      }
   }
   protected JPanel panel;
   private int followTime;
   private int transitionTime;
   private TrafficLightState state;
}
