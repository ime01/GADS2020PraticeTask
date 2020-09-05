package com.flowz.gads2020praticetask.display;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ActivityNavigator;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flowz.gads2020praticetask.R;
import com.flowz.gads2020praticetask.adapters.PagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import static androidx.navigation.ui.NavigationUI.setupActionBarWithNavController;

public class LeaderBoardActivity extends AppCompatActivity {

//    NavController navController = Navigation.findNavController(LeaderBoardActivity.this, R.id.nav_graph);
   // NavController navController = Navigation.findNavController(findViewById(R.id.nav_graph));

    Button submit;
    TextView loading;
    ProgressBar progressBar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_toolbar);

        submit = findViewById(R.id.submit);

        ViewPager2 viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter(this));
//
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position){
                    case 0: {
                        tab.setText("Learning Leaders");
                        break;
                    }
                    case 1:{
                        tab.setText("Skill IQ Leaders");
                        break;
                    }
                }

            }
        });

        tabLayoutMediator.attach();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               // Navigation.findNavController(LeaderBoardActivity.this, R.id.nav_host_fragment).navigate(R.id.action_leaderBoardActivity_to_submitFragment);
                ActivityNavigator activityNavigator = new ActivityNavigator(    LeaderBoardActivity.this);
                activityNavigator.navigate(activityNavigator.createDestination().setIntent(new Intent(LeaderBoardActivity.this, SubmitActivity.class)), null,null,null);
            }
        });
    }





}
