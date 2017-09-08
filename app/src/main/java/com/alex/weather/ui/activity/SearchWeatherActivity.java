package com.alex.weather.ui.activity;

import android.location.Location;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.alex.weather.R;
import com.alex.weather.ui.fragment.MainWeatherFragment;
import com.alex.weather.ui.fragment.SearchCityFragment;

public class SearchWeatherActivity extends AppCompatActivity implements SearchCityFragment.SelectCityListener {

    private final String TAG = "test SearchWeatherAct";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_weather);
        initActionBar();
        showFragment(SearchCityFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }
    }

    private void initActionBar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setCustomView(R.layout.action_bar);
            TextView titleActionBar = getSupportActionBar().getCustomView().findViewById(R.id.title);
            titleActionBar.setText(R.string.search_weather);
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, fragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    public void onSelectItem(Location location) {
        showFragment(MainWeatherFragment.newInstance(location));
    }


}
