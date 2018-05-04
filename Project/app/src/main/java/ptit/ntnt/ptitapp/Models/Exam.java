package ptit.ntnt.ptitapp.Models;

public class Exam {
    String courseID;
    String examDate;

    public Exam(String courseID, String examDate, String examRoom, int tietBD) {
        this.courseID = courseID;
        this.examDate = examDate;
        this.examRoom = examRoom;
        this.tietBD = tietBD;
    }

    String examRoom;
    int tietBD;

    public Exam() {
    }

    @Override
    public String toString() {
        return "Exam{" +
                "courseID='" + courseID + '\'' +
                ", examDate='" + examDate + '\'' +
                ", examRoom='" + examRoom + '\'' +
                ", tietBD=" + tietBD +
                '}';
    }

    public String getCourseID() {
        return courseID;
    }

    public Exam setCourseID(String courseID) {
        this.courseID = courseID;
        return this;
    }

    public String getExamDate() {
        return examDate;
    }

    public Exam setExamDate(String examDate) {
        this.examDate = examDate;
        return this;
    }

    public String getExamRoom() {
        return examRoom;
    }

    public Exam setExamRoom(String examRoom) {
        this.examRoom = examRoom;
        return this;
    }

    public int getTietBD() {
        return tietBD;
    }

    public Exam setTietBD(int tietBD) {
        this.tietBD = tietBD;
        return this;
    }
}
