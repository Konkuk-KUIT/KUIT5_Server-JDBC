package jwp.model;

import java.sql.Timestamp;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(Long questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    // 새로운 질문 등록용 (questionId는 auto_increment라 DB가 자동 생성)
    public Question(String writer, String title, String contents) {
        this(null, writer, title, contents, new Timestamp(System.currentTimeMillis()), 0);
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
    public void increaseCountOfAnswer(){
        countOfAnswer++;
    }
}
