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

//第一个按钮功能模块，实现血糖等数据的录入  
public class RecordMenu extends Activity{  

//数据定义***********************************************************************	
	private Spinner spidata = null;  //定义下拉列表
	private ArrayAdapter<CharSequence> adapterClass = null;  //定义  下拉列表内容适配器
	private EditText editnum = null;  //定义  输入血糖数据的文本框

	private static final String FILENAME = "Diabetes_Data.txt" ;	// 设置文件名称
	private static final String DIR = "DiabetesData" ;	// 操作文件夹的名称
    
	private Button btSave = null; //定义“保存”按钮
	private Button btDate = null; //定义“修改日期”按钮
	private Button btTime = null; //定义“修改时间”按钮
	private Button btReturn = null; //定义“返回”按钮
	
	String spiShow = null;  //定义全局变量，用来保存类别Spinner的数据
//程序开始**********************************************************************
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.record_basic_main_2);  
//取得组件***********************************************************************
        //取得输入文本框组件
      	this.editnum = (EditText)super.findViewById(R.id.rb_data);				
      	//取得按钮
      	this.btSave = (Button)super.findViewById(R.id.rb_save);
      	this.btDate = (Button)super.findViewById(R.id.rb_buttDate);
      	this.btTime = (Button)super.findViewById(R.id.rb_buttTime);
      	this.btReturn= (Button)super.findViewById(R.id.rb_buttReturn);
//类别下拉列表模块*****************************************************************
        //取得下拉列表组件
		this.spidata=(Spinner)super.findViewById(R.id.data_spinner);
		//下拉列表的选择事件，用来取得选择项的值
		this.spidata.setOnItemSelectedListener(new OnItemSelectedListenerlmpl());
		this.adapterClass = ArrayAdapter.createFromResource(this,
				R.array.data_type,
				android.R.layout.simple_spinner_item);
		//设置显示风格
		this.adapterClass.setDropDownViewResource(android.R.
				layout.simple_spinner_item);
		//设置下拉列表选项
		this.spidata.setAdapter(this.adapterClass);
//时间选择模块*******************************************************************
		//取得当前的时间
		Time currentDate=new Time();
		currentDate.setToNow();  
		btDate.setText( currentDate.year+"-"+  //设置日期
						currentDate.month+"-"+
						currentDate.monthDay);
		btTime.setText( currentDate.hour+"："+  //设置时间
						currentDate.minute);
		//将当前日期转化为常量，作为日期显示的默认值
		final int set_year = currentDate.year;  
		final int set_month = currentDate.month;
		final int set_day = currentDate.monthDay;
		//单机日期选择按钮事件
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
						},set_year,set_month,set_day);  //设置默认显示值
				dialog.show();  //显示对话框
			}
		});		
		//将当前时间转化为常量，作为时间显示的默认值
		final int set_hour = currentDate.hour;  
		final int set_minute = currentDate.minute;
		//单机时间选择按钮事件
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
						},set_hour,set_minute,true);  //设置默认显示值
				dialog.show();  //显示对话框
			}
		});
//保存按钮模块********************************************************************	
		btSave.setOnClickListener(new View.OnClickListener() {  //数据保存
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
				if(Environment.getExternalStorageState().equals(
						Environment.MEDIA_MOUNTED)) {
					File file = new File(Environment.
							getExternalStorageDirectory()
							+ File.separator 
							+ DIR + File.separator + FILENAME); // 定义要操作的文件
					if (!file.getParentFile().exists()) {
						file.getParentFile().mkdirs(); // 创建父文件夹路径
					}
					PrintStream out = null;
					try {
						//追加文件操作
						out = new PrintStream(new FileOutputStream(file,true));  
						out.println(btDate.getText().toString()
								+" "
								+spiShow
								+" "
								+editnum.getText().toString());
					} catch (Exception e) {
						e.printStackTrace();
					} finally { // 一定要关闭流
						if (out != null) {
							out.close();
						}
					}
				}
				RecordMenu.this.finish();
			}
		});
//按钮“返回”*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RecordMenu.this.finish();
			}
		});
//其他功能模块*******************************************************************		
	}
//类别下拉列表模块的补充（单击事件）****************************************************	
private class OnItemSelectedListenerlmpl implements OnItemSelectedListener {
		public void onItemSelected(AdapterView<?> adapterView, 
			View view, int position, long id) {
			//这里要分两步走，不能直接对spiShow赋值,否则取得的数据为空
			String value = adapterView.
					getItemAtPosition(position).toString();
			spiShow = value;
			
			TextView tv = (TextView)view;
            tv.setTextSize(25);    //设置大小
            tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);   //设置居中
		}
		@Override
		public void onNothingSelected(AdapterView<?> adapterView) {
			// TODO Auto-generated method stub
		}
	};	
	//需要注意的是，即使不需要后面的这3行代码，也可以显示出Spinner，这里加入这些是为了设置显示风格
	//下拉列表内容适配器  从资源列表读取文件
}