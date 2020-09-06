package com.flowz.gads2020praticetask.display.leaderboard.hoursfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
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
import com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment.SkillIqViewModel;
import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HoursFragment extends Fragment {

    Button submit;
    TextView loading;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    HoursViewModel hoursViewModel;


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


        hoursViewModel = ViewModelProviders.of(getActivity()).get(HoursViewModel.class);

//        getDatafromApi();

        LiveData<ArrayList<HoursModel>> learnersList = hoursViewModel.getHoursFromApi();


        learnersList.observe(getViewLifecycleOwner(), new Observer<ArrayList<HoursModel>>() {
            @Override
            public void onChanged(ArrayList<HoursModel> skilliqModels) {

                loadFetchedData(skilliqModels);
            }
        });

        return view;

    }

    private void loadFetchedData(ArrayList<HoursModel> fetchedList){

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
}
