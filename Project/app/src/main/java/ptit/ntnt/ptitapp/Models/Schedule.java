package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Schedule {
    private String scheduleID;
    private String courseID;
    private int tietBD;
    private String room;
    private String isTheory;   // Lý Thuyêt hay thực hành
    private Date studyDate;
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

    public Date getStudyDate() {
        return studyDate;
    }

    public Schedule setStudyDate(Date studyDate) {
        this.studyDate = studyDate;
        return this;
    }
}
