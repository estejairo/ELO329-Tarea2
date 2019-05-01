import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;

public class PeatonalDetectorRequerimiento extends DetectorRequerimiento{
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

    public void setOn(){
        state = true;
        offButton.setImage(onButtonImage);
    }
    public void setOff(){
        state = false;
        offButton.setImage(offButtonImage);
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