package com.bjtu2013.comm.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	private static final int VERSION = 1;
	
	public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	public DataBaseHelper(Context context, String name) {
		this(context, name, VERSION);
	}
	
	public DataBaseHelper(Context context, String name, int version) {
		this(context, name, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		System.out.println("create a database");
		db.execSQL("create table record(recordNo INTEGER PRIMARY KEY AUTOINCREMENT, fileName TEXT," +
				   "  path TEXT, size REAL, format TEXT, duration TEXT, saveTime TEXT)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		System.out.println("update a database");
	}
	
}
