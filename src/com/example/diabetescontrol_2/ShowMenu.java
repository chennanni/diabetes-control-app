package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import android.app.Activity;  
import android.content.Intent;
import android.os.Bundle;  
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

//��������ť����ģ�飬ʵ������Ѫ�����ݵȵ���ʾ
public class ShowMenu extends Activity{  

//���ݶ���***********************************************************************	
	private static final String FILENAME = "Diabetes_Data.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
	private Button btReturn = null;  //���塰���ء���ť
	private Button btGraph = null;  //���塰��ʾͼ����ť

	//�������е�List����
	private List<Map<String,String>>list = new ArrayList<Map<String,String>>();
	private ListView datalist;	 //����ListView���
	private SimpleAdapter simpleAdapter = null;  //����������
//����ʼ	**********************************************************************	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.show_data_main_2);  
        
        this.btReturn= (Button)super.findViewById(R.id.sd_returnButton);
        this.btGraph= (Button)super.findViewById(R.id.sd_showPic);
        
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
				while(scan.hasNext()) {				
			        //ѭ����������
					Map<String,String> map = new HashMap<String,String>();
		      		map.put("sd_date",     scan.next());
		      		map.put("sd_category", scan.next());
		      		map.put("sd_data",     scan.next());
		      		this.list.add(map);
				}
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
//������ʾ*********************************************************************        
        //ȡ��ListView���
      	this.datalist=(ListView)super.findViewById(R.id.sd_datalist);

      	//ʵ����SimpleAdapter
      	this.simpleAdapter = new SimpleAdapter(
      			this,
      			this.list,
      			R.layout.data_list,  //ȡ��Ҫʹ�õ�Listģ��
      			//������ʾKey
      			new String[]{"sd_date","sd_category","sd_data"},
      			//��ģ���е����ƥ��
      			new int[]{R.id.sd_date,
      					R.id.sd_category,
      					R.id.sd_data,});
      	this.datalist.setAdapter(this.simpleAdapter);  //������ʾ����	
//��ť�����ء�*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowMenu.this.finish();
			}
		});
//��ť����ʾͼ��*******************************************************************
		btGraph.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent achartIntent = new ShowChart().execute(ShowMenu.this);
				startActivity(achartIntent);
			}
		});
    }  
}  