package ptit.ntnt.ptitapp.TimeTable;

public class SubjectSchedule {
    String subjectID,subjectName,room,buoi,courseID, studyDate;

    public SubjectSchedule() {
    }

    public SubjectSchedule(String subjectID, String subjectName, String room, String buoi, String courseID) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.room = room;
        this.buoi = buoi;
        this.courseID = courseID;
    }

    public SubjectSchedule(String subjectID, String subjectName, String room, String buoi, String courseID, String studyDate) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.room = room;
        this.buoi = buoi;
        this.courseID = courseID;
        this.studyDate = studyDate;
    }

    public String getStudyDate() {
        return studyDate;
    }

    public void setStudyDate(String studyDate) {
        this.studyDate = studyDate;
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
