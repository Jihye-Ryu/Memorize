package project.java.memorize.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import project.java.memorize.controller.MemorizeDaoImpl;

public class MemorizeNewGroupFrame extends JFrame {
    
    @FunctionalInterface
    public interface GroupCreateListener {
        void groupCreateNotify(String group);
    }

    private JPanel contentPane;
    private JTextField inputGroupName;
    
    private Component parent;
    private GroupCreateListener listener;
    private MemorizeDaoImpl dao;
    private String id;

    /**
     * Launch the application.
     */
    public static void newMemorizeNewGroupFrame(Component parent, String id, GroupCreateListener listener) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeNewGroupFrame frame = new MemorizeNewGroupFrame(parent, id, listener);
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
    public MemorizeNewGroupFrame(Component parent, String id, GroupCreateListener listener) {
        this.parent = parent;
        this.id = id;
        this.listener = listener;
        this.dao = MemorizeDaoImpl.getInstance();
        
        setTitle("새 그룹 추가");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        int x = parent.getX();
        int y = parent.getY();
        
        setBounds(x+65, y+70, 400, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 239, 213));
        panel.setBounds(0, 0, 384, 461);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblGroup = new JLabel("새 그룹명");
        lblGroup.setForeground(new Color(255, 165, 0));
        lblGroup.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        lblGroup.setBounds(86, 102, 89, 35);
        panel.add(lblGroup);
        
        inputGroupName = new JTextField();
        inputGroupName.setSelectedTextColor(new Color(255, 255, 255));
        inputGroupName.setForeground(new Color(255, 165, 0));
        inputGroupName.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        inputGroupName.setBounds(78, 159, 206, 35);
        panel.add(inputGroupName);
        inputGroupName.setColumns(10);
        
        JButton btnAdd = new JButton("확인");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                addGroup();
                
            }
        });
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnAdd.setBackground(new Color(255, 165, 0));
        btnAdd.setBounds(78, 241, 71, 41);
        panel.add(btnAdd);
        
        JButton btnCancel = new JButton("취소");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                
            }
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnCancel.setBackground(new Color(255, 165, 0));
        btnCancel.setBounds(213, 241, 71, 41);
        panel.add(btnCancel);
    }

    private void addGroup() {
        
        String group = inputGroupName.getText();
        
        if (group.equals("")) {
            JOptionPane.showConfirmDialog(this, "그룹명을 입력하세요.", null, JOptionPane.WARNING_MESSAGE);
            inputGroupName.requestFocus();
            return;
        } else {
            
            JOptionPane.showMessageDialog(this, "새 그룹이 추가되었습니다.");
            dispose();
            dao.createGroup(id, group);
            listener.groupCreateNotify(group);
            
        }
        
    }
}
