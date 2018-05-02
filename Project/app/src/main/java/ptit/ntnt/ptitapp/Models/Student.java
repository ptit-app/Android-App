package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Student{
    private String studentID;
    private String passwd;
    private String fullName;
    private String email;
    private String phone;
    private String classID;
    private String birthday;
    private String facultyID;
    private String userGroup;
    private String note;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", passwd='" + passwd + '\'' +
                ", fullName='" + fullName + '\'' +
                ", mail='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", classID='" + classID + '\'' +
                ", birthday='" + birthday + '\'' +
                ", facultyID='" + facultyID + '\'' +
                ", userGroup='" + userGroup + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getStudentID() {
        return studentID;
    }

    public Student setStudentID(String studentID) {
        this.studentID = studentID;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public Student setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Student setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Student setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getClassID() {
        return classID;
    }

    public Student setClassID(String classID) {
        this.classID = classID;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public Student setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public Student setFacultyID(String facultyID) {
        this.facultyID = facultyID;
        return this;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public Student setUserGroup(String userGroup) {
        this.userGroup = userGroup;
        return this;
    }

    public String getNote() {
        return note;
    }

    public Student setNote(String note) {
        this.note = note;
        return this;
    }
}
