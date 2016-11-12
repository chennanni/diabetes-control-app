package com.example.diabetescontrol_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShareMenu extends Activity {
	private Button btShare = null ;
	private Button btReturn = null ;
	private TextView info = null ;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_weibo_pc_2);
//ȡ�����*******************************************************************
		this.btShare = (Button)super.findViewById(R.id.sw_share);
		this.btReturn = (Button)super.findViewById(R.id.sw_return);
		this.info = (TextView)super.findViewById(R.id.sw_text);
//��Ļ��ʾ����*******************************************************************		
		Intent intent_1 = getIntent();
		int value = intent_1.getIntExtra("key", 0);
		switch(value){
		case 0: info.setText("�Բ��𣬳�����"); break;
		case 1: info.setText("�������治��,��������Ӵ��"); break;
		case 2: info.setText("���������˶��������ͣ�"); break;
		case 3: info.setText("�Եñ�����Եúã�"); break;
		case 4: info.setText("�ӰѾ���"); break;
		}
//��ť�����ء�*******************************************************************
		btShare.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Intent.ACTION_SEND);   
                intent.setType("image/*");   
                intent.putExtra(Intent.EXTRA_SUBJECT, "Share");   
                intent.putExtra(Intent.EXTRA_TEXT, 
                		"(������Android)");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
                startActivity(Intent.createChooser(intent, getTitle()));     
			}
		});
		
//��ť�����ء�*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShareMenu.this.finish();
			}
		});
//*******************************************************************
	}	
}
