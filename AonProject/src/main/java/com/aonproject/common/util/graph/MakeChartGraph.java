package com.aonproject.common.util.graph;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MakeChartGraph {
	private static Logger logger = Logger.getLogger(MakeChartGraph.class);
	
	// �Ϻ� ������ ȸ���� + ���� Ż���� ȸ���� + ��ü ȸ���� + ���� ���Լ� + ���� Ż���
	public static void memberCount(HttpServletRequest request, Map<String, Integer> map){
		String docRoot = request.getSession().getServletContext().getRealPath("/chart");
		File fileDir = new File(docRoot);
		if(!fileDir.exists()) fileDir.mkdir();
		
		logger.info("���ε��� ���� ��� : " + docRoot);
		
		String sysdate = "";
		Date date = new Date();
		SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM-dd");
		
		sysdate = sdf.format(date);
		logger.info("���ε��� ��¥ : " + sysdate);
		
		File file = new File(docRoot+"/memberCount" + sysdate + ".jpg");
		FileOutputStream fos = null;
		
		
		try{
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(Map.Entry<String, Integer> result : map.entrySet()){
				logger.info(result.getKey() + " = " + result.getValue());
				dataset.addValue(result.getValue(),result.getKey(), result.getKey());
			}
			
			JFreeChart chart = ChartFactory.createBarChart("�Ϻ� ȸ�� ���", sysdate, "�ο���(���� : ��)", dataset, PlotOrientation.HORIZONTAL, true, true, false);
			
			chart.setBackgroundPaint(Color.WHITE);
			chart.getTitle().setFont(new Font("sansserif", Font.BOLD, 16));
			
			Font font = new Font("sonsserif", Font.BOLD, 12);
			chart.getLegend().setItemFont(font);
			
			CategoryPlot plot = chart.getCategoryPlot();
			
			plot.getDomainAxis().setLabelFont(font); 
			plot.getDomainAxis().setTickLabelFont(font); 
			plot.getRangeAxis().setLabelFont(font); 
			plot.getRangeAxis().setTickLabelFont(font);
			
			fos = new FileOutputStream(file);
			
			ChartUtilities.writeChartAsJPEG(fos, chart, 720, 480);
		}
		catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
		finally{
			try{
				if(fos != null) fos.close();
			}
			catch(Exception s){
				s.getMessage();
				s.printStackTrace();
			}
		}
	}
	
	
	// ȸ�� ���� ������
	public static void memberSexDistribution(HttpServletRequest request, Map<String, Integer> map){
		String docRoot = request.getSession().getServletContext().getRealPath("/chart");
		File fileDir = new File(docRoot);
		if(!fileDir.exists()) fileDir.mkdir();
		
		logger.info("���ε��� ���� ��� : " + docRoot);
		File file = new File(docRoot+"/memberSexDistribution.jpg");
		FileOutputStream fos = null;
		
		try{
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			for(Map.Entry<String, Integer> result : map.entrySet()){
				logger.info(result.getKey() + " = " + result.getValue());
				dataset.addValue(result.getValue(),result.getKey(), result.getKey());
			}
			
			JFreeChart chart = ChartFactory.createBarChart("���� ���", "����", "�ο���(���� : ��)", dataset, PlotOrientation.VERTICAL, true, true, false);
			
			chart.setBackgroundPaint(Color.WHITE);
			chart.getTitle().setFont(new Font("sansserif", Font.BOLD, 16));
			
			Font font = new Font("sonsserif", Font.BOLD, 12);
			chart.getLegend().setItemFont(font);
			
			CategoryPlot plot = chart.getCategoryPlot();
			
			plot.getDomainAxis().setLabelFont(font); 
			plot.getDomainAxis().setTickLabelFont(font); 
			plot.getRangeAxis().setLabelFont(font); 
			plot.getRangeAxis().setTickLabelFont(font);
			
			fos = new FileOutputStream(file);
			
			ChartUtilities.writeChartAsJPEG(fos, chart, 720, 480);
		}
		catch(Exception e){
			e.getMessage();
			e.printStackTrace();
		}
		finally{
			try{
				if(fos != null) fos.close();
			}
			catch(Exception s){
				s.getMessage();
				s.printStackTrace();
			}
		}
	}
}
