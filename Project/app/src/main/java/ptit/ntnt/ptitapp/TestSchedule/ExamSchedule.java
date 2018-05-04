package ptit.ntnt.ptitapp.TestSchedule;

public class ExamSchedule {
    String Id;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getTietBD() {
        return TietBD;
    }

    public void setTietBD(String tietBD) {
        TietBD = tietBD;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    String Room;
    String TietBD;
    String Date;
    public ExamSchedule(String id, String room, String tietBD, String date) {
        Id = id;
        Room = room;
        TietBD = tietBD;
        Date = date;
    }

    public ExamSchedule() {
    }
}
