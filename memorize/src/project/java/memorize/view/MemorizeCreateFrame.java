package project.java.memorize.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.LineBorder;

import project.java.memorize.controller.MemorizeDaoImpl;
import project.java.memorize.model.Group;
import project.java.memorize.model.Word;

import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import project.java.memorize.view.MemorizeNewGroupFrame.GroupCreateListener;

public class MemorizeCreateFrame extends JFrame implements GroupCreateListener {
    
    @FunctionalInterface
    public interface WordInsertListener {
        void wordInsertNotify();
    }
    
    private Component parent;
    private WordInsertListener wordInsertListener;
    private GroupCreateListener groupCreateListener;
    private MemorizeDaoImpl dao;
    private String userID;
    private List<Group> list;

    private JPanel contentPane;
    private JTextField inputWord;
    private JTextField inputMeaning;
    private JTextArea inputExplanation;
    private JComboBox<String> comboBoxGroup;
    private JComboBox<String> comboBoxLevel;
    private JButton btnCreateNewGroup;
    private JButton btnCreate;
    private JButton btnCancel;
    
    private DefaultComboBoxModel<String> comboBoxGroupModel;

    /**
     * Launch the application.
     */
    public static void newMemorizeCreateFrame(Component parent, String id,
            WordInsertListener wordInsertListener, GroupCreateListener groupCreateListener) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeCreateFrame frame = new MemorizeCreateFrame(parent, id, wordInsertListener, groupCreateListener);
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
    public MemorizeCreateFrame(Component parent, String id,
            WordInsertListener wordInsertListener, GroupCreateListener groupCreateListener) {
        this.parent = parent;
        this.userID = id;
        this.wordInsertListener = wordInsertListener;
        this.groupCreateListener = groupCreateListener;
        this.dao = MemorizeDaoImpl.getInstance();
        initialize();
        
    }

