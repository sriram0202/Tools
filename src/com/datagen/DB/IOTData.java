package com.datagen.DB;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "user")
public class IOTData implements Serializable {

   private static final long serialVersionUID = 1L;
   
public String getDate() {
	return date;
}

public void setDate(String date) {
	this.date = date;
}

public String getTimestamp() {
	return timestamp;
}

public void setTimestamp(String timestamp) {
	this.timestamp = timestamp;
}

public String getRoomtemp() {
	return roomtemp;
}

public void setRoomtemp(String roomtemp) {
	this.roomtemp = roomtemp;
}

public String getCoEm() {
	return coEm;
}

public void setCoEm(String coEm) {
	this.coEm = coEm;
}

public String getCo2Em() {
	return co2Em;
}

public void setCo2Em(String co2Em) {
	this.co2Em = co2Em;
}

public String getEq() {
	return eq;
}

public void setEq(String eq) {
	this.eq = eq;
}

public String getHumidity() {
	return humidity;
}

public void setHumidity(String humidity) {
	this.humidity = humidity;
}

public String getDoorSt() {
	return doorSt;
}

public void setDoorSt(String doorSt) {
	this.doorSt = doorSt;
}

public String getEnergyCon() {
	return energyCon;
}

public void setEnergyCon(String energyCon) {
	this.energyCon = energyCon;
}
   private String date;
   private String timestamp;
   private String roomtemp;
   private String coEm;
   private String co2Em;
   private String eq;
   private String humidity;
   private String doorSt;
   private String energyCon;
public IOTData(String date, String timestamp, String roomtemp, String coEm, String co2Em, String eq,
		String humidity, String doorSt, String energyCon) {
	super();

	this.date = date;
	this.timestamp = timestamp;
	this.roomtemp = roomtemp;
	this.coEm = coEm;
	this.co2Em = co2Em;
	this.eq = eq;
	this.humidity = humidity;
	this.doorSt = doorSt;
	this.energyCon = energyCon;
}


 }