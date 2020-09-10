package com.flowz.gads2020praticetask.display.leaderboard.hoursfragment;

import android.app.Application;

import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.repository.HoursRepository;
import com.flowz.gads2020praticetask.roomdb.hoursdatabse.dbHoursModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class HoursViewModel extends AndroidViewModel{

//    public MutableLiveData<ArrayList<HoursModel>> learnersList = new MutableLiveData<>();

    public LiveData<List<dbHoursModel>> allLearnersHours;
    public HoursRepository hoursRepository;

    public HoursViewModel (Application application){

        super(application);

        hoursRepository = new HoursRepository(application);
        allLearnersHours = hoursRepository.getAllHours();
    }

    LiveData<List<dbHoursModel>> getAllLearnersHours(){

        return allLearnersHours;
    }

    public void insert(dbHoursModel dbHoursModel){
        hoursRepository.insert(dbHoursModel);
    }




//    public MutableLiveData<ArrayList<HoursModel>> getHoursFromApi (){
//
//        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);
//
//        Call<ArrayList<HoursModel>> getHours = retrofitInterface.getHours();
//
//        getHours.enqueue(new Callback<ArrayList<HoursModel>>() {
//            @Override
//            public void onResponse(Call<ArrayList<HoursModel>> call, Response<ArrayList<HoursModel>> response) {
//
//                if (response != null){
//
//                    ArrayList<HoursModel> hours = response.body();
//                    learnersList.setValue(hours);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<HoursModel>> call, Throwable t) {
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
