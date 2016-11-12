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
//��һ����ť����ģ�飬ʵ��Ѫ�ǵ����ݵ�¼��
public class SetupMenu extends Activity { 
	
//���ݶ���******************************************************************	
	private Button btClear = null;  //���塰���ݳ�ʼ������ť
	private Button btReturn = null;  //���塰���ء���ť
	private Button btEmail = null;	//���塰�û���������ť
	private Button btReminder = null; //���塰�ҵ����ѡ���ť
	private Button btUpdate = null;  //���塰�����ϴ�����ť
	private Button btMe = null;  //���塰�û�����Ϣ��ť
	private static final String FILENAME = "Diabetes_Data.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
//����ʼ******************************************************************	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.setup_main_2); 
//ȡ�����****************************************************************** 
        this.btClear = (Button)super.findViewById(R.id.su_clear);
        this.btReturn = (Button)super.findViewById(R.id.su_return);
        this.btEmail = (Button)super.findViewById(R.id.su_email);
        this.btEmail.setOnClickListener(new OnClickListenerlmpl());
        this.btReminder = (Button)super.findViewById(R.id.su_reminder);
        this.btUpdate = (Button)super.findViewById(R.id.su_update);
        this.btMe = (Button)super.findViewById(R.id.su_me);
//���ݳ�ʼ��ģ��**************************************************************
        btClear.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				//���ﳢ����  ��SD�����ݸ�дΪ�հ׵ķ���  ʵ�����ݵ����
				if(Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File file = new File(Environment.getExternalStorageDirectory()
							+ File.separator 
							+ DIR + File.separator + FILENAME); // ����Ҫ�������ļ�
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs(); // �������ļ���·��
					}
					PrintStream out = null;
					try {
						//����д���ļ�����������
						out = new PrintStream(new FileOutputStream(file));  
						out.println("");  //�գ�
					} catch (Exception e) {
						e.printStackTrace();
					} finally { // һ��Ҫ�ر���
						if (out != null) {
							out.close();
						}
					}
				}
			}
		});
// ��ť�����ء�*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SetupMenu.this.finish();
			}
		});		
// ��ť���ҵ����ѡ�*******************************************************************
		btReminder.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SetupMenu.this, MyReminder.class);
				startActivity(intent);
			}
		});
// ��ť�������ϴ���*******************************************************************
		btUpdate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(SetupMenu.this, UpdateFunction.class);
				startActivity(intent);
			}
		});	
// ��ť���û���Ϣ��ģ��****************************************************************	
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
//�û�����ģ��*************************************************************
	//���ģ��д����OnCreate�������棬ע�⣡
	private class OnClickListenerlmpl implements OnClickListener {
		@Override
		public void onClick(View view) {
			// TODO Auto-generated method stub
			Intent emailIntent = new Intent(Intent.ACTION_SEND);
			emailIntent.setType("plain/text");
			String address[]= new String[]{"imncn@qq.com"};
			String subject = "�������ҹ�������Ľ����뷴��";
			String content = "�����ߣ���ã�����ʹ���������ҹ��������һ���û���������������뽨�飺 ";
			emailIntent.putExtra(Intent.EXTRA_EMAIL, address);
			emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
			emailIntent.putExtra(Intent.EXTRA_TEXT, content);
			SetupMenu.this.startActivity(emailIntent);
		}
	}
}