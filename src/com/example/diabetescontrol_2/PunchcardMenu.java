package com.example.diabetescontrol_2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PunchcardMenu extends Activity {
//数据定义******************************************************************
	private Button btYes = null;  //定义“Yes”按钮	
	private Button btNo = null; //定义“No”按钮
	private TextView Text = null; //定义文本显示组件
	CharSequence str1="今天你运动了吗？";
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.punch_card_main_2);  
//取得各个组件***************************************************************
        this.btYes = (Button)super.findViewById(R.id.pc_yes);
        this.btNo = (Button)super.findViewById(R.id.pc_no);
        this.Text =(TextView)super.findViewById(R.id.pc_text);
        
//按钮“Yes”*******************************************************************
        //第一重选择Y
        btYes.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Text.setText(str1);  //显示第二条信息
				//第二重选择Y
				btYes.setOnClickListener(new View.OnClickListener() {  //YY
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);					
						intent.putExtra("key", 1);  //传递数据
						startActivity(intent);  //跳转Activity
						PunchcardMenu.this.finish();  //结束当前Activity
					}
				});
				//第二重选择N
				btNo.setOnClickListener(new View.OnClickListener() {  //YN
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);
						intent.putExtra("key", 2);  //传递数据
						startActivity(intent);	
						PunchcardMenu.this.finish();
					}
				});
				
			}
		});
//按钮“No”*******************************************************************
        //第一重选择N
        btNo.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Text.setText(str1);  //显示第二条信息				
				//第二重选择Y
				btYes.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);//NY
						intent.putExtra("key", 3);  //传递数据
						startActivity(intent);
						PunchcardMenu.this.finish();
					}
				});
				//第二重选择N
				btNo.setOnClickListener(new View.OnClickListener() {
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PunchcardMenu.this, ShareMenu.class);//NN
						intent.putExtra("key", 4);  //传递数据
						startActivity(intent);	
						PunchcardMenu.this.finish();
					}
				});
			}
		});
//*******************************************************************
	}
}