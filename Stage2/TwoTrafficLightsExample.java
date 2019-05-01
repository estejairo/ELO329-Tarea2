/**
   @version 1.00 2019-04-18
   @author Agustín J. González
*/

import java.awt.*;
import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.*;


public class TwoTrafficLightsExample{ 
   public static DetectorRequerimiento boton_plac;
   public static void main(String[] args) {
      final MyOwnPanel myPanel = new MyOwnPanel();  // aragorn java compiler requires final for myPanel
      final JPanel ButtonPanel = new JPanel();
      ButtonPanel.setLayout(new BoxLayout(ButtonPanel,BoxLayout.X_AXIS));
 
      CrosswalkTrafficLight mattaCrosswalk = new CrosswalkTrafficLight(6,4,525,475);//ft,tt,or_x,or_y
      myPanel.addView(mattaCrosswalk);
      StreetTrafficLight mattaTrafficLight = new StreetTrafficLight(5,3,150,225);
      myPanel.addView(mattaTrafficLight);
      StreetTrafficLight PlaceresTrafficLight = new StreetTrafficLight(8,3,400,300);
      myPanel.addView(PlaceresTrafficLight);   

      boton_plac= new PeatonalDetectorRequerimiento(ButtonPanel);

      Controller controller = new Controller(mattaTrafficLight, mattaCrosswalk, PlaceresTrafficLight,boton_plac);
            
      SwingUtilities.invokeLater(new Runnable() { // implementación Swing recomendada
         public void run() {
            /*
            final ImageIcon offButton = new ImageIcon("OffButton.png");
            final Image offButtonImage = offButton.getImage();
            final ImageIcon onButton = new ImageIcon("OnButton.png");
            final Image onButtonImage = onButton.getImage();
            JButton boton_placeres = new JButton(offButton);
            */
            MyOwnFrame frame = new MyOwnFrame(myPanel);
            frame.add(ButtonPanel,BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            /*
            frame.add(boton_placeres,BorderLayout.SOUTH); 
            boton_placeres.addActionListener(new ActionListener(){
               public void actionPerformed(ActionEvent e){
                  boton_plac.setOn();
                  offButton.setImage(onButtonImage);
               }
            });*/

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
      setResizable(false);
      
      // add panel to frame
      getContentPane().add(panel,BorderLayout.CENTER);
   }

   public static final int DEFAULT_WIDTH = 800;
   public static final int DEFAULT_HEIGHT = 840;  
}

/**
   A panel for a simple graphics object.
*/
class MyOwnPanel extends JPanel{ 
   public MyOwnPanel() { 
      views = new ArrayList<View> ();
      //setFocusable(true);
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
      revalidate();

   }
   
   private ArrayList<View> views;
}
