package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Student {
    private String id;
    private String fullName;
    private String mail;
    private String phone;
    private String classCode;
    private Date birthday;
    private String faculty;
    private Date createdAt;
    private Date modifiedAt;
    private String groupName;

    public Student() {
    }

    public String getId() {
        return id;
    }

    public Student setId(String id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Student setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Student setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Student setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getClassCode() {
        return classCode;
    }

    public Student setClassCode(String classCode) {
        this.classCode = classCode;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Student setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Student setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Student setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public String getFaculty() {
        return faculty;
    }

    public Student setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }


    public String getGroupName() {
        return groupName;
    }

    public Student setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
}
