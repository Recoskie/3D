//optimze sine function in javascript
//sine function that uses only integer
//operations that sizes a rotation point

function sin(size,angle){angle=Shift2(angle);
angle=ChopSize(size,angle);return(angle);}

function cos(size,angle){angle=Shift2(angle+90);
angle=ChopSize(size,angle);return(angle);}

function Shift(angle)
{angle%=360;
phase=Math.floor(angle/90);
angle=angle%90;
if(phase==1||phase==3)
{angle=90-angle;}
if(phase>=2)
{angle=-angle;}
return(angle);}

function Shift2(angle)
{angle%=360;
phase2=Math.floor(angle/180);
angle%=180;
phase=Math.floor(angle/90);
angle-=180*phase;
angle*=(-2*phase)+1;
angle*=(-2*phase2)+1;
return(angle);}

function ChopSize(s,a){over=Math.floor(s/90);err=s-(over*90);
out=over*a;out+=Math.floor((err*a)/90);return(out);}

function sincos(s,a1,a2)
{
s=sin(s,a1);
c=cos(s,a2);

o=s*c;
o=Math.floor(o/s);

return(o);
}

//test the rotation around a point set to -100 useing degrese rotation or angle

WScript.echo(Shift2(167824)+"");