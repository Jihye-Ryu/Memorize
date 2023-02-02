package project.java.memorize.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleDriver;
import project.java.memorize.model.Group;
import project.java.memorize.model.User;
import project.java.memorize.model.Word;

import static project.java.memorize.model.User.Entity.*;
import static project.java.memorize.model.Word.Entity.*;
import static project.java.memorize.model.Group.Entity.*;
import static project.java.memorize.OracleConnection.*;
import static project.java.memorize.controller.MemorizeSql.*;

public class MemorizeDaoImpl implements MemorizeDao {
    
    // Singleton
    private static MemorizeDaoImpl instance = null;
    private MemorizeDaoImpl() {}
    public static MemorizeDaoImpl getInstance() {
        if (instance == null) {
            instance = new MemorizeDaoImpl();
        }
        return instance;
    }
    
    private Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new OracleDriver());
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    private void closeResources(Connection conn, Statement stmt) throws SQLException {
        stmt.close();
        conn.close();
    }
    
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) throws SQLException {
        rs.close();
        closeResources(conn, stmt);
    }
    
    
    
    
    
    @Override
    public boolean selectUserID(String userID) {
        
        boolean result = false;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_CHECK_ID);
            stmt.setString(1, userID);
            
            rs = stmt.executeQuery();
            
            result = rs.next();
            
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return result;
    }

    @Override
    public boolean selectEmail(String email) {
        boolean result = false;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_CHECK_EMAIL);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            
            result = rs.next();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return result;
    }

    @Override
    public String selectPassword(String userID) {
        
        String password = null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_CHECK_PASSWORD);
            stmt.setString(1, userID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                password = rs.getString(COL_PASSWORD);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return password;
    }

    @Override
    public String selectEmailByUserID(String userID) {
        
        String email = null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_EMAIL);
            stmt.setString(1, userID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                email = rs.getString(COL_EMAIL);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return email;
    }

    @Override
    public int createUserAccount(User user) {
        
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_CREATE_USER);
            
            stmt.setString(1,user.getUserId());
            stmt.setString(2,user.getPassword());
            stmt.setString(3,user.getName());
            stmt.setString(4,user.getEmail());
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    
    @Override
    public User selectUserInfo(String userID) {
        
        User user = null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_USER_INFO);
            stmt.setString(1, userID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                String id = rs.getString(COL_USER_ID);
                String password = rs.getString(COL_PASSWORD);
                String name = rs.getString(COL_NAME);
                String email = rs.getString(COL_EMAIL);
                
                user = new User(id, password, name, email);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return user;
    }

    

    @Override
    public int updateUserInfo(User user) {
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_USER);
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getName());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getUserId());
            result = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }

    @Override
    public int deleteUserAccount(String userID) {
    
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_USER);
            stmt.setString(1, userID);
            result = stmt.executeUpdate();
            closeResources(conn, stmt);
            
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_USER_WORDS);
            stmt.setString(1, userID);
            stmt.executeUpdate();
            closeResources(conn, stmt);
            
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_USER_GROUPS);
            stmt.setString(1, userID);
            stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }

    @Override
    public List<Word> readAll(String userID) {
        
        List<Word> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_ALL);
            stmt.setString(1, userID);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer wordID = rs.getInt(COL_WORD_ID);
                String uID = rs.getString(COL_USER_ID);
                String word = rs.getString(COL_WORD);
                String meaning = rs.getString(COL_MEANING);
                String explanation = rs.getString(COL_EXPLANATION);
                String group = rs.getString(COL_GROUPS);
                String level = rs.getString(COL_LEVELS);
                LocalDateTime created = rs.getTimestamp(COL_CREATED_DATE).toLocalDateTime();
                String quiz = rs.getString(COL_QUIZ);
                
                Word showWord = new Word(wordID, uID, word, meaning, explanation, group, level, created, quiz);
                list.add(showWord);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }

    @Override
    public Word selectByWordID(Integer wordID) {
        
        Word word = null;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_WORD_ID);
            stmt.setInt(1, wordID);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                
                Integer wordId = rs.getInt(COL_WORD_ID);
                String userId = rs.getString(COL_USER_ID);
                String showWord = rs.getString(COL_WORD);
                String meaning = rs.getString(COL_MEANING);
                String explanation = rs.getString(COL_EXPLANATION);
                String group = rs.getString(COL_GROUPS);
                String level = rs.getString(COL_LEVELS);
                LocalDateTime created = rs.getTimestamp(COL_CREATED_DATE).toLocalDateTime();
                String quiz = rs.getString(COL_QUIZ);
                
                word = new Word(wordId, userId, showWord, meaning, explanation, group, level, created, quiz);
                
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return word;
    }

    @Override
    public int createWord(String userID, Word word) {
        
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_CREATE_WORD);
            
            stmt.setString(1, userID);
            stmt.setString(2, word.getWord());
            stmt.setString(3, word.getMeaning());
            stmt.setString(4, word.getExplanation());
            stmt.setString(5, word.getGroup());
            stmt.setString(6, word.getLevel());
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int updateWord(Word word) {
        
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE_WORD);
            
            stmt.setString(1, word.getWord());
            stmt.setString(2, word.getMeaning());
            stmt.setString(3, word.getExplanation());
            stmt.setString(4, word.getGroup());
            stmt.setString(5, word.getLevel());
            stmt.setString(6, word.getQuiz());
            stmt.setInt(7, word.getWordID());
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }

    @Override
    public int deleteWord(Integer wordID) {
        
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_WORD);
            stmt.setInt(1, wordID);
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return result;
    }
    
    
    
    @Override
    public List<Integer> selectWordIDAll(String userID) {
        
        
        List<Integer> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_WORD_ID_ALL);
            stmt.setString(1, userID);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer wordID = rs.getInt(COL_WORD_ID);
                list.add(wordID);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    
    
    
    @Override
    public List<Word> select(String id, String group, String level) {
        
        List<Word> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            
            if (group.equals("전체")) {
                
                if (level.equals("전체")) {
                    stmt = conn.prepareStatement(SQL_SELECT_ALL);  // 그룹:전체, 레벨:전체
                    stmt.setString(1, id);
                } else {
                    stmt = conn.prepareStatement(SQL_SELECT_BY_LEVEL);  // 그룹:전체, 레벨:선택한 값
                    stmt.setString(1, id);
                    stmt.setString(2, level);
                }
            } else if (level.equals("전체")) {
                stmt = conn.prepareStatement(SQL_SELECT_BY_GROUP);    // 그룹: 선택한 값, 레벨: 전체
                stmt.setString(1, id);
                stmt.setString(2, group);
            } else {
                
                stmt = conn.prepareStatement(SQL_SELECT_BY_GROUP_AND_LEVEL);  // 그룹레벨 모두 선택한 값
                stmt.setString(1, id);
                stmt.setString(2, group);
                stmt.setString(3, level);
            }
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer wordID = rs.getInt(COL_WORD_ID);
                String uID = rs.getString(COL_USER_ID);
                String word = rs.getString(COL_WORD);
                String meaning = rs.getString(COL_MEANING);
                String explanation = rs.getString(COL_EXPLANATION);
                String groups = rs.getString(COL_GROUPS);
                String levels = rs.getString(COL_LEVELS);
                LocalDateTime created = rs.getTimestamp(COL_CREATED_DATE).toLocalDateTime();
                String quiz = rs.getString(COL_QUIZ);
                
                Word selectedWord = new Word(wordID, uID, word, meaning, explanation, groups, levels, created, quiz);
                list.add(selectedWord);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        
        return list;
    }
    
    
    
    
    @Override
    public List<Word> select(String id, String keyword) {
        
        List<Word> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            
            stmt = conn.prepareStatement(SQL_SELECT_BY_KEYWORD);
            stmt.setString(1, id);
            stmt.setString(2, "%" + keyword.toLowerCase() + "%");
            stmt.setString(3, "%" + keyword.toLowerCase() + "%");
            stmt.setString(4, "%" + keyword.toLowerCase() + "%");
            
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Integer wordID = rs.getInt(COL_WORD_ID);
                String uID = rs.getString(COL_USER_ID);
                String word = rs.getString(COL_WORD);
                String meaning = rs.getString(COL_MEANING);
                String explanation = rs.getString(COL_EXPLANATION);
                String groups = rs.getString(COL_GROUPS);
                String levels = rs.getString(COL_LEVELS);
                LocalDateTime created = rs.getTimestamp(COL_CREATED_DATE).toLocalDateTime();
                String quiz = rs.getString(COL_QUIZ);
                
                Word selectedWord = new Word(wordID, uID, word, meaning, explanation, groups, levels, created, quiz);
                list.add(selectedWord);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    @Override
    public List<Group> selectAll(String userID) {
        
        List<Group> list = new ArrayList<>();
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_GROUP_NAMES);
            stmt.setString(1, userID);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                 Integer groupId = rs.getInt(COL_GROUP_ID);
                String groupName = rs.getString(COL_GROUPS);
                String userId = rs.getString(COL_USER_ID);
                
                Group newGroup = new Group(groupId, groupName, userId);
                list.add(newGroup);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt, rs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return list;
    }
    @Override
    public int createGroup(String userID, String group) {
        
        
        int result = 0;
        
        Connection conn = null;
        PreparedStatement stmt = null;
        
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(SQL_CREATE_GROUP);
            
            stmt.setString(1, group);
            stmt.setString(2, userID);
            
            result = stmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                closeResources(conn, stmt);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    
}
