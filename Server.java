//Major Agota-Piroska
//maim1846
//523

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


public class Server {
	private int port;
	public int userNumber;
	public Set<String> userName;
	public Set<UserThread> userThread;
    
    public Server(int port) {
    	File myObj = new File("user.txt");
    	try {
			myObj.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	this.port = port;
    	userNumber = 0;
    	userName = new HashSet<>();
    	userThread = new HashSet<>();
    }
    
    public void indul() throws IOException {
    	ServerSocket  serverSocket = new ServerSocket(port);
		
    	System.out.println("Elindult a szerver!");
    	
    	while (true) {
    		Socket socket;
			socket = serverSocket.accept();
			UserThread user = new UserThread(socket, this);
	    	user.start();
    	}
    }
    
    public void publikusUzenetKuldese(String msg, UserThread exl) {
    	for (UserThread u: this.userThread) {
    		if (u != exl) {
    			u.uzenetKuldes(msg);
    		}
    	}
    }
    public void privatUzenetKuldese(String msg, String userName) {
    	Iterator<String> uName = this.userName.iterator();
    	Iterator<UserThread> u = this.userThread.iterator();
    	while (uName.hasNext()) {
    		UserThread user = u.next();
    		if (uName.next().equals(userName)) {
    			user.uzenetKuldes(msg);
    			break;
    		}
    		
    	}
    }
    
    public Set<String> felhasznalokListaja() {
    	return this.userName;
    }
    
    public boolean vanFelhasznalo() {
    	return userNumber > 0;
    }
    
    public void felhasznaloHozzaadasa(String userName, UserThread user) throws IOException {
    	LocalDateTime time = LocalDateTime.now();
    	PrintWriter writer = new PrintWriter("user.txt", "UTF-8");
    	writer.println(userName + " " + time);
    	writer.close();
    	this.userName.add(userName);
		this.userThread.add(user);
		userNumber++;
    }
    
    public void felhasznaloKilep(String userName) {
    	Iterator<String> uName = this.userName.iterator();
    	Iterator<UserThread> u = this.userThread.iterator();
    	while (uName.hasNext() && u.hasNext()) {
    		UserThread user = u.next();
    		if (uName.next().equals(userName)) {
    			this.userThread.remove(user);
    			this.userName.remove(userName);
    			userNumber--;
    		}
    		break;
    	}
    }
    
    public static void main(String[] args) {
    	Server server = new Server(8000);
    	try {
			server.indul();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
