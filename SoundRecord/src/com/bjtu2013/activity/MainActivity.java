package com.bjtu2013.activity;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import com.bjtu2013.soundrecord.R;

import android.database.sqlite.SQLiteDatabase;
import com.bjtu2013.comm.util.DataBaseHelper;

import com.bjtu2013.comm.bean.Record;
import com.bjtu2013.comm.dao.RecordDao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
	
	private Button viewRecordListBtn, makeRecordBtn = null; 
	private Button insertDbBtn, updateDbBtn, queryDbBtn, deleteDbBtn = null;
	private EditText msg = null;
	
	private DataBaseHelper helper = null;
	private SQLiteDatabase db = null;
	private RecordDao recordDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dboperation);
        
        viewRecordListBtn = (Button)findViewById(R.id.button_viewRecordList);
        makeRecordBtn = (Button)findViewById(R.id.button_makeRecord);
        
        insertDbBtn = (Button)findViewById(R.id.button_insertDb);
        updateDbBtn = (Button)findViewById(R.id.button_updateDb);
        queryDbBtn = (Button)findViewById(R.id.button_queryDb);
        deleteDbBtn = (Button)findViewById(R.id.button_deleteDb);
        
        viewRecordListBtn.setOnClickListener(new ViewRecordList_Listener());
        makeRecordBtn.setOnClickListener(new MakeRecord_Listener());
        
        insertDbBtn.setOnClickListener(new InsertDb_Listener());
        updateDbBtn.setOnClickListener(new UpdateDb_Listener());
        queryDbBtn.setOnClickListener(new QueryDb_Listener());
        deleteDbBtn.setOnClickListener(new DeleteDb_Listener());
        
        msg = (EditText)findViewById(R.id.msg);
        
        helper = new DataBaseHelper(MainActivity.this, "record_db");
        db = helper.getReadableDatabase();
        recordDao = new RecordDao();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    class ViewRecordList_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();  
			intent.setClass(MainActivity.this, RecordList.class);  
		    startActivity(intent);  
		}
    }
    
    class MakeRecord_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent intent = new Intent();  
			intent.setClass(MainActivity.this, MakeRecordActivity.class);  
		    startActivity(intent);  
		}
    }
    
    class InsertDb_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Record record = new Record();
			record.setFileName("phone_record_20130102215456");
			record.setPath("/data/data/com.bjtu2013.soundrecord/file/");
			record.setSize(312);
			record.setFormat("wma");
			record.setDuration("51s");
			record.setSaveTime("2012/11/02 21:54:56");
			recordDao.insertRecord(db, record);
			msg.setText("Insert Database Success!");
		}
    }
    
    class UpdateDb_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Record record = new Record();
			record.setRecordNo(12);
			record.setFileName("newFile");
			recordDao.renameRecord(db, record);
			msg.setText("FileName: " + record.getFileName());
		}
    }
    
    class QueryDb_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			//String sortMode = "fileName asc"; // 按文件名字母顺序排列
			//String sortMode = "saveTime desc";  // 按保存时间最近优先顺序排列
			//String sortMode = "size asc";  // 按文件大小升序排列
			String sortMode = "format asc";  // 按文件格式字母顺序排列
			List<Record> recordList = recordDao.getAllRecords(db, sortMode);
			String name = "";
			for (Record record : recordList) {
				name += String.valueOf(record.getRecordNo());
			}
			msg.setText("Query: " + name);
		}
    }
    
    class DeleteDb_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			List<String> recordNoList = new ArrayList<String>();
			recordNoList.add("7");
			recordNoList.add("11");
			recordDao.deleteRecords(db, recordNoList);
			msg.setText("Delete Records Success!");
		}
    }
    
}
