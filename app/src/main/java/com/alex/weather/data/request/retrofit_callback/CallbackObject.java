package com.alex.weather.data.request.retrofit_callback;


import com.alex.weather.data.Constants;


import retrofit2.Call;
import retrofit2.Callback;


public class CallbackObject<T> implements Callback<T> {

    CallbackGetObject callbackGetObject;
    public CallbackObject(CallbackGetObject callbackGetObject) {
        this.callbackGetObject = callbackGetObject;
    }

    public interface CallbackGetObject {
        void onFailure();
        void onFailureConnection();
        void onSuccess(Object o);
    }

    @Override
    public void onResponse(Call<T> call, retrofit2.Response<T> response) {
        if (response.code() == Constants.SUCCESS_CODE) {
            callbackGetObject.onSuccess(response.body());
        } else{
            callbackGetObject.onFailure();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        callbackGetObject.onFailureConnection();
    }
}