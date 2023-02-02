package project.java.memorize.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.java.memorize.model.Word;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class MemorizeStudyFrame extends JFrame {
    
    private CardLayout cl;  // 패널 전환 객체

    private JPanel panel;
    private JLabel lblWord;
    private JLabel lblMeaning;
    private JTextArea showExplanation;
    private List<Word> list;
    
    
    private JPanel[] cards;
    private JLabel[] labelsWord;
    private JButton[] btnAnswer;
    private JLabel[] labelsMeaning;
    private JScrollPane[] scrolls;
    private JTextArea[] explanations;

    /**
     * Launch the application.
     */
    public static void newMemorizeStudyFrame(List<Word> list) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeStudyFrame frame = new MemorizeStudyFrame(list);
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
    public MemorizeStudyFrame(List<Word> list) {
        this.list = list;
        
        Random random = new Random();
        
        int size = list.size();
        
        
        setTitle("단어 학습");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(730, 250, 527, 637);
        panel = new JPanel();
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                
                int i = random.nextInt(size);
                String index = ""+i;
                
//                System.out.println(index);
                
                cl.show(panel, index);
                
                for (JLabel label : labelsMeaning) {
                    label.setVisible(false);
                }
                
                for (JScrollPane scroll : scrolls) {
                    scroll.setVisible(false);
                }
                
                for (JTextArea explanation : explanations) {
                    explanation.setVisible(false);
                }
                
                
            }
        });

        setContentPane(panel);
        panel.setLayout(new CardLayout(0, 0));
        
        cl = (CardLayout) panel.getLayout();
        
        cards = new JPanel[size];
        labelsWord = new JLabel[size];
        btnAnswer = new JButton[size];
        labelsMeaning = new JLabel[size];
        scrolls = new JScrollPane[size];
        explanations = new JTextArea[size];
        
        
        
        
        for (int i = 0; i < size; i++) {
            
            cards[i] = new JPanel();
            
            cards[i].setBackground(new Color(255, 165, 0));
            cards[i].setLayout(null);
            
            labelsWord[i] = new JLabel(""+i);
            labelsWord[i].setHorizontalAlignment(SwingConstants.CENTER);
            labelsWord[i].setForeground(new Color(255, 255, 255));
            labelsWord[i].setFont(new Font("맑은 고딕", Font.BOLD, 45));
            labelsWord[i].setBounds(123, 55, 254, 80);
            
            btnAnswer[i] = new JButton("?");
            btnAnswer[i].setForeground(new Color(255,0,0));
            btnAnswer[i].setBackground(new Color(255, 165, 0));
            btnAnswer[i].setBorder(null);
            btnAnswer[i].setHorizontalAlignment(SwingConstants.CENTER);
            btnAnswer[i].setFont(new Font("맑은 고딕", Font.BOLD, 45));
            btnAnswer[i].setBounds(200, 200, 100, 100);
            btnAnswer[i].setFocusPainted(false);
            
            labelsMeaning[i] = new JLabel("");
            labelsMeaning[i].setForeground(new Color(238, 232, 170));
            labelsMeaning[i].setHorizontalAlignment(SwingConstants.CENTER);
            labelsMeaning[i].setFont(new Font("맑은 고딕", Font.BOLD, 45));
            labelsMeaning[i].setBounds(57, 168, 395, 89);
            labelsMeaning[i].setVisible(false);
            
            
            scrolls[i] = new JScrollPane();
            scrolls[i].setBorder(null);
            scrolls[i].setBackground(new Color(255, 165, 0));
            scrolls[i].setForeground(new Color(0,0,0));
            scrolls[i].setBounds(114, 295, 286, 216);
            scrolls[i].setVisible(false);
            
            explanations[i] = new JTextArea("");
            explanations[i].setSelectionColor(new Color(255, 165, 0));
            explanations[i].setForeground(new Color(238, 232, 170));
            explanations[i].setFont(new Font("맑은 고딕", Font.PLAIN, 25));
            explanations[i].setBackground(new Color(255, 165, 0));
            explanations[i].setEditable(false);
            explanations[i].setVisible(false);
            
            scrolls[i].setViewportView(explanations[i]);
            
            
            cards[i].add(labelsWord[i]);
            cards[i].add(labelsMeaning[i]);
            cards[i].add(scrolls[i]);
            cards[i].add(btnAnswer[i]);
            
        
            labelsWord[i].setText(list.get(i).getWord());
            labelsMeaning[i].setText(list.get(i).getMeaning());
            explanations[i].setText(list.get(i).getExplanation());
            
            panel.add(cards[i], ""+i);
            
        }
        
        
        for (JButton button : btnAnswer) {
            
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    
                    for (JLabel meaning : labelsMeaning) {
                        meaning.setVisible(true);
                        
                    }
                    
                    for (JScrollPane scroll : scrolls) {
                        scroll.setVisible(true);
                    }
                    
                    for (JTextArea explanation : explanations) {
                        explanation.setVisible(true);
                    }
                    
                    button.setVisible(false);
                    
                }
            });
        }
        
        
        
        
    }

    
}
