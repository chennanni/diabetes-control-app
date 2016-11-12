package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import android.app.Activity;  
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;  
import android.os.Environment;
import android.text.format.Time;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;

//��һ����ť����ģ�飬ʵ��Ѫ�ǵ����ݵ�¼��  
public class RecordMenu extends Activity{  

//���ݶ���***********************************************************************	
	private Spinner spidata = null;  //���������б�
	private ArrayAdapter<CharSequence> adapterClass = null;  //����  �����б�����������
	private EditText editnum = null;  //����  ����Ѫ�����ݵ��ı���

	private static final String FILENAME = "Diabetes_Data.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
    
	private Button btSave = null; //���塰���桱��ť
	private Button btDate = null; //���塰�޸����ڡ���ť
	private Button btTime = null; //���塰�޸�ʱ�䡱��ť
	private Button btReturn = null; //���塰���ء���ť
	
	String spiShow = null;  //����ȫ�ֱ����������������Spinner������
//����ʼ**********************************************************************
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.record_basic_main_2);  
//ȡ�����***********************************************************************
        //ȡ�������ı������
      	this.editnum = (EditText)super.findViewById(R.id.rb_data);				
      	//ȡ�ð�ť
      	this.btSave = (Button)super.findViewById(R.id.rb_save);
      	this.btDate = (Button)super.findViewById(R.id.rb_buttDate);
      	this.btTime = (Button)super.findViewById(R.id.rb_buttTime);
      	this.btReturn= (Button)super.findViewById(R.id.rb_buttReturn);
//��������б�ģ��*****************************************************************
        //ȡ�������б����
		this.spidata=(Spinner)super.findViewById(R.id.data_spinner);
		//�����б��ѡ���¼�������ȡ��ѡ�����ֵ
		this.spidata.setOnItemSelectedListener(new OnItemSelectedListenerlmpl());
		this.adapterClass = ArrayAdapter.createFromResource(this,
				R.array.data_type,
				android.R.layout.simple_spinner_item);
		//������ʾ���
		this.adapterClass.setDropDownViewResource(android.R.
				layout.simple_spinner_item);
		//���������б�ѡ��
		this.spidata.setAdapter(this.adapterClass);
//ʱ��ѡ��ģ��*******************************************************************
		//ȡ�õ�ǰ��ʱ��
		Time currentDate=new Time();
		currentDate.setToNow();  
		btDate.setText( currentDate.year+"-"+  //��������
						currentDate.month+"-"+
						currentDate.monthDay);
		btTime.setText( currentDate.hour+"��"+  //����ʱ��
						currentDate.minute);
		//����ǰ����ת��Ϊ��������Ϊ������ʾ��Ĭ��ֵ
		final int set_year = currentDate.year;  
		final int set_month = currentDate.month;
		final int set_day = currentDate.monthDay;
		//��������ѡ��ť�¼�
		btDate.setOnClickListener(new View.OnClickListener() {  
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog dialog = new DatePickerDialog(RecordMenu.this,
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker view, 
									int year, 
									int monthOfYear,
									int dayOfMonth) {
								// TODO Auto-generated method stub	
								btDate.setText(
										year+"-"+monthOfYear+"-"+
												dayOfMonth);
							}
						},set_year,set_month,set_day);  //����Ĭ����ʾֵ
				dialog.show();  //��ʾ�Ի���
			}
		});		
		//����ǰʱ��ת��Ϊ��������Ϊʱ����ʾ��Ĭ��ֵ
		final int set_hour = currentDate.hour;  
		final int set_minute = currentDate.minute;
		//����ʱ��ѡ��ť�¼�
		btTime.setOnClickListener(new View.OnClickListener() {  
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Dialog dialog = new TimePickerDialog(RecordMenu.this,
						new TimePickerDialog.OnTimeSetListener() {
							@Override
							public void onTimeSet(TimePicker view, 
									int hourOfDay, 
									int minute) {
								// TODO Auto-generated method stub
								btTime.setText(hourOfDay+"-"+minute);
							}
						},set_hour,set_minute,true);  //����Ĭ����ʾֵ
				dialog.show();  //��ʾ�Ի���
			}
		});
//���水ťģ��********************************************************************	
		btSave.setOnClickListener(new View.OnClickListener() {  //���ݱ���
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
						out = new PrintStream(new FileOutputStream(file,true));  
						out.println(btDate.getText().toString()
								+" "
								+spiShow
								+" "
								+editnum.getText().toString());
					} catch (Exception e) {
						e.printStackTrace();
					} finally { // һ��Ҫ�ر���
						if (out != null) {
							out.close();
						}
					}
				}
				RecordMenu.this.finish();
			}
		});
//��ť�����ء�*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RecordMenu.this.finish();
			}
		});
//��������ģ��*******************************************************************		
	}
//��������б�ģ��Ĳ��䣨�����¼���****************************************************	
private class OnItemSelectedListenerlmpl implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> adapterView, 
			View view, int position, long id) {
			//����Ҫ�������ߣ�����ֱ�Ӷ�spiShow��ֵ,����ȡ�õ�����Ϊ��
			String value = adapterView.
					getItemAtPosition(position).toString();
			spiShow = value;
			
			TextView tv = (TextView)view;
            tv.setTextSize(25);    //���ô�С
            tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);   //���þ���
		}
		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	};	
	//��Ҫע����ǣ���ʹ����Ҫ�������3�д��룬Ҳ������ʾ��Spinner�����������Щ��Ϊ��������ʾ���
	//�����б�����������  ����Դ�б��ȡ�ļ�
}