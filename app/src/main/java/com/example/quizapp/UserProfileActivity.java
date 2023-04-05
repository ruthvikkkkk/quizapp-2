package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.LoginCredentials;
import com.example.quizapp.model.RegisterResponse;
import com.example.quizapp.model.UserData;
import com.example.quizapp.network.ApiInterFace;
import com.example.quizapp.network.UserApiInterface;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserProfileActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    private UserApiInterface userApiInterface;

    private ApiInterFace apiInterFace;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        apiInterFace=((ApplicationClass)getApplication()).registerRetrofit.create(ApiInterFace.class);
        bottomNavigationView = findViewById(R.id.bottomNavigationView_prof);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        findViewById(R.id.bt_profile_sign_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginCredentials loginCredentials=new LoginCredentials();
                loginCredentials.setPlatformId("quiz");
                loginCredentials.setPassword("password");
                loginCredentials.setUsername(getSharedPreferences("LoginCredentialsPref",MODE_PRIVATE).getString("username","def"));
                apiInterFace.logoutUser(loginCredentials).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.body()!=null)
                        {
                            Toast.makeText(UserProfileActivity.this, "logout successfull !!", Toast.LENGTH_SHORT).show();
                            deleteSharedPreferences("LoginCredentialsPref");
                            startActivity(new Intent(UserProfileActivity.this,LoginActivity.class));
                            finish();
                        }else{
                            Toast.makeText(UserProfileActivity.this, "not able to logout", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UserProfileActivity.this, t.getLocalizedMessage()+"", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        userApiInterface = ((ApplicationClass) getApplication()).userRetrofit.create(UserApiInterface.class);
        userApiInterface.getUserData(getSharedPreferences("LoginCredentialsPref", MODE_PRIVATE).getString("userId", "")).enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                UserData userData = response.body();
                ((TextView) findViewById(R.id.tv_profile_sports)).setText(userData.getIsSports()+"");
                ((TextView) findViewById(R.id.tv_profile_entertainment)).setText(userData.getIsEntertainment()+"");
                ((TextView) findViewById(R.id.tv_profile_fashion)).setText(userData.getIsFashion()+"");
                ((TextView) findViewById(R.id.tv_profile_food)).setText(userData.getIsFood()+"");
                ((TextView) findViewById(R.id.tv_profile_travel)).setText(userData.getIsTravel()+"");
                ((TextView) findViewById(R.id.tv_profile_music)).setText(userData.getIsMusic()+"");
                ((TextView) findViewById(R.id.tv_profile_email)).setText(userData.getQuizUserEmail());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {

            }
        });

    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.quiz:

                Intent profileIntent=new Intent(UserProfileActivity.this,ContestsActivity.class);
                startActivity(profileIntent);
                finish();

                break;
            case R.id.upcomingcontests:
                startActivity(new Intent(UserProfileActivity.this,DynamicQuizActivity.class));
                finish();

                break;
            case R.id.dynamic:

                startActivity(new Intent(UserProfileActivity.this,LiveQuizes.class));
                finish();

                break;

            case R.id.profile:

                break;
        }

        return true;
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
}