package ptit.ntnt.ptitapp.Models;

/**
 * Created by datshiro on 20/03/2018.
 */

public class PTITClass {
    String ClassName;
    String ClassCode;

    public PTITClass() {
    }

    public PTITClass(String className, String classCode) {
        ClassName = className;
        ClassCode = classCode;
    }

    public String getClassName() {
        return ClassName;
    }

    public PTITClass setClassName(String className) {
        ClassName = className;
        return this;
    }

    public String getClassCode() {
        return ClassCode;
    }

    public PTITClass setClassCode(String classCode) {
        ClassCode = classCode;
        return this;
    }
}

