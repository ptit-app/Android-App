package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 26/03/2018.
 */

public class Faculty {
    private String facultyID;
    private String facultyName;

    public Faculty() {
    }

    public String getFacultyID() {
        return facultyID;
    }

    public Faculty setFacultyID(String facultyID) {
        this.facultyID = facultyID;
        return this;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public Faculty setFacultyName(String facultyName) {
        this.facultyName = facultyName;
        return this;
    }
}
