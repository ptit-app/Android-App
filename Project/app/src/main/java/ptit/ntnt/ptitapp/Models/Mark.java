package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Mark {
    private Student student;
    private int mark;
    private boolean status;     // Qua Môn hay Rớt
    private Subject subject;
    private Date modifiedAt;

    public Mark() {
    }

    public Mark(Student student, int mark, boolean status, Subject subject, Date lastUpdate) {
        this.student = student;
        this.mark = mark;
        this.status = status;
        this.subject = subject;
        this.modifiedAt = lastUpdate;
    }

    public Mark(Student student, Subject subject) {
        this.student = student;
        this.subject = subject;
        this.modifiedAt = new Date();
    }

    void UpdateModifyTime(){
        this.modifiedAt = new Date();
    }

    public Student getStudent() {
        return student;
    }

    public int getMark() {
        return mark;
    }

    public boolean isStatus() {
        return status;
    }

    public Subject getSubject() {
        return subject;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public Mark setMark(int mark) {
        this.mark = mark;
        UpdateModifyTime();
        return this;
    }

    public Mark setStatus(boolean status) {
        this.status = status;
        return this;
    }
}
