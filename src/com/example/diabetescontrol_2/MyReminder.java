package com.example.diabetescontrol_2;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.TimePicker.OnTimeChangedListener;

public class MyReminder extends Activity {
//定义*****************************************************************
	private AlarmManager alarm = null; // 闹钟服务
	private Button set = null;  //设置提醒
	private Button delete = null;  //删除提醒
	private Button btreturn = null;  //返回按钮
	private TextView msg = null;  //显示提示信息
	private TimePicker time = null;  //日期选择器
	private int hourOfDay = 0 ;
	private int minute = 0;
	private Calendar calendar = Calendar.getInstance() ;  //日历
//程序开始***************************************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.my_reminder_su_2);
//取得组件***************************************************************
		this.set = (Button) super.findViewById(R.id.mr_set);
		this.delete = (Button) super.findViewById(R.id.mr_delete);
		this.btreturn = (Button)super.findViewById(R.id.mr_return);
		this.msg = (TextView) super.findViewById(R.id.mr_msg);
		this.time = (TimePicker) super.findViewById(R.id.mr_time);
		//取得闹钟组件
		this.alarm = (AlarmManager) super.getSystemService(Context.ALARM_SERVICE) ;
		//设置按钮功能
		this.time.setIs24HourView(true) ;
		this.time.setOnTimeChangedListener(new OnTimeChangedListenerImpl()) ;
		this.set.setOnClickListener(new SetOnClickListener()) ;
		this.delete.setOnClickListener(new DeleteOnClickListener()) ;
		this.btreturn.setOnClickListener(new SetReturnOnClickListener());
	}
	//定义日期选择器组件
	private class OnTimeChangedListenerImpl implements OnTimeChangedListener{
		@Override
		public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
			MyReminder.this.calendar.setTimeInMillis(System.currentTimeMillis()) ;
			MyReminder.this.calendar.set(Calendar.HOUR_OF_DAY, hourOfDay) ;
			MyReminder.this.calendar.set(Calendar.MINUTE, minute) ;
			MyReminder.this.calendar.set(Calendar.SECOND, 0) ;
			MyReminder.this.calendar.set(Calendar.MILLISECOND, 0) ;
			MyReminder.this.hourOfDay = hourOfDay ;
			MyReminder.this.minute = minute ;
		}	
	}
	//定义设置按钮
	private class SetOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(MyReminder.this,
					MyAlarmReceiver.class);
			intent.setAction("org.lxh.action.setalarm") ;
			PendingIntent sender = PendingIntent.getBroadcast(
					MyReminder.this, 0, intent,
					PendingIntent.FLAG_UPDATE_CURRENT);
			MyReminder.this.alarm.set(AlarmManager.RTC_WAKEUP,
					MyReminder.this.calendar.getTimeInMillis(), sender);
			MyReminder.this.msg.setText("提醒时间是："
					+ MyReminder.this.hourOfDay + "时"
					+ MyReminder.this.minute + "分。");
			Toast.makeText(MyReminder.this, "提醒设置成功！",
					Toast.LENGTH_LONG).show();
		}
	}
	//定义删除按钮
	private class DeleteOnClickListener implements OnClickListener{
		@Override
		public void onClick(View v) {
			if (MyReminder.this.alarm != null) {
				Intent intent = new Intent(MyReminder.this,
						MyAlarmReceiver.class);
				intent.setAction("org.lxh.action.setalarm") ;
				PendingIntent sender = PendingIntent.getBroadcast(
						MyReminder.this, 0, intent,
						PendingIntent.FLAG_UPDATE_CURRENT);
				MyReminder.this.alarm.cancel(sender) ;	// 取消
				MyReminder.this.msg.setText("当前没有设置提醒。") ;
				Toast.makeText(MyReminder.this, "提醒删除成功！",
						Toast.LENGTH_LONG).show();
			}
		}		
	}
	//定义返回按钮
	private class SetReturnOnClickListener implements OnClickListener{
		public void onClick(View v) {
			MyReminder.this.finish();
		}
	}
}