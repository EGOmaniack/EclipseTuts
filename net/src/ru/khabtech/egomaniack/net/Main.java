package ru.khabtech.egomaniack.net;

import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Main extends JFrame implements Runnable {
	static private Socket connection;
	static private ObjectOutputStream output;
	static private ObjectInputStream input;
	
	public static void main(String[] args) {
		new Thread(new Main("Тест")).start();
		new Thread(new Server()).start();
	}

	public Main(String name){
		super(name);
		setLayout(new FlowLayout());
		setSize(300,150);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		
		final JTextField t1 = new JTextField(10);
		final JButton b1 = new JButton("Send");
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getSource() == b1){
					sendData(t1.getText());
				}
			}
		});
		add(t1);
		add(b1);
	}

	public void run() {
	try {
		while(true){
			connection = new Socket(InetAddress.getByName("192.168.0.92"),5678);
			output = new ObjectOutputStream(connection.getOutputStream());
			input = new ObjectInputStream(connection.getInputStream());
			JOptionPane.showMessageDialog(null, (String)input.readObject());
		}
	}catch(UnknownHostException e){
	}catch (IOException e) {
	}catch (HeadlessException e) {
	}catch (ClassNotFoundException e) {}
	}
	
	private static void sendData(Object obj){
		try {
			output.flush();
			output.writeObject(obj);
		} catch (IOException e) {}
	}
}
