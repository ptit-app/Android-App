package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Lecturer extends User{
    private String id;
    private String faculty;         // Khoa

    public Lecturer(String id, String faculty) {
        this.id = id;
        this.faculty = faculty;
    }

    public Lecturer(String name, String mail, String phone, Date birthday, Date createdAt, Date modifiedAt, UserGroup group, String id, String faculty) {
        super(name, mail, phone, birthday, createdAt, modifiedAt, group);
        this.id = id;
        this.faculty = faculty;
    }

    public Lecturer(String name, String mail, String phone, Date birthday, String id, String faculty) {
        super(name, mail, phone, birthday);
        this.id = id;
        this.faculty = faculty;
    }
}
