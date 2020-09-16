package com.flowz.gads2020praticetask.adapters;

import com.flowz.gads2020praticetask.display.leaderboard.hoursfragment.HoursFragment;
import com.flowz.gads2020praticetask.display.leaderboard.skillsIqfragment.SkillsIQFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HoursFragment();

//            case 1:
        }
        return new SkillsIQFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
