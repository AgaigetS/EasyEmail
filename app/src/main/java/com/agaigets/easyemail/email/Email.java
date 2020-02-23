package com.agaigets.easyemail.email;

import java.util.Date;

public class Email {
    private String from;
    private String to;
    private Date receivedDate;
    private Date sendDate;
    private Object content;
    private String subject;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Email(String from, String to, Date receivedDate, Date sendDate, Object content, String subject) {
        this.from = from;
        this.to = to;
        this.receivedDate = receivedDate;
        this.sendDate = sendDate;
        this.content = content;
        this.subject = subject;
    }
}
