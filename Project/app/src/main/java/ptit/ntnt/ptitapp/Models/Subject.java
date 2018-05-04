package ptit.ntnt.ptitapp.Models;

import android.support.annotation.NonNull;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Subject implements Comparable<Subject>{
    private String subjectID;
    private String subjectName;
    private int soTC;

    @Override
    public int compareTo(@NonNull Subject subject) {
        return this.getSubjectID().compareTo(subject.getSubjectID());
    }

    public Subject(String subjectID, String subjectName, int soTC) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.soTC = soTC;
    }

    public Subject() {
    }

    public String getSubjectID() {
        return subjectID;
    }

    public Subject setSubjectID(String subjectID) {
        this.subjectID = subjectID;
        return this;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public Subject setSubjectName(String subjectName) {
        this.subjectName = subjectName;
        return this;
    }

    public int getSoTC() {
        return soTC;
    }

    public Subject setSoTC(int soTC) {
        this.soTC = soTC;
        return this;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectID='" + subjectID + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", soTC=" + soTC +
                '}';
    }
}
