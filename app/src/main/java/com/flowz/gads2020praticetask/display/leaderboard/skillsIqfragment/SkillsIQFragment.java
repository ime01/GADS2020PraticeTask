package com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import jp.wasabeef.recyclerview.adapters.AlphaInAnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.adapters.SkillIqAdapter;
import com.flowz.gads2020praticetask.models.SkilliqModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillsIQFragment extends Fragment {

    Button submit;
    TextView loading;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    SkillIqViewModel skillIqViewModel;


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


        skillIqViewModel = ViewModelProviders.of(getActivity()).get(SkillIqViewModel.class);

//        getDatafromApi();

        LiveData<ArrayList<SkilliqModel>>  learnersList = skillIqViewModel.getDatafromApi();


        learnersList.observe(getViewLifecycleOwner(), new Observer<ArrayList<SkilliqModel>>() {
            @Override
            public void onChanged(ArrayList<SkilliqModel> skilliqModels) {

                loadFetchedData(skilliqModels);
            }
        });

        return view;




}


    private void loadFetchedData(ArrayList<SkilliqModel> fetchedList){

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


}
