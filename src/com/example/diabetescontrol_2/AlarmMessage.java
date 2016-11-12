package com.example.diabetescontrol_2;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlarmMessage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new AlertDialog.Builder(this)
/*				.setIcon(R.drawable.clock)*/
				.setTitle("服药时间已到！")
				.setMessage(
						"提醒时间已到，现在时间："
								+ new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒")
										.format(new Date()))
				.setPositiveButton("关闭", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						AlarmMessage.this.finish();
					}
				}).show();
	}

}