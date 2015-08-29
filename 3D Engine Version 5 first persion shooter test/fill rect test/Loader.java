import javax.imageio.*;
import java.awt.image.*;
import javax.swing.event.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


class S extends JPanel implements MouseMotionListener
{
int sx=60,sy=70;
int fx=40,fy=50;

public S(){addMouseMotionListener(this);}
public static void main(String[]args)
{new S().window();}

public void paint(Graphics g)
{//time to do this with pixels

g.setColor(Color.white);
g.fillRect(0,0,300,230);

g.setColor(Color.blue);
g.fillRect(fx,fy,-10,-10);
g.setColor(Color.red);


//fill in distance

int fx2=fx-sx,fy2=fy-sy;

if(fy2<0){fy2-=2;sy+=1;}
     else{fy2-=1;sy+=1;}

if(fx2<0){fx2-=2;sx+=1;}
     else{fx2-=1;sx+=1;}

g.fillRect(sx,sy,fx2,fy2);}

//run test code
public void window()
{JFrame f=new JFrame("test");
f.setUndecorated(true);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(300,230);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new S());
f.setVisible(true);}

//event handeler
public void mouseReleased(MouseEvent e){}

public void mouseMoved(MouseEvent e){}

public void mouseDragged(MouseEvent e)
{sx=e.getX();sy=e.getY();repaint();}

}