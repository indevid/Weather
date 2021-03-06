package com.alex.mvp.presenter;

import android.location.Location;
import android.util.Log;

import com.alex.mvp.view.WeatherDayView;
import com.alex.weather.data.TextManager;
import com.alex.weather.data.request.retrofit_answers.AnswerForecastHourly;
import com.alex.weather.data.request.retrofit_callback.CallbackObject;
import com.alex.weather.data.request.retrofit_pojo.forecast_hourly.HourlyForecast;
import com.alex.weather.data.request.retrofit_request.Request;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Alex on 08.09.2017.
 */
@InjectViewState
public class WeatherDayPresenter  extends MvpPresenter<WeatherDayView> {

    private final String TAG = "test WeatherDayPr";

    private Call call;

    public void forecastHourly(Location location) {
        getViewState().showProgressDialog();
         call = Request.getInstance().forecastHourly(location, new CallbackObject.CallbackGetObject<AnswerForecastHourly>() {
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
            public void onSuccess(AnswerForecastHourly answerForecastHourly) {
                Log.d(TAG, "onSuccess");
                getViewState().dismissProgressDialog();
                List<HourlyForecast> hourlyForecastList = answerForecastHourly.getHourlyForecast();
                while (hourlyForecastList.size()> 24){
                    hourlyForecastList.remove(hourlyForecastList.size()-1);
                }
                getViewState().showHourlyForecastList(hourlyForecastList);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(call.isExecuted()){
            call.cancel();
        }
    }
}
