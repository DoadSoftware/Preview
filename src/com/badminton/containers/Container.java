package com.badminton.containers;

import java.util.Arrays;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="container")
@XmlAccessorType(XmlAccessType.FIELD)
public class Container implements Comparable<Container> {
//public class Container {
	
	@XmlElement(name = "container_id")
	private String container_id;
	  
	@XmlElement(name = "container_value")
	private String container_value;

	private String content_type;
	  
	private byte[] file_data;
	
	public Container(String container_id, String container_value) {
		super();
		this.container_id = container_id;
		this.container_value = container_value;
	}
	
	public Container() {
		super();
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public byte[] getFile_data() {
		return file_data;
	}

	public void setFile_data(byte[] file_data) {
		this.file_data = file_data;
	}

	public String getContainer_id() {
		return container_id;
	}

	public void setContainer_id(String container_id) {
		this.container_id = container_id;
	}

	public String getContainer_value() {
		return container_value;
	}

	public void setContainer_value(String container_value) {
		this.container_value = container_value;
	}

	@Override
	public int compareTo(Container con) {
		
		return this.container_id.compareTo(con.container_id);
	}

	@Override
	public String toString() {
		return "Container [container_id=" + container_id + ", container_value=" + container_value + ", content_type="
				+ content_type + ", file_data=" + Arrays.toString(file_data) + "]";
	}
	
}
