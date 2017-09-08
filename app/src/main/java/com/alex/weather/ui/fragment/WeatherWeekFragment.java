package com.alex.weather.ui.fragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alex.mvp.helper.HelperProgressDialog;
import com.alex.mvp.presenter.WeatherWeekPresenter;
import com.alex.mvp.view.WeatherWeekView;
import com.alex.weather.R;
import com.alex.weather.data.request.retrofit_pojo.forecast10day.Forecastday_;
import com.alex.weather.ui.adapter.ListDayForecastRecyclerAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 07.09.2017.
 */

public class WeatherWeekFragment extends MvpAppCompatFragment implements WeatherWeekView{

    @InjectPresenter
    WeatherWeekPresenter weatherWeekPresenter;

    private final String TAG = "test WeatherWeekFrag";
    private static final String ARG_Location = "Location";
    private Location location;
    private HelperProgressDialog helperProgressDialog;

    @BindView(R.id.list_days)
    RecyclerView list_days;

    public WeatherWeekFragment() {
        // Required empty public constructor
    }

    public static WeatherWeekFragment newInstance(Location location) {
        WeatherWeekFragment fragment = new WeatherWeekFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_Location, location);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_week, container, false);
        ButterKnife.bind(this, view);
        helperProgressDialog = new HelperProgressDialog(getActivity());
        weatherWeekPresenter.forecast10day(location);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            location = getArguments().getParcelable(ARG_Location);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    ListDayForecastRecyclerAdapter listDayForecastRecyclerAdapter;


    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressDialog() {
        Log.d(TAG, "showProgressDialog: ");
        helperProgressDialog.showProgress();
    }

    @Override
    public void dismissProgressDialog() {
        Log.d(TAG, "dismissProgressDialog: ");
        helperProgressDialog.dismissProgress();
    }

    @Override
    public void showForecastDayList(List<Forecastday_> forecastDayList) {
        Log.d(TAG, "showForecastDayList: ");
        if (forecastDayList != null) {
            list_days.setLayoutManager(new LinearLayoutManager(getActivity()));
            listDayForecastRecyclerAdapter = new ListDayForecastRecyclerAdapter(forecastDayList);
            list_days.setAdapter(listDayForecastRecyclerAdapter);
        }
    }

}
