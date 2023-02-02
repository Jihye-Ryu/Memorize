package project.java.memorize.model;

import java.time.LocalDateTime;

public class Word {
    
    public interface Entity {
        String TBL_WORDS = "WORDS";
        String COL_WORD_ID = "WORD_ID";  // 단어 고유번호
        String COL_USER_ID_WORDS = "WORDS.USER_ID";  // 사용자 아이디
        String COL_WORD = "WORD";        // 단어
        String COL_MEANING = "MEANING";  // 뜻
        String COL_EXPLANATION = "EXPLANATION";  // 설명(예문, 유의어 등)
        String COL_GROUPS = "GROUPS";  // 그룹
        String COL_LEVELS = "LEVELS";  // 난이도 
        String COL_CREATED_DATE = "CREATED_DATE";  // 단어 추가 날짜
        String COL_QUIZ = "QUIZ";  // 퀴즈 맞춤 여부
    }
    
    private Integer wordID;
    private String userID;
    private String word;
    private String meaning;
    private String explanation;
    private String group;
    private String level;
    private LocalDateTime createdDate;
    private String quiz;
    
    public Word() {}

    public Word(Integer wordID, String userID, String word, String meaning, String explanation, String group,
            String level, LocalDateTime createdDate, String quiz) {
        this.wordID = wordID;
        this.userID = userID;
        this.word = word;
        this.meaning = meaning;
        this.explanation = explanation;
        this.group = group;
        this.level = level;
        this.createdDate = createdDate;
        this.quiz = quiz;
    }

    public Integer getWordID() {
        return wordID;
    }

    public String getUserID() {
        return userID;
    }

    public String getWord() {
        return word;
    }

    public String getMeaning() {
        return meaning;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getGroup() {
        return group;
    }

    public String getLevel() {
        return level;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public String getQuiz() {
        return quiz;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Word(wordID=%d, userID=%s, word=%s, meaning=%s, group=%s, level=%s, createdDate=%s, quiz=%s)", 
                wordID, userID, word, meaning, group, level, createdDate, quiz);
    }

}
