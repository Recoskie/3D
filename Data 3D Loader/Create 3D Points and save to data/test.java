import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class test extends JPanel implements MouseMotionListener,MouseListener
{

//Load My 3D Render
public static Load l=new Load();

//Load My 3D Shape Bilder
public static Render r=new Render();

Point3D[] Data=new Point3D[0];
Points[] p=new Points[0];

//mouse position varibles
int mx=0,my=0,azimuth=0,elevation=0;

public test()
{addMouseListener(this);addMouseMotionListener(this);}

public void Window()
{JFrame f=new JFrame("test");f.setSize(570,383);
f.add(new test());f.setLocationRelativeTo(null);
f.setResizable(false);f.setVisible(true);}

public static void main(String[]args)
{new test().Window();}

public void paint(Graphics g)
{
Data=r.Fillrect3D(-40,-40,-40,40,40,40);

l.Set3DAngle(azimuth,elevation);

p=l.GetPoints(Data);

String Layer=l.FaltenLayers(p);

p=l.GetPoints(Data);

g.setColor(Color.white);

g.fillRect(0,0,570,383);

System.out.println("catculation complete now drawing rect");

l.Draw(100,100,0,p,Layer,g);
}

public void mouseDragged(MouseEvent e)
{int new_mx=e.getX();int new_my=e.getY();
azimuth-=new_mx-mx;elevation+=new_my-my;
mx=new_mx;my=new_my;repaint();e.consume();}
public void mousePressed(MouseEvent e)
{mx=e.getX();my=e.getY();e.consume();}
public void mouseReleased(MouseEvent e){}
public void mouseMoved(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseEntered(MouseEvent e){}
public void mouseClicked(MouseEvent e){}

}