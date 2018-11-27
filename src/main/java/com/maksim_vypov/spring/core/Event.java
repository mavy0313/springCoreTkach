package com.maksim_vypov.spring.core;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;

public class Event {
    private DateFormat df;
    private int id = new Random().nextInt();
    private String msg;
    private Date date;

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }
}
