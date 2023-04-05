package com.example.quizapp.model;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DynamicContest implements Serializable {

    @SerializedName("quizMasterId")
    private String quizMasterId;

    @SerializedName("contestId")
    private String contestId;

    @SerializedName("noOfQuestions")
    private int noOfQuestions;

    @SerializedName("durationOfContest")
    private int durationOfContest;

    @SerializedName("quizType")
    private String quizType;

    @SerializedName("startTime")
    private long startTime;

    @SerializedName("endTime")
    private long endTime;

    @SerializedName("contestName")
    private String contestName;

    @SerializedName("moderatorId")
    private String moderatorId;

    @SerializedName("contentCategory")
    private String contentCategory;

    public void setQuizMasterId(String quizMasterId){
        this.quizMasterId = quizMasterId;
    }

    public String getQuizMasterId(){
        return quizMasterId;
    }

    public void setContestId(String contestId){
        this.contestId = contestId;
    }

    public String getContestId(){
        return contestId;
    }

    public void setNoOfQuestions(int noOfQuestions){
        this.noOfQuestions = noOfQuestions;
    }

    public int getNoOfQuestions(){
        return noOfQuestions;
    }

    public void setDurationOfContest(int durationOfContest){
        this.durationOfContest = durationOfContest;
    }

    public int getDurationOfContest(){
        return durationOfContest;
    }

    public void setQuizType(String quizType){
        this.quizType = quizType;
    }

    public String getQuizType(){
        return quizType;
    }

    public void setStartTime(long startTime){
        this.startTime = startTime;
    }

    public long getStartTime(){
        return startTime;
    }

    public void setEndTime(long endTime){
        this.endTime = endTime;
    }

    public long getEndTime(){
        return endTime;
    }

    public void setContestName(String contestName){
        this.contestName = contestName;
    }

    public String getContestName(){
        return contestName;
    }

    public void setModeratorId(String moderatorId){
        this.moderatorId = moderatorId;
    }

    public String getModeratorId(){
        return moderatorId;
    }

    public void setContentCategory(String contentCategory){
        this.contentCategory = contentCategory;
    }

    public String getContentCategory(){
        return contentCategory;
    }

    @Override
    public String toString(){
        return
                "DynamicContest{" +
                        "quizMasterId = '" + quizMasterId + '\'' +
                        ",contestId = '" + contestId + '\'' +
                        ",noOfQuestions = '" + noOfQuestions + '\'' +
                        ",durationOfContest = '" + durationOfContest + '\'' +
                        ",quizType = '" + quizType + '\'' +
                        ",startTime = '" + startTime + '\'' +
                        ",endTime = '" + endTime + '\'' +
                        ",contestName = '" + contestName + '\'' +
                        ",moderatorId = '" + moderatorId + '\'' +
                        ",contentCategory = '" + contentCategory + '\'' +
                        "}";
    }
}
