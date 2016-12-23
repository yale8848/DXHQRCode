package com.an.zxing;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class BaseActivity extends  Activity {

	protected String TAG;
	protected Toast toast;
	protected ProgressDialog dialog;

	/**
	 * 屏宽
	 * */
	protected int screenW;
	/**
	 * 屏高
	 * */
	protected int screenH;

	protected static final String URL_KEY = "path";

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		TAG = this.getClass().getSimpleName();

		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		requestWindowFeature(Window.FEATURE_NO_TITLE);

	}

	protected void intent2Activity(Class<? extends Activity> tarActivity,
			String url) {
		Intent intent = new Intent(this, tarActivity);
		intent.putExtra(URL_KEY, url);
		startActivity(intent);
	}

	/**
	 * 获取屏幕大小
	 * */
	private void initScreen() {

		DisplayMetrics dm = new DisplayMetrics();

		getWindowManager().getDefaultDisplay().getMetrics(dm);

		screenW = dm.widthPixels;
		screenH = dm.heightPixels;

	}

	protected void showToast(String msg) {
		if (toast == null) {
			toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
		} else {
			toast.setText(msg);
			toast.show();
		}

	}

	protected void showToast(int resId) {
		if (toast == null) {
			toast.makeText(this, getResources().getString(resId),
					Toast.LENGTH_SHORT).show();
		} else {
			toast.setText(getResources().getString(resId));
			toast.show();
		}

	}

	protected void showLog(String msg) {
		Log.d(TAG, msg);

	}

	private ProgressDialog getProgressDialog(String msg) {

		ProgressDialog progressDialog = new ProgressDialog(this);

		progressDialog.setIndeterminate(true);

		progressDialog.setMessage(msg);

		progressDialog.setCancelable(true);

		return progressDialog;

	}

	protected void showDialog(String msg) {
		dialog = getProgressDialog(msg);
		dialog.show();

	}

	protected void cancelDialog() {
		if (dialog != null) {
			dialog.cancel();
		}

	}

}
