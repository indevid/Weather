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
import com.alex.mvp.presenter.WeatherDayPresenter;
import com.alex.mvp.view.WeatherDayView;
import com.alex.weather.R;
import com.alex.weather.data.request.retrofit_answers.AnswerForecastHourly;
import com.alex.weather.data.request.retrofit_callback.CallbackObject;
import com.alex.weather.data.request.retrofit_pojo.forecast_hourly.HourlyForecast;
import com.alex.weather.data.request.retrofit_request.Request;
import com.alex.weather.ui.adapter.ListHourlyForecastRecyclerAdapter;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alex on 07.09.2017.
 */

public class WeatherDayFragment extends MvpAppCompatFragment implements WeatherDayView {

    @InjectPresenter
    WeatherDayPresenter weatherDayPresenter;

    private final String TAG = "test WeatherDayFrag";
    private static final String ARG_Location = "Location";
    private Location location;
    private HelperProgressDialog helperProgressDialog;

    public WeatherDayFragment() {
        // Required empty public constructor
    }

    public static WeatherDayFragment newInstance(Location location) {
        WeatherDayFragment fragment = new WeatherDayFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_Location, location);
        fragment.setArguments(args);
        return fragment;
    }


    @BindView(R.id.list_hours)
    RecyclerView list_days;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_day, container, false);
        ButterKnife.bind(this, view);
        helperProgressDialog = new HelperProgressDialog(getActivity());
        Log.e(TAG, "onCreateView: -----------------");
        weatherDayPresenter.forecastHourly(location);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            location = getArguments().getParcelable(ARG_Location);
        }
    }


    ListHourlyForecastRecyclerAdapter listHourlyForecastRecyclerAdapter;


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
    public void showHourlyForecastList(List<HourlyForecast> hourlyForecastList) {
        Log.d(TAG, "showHourlyForecastList: ");
        if (hourlyForecastList != null) {
            list_days.setLayoutManager(new LinearLayoutManager(getActivity()));
            listHourlyForecastRecyclerAdapter = new ListHourlyForecastRecyclerAdapter(hourlyForecastList);
            list_days.setAdapter(listHourlyForecastRecyclerAdapter);
        }
    }

}
