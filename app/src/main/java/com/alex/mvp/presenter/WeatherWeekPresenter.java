package com.alex.mvp.presenter;

import android.location.Location;
import android.util.Log;

import com.alex.mvp.view.WeatherWeekView;
import com.alex.weather.data.TextManager;
import com.alex.weather.data.request.retrofit_answers.AnswerForecast10day;
import com.alex.weather.data.request.retrofit_callback.CallbackObject;
import com.alex.weather.data.request.retrofit_pojo.forecast10day.Forecastday_;
import com.alex.weather.data.request.retrofit_request.Request;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

/**
 * Created by Alex on 08.09.2017.
 */
@InjectViewState
public class WeatherWeekPresenter  extends MvpPresenter<WeatherWeekView> {

    private final String TAG = "test WeatherWeekPr";

    public void forecast10day(Location location) {
        getViewState().showProgressDialog();
        Request.getInstance().forecast10day(location, new CallbackObject.CallbackGetObject() {
            @Override
            public void onFailure() {
                Log.d(TAG, "onFailure");
                getViewState().dismissProgressDialog();
                getViewState().showErrorMessage(TextManager.getInstance().getMessageErrorServer());
            }

            @Override
            public void onFailureConnection() {
                Log.d(TAG, "onFailureConnection");
                getViewState().dismissProgressDialog();
                getViewState().showErrorMessage(TextManager.getInstance().getMessageErrorConnection());
            }

            @Override
            public void onSuccess(Object o) {
                Log.d(TAG, "onSuccess");
                getViewState().dismissProgressDialog();
                AnswerForecast10day answerForecast10day = (AnswerForecast10day) o;
                List<Forecastday_> forecastDayList = answerForecast10day.getForecast().getSimpleforecast().getForecastday();
                while (forecastDayList.size() > 7) {
                    forecastDayList.remove(forecastDayList.size() - 1);
                }
                getViewState().showForecastDayList(forecastDayList);
            }
        });

    }


}
