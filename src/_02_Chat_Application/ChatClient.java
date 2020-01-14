package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class ChatClient {

	private ChatApp chat;
	
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public ChatClient(String ip, int port, ChatApp chat) {
		this.ip = ip;
		this.port = port;
		this.chat = chat;
	}

	public void start() {
		// TODO Auto-generated method stub
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				chat.addText((String) is.readObject(), "Server");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void sendMessage(String message) {
		// TODO Auto-generated method stub
		try {
			if (os != null) {
				os.writeObject(message);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
