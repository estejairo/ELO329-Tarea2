import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;

/**
 * This class puts the button correspoonding to the inductive sensor in the graphic interface
 */
public class GiroDetectorRequerimiento extends DetectorRequerimiento{

    /**
     * GiroDetectorRequerimiento constructor
     * @param myPanel is the JPanel object to use
     */
    public GiroDetectorRequerimiento(JPanel myPanel){;
        this.panel = myPanel;
        offInductor = new ImageIcon("OffInductor.png");
        offInductorImage = offInductor.getImage();
        onInductor = new ImageIcon("OnInductor.png");
        onInductorImage = onInductor.getImage();

        boton = new JButton(offInductor);
        panel.add(boton);        

        boton.addActionListener( new ActionListener (){
            public void actionPerformed(ActionEvent event) {
            setOn();              
            };
         });
        
    }
	
    /**
     * Changes the button appearance when it's pressen on
     * and also the state
     */
    public void setOn(){
        state = true;
        offInductor.setImage(onInductorImage);
        boton.repaint();
    }

    /**
     * Changes the button appearance when it is pressed off
     * and also the state
     */
    public void setOff(){
        state = false;
        offInductor.setImage(offInductorImage);
        boton.repaint();
    }
    ImageIcon offInductor;
    ImageIcon onInductor;
    Image offInductorImage;
    Image onInductorImage;
    JPanel panel;
    private JButton boton;
    private int origen_x=20;
    private int origen_y=50;

}
