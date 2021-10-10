package com.siddydevelops.newsdaily;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.siddydevelops.newsdaily.fragments.EntertainmentFragment;
import com.siddydevelops.newsdaily.fragments.HealthFragment;
import com.siddydevelops.newsdaily.fragments.HomeFragment;
import com.siddydevelops.newsdaily.fragments.ScienceFragment;
import com.siddydevelops.newsdaily.fragments.SportsFragment;
import com.siddydevelops.newsdaily.fragments.TechnologyFragment;

public class PagerAdapter extends FragmentPagerAdapter {

    int tabCount;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new SportsFragment();
            case 2:
                return new HealthFragment();
            case 3:
                return new ScienceFragment();
            case 4:
                return new EntertainmentFragment();
            case 5:
                return new TechnologyFragment();
            default:
                throw new RuntimeException("position not found");
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
