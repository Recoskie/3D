import javax.swing.*;import java.awt.*;import javax.swing.event.*;import java.awt.event.*;

public class whave extends JPanel implements ActionListener
{

//varibles
int S=0,C=0,A=20;

Boolean State1=true,State2=false,State3=false,State4=true;

Boolean sub1=false,mul1=true,add1=false;
//end of varibles

//jpanel 1
JLabel Sin=new JLabel("Sin "+S+"");
JLabel Cos=new JLabel("Cos "+C+"");
JLabel Amp=new JLabel("Amp "+A+"");

JSlider sin=new JSlider(JSlider.HORIZONTAL,0,360,0);
JSlider cos=new JSlider(JSlider.HORIZONTAL,0,360,0);
JSlider amp=new JSlider(JSlider.HORIZONTAL,0,50,20);

JRadioButton sino1;JRadioButton coso1;
JRadioButton sino2;JRadioButton coso2;

JRadioButton mul;JRadioButton add;JRadioButton sub;
//end jpanel 1

public whave()
{ButtonGroup g1=new ButtonGroup();

sino1=new JRadioButton("Sin",true);
coso1=new JRadioButton("Cos",false);

g1.add(sino1);g1.add(coso1);

ButtonGroup g2=new ButtonGroup();

sino2=new JRadioButton("Sin",false);
coso2=new JRadioButton("Cos",true);

g2.add(sino2);g2.add(coso2);

ButtonGroup g3=new ButtonGroup();

sino1.addActionListener(this);coso1.addActionListener(this);
sino2.addActionListener(this);coso2.addActionListener(this);

sino1.setActionCommand("S1");coso1.setActionCommand("C1");
sino2.setActionCommand("S2");coso2.setActionCommand("C2");

mul=new JRadioButton("*",true);
add=new JRadioButton("+",false);
sub=new JRadioButton("-",false);

mul.addActionListener(this);
add.addActionListener(this);
sub.addActionListener(this);
mul.setActionCommand("M");
add.setActionCommand("A");
sub.setActionCommand("S");

g3.add(mul);g3.add(add);g3.add(sub);

JPanel p1=new JPanel();JPanel p2=new JPanel();JPanel p3=new JPanel();
JPanel p4=new JPanel();JPanel p5=new JPanel();JPanel p6=new JPanel();

p1.add(sin);p1.add(Sin);sin.setBackground(Color.black);
p2.add(cos);p2.add(Cos);cos.setBackground(Color.black);
p3.add(amp);p3.add(Amp);amp.setBackground(Color.black);Amp.setForeground(Color.green);
p4.add(sino1);p4.add(coso1);p5.add(sino2);p5.add(coso2);p6.add(mul);p6.add(add);p6.add(sub);

sin.addChangeListener(new ChangeListener()
{public void stateChanged(ChangeEvent e)
{JSlider source=(JSlider)e.getSource();
if(State2&&State4){C=(int)source.getValue();cos.setValue(C);}
else if(State1&&State3){S=(int)source.getValue();C=S;cos.setValue(C);}
else if(State1){S=(int)source.getValue();}
else if(State2){C=(int)source.getValue();}
Draw();}});

sin.createStandardLabels(1,0);

cos.addChangeListener(new ChangeListener()
{public void stateChanged(ChangeEvent e)
{JSlider source=(JSlider)e.getSource();
if(State3&&State1){S=(int)source.getValue();sin.setValue(S);}
else if(State4&&State2){C=(int)source.getValue();S=C;sin.setValue(S);}
else if(State3){S=(int)source.getValue();}
else if(State4){C=(int)source.getValue();}

Draw();}});

amp.addChangeListener(new ChangeListener()
{public void stateChanged(ChangeEvent e)
{JSlider source=(JSlider)e.getSource();
A=(int)source.getValue();Draw();}});

p1.setBackground(Color.black);p2.setBackground(Color.black);
p3.setBackground(Color.black);p4.setBackground(Color.black);
p5.setBackground(Color.black);p6.setBackground(Color.black);

JPanel p=new JPanel(new GridLayout(2,6));
p.add(p1);p.add(p2);p.add(p3);
p.add(p4);p.add(p5);p.add(p6);

this.setBackground(Color.black);this.add(p);Draw();}

public static void main(String[] arg)
{JFrame f=new JFrame("Sin and Cos And Sin Times Cos");f.setSize(950,550);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.add(new whave());f.setLocationRelativeTo(null);
f.setResizable(false);f.setVisible(true);}

public void paint(Graphics g)
{super.paint(g);

//where to draw the graphics object
int XPos=278;

for(int YPos=90;YPos<=350;YPos+=130)
{

//draw decoration
g.setColor(new Color(100,100,255));
g.fillOval(XPos-40,YPos-10,123,123);
g.fillOval(XPos+280,YPos-10,123,123);
g.fillRect(XPos+12,YPos-9,334,122);
g.setColor(Color.green);
g.fillRect(XPos,YPos,360,103);
g.setColor(Color.yellow);
g.fillRect(XPos,50+YPos,360,2);

//draw the grid
g.setColor(Color.black);
for(int i=0;i<=360;i+=20)
{g.fillRect(i+XPos,YPos,1,103);}
for(int i=0;i<=103;i+=20)
{g.fillRect(XPos,i+YPos,360,1);}
g.setColor(new Color(127,127,255,127));
g.fillRect(XPos,YPos,360,104);

//the first whave graph is sine

if(YPos==90)
{g.setColor(Color.red);
for(int i=0;i<360;i++)
{int s=sin(A,(S+i))+50;
g.fillRect((XPos+i),(YPos+s),2,2);}}

//the secont whave graph is cosine

if(YPos==220)
{g.setColor(Color.blue);
for(int i=0;i<360;i++)
{int c=cos(A,(C+i))+50;
g.fillRect((XPos+i),(YPos+c),2,2);}}

//the last whave graph is the combined whave of sine and cosine

if(YPos==350)
{g.setColor(Color.yellow);
for(int i=0;i<360;i++)
{int c=sincos(A,(S+i),(C+i))+50;
g.fillRect((XPos+i),(YPos+c),2,2);}}

}

}

public void Draw()
{if(State1){Sin.setForeground(Color.red);
Sin.setText("Sin "+S+"");}
else{Sin.setText("Cos "+C+"");
Sin.setForeground(Color.blue);}
if(State4){Cos.setText("Cos "+C+"");
Cos.setForeground(Color.blue);}
else{Cos.setText("Sin "+S+"");
Cos.setForeground(Color.red);}
Amp.setText("Amp "+A+"");repaint();}

public void actionPerformed(ActionEvent e)
{if(e.getActionCommand()=="S1")
{State2=false;State1=true;}
if(e.getActionCommand()=="C1")
{State1=false;State2=true;}
if(e.getActionCommand()=="S2")
{State4=false;State3=true;}
if(e.getActionCommand()=="C2")
{State3=false;State4=true;}
if(e.getActionCommand()=="M")
{mul1=true;add1=false;sub1=false;}
if(e.getActionCommand()=="A")
{mul1=false;add1=true;sub1=false;}
if(e.getActionCommand()=="S")
{mul1=false;add1=false;sub1=true;}Draw();}

//my math functions of rotation

private int sincos(int size,int angle1,int angle2){try{angle1=CercularCerve(size,angle1);
angle2=CercularCerve(size,(angle2+90));return(-((angle1*angle2)/size));}catch(Exception e){return(0);}}

private int sin(int size,int angle){angle=CercularCerve(size,angle);return(angle);}

private int cos(int size,int angle){angle=CercularCerve(size,(angle+90));return(angle);}

private int CercularCerve(int size,int angle){angle%=360;int out=0;
try{if(angle<180){out=ChopSize(size,Shift(angle+90))*ChopSize(size,Shift(angle+270));
out=out/size;out+=size;}else{out=ChopSize(size,Shift(angle+90))*ChopSize(size,Shift(angle+90));
out=out/size;out-=size;}}catch(Exception e){return(0);}return(-out);}

private int Shift(int angle){if(angle<0){angle=-angle;}
angle%=360;int phase=angle/90;angle=angle%90;
if(phase==1||phase==3){angle=90-angle;}
if(phase>=2){angle=-angle;}return(angle);}

private int ChopSize(int s,int a){int over=s/90;
int err=s-(over*90);int out=over*a;out+=(err*a)/90;
return(out);}
}