package com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment;


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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.adapters.SkillIqAdapter;
import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;
import com.flowz.gads2020praticetask.roomdb.skilliqdatabase.dbSkilliqModel;

import org.threeten.bp.ZonedDateTime;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillsIQFragment extends Fragment {

    Button submit;
    TextView loading;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    SkillIqViewModel skillIqViewModel;
    List<dbSkilliqModel>  learnersList;


    public SkillsIQFragment() {
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

        skillIqViewModel = ViewModelProviders.of(this).get(SkillIqViewModel.class);


        getCurrentStudentHoursDetails();
//        fetchDataFromNetwork();


//        LiveData<ArrayList<SkilliqModel>>  learnersList = skillIqViewModel.getDatafromApi();
        skillIqViewModel.getAllSkillsIq().observe(this, new Observer<List<dbSkilliqModel>>() {
            @Override
            public void onChanged(List<dbSkilliqModel> dbSkilliqModels) {

                learnersList = dbSkilliqModels;
                loadFetchedData(learnersList);
            }
        });

//        learnersList.observe(getViewLifecycleOwner(), new Observer<ArrayList<SkilliqModel>>() {
//            @Override
//            public void onChanged(ArrayList<SkilliqModel> skilliqModels) {
//
//                loadFetchedData(skilliqModels);
//            }
//        });
//
        return view;
}

    private void getCurrentStudentHoursDetails(){

        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1))){

        if (isNetworkAvailable()){
//            fetchDataFromNetwork();
           skillIqViewModel.fetchDataFromNetwork();
            Toast.makeText(getActivity(), "Data Updated from network", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getActivity(), "No Network, Enable Internet Connection And Try Again", Toast.LENGTH_LONG).show();
        }

        }else {
            Toast.makeText(getActivity(), "Details Will be Updated when its 30 minutes more than the last GadsApplication it was updated", Toast.LENGTH_LONG).show();
        }

    }

    private Boolean isFetchCurrentNeeded(ZonedDateTime lastFetchTime){

        ZonedDateTime thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30);
        return lastFetchTime.isBefore(thirtyMinutesAgo);

    }

    private Boolean isNetworkAvailable(){

        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        return activeNetworkInfo!= null && activeNetworkInfo.isConnected();

    }




    private void loadFetchedData(List<dbSkilliqModel> fetchedList){

        SkillIqAdapter adapter = new SkillIqAdapter(fetchedList, this.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
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
