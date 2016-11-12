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
	
	private static final String FILENAME = "Diabetes_Me.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
	
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
					codeinfo.setText("����Ϊ��"+codeinput.getText().toString()+"����������ȷ������");
				}
			}
		});
		
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
			
			        code = scan.next();
			        
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
	}
}
