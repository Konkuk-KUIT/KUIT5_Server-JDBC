package jwp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(Long questionId, String writer, String title, String  contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public  Question(String writer, String title, String  contents, Timestamp createdDate){
        this(-1L, writer, title, contents, createdDate, 0);
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void increaseCountOfAnswer() {
        countOfAnswer ++;
    }

    public void decreaseCountOfAnswer() {
        if(countOfAnswer > 0){
            countOfAnswer--;
        }
    }
}
