package com.bjtu2013.comm.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.bjtu2013.comm.bean.Record;

import java.util.ArrayList;
import java.util.List;

public class RecordDao {
	
	// 新建一条录音记录
	public void insertRecord(SQLiteDatabase db, Record record) {
		ContentValues value = new ContentValues();
		value.put("fileName", record.getFileName());
		value.put("path", record.getPath());
		value.put("size", record.getSize());
		value.put("format", record.getFormat());
		value.put("duration", record.getDuration());
		value.put("saveTime", record.getSaveTime());
		db.insert("record", null, value);
	}
	
	// 批量删除录音文件记录
	public void deleteRecords(SQLiteDatabase db, List<String> recordNoList) {
		for (String recordNo : recordNoList) {
			db.delete("record", "recordNo=?", new String[]{recordNo});
		}
	}	
	
	// 录音文件重命名
	public void renameRecord(SQLiteDatabase db, Record record) {
		ContentValues value = new ContentValues();
		value.put("fileName", record.getFileName());
		String recordNo = String.valueOf(record.getRecordNo());
		db.update("record", value, "recordNo=?", new String[]{recordNo});
	}
	
	// 按照一定的排序规则获得全部录音文件列表
	public List<Record> getAllRecords(SQLiteDatabase db, String sortMode) {
		List<Record> recordList = new ArrayList<Record>();
	    Cursor cursor = db.query("record", null, null, null, null, null, sortMode);

        while(cursor.moveToNext()) {
        	Record record = new Record();
            record.setRecordNo(Integer.parseInt(cursor.getString(cursor.getColumnIndex("recordNo"))));
            record.setFileName(cursor.getString(cursor.getColumnIndex("fileName")));
            record.setPath(cursor.getString(cursor.getColumnIndex("path")));
            record.setSize(Double.parseDouble(cursor.getString(cursor.getColumnIndex("size"))));
            record.setFormat(cursor.getString(cursor.getColumnIndex("format")));
            record.setDuration(cursor.getString(cursor.getColumnIndex("duration")));
            record.setSaveTime(cursor.getString(cursor.getColumnIndex("saveTime")));
            recordList.add(record);
        }
        cursor.close();
        
        return recordList;
	}
	
}
