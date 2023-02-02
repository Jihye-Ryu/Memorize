package project.java.memorize.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

import javax.swing.border.LineBorder;

import project.java.memorize.controller.MemorizeDaoImpl;
import project.java.memorize.model.User;

import static project.java.memorize.model.User.Entity.*;
import static project.java.memorize.model.Word.Entity.*;

import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

public class MemorizeMain extends JFrame {

    private JPanel panels;
    private JTextField inputIDLogIn;
    private JPasswordField inputPasswordLogIn;
    private JButton btnLogIn;
    private JButton btnFindPasswordPanel;
    private JButton btnSignUpPanel;
    private JTextField inputIDFind;
    private JButton btnFindPassword;
    private JButton btnLogInPanel;
    private JPanel panel;
    private JButton btnSignUpPanel2;
    private JTextField inputIDSignUp;
    private JPasswordField inputPasswordSignUp;
    private JButton btnLogInPanel2;
    private JButton btnSignUp;
    private JPasswordField inputPassWordConfirm;
    private JTextField inputName;
    private JTextField inputEmail;
    private JLabel lblCheckEmailResult;
    private JLabel lblNewLabel_1;
    private JLabel lblCheckIDResult;
    private JLabel lblCheckPasswordResult;
    private JLabel lblCheckPasswordConfirm;
    private JLabel lblCheckNameResult;
    private CardLayout cl;   // 패널 전환 객체
    
