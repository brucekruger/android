package com.example.vacancies;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewFragment;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
@SuppressLint("NewApi")
public abstract class WebFragment extends WebViewFragment {

	abstract String getPage();

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRetainInstance(true);
	}

	@SuppressLint({ "SetJavaScriptEnabled", "NewApi" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View result = super.onCreateView(inflater, container,
				savedInstanceState);
		getWebView().getSettings().setJavaScriptEnabled(true);
		getWebView().getSettings().setSupportZoom(true);
		getWebView().getSettings().setBuiltInZoomControls(true);
		getWebView().loadUrl(getPage());
		return (result);
	}
}