    private void initialize() {
        
        setTitle("단어 추가");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(730, 250, 527, 637);

        JPanel contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 239, 213));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 239, 213));
        panel.setBounds(62, 10, 379, 563);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblWord = new JLabel("단어");
        lblWord.setHorizontalAlignment(SwingConstants.CENTER);
        lblWord.setBackground(new Color(255, 165, 0));
        lblWord.setOpaque(true);
        lblWord.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lblWord.setForeground(new Color(255, 255, 255));
        lblWord.setBounds(23, 30, 65, 34);
        panel.add(lblWord);
        
        JLabel lblMeaning = new JLabel("뜻");
        lblMeaning.setOpaque(true);
        lblMeaning.setHorizontalAlignment(SwingConstants.CENTER);
        lblMeaning.setForeground(Color.WHITE);
        lblMeaning.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lblMeaning.setBackground(new Color(255, 165, 0));
        lblMeaning.setBounds(23, 87, 65, 34);
        panel.add(lblMeaning);
        
        JLabel lblExplanation = new JLabel("설명");
        lblExplanation.setOpaque(true);
        lblExplanation.setHorizontalAlignment(SwingConstants.CENTER);
        lblExplanation.setForeground(Color.WHITE);
        lblExplanation.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lblExplanation.setBackground(new Color(255, 165, 0));
        lblExplanation.setBounds(23, 143, 65, 34);
        panel.add(lblExplanation);
        
        JLabel lblGroup = new JLabel("그룹");
        lblGroup.setOpaque(true);
        lblGroup.setHorizontalAlignment(SwingConstants.CENTER);
        lblGroup.setForeground(Color.WHITE);
        lblGroup.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lblGroup.setBackground(new Color(255, 165, 0));
        lblGroup.setBounds(23, 345, 65, 34);
        panel.add(lblGroup);
        
        JLabel lblLevel = new JLabel("난이도");
        lblLevel.setOpaque(true);
        lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
        lblLevel.setForeground(Color.WHITE);
        lblLevel.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        lblLevel.setBackground(new Color(255, 165, 0));
        lblLevel.setBounds(23, 401, 65, 34);
        panel.add(lblLevel);
        
        inputWord = new JTextField();
        inputWord.setForeground(new Color(255, 165, 0));
        inputWord.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        inputWord.setBounds(109, 30, 225, 34);
        panel.add(inputWord);
        
        inputMeaning = new JTextField();
        inputMeaning.setForeground(new Color(255, 165, 0));
        inputMeaning.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        inputMeaning.setColumns(10);
        inputMeaning.setBounds(109, 87, 225, 34);
        panel.add(inputMeaning);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(109, 143, 225, 185);
        panel.add(scrollPane);
        
        inputExplanation = new JTextArea();
        inputExplanation.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        inputExplanation.setForeground(new Color(255, 165, 0));
        scrollPane.setViewportView(inputExplanation);
        
        btnCreate = new JButton("추가");
        btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                insert();
                
            }
        });
        btnCreate.setBorder(new LineBorder(new Color(255, 140, 0), 3, true));
        btnCreate.setFocusPainted(false);
        btnCreate.setBackground(new Color(255, 165, 0));
        btnCreate.setForeground(new Color(255, 255, 255));
        btnCreate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        btnCreate.setBounds(88, 471, 108, 54);
        panel.add(btnCreate);
        
        btnCancel = new JButton("나가기");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                cancel();
                
            }
        });
        btnCancel.setForeground(Color.WHITE);
        btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        btnCancel.setFocusPainted(false);
        btnCancel.setBorder(new LineBorder(new Color(255, 140, 0), 3, true));
        btnCancel.setBackground(new Color(255, 165, 0));
        btnCancel.setBounds(219, 471, 108, 54);
        panel.add(btnCancel);
        
        comboBoxGroup = new JComboBox<>();
        comboBoxGroup.setBounds(109, 345, 87, 34);
        comboBoxGroup.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        
        initializeComboBoxGroup();
        
        comboBoxGroup.setSelectedIndex(0);
        panel.add(comboBoxGroup);
        
        comboBoxLevel = new JComboBox<>();
        comboBoxLevel.setBounds(109, 401, 87, 34);
        comboBoxLevel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
        
        String[] comboBoxLevelItems = {"어려움", "보통", "쉬움"};
        DefaultComboBoxModel<String> comboBoxLevelModel = new DefaultComboBoxModel<>(comboBoxLevelItems);
        comboBoxLevel.setModel(comboBoxLevelModel);
        comboBoxLevel.setSelectedIndex(1);
        panel.add(comboBoxLevel);
        
        btnCreateNewGroup = new JButton("새 그룹");
        btnCreateNewGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MemorizeNewGroupFrame.newMemorizeNewGroupFrame(MemorizeCreateFrame.this,userID, MemorizeCreateFrame.this);
                
            }
        });
        btnCreateNewGroup.setBorder(new LineBorder(new Color(255, 140, 0), 3, true));
        btnCreateNewGroup.setFont(new Font("맑은 고딕", Font.BOLD, 15));
        btnCreateNewGroup.setForeground(new Color(255, 255, 255));
        btnCreateNewGroup.setBackground(new Color(255, 165, 0));
        btnCreateNewGroup.setBounds(247, 345, 87, 34);
        panel.add(btnCreateNewGroup);
        
    }

    private void initializeComboBoxGroup() {
        
        List<String> groupNames = new ArrayList<>();
        
        list = dao.selectAll(userID);
        for (Group g : list) {
            groupNames.add(g.getGroup());
        }
        
        // ArrayList를 배열로 변환       
        String[] comboBoxGroupItems = groupNames.toArray(new String[groupNames.size()]);   
        DefaultComboBoxModel<String> comboBoxGroupModel = new DefaultComboBoxModel<>(comboBoxGroupItems);
        comboBoxGroup.setModel(comboBoxGroupModel);
        
        
        
    }

    private void cancel() {
        
        String word = inputWord.getText();
        String meaning = inputMeaning.getText();
        String explanation = inputExplanation.getText();
        
        if (word.equals("") && meaning.equals("") && explanation.equals("")) {
            dispose();
        } else {
            
            int confirm = JOptionPane.showConfirmDialog(this, "입력된 내용이 저장되지 않습니다.\n정말 취소하시겠습니까?", null, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            } else if (confirm == JOptionPane.NO_OPTION) {
                return;
            }
            
        }
        
    }

    private void insert() {
        
        String word = inputWord.getText();
        String meaning = inputMeaning.getText();
        String explanation = inputExplanation.getText();
        String group = (String) comboBoxGroup.getSelectedItem();
        String level = (String) comboBoxLevel.getSelectedItem();
        
        if (word.equals("")) {
            JOptionPane.showMessageDialog(this, "단어를 입력하세요.", null, JOptionPane.WARNING_MESSAGE);
            inputWord.requestFocus();
            return;
        } else if (meaning.equals("")) {
            JOptionPane.showMessageDialog(this, "뜻을 입력하세요.", null, JOptionPane.WARNING_MESSAGE);
            inputMeaning.requestFocus();
            return;
        } else {
            
            Word newWord = new Word(null, userID, word, meaning, explanation, group, level, null, null);
            
            int result = dao.createWord(userID, newWord);
            
            if (result == 1) {
                
                JOptionPane.showMessageDialog(this, "단어를 추가했습니다.");
                wordInsertListener.wordInsertNotify();
                inputWord.setText("");
                inputMeaning.setText("");
                inputExplanation.setText("");
                comboBoxGroup.setSelectedIndex(0);
                comboBoxLevel.setSelectedIndex(1);
                return;
            } else {
                JOptionPane.showMessageDialog(this, "단어를 추가하지 못했습니다.", null, JOptionPane.ERROR_MESSAGE);
                return;
            }
            
        }
        
    }

    @Override
    public void groupCreateNotify(String group) {
        
        initializeComboBoxGroup();
        comboBoxGroup.setSelectedItem(group);
        groupCreateListener.groupCreateNotify(group);
        
    }
}
