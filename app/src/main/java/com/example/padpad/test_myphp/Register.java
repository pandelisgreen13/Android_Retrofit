package com.example.padpad.test_myphp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText mail,pass,name;
    Button reg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        mail=(EditText)findViewById(R.id.mail);
        pass=(EditText)findViewById(R.id.pass);
        name=(EditText)findViewById(R.id.name);

        reg=(Button) findViewById(R.id.register);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final RequestInterface requestInterface = retrofit.create(RequestInterface.class);


        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User();
                user.setName(name.getText().toString());
                user.setEmail(mail.getText().toString());
                user.setPassword(pass.getText().toString());
                ServerRequest request = new ServerRequest();
                request.setOperation(Constants.REGISTER_OPERATION);
                request.setUser(user);
                Call<ServerResponse> response = requestInterface.operation(request);

                response.enqueue(new Callback<ServerResponse>() {
                    @Override
                    public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                        ServerResponse resp = response.body();
                        Toast.makeText(Register.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                        finish();
                        Intent intent=new Intent(getApplicationContext(),Log_In.class);
                        intent.putExtra("mail",mail.getText().toString());
                        intent.putExtra("pass",mail.getText().toString());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<ServerResponse> call, Throwable t) {
                        Toast.makeText(Register.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

    }
}
