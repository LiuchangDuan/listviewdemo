package com.example.listviewdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;


public class MainActivity extends Activity implements OnItemClickListener, OnScrollListener{
	
	private ListView listView;
	private ArrayAdapter<String> arr_adapter;
	private SimpleAdapter simp_adapter;
	private List<Map<String, Object>> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        listView = (ListView) findViewById(R.id.listView);
        
        //1.�½�һ������������
        //ArrayAdapter(������, ��ǰListView���ص�ÿһ���б�������Ӧ�Ĳ����ļ�, ����Դ)
        //SimpleAdapter()
        /**
         * context��������
         * data������Դ  List<? extends Map<String, ?>> data һ��Map����ɵ�List����
         * 		 ÿһ��Map����ȥ��ӦListView�б��е�һ��
         * 		ÿһ��Map����-ֵ�ԣ��еļ��������������from����ָ���ļ� 	
         * resource���б���Ĳ����ļ�ID
         * from��Map�еļ���
         * to����������ͼ�е�ID����from�ɶ�Ӧ��ϵ
         */
        //2.��������������Դ
        String[] arr_data = {"1", "2", "3", "4"};
        dataList = new ArrayList<Map<String, Object>>();
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_data);
        simp_adapter = new SimpleAdapter(this, getData(), R.layout.item, new String[]{"pic", "text"}, new int[]{R.id.pic, R.id.text});
        //3.��ͼ��ListView������������
//        listView.setAdapter(arr_adapter);
        listView.setAdapter(simp_adapter);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(this);
    }
    
    private List<Map<String, Object>> getData() {
    	
    	for (int i = 0; i < 20; i++) {
    		Map<String, Object> map = new HashMap<String, Object>();
    		map.put("pic", R.drawable.ic_launcher);
    		map.put("text", i + "");
    		dataList.add(map);
    	}
    	
    	return dataList;
    }

	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		switch (scrollState) {
		case SCROLL_STATE_FLING:
			Log.i("Main", "�û�����ָ�뿪��Ļ֮ǰ��������������һ�£���ͼ���������Լ�������");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pic", R.drawable.ic_launcher);
			map.put("text", "������");
			dataList.add(map);
			//��̬������ͼ��������������
			simp_adapter.notifyDataSetChanged();
			break;
		case SCROLL_STATE_IDLE:
			Log.i("Main", "��ͼ�Ѿ�ֹͣ����");
			break;
		case SCROLL_STATE_TOUCH_SCROLL:
			Log.i("Main", "��ָû���뿪��Ļ����ͼ���ڻ���");
			break;
		}
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String text = listView.getItemAtPosition(position) + "";
		Toast.makeText(this, "position = " + position + ", text = " + text, Toast.LENGTH_SHORT).show();
	}
    
}
