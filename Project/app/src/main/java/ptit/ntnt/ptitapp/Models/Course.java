package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Course {
    private String courseID;
    private String subjectID;
    private String classCode;
    private String studyGroup;
    private String TTH;
    private String dayOfWeek;
    private String tietBD;
    private String soTiet;
    private String studyTime;
    private Date startDate;
    private Date endDate;
    private String note;

    public Course() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public Course setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Course setEndDate(Date endDate) {
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
        return classCode;
    }

    public Course setClassCode(String classCode) {
        this.classCode = classCode;
        return this;
    }

    public String getSoTiet() {
        return soTiet;
    }

    public Course setSoTiet(String soTiet) {
        this.soTiet = soTiet;
        return this;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public Course setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }


    public String getStudyGroup() {
        return studyGroup;
    }

    public Course setStudyGroup(String studyGroup) {
        this.studyGroup = studyGroup;
        return this;
    }

    public String getTTH() {
        return TTH;
    }

    public Course setTTH(String TTH) {
        this.TTH = TTH;
        return this;
    }

    public String getStudyTime() {
        return studyTime;
    }

    public Course setStudyTime(String studyTime) {
        this.studyTime = studyTime;
        return this;
    }

    public String getTietBD() {
        return tietBD;
    }

    public Course setTietBD(String tietBD) {
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
}
