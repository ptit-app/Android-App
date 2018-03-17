package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Lecturer extends User{
    private String id;
    private String faculty;         // Khoa
    private int rating;

    public Lecturer(String id, String faculty) {
        this.id = id;
        this.faculty = faculty;
        this.rating = 0;
    }
}
