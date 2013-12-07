package org.appuniverz.animo;

import java.text.SimpleDateFormat;
import java.util.List;

import com.parse.ParseObject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterSearchResult extends BaseAdapter {

	private  final  static  String TAG = "ADAPTER_SEARCH";
	
	protected LayoutInflater inflater;

	protected List<ParseObject> mydata;
	
	protected ViewHolder holder;
	
	private ImageLoader imageLoader ;
	
	public AdapterSearchResult(Activity act, List<ParseObject> items) {

		this.mydata = items;

		imageLoader = new ImageLoader(act);

		inflater = (LayoutInflater) act
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	
	}

	
	
	@Override
	public int getCount() {
		
		return mydata.size();
	}

	@Override
	public ParseObject getItem(int position) {
	
		return mydata.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return 0;
	}
	
	
	public void addItem ( ParseObject item ) {
		
		mydata.add(item);
		notifyDataSetChanged();
	}
	
	public void addRange( List<ParseObject> list) {
		
		mydata.addAll(list);
		notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		View vi = convertView;
		
		if (convertView == null) {

			vi = inflater.inflate(R.layout.search_row, null);
			
			holder = new ViewHolder();

			holder.Name = (TextView) vi.findViewById(R.id.search_row_name); 
															
			holder.Sex = (TextView) vi.findViewById(R.id.search_row_sex);
										
			
			holder.Kind = (TextView) vi.findViewById(R.id.search_row_kind);
																
			holder.Age = (TextView) vi.findViewById(R.id.search_row_age);
			
			holder.Image = (ImageView) vi.findViewById(R.id.search_row_image);
			
			
			holder.Update = (TextView) vi.findViewById(R.id.search_row_update);
			
			
			
			vi.setTag(holder);
		} else {

			holder = (ViewHolder) vi.getTag();
		}



		holder.Name.setText( "名子： " + mydata.get(position).getString("name"));
		holder.Sex.setText("性別： " + mydata.get(position).getString("sex"));
		holder.Kind.setText("品種： " + mydata.get(position).getString("kind"));
		holder.Age.setText("年齡： " + mydata.get(position).getString("age"));
		holder.Update.setText("更新日期： " +  new SimpleDateFormat("yyyy-MM-dd").format(mydata.get(position).getUpdatedAt()));
		
		
		imageLoader.DisplayImage(
				mydata.get(position).getString("img"),
				holder.Image);

		return vi;
	}
	
	
	static class ViewHolder {
		TextView Name;
		TextView Sex;
		TextView Kind;
		TextView Age;
		TextView Update;
		ImageView Image;
	}
}
