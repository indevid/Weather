package com.alex.weather.data;

import android.content.Context;

import com.alex.weather.R;


public class TextManager {
	private static final TextManager ourInstance = new TextManager();

	public static TextManager getInstance() {
		return ourInstance;
	}

	private TextManager() {
	}

	Context ctx;

	public void setCtx(Context ctx) {
		this.ctx = ctx;
	}

	public String getMessageErrorServer(){
		return ctx.getResources().getString(R.string.failure);
	}
	public String getMessageErrorConnection(){
		return ctx.getResources().getString(R.string.failure_connection);
	}

}
