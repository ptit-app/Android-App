package ptit.ntnt.ptitapp.TimeTable;

public class CustomSubject
{
    int soTC;
    String subjectID,subjectName;

    public CustomSubject() {
    }

    public CustomSubject(int soTC, String subjectID, String subjectName) {
        this.soTC = soTC;
        this.subjectID = subjectID;
        this.subjectName = subjectName;
    }

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
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
}

//package ptit.ntnt.ptitapp.TimeTable;
//
//public class CustomSubject
//{
//    int soTC;
//    String subjectID,subjectName;
//
//    public CustomSubject() {
//    }
//
//    public CustomSubject(int soTC, String subjectID, String subjectName) {
//        this.soTC = soTC;
//        this.subjectID = subjectID;
//        this.subjectName = subjectName;
//    }
//
//    public int getSoTC() {
//        return soTC;
//    }
//
//    public void setSoTC(int soTC) {
//        this.soTC = soTC;
//    }
//
//    public String getSubjectID() {
//        return subjectID;
//    }
//
//    public void setSubjectID(String subjectID) {
//        this.subjectID = subjectID;
//    }
//
//    public String getSubjectName() {
//        return subjectName;
//    }
//
//    public void setSubjectName(String subjectName) {
//        this.subjectName = subjectName;
//    }
//}