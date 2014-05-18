package com.example.vacancies;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebFragment extends Fragment {

	private WebView mWebView;
	private boolean mIsWebViewAvailable;

	public WebFragment() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mWebView != null) {
			mWebView.destroy();
		}
		mWebView = new WebView(getActivity());
		mIsWebViewAvailable = true;
		
		return mWebView;
	}

	@TargetApi(11)
	@Override
	public void onPause() {
		super.onPause();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			mWebView.onPause();
		}
	}

	@TargetApi(11)
	@Override
	public void onResume() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			mWebView.onResume();
		}
		super.onResume();
	}

	@Override
	public void onDestroyView() {
		mIsWebViewAvailable = false;
		super.onDestroyView();
	}

	@Override
	public void onDestroy() {
		if (mWebView != null) {
			mWebView.destroy();
			mWebView = null;
		}
		super.onDestroy();
	}

	public WebView getWebView() {
		return mIsWebViewAvailable ? mWebView : null;
	}
}
