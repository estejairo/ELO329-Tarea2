import java.awt.Graphics2D;
import javax.swing.JPanel;
/**
  Any View must know how to paint itself.
  Any View must be displayed on a panel, so its panel
  must be set.
  To update a view, its panel must be repainted,
  this is achieved with repaint method.
*/
public interface View {
   void paint_view(Graphics2D g2d);
   void repaint();
   void setPanel(JPanel panel);
}
