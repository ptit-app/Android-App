package ptit.ntnt.ptitapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.Models.Student;

public class NotiService extends Service{

    DatabaseReference fbData;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fbData = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        /*
         * Mục dưới đây để listen các thay đổi để hiển thị thông báo ra màn hình
         * Ex: báo thay đổi khi đổi password cho student N14DCAT022
         */
        fbData.child(DBConst.TB_STUDENT.TB_NAME).child("N14DCAT022").child("passwd").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Notification noti = new Notification(getApplicationContext());
                noti.createNotification("Password thay đổi", "Your pass has been changed to "+dataSnapshot.getValue(), LoginActivity.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return START_STICKY;
    }
}
