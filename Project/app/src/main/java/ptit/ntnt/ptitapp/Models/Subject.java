package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Subject {
    private String id;
    private String name;
    private int soTC;

    public Subject() {
    }

    public Subject(String id, String name, int soTC) {
        this.id = id;
        this.name = name;
        this.soTC = soTC;
    }

    public String getId() {
        return id;
    }

    public Subject setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Subject setName(String name) {
        this.name = name;
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