    private MemorizeDaoImpl dao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeMain frame = new MemorizeMain();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MemorizeMain() {
        
        dao = MemorizeDaoImpl.getInstance();
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // DISPOSE_ON_CLOSE
        
        setBounds(730, 250, 527, 637);
        panels = new JPanel();
        panels.setBackground(new Color(255, 165, 0));
        panels.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(panels);
        panels.setLayout(new CardLayout(0, 0));
        
        cl = (CardLayout) panels.getLayout();
        
        JPanel panelLogIn = new JPanel();
        panelLogIn.setBackground(new Color(255, 165, 0));
        panels.add(panelLogIn, "Panel1");
        panelLogIn.setLayout(null);
        
        JLabel lblMemorize = new JLabel("MEMORIZE");
        lblMemorize.setBorder(null);
        lblMemorize.setForeground(new Color(255, 255, 255));
        lblMemorize.setOpaque(true);
        lblMemorize.setBackground(new Color(255, 165, 0));
        lblMemorize.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        lblMemorize.setHorizontalAlignment(SwingConstants.CENTER);
        lblMemorize.setBounds(130, 55, 253, 64);
        panelLogIn.add(lblMemorize);
        
        JLabel lblID = new JLabel("ID");
        lblID.setForeground(new Color(255, 255, 255));
        lblID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblID.setHorizontalAlignment(SwingConstants.CENTER);
        lblID.setBounds(138, 197, 32, 21);
        panelLogIn.add(lblID);
        
        JLabel lblPassword = new JLabel("PASSWORD");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblPassword.setBounds(100, 252, 84, 21);
        panelLogIn.add(lblPassword);
        
        inputIDLogIn = new JTextField();
        inputIDLogIn.setForeground(new Color(255, 140, 0));
        inputIDLogIn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputIDLogIn.setBounds(198, 194, 136, 27);
        panelLogIn.add(inputIDLogIn);
        
        inputPasswordLogIn = new JPasswordField();
        inputPasswordLogIn.setForeground(new Color(255, 140, 0));
        inputPasswordLogIn.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputPasswordLogIn.setBounds(198, 249, 136, 27);
        panelLogIn.add(inputPasswordLogIn);
        
        btnLogIn = new JButton("Log In");
        btnLogIn.setFocusPainted(false);
        btnLogIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                logIn();
                
            }
        });
        btnLogIn.setBorder(new LineBorder(new Color(255, 239, 213), 2, true));
        btnLogIn.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        btnLogIn.setForeground(new Color(255, 255, 255));
        btnLogIn.setBackground(new Color(255, 140, 0));
        btnLogIn.setBounds(198, 318, 136, 48);
        panelLogIn.add(btnLogIn);
        
        btnFindPasswordPanel = new JButton("비밀번호 찾기");
        btnFindPasswordPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                cl.next(panels);
                clearFindPasswordPanel();
                
            }
        });
        btnFindPasswordPanel.setBorder(null);
        btnFindPasswordPanel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnFindPasswordPanel.setForeground(new Color(255, 255, 255));
        btnFindPasswordPanel.setBackground(new Color(255, 165, 0));
        btnFindPasswordPanel.setBounds(263, 499, 95, 21);
        panelLogIn.add(btnFindPasswordPanel);
        
        btnSignUpPanel = new JButton("회원가입");
        btnSignUpPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                cl.last(panels);
                clearSignUpPanel();
                
            }
        });
        btnSignUpPanel.setForeground(Color.WHITE);
        btnSignUpPanel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnSignUpPanel.setBorder(null);
        btnSignUpPanel.setBackground(new Color(255, 165, 0));
        btnSignUpPanel.setBounds(398, 499, 61, 21);
        panelLogIn.add(btnSignUpPanel);
        
        JPanel panelFindPassword = new JPanel();
        panelFindPassword.setBackground(new Color(255, 165, 0));
        panels.add(panelFindPassword, "Panel2");
        panelFindPassword.setLayout(null);
        
        JLabel lblID2 = new JLabel("ID");
        lblID2.setBounds(84, 242, 73, 21);
        lblID2.setBackground(new Color(255, 165, 0));
        lblID2.setHorizontalAlignment(SwingConstants.CENTER);
        lblID2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblID2.setForeground(new Color(255, 255, 255));
        panelFindPassword.add(lblID2);
        
        inputIDFind = new JTextField();
        inputIDFind.setBounds(158, 236, 173, 34);
        inputIDFind.setForeground(new Color(255, 140, 0));
        inputIDFind.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        panelFindPassword.add(inputIDFind);
        inputIDFind.setColumns(10);
        
        btnFindPassword = new JButton("찾기");
        btnFindPassword.setFocusPainted(false);
        btnFindPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                findPassword();
                
            }
        });
        btnFindPassword.setBounds(193, 308, 106, 44);
        btnFindPassword.setForeground(new Color(255, 255, 255));
        btnFindPassword.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnFindPassword.setBorder(new LineBorder(new Color(255, 235, 205), 2, true));
        btnFindPassword.setBackground(new Color(255, 140, 0));
        panelFindPassword.add(btnFindPassword);
        
        btnLogInPanel = new JButton("로그인");
        btnLogInPanel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                cl.first(panels);
                clearLogInPanel();
                
            }
        });
        btnLogInPanel.setBounds(312, 502, 45, 21);
        btnLogInPanel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnLogInPanel.setForeground(new Color(255, 255, 255));
        btnLogInPanel.setBackground(new Color(255, 165, 0));
        btnLogInPanel.setBorder(null);
        panelFindPassword.add(btnLogInPanel);
        
        JPanel panelSignUp = new JPanel();
        panelSignUp.setForeground(new Color(255, 165, 0));
        panelSignUp.setBackground(new Color(255, 165, 0));
        panels.add(panelSignUp, "panel3");
        panelSignUp.setLayout(null);
        
        btnSignUpPanel2 = new JButton("회원가입");
        btnSignUpPanel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                cl.last(panels);
                clearSignUpPanel();
                
            }
        });
        btnSignUpPanel2.setForeground(Color.WHITE);
        btnSignUpPanel2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnSignUpPanel2.setBorder(null);
        btnSignUpPanel2.setBackground(new Color(255, 165, 0));
        btnSignUpPanel2.setBounds(378, 502, 78, 21);
        panelFindPassword.add(btnSignUpPanel2);
        
        JLabel lblFindPassword = new JLabel("FIND PASSWORD");
        lblFindPassword.setOpaque(true);
        lblFindPassword.setHorizontalAlignment(SwingConstants.CENTER);
        lblFindPassword.setForeground(Color.WHITE);
        lblFindPassword.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        lblFindPassword.setBorder(null);
        lblFindPassword.setBackground(new Color(255, 165, 0));
        lblFindPassword.setBounds(72, 60, 360, 64);
        panelFindPassword.add(lblFindPassword);
        
        
        JLabel lblSignUp = new JLabel("SIGN UP");
        lblSignUp.setOpaque(true);
        lblSignUp.setHorizontalAlignment(SwingConstants.CENTER);
        lblSignUp.setForeground(Color.WHITE);
        lblSignUp.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        lblSignUp.setBorder(null);
        lblSignUp.setBackground(new Color(255, 165, 0));
        lblSignUp.setBounds(126, 22, 253, 64);
        panelSignUp.add(lblSignUp);
        
        JLabel lblID3 = new JLabel("아이디");
        lblID3.setHorizontalAlignment(SwingConstants.CENTER);
        lblID3.setForeground(Color.WHITE);
        lblID3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblID3.setBounds(127, 132, 50, 21);
        panelSignUp.add(lblID3);
        
        JLabel lblPassword3 = new JLabel("비밀번호");
        lblPassword3.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword3.setForeground(Color.WHITE);
        lblPassword3.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblPassword3.setBounds(93, 190, 84, 21);
        panelSignUp.add(lblPassword3);
        
        inputIDSignUp = new JTextField();
        inputIDSignUp.setForeground(new Color(255, 140, 0));
        inputIDSignUp.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputIDSignUp.setColumns(10);
        inputIDSignUp.setBounds(191, 129, 136, 27);
        panelSignUp.add(inputIDSignUp);
        
        inputPasswordSignUp = new JPasswordField();
        inputPasswordSignUp.setForeground(new Color(255, 140, 0));
        inputPasswordSignUp.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputPasswordSignUp.setBounds(191, 187, 136, 27);
        panelSignUp.add(inputPasswordSignUp);
        
        btnLogInPanel2 = new JButton("로그인");
        btnLogInPanel2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                cl.first(panels);
                clearLogInPanel();
                
            }
        });
        btnLogInPanel2.setForeground(Color.WHITE);
        btnLogInPanel2.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnLogInPanel2.setBorder(null);
        btnLogInPanel2.setBackground(new Color(255, 165, 0));
        btnLogInPanel2.setBounds(396, 527, 61, 21);
        panelSignUp.add(btnLogInPanel2);
        
        btnSignUp = new JButton("가입");
        btnSignUp.setFocusPainted(false);
        btnSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                signUp();
                
                
            }
        });
        btnSignUp.setForeground(Color.WHITE);
        btnSignUp.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        btnSignUp.setBorder(new LineBorder(new Color(255, 239, 213), 2, true));
        btnSignUp.setBackground(new Color(255, 140, 0));
        btnSignUp.setBounds(192, 444, 136, 39);
        panelSignUp.add(btnSignUp);
        
        JLabel lblName = new JLabel("닉네임");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblName.setBounds(127, 303, 50, 21);
        panelSignUp.add(lblName);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblEmail.setBounds(127, 357, 50, 21);
        panelSignUp.add(lblEmail);
        
        JLabel lblPassword3Confirm = new JLabel("비밀번호 확인");
        lblPassword3Confirm.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword3Confirm.setForeground(Color.WHITE);
        lblPassword3Confirm.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblPassword3Confirm.setBounds(72, 244, 105, 21);
        panelSignUp.add(lblPassword3Confirm);
        
        inputPassWordConfirm = new JPasswordField();
        inputPassWordConfirm.setForeground(new Color(255, 140, 0));
        inputPassWordConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputPassWordConfirm.setBounds(191, 244, 136, 27);
        panelSignUp.add(inputPassWordConfirm);
        
        inputName = new JTextField();
        inputName.setForeground(new Color(255, 140, 0));
        inputName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputName.setColumns(10);
        inputName.setBounds(191, 300, 136, 27);
        panelSignUp.add(inputName);
        
        inputEmail = new JTextField();
        inputEmail.setForeground(new Color(255, 140, 0));
        inputEmail.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputEmail.setColumns(10);
        inputEmail.setBounds(191, 354, 136, 27);
        panelSignUp.add(inputEmail);
        
        lblCheckEmailResult = new JLabel("이메일은 비밀번호 찾기에 사용됩니다.");
        lblCheckEmailResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        lblCheckEmailResult.setBounds(191, 384, 214, 21);
        panelSignUp.add(lblCheckEmailResult);
        lblCheckEmailResult.setVisible(true);
        
        lblCheckIDResult = new JLabel("");
        lblCheckIDResult.setForeground(new Color(255, 255, 240));
        lblCheckIDResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckIDResult.setBounds(191, 156, 214, 21);
        panelSignUp.add(lblCheckIDResult);
        lblCheckIDResult.setVisible(true);
        
        lblCheckPasswordResult = new JLabel("");
        lblCheckPasswordResult.setForeground(new Color(255, 0, 0));
        lblCheckPasswordResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckPasswordResult.setBounds(191, 213, 214, 21);
        panelSignUp.add(lblCheckPasswordResult);
        lblCheckPasswordResult.setVisible(true);
        
        lblCheckPasswordConfirm = new JLabel("");
        lblCheckPasswordConfirm.setForeground(new Color(255, 255, 240));
        lblCheckPasswordConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckPasswordConfirm.setBounds(191, 269, 214, 21);
        panelSignUp.add(lblCheckPasswordConfirm);
        lblCheckPasswordConfirm.setVisible(true);
        
        lblCheckNameResult = new JLabel("");
        lblCheckNameResult.setForeground(new Color(255, 0, 0));
        lblCheckNameResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckNameResult.setBounds(191, 325, 214, 21);
        panelSignUp.add(lblCheckNameResult);
        lblCheckNameResult.setVisible(true);
        
        
        
    }

   private void clearLogInPanel() {
        
       inputIDLogIn.setText("");
       inputPasswordLogIn.setText("");
        
    }

   private void clearSignUpPanel() {
        
       inputIDSignUp.setText("");
       inputPasswordSignUp.setText("");
       inputPassWordConfirm.setText("");
       inputName.setText("");
       inputEmail.setText("");
       lblCheckIDResult.setText("");
       lblCheckPasswordResult.setText("");
       lblCheckPasswordConfirm.setText("");
       lblCheckNameResult.setText("");
       lblCheckEmailResult.setText("이메일은 비밀번호 찾기에 사용됩니다.");
       lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        
    }

   private void clearFindPasswordPanel() {
       
       inputIDFind.setText("");
        
    }

    private void signUp() {
        
        String id = inputIDSignUp.getText();
        String password = inputPasswordSignUp.getText();    
        String passwordConfirm = inputPassWordConfirm.getText();
        String name = inputName.getText();
        String email = inputEmail.getText();
        
        lblCheckIDResult.setVisible(false);
        lblCheckPasswordResult.setVisible(false);
        lblCheckPasswordConfirm.setVisible(false);
        lblCheckNameResult.setVisible(false);
        lblCheckEmailResult.setText("이메일은 비밀번호 찾기에 사용됩니다.");
        lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        
        
        if (id.equals("")) {
            lblCheckIDResult.setText("아이디를 입력하세요.");
            lblCheckIDResult.setForeground(new Color(255,0,0));
            lblCheckIDResult.setVisible(true);
            inputIDSignUp.requestFocus();
        } else if (dao.selectUserID(id) == true) {
            lblCheckIDResult.setText("중복된 아이디입니다.");
            lblCheckIDResult.setForeground(new Color(255,0,0));
            lblCheckIDResult.setVisible(true);
        } else if (dao.selectUserID(id) == false) {
            lblCheckIDResult.setText("사용 가능한 아이디입니다.");
            lblCheckIDResult.setForeground(new Color(255,255,240));
            lblCheckIDResult.setVisible(true);
            
            if (password.equals("")) {
                lblCheckPasswordResult.setText("비밀번호를 입력하세요.");
                lblCheckPasswordResult.setForeground(new Color(255,0,0));
                lblCheckPasswordResult.setVisible(true);
                inputPasswordSignUp.requestFocus();
            } else if (password.length() < 4 ) {
                lblCheckPasswordResult.setText("비밀번호는 4자리 이상이어야 합니다.");
                lblCheckPasswordResult.setForeground(new Color(255,0,0));
                lblCheckPasswordResult.setVisible(true);
            } else if (passwordConfirm.equals("")) {
                lblCheckPasswordConfirm.setText("비밀번호를 다시 한 번 입력하세요.");
                lblCheckPasswordConfirm.setForeground(new Color(255,0,0));
                lblCheckPasswordConfirm.setVisible(true);
                inputPassWordConfirm.requestFocus();
            } else if (!(password.equals(passwordConfirm))) {
                lblCheckPasswordConfirm.setText("비밀번호가 일치하지 않습니다.");
                lblCheckPasswordConfirm.setForeground(new Color(255,0,0));
                lblCheckPasswordConfirm.setVisible(true);
            } else {
                
                // 아이디 입력됐고, 중복확인됐고, 사용가능한 아이디고, 비번과 비번확인 일치할 때
                
                lblCheckPasswordConfirm.setText("비밀번호가 일치합니다.");
                lblCheckPasswordConfirm.setForeground(new Color(255,255,240));
                lblCheckPasswordConfirm.setVisible(true);
                
                
                if (name.equals("")) {
                    lblCheckNameResult.setText("닉네임을 입력하세요.");
                    lblCheckNameResult.setForeground(new Color(255,0,0));
                    lblCheckNameResult.setVisible(true);
                    inputName.requestFocus();
                } else if (email.equals("")) {
                    lblCheckEmailResult.setText("이메일을 입력하세요.");
                    lblCheckEmailResult.setForeground(new Color(255,0,0));
                    inputEmail.requestFocus();
                } else if (dao.selectEmail(email) == true) {
                    lblCheckEmailResult.setText("중복된 이메일입니다.");
                    lblCheckEmailResult.setForeground(new Color(255,0,0));
                } else if (dao.selectEmail(email) == false) {
                    lblCheckEmailResult.setText("사용 가능한 이메일입니다.");
                    lblCheckEmailResult.setForeground(new Color(255,255,240));
                    
                 // 모든 칸 입력됐고, 아이디 중복확인 통과, 비번확인 통과, 이메일 중복확인 통과했을 때
                 // USER_INFO 테이블에 정보 저장
                    
                    User user = new User(id, password, name, email);
                    
                    int result = dao.createUserAccount(user);
                    
                    if (result == 1) {
                        JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
                        dao.createGroup(id, "기본");  // 모든 유저에게 "기본" 단어그룹 생성
                        cl.first(panels);  // 로그인 창으로
                        clearLogInPanel();
                    } else {
                        JOptionPane.showMessageDialog(this, "가입 실패");
                    }
                    
                    
                }
            }
        }
        
    }

    private void findPassword() {
        
        String id = inputIDFind.getText();
        
        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.", null, JOptionPane.WARNING_MESSAGE);
            inputIDFind.requestFocus();
        } else if (
                // id가 COL_USER_ID의 어느 값과도 일치하지 않을 때
                dao.selectUserID(id) == false
                ) {
            JOptionPane.showMessageDialog(this, "일치하는 아이디가 없습니다.", null, JOptionPane.ERROR_MESSAGE);
            inputIDFind.setText("");
        } else {
            // 해당 COL_USER_ID의 이메일 리턴
            String email = dao.selectEmailByUserID(id);
            String[] emailChars = email.split("");
            String[] emailDomain = email.split("@");
            JOptionPane.showMessageDialog(this, 
                    emailChars[0] + emailChars[1] + "*****@" + emailDomain[1] + " (으)로\n비밀번호가 전송되었습니다."
                    );
            
            cl.first(panels);   // 로그인 창으로
            clearLogInPanel();
        }
            
        
    }

    private void logIn() {
        
        String id = inputIDLogIn.getText();
        String password = inputPasswordLogIn.getText();
        String dbPassword = dao.selectPassword(id);
        
        if (id.equals("")) {
            JOptionPane.showMessageDialog(this, "아이디를 입력해 주세요.", null, JOptionPane.WARNING_MESSAGE);
            inputIDLogIn.requestFocus();
        } else if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "비밀번호를 입력해 주세요.", null, JOptionPane.WARNING_MESSAGE);
            inputPasswordLogIn.requestFocus();
        } else if (
                // id가 COL_USER_ID의 어느 값과도 일치하지 않을 때
                dao.selectUserID(id) == false
            ) {
            JOptionPane.showMessageDialog(this, "일치하는 아이디가 없습니다.", null, JOptionPane.ERROR_MESSAGE);
            inputIDLogIn.setText("");
            inputPasswordLogIn.setText("");
        } else if (
                // password가 해당 COL_USER_ID의 COL_PASSWORD와 일치하지 않을 때
                !(password.equals(dbPassword))
                ) {
            JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", null, JOptionPane.ERROR_MESSAGE);
            inputPasswordLogIn.setText("");
        } else {
            
            JOptionPane.showMessageDialog(this, "로그인 성공");
            
            // 현재창 닫고 MemorizeMain 불러오기, 
            // 메인에 해당 user와 일치하는 결과 띄우기
            dispose();
            MemorizeTable.newMemorizeTable(id);
            
        }
        
    }
}
