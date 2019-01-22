import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	ServerSocket ss;
	Socket socket;
	DataOutputStream dos;
	Scanner sc;
	public Server(int port) throws IOException {
	
		ss=new ServerSocket(port);
		System.out.println("Server started");
		sc=new Scanner(System.in);
		socket=ss.accept();
		System.out.println("Client connected");
		dos=new DataOutputStream(socket.getOutputStream());
		
		while (true) {
			
			System.out.println("Enter data to be deliver to client (Only integer)\n");
			String str=sc.nextLine();
			if(str.equals("exit")) break;
			
			String converted=converttobinary(str);
			System.out.println(converted);
			dos.writeUTF(converted);
			
		}
		socket.close();
		ss.close();
		
	}
	private String converttobinary(String str) {
		
		StringBuilder sb=new StringBuilder();
		String s = null;
		String toReturn="";
		for (char c : str.toCharArray())
		{
			s = String.format("%8s", Integer.toBinaryString((int)c & 0xFF)).replace(' ', '0');
			if(Integer.bitCount(((int)c))%2==0) s=s+"0";
			else s+="1";
			toReturn+=s;
		}
		
		return toReturn;
	}
	public static void main(String[] args) {
		
		try {
			Server sr=new Server(5000);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
