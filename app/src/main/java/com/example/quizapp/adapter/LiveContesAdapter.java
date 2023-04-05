package com.example.quizapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapp.R;
import com.example.quizapp.model.LiveQuizesModel;

import java.util.List;

public class LiveContesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<LiveQuizesModel> liveQuizesList;

    private LiveQuizRedirect liveQuizRedirect;

    public LiveContesAdapter(List<LiveQuizesModel> liveQuizesList,LiveQuizRedirect liveQuizRedirect)
    {
        this.liveQuizesList=liveQuizesList;
        this.liveQuizRedirect=liveQuizRedirect;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_contest_item,parent,false);

        return new LiveContesAdapter.CustomLiveQuizes(view);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((LiveContesAdapter.CustomLiveQuizes)holder).onBind(liveQuizesList.get(position));
    }

    @Override
    public int getItemCount() {
        return liveQuizesList.size();
    }
    public class CustomLiveQuizes extends RecyclerView.ViewHolder{

        View itemView;

        TextView contestName,contestCategory,contestDuration,contestTime;


        public CustomLiveQuizes(@NonNull View itemView) {
            super(itemView);
            this.itemView=itemView;
            itemView.findViewById(R.id.btn_play).setActivated(true);
            itemView.findViewById(R.id.btn_play).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    liveQuizRedirect.onLivePlayRedirectDynamic(liveQuizesList.get(getAdapterPosition()));
                }
            });
        }

        public void onBind(LiveQuizesModel liveQuizes)
        {

            contestName=itemView.findViewById(R.id.dy_tv_contest_name);
            contestCategory=itemView.findViewById(R.id.dy_tv_category);
            contestDuration=itemView.findViewById(R.id.dy_tv_contest_duration);
            contestTime = itemView.findViewById(R.id.dy_tv_time_to_play);

            contestName.setText(liveQuizes.getContestName());
            contestCategory.setText(liveQuizes.getContentCategory());
            contestDuration.setText(liveQuizes.getNoOfQuestions()+"");
            contestTime.setText("Stared!!");

        }


    }

    public interface LiveQuizRedirect
    {
        public void onLivePlayRedirectDynamic( LiveQuizesModel liveQuizes);

    }

}
