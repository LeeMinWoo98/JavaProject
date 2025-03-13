package movie;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import user.U_DAO;
import user.U_DTO;

public class GUI {
	U_DTO Udto = new U_DTO();
	U_DAO Udao = new U_DAO();
	private JFrame frame;
	private JTextField textField;
	private JTextField passwordField;
	private JButton btnNewButton_1;
	private JButton btnJoin;
	private JLabel lblNewLabel;
	private JButton btnNewButton_2;
	private JFrame joinframe;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(500, 300, 434, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		textField = new JTextField("ID : ");
		textField.setBounds(132, 166, 160, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 10));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				String pw = passwordField.getText();
				U_DTO udto = new U_DTO();
				udto.setId(id);
				udto.setPw(pw);
				Udto = udto;
				//Udao.login(Udto);
			}
		});
		btnNewButton.setBounds(304, 167, 57, 21);
		panel.add(btnNewButton);
		
		passwordField = new JTextField("PW : ");
		passwordField.setBounds(132, 197, 160, 21);
		panel.add(passwordField);
		
		btnNewButton_1 = new JButton("guset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(132, 228, 67, 23);
		panel.add(btnNewButton_1);
		
		btnJoin = new JButton("join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				joinframe = new JFrame();
				joinframe.getContentPane().setLayout(null);
				JPanel joinpanel = new JPanel();
			}
		});
		btnJoin.setFont(new Font("굴림", Font.PLAIN, 11));
		btnJoin.setBounds(304, 196, 57, 21);
		panel.add(btnJoin);
		
		btnNewButton_2 = new JButton("resign");
		btnNewButton_2.setFont(new Font("굴림", Font.PLAIN, 11));
		btnNewButton_2.setBounds(225, 228, 67, 23);
		panel.add(btnNewButton_2);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(GUI.class.getResource("/image/image.jpg")));
		lblNewLabel.setBounds(0, 0, 434, 261);
		panel.add(lblNewLabel);
	}
}
