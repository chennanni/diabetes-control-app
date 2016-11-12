package com.example.diabetescontrol_2;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Environment;

public class ShowChart extends AbstractDemoChart {
	
	//文件读取定义
	private static final String FILENAME = "Diabetes_Data.txt" ;	// 设置文件名称
	private static final String DIR = "DiabetesData" ;	// 操作文件夹的名称
	
	int count = 0;
	String data[] = new String [10];
	String data_date[] = new String [10];
	
	//数据作图定义
	private XYMultipleSeriesDataset mDataset;
	private XYSeries series;
    private String title = "血糖";

//读取SD卡上的数据*****************************************************************
    //定义一个getData函数，在后面用到
    public void getData() {
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

				int i=0;
				while(scan.hasNext()) {				
			        //循环设置数据					
					data_date[i] = scan.next();
					scan.next();
					data[i] = scan.next();
					count++;
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // 一定要关闭流
				if (scan != null) {
					scan.close();
				}
			}
		}
	}

//数据作图*****************************************************************	
	//定义无参方法
	public String getName() {
		return "Chart";
	}
	public String getDesc() {
		return "The data of input (line chart)";
	}
	
	//作图事件
	public Intent execute(Context context) {
		getData();
		//数据设置
		mDataset = new XYMultipleSeriesDataset();
		series = new XYSeries(title);
		int ymax=Integer.parseInt(data[0].toString().trim());
		for (int j=0;j<count;j++){
			series.add((j+1), Integer.parseInt(data[j].toString().trim()));
			if (ymax < Integer.parseInt(data[j].toString().trim())){
				ymax = Integer.parseInt(data[j].toString().trim());
			}  //取得最大的y值
		}
		mDataset.addSeries(series);
		/*String[] titles = new String[] { 
				"血糖", 
				"2", 
				"3",};// 图例
		List<double[]> x = new ArrayList<double[]>();

			x.add(new double[]{1});// 每个序列中点的X坐标
	
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 20, 25, 30, 35, 20.4});// 序列1中点的y坐标
*/		
/*		values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14,
				11 });// 序列2中点的Y坐标
		values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 
				15, 9, 6 });// 序列3中点的Y坐标
*/		
	    
	    //样式设置
		int[] colors = new int[] { 
				Color.BLUE, 
/*				Color.GREEN, 
				Color.CYAN,*/};// 每个序列的颜色设置
		
		PointStyle[] styles = new PointStyle[] { 
				PointStyle.CIRCLE,
/*				PointStyle.DIAMOND, 
				PointStyle.TRIANGLE, */};// 每个序列中点的形状设置
		
		// 调用AbstractDemoChart中的方法设置renderer
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);// 设置图上的点为实心
		}
		
		// 调用AbstractDemoChart中的方法设置图表的renderer属性
		setChartSettings(renderer, 
				getName(), 
				"Time",  //x坐标提示信息
				"Value",   //y坐标提示信息
				0, count,  //x坐标默认范围   
				0, ymax,  //y坐标默认范围
				Color.BLACK, Color.BLACK);  //x,y坐标颜色
		renderer.setXLabels(6);// 设置x轴显示12个点,根据setChartSettings的最大值和最小值自动计算点的间隔
		renderer.setYLabels(5);// 设置y轴显示10个点,根据setChartSettings的最大值和最小值自动计算点的间隔
		renderer.setLabelsColor(Color.BLACK);
		renderer.setShowGrid(true);// 是否显示网格
		renderer.setGridColor(Color.LTGRAY);
		renderer.setXLabelsAlign(Align.RIGHT);// 刻度线与刻度标注之间的相对位置关系
		renderer.setYLabelsAlign(Align.CENTER);// 刻度线与刻度标注之间的相对位置关系
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setMarginsColor(Color.WHITE);
		
/*		renderer.setZoomButtonsVisible(true);// 是否显示放大缩小按钮
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); // 设置拖动时X轴Y轴允许的最大值最小值.
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });// 设置放大缩小时X轴Y轴允许的最大最小值.
		*/
		
		// 构建Intent
		Intent intent = ChartFactory.getLineChartIntent(
				context,
				mDataset, 
				renderer,
				"图表显示"); //应用上显示的标题
		return intent;
	}
}