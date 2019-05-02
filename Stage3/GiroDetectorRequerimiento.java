import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.JPanel;

public class GiroDetectorRequerimiento extends DetectorRequerimiento{
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

    public void setOn(){
        state = true;
        offInductor.setImage(onInductorImage);
        boton.repaint();
    }
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