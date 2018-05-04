package ptit.ntnt.ptitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ptit.ntnt.ptitapp.Database.DBConst;
import ptit.ntnt.ptitapp.ForgotPassword.PassRecoverS1;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edEmail, edPass;
    Button btnLogin;
    TextView tvForgotPass;
    DatabaseReference fbData;
    ArrayList<String> key;
    ArrayList<Object> svAttendArr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (TextInputEditText) findViewById(R.id.edEmail);
        edPass = (TextInputEditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);
        fbData = FirebaseDatabase.getInstance().getReference();
        svAttendArr = new ArrayList<>();
        key = new ArrayList<>();

        fbData.child(DBConst.TB_ATTENDANCE.TB_NAME).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Object svAttend = data.getValue();
                    key.add(data.getKey());
                    svAttendArr.add(svAttend);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PassRecoverS1 step1 = new PassRecoverS1();
                step1.show(getFragmentManager(),"rec_pass_1");
            }
        });
    }

    private void validateLogin(){
        String email = edEmail.getText().toString();
        String pass = edPass.getText().toString();
        String studentLoginID = email.split("@")[0];
        if(email.isEmpty()){
            //Toast.makeText(LoginActivity.this, "Vui long nhap Email!", Toast.LENGTH_SHORT).show();
            edEmail.setError("Vui long nhap Email!");
            edEmail.requestFocus();
        }else{
            if(pass.isEmpty()){
                //Toast.makeText(LoginActivity.this, "Vui long nhap mat khau!", Toast.LENGTH_SHORT).show();
                edPass.setError("Vui long nhap mat khau!");
                edPass.requestFocus();
            }else{
                if(email.equals("admin")&&pass.equals("admin")){
                    Bundle args = new Bundle();
                    args.putSerializable("KEY", key);
                    args.putSerializable("ARRAYLIST", svAttendArr);
                    Intent intent = new Intent(this, MainActivity.class);
                    intent.putExtra("BUNDLE",args);
                    startActivity(intent);
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else if(MyApplication.mapAllStudent.get(studentLoginID) != null){
                    MyApplication.setCurrentStudent(MyApplication.mapAllStudent.get(studentLoginID));
//                    Bundle args = new Bundle();
//                    args.putSerializable("KEY", key);
//                    args.putSerializable("ARRAYLIST", svAttendArr);
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.putExtra("BUNDLE",args);
//                    startActivity(intent);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Sai Email hoac mat khau!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
