//Major Agota-Piroska
//maim1846
//523

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class UserThread extends Thread {
	private Socket socket;
	private Server server;
	private PrintWriter out;
	
	public UserThread(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }
	
	public void run() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStream output = socket.getOutputStream();
            out = new PrintWriter(output, true);
            
            felhasznalokKiirasa();
            
            String userName = in.readLine();
            server.felhasznaloHozzaadasa(userName, this);
            
            String msgServer = userName + " aktiv";
            server.publikusUzenetKuldese(msgServer, this);
            
            String to = "";
            String msgClient = "";
            
            while (true) {
            	to = in.readLine();
            	if (to.equals("vege"))
            	{
            		break;
            	} else if (to.equals("all")) {
            		msgClient = in.readLine();
            		msgServer = userName + ": " + msgClient;
            		server.publikusUzenetKuldese(msgServer, this);
            	} else {
            		msgClient = in.readLine();
            		msgServer = userName + ": " + msgClient;
            		server.privatUzenetKuldese(msgServer, to);
            	}
            }
            
            server.felhasznaloKilep(userName);
            //socket.close();
            
            msgServer = userName + " mar nem aktiv";
            server.publikusUzenetKuldese(msgServer, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void felhasznalokKiirasa() {
		if (server.vanFelhasznalo()) {
			out.println("Aktiv felhasznalok: " + server.felhasznalokListaja());
		} else {
			out.println("Nincs aktiv felhasznalo!");
		}
	}
	
	public void uzenetKuldes(String msg) {
		out.println(msg);
	}
}
