public class Render
{

//My 3D Tool
public static Load l=new Load();

//this function sets the 3d sin cosine of angels to draw 3D rectangel
public Point3D[] Fillrect3D(int x,int y,int z,int x2,int y2,int z2)
{
Point3D[] out=new Point3D[0];Point3D o=new Point3D();

//flip them so thay can stil be drawen corectly
if(x>x2){int t=x;x=x2;x2=t;}
if(y>y2){int t=y;x=y2;y2=t;}
if(z>z2){int t=z;z=z2;z2=t;}

//top and bottom

for(int n=0;n<2;n++)
{for(int y3=y;y3<y2;y3++){for(int x3=x;x3<x2;x3++)
{if(n==0){o=new Point3D(x3,y3,z,255,0,0);}
else{o=new Point3D(x3,y3,z2,0,255,0);}
out=l.CreatePoint(out,o);}}}

//left and right

for(int n=0;n<2;n++)
{for(int z3=z;z3<z2;z3++){for(int y3=y;y3<y2;y3++)
{if(n==0){o=new Point3D(x,y3,z3,0,0,255);}
else{o=new Point3D(x2,y3,z3,255,255,0);}
out=l.CreatePoint(out,o);}}}

//90 degrea top and botom

for(int n=0;n<2;n++)
{for(int z3=z;z3<z2;z3++){for(int x3=x;x3<x2;x3++)
{if(n==0){o=new Point3D(x3,y,z3,255,0,255);}
else{o=new Point3D(x3,y2,z3,0,255,255);}
out=l.CreatePoint(out,o);}}}

return(out);}

}