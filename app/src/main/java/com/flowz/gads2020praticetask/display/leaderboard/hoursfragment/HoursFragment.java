package com.flowz.gads2020praticetask.display.leaderboard.hoursfragment;


import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.adapters.HoursAdapter;
import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;
import com.flowz.gads2020praticetask.roomdb.hoursdatabse.dbHoursModel;
import com.jakewharton.threetenabp.AndroidThreeTen;

import org.threeten.bp.ZonedDateTime;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoursFragment extends Fragment {

    Button submit;
    TextView loading;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    HoursViewModel hoursViewModel;
    List<dbHoursModel> hoursList;
    Application application;


    public HoursFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.skillsiq_fragment, container, false);

        submit = view.findViewById(R.id.submit);
        loading = view.findViewById(R.id.textView2);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recycler);




        hoursViewModel = ViewModelProviders.of(this).get(HoursViewModel.class);


//        fetchDataFromNetwork();

        getCurrentStudentHoursDetails();

        hoursViewModel.getAllLearnersHours().observe(this, new Observer<List<dbHoursModel>>() {
            @Override
            public void onChanged(List<dbHoursModel> dbHoursModels) {

                hoursList = dbHoursModels;
                loadFetchedData(hoursList);

            }
        });

        return view;

    }

    private void getCurrentStudentHoursDetails(){

//        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))){

            if (isNetworkAvailable()){
                fetchDataFromNetwork();

            }else {
                Toast.makeText(getActivity(), "No Network, Enable Internet Connection And Try Again", Toast.LENGTH_LONG).show();
            }

//        }else {
//            Toast.makeText(getActivity(), "Details Will be Updated when its 30 minutes more than the last time it was updated", Toast.LENGTH_LONG).show();
//        }

    }

    private Boolean isNetworkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo!= null && activeNetworkInfo.isConnected();

    }

//    public Instant now() {
//        return Instant.now();
//    }
//
//    public ZonedDateTime hereAndNow() {
//        return ZonedDateTime.ofInstant(now(), ZoneId.systemDefault());
//    }


    private Boolean isFetchCurrentNeeded(ZonedDateTime lastFetchTime){

        ZonedDateTime thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30);
        return lastFetchTime.isBefore(thirtyMinutesAgo);

    }


    private void fetchDataFromNetwork(){

        progressBar.setVisibility(View.VISIBLE);

        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<HoursModel>> getHours = retrofitInterface.getHours();


      getHours.enqueue(new Callback<List<HoursModel>>() {
          @Override
          public void onResponse(Call<List<HoursModel>> call, Response<List<HoursModel>> response) {

              if(response.isSuccessful()){
                  if (response != null){

                      List<HoursModel> dbHoursList = response.body();

                      for (int i = 0; i< dbHoursList.size(); i++){

                          String name = dbHoursList.get(i).getName();
                          int hours = dbHoursList.get(i).getHours();
                          String country = dbHoursList.get(i).getCountry();
                          String badgeUrl = dbHoursList.get(i).getBadgeUrl();

//                       Assign the networkResults to RoomDataBase Object

                          dbHoursModel mydbHoursModel = new dbHoursModel(name,hours,country,badgeUrl);

                          hoursViewModel.insert(mydbHoursModel);

                          progressBar.setVisibility(View.GONE);

                      }

                  }
              }

          }

          @Override
          public void onFailure(Call<List<HoursModel>> call, Throwable t) {

              netWorkFailedToast();
              Log.d("My tag", "Hours Network Request Failed " + t);
          }
      });

    }


    private void loadFetchedData(List<dbHoursModel> fetchedList){

        HoursAdapter adapter = new HoursAdapter(fetchedList, this.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);

//        Added Animation to make recyclerView swipe pretty cool

        AlphaInAnimationAdapter adapter1 = new AlphaInAnimationAdapter(adapter);
        ScaleInAnimationAdapter sAdapter = new ScaleInAnimationAdapter(adapter1);
        recyclerView.setAdapter(sAdapter);
        progressBar.setVisibility(View.GONE);
        loading.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);

    }

    private void netWorkFailedToast(){

        Toast.makeText(getActivity(), "SkillIQ network request failed", Toast.LENGTH_LONG).show();

    }
}
