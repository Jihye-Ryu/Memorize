package project.java.memorize.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import project.java.memorize.controller.MemorizeDaoImpl;
import project.java.memorize.model.User;

public class MemorizeUserInfoFrame extends JFrame {
    
    public interface UserUpdateListener {
        void userUpdateNotify(String name);
    }
    
    public interface DisposeListener {
        void disposeNotify();
    }
    
    private UserUpdateListener updateListener;
    private DisposeListener disposeListener;

    private Component parent;
    private String userID;
    private MemorizeDaoImpl dao;
    private JPasswordField inputNewPassWordConfirm;
    private JTextField showName;
    private JTextField showEmail;
    private JPasswordField inputPassword;
    private JTextField showID;
    private JButton btnUpdate;
    private JButton btnLogOut;
    private JButton btnDelete;
    private JLabel lblCheckEmailResult;
    private JLabel lblCheckPasswordResult;
    private JLabel lblCheckNewPasswordConfirm;
    private JLabel lblCheckNameResult;
    private JPasswordField inputNewPassword;
    private JLabel lblCheckNewPasswordResult;

    /**
     * Launch the application.
     */
    public static void newMemorizeUserInfoFrame(
            Component parent, String userID, 
            UserUpdateListener updateListener, DisposeListener disposeListener) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeUserInfoFrame frame = new MemorizeUserInfoFrame(
                            parent, userID, updateListener, disposeListener);
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
    public MemorizeUserInfoFrame(
            Component parent, String userID, 
            UserUpdateListener updateListener, DisposeListener disposeListener) {
        this.parent = parent;
        this.userID = userID;
        this.updateListener = updateListener;
        this.disposeListener = disposeListener;
        this.dao = MemorizeDaoImpl.getInstance();
        initialize();
        initializeUserInfo();
    }

    private void initializeUserInfo() {
    
        User user = dao.selectUserInfo(userID);
        if (user != null) {
            showID.setText(user.getUserId());
            showName.setText(user.getName());
            showEmail.setText(user.getEmail());
        }
        
    }

