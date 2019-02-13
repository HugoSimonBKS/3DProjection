import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.JComponent;
import java.awt.geom.Line2D;
import java.awt.geom.Ellipse2D;
public class InterfaceGraphique extends JComponent {
  private Image image;
  private Graphics2D grafikse;
  private Matrices mat;
  private double angle = 0.0;
  public InterfaceGraphique(Matrices m){
    this.mat = m;
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
    for(int i = 0; i < 8; i++){
      System.out.println("point x du vecteur " + i + " : " + mat.getPointX(i) + " point y : "  + (mat.getPointy(i)));
      Ellipse2D.Double circle = new Ellipse2D.Double((mat.getPointX(i)+250.0),(mat.getPointy(i)+250.0),5.0,5.0);
      grafikse.fill(circle);
      repaint();
    }
    this.mat.readVect();
    this.angle += 0.01;
    System.out.println("angle : " + this.angle);
    this.mat.Rotx(this.angle);
    this.mat.Roty(this.angle);
    this.mat.Rotz(this.angle);
  }
}
