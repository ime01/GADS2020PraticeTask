package com.flowz.gads2020praticetask.display.leaderboard.hoursfragment;

import android.util.Log;

import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;

import java.util.ArrayList;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HoursViewModel extends ViewModel {

    public MutableLiveData<ArrayList<HoursModel>> learnersList = new MutableLiveData<>();


    public MutableLiveData<ArrayList<HoursModel>> getHoursFromApi (){

        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<HoursModel>> getHours = retrofitInterface.getHours();

        getHours.enqueue(new Callback<ArrayList<HoursModel>>() {
            @Override
            public void onResponse(Call<ArrayList<HoursModel>> call, Response<ArrayList<HoursModel>> response) {

                if (response != null){

                    ArrayList<HoursModel> hours = response.body();
                    learnersList.setValue(hours);

                }
            }

            @Override
            public void onFailure(Call<ArrayList<HoursModel>> call, Throwable t) {

//                Toast.makeText(this.getContext(), "Network Call failed" + t, Toast.LENGTH_SHORT).show();
                Log.e("network failed", "Network Call failed" + t);

            }
        });

        return learnersList;
    }
}
