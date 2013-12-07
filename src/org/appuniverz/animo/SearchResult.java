package org.appuniverz.animo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.appuniverz.animo.asynctasks.SearchTask;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SearchResult extends Activity implements OnClickListener {

	public ListView list ;
	
	private String search_area = "";
	private String search_type = "";
	
	private List<ParseObject> data ;
	
	public AdapterSearchResult adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_result);
		
		list = (ListView) findViewById(R.id.activity_search_list);
		
		Intent intent = getIntent();
		
		data = new ArrayList<ParseObject>();
			
		adapter = new 	AdapterSearchResult(this,data);
		
		/* get Extra String */
		

		search_area = intent.getExtras().getString("area");
		search_type = intent.getExtras().getString("type");
		
		
		
		
		SearchTask task = new SearchTask(this);
		
		task.execute(search_area,search_type);

		list.setAdapter(adapter);
		list.invalidate();
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	

}
