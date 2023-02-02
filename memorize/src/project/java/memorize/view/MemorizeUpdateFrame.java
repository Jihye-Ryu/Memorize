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
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import project.java.memorize.view.MemorizeNewGroupFrame.GroupCreateListener;

public class MemorizeUpdateFrame extends JFrame implements GroupCreateListener {
    
    @FunctionalInterface
    public interface WordUpdateListener {
        void wordUpdateNotify();
    }

    private Component parent;
    private WordUpdateListener wordUpdateListener;
    private GroupCreateListener groupCreateListener;
    private MemorizeDaoImpl dao;
    private Integer id;
    private String userId;
    private List<Group> list;

    private JPanel contentPane;
    private JTextField inputWord;
    private JTextField inputMeaning;
    private JTextArea inputExplanation;
    private JComboBox<String> comboBoxGroup;
    private JComboBox<String> comboBoxLevel;
    private JButton btnCreateNewGroup;
    private JButton btnUpdate;
    private JButton btnCancel;

    /**
     * Launch the application.
     */
    public static void newMemorizeUpdateFrame(Component parent, Integer id, String userId,
            WordUpdateListener wordUpdateListener, GroupCreateListener groupCreateListener) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeUpdateFrame frame = new MemorizeUpdateFrame(parent, id, userId, wordUpdateListener, groupCreateListener);
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
    public MemorizeUpdateFrame(Component parent, Integer id, String userId,
            WordUpdateListener wordUpdateListener, GroupCreateListener groupCreateListener) {
        this.parent = parent;
        this.id = id;
        this.userId = userId;
        this.wordUpdateListener = wordUpdateListener;
        this.groupCreateListener = groupCreateListener;
        this.dao = MemorizeDaoImpl.getInstance();
        initialize();
        initializeWordData();
    }

    private void initializeWordData() {
        
        Word word = dao.selectByWordID(id);
        if (word != null) {
            inputWord.setText(word.getWord());
            inputMeaning.setText(word.getMeaning());
            inputExplanation.setText(word.getExplanation());
            comboBoxGroup.setSelectedItem(word.getGroup());
            comboBoxLevel.setSelectedItem(word.getLevel());
        }
        
    }

    private void initialize() {

        setTitle("단어 수정");
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
        
        btnUpdate = new JButton("저장");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                update();
                
            }
        });
        btnUpdate.setBorder(new LineBorder(new Color(255, 140, 0), 3, true));
        btnUpdate.setFocusPainted(false);
        btnUpdate.setBackground(new Color(255, 165, 0));
        btnUpdate.setForeground(new Color(255, 255, 255));
        btnUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 25));
        btnUpdate.setBounds(88, 471, 108, 54);
        panel.add(btnUpdate);
        
        btnCancel = new JButton("취소");
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
                
                MemorizeNewGroupFrame.newMemorizeNewGroupFrame(MemorizeUpdateFrame.this, userId, MemorizeUpdateFrame.this);
                
                
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
        
        list = dao.selectAll(userId);
        for (Group g : list) {
            groupNames.add(g.getGroup());
        }
        
        // ArrayList를 배열로 변환       
        String[] comboBoxGroupItems = groupNames.toArray(new String[groupNames.size()]);   
        DefaultComboBoxModel<String> comboBoxGroupModel = new DefaultComboBoxModel<>(comboBoxGroupItems);
        comboBoxGroup.setModel(comboBoxGroupModel);
        
    }

    private void update() {
        
        Word original = dao.selectByWordID(id);
        
        String userId = original.getUserID();
        String originalWord = original.getWord();
        String originalMeaning = original.getMeaning();
        String originalExplanation = original.getExplanation();
        String originalGroup = original.getGroup();
        String originalLevel = original.getLevel();
        LocalDateTime created = original.getCreatedDate();
        String quiz = original.getQuiz();
        
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
        } else if (word.equals(originalWord) && meaning.equals(originalMeaning) 
                && explanation.equals(originalExplanation) 
                && group.equals(originalGroup) && level.equals(originalLevel)) {
            
            dispose();
            
        } else {
            
            int confirm = JOptionPane.showConfirmDialog(this, "단어 정보가 변경되었습니다.\n수정하시겠습니까?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                
            Word updatedWord = new Word(id, userId, word, meaning, explanation, group, level, created, quiz);
            int result = dao.updateWord(updatedWord);
            
            if (result == 1) {
                
                JOptionPane.showMessageDialog(this, "수정되었습니다.");
                dispose();
                wordUpdateListener.wordUpdateNotify();
            } else {
                JOptionPane.showMessageDialog(this, "수정 실패");
            }
            
        } else if (confirm == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "취소되었습니다.");
                return;
        }
    }
}

    private void cancel() {
        
        Word original = dao.selectByWordID(id);
        
        String originalWord = original.getWord();
        String originalMeaning = original.getMeaning();
        String originalExplanation = original.getExplanation();
        String originalGroup = original.getGroup();
        String originalLevel = original.getLevel();
        
        String word = inputWord.getText();
        String meaning = inputMeaning.getText();
        String explanation = inputExplanation.getText();
        String group = (String) comboBoxGroup.getSelectedItem();
        String level = (String) comboBoxLevel.getSelectedItem();
        
        if (word.equals("") && meaning.equals("") && explanation.equals("")) {
            dispose();
        }  else if (word.equals(originalWord) && meaning.equals(originalMeaning) 
                && explanation.equals(originalExplanation) && group.equals(originalGroup) && level.equals(originalLevel)) {
            dispose();
        }  else {
            
//            System.out.println(
//                    originalWord + ", " + originalMeaning + ", " + originalExplanation + ", " + originalGroup + ", " + originalLevel
//                    + "\n" +
//                    word + ", " + meaning + ", " + explanation + ", " + group + ", " + level
//                    
//                    );
            
            int confirm = JOptionPane.showConfirmDialog(this, "변경된 내용이 저장되지 않습니다.\n정말 취소하시겠습니까?", null, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
            } else if (confirm == JOptionPane.NO_OPTION) {
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
