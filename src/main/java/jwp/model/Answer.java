package jwp.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/*
CREATE TABLE ANSWERS (
                         answerId 			bigint				auto_increment,
                         writer				varchar(30)			NOT NULL,
                         contents			varchar(5000)		NOT NULL,
                         createdDate			timestamp			NOT NULL,
                         questionId			bigint				NOT NULL,
                         PRIMARY KEY         (answerId)
);

 */
public class Answer {
    private long answerId;
    private String writer;
    private String contents;
    private LocalDateTime createdDate;
    private long questionId;

    public Answer(long answerId, String writer, String contents, LocalDateTime createdDate, long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return Timestamp.valueOf(createdDate);
    }

    public long getQuestionId() {
        return questionId;
    }
}
