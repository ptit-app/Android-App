package ptit.ntnt.ptitapp.TeacherRating;

import java.io.Serializable;

public class Teacher implements Serializable {
    String nameGV;
    String idGV;
    String emailGV;
    String hocviGV;
    String chucvuGV;
    //RatingBar ratingGV;
    int countView;

    public Teacher() {
    }

    public Teacher(String nameGV, String idGV, String emailGV, String hocviGV, String chucvuGV, int countView) {
        this.nameGV = nameGV;
        this.idGV = idGV;
        this.emailGV = emailGV;
        this.hocviGV = hocviGV;
        this.chucvuGV = chucvuGV;
        this.countView = countView;
    }

    public String getNameGV() {
        return nameGV;
    }

    public void setNameGV(String nameGV) {
        this.nameGV = nameGV;
    }

    public String getIdGV() {
        return idGV;
    }

    public void setIdGV(String idGV) {
        this.idGV = idGV;
    }

    public String getEmailGV() {
        return emailGV;
    }

    public void setEmailGV(String emailGV) {
        this.emailGV = emailGV;
    }

    public String getHocviGV() {
        return hocviGV;
    }

    public void setHocviGV(String hocviGV) {
        this.hocviGV = hocviGV;
    }

    public String getChucvuGV() {
        return chucvuGV;
    }

    public void setChucvuGV(String chucvuGV) {
        this.chucvuGV = chucvuGV;
    }

    public int getCountView() {
        return countView;
    }

    public void setCountView(int countView) {
        this.countView = countView;
    }
}


