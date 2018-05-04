package ptit.ntnt.ptitapp.Models;

import android.support.annotation.NonNull;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Schedule implements Comparable<Schedule>{
    private String scheduleID;
    private String courseID;
    private int tietBD;
    private String room;
    private String isTheory;   // Lý Thuyêt hay thực hành
    private String studyDate;
    private String note;

    public Schedule() {
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public Schedule setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
        return this;
    }

    public String getCourseID() {
        return courseID;
    }

    public Schedule setCourseID(String courseID) {
        this.courseID = courseID;
        return this;
    }

    public int getTietBD() {
        return tietBD;
    }

    public Schedule setTietBD(int tietBD) {
        this.tietBD = tietBD;
        return this;
    }

    public String getRoom() {
        return room;
    }

    public Schedule setRoom(String room) {
        this.room = room;
        return this;
    }

    public String getIsTheory() {
        return isTheory;
    }

    public Schedule setIsTheory(String isTheory) {
        this.isTheory = isTheory;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Schedule setNote(String note) {
        this.note = note;
        return this;
    }

    public String getStudyDate() {
        return studyDate;
    }

    public Schedule setStudyDate(String studyDate) {
        this.studyDate = studyDate;
        return this;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "scheduleID='" + scheduleID + '\'' +
                ", courseID='" + courseID + '\'' +
                ", tietBD=" + tietBD +
                ", room='" + room + '\'' +
                ", isTheory='" + isTheory + '\'' +
                ", studyDate='" + studyDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    @Override
    public int compareTo(@NonNull Schedule o) {
        return getTietBD()-o.getTietBD();
    }
}
