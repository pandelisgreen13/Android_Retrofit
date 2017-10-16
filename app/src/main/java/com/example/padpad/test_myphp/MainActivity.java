package com.example.padpad.test_myphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText mail,pass;
    Button log_in, sign_in;
    String ma,pas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);



        log_in =(Button)findViewById(R.id.button1);
        sign_in =(Button)findViewById(R.id.button2);

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma=mail.getText().toString();
                pas=pass.getText().toString();

                Intent intent=new Intent(getApplicationContext(),Log_In.class);
                if(!ma.isEmpty() &&! pas.isEmpty()) {
                    intent.putExtra("mail", mail.getText().toString());
                    intent.putExtra("pass", pass.getText().toString());
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Fields is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ma=mail.getText().toString();
                pas=pass.getText().toString();
//                String method = "register";
//                BackgroundTask backgroundTask = new BackgroundTask(getApplication());
//                backgroundTask.execute(method,ma,pas);

                Intent intent=new Intent(getApplicationContext(),Register.class);
                startActivity(intent);
            }
        });


    }
}
