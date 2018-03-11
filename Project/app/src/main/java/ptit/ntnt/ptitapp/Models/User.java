package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class User {
    private String name;
    private String mail;
    private String phone;
    private Date birthday;
    private Date createdAt;
    private Date modifiedAt;
    private UserGroup group;

    public User() {
    }

    public User(String name, String mail, String phone, Date birthday, Date createdAt, Date modifiedAt, UserGroup group) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.birthday = birthday;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.group = group;
    }

    public User(String name, String mail, String phone, Date birthday) {
        this.name = name;
        this.mail = mail;
        this.phone = phone;
        this.birthday = birthday;
        this.createdAt = new Date();
        this.modifiedAt = new Date();
        this.group = new UserGroup();
    }

    void UpdateModifyTime(){
        this.modifiedAt = new Date();
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        UpdateModifyTime();
        return this;
    }

    public String getMail() {
        return mail;
    }

    public User setMail(String mail) {
        this.mail = mail;
        UpdateModifyTime();
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        UpdateModifyTime();
        return this;
    }

    public Date getBirthday() {
        return birthday;
    }

    public User setBirthday(Date birthday) {
        this.birthday = birthday;
        UpdateModifyTime();
        return this;
    }

    public Date getCreatedAt() { return createdAt; }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public UserGroup getGroup() {return group; }
}
