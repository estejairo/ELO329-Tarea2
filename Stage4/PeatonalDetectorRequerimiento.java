import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;

/**
 * This class puts the pedestrian buttons in the graphic interface
 */
public class PeatonalDetectorRequerimiento extends DetectorRequerimiento{

    /**
     * PeatonalDetectorRequerimientoconstructor
     * @param mypanel is a JPanel object to use
     */
    public PeatonalDetectorRequerimiento(JPanel myPanel){;
        this.panel = myPanel;
        offButton = new ImageIcon("OffButton.png");
        offButtonImage = offButton.getImage();
        onButton = new ImageIcon("OnButton.png");
        onButtonImage = onButton.getImage();

        boton = new JButton(offButton);
        panel.add(boton);        

        boton.addActionListener( new ActionListener (){
            public void actionPerformed(ActionEvent event) {
            setOn();              
            };
         });
        
    }

    /**
     * Changes the button appearance when it's pressed on
     * and the state
     */
    public void setOn(){
        state = true;
        offButton.setImage(onButtonImage);
        boton.repaint();
    }

    /**
     * Changes the button appearance when it's pressed off
     * and the state
     */
    public void setOff(){
        state = false;
        offButton.setImage(offButtonImage);
        boton.repaint();
    }
    ImageIcon offButton;
    ImageIcon onButton;
    Image offButtonImage;
    Image onButtonImage;
    JPanel panel;
    private JButton boton;
    private int origen_x=20;
    private int origen_y=50;

}
