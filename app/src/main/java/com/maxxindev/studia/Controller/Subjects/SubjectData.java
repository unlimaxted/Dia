package com.maxxindev.studia.Controller.Subjects;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class for the data of Subjects
 */
public class SubjectData implements Parcelable{

    private String subject;
    private String professor;
    private String uc;
    private Integer color;

    public SubjectData(String subject, String professor, String uc, Integer color) {
        this.subject = subject;
        this.professor = professor;
        this.uc = uc;
        this.color = color;
    }

    public SubjectData(Parcel in){
        this.subject= in.readString();
        this.professor = in.readString();
        this.uc = in.readString();
        this.color = in.readInt();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public Integer getColor() {
        return color;
    }

    public void setColor(Integer color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(getSubject());
        parcel.writeString(getProfessor());
        parcel.writeString(getUc());
        parcel.writeInt(getColor());
    }

    public static final Parcelable.Creator<SubjectData> CREATOR = new Parcelable.Creator<SubjectData>() {
        public SubjectData createFromParcel(Parcel in) {
            return new SubjectData(in);
        }

        public SubjectData[] newArray(int size) {
            return new SubjectData[size];
        }
    };


}