import java.io.*;

class Points{int x=0,y=0,r=0,g=0,b=0;float z=0;
public Points(int x2,int y2,float z2,int r2,int g2,int b2)
{x=x2;y=y2;z=z2;r=r2;g=g2;b=b2;}}

class Point3D{int x,y,z,r,g,b,t;
public Point3D(int X,int Y,int Z,int R,int G,int B)
{x=X;y=Y;z=Z;r=R;g=G;b=B;}}

class Point2D{int x,y,r,g,b,t;
public Point2D(int X,int Y,int R,int G,int B)
{x=X;y=Y;r=R;g=G;b=B;}}

public class test
{

public test()
{Points[] p=new Points[10];

p[0]=new Points(0,0,(float)0.7,0,0,0);
p[1]=new Points(1,0,(float)0.7,0,0,0);
p[2]=new Points(2,0,(float)0.7,0,0,0);
p[3]=new Points(3,0,(float)0.4,0,0,0);
p[4]=new Points(4,0,(float)0.9,0,0,0);

p[5]=new Points(5,1,(float)0.9,0,0,0);
p[6]=new Points(6,1,(float)0.7,0,0,0);
p[7]=new Points(6,1,(float)0.3,0,0,0);
p[8]=new Points(6,1,(float)0.4,0,0,0);
p[9]=new Points(9,1,(float)0.9,0,0,0);

String d=KillLayers(p);
System.out.println(d);}

public static void main(String[]args)
{new test();}

public String KillLayers(Points[] L)
{float zs=0,zs2=0;int x=0,y=0,x2=0,y2=0;
int element=0;String temp="";
for(int i=0;i<L.length;i++)
{element=-1;x=L[i].x;y=L[i].y;zs=L[i].z;
if(x==(-0x80000000)&y==(-0x80000000)){}
else{element=i;for(int i2=0;i2<L.length;i2++)
{x2=L[i2].x;y2=L[i2].y;zs2=L[i2].z;
if(x==x2&y==y2){if(zs2<zs){zs=zs2;element=i2;}
L[i2].x=-0x80000000;L[i2].y=-0x80000000;}}}
if(element!=-1){temp+=element+".";}}return(temp);}

}