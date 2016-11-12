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
	
	//�ļ���ȡ����
	private static final String FILENAME = "Diabetes_Data.txt" ;	// �����ļ�����
	private static final String DIR = "DiabetesData" ;	// �����ļ��е�����
	
	int count = 0;
	String data[] = new String [10];
	String data_date[] = new String [10];
	
	//������ͼ����
	private XYMultipleSeriesDataset mDataset;
	private XYSeries series;
    private String title = "Ѫ��";

//��ȡSD���ϵ�����*****************************************************************
    //����һ��getData�������ں����õ�
    public void getData() {
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

				int i=0;
				while(scan.hasNext()) {				
			        //ѭ����������					
					data_date[i] = scan.next();
					scan.next();
					data[i] = scan.next();
					count++;
					i++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally { // һ��Ҫ�ر���
				if (scan != null) {
					scan.close();
				}
			}
		}
	}

//������ͼ*****************************************************************	
	//�����޲η���
	public String getName() {
		return "Chart";
	}
	public String getDesc() {
		return "The data of input (line chart)";
	}
	
	//��ͼ�¼�
	public Intent execute(Context context) {
		getData();
		//��������
		mDataset = new XYMultipleSeriesDataset();
		series = new XYSeries(title);
		int ymax=Integer.parseInt(data[0].toString().trim());
		for (int j=0;j<count;j++){
			series.add((j+1), Integer.parseInt(data[j].toString().trim()));
			if (ymax < Integer.parseInt(data[j].toString().trim())){
				ymax = Integer.parseInt(data[j].toString().trim());
			}  //ȡ������yֵ
		}
		mDataset.addSeries(series);
		/*String[] titles = new String[] { 
				"Ѫ��", 
				"2", 
				"3",};// ͼ��
		List<double[]> x = new ArrayList<double[]>();

			x.add(new double[]{1});// ÿ�������е��X����
	
		List<double[]> values = new ArrayList<double[]>();
		values.add(new double[] { 20, 25, 30, 35, 20.4});// ����1�е��y����
*/		
/*		values.add(new double[] { 10, 10, 12, 15, 20, 24, 26, 26, 23, 18, 14,
				11 });// ����2�е��Y����
		values.add(new double[] { 5, 5.3, 8, 12, 17, 22, 24.2, 24, 19, 
				15, 9, 6 });// ����3�е��Y����
*/		
	    
	    //��ʽ����
		int[] colors = new int[] { 
				Color.BLUE, 
/*				Color.GREEN, 
				Color.CYAN,*/};// ÿ�����е���ɫ����
		
		PointStyle[] styles = new PointStyle[] { 
				PointStyle.CIRCLE,
/*				PointStyle.DIAMOND, 
				PointStyle.TRIANGLE, */};// ÿ�������е����״����
		
		// ����AbstractDemoChart�еķ�������renderer
		XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
		int length = renderer.getSeriesRendererCount();
		for (int i = 0; i < length; i++) {
			((XYSeriesRenderer) renderer.getSeriesRendererAt(i))
					.setFillPoints(true);// ����ͼ�ϵĵ�Ϊʵ��
		}
		
		// ����AbstractDemoChart�еķ�������ͼ���renderer����
		setChartSettings(renderer, 
				getName(), 
				"Time",  //x������ʾ��Ϣ
				"Value",   //y������ʾ��Ϣ
				0, count,  //x����Ĭ�Ϸ�Χ   
				0, ymax,  //y����Ĭ�Ϸ�Χ
				Color.BLACK, Color.BLACK);  //x,y������ɫ
		renderer.setXLabels(6);// ����x����ʾ12����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��
		renderer.setYLabels(5);// ����y����ʾ10����,����setChartSettings�����ֵ����Сֵ�Զ������ļ��
		renderer.setLabelsColor(Color.BLACK);
		renderer.setShowGrid(true);// �Ƿ���ʾ����
		renderer.setGridColor(Color.LTGRAY);
		renderer.setXLabelsAlign(Align.RIGHT);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
		renderer.setYLabelsAlign(Align.CENTER);// �̶�����̶ȱ�ע֮������λ�ù�ϵ
		renderer.setBackgroundColor(Color.WHITE);
		renderer.setMarginsColor(Color.WHITE);
		
/*		renderer.setZoomButtonsVisible(true);// �Ƿ���ʾ�Ŵ���С��ť
		renderer.setPanLimits(new double[] { -10, 20, -10, 40 }); // �����϶�ʱX��Y����������ֵ��Сֵ.
		renderer.setZoomLimits(new double[] { -10, 20, -10, 40 });// ���÷Ŵ���СʱX��Y������������Сֵ.
		*/
		
		// ����Intent
		Intent intent = ChartFactory.getLineChartIntent(
				context,
				mDataset, 
				renderer,
				"ͼ����ʾ"); //Ӧ������ʾ�ı���
		return intent;
	}
}