    private void initialize() {
        
        setTitle("회원 정보");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(730, 250, 527, 637);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel lblUserInfo = new JLabel("회원 정보");
        lblUserInfo.setOpaque(true);
        lblUserInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserInfo.setForeground(Color.WHITE);
        lblUserInfo.setFont(new Font("맑은 고딕", Font.BOLD, 40));
        lblUserInfo.setBorder(null);
        lblUserInfo.setBackground(new Color(255, 165, 0));
        lblUserInfo.setBounds(126, 22, 253, 64);
        contentPane.add(lblUserInfo);
        
        JLabel lblID = new JLabel("아이디");
        lblID.setHorizontalAlignment(SwingConstants.CENTER);
        lblID.setForeground(Color.WHITE);
        lblID.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblID.setBounds(143, 123, 50, 21);
        contentPane.add(lblID);
        
        JLabel lblPassword = new JLabel("비밀번호");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblPassword.setBounds(109, 167, 84, 21);
        contentPane.add(lblPassword);
        
        JLabel lblName = new JLabel("닉네임");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblName.setBounds(143, 321, 50, 21);
        contentPane.add(lblName);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblEmail.setBounds(143, 374, 50, 21);
        contentPane.add(lblEmail);
        
        JLabel lblNewPasswordConfirm = new JLabel("새 비밀번호 확인");
        lblNewPasswordConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewPasswordConfirm.setForeground(Color.WHITE);
        lblNewPasswordConfirm.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewPasswordConfirm.setBounds(78, 269, 115, 21);
        contentPane.add(lblNewPasswordConfirm);
        
        showID = new JTextField();
        showID.setEditable(false);
        showID.setForeground(new Color(255, 140, 0));
        showID.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        showID.setColumns(10);
        showID.setBounds(207, 120, 136, 27);
        contentPane.add(showID);
        
        inputPassword = new JPasswordField();
        inputPassword.setForeground(new Color(255, 140, 0));
        inputPassword.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputPassword.setBounds(207, 166, 136, 27);
        contentPane.add(inputPassword);
        
        inputNewPassWordConfirm = new JPasswordField();
        inputNewPassWordConfirm.setForeground(new Color(255, 140, 0));
        inputNewPassWordConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputNewPassWordConfirm.setBounds(207, 269, 136, 27);
        contentPane.add(inputNewPassWordConfirm);
        
        showName = new JTextField();
        showName.setForeground(new Color(255, 140, 0));
        showName.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        showName.setColumns(10);
        showName.setBounds(207, 321, 136, 27);
        contentPane.add(showName);
        
        showEmail = new JTextField();
        showEmail.setForeground(new Color(255, 140, 0));
        showEmail.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        showEmail.setColumns(10);
        showEmail.setBounds(207, 374, 136, 27);
        contentPane.add(showEmail);
        
        lblCheckEmailResult = new JLabel("이메일은 비밀번호 찾기에 사용됩니다.");
        lblCheckEmailResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        lblCheckEmailResult.setBounds(207, 401, 214, 21);
        contentPane.add(lblCheckEmailResult);
        lblCheckEmailResult.setVisible(true);
        
        lblCheckPasswordResult = new JLabel("");
        lblCheckPasswordResult.setForeground(new Color(255, 0, 0));
        lblCheckPasswordResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckPasswordResult.setBounds(207, 191, 214, 21);
        contentPane.add(lblCheckPasswordResult);
        lblCheckPasswordResult.setVisible(true);
        
        lblCheckNewPasswordConfirm = new JLabel("");
        lblCheckNewPasswordConfirm.setForeground(new Color(255, 255, 240));
        lblCheckNewPasswordConfirm.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckNewPasswordConfirm.setBounds(207, 295, 214, 21);
        contentPane.add(lblCheckNewPasswordConfirm);
        lblCheckNewPasswordConfirm.setVisible(true);
        
        lblCheckNameResult = new JLabel("");
        lblCheckNameResult.setForeground(new Color(255, 0, 0));
        lblCheckNameResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckNameResult.setBounds(207, 347, 214, 21);
        contentPane.add(lblCheckNameResult);
        lblCheckNameResult.setVisible(true);
        
        btnUpdate = new JButton("수정");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                updateUserInfo();
                
            }
        });
        btnUpdate.setBounds(149, 460, 91, 37);
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnUpdate.setBorder(new LineBorder(new Color(255, 235, 205), 2, true));
        btnUpdate.setBackground(new Color(255, 140, 0));
        contentPane.add(btnUpdate);
        
        JButton btnDispose = new JButton("돌아가기");
        btnDispose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
            }
        });
        btnDispose.setForeground(Color.WHITE);
        btnDispose.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnDispose.setBorder(new LineBorder(new Color(255, 235, 205), 2, true));
        btnDispose.setBackground(new Color(255, 140, 0));
        btnDispose.setBounds(288, 460, 91, 37);
        contentPane.add(btnDispose);
        
        btnLogOut = new JButton("로그아웃");
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                logOut();
                
            }
        });
        btnLogOut.setBounds(402, 542, 84, 21);
        btnLogOut.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnLogOut.setForeground(new Color(255, 255, 255));
        btnLogOut.setBackground(new Color(255, 165, 0));
        btnLogOut.setBorder(null);
        contentPane.add(btnLogOut);
        
        btnDelete = new JButton("탈퇴");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                deleteUserAccount();
                
            }
        });
        btnDelete.setBounds(30, 542, 45, 21);
        btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setBackground(new Color(255, 165, 0));
        btnDelete.setBorder(null);
        contentPane.add(btnDelete);
        
        JLabel lblNewPassword = new JLabel("새 비밀번호");
        lblNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewPassword.setForeground(Color.WHITE);
        lblNewPassword.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        lblNewPassword.setBounds(109, 221, 84, 21);
        contentPane.add(lblNewPassword);
        
        inputNewPassword = new JPasswordField();
        inputNewPassword.setForeground(new Color(255, 140, 0));
        inputNewPassword.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        inputNewPassword.setBounds(207, 218, 136, 27);
        contentPane.add(inputNewPassword);
        
        lblCheckNewPasswordResult = new JLabel("");
        lblCheckNewPasswordResult.setForeground(new Color(255, 0, 0));
        lblCheckNewPasswordResult.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        lblCheckNewPasswordResult.setBounds(207, 245, 214, 21);
        contentPane.add(lblCheckNewPasswordResult);
        lblCheckNewPasswordResult.setVisible(true);
        
    }

    private void deleteUserAccount() {
        
        User user = dao.selectUserInfo(userID);
        
        String originalPassword = user.getPassword();
        String password = inputPassword.getText();
        
        if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "탈퇴하려면 비밀번호를 입력해야 합니다.", null, JOptionPane.WARNING_MESSAGE);
            inputPassword.requestFocus();
            return;
        } else if (!(password.equals(originalPassword))) {
            JOptionPane.showMessageDialog(this, "비밀번호가 일치하지 않습니다.", null,JOptionPane.ERROR_MESSAGE);
            return;
        } else {
        
        int confirm = JOptionPane.showConfirmDialog(
                this, "탈퇴하면 계정과 단어들을 복구할 수 없습니다.\n정말 탈퇴하시겠습니까?", 
                null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            
            int result = dao.deleteUserAccount(userID);
            
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "계정이 삭제되었습니다.");
                dispose();
                disposeListener.disposeNotify();
                MemorizeMain.main(null);
            } else {
                JOptionPane.showMessageDialog(this, "계정을 삭제할 수 없습니다.", null, JOptionPane.ERROR_MESSAGE);
            }
            
        } if (confirm == JOptionPane.NO_OPTION) {
            return;
        }
    }
        
    }

    private void logOut() {
        
        int confirm = JOptionPane.showConfirmDialog(this, "로그아웃하시겠습니까?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            
            JOptionPane.showMessageDialog(this, "로그아웃되었습니다.");
            dispose();
            disposeListener.disposeNotify();
            MemorizeMain.main(null);
            
        } if (confirm == JOptionPane.NO_OPTION) {
            return;
        }
        
        
        
    }

    private void updateUserInfo() {
        
        User user = dao.selectUserInfo(userID);
        
        String id = user.getUserId();
        String originalPassword = user.getPassword();
        String originalName = user.getName();
        String originalEmail = user.getEmail();
        
        String password = inputPassword.getText();
        String newPassword = inputNewPassword.getText();
        String newPasswordConfirm = inputNewPassWordConfirm.getText();
        String name = showName.getText();
        String email = showEmail.getText();
        
        lblCheckPasswordResult.setVisible(false);
        lblCheckNewPasswordResult.setVisible(false);
        lblCheckNewPasswordConfirm.setVisible(false);
        lblCheckNameResult.setVisible(false);
        lblCheckEmailResult.setText("이메일은 비밀번호 찾기에 사용됩니다.");
        lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        
        if (password.equals("")) {
            lblCheckPasswordResult.setText("비밀번호를 입력하세요.");
            lblCheckPasswordResult.setForeground(new Color(255,0,0));
            lblCheckPasswordResult.setVisible(true);
            inputPassword.requestFocus();
        } else if (!(password.equals(originalPassword))) {
            lblCheckPasswordResult.setText("비밀번호가 일치하지 않습니다.");
            lblCheckPasswordResult.setForeground(new Color(255,0,0));
            lblCheckPasswordResult.setVisible(true);
        } else if (!(newPassword.equals(""))
                && newPassword.length() < 4 ) {
            lblCheckNewPasswordResult.setText("비밀번호는 4자리 이상이어야 합니다.");
            lblCheckNewPasswordResult.setForeground(new Color(255,0,0));
            lblCheckNewPasswordResult.setVisible(true);
        } else if (!(newPassword.equals(""))
                && newPasswordConfirm.equals("")) {
            lblCheckNewPasswordConfirm.setText("비밀번호를 다시 한 번 입력하세요.");
            lblCheckNewPasswordConfirm.setForeground(new Color(255,0,0));
            lblCheckNewPasswordConfirm.setVisible(true);
            inputNewPassWordConfirm.requestFocus();
        } else if (!(newPassword.equals(newPasswordConfirm))) {
            lblCheckNewPasswordConfirm.setText("비밀번호가 일치하지 않습니다.");
            lblCheckNewPasswordConfirm.setForeground(new Color(255,0,0));
            lblCheckNewPasswordConfirm.setVisible(true);
        } else {
            
            if (name.equals("")) {
                lblCheckNameResult.setText("닉네임을 입력하세요.");
                lblCheckNameResult.setForeground(new Color(255,0,0));
                lblCheckNameResult.setVisible(true);
                showName.requestFocus();
            } else if (email.equals("")) {
                lblCheckEmailResult.setText("이메일을 입력하세요.");
                lblCheckEmailResult.setForeground(new Color(255,0,0));
                showEmail.requestFocus();
            } else if (!(email.equals(originalEmail))
                    && dao.selectEmail(email) == true
                    ) {
                lblCheckEmailResult.setText("중복된 이메일입니다.");
                lblCheckEmailResult.setForeground(new Color(255,0,0));
            } else {
                
                lblCheckEmailResult.setText("사용 가능한 이메일입니다.");
                lblCheckEmailResult.setForeground(new Color(255,255,240));
                
                int confirm = JOptionPane.showConfirmDialog(this, "회원정보가 변경되었습니다.\n수정하시겠습니까?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    
                    
                    if (newPassword.equals("")) {
                        
                        User updatedUser = new User(id, password, name, email);
                        int result = dao.updateUserInfo(updatedUser);
                        
                        if (result == 1) {
                            JOptionPane.showMessageDialog(this, "수정되었습니다.");
                            dispose();
                            updateListener.userUpdateNotify(name);
                        } else {
                            JOptionPane.showMessageDialog(this, "수정 실패");
                        }
                        
                    } else {
                        
                        User updatedUser = new User(id, newPassword, name, email);
                        int result = dao.updateUserInfo(updatedUser);
                        
                        if (result == 1) {
                            JOptionPane.showMessageDialog(this, "수정되었습니다.");
                            dispose();
                            updateListener.userUpdateNotify(name);
                        } else {
                            JOptionPane.showMessageDialog(this, "수정 실패");
                        }
                        
                    }
                    
                    
                } else if (confirm == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "취소되었습니다.");
                    return;
                }
                
            }
        }
        
    }
}
