import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class app extends JPanel implements MouseMotionListener,MouseListener
{
//window width and height
public static int width=700,height=700;

//computer mouse possition
int mx=0,my=0,px=0,py=0;

//3D points
int P[][]=new int[][]{{-100,100,-100},{100,100,-100},{-100,-100,-100},{100,-100,-100},
{-100,100,100},{100,100,100},{-100,-100,100},{100,-100,100}};

//wich 3D points to connect togather with draw line function
int L[][]=new int[][]
{
{0,1},{2,3},{0,2},{1,3},
{4,5},{6,7},{4,6},{7,5},
{0,4},{1,5},{2,6},{3,7}
};

//tempory storage of calculated 2d points
Point out[]=new Point[8];

public app(){addMouseListener(this);addMouseMotionListener(this);}

public static void main(String[]args)
{JFrame frame=new JFrame("");
frame.setSize(width,height);
frame.setLocationRelativeTo(null);
frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
frame.add(new app());frame.show();}

//change the positions of x y and z on 2d serface
//useing my integer rotate functions

public int[] P3D(int x,int y,int z)
{int x2=cos(x,mx)+sin(z,mx);

int y2=(cos(y,my)+sinsin(x,my,mx))-sincos(z,my,mx);

//add perspective

//int z2=coscos(z,mx,my)-sincos(x,mx,my)-sin(y,my);

//how is it posable to add prespective if i can not fractoinly do it ?

//first calculate a small perspective to add or subtract to lengths

/*try{//System.out.println("Z="+z2+" X="+x2+" y="+y2+" ZX="+((z2*x2)/100)+" ZY="+((z2*y2)/100)+" OX="+(x2+((z2*x2)/100))+" OY="+(y2+((z2*x2)/100))+"");

if(z2<0){if(y2<0){y2+=z2;}else{y2-=z2;}
if(x2<0){x2+=z2;}else{x2-=z2;}}
else{if(y2<0){y2-=z2;}else{y2+=z2;}
if(x2<0){x2-=z2;}else{x2+=z2;}}
}catch(Exception e){}*/

//chop the size based on wich drection



return(new int[]{(x2+(width/2)),(y2+(height/2))});}

public void paint(Graphics g)
{super.paint(g);
//System.out.println("");System.out.println("--------------------Calculating Points--------------------");System.out.println("");
g.setColor(Color.black);
g.fillRect(0,0,width,height);
for(int i=0;i<P.length;i++)
{int[] o=P3D(P[i][0],P[i][1],P[i][2]);
out[i]=new Point(o[0],o[1]);}
g.setColor(Color.white);
for(int i=0;i<L.length;i++)
{g.drawLine(out[L[i][0]].x,out[L[i][0]].y,out[L[i][1]].x,out[L[i][1]].y);}}

//my rotation functions integer angles not float point cerved type
//complete spead integer solution by damian recoskie

private int coscos(int size,int angle1,int angle2){try{angle1=CercularCerve(size,angle1+90);
angle2=CercularCerve(size,angle2+90);return(-((angle1*angle2)/size));}catch(Exception e){return(0);}}

private int sinsin(int size,int angle1,int angle2){try{angle1=CercularCerve(size,angle1);
angle2=CercularCerve(size,angle2);return(-((angle1*angle2)/size));}catch(Exception e){return(0);}}

private int sincos(int size,int angle1,int angle2){try{angle1=CercularCerve(size,angle1);
angle2=CercularCerve(size,(angle2+90));return(-((angle1*angle2)/size));}catch(Exception e){return(0);}}

private int sin(int size,int angle){angle=CercularCerve(size,angle);return(angle);}

private int cos(int size,int angle){angle=CercularCerve(size,(angle+90));return(angle);}

private int CercularCerve(int size,int angle){angle%=360;int out=0;
try{if(angle<180){out=ChopSize(size,Shift(angle+90))*ChopSize(size,Shift(angle+270));
out=out/size;out+=size;}else{out=ChopSize(size,Shift(angle+90))*ChopSize(size,Shift(angle+90));
out=out/size;out-=size;}}catch(Exception e){return(0);}return(-out);}

private int Shift(int angle){if(angle<0)
{angle%=360;angle=360+angle;}else{angle%=360;}
int phase=angle/90;angle=angle%90;
if(phase==1||phase==3){angle=90-angle;}
if(phase>=2){angle=-angle;}return(angle);}

private int ChopSize(int s,int a){return((s*a)/90);}

//Mouse event handelers

public void mouseEntered(MouseEvent e){e.consume();}
public void mouseExited(MouseEvent e){e.consume();}
public void mouseClicked(MouseEvent e){e.consume();}
public void mousePressed(MouseEvent e){px=e.getX();py=e.getY();e.consume();}
public void mouseReleased(MouseEvent e){e.consume();}
public void mouseMoved(MouseEvent e){e.consume();}
public void mouseDragged(MouseEvent e){int nx=e.getX(),ny=e.getY();
mx-=nx-px;my-=ny-py;px=nx;py=ny;repaint();e.consume();}
}