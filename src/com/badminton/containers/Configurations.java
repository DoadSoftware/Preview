package com.badminton.containers;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Configurations")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configurations {
	
	@XmlElement(name = "previewIpAddress")
	private String previewIpAddress;
	
	@XmlElement(name="format")
	private String format;

	public Configurations() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Configurations(String previewIpAddress, String format) {
		super();
		this.previewIpAddress = previewIpAddress;
		this.format = format;
	}


	public String getPreviewIpAddress() {
		return previewIpAddress;
	}

	public void setPreviewIpAddress(String previewIpAddress) {
		this.previewIpAddress = previewIpAddress;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	
}
