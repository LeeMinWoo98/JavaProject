package movie;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import user.U_DAO;
import user.U_DTO;

public class UI extends JFrame implements ActionListener {

	private U_DAO udao = new U_DAO();
	private JFrame frame;
	private JFrame movieFrame;
	private JTextField idTextField;
	private JTextField pwTextField;
	private JTextField guestTextField;
	private JTextField guestNameTextField; //
	private JButton resignButton;
	private JButton joinButton;
	private JButton guestButton;
	private JButton idcheckButton;
	private JButton pwcheckButton;
	private JButton joinButton2;
	private JButton resignIdButton;
	private JButton resignPwButton;
	private JFrame joinframe;
	private JLabel imageLabel;
	private JLabel imageLabel2;
	private JLabel checkLabel;
	private JTextField idTextField2;
	private JTextField pwTextField2;
	private JTextField nameTextField;
	private JTextField bdayTextField;
	private JTextField phoneTextField;
	private int pwcheck = 0;
	private int idchecknum = 0;
	private int imagenum = 0;

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

				switch (a) {
				case 0: // 로그인 성공
					frame.setVisible(false);
					movieList(id);
					break;
				case 1: // 비밀번호 틀림
					JFrame wrongpw = new JFrame();
					wrongpw.setSize(200, 80);
					panel.setSize(200, 80);
					wrongpw.setVisible(true);
					wrongpw.getContentPane().add(panel);
					wrongpw.setLocationRelativeTo(null);
					JLabel aa = new JLabel("비밀번호가 올바르지 않습니다.");
					panel.add(aa);
					break;

				case 2: // 아이디 없음
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
		guestButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame guestFrame = new JFrame("게스트 로그인");
				guestFrame.setSize(300, 400);
				guestFrame.setLocationRelativeTo(null);
				guestFrame.setVisible(true);
				JPanel panel = new JPanel();
				panel.setBackground(Color.LIGHT_GRAY);
				panel.setSize(300, 400);
				guestFrame.getContentPane().add(panel);
				panel.setLayout(null);

				// 게스트 휴대폰번호 입력
				guestTextField = new JTextField();
				guestTextField.setText("phone : ");
				guestTextField.setBounds(80, 150, 130, 18);
				panel.add(guestTextField);
				guestTextField.setColumns(10);
				guestTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (guestTextField.getText().isEmpty()) {
							guestTextField.setText("phone : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (guestTextField.getText().equals("phone : "))
							guestTextField.setText("");

					}
				});

