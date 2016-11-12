package com.example.diabetescontrol_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PunchcardMenu extends Activity {
//���ݶ���******************************************************************
	private Button btYes = null;  //���塰Yes����ť	
	private Button btNo = null; //���塰No����ť
	private TextView Text = null; //�����ı���ʾ���
	CharSequence str1="�������˶�����";
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.punch_card_main_2);  
//ȡ�ø������***************************************************************
        this.btYes = (Button)super.findViewById(R.id.pc_yes);
        this.btNo = (Button)super.findViewById(R.id.pc_no);
        this.Text =(TextView)super.findViewById(R.id.pc_text);
        
//��ť��Yes��*******************************************************************
        //��һ��ѡ��Y
        btYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Text.setText(str1);  //��ʾ�ڶ�����Ϣ
				//�ڶ���ѡ��Y
				btYes.setOnClickListener(new View.OnClickListener() {  //YY
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);					
						intent.putExtra("key", 1);  //��������
						startActivity(intent);  //��תActivity
						PunchcardMenu.this.finish();  //������ǰActivity
					}
				});
				//�ڶ���ѡ��N
				btNo.setOnClickListener(new View.OnClickListener() {  //YN
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);
						intent.putExtra("key", 2);  //��������
						startActivity(intent);	
						PunchcardMenu.this.finish();
					}
				});
				
			}
		});
//��ť��No��*******************************************************************
        //��һ��ѡ��N
        btNo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Text.setText(str1);  //��ʾ�ڶ�����Ϣ				
				//�ڶ���ѡ��Y
				btYes.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);//NY
						intent.putExtra("key", 3);  //��������
						startActivity(intent);
						PunchcardMenu.this.finish();
					}
				});
				//�ڶ���ѡ��N
				btNo.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);//NN
						intent.putExtra("key", 4);  //��������
						startActivity(intent);	
						PunchcardMenu.this.finish();
					}
				});
			}
		});
//*******************************************************************
	}
}