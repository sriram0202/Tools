package com.datagen.DB;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class IOTDao {

	public void insertIOTData(List <IOTData> objIOTList) {
		
		PreparedStatement preparedStatement = null;
		JDBCConnect.connect();
		String insertTableSQL = "INSERT INTO IOTDATA"
				+ "(date, timestamp, roomtemp,coEm,co2Em,earthQuake,humidity,doorStatus, energyCon) VALUES"
				+ "(?,?,?,?,?,?,?,?,?)";
		Integer icnt = 0;
		try {
		preparedStatement = JDBCConnect.conn.prepareStatement(insertTableSQL);
		for (Iterator<IOTData> iterator = objIOTList.iterator(); iterator.hasNext();) {
			IOTData objIOT = (IOTData) iterator.next();
				JDBCConnect.conn.setAutoCommit(false);
				preparedStatement.setString(1,objIOT.getDate());
				preparedStatement.setString(2,objIOT.getTimestamp());
				preparedStatement.setString(3,objIOT.getRoomtemp());
				preparedStatement.setString(4,objIOT.getCoEm());
				preparedStatement.setString(5,objIOT.getCo2Em());
				preparedStatement.setString(6,objIOT.getEq());
				preparedStatement.setString(7,objIOT.getHumidity());
				preparedStatement.setString(8,objIOT.getDoorSt());
				preparedStatement.setString(9,objIOT.getEnergyCon());
				preparedStatement.addBatch();
				icnt++;
				if ((icnt > 5000) || (icnt >= objIOTList.size()-1)) 
				{
					preparedStatement.executeBatch();
					JDBCConnect.conn.commit();
					icnt = 0;
				}					
		}
		} catch (SQLException e) {

			e.printStackTrace();
		}	
		
		
	}
}