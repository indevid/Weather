package com.alex.weather.ui.fragment;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alex.weather.R;
import com.alex.weather.ui.adapter.ViewTubPagerAdapterWeather;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 07.09.2017.
 */

public class MainWeatherFragment extends Fragment {

    private final String TAG = "test MainWeatherFrag";
    private static final String ARG_Location = "Location";
    private Location location;

    public MainWeatherFragment() {
        // Required empty public constructor
    }

    public static MainWeatherFragment newInstance(Location location) {
        MainWeatherFragment fragment = new MainWeatherFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_Location, location);
        fragment.setArguments(args);
        return fragment;
    }

    private Context ctx;

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.tabs) TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_weather, container, false);
        ButterKnife.bind(this, view);
        ctx = getActivity();
        setupViewPager(viewPager);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            location = getArguments().getParcelable(ARG_Location);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewTubPagerAdapterWeather adapter = new ViewTubPagerAdapterWeather(getChildFragmentManager());
        adapter.addFrag(WeatherDayFragment.newInstance(location), getResources().getString(R.string.weather_title1));
        adapter.addFrag(WeatherWeekFragment.newInstance(location), getResources().getString(R.string.weather_title2));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }


}
