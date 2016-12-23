package com.an.zxing;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class TitleBuilder {

	private View viewTitle;
	private TextView tvTitle;
	private TextView txtLeft;
	private TextView txtRight;
	private ImageView ivRight_front;
	private ImageView ivRight_behind;

	public TitleBuilder(Activity context) {
		viewTitle = context.findViewById(R.id.rl_titlebar);
		tvTitle = (TextView) viewTitle.findViewById(R.id.txt_tit);
		txtRight = (TextView) viewTitle.findViewById(R.id.txt_right);
		txtLeft = (TextView) viewTitle.findViewById(R.id.txt_top_back);
		ivRight_front = (ImageView) viewTitle.findViewById(R.id.img_front);
		ivRight_behind = (ImageView) viewTitle.findViewById(R.id.img_behind);

	}

	public TitleBuilder(View context) {
		viewTitle = context.findViewById(R.id.rl_titlebar);
		tvTitle = (TextView) viewTitle.findViewById(R.id.txt_tit);
		txtRight = (TextView) viewTitle.findViewById(R.id.txt_right);
		txtLeft = (TextView) viewTitle.findViewById(R.id.txt_top_back);
		ivRight_front = (ImageView) viewTitle.findViewById(R.id.img_front);
		ivRight_behind = (ImageView) viewTitle.findViewById(R.id.img_behind);
	}

	/**
	 * 设置标题背景
	 * */
	public TitleBuilder setTitleBgRes(int resid) {
		viewTitle.setBackgroundResource(resid);
		return this;
	}

	/**
	 * 设置中间键文�?
	 * */
	public TitleBuilder setTitleText(String text) {
		tvTitle.setVisibility(TextUtils.isEmpty(text) ? View.GONE
				: View.VISIBLE);
		tvTitle.setText(text);
		return this;
	}

	/**
	 * 设置中间键文�?
	 * */
	public TitleBuilder setTitleText(int resId) {
		tvTitle.setVisibility(resId < 0 ? View.GONE : View.VISIBLE);
		tvTitle.setText(resId);
		return this;
	}

	/**
	 * 设置左边键图�?
	 * */
	public TitleBuilder setLeftTxt(int resId) {
		txtLeft.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
		txtLeft.setBackgroundResource(resId);
		return this;
	}

	/**
	 * 设置右边键文�?
	 * */
	public TitleBuilder setRightTxt(String txt) {
		txtRight.setVisibility(TextUtils.isEmpty(txt) ? View.GONE
				: View.VISIBLE);
		txtRight.setText(txt);
		return this;
	}

	/**
	 * 设置右边键文�?
	 * */
	public TitleBuilder setRightTxt(int resId) {
		txtRight.setVisibility(resId < 0 ? View.GONE : View.VISIBLE);
		txtRight.setText(resId);
		return this;
	}

	/**
	 * 右边�?
	 * */
	public View getRightTxt() {
		return txtRight;
	}

	/**
	 * 中间�?
	 * */
	public View getTxtTitle() {
		return tvTitle;
	}

	/**
	 * 左边�?
	 * */
	public View getLeftTxt() {
		return txtLeft;
	}

	/**
	 * 右边前按�?
	 * */
	public View getRightImageFront() {

		return ivRight_front;
	}

	/**
	 * 右边后按�?
	 * */
	public View getRightImageBehind() {

		return ivRight_behind;
	}

	/**
	 * 左边键的隐藏与显�?
	 * */
	public TitleBuilder setLeftTxtHide(boolean isHide) {
		txtLeft.setVisibility(isHide ? View.VISIBLE : View.GONE);
		return this;
	}

	/**
	 * 左边键点击事�?
	 * */
	public TitleBuilder setLeftOnClickListener(OnClickListener listener) {
		if (txtLeft.getVisibility() == View.VISIBLE) {
			txtLeft.setOnClickListener(listener);
		}
		return this;
	}

	/**
	 * 右边键点击事�?
	 * */
	public TitleBuilder setRightOnClickListener(OnClickListener listener) {
		if (txtRight.getVisibility() == View.VISIBLE) {
			txtRight.setOnClickListener(listener);
		}
		return this;
	}

	/**
	 * 右边先展示按钮，即前按钮
	 * */

	public TitleBuilder setRightImageFront(int resId) {
		ivRight_front.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
		ivRight_front.setBackgroundResource(resId);
		return this;
	}

	/**
	 * 右边后展示按钮，即后按钮
	 * */
	public TitleBuilder setRightImageBehind(int resId) {
		ivRight_behind.setVisibility(resId > 0 ? View.VISIBLE : View.GONE);
		ivRight_behind.setBackgroundResource(resId);
		return this;
	}

	/**
	 * 右边前按钮点击事�?
	 * */
	public TitleBuilder setRightFrontOnClickListener(OnClickListener listener) {
		if (ivRight_front.getVisibility() == View.VISIBLE) {
			ivRight_front.setOnClickListener(listener);
		}
		return this;
	}

	/**
	 * 右边后按钮点击事�?
	 * */
	public TitleBuilder setRightBehindOnClickListener(OnClickListener listener) {
		if (ivRight_behind.getVisibility() == View.VISIBLE) {
			ivRight_behind.setOnClickListener(listener);
		}
		return this;
	}

	public View build() {
		return viewTitle;
	}

}
