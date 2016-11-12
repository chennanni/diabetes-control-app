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
//����*****************************************************************
	private AlarmManager alarm = null; // ���ӷ���
	private Button set = null;  //��������
	private Button delete = null;  //ɾ������
	private Button btreturn = null;  //���ذ�ť
	private TextView msg = null;  //��ʾ��ʾ��Ϣ
	private TimePicker time = null;  //����ѡ����
	private int hourOfDay = 0 ;
	private int minute = 0;
	private Calendar calendar = Calendar.getInstance() ;  //����
//����ʼ***************************************************************
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		super.setContentView(R.layout.my_reminder_su_2);
//ȡ�����***************************************************************
		this.set = (Button) super.findViewById(R.id.mr_set);
		this.delete = (Button) super.findViewById(R.id.mr_delete);
		this.btreturn = (Button)super.findViewById(R.id.mr_return);
		this.msg = (TextView) super.findViewById(R.id.mr_msg);
		this.time = (TimePicker) super.findViewById(R.id.mr_time);
		//ȡ���������
		this.alarm = (AlarmManager) super.getSystemService(Context.ALARM_SERVICE) ;
		//���ð�ť����
		this.time.setIs24HourView(true) ;
		this.time.setOnTimeChangedListener(new OnTimeChangedListenerImpl()) ;
		this.set.setOnClickListener(new SetOnClickListener()) ;
		this.delete.setOnClickListener(new DeleteOnClickListener()) ;
		this.btreturn.setOnClickListener(new SetReturnOnClickListener());
	}
	//��������ѡ�������
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
	//�������ð�ť
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
			MyReminder.this.msg.setText("����ʱ���ǣ�"
					+ MyReminder.this.hourOfDay + "ʱ"
					+ MyReminder.this.minute + "�֡�");
			Toast.makeText(MyReminder.this, "�������óɹ���",
					Toast.LENGTH_LONG).show();
		}
	}
	//����ɾ����ť
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
				MyReminder.this.alarm.cancel(sender) ;	// ȡ��
				MyReminder.this.msg.setText("��ǰû���������ѡ�") ;
				Toast.makeText(MyReminder.this, "����ɾ���ɹ���",
						Toast.LENGTH_LONG).show();
			}
		}		
	}
	//���巵�ذ�ť
	private class SetReturnOnClickListener implements OnClickListener{
		public void onClick(View v) {
			MyReminder.this.finish();
		}
	}
}