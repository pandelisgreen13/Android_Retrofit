package com.example.padpad.test_myphp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Log_In extends AppCompatActivity {

    Button btn_chg_password;

    private TextView tv_name,tv_email,tv_message;
    private SharedPreferences pref;
    private EditText et_old_password,et_new_password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log__in);


        tv_name=(TextView) findViewById(R.id.tv_name);
        tv_email=(TextView) findViewById(R.id.tv_email);
        et_old_password=(EditText) findViewById(R.id.et_old_password);
        et_new_password=(EditText) findViewById(R.id.et_new_password);

        btn_chg_password=(Button) findViewById(R.id.btn_chg_password);

        Bundle b = this.getIntent().getExtras();
        final String mail =b.getString("mail");
        String pass =b.getString("pass");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RequestInterface requestInterface = retrofit.create(RequestInterface.class);

        User user = new User();
        user.setEmail(mail);
        user.setPassword(pass);
        ServerRequest request = new ServerRequest();
        request.setOperation(Constants.LOGIN_OPERATION);
        request.setUser(user);
        Call<ServerResponse> response = requestInterface.operation(request);
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                Toast.makeText(Log_In.this, resp.getMessage(), Toast.LENGTH_SHORT).show();
                if(resp.getResult().equals(Constants.SUCCESS)){

                    tv_name.setText("Welcome"+" "+mail.toString());
//                    SharedPreferences.Editor editor = pref.edit();
//                    editor.putBoolean(Constants.IS_LOGGED_IN,true);
//                    editor.putString(Constants.EMAIL,resp.getUser().getEmail());
//                    editor.putString(Constants.NAME,resp.getUser().getName());
//                    editor.putString(Constants.UNIQUE_ID,resp.getUser().getUnique_id());
//                    editor.apply();
                    Toast.makeText(Log_In.this, "Success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

                Toast.makeText(Log_In.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

        btn_chg_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String temp=et_old_password.getText().toString();
                String temp2=et_new_password.getText().toString();
                if(!temp.isEmpty() && !temp2.isEmpty()){

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(Constants.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    RequestInterface requestInterface = retrofit.create(RequestInterface.class);

                    User user = new User();
                    user.setEmail(mail);
                    user.setOld_password(temp);
                    user.setNew_password(temp2);
                    ServerRequest request = new ServerRequest();
                    request.setOperation(Constants.CHANGE_PASSWORD_OPERATION);
                    request.setUser(user);
                    Call<ServerResponse> response = requestInterface.operation(request);

                    response.enqueue(new Callback<ServerResponse>() {
                        @Override
                        public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                            ServerResponse resp = response.body();

                            if(resp.getResult().equals(Constants.SUCCESS)){
                                Toast.makeText(Log_In.this, "Done", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Log_In.this, "Failed", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ServerResponse> call, Throwable t) {

                        }
                    });
                }else {
                    Toast.makeText(Log_In.this, "Some field empty", Toast.LENGTH_SHORT).show();
                }



            }
        });


    }
}
