package com.flowz.gads2020praticetask.display.leaderboard.hoursfragment;

import android.app.Application;
import android.util.Log;
import android.view.View;

import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;
import com.flowz.gads2020praticetask.repository.HoursRepository;
import com.flowz.gads2020praticetask.roomdb.hoursdatabse.dbHoursModel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

    public void fetchDataFromNetwork(){


        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<HoursModel>> getHours = retrofitInterface.getHours();


        getHours.enqueue(new Callback<List<HoursModel>>() {
            @Override
            public void onResponse(Call<List<HoursModel>> call, Response<List<HoursModel>> response) {

                if(response.isSuccessful()){
                    if (response != null){
                        Log.v("My tag", "Hours Network Request  Success " + response.code());

                        List<HoursModel> dbHoursList = response.body();

                        for (int i = 0; i< dbHoursList.size(); i++){

                            String name = dbHoursList.get(i).getName();
                            int hours = dbHoursList.get(i).getHours();
                            String country = dbHoursList.get(i).getCountry();
                            String badgeUrl = dbHoursList.get(i).getBadgeUrl();

//                       Assign the networkResults to RoomDataBase Object

                            dbHoursModel mydbHoursModel = new dbHoursModel(name,hours,country,badgeUrl);

                            hoursRepository.insert(mydbHoursModel);

                        }

                    }
                }

            }

            @Override
            public void onFailure(Call<List<HoursModel>> call, Throwable t) {

                Log.d("My tag", "Hours Network Request Failed " + t);
            }
        });

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
