package com.arenglish.domain;

import java.io.File;

public class Image {
	
	
	private String id;
	private Integer tag;
	private String path;
	private String undefinedName;
	private File file;
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getTag() {
		return tag;
	}
	public void setTag(Integer tag) {
		this.tag = tag;
	}
	public String getUndefinedName() {
		return undefinedName;
	}
	public void setUndefinedName(String undefinedName) {
		this.undefinedName = undefinedName;
	}
	
	
	
	
}
