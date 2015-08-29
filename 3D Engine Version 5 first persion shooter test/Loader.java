import javax.imageio.*;import java.awt.image.*;import java.awt.event.*;
import java.awt.*;import javax.swing.*;

public class Loader extends JPanel implements MouseMotionListener,MouseListener
{

//****************************************************************************************
//Loader Varibles
//****************************************************************************************

//width and hight of window display
public static int gw=750,gh=750;

//calculate angle only once
boolean angle=false;
//the difrent top sides and other factors that only nead to be calcultated once
float CX=0,SX=0,CY=0,SY=0,SXSY=0,CXSY=0,CXCY=0,SXCY=0;
int OldX=0,OldY=0;

//player position
int px=0,py=0,pz=0;

//position of image
int xa=0,ya=0,za=0;

//array of 3D calculated output of images
//image number x position y position
Points[][][] Data=new Points[0][0][0];

//3D image array
Point3D[][][] Data2=new Point3D[0][0][0];

//full perspecitve of image number
float[] P;

//players point of view rotation
int azimuth=90,elevation=-90;int mx=0,my=0;

//the leval data file
Leval l=new Leval();

//used to get in and out distance
float Z=0;

//****************************************************************************************
//used to set up points affter 3d rotate
//****************************************************************************************

class Points{int x=0,y=0,r=0,g=0,b=0,t=0;public Points(){}
public Points(int x2,int y2,int r2,int g2,int b2,int t2)
{x=x2;y=y2;r=r2;g=g2;b=b2;t=t2;}}

//****************************************************************************************
//image stored in 3D points
//****************************************************************************************

class Point3D{int x,y,z,r,g,b,t;public Point3D(){}
public Point3D(int X,int Y,int Z,int R,int G,int B,int T)
{x=X;y=Y;z=Z;r=R;g=G;b=B;t=T;}}

//****************************************************************************************
//where the loading should be taking place
//****************************************************************************************

public Loader() 
{System.out.println("Loading Data");
this.setBackground(Color.black);
addMouseListener(this);addMouseMotionListener(this);
//how menay images to load as 3D
Data2=new Point3D[l.I.length][0][0];
//load images 3D
for(int i=0;i<l.I.length;i++)
{try{java.net.URL url=getClass().getResource(l.I[i]);BufferedImage a=ImageIO.read(url);
Set3DRotate(l.IPoint[i][3],l.IPoint[i][4]);
Data2[i]=LoadImage3D(a);
}catch(java.io.IOException e){}}
System.out.println("Loading Data Complete");}

//****************************************************************************************
//where one image is set up in 3D points from loader
//****************************************************************************************

public Point3D[][] LoadImage3D(BufferedImage a)
{int w=a.getWidth(),h=a.getHeight();
Point3D[][] out=new Point3D[h][0];
for(int y=0;y<=h-1;y++){out[y]=new Point3D[w];
for(int x=0;x<=w-1;x++){int c=a.getRGB(x,y);
int T=(c>>24)&0xff;

int red=(c&0x00ff0000)>>16;
int green=(c&0x0000ff00)>>8;int blue=c&0x000000ff;
int[] p=RPiont2(x,y,w,h);
//point calculated
out[y][x]=new Point3D(p[0],p[1],p[2],red,green,blue,T);

}}
return(out);}

//****************************************************************************************
//Load a window on os window
//****************************************************************************************

public static void main(String[]args)
{JFrame f=new JFrame("test");
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setSize(gw,gh);
f.setLocationRelativeTo(null);
f.setResizable(false);
f.add(new Loader());
f.setVisible(true);}

//****************************************************************************************
//where the graphics draw
//****************************************************************************************

public void paint(Graphics g)
{super.paint(g);

//calculate all 3D points
//what we are rotated at
Set3DRotate(azimuth,elevation);

Calculate();

//put perspective togather of wich are the most visable

int[] order=SortData(P);

int L=0;

//image number
for(int i=0;i<order.length;i++)
{
//draw them in correct order in perspective
L=order[i];

//height
for(int i2=0;i2<Data[L].length;i2++)
{
//width
for(int i3=0;i3<Data[L][i2].length;i3++)
{
//the 2d point
//get color
int red=Data[L][i2][i3].r,green=Data[L][i2][i3].g,blue=Data[L][i2][i3].b,T=Data[L][i2][i3].t;
//set color

g.setColor(new Color(red,green,blue,T));

int X2=0,Y2=0;

//pixel position

int X1=Data[L][i2][i3].x,Y1=Data[L][i2][i3].y;

//calculate pixle filling distance

if(Data[L][i2].length>i3+1){X2=Data[L][i2][i3+1].x;X2-=X1;}
if(Data[L].length>i2+1){Y2=Data[L][i2+1][i3].y;Y1-=Y2;}

/*if(L==0)
{
System.out.println(X1+","+Y1+","+X2+","+Y2+"");
}*/



if(Y2<0){Y2-=2;Y1+=1;}
if(Y2>0){Y2+=1;Y1-=1;}
if(Y2==0){Y2=1;}

if(X2<0){X2-=2;X1+=1;}
if(X2>0){X2+=1;X1-=1;}
if(X2==0){X2=1;}

//draw Point and fill it in
g.fillRect(X1,Y1,X2,Y2);

}
}

}

}

//****************************************************************************************
//this function sets up Data array and perspective array
//****************************************************************************************

public void Calculate()
{
//set up data to how meny images if already sized do not resize
if(Data.length!=Data2.length){Data=new Points[Data2.length][0][0];}

//perspective of image points
P=new float[Data2.length];float Z1=0;


//load in images and rotate 3D
for(int i=0;i<Data2.length;i++)
{//start perspective over
Z1=0;

//get image cordinets
xa=l.IPoint[i][0];
ya=l.IPoint[i][1];
za=l.IPoint[i][2];

//read one pixel at a time
int w=Data2[i][0].length,h=Data2[i].length;

//set up data to height of image if already interlized to this data do not reload
if(Data[i].length!=h){Data[i]=new Points[h][0];}

for(int y=0;y<=h-1;y++)
{
//add a new line of x to selected height in array if already sized do not reload
if(Data[i][y].length!=w){Data[i][y]=new Points[w];}

for(int x=0;x<=w-1;x++)
{

int[] p=RPiont(Data2[i][y][x].x,Data2[i][y][x].y,Data2[i][y][x].z,w,h);

//point calculated
Data[i][y][x]=new Points(p[0],p[1],Data2[i][y][x].r,Data2[i][y][x].g,Data2[i][y][x].b,Data2[i][y][x].t);

//add perspective of image points
Z1-=Z;}}

//record this perspective
P[i]=Z1;
}}

//****************************************************************************************
//Rotate points in 3D
//****************************************************************************************

public int[] RPiont(int x,int y,int z,int w,int h)
{x+=px+xa;y+=py+ya;z+=pz+za;
//Rotate as if 3D Piont
float X2=CX*x+SX*z;
float Y2=-SXSY*x+CY*y+CXSY*z;
//Perspective
Z=CXCY*z-SXCY*x-SY*y;
//if(w>h){Z=(Z/(w/2));}else{Z=(Z/(h/2));}
Z=Z/299;

X2*=20/((Z)+20+0);
Y2*=20/((Z)+20+0);
//Round output

//scale bigger
X2*=4;Y2*=4;

int NX=(int)(X2+(gw/2)+0.5),NY=(int)(Y2+(gh/2)+0.5);

return(new int[]{NX,NY});}

//****************************************************************************************
//convert image 2D points into 3D Points to it's set rotation
//****************************************************************************************

public int[] RPiont2(int x,int y,int w,int h)
{//convert plane to 2 halfs of left and right or top and bottom
float X=(x-(w/2)),Y=(y-(h/2));
//Rotate as 3D Piont
float X2=CX*X;
float Y2=-SXSY*X+CY*Y;
Z=SXCY*X-SY*Y;
//Round output
int NX=(int)(X2+0.5),NY=(int)(Y2+0.5),NZ=(int)(Z+0.5);
return(new int[]{NX,NY,NZ});}

//****************************************************************************************
//this function sets up the 3D Varibles used over and over on one rotation
//****************************************************************************************

public void Set3DRotate(int az,int el)
{float Rx=(float)(Math.PI*(az)/180.0);
float Ry=(float)(Math.PI*(el)/180.0);
//incresment and decresment over rotation
CX=(float)Math.cos(Rx);
SX=(float)Math.sin(Rx);
CY=(float)Math.cos(Ry);
SY=(float)Math.sin(Ry);
//factors of terning and top view
SXSY=SX*SY;CXSY=CX*SY;
//reversed factors of top and terning to increse lengths that are terning called perspective
CXCY=CX*CY;SXCY=SX*CY;}

//****************************************************************************************
//this function is used to fix perspective drawing and to increse loading spead
//****************************************************************************************

public int[] SortData(float[] Data)
{int[] Temp=new int[Data.length];
float b=0;int p=0;boolean R=true;
for(int j2=0;j2<Data.length;++j2)
{for(int j=0;j<Data.length;++j)
{if((Data[j]!=999999&Data[j]<b)||(Data[j]!=999999&R)){R=false;b=Data[j];p=j;}}
Temp[j2]=p;Data[p]=999999;R=true;}return(Temp);}

//****************************************************************************************
//handel events
//****************************************************************************************

public void mouseEntered(MouseEvent e){}
public void mouseExited(MouseEvent e){}
public void mouseClicked(MouseEvent e){}
public void mousePressed(MouseEvent e){mx=e.getX();my=e.getY();e.consume();}
public void mouseReleased(MouseEvent e){}
public void mouseMoved(MouseEvent e){}
public void mouseDragged(MouseEvent e)
{int new_mx=e.getX();int new_my=e.getY();
azimuth-=new_mx-mx;elevation-=new_my-my;
mx=new_mx;my=new_my;
if(elevation>90){elevation=90;}
if(elevation<-90){elevation=-90;}
if(azimuth>360){azimuth=0;}
if(azimuth<-360){azimuth=0;}
repaint();e.consume();}

}