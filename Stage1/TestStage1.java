/**
   @version 1.00 2019-04-18
   @author Agustín J. González
*/

import java.awt.*;
import javax.swing.*;
import java.util.*;

public class TestStage1 {  
   public static void main(String[] args) {
//      MyOwnPanel myPanel = new MyOwnPanel();
      final MyOwnPanel myPanel = new MyOwnPanel();  // aragorn java compiler requires final for myPanel
      CrosswalkTrafficLight mattaCrosswalk = new CrosswalkTrafficLight(6,4,525,475);
      myPanel.addView(mattaCrosswalk);
      StreetTrafficLight mattaTrafficLight = new StreetTrafficLight(5,3,150,225);
      myPanel.addView(mattaTrafficLight);
      StreetTrafficLight PlaceresTrafficLight = new StreetTrafficLight(8,3,400,300);
      myPanel.addView(PlaceresTrafficLight);      
      Controller controller = new Controller(mattaTrafficLight, mattaCrosswalk, PlaceresTrafficLight);
            
      SwingUtilities.invokeLater(new Runnable() { // implementación Swing recomendada
         public void run() {
            MyOwnFrame frame = new MyOwnFrame(myPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         } // run ends
      });
      
      controller.manageTraffic();
   }
}

/**
   A frame with a panel for a simple graphics object
*/
class MyOwnFrame extends JFrame  {
   public MyOwnFrame(JPanel panel) {
      setTitle("Two Traffic Lights Example");
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      // add panel to frame
      getContentPane().add(panel);
   }

   public static final int DEFAULT_WIDTH = 800;
   public static final int DEFAULT_HEIGHT = 640;  
}

/**
   A panel for a simple graphics object.
*/
class MyOwnPanel extends JPanel  { 
   public MyOwnPanel() {  
      views = new ArrayList<View> ();
      setFocusable(true);
   }
   /** 
   For each call, the panel sets itself within
   the view being added to the panel.
   */
   public void addView(View v) {  
      views.add(v);
      v.setPanel(this);
   }
   public void paintComponent(Graphics g)   {  
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g; // For historical reasons, this method
      for (View v: views){     // has Graphics as parameter, but now an intance
         v.paint_view(g2);   // of a subclass of Graphics, instance of Graphics2D,
      }                      // is passed as argument.
      //calle mata abajo
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(5));
      g2.drawLine(0, 400, 200, 400);
      //calle mata arriba
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(5));
      g2.drawLine(0, 200, 200, 200);

      //linea placeres derecha
      g2.setStroke(new BasicStroke(5));
      g2.setColor(Color.BLACK);
      g2.drawLine(500, 50, 500, 600);

      //linea placeres izq arriba
      g2.setStroke(new BasicStroke(5));
      g2.setColor(Color.BLACK);
      g2.drawLine(200, 50, 200, 200);
      //linea placeres izq abajo
      g2.setColor(Color.BLACK);
      g2.setStroke(new BasicStroke(5));
      g2.drawLine(200, 400, 200, 600);//x1,y1,x2,y2
      for (int i = 0; i<5;i++){
         g2.setColor(Color.BLACK);
         g2.setStroke(new BasicStroke(3));
         g2.drawLine(250+50*i, 475, 250+50*i, 575);
      }

   }

   private ArrayList<View> views;
}
