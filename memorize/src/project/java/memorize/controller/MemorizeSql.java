package project.java.memorize.controller;

import static project.java.memorize.model.User.Entity.*;
import static project.java.memorize.model.Word.Entity.*;
import static project.java.memorize.model.Group.Entity.*;

public interface MemorizeSql {
    
    // 아이디 존재 여부 체크
    String SQL_CHECK_ID = String.format(
            "select %s from %s where %s = ?", 
            COL_USER_ID_USER_INFO, TBL_USER_INFO, COL_USER_ID_USER_INFO);
    
    // 가입창 이메일 중복체크
    String SQL_CHECK_EMAIL = String.format(
            "select %s from %s where %s = ?", 
            COL_EMAIL, TBL_USER_INFO, COL_EMAIL);
    
    // 로그인창 비밀번호 체크
    String SQL_CHECK_PASSWORD = String.format(
            "select %s from %s where %s = ?", 
            COL_PASSWORD, TBL_USER_INFO, COL_USER_ID_USER_INFO);
    
    // 아이디로 비번찾기(이메일 리턴)
    String SQL_SELECT_EMAIL = String.format(
            "select %s from %s where %s = ?", 
            COL_EMAIL, TBL_USER_INFO, COL_USER_ID_USER_INFO);
    
    // 회원정보 저장(가입)
    String SQL_CREATE_USER = String.format(
            "insert into %s (%s, %s, %s, %s) values (?, ?, ?, ?)", 
            TBL_USER_INFO, COL_USER_ID_USER_INFO, COL_PASSWORD, COL_NAME, COL_EMAIL);
    
    // 회원정보 보기
    String SQL_USER_INFO = String.format(
            "select * from %s where %s = ?", 
            TBL_USER_INFO, COL_USER_ID_USER_INFO);
    
    // 회원정보 수정(비밀번호, 닉네임, 이메일)
    String SQL_UPDATE_USER = String.format(
            "update %s set %s = ?, %s = ?, %s = ? where %s = ?", 
            TBL_USER_INFO, COL_PASSWORD, COL_NAME, COL_EMAIL, COL_USER_ID_USER_INFO);
    
    // 회원정보 삭제(탈퇴)
    String SQL_DELETE_USER = String.format(
            "delete from %s where %s = ?", 
            TBL_USER_INFO, COL_USER_ID_USER_INFO);
    
    // 회원의 모든 단어장 삭제
    String SQL_DELETE_USER_WORDS = String.format(
            "delete from %s where %s = ?", 
            TBL_WORDS, COL_USER_ID);
    
    // 회원의 모든 단어그룹 삭제
    String SQL_DELETE_USER_GROUPS = String.format(
            "delete from %s where %s = ?", 
            TBL_GROUPNAMES, COL_USER_ID);
    
    
    
    
    // 단어장(Words 테이블) 관련은 모두 해당 계정 것만 보이도록! 주의!!
    
    // 계정별 단어장 전체 보이기
    String SQL_SELECT_ALL = String.format(
            "select * from %s where %s = ? order by %s desc", 
            TBL_WORDS, COL_USER_ID_WORDS, COL_CREATED_DATE);
    
    // 계정별 모든 단어들의 id 보이기
    String SQL_SELECT_WORD_ID_ALL = String.format(
            "select %s from %s where %s = ?", 
            COL_WORD_ID, TBL_WORDS, COL_USER_ID);
    
    // 단어 추가
    String SQL_CREATE_WORD = String.format(
            "insert into %s (%s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?)", 
            TBL_WORDS, COL_USER_ID, COL_WORD, COL_MEANING, COL_EXPLANATION,
            COL_GROUPS, COL_LEVELS);
    
    // 단어 수정
    String SQL_UPDATE_WORD = String.format(
            "update %s set %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? where %s = ?", 
            TBL_WORDS, 
            COL_WORD, COL_MEANING, COL_EXPLANATION, COL_GROUPS, COL_LEVELS, COL_QUIZ, 
            COL_WORD_ID);
    
    // 단어 삭제
    String SQL_DELETE_WORD = String.format(
            "delete from %s where %s = ?", 
            TBL_WORDS, COL_WORD_ID);
    
    // 단어id로 단어 찾기
    String SQL_SELECT_BY_WORD_ID = String.format(
            "select * from %s where %s = ?", 
            TBL_WORDS, COL_WORD_ID);
    
    // 그룹으로 찾기
    String SQL_SELECT_BY_GROUP = String.format(
            "select * from %s where %s = ? and %s = ? order by %s desc", 
            TBL_WORDS, COL_USER_ID, COL_GROUPS, COL_CREATED_DATE);
    
    // 레벨로 찾기
    String SQL_SELECT_BY_LEVEL = String.format(
            "select * from %s where %s = ? and %s = ? order by %s desc", 
            TBL_WORDS, COL_USER_ID, COL_LEVELS, COL_CREATED_DATE);
    
    // 그룹, 레벨로 찾기
    String SQL_SELECT_BY_GROUP_AND_LEVEL = String.format(
            "select * from %s where %s = ? and %s = ? and %s = ? order by %s desc", 
            TBL_WORDS, COL_USER_ID, COL_GROUPS, COL_LEVELS, COL_CREATED_DATE);
    
    // 검색어(keyword)로 찾기
    String SQL_SELECT_BY_KEYWORD = String.format(
            "select * from %s where %s = ? and (lower(%s) like ? or lower(%s) like ? or lower(%s) like ?) order by %s desc", 
            TBL_WORDS, COL_USER_ID, COL_WORD, COL_MEANING, COL_EXPLANATION, COL_CREATED_DATE);
    
    
    
    
    // 그룹명 리스트 불러오기
    String SQL_SELECT_GROUP_NAMES = String.format(
            "select * from %s where %s = ? order by %s", 
            TBL_GROUPNAMES, COL_USER_ID, COL_GROUP_ID);
    
    
    // 새 그룹 추가
    String SQL_CREATE_GROUP = String.format(
            "insert into %s (%s, %s) values (?, ?)", 
            TBL_GROUPNAMES, COL_GROUPS, COL_USER_ID);
    

}
