package com.alex.weather.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.alex.weather.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "test MainActivity";

    @OnClick(R.id.btn_current)
    public void btn_current() {
        Log.d(TAG, "btn_current");
        Intent intent = new Intent(this, CurrentWeatherActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_search)
    public void btn_search() {
        Log.d(TAG, "btn_search");
        Intent intent = new Intent(this, SearchWeatherActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_weather);
        ButterKnife.bind(this);
    }

}
