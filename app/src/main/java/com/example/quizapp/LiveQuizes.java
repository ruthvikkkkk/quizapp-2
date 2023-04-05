package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.quizapp.adapter.LiveContesAdapter;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.LiveQuizesModel;
import com.example.quizapp.network.ApiInterFace;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveQuizes extends AppCompatActivity implements LiveContesAdapter.LiveQuizRedirect, BottomNavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;

    ApiInterFace apiInterFace;

    List<LiveQuizesModel> liveQuizesModelList=new ArrayList<>();

    private BottomNavigationView bottomNavigationView;

    public  void loadDynamicQuizes()
    {

        apiInterFace.getLiveQuizes().enqueue(new Callback<List<LiveQuizesModel>>() {
            @Override
            public void onResponse(Call<List<LiveQuizesModel>> call, Response<List<LiveQuizesModel>> response) {
                if(response.body()!=null)
                {
                    liveQuizesModelList.addAll(response.body());
                    recyclerView.setAdapter(new LiveContesAdapter(liveQuizesModelList,LiveQuizes.this));
                    recyclerView.setLayoutManager(new LinearLayoutManager(LiveQuizes.this, LinearLayoutManager.VERTICAL, false));

                }
            }

            @Override
            public void onFailure(Call<List<LiveQuizesModel>> call, Throwable t) {
                Toast.makeText(LiveQuizes.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                System.out.println(t.getLocalizedMessage());

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_quizes);

        bottomNavigationView = findViewById(R.id.bottomNavigationView_live);
        bottomNavigationView.setOnNavigationItemSelectedListener(LiveQuizes.this);
        bottomNavigationView.setSelectedItemId(R.id.dynamic);
        recyclerView=findViewById(R.id.rv_live_quiz);


        apiInterFace=((ApplicationClass)getApplication()).DynamicContestRetrofit.create(ApiInterFace.class);
        loadDynamicQuizes();





    }

    @Override
    public void onLivePlayRedirectDynamic(LiveQuizesModel liveQuizes) {

        Intent newContest=new Intent(LiveQuizes.this, DynamicQuizPlaying.class);
        newContest.putExtra("newContest",liveQuizes);
        startActivity(newContest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.quiz:
                startActivity(new Intent(LiveQuizes.this,ContestsActivity.class));
                finish();
                break;
            case R.id.upcomingcontests:
                startActivity(new Intent(LiveQuizes.this,DynamicQuizActivity.class));
                finish();
                break;
            case R.id.dynamic:
                break;

            case R.id.profile:
                Intent cartIntent=new Intent(LiveQuizes.this,UserProfileActivity.class);
                startActivity(cartIntent);
                finish();

                break;
        }




        return false;
    }

//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//        super.onPointerCaptureChanged(hasCapture);
//    }
}