package ptit.ntnt.ptitapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText edEmail, edPass;
    Button btnLogin;
    TextView tvForgotPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = (TextInputEditText) findViewById(R.id.edEmail);
        edPass = (TextInputEditText) findViewById(R.id.edPass);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLogin();
            }
        });
    }

    private void validateLogin(){
        String email = edEmail.getText().toString();
        String pass = edPass.getText().toString();
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
                }else{
                    Toast.makeText(LoginActivity.this, "Sai Email hoac mat khau!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
