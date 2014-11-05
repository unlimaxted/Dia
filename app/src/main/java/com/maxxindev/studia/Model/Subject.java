package com.maxxindev.studia.Model;

/**
 * Class of subject
 */
public class Subject
{
    private String name;
    private Integer creditUnits;
    private String bibliography;
    private Integer colorMark;

    public Subject(String name, Integer creditUnits, String bibliography, Integer colorMark) {
        this.name = name;
        this.creditUnits = creditUnits;
        this.bibliography = bibliography;
        this.colorMark = colorMark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditUnits() {
        return creditUnits;
    }

    public void setCreditUnits(Integer creditUnits) {
        this.creditUnits = creditUnits;
    }

    public String getBibliography() {
        return bibliography;
    }

    public void setBibliography(String bibliography) {
        this.bibliography = bibliography;
    }

    public Integer getColorMark() {
        return colorMark;
    }

    public void setColorMark(Integer colorMark) {
        this.colorMark = colorMark;
    }
}
