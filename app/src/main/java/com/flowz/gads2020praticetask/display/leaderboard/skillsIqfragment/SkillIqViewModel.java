package com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment;

import android.app.Application;

import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.repository.SkillIqRepository;
import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.dbSkilliqModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class SkillIqViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<SkilliqModel>> learnersList = new MutableLiveData<>();
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




//    public MutableLiveData<ArrayList<SkilliqModel>> getDatafromApi(){
//
//        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);
//
//        Call<ArrayList<SkilliqModel>> getSkills = retrofitInterface.getSkillIq();
//
//        getSkills.enqueue(new Callback<ArrayList<SkilliqModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<SkilliqModel>> call, Response<ArrayList<SkilliqModel>> response) {
//
//                if (response != null){
//
//                    ArrayList<SkilliqModel> iqScores = response.body();
//
//                    learnersList.setValue(iqScores);
//
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<SkilliqModel>> call, Throwable t) {
//
////                Toast.makeText(this.getContext(), "Network Call failed" + t, Toast.LENGTH_SHORT).show();
//                Log.e("network failed", "Network Call failed" + t);
//
//            }
//        });
//
//        return learnersList;
//    }

}
