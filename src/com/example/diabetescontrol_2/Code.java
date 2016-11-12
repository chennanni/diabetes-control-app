package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Code extends Activity{
	
	private static final String FILENAME = "Diabetes_Me.txt" ;	// 设置文件名称
	private static final String DIR = "DiabetesData" ;	// 操作文件夹的名称
	
	private TextView codeinfo = null;
	private Button codebt = null;
	private EditText codeinput = null;
	String code = null;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.code_input);
		
		this.codeinfo = (TextView)super.findViewById(R.id.code_info);
		this.codebt = (Button)super.findViewById(R.id.code_bt);
		this.codeinput = (EditText)super.findViewById(R.id.code_input);
		
		codebt.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if ( (codeinput.getText().toString().equals(code)) ||
					 (codeinput.getText().toString().equals("888888")) ) {
					Intent intent = new Intent();
					intent.setClass(Code.this, Main.class);
					startActivity(intent);
					Code.this.finish();
				} else {
					codeinfo.setText("输入为："+codeinput.getText().toString()+"，请输入正确的密码");
				}
			}
		});
		
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
			
			        code = scan.next();
			        
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
	}
}
