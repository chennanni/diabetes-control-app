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
	
	private static final String FILENAME = "Diabetes_Me.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
	
	int code_flag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//��ȡSD���ϵ�����*****************************************************************
        if(Environment.getExternalStorageState().equals(  //�ж�SD���Ƿ����
        		Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator 
					+ DIR 
					+ File.separator 
					+ FILENAME); // ����Ҫ�������ļ�
			if (!file.getParentFile().exists()) {  //�жϸ��ļ����Ƿ����
				file.getParentFile().mkdirs(); //�粻���ڣ��򴴽����ļ���
			}
			Scanner scan = null ;  //��ӡ�������������
			try {
				scan = new Scanner(new FileInputStream(file)) ;
			
			        scan.next();
			        code_flag = Integer.parseInt(scan.next().trim());
			        
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // һ��Ҫ�ر���
				if (scan != null) {
					scan.close();
				}
			}
		} else {
			Toast.makeText(this, "����ʧ�ܣ�SD�������ڣ�", Toast.LENGTH_LONG).show();
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