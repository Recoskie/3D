import javax.imageio.*;import java.awt.image.*;import java.awt.event.*;
import java.awt.*;import javax.swing.*;

//this version uses images on vertice pionts

public class Loader extends JPanel implements MouseMotionListener,MouseListener
{
//where images load in form loading file Teckneack perload into ram incresse spead
BufferedImage[] a;

//players piont of view rotation
int azimuth=0,elevation=0;int mx=0,my=0;

//the leval data file
Leval l=new Leval();

//load Pictures
public Loader() 
{this.setBackground(Color.blue);
addMouseListener(this);addMouseMotionListener(this);
a=new BufferedImage[l.I.length];
for(int i=0;i<l.I.length;i++)
{
try{java.net.URL url=getClass().getResource(l.I[i]);
a[i]=ImageIO.read(url);}catch(java.io.IOException e){}
}
}

//window
public static void main(String[]args)
{JFrame f=new JFrame("test");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(300,230);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new Loader());
f.setVisible(true);}

public void paint(Graphics g)
{super.paint(g);

//read image data and 3D Piont from loop

for(int i=0;i<a.length;i++)
{
//get image cordinets and set rotation
int xa=l.IPoint[i][0];
int ya=l.IPoint[i][1];
int za=l.IPoint[i][2];
int rxa=l.IPoint[i][3];
int rya=l.IPoint[i][4];


//read pictuer one pixel at a time
int w=a[i].getWidth(),h=a[i].getHeight();

//used for pixel filler and where to draw pixles
int[] p=new int[]{0,0},p2=new int[]{0,0},p3=new int[]{0,0};

for(int y=0;y<=h-1;y++){for(int x=0;x<=w-1;x++)
{int c=a[i].getRGB(x,y);int T=(c>>24)&0xff;int red=(c&0x00ff0000)>>16;
int green=(c&0x0000ff00)>>8;int blue=c&0x000000ff;
g.setColor(new Color(red,green,blue,T));

p=RPiont(x,y,xa,ya,za,azimuth+rxa,elevation+rya,w,h);
if(x!=w){p2=RPiont((x+1),y,xa,ya,za,azimuth+rxa,elevation+rya,w,h);}
if(y!=h){p3=RPiont(x,(y+1),xa,ya,za,azimuth+rxa,elevation+rya,w,h);}

int TX=0,TY=0;

//pixel filling distance

if(y!=h){if(p[1]>p3[1]){TY=p[1]-p3[1];}else{TY=p3[1]-p[1];}}else{TY=y;}
if(x!=w){if(p[0]>p2[0]){TX=p[0]-p2[0];}else{TX=p2[0]-p[0];}}else{TX=x;}

g.fillRect(p[0],p[1],TX+1,TY+1);
}}

}
//end of looping though images

}

//x y pixle point cordindent x y z then az and el rotation width height of image

public int[] RPiont(int x,int y,int cx,int cy,int cz,int az,int el,int w,int h)
{

double Rx=Math.PI*(az)/180.0;
double Ry=Math.PI*(el)/180.0;

double CX=Math.cos(Rx);
double SX=Math.sin(Rx);
double CY=Math.cos(Ry);
double SY=Math.sin(Ry);

//convert plane to 2 halfs of left and right or top and bottom
double X=x-(w/2),Y=y-(h/2);

//Rotate as if 3D Piont
double X2=CX*(X+cx)+SX*cz;
double Y2=-(SX*SY)*(X+cx)+CY*(Y+cy)+(CX*SY)*cz;

//Perspective
double Z=(CX*CY)*cz-(SX*CY)*(X+cx)-SY*(Y+cy);
if(w>h){Z=Z/(w/2);}else{Z=Z/(h/2);}
X2*=10/(Z+10+0);
Y2*=10/(Z+10+0);

//Round output
int NewX=(int)(X2+(300/2)+0.5),NewY=(int)(Y2+(230/2)+0.5);

return(new int[]{NewX,NewY});}

public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseClicked(MouseEvent e){}

public void mousePressed(MouseEvent e){mx=e.getX();my=e.getY();e.consume();}

public void mouseReleased(MouseEvent e){}
public void mouseMoved(MouseEvent e){}

public void mouseDragged(MouseEvent e)
{int new_mx=e.getX();int new_my=e.getY();
azimuth+=new_mx-mx;elevation+=new_my-my;
mx=new_mx;my=new_my;

if(elevation>360){elevation=0;}
if(elevation<-360){elevation=0;}

if(azimuth>360){azimuth=0;}
if(azimuth<-360){azimuth=0;}

repaint();
e.consume();}

}