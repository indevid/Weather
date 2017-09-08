package com.alex.weather.app;

import android.app.Application;

import com.alex.weather.data.TextManager;
import com.facebook.drawee.backends.pipeline.Fresco;

public class WeatherApp extends Application {

	@Override
	public void onCreate() {
		super.onCreate();
		Fresco.initialize(this);
		TextManager.getInstance().setCtx(getApplicationContext());
	}

}