package com.maxxindev.studia.Model;

import java.util.Date;

/**
 * Class of Homework
 */
public class Homework
{
    private String name;
    private Date hour;
    private String description;
    private Date deliveryDate;

    public Homework(String name, Date hour, String description, Date deliveryDate) {
        this.name = name;
        this.hour = hour;
        this.description = description;
        this.deliveryDate = deliveryDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHour() {
        return hour;
    }

    public void setHour(String content) {
        this.hour = hour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

}