package com.bjtu2013.comm.bean;

public class Record {
	
	private int recordNo;  // ¼����ţ���Ӧ���ݿ�����
	private String fileName;  // ¼���ļ���
	private String path;  // ¼���ļ�·��
	private double size;  // ¼���ļ���С
	private String format;  // ¼���ļ���ʽ
	private String duration;  // ¼������ʱ��
	private String saveTime;  // ¼���ļ�����ʱ��
	
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
