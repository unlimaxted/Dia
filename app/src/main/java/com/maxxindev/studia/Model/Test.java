package com.maxxindev.studia.Model;

/**
 * Class of test
 */
public class Test
{
    private String name;
    private String date;
    private String dayOfWeek;
    private String hour;
    private String salon;
    private String description;
    private Integer qualification;
    private Integer percent;

    public Test(String name, String date, String dayOfWeek, String hour, String salon, String description, Integer qualification, Integer percent) {
        this.name = name;
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.hour = hour;
        this.salon = salon;
        this.description = description;
        this.qualification = qualification;
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPercent() {
        return percent;
    }

    public void setPercent(Integer percent) {
        this.percent = percent;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
