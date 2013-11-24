package com.rosenthal.ramchat;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

public class Client extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String name, address;
	private int port;
	private JTextField txtMessage;
	private JTextArea txtHistory;
	private DefaultCaret caret;

	/**
	 * Constructor for the Client window.
	 * 
	 * @param name
	 * @param address
	 * @param port
	 * @author Andrew Rosenthal
	 * @see createWindow()
	 */

	public Client(String name, String address, int port) {
		setTitle("Ram Chat Client");
		this.name = name;
		this.address = address;
		this.port = port;
		createWindow();
		console("Attempting a connection to " + address + ":" + port + ", user: " + name);
	}

	/**
	 * Creates a window to chat in
	 * 
	 * @see Client()
	 */

	private void createWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 28, 815, 30, 7 }; //SUM = 880
		gbl_contentPane.rowHeights = new int[] { 35, 475, 40 }; //SUM = 550
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0 };
		gbl_contentPane.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		
		txtHistory = new JTextArea();
		txtHistory.setEditable(false);
		JScrollPane scroll = new JScrollPane(txtHistory);
		caret = (DefaultCaret) txtHistory.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		GridBagConstraints gbc_txtHistory = new GridBagConstraints();
		gbc_txtHistory.insets = new Insets(0, 0, 5, 5);
		gbc_txtHistory.fill = GridBagConstraints.BOTH;
		gbc_txtHistory.gridx = 1;
		gbc_txtHistory.gridy = 1;
		gbc_txtHistory.gridwidth = 2;
		gbc_txtHistory.insets = new Insets(0, 5, 0, 0);
		contentPane.add(scroll, gbc_txtHistory);
		
		txtMessage = new JTextField();
		txtMessage.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					send(txtMessage.getText());
				}
			}
		});
		GridBagConstraints gbc_txtMessage = new GridBagConstraints();
		gbc_txtMessage.insets = new Insets(0, 0, 0, 5);
		gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMessage.gridx = 1;
		gbc_txtMessage.gridy = 2;
		contentPane.add(txtMessage, gbc_txtMessage);
		txtMessage.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				send(txtMessage.getText());
			}
		});
		GridBagConstraints gbc_btnSend = new GridBagConstraints();
		gbc_btnSend.insets = new Insets(0, 0, 0, 5);
		gbc_btnSend.gridx = 2;
		gbc_btnSend.gridy = 2;
		contentPane.add(btnSend, gbc_btnSend);
		
		setVisible(true);
		
		txtMessage.requestFocusInWindow();
	}
	
	private void send(String message) {
		if (message.equals(""))return; //ANTI-SPAM LINE
		message = name + ": " + message;
		console(message);
		txtMessage.setText("");
	}
	
	public void console(String message) {
		txtHistory.append(message + "\n\r");
		txtHistory.setCaretPosition(txtHistory.getDocument().getLength());
	}
}