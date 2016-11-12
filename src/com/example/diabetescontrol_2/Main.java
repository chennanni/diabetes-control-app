package com.example.diabetescontrol_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity{
//数据定义******************************************************************
		private Button buttonrecord = null;  //定义  “记录”  按钮	
		private Button buttonshow = null; //定义“显示”按钮	
		private Button buttonexit = null; //定义“退出”按钮
		private Button buttonsetup = null; //定义“设置”按钮
		private Button buttonpunchcard = null; //定义“打卡”按钮
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//取得各个组件***************************************************************
		// 取得按钮
		this.buttonrecord = (Button) super.findViewById(R.id.Record);
		this.buttonshow = (Button) super.findViewById(R.id.Display);
		this.buttonexit = (Button) super.findViewById(R.id.Exit);
		this.buttonsetup = (Button) super.findViewById(R.id.Setup);
		this.buttonpunchcard = (Button) super.findViewById(R.id.PunchCard);
//按钮“记录”*******************************************************************		
		buttonrecord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, RecordMenu.class);
				startActivity(intent);
			}
		});
//按钮“显示”*******************************************************************
		buttonshow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, ShowMenu.class);
				startActivity(intent);
			}
		});
//按钮“退出”*******************************************************************
		buttonexit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Main.this.finish();
			}
		});
//按钮“设置”*******************************************************************
		buttonsetup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, SetupMenu.class);
				startActivity(intent);
			}
		});
//按钮“打卡”*******************************************************************
		buttonpunchcard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, PunchcardMenu.class);
				startActivity(intent);
			}
		});
//其他********************************************************************	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}