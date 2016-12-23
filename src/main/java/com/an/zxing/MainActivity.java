package com.an.zxing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qicode_activity_main);

		new TitleBuilder(this).setTitleText("首页")
				.setLeftTxt(R.drawable.qrcode_btn_back_top_sel)
				.setLeftOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();

					}
				}).setRightTxt("扫一扫")
				.setRightOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {

						Intent intent = new Intent(MainActivity.this,
								ShowActivity.class);
						startActivity(intent);

					}
				});

	}
}
