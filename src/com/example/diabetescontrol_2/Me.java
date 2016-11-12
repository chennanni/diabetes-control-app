package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Me extends Activity{
	
	private static final String FILENAME = "Diabetes_Me.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
	
	private Button btcancel = null;
	private Button btyes = null;
	private EditText input = null;
	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.me_su_2); 
        
        this.btcancel = (Button)super.findViewById(R.id.me_bt_cancel);
        this.btyes = (Button)super.findViewById(R.id.me_bt_yes);
        this.input = (EditText)super.findViewById(R.id.me_edit);
		
		btcancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				if(Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File file = new File(Environment.
							getExternalStorageDirectory()
							+ File.separator 
							+ DIR + File.separator + FILENAME); // ����Ҫ�������ļ�
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs(); // �������ļ���·��
					}
					PrintStream out = null;
					try {
						//׷���ļ�����
						out = new PrintStream(new FileOutputStream(file));  //��д
						out.println(" "
								+" "
								+"0");
					} catch (Exception e) {
						e.printStackTrace();
					} finally { // һ��Ҫ�ر���
						if (out != null) {
							out.close();
						}
					}
				}
				Me.this.finish();
			}
		});
        
        btyes.setOnClickListener(new View.OnClickListener() {  //���ݱ���
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				if(Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File file = new File(Environment.
							getExternalStorageDirectory()
							+ File.separator 
							+ DIR + File.separator + FILENAME); // ����Ҫ�������ļ�
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs(); // �������ļ���·��
					}
					PrintStream out = null;
					try {
						//׷���ļ�����
						out = new PrintStream(new FileOutputStream(file));  //��д
						out.println(input.getText().toString()
								+" "
								+"1");
					} catch (Exception e) {
						e.printStackTrace();
					} finally { // һ��Ҫ�ر���
						if (out != null) {
							out.close();
						}
					}
				}
				Me.this.finish();
			}
		});
	}
}
