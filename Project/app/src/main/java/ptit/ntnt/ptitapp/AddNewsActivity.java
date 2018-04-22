package ptit.ntnt.ptitapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

import ptit.ntnt.ptitapp.Firebase.FirebaseHelper;
import ptit.ntnt.ptitapp.Models.News;

public class AddNewsActivity extends AppCompatActivity {
    Button addNewNewsButton;
    Button cancleNewNewsButton;
    EditText titleEditText;
    EditText contentEditText;
    static public String LIST_SIZE = "LIST_SIZE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_news);
        setControl();
        final int listSize = (int)getIntent().getExtras().get(LIST_SIZE);
        Log.d("Array List News", listSize + " ");

        cancleNewNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addNewNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (contentEditText.getText().toString().isEmpty() || titleEditText.getText().toString().isEmpty()){
                    Toast.makeText(AddNewsActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    News news = new News();
                    news.setTitle(titleEditText.getText().toString());
                    news.setContent(contentEditText.getText().toString());
                    news.setDescription(contentEditText.getText().toString().length() < 50 ? contentEditText.getText().toString() : contentEditText.getText().toString().substring(0,50));
                    String idNews = String.valueOf(new Date().getTime());
                    news.setId(idNews);
                    DatabaseReference firebase = FirebaseDatabase.getInstance().getReference();

                    firebase.child("TB_NEWS2").child(idNews).setValue(news);
                    finish();
                }
            }
        });

    }

    void setControl(){
        addNewNewsButton = findViewById(R.id.addNewNewsButton);
        cancleNewNewsButton = findViewById(R.id.cancelNewNewsButton);
        titleEditText = findViewById(R.id.newNewsTitleEditText);
        contentEditText = findViewById(R.id.newNewsContentEditText);
    }
}
