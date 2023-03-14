package com.uniovi.sdi2223entrega133.entities;

import javax.persistence.*;
import java.util.*;

@Entity
public class Log {

    @Id
    @GeneratedValue
    private Long id;

    private String logType;


    private Date date;


    private String text;

    public Log(){}
    public Log(String type, Date date, String desc){
        super();
        this.logType = type;
        this.date = date;
        this.text = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
