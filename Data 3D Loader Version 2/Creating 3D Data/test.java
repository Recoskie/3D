import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class test extends JPanel implements MouseMotionListener,MouseListener
{

//My 3D Tool
public static Loader l=new Loader();
//story 3D Point Data from file in ram
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
{Data=l.Load3DFormat("test2");
l.Set3DAngle(azimuth,elevation);
p=l.GetPoints(Data);
String Layer=l.KillLayers(p);
p=l.GetPoints(Data);
g.setColor(Color.white);
g.fillRect(0,0,570,383);
l.Draw(100,100,0,p,Layer,g);}

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