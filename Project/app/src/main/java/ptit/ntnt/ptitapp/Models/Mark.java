package ptit.ntnt.ptitapp.Models;

import java.util.Date;

/**
 * Created by datshiro on 11/03/2018.
 */

public class Mark {
    private String markID;
    private String studentID;
    private String subjectID;
    private float CC ;
    private float KT ;
    private float TH ;
    private float thi ;
    private String KQ;
    private float TK;
    private String xepLoai;

    public Mark() {
    }

    public String getMarkID() {
        return markID;
    }

    public Mark setMarkID(String markID) {
        this.markID = markID;
        return this;
    }

    public String getStudentID() {
        return studentID;
    }

    public Mark setStudentID(String studentID) {
        this.studentID = studentID;
        return this;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public Mark setSubjectID(String subjectID) {
        this.subjectID = subjectID;
        return this;
    }

    public float getCC() {
        return CC;
    }

    public Mark setCC(float CC) {
        this.CC = CC;
        return this;
    }

    public float getKT() {
        return KT;
    }

    public Mark setKT(float KT) {
        this.KT = KT;
        return this;
    }

    public float getTH() {
        return TH;
    }

    public Mark setTH(float TH) {
        this.TH = TH;
        return this;
    }

    public float getThi() {
        return thi;
    }

    public Mark setThi(float thi) {
        this.thi = thi;
        return this;
    }

    public String getKQ() {
        return KQ;
    }

    public Mark setKQ(String KQ) {
        this.KQ = KQ;
        return this;
    }

    public float getTK() {
        return TK;
    }

    public Mark setTK(float TK) {
        this.TK = TK;
        return this;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public Mark setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
        return this;
    }
}
