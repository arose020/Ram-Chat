package com.rosenthal.ramchat;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Client extends JFrame {
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	private String name, address;
	private int port;

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
	}

	/**
	 * Creates a window to chat in
	 * 
	 * @see Client()
	 */

	private void createWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(880, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0 };
		gbl_contentPane.rowHeights = new int[] { 0 };
		gbl_contentPane.columnWeights = new double[] { Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);
		setVisible(true);
	}
}
