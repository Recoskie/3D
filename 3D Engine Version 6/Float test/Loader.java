import java.nio.*;

class S
{

public float CreateFloat(boolean s,String Ex,String Mantissa)
{String Sing="";
if(s){Sing="0";}else{Sing="1";}
if(Ex.length()>8||Ex.length()<8)
{}
if(Mantissa.length()>23||Mantissa.length()<23)
{}
String Output=Sing+Ex+Mantissa;
byte[] b=new byte[4];
b[0]=(byte)(Integer.parseInt(Output.substring(0,8),2));
b[1]=(byte)(Integer.parseInt((Output.substring(8,16)),2));
b[2]=(byte)(Integer.parseInt(Output.substring(16,24),2));
b[3]=(byte)(Integer.parseInt(Output.substring(24,32),2));
ByteBuffer buf=ByteBuffer.wrap(b);
return(buf.getFloat(0));}

public S(){}
public static void main(String[]args)
{new S().window();}

//run test code
public void window()
{
float f=CreateFloat(true,"10010101","00000000000000000000011");

int i=Float.floatToIntBits(f);

//float data in reverse order

//get the expoenet from other side of float because data is reversed order
int ex=((i>>23)&0xFF)-127;

//get value behind decanal place
int d=(i&0x7FFFFF)>>ex;

System.out.println(f+"");

System.out.println(ex+"");

System.out.println(d+"");
}

}