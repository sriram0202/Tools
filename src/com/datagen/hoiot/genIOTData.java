package com.datagen.hoiot;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.datagen.DB.IOTDao;
import com.datagen.DB.IOTData;

import java.util.ArrayList;
import java.util.Calendar;

public class genIOTData {
	public static final int minRT = 50;
	public static final int maxRT = 150;
	public static final int minCO = 50;
	public static final int maxCO = 80;
	public static final int minCO2 = 350;
	public static final int maxCO2 = 1000;
	public static final int minRS = 0;
	public static final int maxRS = 12;
	public static final int minHU = 10;
	public static final int maxHU = 100;
	public static final int minEN = 0;
	public static final int maxEN = 5;
	
	public static final int timeInt = 240;
	
	public static void main(String[] args) {
		String fileName = "C:\\Work\\temp\\test.csv";
		Integer recCn = 100;
		if (args.length > 0)
		{	
			if (args[0]!=null)
			{
				fileName = args[0];
			}
			if (args[1]!=null)
			{
				recCn = new Integer(args[1]);
			}
		}
		//generateCsvFile(fileName, recCn);
		insertToDB(recCn);

	}
	private static void generateCsvFile(String sFileName, Integer recCount)
	   {
			Integer sno = 0;
			//create Calendar instance
		    Calendar tm = Calendar.getInstance();
			String time = "";
			final int minRT = 50;
		
			try
			{
			    FileWriter writer = new FileWriter(sFileName);
				 
			    writer.append("SNO");
			    writer.append(',');
			    writer.append("Date");
			    writer.append(',');
			    writer.append("Timestamp");
			    writer.append(',');
			    writer.append("Room Temperature(F)");
			    writer.append(',');
			    writer.append("CO (ppm)");
			    writer.append(',');
			    writer.append("CO2 (ppm)");
			    writer.append(',');
			    writer.append("Earth Quake (RS)");
			    writer.append(',');
			    writer.append("Humidity %");
			    writer.append(',');
			    writer.append("Home Door Status (Open/Locked)");
			    writer.append(',');
			    writer.append("Energy Consumption (kW)");		    
			    writer.append('\n');
			    DateFormat dt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			    // Generate Random Data for HO IOT
			    for (int i=0;i<=recCount;i++)
			    {
			    	sno++;
			    	time = tm.get(Calendar.HOUR_OF_DAY) + ":" + tm.get(Calendar.MINUTE) + ":" + tm.get(Calendar.SECOND) ;
			    	writer.append(sno.toString());
				    writer.append(',');
				    writer.append(dt.format(tm.getTime()));
				    writer.append(',');				    
				    writer.append(time);
				    writer.append(',');
				    //Increase timestamp by X mins
				    tm.add(Calendar.MINUTE, timeInt);	
				   
				    writer.append(randInt(minRT,maxRT).toString());
				    writer.append(',');
				    writer.append(randInt(minCO,maxCO).toString());
				    writer.append(',');
				    writer.append(randInt(minCO2,maxCO2).toString());
				    writer.append(',');
				    writer.append(randDecimal(minRS,maxRS).toString());
				    writer.append(',');
				    writer.append(randInt(minHU,maxHU).toString());
				    writer.append(',');
				    writer.append(randInt(0,1).toString());
				    writer.append(',');
				    writer.append(randDecimal(minEN,maxEN).toString());
				    writer.append('\n');
			    }
			    
				
			    writer.flush();
			    writer.close();
			}
			catch(IOException e)
			{
			     e.printStackTrace();
			} 
	   }
	private static void insertToDB(Integer recCount)
	   {
				List<IOTData> objIOTList = null;
				objIOTList = new ArrayList<IOTData>();
				Integer sno = 0;
				//create Calendar instance
			    Calendar tm = Calendar.getInstance();
			    DateFormat dt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				String time = "";
				System.out.println("Data insert started");
			    // Generate Random Data for HO IOT
			    for (int i=0;i<=recCount;i++)
			    {
			    	sno++;
			    	time = tm.get(Calendar.HOUR_OF_DAY) + ":" + tm.get(Calendar.MINUTE) + ":" + tm.get(Calendar.SECOND) + ":" +tm.get(Calendar.MILLISECOND);			    	
			    	IOTData objData = new IOTData(dt.format(tm.getTime()),time,randInt(minRT,maxRT).toString(),randInt(minCO,maxCO).toString(),randInt(minCO2,maxCO2).toString(),randDecimal(minRS,maxRS).toString(),randInt(minHU,maxHU).toString(),randInt(0,1).toString(),randDecimal(minEN,maxEN).toString());
			    	 //Increase timestamp
				    tm.add(Calendar.MINUTE, timeInt);
				    objIOTList.add(objData);
			    }
			    
				
				IOTDao objDao = new IOTDao();
				objDao.insertIOTData(objIOTList);
				System.out.println("Data insert complete");
				
	   }
	public static Integer randInt(int min, int max) {

	    Random rand = new Random();
	    Integer randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}
	public static Double randDecimal(int min, int max) {

	    Random rand = new Random();
	    Double randomNum = rand.nextInt((max - min) + 1) + Math.random();
	    return new Double(Math.round(randomNum*100)/100);
	}
}
