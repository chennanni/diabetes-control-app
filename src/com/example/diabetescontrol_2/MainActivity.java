package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final String FILENAME = "Diabetes_Me.txt" ;	// 设置文件名称
	private static final String DIR = "DiabetesData" ;	// 操作文件夹的名称
	
	int code_flag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//读取SD卡上的数据*****************************************************************
        if(Environment.getExternalStorageState().equals(  //判断SD卡是否存在
        		Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator 
					+ DIR 
					+ File.separator 
					+ FILENAME); // 定义要操作的文件
			if (!file.getParentFile().exists()) {  //判断父文件夹是否存在
				file.getParentFile().mkdirs(); //如不存在，则创建父文件夹
			}
			Scanner scan = null ;  //打印流对象用于输出
			try {
				scan = new Scanner(new FileInputStream(file)) ;
			
			        scan.next();
			        code_flag = Integer.parseInt(scan.next().trim());
			        
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 一定要关闭流
				if (scan != null) {
					scan.close();
				}
			}
		} else {
			Toast.makeText(this, "保存失败，SD卡不存在！", Toast.LENGTH_LONG).show();
		}
		
		
		if (code_flag == 0){
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Main.class);
			startActivity(intent);
		}
		else{
			Intent intent = new Intent();
			intent.setClass(MainActivity.this, Code.class);
			startActivity(intent);
		}
		MainActivity.this.finish();
	}
}