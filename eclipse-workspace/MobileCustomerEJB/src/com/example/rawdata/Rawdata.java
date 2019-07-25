package com.example.rawdata;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@XmlRootElement
public class Rawdata {

@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
private String dateof;

private String injuryType;

private String description;


public String getDateof() {
	return dateof;
}

public void setDateof(String dateof) {
	this.dateof = dateof;
}

public String getInjuryType() {
	return injuryType;
}

public void setInjuryType(String injuryType) {
	this.injuryType = injuryType;
}

public String getDescription() {
	return description;
}

public void setDesciption(String description) {
	this.description = description;
}




}
