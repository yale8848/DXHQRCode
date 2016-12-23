package com.an.zxing;

import java.net.URL;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.MimeTypeMap;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 显示扫描结果的界面
 * */

public class ShowActivity extends BaseActivity implements OnClickListener {

	public final static int SCANNIN_GREQUEST_CODE = 1;
	public final static String QRCODE_BACK_KEY = "result";
	public final static String QRCODE_BACK_MY_KEY = "bc_qrcode";

	private TextView txt_what;
	private TextView txt_show;
	private TextView txt_link;
	private TextView txt_copy;
	private TextView txt_share;

	private TextView txt_null;
	private LinearLayout layout_show;

	/**
	 * 链接
	 * */
	private String links;
	/**
	 * 音频
	 * */
	private String audioPath;

	/**
	 * 视频
	 * */
	private String videoPath;
	/**
	 * 复制
	 * */
	private String copyString;

	private String key;

	private String video[] = { ".mp4", "3gp", ".rmvb", ".f4v", ".flv", ".avi" };
	private String audio[] = { ".mp3", ".m4a" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.qrcode_activity_show);

		String va = getIntent().getStringExtra(QRCODE_BACK_MY_KEY);
		if (!TextUtils.isEmpty(va)) {
			key = va.trim();
		}

		setTitle();

		initView();

	}

	private void setTitle() {

		new TitleBuilder(this).setTitleText(R.string.scan_result)
				.setLeftTxt(R.drawable.qrcode_btn_back_top_sel)
				.setLeftOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						finish();

					}
				}).setRightTxt(R.string.scan_scan)
				.setRightOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						setIntent();
						finish();
					}
				});

	}

	private void initView() {

		txt_null = (TextView) this.findViewById(R.id.txt_null);
		layout_show = (LinearLayout) this.findViewById(R.id.layout_show);

		txt_what = (TextView) this.findViewById(R.id.txt_what);
		txt_what.setText("");

		txt_show = (TextView) this.findViewById(R.id.txt_show);

		txt_link = (TextView) this.findViewById(R.id.txt_link);
		txt_link.setOnClickListener(this);

		txt_copy = (TextView) this.findViewById(R.id.txt_copy);
		txt_copy.setOnClickListener(this);

		if (Build.VERSION.SDK_INT < 11) {
			txt_copy.setVisibility(View.GONE);
		}

		txt_share = (TextView) this.findViewById(R.id.txt_share);
		txt_share.setOnClickListener(this);

		setResults(key);

	}

	/**
	 * 跳转扫描界面
	 * */
	private void setIntent() {
		Intent intent = new Intent();
		intent.setClass(ShowActivity.this, CaptureActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivityForResult(intent, SCANNIN_GREQUEST_CODE);

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == SCANNIN_GREQUEST_CODE) {
			if (resultCode == RESULT_OK) {

				String results = data.getStringExtra(QRCODE_BACK_KEY);
				if (!TextUtils.isEmpty(results)) {
					results = results.trim();
				}
				setResults(results);

			} else {
				layout_show.setVisibility(View.GONE);
				txt_null.setVisibility(View.VISIBLE);
			}
		}
	}

	private boolean isAudio(String url) {

		boolean result = false;
		for (int i = 0; i < audio.length; i++) {
			result = url.endsWith(audio[i]);
			if (result == true) {
				return true;
			}
		}
		return result;

	}

	private boolean isVideo(String url) {

		boolean result = false;

		for (int i = 0; i < video.length; i++) {
			result = url.endsWith(video[i]);
			if (result == true) {
				return true;
			}

		}
		return result;
	}

	private void setResults(String results) {

		layout_show.setVisibility(View.VISIBLE);
		txt_null.setVisibility(View.GONE);

		if (!TextUtils.isEmpty(results)) {
			txt_show.setText(results);
			this.copyString = results;

			if (results.startsWith("http://") || results.startsWith("https://")) {

				txt_link.setVisibility(View.VISIBLE);

				String urlTmp = checkUrl(results).toLowerCase();

				if (!TextUtils.isEmpty(urlTmp) && isAudio(urlTmp)) {
					this.audioPath = results;
					txt_what.setText(R.string.audio);
					txt_link.setText(R.string.audio_playing);

				} else if (!TextUtils.isEmpty(urlTmp) && isVideo(urlTmp)) {
					this.videoPath = results;
					txt_what.setText(R.string.video);
					txt_link.setText(R.string.video_playing);

				} else {
					this.links = results;
					txt_what.setText(R.string.url);
					txt_link.setText(R.string.visit_url);

				}

			} else {
				txt_what.setText(R.string.text);
				txt_link.setVisibility(View.INVISIBLE);
			}
		} else {
			layout_show.setVisibility(View.GONE);
			txt_null.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {

		if (v == txt_link) {

			String text = (String) txt_link.getText();
			if (text.equalsIgnoreCase(getResources().getString(
					R.string.video_playing))) {
				playVideo(videoPath);

			} else if (text.equalsIgnoreCase(getResources().getString(
					R.string.audio_playing))) {
				playVideo(audioPath);
			} else if (text.equalsIgnoreCase(getResources().getString(
					R.string.visit_url))) {
				setLink(links);
			}
		} else if (v == txt_copy) {
			setCopy(copyString);
		}
	}

	/**
	 * 复制操作
	 * */
	@SuppressLint("NewApi")
	private void setCopy(String txt) {
		if (!TextUtils.isEmpty(txt)) {
			showToast(R.string.copy_succeed);
			ClipboardManager mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			mClipboardManager.setText(txt);
		}
	}

	/**
	 * 调用系统浏览器
	 * */
	private void setLink(String txt) {
		if (!TextUtils.isEmpty(txt)) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(txt));
			startActivity(intent);
		}

	}

	/**
	 * 调用系统流媒体播放器
	 * */
	private void playVideo(String videoPath) {

		String extension = MimeTypeMap.getFileExtensionFromUrl(videoPath);
		String mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(
				extension);
		Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
		mediaIntent.setDataAndType(Uri.parse(videoPath), mimeType);
		startActivity(mediaIntent);

	}

	private String checkUrl(String str) {
		try {
			URL url = new URL(str);
			return url.getPath();
		} catch (Exception e) {
			return "";
		}
	}

}
