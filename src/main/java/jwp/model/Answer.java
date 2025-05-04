package jwp.model;

import java.sql.Date;

public class Answer {
    private int answerId;
    private String writer;
    private String content;
    private Date createdDate;
    private int questionId;

    public Answer(String writer, String content, Date createdDate, int questionId) {
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Answer(int answerId, String writer, String content, Date createdDate, int questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public int getAnswerId() {
        return answerId;
    }
}
