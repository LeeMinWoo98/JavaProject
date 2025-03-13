package movie;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import user.U_DAO;
import user.U_DTO;

public class UI extends JFrame implements ActionListener {

	private U_DAO udao = new U_DAO();
	private JFrame frame;
	private JFrame movieFrame;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JButton resignButton;
	private JButton joinButton;
	private JButton guestButton;
	private JButton checkButton;
	private JButton joinButton2;
	private JFrame joinframe;
	private JLabel imageLabel;
	private JLabel imageLabel2;
	private JLabel checkLabel;
	private JTextField idTextField2;
	private JTextField pwTextField2;
	private JTextField nameTextField;
	private JTextField bdayTextField;
	private int pwcheck = 0;

	public UI() {

		// 프레임 만들기
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setSize(434, 310);
		frame.setLocationRelativeTo(null);

		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setSize(500, 300);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// 로그인 버튼 만들기
		JButton loginButton = new JButton("login");
		loginButton.setFont(new Font("굴림", Font.PLAIN, 10));
		loginButton.setBounds(304, 167, 57, 21);
		panel.add(loginButton);
		loginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idTextField.getText();
				String pw = pwTextField.getText();
				int a = udao.login(id, pw);
				JPanel panel = new JPanel();
				
				switch(a) {
				case 0:	//로그인 성공
					frame.setVisible(false);
					movieList();
					break;
				case 1:	//비밀번호 틀림
					JFrame wrongpw = new JFrame();
					wrongpw.setSize(200, 80);
					panel.setSize(200, 80);
					wrongpw.setVisible(true);
					wrongpw.getContentPane().add(panel);
					wrongpw.setLocationRelativeTo(null);
					JLabel aa = new JLabel("비밀번호가 올바르지 않습니다.");
					panel.add(aa);
					break;
					
				case 2:	//아이디 없음
					JFrame noid = new JFrame();
					noid.setSize(200, 80);
					panel.setSize(200, 80);
					noid.setVisible(true);
					noid.getContentPane().add(panel);
					noid.setLocationRelativeTo(null);
					JLabel bb = new JLabel("아이디가 존재하지 않습니다");
					panel.add(bb);
					
					break;
				}
			}
		});

		// 게스트 버튼 만들기
		guestButton = new JButton("guest");
		guestButton.setBounds(132, 228, 67, 21);
		panel.add(guestButton);

		// 회원가입버튼 만들기
		joinButton = new JButton("join");
		joinButton.setFont(new Font("굴림", Font.PLAIN, 11));
		joinButton.setBounds(304, 196, 57, 21);
		panel.add(joinButton);
		joinButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 새로운 jframe 생성
				// 회원가입 창
				JFrame joinFrame = new JFrame("회원가입");
				joinFrame.setSize(300, 400);
				joinFrame.setLocationRelativeTo(null);
				joinFrame.setVisible(true);
				JPanel panel = new JPanel();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setSize(300, 400);
				joinFrame.getContentPane().add(panel);
				panel.setLayout(null);

				// 회원가입 id 필드
				idTextField = new JTextField();
				idTextField.setText("ID : ");
				idTextField.setBounds(80, 150, 130, 18);
				panel.add(idTextField);
				idTextField.setColumns(10);
				idTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (idTextField.getText().isEmpty()) {
							idTextField.setText("ID : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (idTextField.getText().equals("ID : "))
							idTextField.setText("");

					}
				});

				// 회원가입 pw 필드
				pwTextField = new JTextField("PW : ");
				pwTextField.setBounds(80, 180, 130, 18);
				panel.add(pwTextField);
				pwTextField.setColumns(10);
				pwTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (pwTextField.getText().isEmpty()) {
							pwTextField.setText("PW : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (pwTextField.getText().equals("PW : "))
							pwTextField.setText("");

					}
				});

				// 회원가입 pw 확인 필드
				pwTextField2 = new JTextField("PW확인 : ");
				pwTextField2.setBounds(80, 210, 130, 18);
				panel.add(pwTextField2);
				pwTextField2.setColumns(10);
				pwTextField2.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (pwTextField2.getText().isEmpty()) {
							pwTextField2.setText("PW확인 : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (pwTextField2.getText().equals("PW확인 : "))
							pwTextField2.setText("");

					}
				});

				// 회원가입 이름 입력 필드
				nameTextField = new JTextField("이름 : ");
				nameTextField.setBounds(80, 240, 130, 18);
				panel.add(nameTextField);
				nameTextField.setColumns(10);
				nameTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (nameTextField.getText().isEmpty()) {
							nameTextField.setText("이름 : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (nameTextField.getText().equals("이름 : "))
							nameTextField.setText("");

					}
				});

				// 회원가입 생일 입력 필드
				bdayTextField = new JTextField("생년월일 : ");
				bdayTextField.setBounds(80, 270, 130, 18);
				panel.add(bdayTextField);
				bdayTextField.setColumns(10);
				bdayTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (bdayTextField.getText().isEmpty()) {
							bdayTextField.setText("생년월일 : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (bdayTextField.getText().equals("생년월일 : "))
							bdayTextField.setText("");

					}
				});

				// 가입버튼
				joinButton = new JButton("가입");
				joinButton.setFont(new Font("굴림", Font.PLAIN, 11));
				joinButton.setBounds(110, 300, 67, 23);
				panel.add(joinButton);
				joinButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (pwcheck == 1) {
							U_DTO u = new U_DTO();
							String id = idTextField.getText();
							String pw = pwTextField.getText();
							String name = nameTextField.getText();
							String bday = bdayTextField.getText();
							u.setId(id);
							u.setPw(pw);
							u.setName(name);
							u.setB_day(bday);
							
							udao.insert(u);
						} else {
							JFrame pwCheck = new JFrame();
							pwCheck.setSize(250, 90);
							pwCheck.setVisible(true);
							JPanel panel = new JPanel();
							panel.setSize(90, 180);
							pwCheck.getContentPane().add(panel);
							pwCheck.setLocationRelativeTo(null);
							if (pwcheck == 2) {
								checkLabel = new JLabel("비밀번호확인을 다시 실시해주세요.");
								panel.add(checkLabel);
							} else {
								checkLabel = new JLabel("비밀번호확인을 실시하지 않았습니다.");
								panel.add(checkLabel);
							}
						}

					}
				});
				// 비밀번호 입력 확인 버튼
				checkButton = new JButton("확인");
				checkButton.setFont(new Font("굴림", Font.PLAIN, 8));
				checkButton.setBounds(215, 210, 50, 18);
				panel.add(checkButton);
				checkButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String pw = pwTextField.getText();
						String pw2 = pwTextField2.getText();

						JFrame pwCheck = new JFrame();
						pwCheck.setSize(20, 90);
						pwCheck.setVisible(true);
						JPanel panel = new JPanel();
						panel.setSize(20, 90);
						pwCheck.getContentPane().add(panel);
						pwCheck.setLocationRelativeTo(null);
						if (pw.equals(pw2)) {
							checkLabel = new JLabel("일치합니다");
							panel.add(checkLabel);
							pwcheck = 1;
						} else {
							checkLabel = new JLabel("일치하지 않습니다.");
							panel.add(checkLabel);
							pwcheck = 2;
						}

					}
				});

				imageLabel2 = new JLabel();
				imageLabel2.setIcon(new ImageIcon(UI.class.getResource("/image/image_login.png")));
				imageLabel2.setSize(300, 400);
				panel.add(imageLabel2);

			}
		});

		// 탈퇴버튼 만들기
		resignButton = new JButton("resign");
		resignButton.setFont(new Font("굴림", Font.PLAIN, 11));
		resignButton.setBounds(225, 228, 67, 23);
		panel.add(resignButton);

		// id텍스트필드
		idTextField = new JTextField("ID : ");
		idTextField.setBounds(132, 166, 160, 21);
		panel.add(idTextField);
		idTextField.setColumns(10);
		idTextField.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (idTextField.getText().isEmpty()) {
					idTextField.setText("ID : ");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (idTextField.getText().equals("ID : "))
					idTextField.setText("");

			}
		});

		// pw텍스트필드
		pwTextField = new JTextField("PW : ");
		pwTextField.setBounds(132, 197, 160, 21);
		panel.add(pwTextField);
		pwTextField.setColumns(10);
		pwTextField.addFocusListener(new FocusListener() {

			public void focusLost(FocusEvent e) {
				if (pwTextField.getText().isEmpty()) {
					pwTextField.setText("PW : ");
				}
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (pwTextField.getText().equals("PW : "))
					pwTextField.setText("");

			}
		});

		// 이미지 넣기
		imageLabel = new JLabel();
		imageLabel.setIcon(new ImageIcon(UI.class.getResource("/image/image.jpg")));

		imageLabel.setSize(434, 261);
		panel.add(imageLabel);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
	
	public void movieList() {
		movieFrame.setSize(800, 700);
		JPanel panel = new JPanel();
		panel.setSize(800, 700);
		panel.add(movieFrame);
		movieFrame.setLocationRelativeTo(null);
		movieFrame.setVisible(true);
	}
}
