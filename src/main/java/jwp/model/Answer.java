package jwp.model;

import java.sql.Timestamp;

public class Answer {
    private Long answerId;
    private Long questionId;
    private String writer;
    private String contents;
    private Timestamp createdDate;

    public Answer(Long answerId, Long questionId, String writer, String contents, Timestamp createdDate) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
    }

    public Long getAnswerId() { return answerId; }
    public Long getQuestionId() { return questionId; }
    public String getWriter() { return writer; }
    public String getContents() { return contents; }
    public Timestamp getCreatedDate() { return createdDate; }
}
