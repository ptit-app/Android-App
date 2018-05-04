package ptit.ntnt.ptitapp.Models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Lecturer implements Serializable, Comparable<Lecturer>{
    private String id;
    private String fullName;
    private String email;
    private String phone;
    private String facultyID;         // Khoa
    private int rating;
    private int ratingCount;
    private String website;
    private String position;
    private String degree;
    private String groupName;

    public Lecturer() {
    }

    public Lecturer(String fullName, String id,  String email,String degree, String position, int ratingCount ) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.ratingCount = ratingCount;
        this.position = position;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Lecturer{" +
                "id='" + id + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", facultyID='" + facultyID + '\'' +
                ", rating=" + rating +
                ", ratingCount=" + ratingCount +
                ", website='" + website + '\'' +
                ", position='" + position + '\'' +
                ", degree='" + degree + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public Lecturer setId(String id) {
        this.id = id;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Lecturer setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Lecturer setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Lecturer setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getFacultyID() {
        return facultyID;
    }

    public Lecturer setFacultyID(String facultyID) {
        this.facultyID = facultyID;
        return this;
    }

    public int getRating() {
        return rating;
    }

    public Lecturer setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public Lecturer setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public Lecturer setWebsite(String website) {
        this.website = website;
        return this;
    }

    public String getPosition() {
        return position;
    }

    public Lecturer setPosition(String position) {
        this.position = position;
        return this;
    }

    public String getDegree() {
        return degree;
    }

    public Lecturer setDegree(String degree) {
        this.degree = degree;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public Lecturer setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    @Override
    public int compareTo(@NonNull Lecturer o) {
        return Integer.parseInt(this.getId()) - Integer.parseInt(o.getId());
    }
}
