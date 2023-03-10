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
        
        setTitle("?????? ??????");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(730, 250, 527, 637);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 165, 0));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JLabel lblUserInfo = new JLabel("?????? ??????");
        lblUserInfo.setOpaque(true);
        lblUserInfo.setHorizontalAlignment(SwingConstants.CENTER);
        lblUserInfo.setForeground(Color.WHITE);
        lblUserInfo.setFont(new Font("?????? ??????", Font.BOLD, 40));
        lblUserInfo.setBorder(null);
        lblUserInfo.setBackground(new Color(255, 165, 0));
        lblUserInfo.setBounds(126, 22, 253, 64);
        contentPane.add(lblUserInfo);
        
        JLabel lblID = new JLabel("?????????");
        lblID.setHorizontalAlignment(SwingConstants.CENTER);
        lblID.setForeground(Color.WHITE);
        lblID.setFont(new Font("?????? ??????", Font.BOLD, 15));
        lblID.setBounds(143, 123, 50, 21);
        contentPane.add(lblID);
        
        JLabel lblPassword = new JLabel("????????????");
        lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("?????? ??????", Font.BOLD, 15));
        lblPassword.setBounds(109, 167, 84, 21);
        contentPane.add(lblPassword);
        
        JLabel lblName = new JLabel("?????????");
        lblName.setHorizontalAlignment(SwingConstants.CENTER);
        lblName.setForeground(Color.WHITE);
        lblName.setFont(new Font("?????? ??????", Font.BOLD, 15));
        lblName.setBounds(143, 321, 50, 21);
        contentPane.add(lblName);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("?????? ??????", Font.BOLD, 15));
        lblEmail.setBounds(143, 374, 50, 21);
        contentPane.add(lblEmail);
        
        JLabel lblNewPasswordConfirm = new JLabel("??? ???????????? ??????");
        lblNewPasswordConfirm.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewPasswordConfirm.setForeground(Color.WHITE);
        lblNewPasswordConfirm.setFont(new Font("?????? ??????", Font.BOLD, 15));
        lblNewPasswordConfirm.setBounds(78, 269, 115, 21);
        contentPane.add(lblNewPasswordConfirm);
        
        showID = new JTextField();
        showID.setEditable(false);
        showID.setForeground(new Color(255, 140, 0));
        showID.setFont(new Font("?????? ??????", Font.PLAIN, 15));
        showID.setColumns(10);
        showID.setBounds(207, 120, 136, 27);
        contentPane.add(showID);
        
        inputPassword = new JPasswordField();
        inputPassword.setForeground(new Color(255, 140, 0));
        inputPassword.setFont(new Font("?????? ??????", Font.PLAIN, 15));
        inputPassword.setBounds(207, 166, 136, 27);
        contentPane.add(inputPassword);
        
        inputNewPassWordConfirm = new JPasswordField();
        inputNewPassWordConfirm.setForeground(new Color(255, 140, 0));
        inputNewPassWordConfirm.setFont(new Font("?????? ??????", Font.PLAIN, 15));
        inputNewPassWordConfirm.setBounds(207, 269, 136, 27);
        contentPane.add(inputNewPassWordConfirm);
        
        showName = new JTextField();
        showName.setForeground(new Color(255, 140, 0));
        showName.setFont(new Font("?????? ??????", Font.PLAIN, 15));
        showName.setColumns(10);
        showName.setBounds(207, 321, 136, 27);
        contentPane.add(showName);
        
        showEmail = new JTextField();
        showEmail.setForeground(new Color(255, 140, 0));
        showEmail.setFont(new Font("?????? ??????", Font.PLAIN, 15));
        showEmail.setColumns(10);
        showEmail.setBounds(207, 374, 136, 27);
        contentPane.add(showEmail);
        
        lblCheckEmailResult = new JLabel("???????????? ???????????? ????????? ???????????????.");
        lblCheckEmailResult.setFont(new Font("?????? ??????", Font.PLAIN, 12));
        lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        lblCheckEmailResult.setBounds(207, 401, 214, 21);
        contentPane.add(lblCheckEmailResult);
        lblCheckEmailResult.setVisible(true);
        
        lblCheckPasswordResult = new JLabel("");
        lblCheckPasswordResult.setForeground(new Color(255, 0, 0));
        lblCheckPasswordResult.setFont(new Font("?????? ??????", Font.PLAIN, 12));
        lblCheckPasswordResult.setBounds(207, 191, 214, 21);
        contentPane.add(lblCheckPasswordResult);
        lblCheckPasswordResult.setVisible(true);
        
        lblCheckNewPasswordConfirm = new JLabel("");
        lblCheckNewPasswordConfirm.setForeground(new Color(255, 255, 240));
        lblCheckNewPasswordConfirm.setFont(new Font("?????? ??????", Font.PLAIN, 12));
        lblCheckNewPasswordConfirm.setBounds(207, 295, 214, 21);
        contentPane.add(lblCheckNewPasswordConfirm);
        lblCheckNewPasswordConfirm.setVisible(true);
        
        lblCheckNameResult = new JLabel("");
        lblCheckNameResult.setForeground(new Color(255, 0, 0));
        lblCheckNameResult.setFont(new Font("?????? ??????", Font.PLAIN, 12));
        lblCheckNameResult.setBounds(207, 347, 214, 21);
        contentPane.add(lblCheckNameResult);
        lblCheckNameResult.setVisible(true);
        
        btnUpdate = new JButton("??????");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                updateUserInfo();
                
            }
        });
        btnUpdate.setBounds(149, 460, 91, 37);
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setFont(new Font("?????? ??????", Font.BOLD, 15));
        btnUpdate.setBorder(new LineBorder(new Color(255, 235, 205), 2, true));
        btnUpdate.setBackground(new Color(255, 140, 0));
        contentPane.add(btnUpdate);
        
        JButton btnDispose = new JButton("????????????");
        btnDispose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
            }
        });
        btnDispose.setForeground(Color.WHITE);
        btnDispose.setFont(new Font("?????? ??????", Font.BOLD, 15));
        btnDispose.setBorder(new LineBorder(new Color(255, 235, 205), 2, true));
        btnDispose.setBackground(new Color(255, 140, 0));
        btnDispose.setBounds(288, 460, 91, 37);
        contentPane.add(btnDispose);
        
        btnLogOut = new JButton("????????????");
        btnLogOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                logOut();
                
            }
        });
        btnLogOut.setBounds(402, 542, 84, 21);
        btnLogOut.setFont(new Font("?????? ??????", Font.BOLD, 15));
        btnLogOut.setForeground(new Color(255, 255, 255));
        btnLogOut.setBackground(new Color(255, 165, 0));
        btnLogOut.setBorder(null);
        contentPane.add(btnLogOut);
        
        btnDelete = new JButton("??????");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                deleteUserAccount();
                
            }
        });
        btnDelete.setBounds(30, 542, 45, 21);
        btnDelete.setFont(new Font("?????? ??????", Font.BOLD, 15));
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setBackground(new Color(255, 165, 0));
        btnDelete.setBorder(null);
        contentPane.add(btnDelete);
        
        JLabel lblNewPassword = new JLabel("??? ????????????");
        lblNewPassword.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNewPassword.setForeground(Color.WHITE);
        lblNewPassword.setFont(new Font("?????? ??????", Font.BOLD, 15));
        lblNewPassword.setBounds(109, 221, 84, 21);
        contentPane.add(lblNewPassword);
        
        inputNewPassword = new JPasswordField();
        inputNewPassword.setForeground(new Color(255, 140, 0));
        inputNewPassword.setFont(new Font("?????? ??????", Font.PLAIN, 15));
        inputNewPassword.setBounds(207, 218, 136, 27);
        contentPane.add(inputNewPassword);
        
        lblCheckNewPasswordResult = new JLabel("");
        lblCheckNewPasswordResult.setForeground(new Color(255, 0, 0));
        lblCheckNewPasswordResult.setFont(new Font("?????? ??????", Font.PLAIN, 12));
        lblCheckNewPasswordResult.setBounds(207, 245, 214, 21);
        contentPane.add(lblCheckNewPasswordResult);
        lblCheckNewPasswordResult.setVisible(true);
        
    }

    private void deleteUserAccount() {
        
        User user = dao.selectUserInfo(userID);
        
        String originalPassword = user.getPassword();
        String password = inputPassword.getText();
        
        if (password.equals("")) {
            JOptionPane.showMessageDialog(this, "??????????????? ??????????????? ???????????? ?????????.", null, JOptionPane.WARNING_MESSAGE);
            inputPassword.requestFocus();
            return;
        } else if (!(password.equals(originalPassword))) {
            JOptionPane.showMessageDialog(this, "??????????????? ???????????? ????????????.", null,JOptionPane.ERROR_MESSAGE);
            return;
        } else {
        
        int confirm = JOptionPane.showConfirmDialog(
                this, "???????????? ????????? ???????????? ????????? ??? ????????????.\n?????? ?????????????????????????", 
                null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            
            int result = dao.deleteUserAccount(userID);
            
            if (result == 1) {
                JOptionPane.showMessageDialog(this, "????????? ?????????????????????.");
                dispose();
                disposeListener.disposeNotify();
                MemorizeMain.main(null);
            } else {
                JOptionPane.showMessageDialog(this, "????????? ????????? ??? ????????????.", null, JOptionPane.ERROR_MESSAGE);
            }
            
        } if (confirm == JOptionPane.NO_OPTION) {
            return;
        }
    }
        
    }

    private void logOut() {
        
        int confirm = JOptionPane.showConfirmDialog(this, "???????????????????????????????", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        
        if (confirm == JOptionPane.YES_OPTION) {
            
            JOptionPane.showMessageDialog(this, "???????????????????????????.");
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
        lblCheckEmailResult.setText("???????????? ???????????? ????????? ???????????????.");
        lblCheckEmailResult.setForeground(new Color(255, 255, 240));
        
        if (password.equals("")) {
            lblCheckPasswordResult.setText("??????????????? ???????????????.");
            lblCheckPasswordResult.setForeground(new Color(255,0,0));
            lblCheckPasswordResult.setVisible(true);
            inputPassword.requestFocus();
        } else if (!(password.equals(originalPassword))) {
            lblCheckPasswordResult.setText("??????????????? ???????????? ????????????.");
            lblCheckPasswordResult.setForeground(new Color(255,0,0));
            lblCheckPasswordResult.setVisible(true);
        } else if (!(newPassword.equals(""))
                && newPassword.length() < 4 ) {
            lblCheckNewPasswordResult.setText("??????????????? 4?????? ??????????????? ?????????.");
            lblCheckNewPasswordResult.setForeground(new Color(255,0,0));
            lblCheckNewPasswordResult.setVisible(true);
        } else if (!(newPassword.equals(""))
                && newPasswordConfirm.equals("")) {
            lblCheckNewPasswordConfirm.setText("??????????????? ?????? ??? ??? ???????????????.");
            lblCheckNewPasswordConfirm.setForeground(new Color(255,0,0));
            lblCheckNewPasswordConfirm.setVisible(true);
            inputNewPassWordConfirm.requestFocus();
        } else if (!(newPassword.equals(newPasswordConfirm))) {
            lblCheckNewPasswordConfirm.setText("??????????????? ???????????? ????????????.");
            lblCheckNewPasswordConfirm.setForeground(new Color(255,0,0));
            lblCheckNewPasswordConfirm.setVisible(true);
        } else {
            
            if (name.equals("")) {
                lblCheckNameResult.setText("???????????? ???????????????.");
                lblCheckNameResult.setForeground(new Color(255,0,0));
                lblCheckNameResult.setVisible(true);
                showName.requestFocus();
            } else if (email.equals("")) {
                lblCheckEmailResult.setText("???????????? ???????????????.");
                lblCheckEmailResult.setForeground(new Color(255,0,0));
                showEmail.requestFocus();
            } else if (!(email.equals(originalEmail))
                    && dao.selectEmail(email) == true
                    ) {
                lblCheckEmailResult.setText("????????? ??????????????????.");
                lblCheckEmailResult.setForeground(new Color(255,0,0));
            } else {
                
                lblCheckEmailResult.setText("?????? ????????? ??????????????????.");
                lblCheckEmailResult.setForeground(new Color(255,255,240));
                
                int confirm = JOptionPane.showConfirmDialog(this, "??????????????? ?????????????????????.\n?????????????????????????", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    
                    
                    if (newPassword.equals("")) {
                        
                        User updatedUser = new User(id, password, name, email);
                        int result = dao.updateUserInfo(updatedUser);
                        
                        if (result == 1) {
                            JOptionPane.showMessageDialog(this, "?????????????????????.");
                            dispose();
                            updateListener.userUpdateNotify(name);
                        } else {
                            JOptionPane.showMessageDialog(this, "?????? ??????");
                        }
                        
                    } else {
                        
                        User updatedUser = new User(id, newPassword, name, email);
                        int result = dao.updateUserInfo(updatedUser);
                        
                        if (result == 1) {
                            JOptionPane.showMessageDialog(this, "?????????????????????.");
                            dispose();
                            updateListener.userUpdateNotify(name);
                        } else {
                            JOptionPane.showMessageDialog(this, "?????? ??????");
                        }
                        
                    }
                    
                    
                } else if (confirm == JOptionPane.NO_OPTION) {
                    JOptionPane.showMessageDialog(this, "?????????????????????.");
                    return;
                }
                
            }
        }
        
    }
}
