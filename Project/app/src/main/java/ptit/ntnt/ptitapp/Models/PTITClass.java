package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 20/03/2018.
 */

public class PTITClass {
    String classID;
    String className;

    public PTITClass() {
    }

    public String getClassID() {
        return classID;
    }

    public PTITClass setClassID(String classID) {
        this.classID = classID;
        return this;
    }

    public String getClassName() {
        return className;
    }

    public PTITClass setClassName(String className) {
        this.className = className;
        return this;
    }
}

