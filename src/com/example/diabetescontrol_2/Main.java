package com.example.diabetescontrol_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Main extends Activity{
//���ݶ���******************************************************************
		private Button buttonrecord = null;  //����  ����¼��  ��ť	
		private Button buttonshow = null; //���塰��ʾ����ť	
		private Button buttonexit = null; //���塰�˳�����ť
		private Button buttonsetup = null; //���塰���á���ť
		private Button buttonpunchcard = null; //���塰�򿨡���ť
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//ȡ�ø������***************************************************************
		// ȡ�ð�ť
		this.buttonrecord = (Button) super.findViewById(R.id.Record);
		this.buttonshow = (Button) super.findViewById(R.id.Display);
		this.buttonexit = (Button) super.findViewById(R.id.Exit);
		this.buttonsetup = (Button) super.findViewById(R.id.Setup);
		this.buttonpunchcard = (Button) super.findViewById(R.id.PunchCard);
//��ť����¼��*******************************************************************		
		buttonrecord.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, RecordMenu.class);
				startActivity(intent);
			}
		});
//��ť����ʾ��*******************************************************************
		buttonshow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, ShowMenu.class);
				startActivity(intent);
			}
		});
//��ť���˳���*******************************************************************
		buttonexit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Main.this.finish();
			}
		});
//��ť�����á�*******************************************************************
		buttonsetup.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, SetupMenu.class);
				startActivity(intent);
			}
		});
//��ť���򿨡�*******************************************************************
		buttonpunchcard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Main.this, PunchcardMenu.class);
				startActivity(intent);
			}
		});
//����********************************************************************	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}