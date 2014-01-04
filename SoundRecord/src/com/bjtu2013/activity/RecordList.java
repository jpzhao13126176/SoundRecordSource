package com.bjtu2013.activity;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bjtu2013.soundrecord.R;

import android.database.sqlite.SQLiteDatabase;
import com.bjtu2013.comm.util.DataBaseHelper;
import com.bjtu2013.comm.bean.Record;
import com.bjtu2013.comm.dao.RecordDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordList extends ListActivity {
	
	private ListView recordListView = null;
	
	private DataBaseHelper helper = null;
	private SQLiteDatabase db = null;
	private RecordDao recordDao = null;
    
	private List<Integer> recordNoList = new ArrayList<Integer>();
	ArrayList<Map<String, Object>> recordData = new ArrayList<Map<String, Object>>();
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        
        helper = new DataBaseHelper(RecordList.this, "record_db");
        db = helper.getReadableDatabase();
        recordDao = new RecordDao();
        String sortMode = "saveTime desc";
        List<Record> recordList = recordDao.getAllRecords(db, sortMode);
        
        for(Record record : recordList) {
        	recordNoList.add(record.getRecordNo());
        	Map<String, Object> item = new HashMap<String, Object>();
        	item.put("fileName", record.getFileName());
        	item.put("saveTime", record.getSaveTime());
        	recordData.add(item);
        }
        
        recordListView = getListView();
        SimpleAdapter adapter = new SimpleAdapter(this, recordData, R.layout.record_list_item, 
        		                                  new String[]{"fileName", "saveTime"}, new int[]{R.id.fileName, R.id.saveTime});
        setListAdapter(adapter);
        
        recordListView.setOnItemClickListener(new OnItemClickListener() {
        	
        	public void onItemClick(AdapterView<?> adapterview, View view, int position, long id) {
        		//Toast.makeText(RecordList.this, "您选择了文件：" + recordNoList.get(position-1), Toast.LENGTH_LONG).show();
        		Toast.makeText(getApplicationContext(), ((TextView)view).getText(), Toast.LENGTH_LONG).show();
        	}
        });
        
        super.onCreate(savedInstanceState);
    }

}
