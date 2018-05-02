package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Subject {
    private String id;
    private String subjectName;
    private int soTC;

    public Subject() {
    }

    public String getId() {
        return id;
    }

    public Subject setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return subjectName;
    }

    public Subject setName(String name) {
        this.subjectName = name;
        return this;
    }

    public int getSoTC() {
        return soTC;
    }

    public Subject setSoTC(int soTC) {
        this.soTC = soTC;
        return this;
    }
}
