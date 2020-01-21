package _02_Chat_Application;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

import _00_Click_Chat.networking.Client;
import _00_Click_Chat.networking.Server;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame implements KeyListener {
	JTextArea text = new JTextArea();
	JTextArea message = new JTextArea();
	JTextArea message2 = new JTextArea();
	JButton button = new JButton("Send Message");
	JButton button2 = new JButton("Send Message");
	
	ChatServer server;
	ChatClient client;
	
	public static void main(String[] args) {
		new ChatApp();
	}
	
	public ChatApp() {
		
		int response = JOptionPane.showConfirmDialog(null, "Would you like to host a connection?", "Connection?", JOptionPane.YES_NO_OPTION);
		if(response == JOptionPane.YES_OPTION){
			server = new ChatServer(8080, this);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null, "Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			button.addActionListener((e)->{
				text.setText(text.getText()+"\nServer: "+message.getText());
				server.sendMessage(message.getText());
				message.setText("");
			});
			JFrame frame = new JFrame();
			
			

			
			message.addKeyListener(this);
			
			
			frame.setLayout(null);
			frame.setResizable(false);
			
			message.setSize(260, 20);
			message.setLocation(10, 270);
			frame.add(message);
			text.setLineWrap(true);
			text.setEditable(false);
			text.setSize(300, 250);
			text.setLocation(10, 10);
			text.setVisible(true);
			
			
			JScrollPane scroll = new JScrollPane (text);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		          scroll.setSize(300, 250);
					scroll.setLocation(10, 10);
		    frame.add(scroll);
			button.setSize(100, 20);
			button.setLocation(275, 270);
			frame.add(button);
			frame.setVisible(true);
			frame.setSize(400, 330);
			//frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();
			
		}
	
		else{
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new ChatClient(ipStr, port, this);
			button2.addKeyListener(this);
			button2.addActionListener((e)->{
				text.setText(text.getText()+"\nClient: "+message2.getText());
				client.sendMessage(message2.getText());
				message2.setText("");
			});
			JFrame frame = new JFrame();
			frame.setLayout(null);
			frame.setResizable(false);
			message2.setSize(260, 20);
			message2.setLocation(10, 270);
			message2.addKeyListener(this);
			frame.add(message2);
			text.setLineWrap(true);
			text.setEditable(false);
			text.setSize(300, 250);
			text.setLocation(10, 10);
			text.setVisible(true);
			JScrollPane scroll = new JScrollPane (text);
		    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		          scroll.setSize(300, 250);
					scroll.setLocation(10, 10);
		    frame.add(scroll);
			button2.setSize(100, 20);
			button2.setLocation(275, 270);
			frame.add(button2);
			frame.setVisible(true);
			frame.setSize(400, 330);
			//frame.pack();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();

		}
		
	}
	
	public void addText(String input, String soc) {
		text.setText(text.getText()+"\n"+soc+": "+input);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("noo");
		if(e.getKeyChar()==KeyEvent.VK_ENTER) {	
			if(e.getSource().equals(message2)) {
				message2.setText(message2.getText().substring(0,message2.getText().length()-1));
				text.setText(text.getText()+"\nClient: "+message2.getText());
				client.sendMessage(message2.getText());
				message2.setText("");
			}
			if(e.getSource().equals(message)) {
				message.setText(message.getText().substring(0,message.getText().length()-1));
				text.setText(text.getText()+"\nServer: "+message.getText());
				server.sendMessage(message.getText());
				message.setText("");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
