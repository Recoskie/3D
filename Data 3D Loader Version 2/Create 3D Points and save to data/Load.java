import java.io.*;import java.awt.*;

//****************************************************************************************
//Position of points in 2D after 3D calculation and perspective of each point to stort
//wich ones draw in the right order
//****************************************************************************************

class Points{int x=0,y=0,r=0,g=0,b=0;float z=0;public Points(){}
public Points(int x2,int y2,float z2,int r2,int g2,int b2)
{x=x2;y=y2;z=z2;r=r2;g=g2;b=b2;}}

//****************************************************************************************
//image stored in 3D points
//****************************************************************************************

class Point3D{int x,y,z,r,g,b,t;public Point3D(){}
public Point3D(int X,int Y,int Z,int R,int G,int B)
{x=X;y=Y;z=Z;r=R;g=G;b=B;}}

//*******************************************************************************3d Load

public class Load
{

//****************************************************************************************
//Load Varibles
//****************************************************************************************

//the difrent top sides and other factors that only nead to be calcultated once
float CX=0,SX=0,CY=0,SY=0,SXSY=0,CXSY=0,CXCY=0,SXCY=0;
//width and height
int width=0,height=0;

//****************************************************************************************
//Load My 3D Format
//****************************************************************************************

public Point3D[] Load3DFormat(String file)
{
Point3D[] out=new Point3D[0];
try{
FileInputStream fis=new FileInputStream(file);
BufferedInputStream bis=new BufferedInputStream(fis);

out=new Point3D[GetInt(bis)];

//read Data From Binary File

int i=0;

while(bis.available()>0)
{
int x=GetInt(bis);
int y=GetInt(bis);
int z=GetInt(bis);
int r=GetColor(bis);
int g=GetColor(bis);
int b=GetColor(bis);

//point calculated
out[i]=new Point3D(x,y,z,r,g,b);i+=1;}
}catch(Exception e){}
return(out);}

//****************************************************************************************
//Save 3D points into my 3D file format
//****************************************************************************************

public void Save3DFormat(String n,Point3D[] p)
{
//file
try{BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(n));

bos.write(GetBytes(p.length));for(int i=0;i<p.length;i++)
{bos.write(GetBytes(p[i].x));bos.write(GetBytes(p[i].y));
bos.write(GetBytes(p[i].z));bos.write((GetBytes(p[i].r))[3]);
bos.write((GetBytes(p[i].g))[3]);bos.write((GetBytes(p[i].b))[3]);}

bos.flush();bos.close();

}catch(Exception e){}
}

//****************************************************************************************
//Transform 3D over 2d pionts
//****************************************************************************************

public Points[] GetPoints(Point3D[] p)
{
Points[] out=new Points[p.length];

for(int i=0;i<p.length;i++)
{//get all Data of point in 3D
float x=p[i].x,y=p[i].y,z=p[i].z;
int r=p[i].r,g=p[i].g,b=p[i].b;

float x2=x*CX+z*SX;
float y2=(-(x*SXSY))+(z*CXSY)+(y*CY);
float z2=(x*SXCY)-(y*SY)-(z*CXCY);
float z3=(float)(z2-Math.floor(z2));
x*=z3;y*=z3;

out[i]=new Points((int)(x2+0.9),(int)(y2+0.9),z2,r,g,b);}
return(out);}

//****************************************************************************************
//Set Up Tignomic Angles when points are rotated 3D
//****************************************************************************************

public void Set3DAngle(int az,int el)
{//x rotation
float r1=(float)(Math.PI*az/180.0);
//y rotation
float r2=(float)(Math.PI*el/180.0);
CX=(float)Math.cos(r1);SX=(float)Math.sin(r1);
CY=(float)Math.cos(r2);SY=(float)Math.sin(r2);
SXSY=(float)(SX*SY);CXSY=(float)(CX*SY);SXCY=(float)(SX*CY);CXCY=(float)(CX*CY);}

//****************************************************************************************
//convert to 2D Points with no layers
//****************************************************************************************

public String FaltenLayers(Points[] L)
{float zs=0,zs2=0;int x=0,y=0,x2=0,y2=0;
int element=0;String temp="";
for(int i=0;i<L.length;i++)
{element=-1;x=L[i].x;y=L[i].y;zs=L[i].z;

//System.out.println(zs+"");

if(x==(-0x80000000)&y==(-0x80000000)){}
else{element=i;for(int i2=0;i2<L.length;i2++)
{x2=L[i2].x;y2=L[i2].y;zs2=L[i2].z;
if(x==x2&y==y2){if(zs2<zs){zs=zs2;element=i2;}
L[i2].x=-0x80000000;L[i2].y=-0x80000000;}}}
if(element!=-1){temp+=element+",";}
}return(temp);}

//****************************************************************************************
//Draw 3D File With The Layer Used and draw to graphics conainer with x y z position
//****************************************************************************************

public void Draw(int x,int y,int z,Points[] p,String Layer,Graphics g)
{
String[] draw=Layer.split(",");
int x2=0,y2=0;
int rc=0,gc=0,bc=0;
int e=0;

for(int i=0;i<draw.length;i++)
{
e=Integer.parseInt(draw[i]);
x2=p[e].x;y2=p[e].y;
rc=p[e].r;gc=p[e].g;bc=p[e].b;

g.setColor(new Color(rc,gc,bc));
g.fillRect((x2+x),(y2+y),1,1);
}
}

//************************************************create new 3D point for shape rendering

public Point3D[] CreatePoint(Point3D[] p,Point3D p2)
{Point3D[] out=new Point3D[(p.length+1)];
for(int i=0;i<p.length;out[i]=p[i],i++);
out[p.length]=p2;return(out);}

//*****************************************************************load integer from file

private int GetInt(BufferedInputStream i)
{String temp="",temp2="";boolean sing=false,sing2=false,c=false;int v=0;
for(int i2=0;i2<4;i2++){try{v=i.read()&0x000000FF;}catch(Exception e){}
if(v>=128&!sing){v=127-(v-128);sing=true;sing2=true;}
else if(sing2){v=255-v;v=v&0x000000FF;};sing=true;
temp2=Integer.toHexString(v);
if((temp2.length()/2.0)!=(temp2.length()/2)){temp2="0"+temp2;}
if(sing2&!c){c=true;temp2="-"+temp2;};temp+=temp2;}
return(Integer.parseInt(temp,16));}

//*******************************************************************load color from file

private int GetColor(BufferedInputStream i)
{int c=0;try{c=(i.read()&0x000000FF);}
catch(Exception e){}return(c);}

//**************************************************************************int to bytes

private byte[] GetBytes(int v){return((new byte[]{(byte)(v>>24),(byte)(v>>16),(byte)(v>>8),(byte)v}));}

}