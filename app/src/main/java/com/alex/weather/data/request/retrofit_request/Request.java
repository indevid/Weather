package com.alex.weather.data.request.retrofit_request;


import android.location.Location;
import android.util.Log;

import com.alex.weather.data.request.RequestFactory;
import com.alex.weather.data.request.retrofit_answers.AnswerForecast10day;
import com.alex.weather.data.request.retrofit_answers.AnswerForecastHourly;
import com.alex.weather.data.request.retrofit_callback.CallbackObject;

import retrofit2.Call;

public class Request {

    private Request() {
    }

    private static Request ourInstance = new Request();

    public static Request getInstance() {
        return ourInstance;
    }

    private String TAG = "test Request";

    public void forecast10day(Location location, CallbackObject.CallbackGetObject callbackGetObject) {
        Log.d(TAG, "forecast10day");
        String lat_lon = location.getLatitude()+","+location.getLongitude();
        Call<AnswerForecast10day> call = RequestFactory.getRequestWunderground().forecast10day(lat_lon);
        call.enqueue(new CallbackObject<AnswerForecast10day>(callbackGetObject));
    }

    public void forecastHourly(Location location, CallbackObject.CallbackGetObject callbackGetObject) {
        Log.d(TAG, "forecastHourly");
        String lat_lon = location.getLatitude()+","+location.getLongitude();
        Call<AnswerForecastHourly> call = RequestFactory.getRequestWunderground().forecastHourly(lat_lon);
        call.enqueue(new CallbackObject<AnswerForecastHourly>(callbackGetObject));
    }
}
