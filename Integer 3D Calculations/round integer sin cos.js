//note a cercal of 180 degrease is equal to the verve of half a phase devided by the starting phase

function sin(size,angle){out=CercularCerve(size,angle);return(out);}

function cos(size,angle){out=CercularCerve(size,(angle+90));return(out);}

function CercularCerve(size,angle){angle%=360;
if(angle<180){out=ChopSize(size,Shift(angle+90))*ChopSize(size,Shift(angle+270));
out=Math.floor(out/size);out+=size;}
else{out=ChopSize(size,Shift(angle+90))*ChopSize(size,Shift(angle+90));
out=Math.floor(out/size);out-=size;}return(out);}

function Shift(angle){angle%=360;phase=Math.floor(angle/90);
angle=angle%90;if(phase==1||phase==3){angle=90-angle;}
if(phase>=2){angle=-angle;}return(angle);}

function ChopSize(s,a){over=Math.floor(s/90);err=s-(over*90);
out=over*a;out+=Math.floor((err*a)/90);return(out);}

WScript.echo(sin(100,45)+"");


