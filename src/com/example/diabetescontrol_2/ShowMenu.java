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

//第三个按钮功能模块，实现输入血糖数据等的显示
public class ShowMenu extends Activity{  

//数据定义***********************************************************************	
	private static final String FILENAME = "Diabetes_Data.txt" ;	// 设置文件名称
	private static final String DIR = "DiabetesData" ;	// 操作文件夹的名称
	private Button btReturn = null;  //定义“返回”按钮
	private Button btGraph = null;  //定义“显示图表”按钮

	//保存所有的List数据
	private List<Map<String,String>>list = new ArrayList<Map<String,String>>();
	private ListView datalist;	 //定义ListView组件
	private SimpleAdapter simpleAdapter = null;  //定义适配器
//程序开始	**********************************************************************	
	public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.show_data_main_2);  
        
        this.btReturn= (Button)super.findViewById(R.id.sd_returnButton);
        this.btGraph= (Button)super.findViewById(R.id.sd_showPic);
        
//读取SD卡上的数据*****************************************************************
        if(Environment.getExternalStorageState().equals(  //判断SD卡是否存在
        		Environment.MEDIA_MOUNTED)) {
			File file = new File(Environment.getExternalStorageDirectory()
					+ File.separator 
					+ DIR 
					+ File.separator 
					+ FILENAME); // 定义要操作的文件
			if (!file.getParentFile().exists()) {  //判断父文件夹是否存在
				file.getParentFile().mkdirs(); //如不存在，则创建父文件夹
			}
			Scanner scan = null ;  //打印流对象用于输出
			try {
				scan = new Scanner(new FileInputStream(file)) ;
				while(scan.hasNext()) {				
			        //循环设置数据
					Map<String,String> map = new HashMap<String,String>();
		      		map.put("sd_date",     scan.next());
		      		map.put("sd_category", scan.next());
		      		map.put("sd_data",     scan.next());
		      		this.list.add(map);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 一定要关闭流
				if (scan != null) {
					scan.close();
				}
			}
		} else {
			Toast.makeText(this, "保存失败，SD卡不存在！", Toast.LENGTH_LONG).show();
		}
//数据显示*********************************************************************        
        //取得ListView组件
      	this.datalist=(ListView)super.findViewById(R.id.sd_datalist);

      	//实例化SimpleAdapter
      	this.simpleAdapter = new SimpleAdapter(
      			this,
      			this.list,
      			R.layout.data_list,  //取得要使用的List模板
      			//定义显示Key
      			new String[]{"sd_date","sd_category","sd_data"},
      			//与模板中的组件匹配
      			new int[]{R.id.sd_date,
      					R.id.sd_category,
      					R.id.sd_data,});
      	this.datalist.setAdapter(this.simpleAdapter);  //设置显示数据	
//按钮“返回”*******************************************************************
		btReturn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ShowMenu.this.finish();
			}
		});
//按钮“显示图表”*******************************************************************
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