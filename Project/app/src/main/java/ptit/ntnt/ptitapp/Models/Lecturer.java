package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Lecturer{
    private String id;
    private String name;
    private String mail;
    private String phone;
    private Date birthday;
    private String faculty;         // Khoa
    private int rating;
    private Date createdAt;
    private Date modifiedAt;
    private String groupName;

    public Lecturer() {
    }

    public String getId() {
        return id;
    }

    public Lecturer setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Lecturer setName(String name) {
        this.name = name;
        return this;
    }

    public String getMail() {
        return mail;
    }

    public Lecturer setMail(String mail) {
        this.mail = mail;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Lecturer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Lecturer setBirthday(Date birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getFaculty() {
        return faculty;
    }

    public Lecturer setFaculty(String faculty) {
        this.faculty = faculty;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Lecturer setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Lecturer setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Lecturer setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public Lecturer setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }
}
