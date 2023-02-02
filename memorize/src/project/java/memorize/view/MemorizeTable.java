package project.java.memorize.view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Point;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import project.java.memorize.controller.MemorizeDaoImpl;
import project.java.memorize.model.Group;
import project.java.memorize.model.User;
import project.java.memorize.model.User.Entity.*;
import project.java.memorize.model.Word;
import project.java.memorize.model.Word.Entity.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import project.java.memorize.view.MemorizeUserInfoFrame.UserUpdateListener;
import project.java.memorize.view.MemorizeUserInfoFrame.DisposeListener;
import project.java.memorize.view.MemorizeCreateFrame.WordInsertListener;
import project.java.memorize.view.MemorizeUpdateFrame.WordUpdateListener;
import project.java.memorize.view.MemorizeNewGroupFrame.GroupCreateListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Component;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class MemorizeTable extends JFrame implements 
    UserUpdateListener, DisposeListener, WordInsertListener, WordUpdateListener, ItemListener, GroupCreateListener {
    
    private static final String[] COLUMN_NAMES = {"단어", "뜻", "그룹", "난이도"};
    
    private List<Word> list;
    private List<Group> groupList;
    private String name;

    private JFrame frame;
    private JTable table;
    private JButton btnAdd;
    private JButton btnDelete;
    private JButton btnStudy;
    
    private DefaultTableModel model;
    private MemorizeDaoImpl dao;
    private String userID;
    private JButton btnUserInfo;
    private JComboBox<String> comboBoxLevel;
    private JComboBox<String> comboBoxGroup;
    private JTextField inputKeyword;
    private JButton btnSearch;
    private JButton btnSearch_1;
    
    

    /**
     * Launch the application.
     */
    public static void newMemorizeTable(String id) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MemorizeTable frame = new MemorizeTable(id);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MemorizeTable(String id) {
        this.userID = id;
        dao = MemorizeDaoImpl.getInstance();
        
        initialize();  // UI 컴포넌트 생성, 초기화
        
        initializeTable();
        btnUserInfo.setText(dao.selectUserInfo(id).getName());
    }

    private void initializeTable() {
        
        model = new DefaultTableModel(null, COLUMN_NAMES) {
            
            // 행 더블클릭해도 해당 셀 수정모드로 들어가지 않도록
            @Override 
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table.setModel(model);
        
        
        list = dao.readAll(userID);
        for (Word w : list) {
            Object[] row = {
                    w.getWord(),
                    w.getMeaning(),
                    w.getGroup(),
                    w.getLevel()
            };
            model.addRow(row);
        }
        
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
//        frame = new JFrame();
        setTitle("MEMORIZE"); //
        getContentPane().setBackground(new Color(255, 255, 255));//
        setBounds(730, 250, 527, 637);//
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //
        getContentPane().setLayout(null); //
        
        JPanel panelButtons1 = new JPanel();
        panelButtons1.setBounds(0, 0, 511, 88);
        panelButtons1.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelButtons1);  //
        
        btnAdd = new JButton("추가");
        btnAdd.setBounds(245, 27, 78, 35);
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MemorizeCreateFrame.newMemorizeCreateFrame(frame, userID, MemorizeTable.this, MemorizeTable.this);
                
            }
        });
        panelButtons1.setLayout(null);
        btnAdd.setFocusPainted(false);
        btnAdd.setForeground(new Color(255, 255, 255));
        btnAdd.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnAdd.setBackground(new Color(255, 165, 0));
        panelButtons1.add(btnAdd);
        
        btnDelete = new JButton("삭제");
        btnDelete.setBounds(335, 27, 78, 35);
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                delete();
                
            }
        });
        btnDelete.setForeground(new Color(255, 255, 255));
        btnDelete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnDelete.setFocusPainted(false);
        btnDelete.setBackground(new Color(255, 165, 0));
        panelButtons1.add(btnDelete);
        
        btnUserInfo = new JButton("<dynamic>");
        btnUserInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                MemorizeUserInfoFrame.newMemorizeUserInfoFrame(frame, userID, MemorizeTable.this, MemorizeTable.this);
                
                
            }
        });
        btnUserInfo.setBorder(null);
        btnUserInfo.setForeground(new Color(255, 165, 0));
        btnUserInfo.setFont(new Font("맑은 고딕", Font.BOLD, 18));
        btnUserInfo.setBackground(new Color(255, 255, 255));
        btnUserInfo.setFocusPainted(false);
        btnUserInfo.setBounds(396, 0, 115, 26);
        panelButtons1.add(btnUserInfo);
        
        comboBoxGroup = new JComboBox<>();
        comboBoxGroup.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                Object item = (String) e.getItem();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    if (!(item.equals(""))) {
                        comboBoxLevel.setVisible(true);
                        comboBoxLevel.setSelectedIndex(0);
                    } else {
                        
                        comboBoxLevel.setVisible(false);
                        
                    }
                }
                
            }
        });
        comboBoxGroup.setBounds(25, 27, 67, 23);
        comboBoxGroup.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        
        initializeComboBoxGroup();
        
        comboBoxGroup.setSelectedIndex(0);
        
        comboBoxGroup.addItemListener(this);
        
        panelButtons1.add(comboBoxGroup);
        
        comboBoxLevel = new JComboBox<>();
        comboBoxLevel.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                String item = (String) e.getItem();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    if (!(item.equals(""))) {
                        
                        String group = (String) comboBoxGroup.getSelectedItem();
                        
                        list = dao.select(userID, group, item);
                        
                        
                        // 콘솔창에 결과 출력
//                        for (Word w : list) {
//                            System.out.println(w + "\n");
//                        }
                        
                        
                        model = new DefaultTableModel(null, COLUMN_NAMES) {
                            
                            // 행 더블클릭해도 해당 셀 수정모드로 들어가지 않도록
                            @Override 
                            public boolean isCellEditable(int row, int column) {
                                return false;
                            }
                        };
                        
                        table.setModel(model);
                        
                        for (Word w : list) {
                            Object[] row = {
                                    w.getWord(),
                                    w.getMeaning(),
                                    w.getGroup(),
                                    w.getLevel()
                            };
                            model.addRow(row);
                        }
                    } 
                }
                
            }
        });
        comboBoxLevel.setBounds(25, 58, 67, 23);
        comboBoxLevel.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
        
        String[] comboBoxLevelItems = {"", "전체", "어려움", "보통", "쉬움"};
        DefaultComboBoxModel<String> comboBoxLevelModel = new DefaultComboBoxModel<>(comboBoxLevelItems);
        comboBoxLevel.setModel(comboBoxLevelModel);
        comboBoxLevel.setSelectedIndex(0);
        comboBoxLevel.setVisible(false);
        
        panelButtons1.add(comboBoxLevel);
        
        btnSearch_1 = new JButton("전체보기");
        btnSearch_1.setBounds(107, 27, 126, 35);
        panelButtons1.add(btnSearch_1);
        btnSearch_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                initializeTable();
                inputKeyword.setText("");
                comboBoxGroup.setSelectedIndex(1);
                comboBoxLevel.setSelectedIndex(1);
                
            }
        });
        btnSearch_1.setForeground(Color.WHITE);
        btnSearch_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnSearch_1.setFocusPainted(false);
        btnSearch_1.setBackground(new Color(255, 165, 0));
        
        JPanel panelButtons2 = new JPanel();
        panelButtons2.setBounds(0, 530, 511, 68);
        panelButtons2.setBackground(new Color(255, 255, 255));
        getContentPane().add(panelButtons2); //
        panelButtons2.setLayout(null);
        
        btnSearch = new JButton("검색");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                String keyword = inputKeyword.getText();
                
                if (keyword.equals("")) {
                    JOptionPane.showMessageDialog(MemorizeTable.this, "검색어를 입력하세요", null, JOptionPane.WARNING_MESSAGE);
                    inputKeyword.requestFocus();
                    return;
                } else {
                    list = dao.select(userID, keyword);
                    
                    model = new DefaultTableModel(null, COLUMN_NAMES) {
                        
                        // 행 더블클릭해도 해당 셀 수정모드로 들어가지 않도록
                        @Override 
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    };
                    
                    table.setModel(model);
                    
                    for (Word w : list) {
                        Object[] row = {
                                w.getWord(),
                                w.getMeaning(),
                                w.getGroup(),
                                w.getLevel()
                        };
                        model.addRow(row);
                    }
                    
                    inputKeyword.setText("");
                    comboBoxGroup.setSelectedIndex(0);
                    comboBoxLevel.setSelectedIndex(0);
                } 
                
            }
        });
        btnSearch.setForeground(Color.WHITE);
        btnSearch.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnSearch.setFocusPainted(false);
        btnSearch.setBackground(new Color(255, 165, 0));
        btnSearch.setBounds(265, 10, 78, 35);
        panelButtons2.add(btnSearch);
        
        inputKeyword = new JTextField();
        inputKeyword.setFont(new Font("맑은 고딕", Font.PLAIN, 16));
        inputKeyword.setForeground(new Color(255, 165, 0));
        inputKeyword.setBounds(88, 15, 165, 27);
        panelButtons2.add(inputKeyword);
        inputKeyword.setColumns(10);
        
        btnStudy = new JButton("단어 학습");
        btnStudy.setBounds(355, 10, 126, 35);
        panelButtons2.add(btnStudy);
        btnStudy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                study();
                
            }
        });
        btnStudy.setForeground(new Color(255, 255, 255));
        btnStudy.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        btnStudy.setFocusPainted(false);
        btnStudy.setBackground(new Color(255, 165, 0));
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 89, 511, 441);
        scrollPane.setBackground(new Color(255, 255, 255));
        getContentPane().add(scrollPane);  //
        
        table = new JTable();
        table.setRowHeight(25);
        table.setGridColor(new Color(255, 255, 255));
        table.setFillsViewportHeight(true);
        table.setSelectionForeground(new Color(255, 255, 255));
        table.setSelectionBackground(new Color(255, 165, 0));
        table.setForeground(new Color(255, 165, 0));
        table.setFont(new Font("맑은 고딕", Font.BOLD, 16));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
                // 행 더블클릭시 상세보기(수정)창 띄우기
                
                JTable t = (JTable)e.getSource();
                if(e.getClickCount()==2) {
                    
                    Point pt = e.getPoint();
                    int row = t.rowAtPoint(pt);
                
                if (row != -1) {   // 열이 선택되었다면
                    Integer wordID = list.get(row).getWordID();
                    MemorizeUpdateFrame.newMemorizeUpdateFrame(frame, wordID, userID, MemorizeTable.this, MemorizeTable.this);
              
                }
                    
                }
                
            }
        });
        table.setBackground(new Color(255, 250, 240));
        scrollPane.setViewportView(table);
    }

    private void study() {
        
        if (list.size() < 5) {
            JOptionPane.showMessageDialog(
                    this, "학습할 단어는 5개 이상이어야 합니다.", null, JOptionPane.WARNING_MESSAGE);
        } else {
        
        MemorizeStudyFrame.newMemorizeStudyFrame(list); 
        }
    }

    private void initializeComboBoxGroup() {

        List<String> groupNames = new ArrayList<>();
        groupNames.add("");
        groupNames.add("전체");
        
        groupList = dao.selectAll(userID);
        for (Group g : groupList) {
            groupNames.add(g.getGroup());
        }
        
        // ArrayList를 배열로 변환       
        String[] comboBoxGroupItems = groupNames.toArray(new String[groupNames.size()]);   
        DefaultComboBoxModel<String> comboBoxGroupModel = new DefaultComboBoxModel<>(comboBoxGroupItems);
        comboBoxGroup.setModel(comboBoxGroupModel);
        
    }

    private void delete() {
        
        int row = table.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "삭제할 단어를 먼저 선택하세요.", null, JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "정말 삭제하시겠습니까?", null, JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (confirm == JOptionPane.YES_OPTION) {
                Integer wordID = list.get(row).getWordID();
                int result = dao.deleteWord(wordID);
                if (result == 1) {
                    JOptionPane.showMessageDialog(this, "삭제되었습니다.");
                    initializeTable();
                } else {
                    JOptionPane.showMessageDialog(this, "삭제 실패", null, JOptionPane.ERROR_MESSAGE);
                }
            } else if (confirm == JOptionPane.NO_OPTION) {
                JOptionPane.showMessageDialog(this, "취소했습니다.");
                return;
            }
            
        }
        
    }

    @Override
    public void userUpdateNotify(String name) {
        btnUserInfo.setText(name);
    }
    
    @Override
    public void disposeNotify() {
        dispose();
    }

    @Override
    public void wordInsertNotify() {
        
        initializeTable();
        
    }
    
    @Override
    public void wordUpdateNotify() {
        
        initializeTable();
        
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
    }

    @Override
    public void groupCreateNotify(String group) {
        
        initializeComboBoxGroup();
        
    }
}
