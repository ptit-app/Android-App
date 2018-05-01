package ptit.ntnt.ptitapp.TimeTable;

public class SubjectSchedule {
    String subjectID,subjectName,room,buoi,courseID;

    public SubjectSchedule() {
    }

    public SubjectSchedule(String subjectID, String subjectName, String room, String buoi) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.room = room;
        this.buoi = buoi;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBuoi() {
        return buoi;
    }

    public void setBuoi(String buoi) {
        this.buoi = buoi;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
}
