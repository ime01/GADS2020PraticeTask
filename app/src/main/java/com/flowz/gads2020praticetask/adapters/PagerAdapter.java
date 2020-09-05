package com.flowz.gads2020praticetask.adapters;

import com.flowz.gads2020praticetask.display.HoursFragment;
import com.flowz.gads2020praticetask.display.SkillsIQFragment;

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
            return new SkillsIQFragment();
//            case 1:
        }
        return new HoursFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
