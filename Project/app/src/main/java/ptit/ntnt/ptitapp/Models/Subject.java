package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Subject {
    private String name;
    private String id;

    public Subject() {
    }

    public Subject(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
