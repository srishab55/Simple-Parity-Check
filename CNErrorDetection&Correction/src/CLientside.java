import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import com.google.common.base.Splitter; 

public class CLientside {
	
	Socket socket;
	DataInputStream dis;
	String received;
	public CLientside() throws UnknownHostException, IOException {
	
		socket=new Socket("", 5000);
		System.out.println("Connected");
		
		dis=new DataInputStream(socket.getInputStream());
		
		received=dis.readUTF();
		System.out.println(received);
		//received=makeString("010011100010010011010101000");
		received=makeString(received);
		if(received==null) System.out.println("There is some error in data");
		else 
			{
			System.out.println("The data is correct ");
			System.out.println(received);
			}
		
	}
	public static void main(String[] args) throws UnknownHostException, IOException {

		CLientside cs=new CLientside();
	}

	String makeString(String input)
	{
		String toReturn="";
		Iterable<String> result = Splitter.fixedLength(9) 
                .trimResults() 
                .split(input);
		
		for( String str : result)
		{
			if(str.length()==0) break;
			String st=str.substring(0, str.length()-1);
			int asciibit=Integer.bitCount(Integer.parseInt(str, 2))%2;
			//System.out.println("ascii :"+asciibit);
			
			toReturn+= (char)Integer.parseInt(st, 2)+"";
			if  (asciibit==1) {toReturn=null;}
		}
		//System.out.println(toReturn);
		return toReturn;
		
	}
}
