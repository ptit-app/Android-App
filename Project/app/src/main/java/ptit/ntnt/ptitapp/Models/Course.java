package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Course {
    private String courseID;
    private String subjectID;
    private String classID;
    private int studyGroup;
    private int TTH;
    private int dayOfWeek;
    private int tietBD;
    private int soTiet;
    private String startDate;
    private String endDate;
    private String note;
    private String lecturerID;
    private String semester;
    private String room;

    public Course() {
    }

    public String getStartDate() {
        return startDate;
    }

    public Course setStartDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public String getEndDate() {
        return endDate;
    }

    public Course setEndDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public String getCourseID() {
        return courseID;
    }

    public Course setCourseID(String courseID) {
        this.courseID = courseID;
        return this;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public Course setSubjectID(String subjectID) {
        this.subjectID = subjectID;
        return this;
    }

    public String getClassCode() {
        return classID;
    }

    public Course setClassCode(String classID) {
        this.classID = classID;
        return this;
    }

    public int getSoTiet() {
        return soTiet;
    }

    public Course setSoTiet(int soTiet) {
        this.soTiet = soTiet;
        return this;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public Course setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }


    public int getStudyGroup() {
        return studyGroup;
    }

    public Course setStudyGroup(int studyGroup) {
        this.studyGroup = studyGroup;
        return this;
    }

    public int getTTH() {
        return TTH;
    }

    public Course setTTH(int TTH) {
        this.TTH = TTH;
        return this;
    }


    public int getTietBD() {
        return tietBD;
    }

    public Course setTietBD(int tietBD) {
        this.tietBD = tietBD;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Course setNote(String note) {
        this.note = note;
        return this;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public Course setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
        return this;
    }

    public String getSemester() {
        return semester;
    }

    public Course setSemester(String semester) {
        this.semester = semester;
        return this;
    }

    public String getClassID() {
        return classID;
    }

    public Course setClassID(String classID) {
        this.classID = classID;
        return this;
    }

    public String getRoom() {
        return room;
    }

    public Course setRoom(String room) {
        this.room = room;
        return this;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseID='" + courseID + '\'' +
                ", subjectID='" + subjectID + '\'' +
                ", classID='" + classID + '\'' +
                ", studyGroup=" + studyGroup +
                ", TTH=" + TTH +
                ", dayOfWeek=" + dayOfWeek +
                ", tietBD=" + tietBD +
                ", soTiet=" + soTiet +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", note='" + note + '\'' +
                ", lecturerID='" + lecturerID + '\'' +
                ", semester='" + semester + '\'' +
                ", room='" + room + '\'' +
                '}';
    }
}
