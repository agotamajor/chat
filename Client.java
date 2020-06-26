//Major Agota-Piroska
//maim1846
//523

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	private String userName;
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	
	public Client(String hostname, int port) {
		try {
			socket = new Socket(hostname, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(System.in));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void indit() {
		System.out.print("Add meg a felhasznaloneed: ");
		try {
			userName = in.readLine();
			out.println(userName);
			new Iras(userName, socket, this).start();
			new Olvasas(socket, this).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Client client = new Client("localhost", 8000);
		client.indit();
	}
}
