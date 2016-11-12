package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//第一个按钮功能模块，实现血糖等数据的录入
public class SetupMenu extends Activity { 
	
//数据定义******************************************************************	
	private Button btClear = null;  //定义“数据初始化”按钮
	private Button btReturn = null;  //定义“返回”按钮
	private Button btEmail = null;	//定义“用户反馈”按钮
	private Button btReminder = null; //定义“我的提醒”按钮
	private Button btUpdate = null;  //定义“数据上传”按钮
	private Button btMe = null;  //定义“用户”信息按钮
	private static final String FILENAME = "Diabetes_Data.txt" ;	// 设置文件名称
	private static final String DIR = "DiabetesData" ;	// 操作文件夹的名称
//程序开始******************************************************************	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.setup_main_2); 
//取得组件****************************************************************** 
        this.btClear = (Button)super.findViewById(R.id.su_clear);
        this.btReturn = (Button)super.findViewById(R.id.su_return);
        this.btEmail = (Button)super.findViewById(R.id.su_email);
        this.btEmail.setOnClickListener(new OnClickListenerlmpl());
        this.btReminder = (Button)super.findViewById(R.id.su_reminder);
        this.btUpdate = (Button)super.findViewById(R.id.su_update);
        this.btMe = (Button)super.findViewById(R.id.su_me);
//数据初始化模块**************************************************************
        btClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				//这里尝试用  将SD卡内容覆写为空白的方法  实现数据的清除
				if(Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File file = new File(Environment.getExternalStorageDirectory()
							+ File.separator 
							+ DIR + File.separator + FILENAME); // 定义要操作的文件
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs(); // 创建父文件夹路径
					}
					PrintStream out = null;
					try {
						//“覆写”文件操作！！！
						out = new PrintStream(new FileOutputStream(file));  
						out.println("");  //空！
					} catch (Exception e) {
						e.printStackTrace();
					} finally { // 一定要关闭流
						if (out != null) {
							out.close();
						}
					}
				}
			}
		});
// 按钮“返回”*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SetupMenu.this.finish();
			}
		});		
// 按钮“我的提醒”*******************************************************************
		btReminder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SetupMenu.this, MyReminder.class);
				startActivity(intent);
			}
		});
// 按钮“数据上传”*******************************************************************
		btUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SetupMenu.this, UpdateFunction.class);
				startActivity(intent);
			}
		});	
// 按钮“用户信息”模块****************************************************************	
		btMe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SetupMenu.this, Me.class);
				startActivity(intent);
			}
		});	
	}
//用户反馈模块*************************************************************
	//这个模块写在了OnCreate方法外面，注意！
	private class OnClickListenerlmpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("plain/text");
			String address[]= new String[]{"imncn@qq.com"};
			String subject = "糖尿病自我管理软件的建议与反馈";
			String content = "开发者，你好！我是使用糖尿病自我管理软件的一个用户，我有如下意见与建议： ";
			emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			emailIntent.putExtra(Intent.EXTRA_TEXT, content);
			SetupMenu.this.startActivity(emailIntent);
		}
	}
}