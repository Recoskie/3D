import javax.imageio.*;import java.awt.image.*;import java.awt.event.*;
import java.awt.*;import javax.swing.*;

//this is the improved version

public class Rotation extends JPanel implements MouseMotionListener,MouseListener
{
BufferedImage a;

//window draw area size
public static int gw=700,gh=700;

//point 1
int px0=1,py0=1;
//point 2
int px1=gw-10,py1=1;
//point 3
int px2=1,py2=gh-40;
//point 4
int px3=gw-10,py3=gh-40;

//when point clicked or relesed
boolean p0=false,p1=false,p2=false,p3=false;

//load Picture
public Rotation() 
{this.setBackground(Color.gray);
addMouseListener(this);addMouseMotionListener(this);
try{java.net.URL url=getClass().getResource("test.bmp");
a=ImageIO.read(url);}catch(java.io.IOException e){}}

//window
public static void main(String[]args)
{JFrame f=new JFrame("test");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(gw,gh);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new Rotation());
f.setVisible(true);}

//Rotate Alagrithm
public void paint(Graphics g)
{super.paint(g);


DrawCorners((int)px0,(int)py0,(int)px1,(int)py1,(int)px2,(int)py2,(int)px3,(int)py3,g);}


public void DrawCorners(int x0,int y0,int x1,int y1,int x2,int y2,int x3,int y3,Graphics g)
{
//0 top left
//1 top right
//2 botom left
//3 botom right

//fill distance factors

float LY=1,RY=1,LX=1,RX=1;

//scale factor of pixle selection

float inc=0;

//the bigest point in height

int MaxY=Math.max(y0,y1);
MaxY=Math.max(MaxY,y2);
MaxY=Math.max(MaxY,y3);

try{inc=(float)a.getHeight()/Math.abs(MaxY);}catch(Exception e){inc=1;}
float yo1=y0,yo2=y1,xo1=x0,xo2=x1,L=0;

//fill y distance

LY=(y2-y0);LY=(float)(LY/MaxY);RY=(y3-y1);RY=(float)(RY/MaxY);

if(Float.isInfinite(LY)){LY=0;}
if(Float.isInfinite(RY)){RY=0;}

//fill x distance
LX=(float)(x2-x0)/MaxY;RX=(float)(x3-x1)/MaxY;

if(Float.isInfinite(LX)){LX=0;}
if(Float.isInfinite(RX)){RX=0;}

MaxY=Math.abs(MaxY);

for(int y=0;y<MaxY;y++)
{

DrawLine((int)xo1,(int)yo1,(int)xo2,(int)yo2,(int)L,g);

xo1+=LX;
xo2+=RX;
yo1+=LY;
yo2+=RY;

L+=inc;
}

//after image is drawen draw where the 4 points are being compeared at to there value when thay are moved to mouse position agen
g.setColor(Color.black);
//point 1
g.fillRect(px0-10,py0-10,20,20);
//point 2
g.fillRect(px1-10,py1-10,20,20);
//point 3
g.fillRect(px2-10,py2-10,20,20);
//point 4
g.fillRect(px3-10,py3-10,20,20);

}

public void DrawLine(int x0,int y0,int x1,int y1,int L,Graphics g)
{
//how much less color to use or more if shorter or longer to size of data of color in x of image size
float CS;
try{

if(Math.abs(x0-x1)>Math.abs(y0-y1))
{CS=(float)a.getWidth()/Math.abs(x0-x1);}
else{CS=(float)a.getHeight()/Math.abs(y0-y1);}

}catch(Exception e){CS=0;}
float Increse=0;

boolean steep=Math.abs(y1-y0)>Math.abs(x1-x0);
if(steep){int t=x0;x0=y0;y0=t;t=x1;x1=y1;y1=t;}
if(x0>x1){int t=x0;x0=x1;x1=t;t=y0;y0=y1;y1=t;}
int deltax=x1-x0,deltay=Math.abs(y1-y0);float error;
try{error=deltax/2;}catch(Exception e){error=0;}
int ystep=0,y=y0;
if(y0<y1){ystep=1;}else{ystep=-1;}
int x;for(x=x0;x<=x1;x++)
{
try{int c=a.getRGB((int)Increse,L);int T=(c>>24)&0xff;int red=(c&0x00ff0000)>>16;
int green=(c&0x0000ff00)>>8;int blue=c&0x000000ff;Increse+=CS;
g.setColor(new Color(red,green,blue,T));}catch(Exception e){}

if(steep){g.fillRect(y,x,1,1);}else{g.fillRect(x,y,1,1);}
error-=deltay;if(error<0){y+=ystep;error=error+deltax;}}

}

public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseClicked(MouseEvent e){}

public void mousePressed(MouseEvent e)
{int x=e.getX();int y=e.getY();

//compear and move point mouse is pressed

//point 1
if((x<(px0+10)&&x>(px0-10))&&(y<(py0+10)&&y>(py0-10))){p0=true;}

//point 2
if((x<(px1+10)&&x>(px1-10))&&(y<(py1+10)&&y>(py1-10))){p1=true;}

//point 3
if((x<(px2+10)&&x>(px2-10))&&(y<(py2+10)&&y>(py2-10))){p2=true;}

//point 4
if((x<(px3+10)&&x>(px3-10))&&(y<(py3+10)&&y>(py3-10))){p3=true;}

e.consume();}

public void mouseReleased(MouseEvent e)
{
//reset all points booleans when mouse is no longer focused on point
p0=false;p1=false;p2=false;p3=false;
e.consume();
}

public void mouseMoved(MouseEvent e){}

public void mouseDragged(MouseEvent e)
{int x=e.getX();int y=e.getY();

//compear and move point mouse is moving

//move point 1
if(p0){px0=x;py0=y;repaint();}

//move point 2
if(p1){px1=x;py1=y;repaint();}

//move point 3
if(p2){px2=x;py2=y;repaint();}

//move point 4
if(p3){px3=x;py3=y;repaint();}


e.consume();}

}