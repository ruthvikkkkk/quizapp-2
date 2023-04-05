package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.quizapp.adapter.LeaderBoardAdapter;
import com.example.quizapp.application.ApplicationClass;
import com.example.quizapp.model.AdsResponse;
import com.example.quizapp.model.LeaderBoardModel;
import com.example.quizapp.model.Ranking;
import com.example.quizapp.network.ApiInterFace;
import com.google.android.gms.common.api.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderBoard extends AppCompatActivity {

    private RecyclerView recyclerView;

    private List<LeaderBoardModel> listLeaderBoard=new ArrayList<>();

    private ApiInterFace apiInterFace,adsapiInterFace;

    private ImageView imageAds;

    private AdsResponse adsResponse;

    private List<AdsResponse> adsResponseList=new ArrayList<>();

    private void loadLeaderBoard(String contestId)
    {

        apiInterFace.getLeaderBoardByContestId(contestId).enqueue(new Callback<Ranking>() {
            @Override
            public void onResponse(Call<Ranking> call, Response<Ranking> response) {
                if(response.body()!=null)
                {
                    listLeaderBoard.addAll(response.body().getListLeaderBoard());
                    recyclerView.setAdapter(new LeaderBoardAdapter(listLeaderBoard));
                    recyclerView.setLayoutManager(new LinearLayoutManager(LeaderBoard.this, LinearLayoutManager.VERTICAL, false));
                }else{
                    Toast.makeText(LeaderBoard.this, "emptyResponse", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ranking> call, Throwable t) {

                Toast.makeText(LeaderBoard.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void loadads(String userId)
    {
        Toast.makeText(this, "inside load ads", Toast.LENGTH_SHORT).show();

        adsapiInterFace.getAds(userId).enqueue(new Callback<List<AdsResponse>>() {
            @Override
            public void onResponse(Call<List<AdsResponse>> call, Response<List<AdsResponse>> response) {
                if(response.body()!=null){

                    adsResponseList.addAll(response.body());
                    Glide.with(imageAds.getContext())
                            .load(adsResponseList.get(0).getUrl())
                            .centerCrop()
                            .placeholder(R.drawable.baseline_play_arrow_24)
                            .into(imageAds);
                }else{
                    Toast.makeText(LeaderBoard.this, "empty response for ads", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdsResponse>> call, Throwable t) {

                Toast.makeText(LeaderBoard.this, t.getLocalizedMessage()+"failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        imageAds=findViewById(R.id.img_ads);


        recyclerView=findViewById(R.id.rv_leader);
        apiInterFace=((ApplicationClass)getApplication()).leaderBoardRetrofit.create(ApiInterFace.class);
        adsapiInterFace=((ApplicationClass)getApplication()).AdsRetrofit.create(ApiInterFace.class);


        SharedPreferences sharedPreferences=getSharedPreferences("LoginCredentialsPref",MODE_PRIVATE);
        String userId=sharedPreferences.getString("userId","defaultUser");
        //loadads(userId);


        adsapiInterFace.getAds(userId).enqueue(new Callback<List<AdsResponse>>() {
            @Override
            public void onResponse(Call<List<AdsResponse>> call, Response<List<AdsResponse>> response) {
                if(response.body()!=null){

                    adsResponseList.addAll(response.body());
                    Glide.with(imageAds.getContext())
                            .load(adsResponseList.get(0).getUrl())
                            .centerCrop()
                            .placeholder(R.drawable.baseline_play_arrow_24)
                            .into(imageAds);

                    Intent contestIntent=getIntent();
                    loadLeaderBoard(contestIntent.getStringExtra("contestId"));
                }else{
                    Toast.makeText(LeaderBoard.this, "empty response for ads", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AdsResponse>> call, Throwable t) {

                Toast.makeText(LeaderBoard.this, t.getLocalizedMessage()+"failed", Toast.LENGTH_SHORT).show();
            }
        });





    }
}