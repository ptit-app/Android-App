package ptit.ntnt.ptitapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ptit.ntnt.ptitapp.ForgotPassword.PassRecoverS1;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edEmail, edPass;
    Button btnLogin;
    TextView tvForgotPass;
    String mssv="N14DCAT022";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (TextInputEditText) findViewById(R.id.edEmail);
        edPass = (TextInputEditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvForgotPass = (TextView) findViewById(R.id.tvForgotPass);

        MyApplication.getMapCourse(mssv);
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
        //Dưới đây là start service hiển thị thông báo
        //startService(new Intent(this, NotiService.class));
    }

    private void validateLogin(){
        Log.d("DAT SHIRO WORK" , MyApplication.listAllLecturer.toString());
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
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else if(MyApplication.mapAllStudent.get(studentLoginID) != null){
                    MyApplication.setCurrentStudent(MyApplication.mapAllStudent.get(studentLoginID));
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }else{
                    Toast.makeText(LoginActivity.this, "Sai Email hoac mat khau!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
