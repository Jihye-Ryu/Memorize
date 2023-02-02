package project.java.memorize.controller;

import java.util.List;

import project.java.memorize.model.User;
import project.java.memorize.model.Word;
import project.java.memorize.model.Group;

public interface MemorizeDao {
    
    boolean selectUserID(String userID);   // SQL_CHECK_ID
    boolean selectEmail(String email);  // SQL_CHECK_EMAIL
    String selectPassword(String userID);  // SQL_CHECK_PASSWORD
    String selectEmailByUserID(String userID);  // SQL_SELECT_EMAIL
    User selectUserInfo(String userID);   // SQL_SELECT_USER_INFO
    
    int createUserAccount(User user);    // SQL_USER_SIGNUP
    int updateUserInfo(User user);    // SQL_USER_UPDATE
    int deleteUserAccount(String userID);  // SQL_USER_DELETE
    
    
    
    
    List<Word> readAll(String userID);   // SQL_SELECT_ALL
    List<Integer> selectWordIDAll(String userID);  // SQL_SELECT_WORD_ID_ALL
    Word selectByWordID(Integer wordID);  // SQL_SELECT_BY_WORD_ID
    
    int createWord(String userID, Word word);  // SQL_CREATE_WORD
    int updateWord(Word word);  // SQL_UPDATE_WORD
    int deleteWord(Integer wordID);   // SQL_DELETE_WORD
    
    List<Word> select(String userID, String group, String level);  // 그룹,레벨로 정렬
    List<Word> select(String userID, String keyword);   // SQL_SELECT_BY_KEYWORD
    
    
    List<Group> selectAll(String userID);  // 그룹명 모두 불러오기
    int createGroup(String userID, String group);  // 새 그룹 추가

}
   