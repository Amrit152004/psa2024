package com.crm.payload;

import java.util.Date;

public class ErrorDetails {
    private Date date;
    private String message;

    public ErrorDetails(Date date,String message) {
        this.date = date;
        this.message = message;
    }

    public ErrorDetails(Date date) {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
