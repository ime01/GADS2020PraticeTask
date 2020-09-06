package com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment;

import android.util.Log;
import android.view.View;

import com.flowz.gads2020praticetask.adapters.SkillIqAdapter;
import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIqViewModel extends ViewModel {

    public MutableLiveData<ArrayList<SkilliqModel>> learnersList = new MutableLiveData<>();


    public MutableLiveData<ArrayList<SkilliqModel>> getDatafromApi(){

        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<SkilliqModel>> getSkills = retrofitInterface.getSkillIq();

        getSkills.enqueue(new Callback<ArrayList<SkilliqModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SkilliqModel>> call, Response<ArrayList<SkilliqModel>> response) {

                if (response != null){

                    ArrayList<SkilliqModel> iqScores = response.body();

                    learnersList.setValue(iqScores);

                }

            }

            @Override
            public void onFailure(Call<ArrayList<SkilliqModel>> call, Throwable t) {

//                Toast.makeText(this.getContext(), "Network Call failed" + t, Toast.LENGTH_SHORT).show();
                Log.e("network failed", "Network Call failed" + t);

            }
        });

        return learnersList;
    }

}
