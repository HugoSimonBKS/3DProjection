import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class TestMatrices{
  public static void main(String args[]){
    Matrices tests = new Matrices();
    tests.readVect();
    //a tester : fonction multiplication vect
    tests.affMat(tests.listevecteurs);
    JFrame frame = new JFrame("3D Vectors");
    frame.setSize(600, 600);
    frame.setVisible(true);
    Container content = frame.getContentPane();
    content.setLayout(new BorderLayout());
    final InterfaceGraphique ig = new InterfaceGraphique(tests);
    content.add(ig, BorderLayout.CENTER);
    while(true){
      try{
        Thread.sleep(1000/60);
        ig.clear();
      }
      catch(Exception e){
        System.out.println(e);
      }
    }
  }
}
