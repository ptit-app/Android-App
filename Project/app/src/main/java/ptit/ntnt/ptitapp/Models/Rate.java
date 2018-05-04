package ptit.ntnt.ptitapp.Models;

public class Rate {
    String lecturerID;
    int star;

    public Rate() {
    }

    @Override
    public String toString() {
        return "Rate{" +
                "lecturerID='" + lecturerID + '\'' +
                ", star=" + star +
                '}';
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public Rate setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
        return this;
    }

    public int getStar() {
        return star;
    }

    public Rate setStar(int star) {
        this.star = star;
        return this;
    }
}