				// 게스트 이름 필드
				guestNameTextField = new JTextField("이름 : ");
				guestNameTextField.setBounds(80, 180, 130, 18);
				panel.add(guestNameTextField);
				guestNameTextField.setColumns(10);
				guestNameTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (guestNameTextField.getText().isEmpty()) {
							guestNameTextField.setText("이름 : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (guestNameTextField.getText().equals("이름 : "))
							guestNameTextField.setText("");

					}
				});
				// 게스트 접속 버튼
				joinButton = new JButton("게스트");
				joinButton.setFont(new Font("굴림", Font.PLAIN, 11));
				joinButton.setBounds(110, 300, 67, 23);
				panel.add(joinButton);
				joinButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int check;
						String phone = guestTextField.getText();
						String name = guestNameTextField.getText();
						check = udao.guest(phone, name);

						switch (check) {
						case 1:
							guestFrame.setVisible(false);
							frame.setVisible(false);
							movieList(phone);
							break;
						case 2:
							break;

						}

					}
				});
			}

		});

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
				idTextField.setBounds(80, 120, 130, 18);
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
				pwTextField.setBounds(80, 150, 130, 18);
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
				pwTextField2.setBounds(80, 180, 130, 18);
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
				// 회원가입 핸드폰 번호 필드
				phoneTextField = new JTextField("phone : ");
				phoneTextField.setBounds(80, 210, 130, 18);
				panel.add(phoneTextField);
				phoneTextField.setColumns(10);
				phoneTextField.addFocusListener(new FocusListener() {

					public void focusLost(FocusEvent e) {
						if (phoneTextField.getText().isEmpty()) {
							phoneTextField.setText("phone : ");
						}
					}

					@Override
					public void focusGained(FocusEvent e) {
						if (phoneTextField.getText().equals("phone : "))
							phoneTextField.setText("");

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
						if (pwcheck == 0 && idchecknum == 0) {
							int joinCheck;
							U_DTO u = new U_DTO();
							String id = idTextField.getText();
							String pw = pwTextField.getText();
							String name = nameTextField.getText();
							String bday = bdayTextField.getText();
							String phone = phoneTextField.getText();

							u.setId(id);
							u.setPw(pw);
							u.setName(name);
							u.setB_day(bday);
							u.setPhone(phone);
							joinCheck = udao.insert(u);
							if (joinCheck == 1) {
								JFrame adminCheck = new JFrame();
								adminCheck.setSize(250, 90);
								adminCheck.setVisible(true);
								JPanel adminpanel = new JPanel();
								adminpanel.setSize(90, 180);
								adminCheck.getContentPane().add(panel);
								adminCheck.setLocationRelativeTo(null);
								checkLabel = new JLabel("가입 성공!");
								panel.add(checkLabel);
							} else {
								JFrame adminCheck = new JFrame();
								adminCheck.setSize(250, 90);
								adminCheck.setVisible(true);
								JPanel adminpanel = new JPanel();
								adminpanel.setSize(90, 180);
								adminCheck.getContentPane().add(panel);
								adminCheck.setLocationRelativeTo(null);
								checkLabel = new JLabel("가입 실패.");
								panel.add(checkLabel);

							}

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
				// 아이디 중복 확인 버튼
				idcheckButton = new JButton("확인");
				idcheckButton.setFont(new Font("굴림", Font.PLAIN, 8));
				idcheckButton.setBounds(215, 120, 50, 18);
				panel.add(idcheckButton);
				idcheckButton.addActionListener(new ActionListener() {
					@Override

					public void actionPerformed(ActionEvent e) {
						int idcheck;
						String id = idTextField.getText();
						idcheck = udao.idcheck(id);
						if (idcheck == 0) {
							JFrame idCheck = new JFrame();
							idCheck.setSize(250, 90);
							idCheck.setVisible(true);
							JPanel panel = new JPanel();
							panel.setSize(90, 180);
							idCheck.getContentPane().add(panel);
							idCheck.setLocationRelativeTo(null);
							checkLabel = new JLabel("사용 가능한 아이디입니다.");
							panel.add(checkLabel);
							idchecknum = 0;
						} else {
							JFrame idCheck = new JFrame();
							idCheck.setSize(250, 90);
							idCheck.setVisible(true);
							JPanel panel = new JPanel();
							panel.setSize(90, 180);
							idCheck.getContentPane().add(panel);
							idCheck.setLocationRelativeTo(null);
							checkLabel = new JLabel("중복된 아이디입니다.");
							panel.add(checkLabel);
							idchecknum = 1;
						}
					}
				});

				// 비밀번호 입력 확인 버튼
				pwcheckButton = new JButton("확인");
				pwcheckButton.setFont(new Font("굴림", Font.PLAIN, 8));
				pwcheckButton.setBounds(215, 240, 50, 18);
				panel.add(pwcheckButton);
				pwcheckButton.addActionListener(new ActionListener() {

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
							pwcheck = 0;
						} else {
							checkLabel = new JLabel("일치하지 않습니다.");
							panel.add(checkLabel);
							pwcheck = 1;
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
		resignButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame resignFrame = new JFrame("회원탈퇴");
				resignFrame.setSize(400, 300);
				resignFrame.setLocationRelativeTo(null);
				resignFrame.setVisible(true);
				JPanel panel = new JPanel();
				panel.setSize(300, 400);
				resignFrame.getContentPane().add(panel);
				panel.setLayout(null);

				idTextField = new JTextField();
				idTextField.setText("ID : ");
				idTextField.setBounds(130, 150, 130, 18);
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

				pwTextField = new JTextField("PW : ");
				pwTextField.setBounds(130, 180, 130, 18);
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
				resignIdButton = new JButton("확인");
				resignIdButton.setFont(new Font("굴림", Font.PLAIN, 11));
				resignIdButton.setBounds(130, 210, 67, 23);
				panel.add(resignIdButton);
				resignIdButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int a;
						String id = idTextField.getText();
						String pw = pwTextField.getText();
						a = udao.resign(id, pw);
						switch (a) {
						case 0:
							// 삭제완료 창
							JFrame resignFrame1 = new JFrame();
							resignFrame1.setSize(200, 90);
							JPanel panel1 = new JPanel();
							panel1.setSize(200, 90);
							resignFrame1.setVisible(true);
							resignFrame1.getContentPane().add(panel1);
							resignFrame1.setLocationRelativeTo(null);
							checkLabel = new JLabel("회원 탈퇴가 완료되었습니다.");
							panel1.add(checkLabel);
							break;
						case 1:
							// 삭제 실패
							JFrame resignFrame2 = new JFrame();
							resignFrame2.setSize(200, 90);
							JPanel panel2 = new JPanel();
							panel2.setSize(200, 90);
							resignFrame2.setVisible(true);
							resignFrame2.getContentPane().add(panel2);
							resignFrame2.setLocationRelativeTo(null);
							checkLabel = new JLabel("비밀번호가 틀렸습니다.");
							panel2.add(checkLabel);
							break;

						case 2:
							JFrame resignFrame3 = new JFrame();
							resignFrame3.setSize(200, 90);
							JPanel panel3 = new JPanel();
							panel3.setSize(200, 90);
							resignFrame3.setVisible(true);
							resignFrame3.getContentPane().add(panel3);
							resignFrame3.setLocationRelativeTo(null);
							checkLabel = new JLabel("해당하는 아이디가 없습니다.");
							panel3.add(checkLabel);
							break;
						}
					}
				});

				resignPwButton = new JButton("취소");
				resignPwButton.setFont(new Font("굴림", Font.PLAIN, 11));
				resignPwButton.setBounds(200, 210, 67, 23);
				panel.add(resignPwButton);
				resignPwButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						resignFrame.setVisible(false);

					}
				});

			}
		});

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

	public void movieList(String id) {
		M_DAO mdao = new M_DAO();
		ArrayList<M_DTO> mlist = new ArrayList<>();

		System.out.println(id);
		movieFrame = new JFrame();
		movieFrame.getContentPane().setLayout(null);
		movieFrame.setLocationRelativeTo(null);
		movieFrame.setSize(1200, 900);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(1200, 900);
		movieFrame.getContentPane().add(panel);
		movieFrame.setLocationRelativeTo(null);
		movieFrame.setVisible(true);
		JLabel movieImage = new JLabel();
		movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/nowshowing.jpg")));
		movieImage.setBounds(30, 30, 300, 400);
		panel.add(movieImage);
		JButton rightMovie = new JButton("다음");
		rightMovie.setBounds(220, 430, 67, 23);
		rightMovie.setFont(new Font("굴림", Font.PLAIN, 11));
		panel.add(rightMovie);
		rightMovie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (imagenum) {
				case 0:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/괴물.jpg")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 1:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/기생충.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 2:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/더퍼스트슬램덩크.jpg")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 3:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/범죄도시.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 4:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/스마일.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 5:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/스파이더맨 노웨이홈.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 6:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/아이언맨2.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 7:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/어벤져스.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 8:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/전우치.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum++;
					break;
				case 9:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/타짜.jpg")));
					movieImage.setBounds(30, 30, 300, 400);
					break;

				}

			}
		});

		JButton leftMovie = new JButton("이전");
		leftMovie.setBounds(70, 430, 67, 23);
		leftMovie.setFont(new Font("굴림", Font.PLAIN, 11));
		panel.add(leftMovie);
		leftMovie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch (imagenum) {
				case 0:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/괴물.jpg")));
					movieImage.setBounds(30, 30, 300, 400);

					break;
				case 1:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/기생충.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 2:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/더퍼스트슬램덩크.jpg")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 3:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/범죄도시.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 4:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/스마일.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 5:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/스파이더맨 노웨이홈.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 6:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/아이언맨2.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 7:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/어벤져스.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 8:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/전우치.png")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;
				case 9:
					movieImage.setIcon(new ImageIcon(UI.class.getResource("/image/타짜.jpg")));
					movieImage.setBounds(30, 30, 300, 400);
					imagenum--;
					break;

				}

			}
		});

		JButton pickMovie = new JButton("예매");
		pickMovie.setBounds(145, 430, 67, 23);
		pickMovie.setFont(new Font("굴림", Font.PLAIN, 11));
		panel.add(pickMovie);
		pickMovie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		mlist = mdao.movieInfo();
		
		String[] theater = new String[mlist.size()];
		for(int i=0; i<mlist.size(); i++) {
			theater[i] ="🎬"+ mlist.get(i).getLocation();
					//{"🎬 롯데시네마 홍대", "🎬 메가박스 신촌", "🎬 씨네큐 동대문"};
			
		}
		
		
		JList<String> theaterList = new JList<>(theater);
		theaterList.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
		theaterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		theaterList.setFixedCellHeight(40);
		theaterList.setBackground(Color.WHITE);
		theaterList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		
		
		JPanel listPanel = new JPanel();
        listPanel.setLayout(new BorderLayout());
        listPanel.setBounds(600, 10, 250, 300);
        listPanel.setBackground(Color.LIGHT_GRAY);
        listPanel.setBorder(BorderFactory.createTitledBorder("영화관 선택"));
        JScrollPane scrollPane = new JScrollPane(theaterList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        panel.add(listPanel);


		// int guestCheck;
		// String guest = "1";
		// guestCheck = udao.checkGuest(id, guest);

		movieFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
