//Major Agota-Piroska
//maim1846
//523

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Iras extends Thread {
	private PrintWriter out;
	private BufferedReader in;
	private Socket socket;
	private Client client;
	private String userName;
	
	public Iras(String userName, Socket socket, Client client) {
		this.userName = userName;
		this.socket = socket;
		this.client = client;
		try {
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(System.in));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			String msg;
			String to;
			while (true) {
				System.out.print(userName + ": ");
				to = in.readLine();
				out.println(to);
				if (to.equals("vege")) {
					break;
				}
				msg = in.readLine();
				out.println(msg);
			}
			
			//socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
