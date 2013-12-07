package org.appuniverz.animo.asynctasks;

import java.util.List;

import org.appuniverz.animo.AdapterSearchResult;

import org.appuniverz.animo.SearchResult;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity; 
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SearchTask extends AsyncTask<String, Integer, Boolean> {
	
	private final static String TAG = "SearchTask";
	
	private ProgressDialog dialog;
	private SearchResult activity;

	public SearchTask(Activity activity) {
		
		// init dialog in contructer
		this.activity = (SearchResult)activity;
		dialog = new ProgressDialog(activity);
		
	}
	
	@Override
	protected void onPreExecute() {
		
		dialog.setCanceledOnTouchOutside(false);
		this.dialog.setMessage("搜尋中.....");
		this.dialog.show();
		
	}
	
	
	@Override
	protected Boolean doInBackground(String... params) {
		
		this.dialog.setMessage("處理資料中.....");
		
		String area = params[0];
		String type = params[1];
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery("animo");
		query.whereEqualTo("location", area ).whereEqualTo("type", type);
		query.findInBackground(new FindCallback<ParseObject>() {
			@Override
			public void done(List<ParseObject> list, ParseException e) {
				
				if ( e == null )
				{	
					activity.adapter.addRange(list);
						
				}
				else
				{
					Log.i(TAG, e.getMessage());
				}
				
			}
		});		
		
		
		return true;
	}
	
	
	@Override
	protected void onPostExecute(final Boolean status) {
		
			if ( status == true)
			{	
				
				dialog.dismiss();
			}
	}
}
