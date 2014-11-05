package com.maxxindev.studia.Model;

import java.util.Date;

/**
 * Class of Classes
 */
public class Class
{
    private Date time;
    private String salon;
    private Date day;

    public Class(Date time, String salon, Date day) {
        this.time = time;
        this.salon = salon;
        this.day = day;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
}
