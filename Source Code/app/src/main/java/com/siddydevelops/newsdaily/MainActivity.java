package com.siddydevelops.newsdaily;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import com.siddydevelops.customlottiedialogbox.CustomLottieDialog;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem mHome, mSports, mHealth, mTech, mEntertainment, mScience;
    PagerAdapter pagerAdapter;
    Toolbar mToolbar;

    CustomLottieDialog customLottieDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getSupportActionBar().hide();

        customLottieDialog = new CustomLottieDialog(MainActivity.this, "LO04");
        customLottieDialog.setLoadingText("Fetching News...");
        customLottieDialog.setLottieBackgroundColor("#E64556");
        customLottieDialog.setLoadingTextColor("#FFFFFF");
        customLottieDialog.show();

        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mHome = findViewById(R.id.home);
        mSports = findViewById(R.id.sports);
        mEntertainment = findViewById(R.id.entertainment);
        mScience = findViewById(R.id.science);
        mHealth = findViewById(R.id.health);
        mTech = findViewById(R.id.technology);

        ViewPager2 viewPager = findViewById(R.id.fragmentContainer);
        tabLayout = findViewById(R.id.include);

        pagerAdapter = new PagerAdapter(this, 6);
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getPosition() == 0 || tab.getPosition() == 1 || tab.getPosition() == 2 || tab.getPosition() == 3 || tab.getPosition() == 4 || tab.getPosition() == 5)
                {
                    pagerAdapter.notifyDataSetChanged();
                    customLottieDialog.show();
                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            customLottieDialog.dismiss();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                customLottieDialog.dismiss();
            }
        }, 2000);
    }
}
