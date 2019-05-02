import java.awt.Graphics2D;
import javax.swing.JPanel;
/**  
General class for a Traffic Light. 
It is the parent class for different traffic light views.
*/
public class TrafficLight {    // store the model, it needs a view in a subclass
   public TrafficLight (int ft, int tt){
      followTime = ft;
      transitionTime = tt;
      state = TrafficLightState.STOP;
   }
   public void turnStop() {
      state = TrafficLightState.STOP;
      repaint();
   }
   public void turnTransition() {
      state = TrafficLightState.TRANSITION;
      repaint();
   }
   public void turnFollow() {
      state = TrafficLightState.FOLLOW;
      repaint();
   }
   public int getFollowTime() {
      return followTime;
   }
   public int getTransitionTime() {
      return transitionTime;
   }
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
   public void setPanel(JPanel p){
      panel=p;
   }
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