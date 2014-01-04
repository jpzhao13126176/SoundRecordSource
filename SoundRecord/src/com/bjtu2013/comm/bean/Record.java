package com.bjtu2013.comm.bean;

public class Record {
	
	private int recordNo;  // 录音编号，对应数据库索引
	private String fileName;  // 录音文件名
	private String path;  // 录音文件路径
	private double size;  // 录音文件大小
	private String format;  // 录音文件格式
	private String duration;  // 录音持续时间
	private String saveTime;  // 录音文件保存时间
	
	public int getRecordNo() {
		return this.recordNo;
	}
	public void setRecordNo(int recordNo) {
		this.recordNo = recordNo;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getPath() {
		return this.path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public double getSize() {
		return this.size;
	}
	public void setSize(double size) {
		this.size = size;
	}
	
	public String getFormat() {
		return this.format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	
	public String getDuration() {
		return this.duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	public String getSaveTime() {
		return this.saveTime;
	}
	public void setSaveTime(String saveTime) {
		this.saveTime = saveTime;
	}
	
}
