//Major Agota-Piroska
//maim1846
//523

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

public class Olvasas extends Thread {
	private BufferedReader in;
	private Socket socket;
	private Client client;
	
	public Olvasas(Socket socket, Client client) {
		this.socket = socket;
		this.client = client;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		String msg;
		while (true) {
			try {
				msg = in.readLine();
				System.out.println(msg);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
	}
}
