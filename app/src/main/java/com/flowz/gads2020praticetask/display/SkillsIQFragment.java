package com.flowz.gads2020praticetask.display;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
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
import com.flowz.gads2020praticetask.adapters.SkillIqAdapter;
import com.flowz.gads2020praticetask.models.HoursModel;
import com.flowz.gads2020praticetask.models.SkilliqModel;
import com.flowz.gads2020praticetask.network.get.ApiClient;
import com.flowz.gads2020praticetask.network.get.ApiInterface;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkillsIQFragment extends Fragment {

    Button submit;
    TextView loading;
    ProgressBar progressBar;
    RecyclerView recyclerView;


    public SkillsIQFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.skillsiq_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        submit = view.findViewById(R.id.submit);
        loading = view.findViewById(R.id.textView2);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recycler);

        getDatafromApi();
    }


    public void getDatafromApi(){

        ApiInterface retrofitInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<ArrayList<SkilliqModel>> getSkills = retrofitInterface.getSkillIq();

        getSkills.enqueue(new Callback<ArrayList<SkilliqModel>>() {
            @Override
            public void onResponse(Call<ArrayList<SkilliqModel>> call, Response<ArrayList<SkilliqModel>> response) {

                if (response != null){

                    ArrayList<SkilliqModel> iqScores = response.body();
                    loadFetchedData(iqScores);
                }

            }

            @Override
            public void onFailure(Call<ArrayList<SkilliqModel>> call, Throwable t) {

//                Toast.makeText(this.getContext(), "Network Call failed" + t, Toast.LENGTH_SHORT).show();
                Log.e("network failed", "Network Call failed" + t);

            }
        });

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
