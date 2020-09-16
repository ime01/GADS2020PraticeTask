package com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;
import com.flowz.gads2020praticetask.repository.SkillIqRepository;
import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.dbSkilliqModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIqViewModel extends AndroidViewModel {

//    public MutableLiveData<ArrayList<SkilliqModel>> learnersList = new MutableLiveData<>();
    public LiveData<List<dbSkilliqModel>> allSkillsIq;
    public SkillIqRepository skillIqRepository;

    public SkillIqViewModel (Application application){

        super(application);

        skillIqRepository = new SkillIqRepository(application);
        allSkillsIq = skillIqRepository.getSkillIqScores();
    }

    LiveData<List<dbSkilliqModel>> getAllSkillsIq(){
        return allSkillsIq;
    }

    public void insert(dbSkilliqModel dbSkilliqModel){

        skillIqRepository.insert(dbSkilliqModel);
    }

    public void fetchDataFromNetwork(){


        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<SkilliqModel>> getSkills = retrofitInterface.getSkillIq();

        getSkills.enqueue(new Callback<List<SkilliqModel>>() {
            @Override
            public void onResponse(Call<List<SkilliqModel>> call, Response<List<SkilliqModel>> response) {

                if(response.isSuccessful()){
                    if (response != null){
                        Log.v("My tag", "Hours Network Request  Success " + response.code());

                        List<SkilliqModel> dbSkilliqModelsList = response.body();

                        for (int i = 0; i< dbSkilliqModelsList.size(); i++){

                            String name = dbSkilliqModelsList.get(i).getName();
                            int score = dbSkilliqModelsList.get(i).getScore();
                            String country = dbSkilliqModelsList.get(i).getCountry();
                            String badgeUrl = dbSkilliqModelsList.get(i).getBadgeUrl();

//                       Assign the networkResults to RoomDataBase Object

                            dbSkilliqModel mydbSkilliqModel = new dbSkilliqModel(name,score,country,badgeUrl);

                            skillIqRepository.insert(mydbSkilliqModel);


                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<List<SkilliqModel>> call, Throwable t) {

                Log.v("My tag", "SkillIq Network Request  Failed " + t);
            }
        });

    }

}
