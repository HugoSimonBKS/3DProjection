import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JComponent;
public class InterfaceGraphique extends JComponent {
  private Image image;
  private Graphics2D grafikse;
  private int currentx, currenty, oldx, oldy;

  public InterfaceGraphique(){
    setDoubleBuffered(false);
  }

  protected void paintComponent(Graphics g){
    if(this.image == null){
      this.image = createImage(getSize().width, getSize().height);
      this.grafikse = (Graphics2D) this.image.getGraphics();
      this.grafikse.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      clear();
    }

    g.drawImage(this.image,0,0,null);
  }

  public void clear(){
    grafikse.setPaint(Color.white);
    grafikse.fillRect(0, 0, getSize().width, getSize().height);
    grafikse.setPaint(Color.black);
    repaint();
  }
}
