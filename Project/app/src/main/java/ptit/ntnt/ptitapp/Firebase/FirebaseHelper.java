package ptit.ntnt.ptitapp.Firebase;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Lecturer;
import ptit.ntnt.ptitapp.Models.Student;
import ptit.ntnt.ptitapp.Models.User;
import ptit.ntnt.ptitapp.R;

/**
 * Created by datshiro on 26/03/2018.
 */

public class FirebaseHelper {
    public static DatabaseReference mData;

    public FirebaseHelper(){
        mData = FirebaseDatabase.getInstance().getReference();
    }

    public Student getStudent (String StudentID){
        final Student[] students = {new Student()};
        mData.child(DBConst.TB_STUDENT.TB_NAME).child(StudentID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                students[0] = dataSnapshot.getValue(Student.class);         // Do cai ham nay doi` phai? tra ve` 1 phan tu cua mang
                Log.d("Student", students[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (students[0] != null){
            return students[0];
        }else{
            return null ;
        }
    }

    public Lecturer getLecturer(String LecturerID){
        final Lecturer[] lecturers = {new Lecturer()};
        mData.child(DBConst.TB_STUDENT.TB_NAME).child(LecturerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                lecturers[0] = dataSnapshot.getValue(Lecturer.class);         // Do cai ham nay doi` phai? tra ve` 1 phan tu cua mang
                Log.d("Lecturer", lecturers[0].toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (lecturers[0] != null){
            return lecturers[0];
        }else{
            return null ;
        }
    }

}
