package org.appuniverz.animo.fragments;

import org.appuniverz.animo.R;
import org.appuniverz.animo.SearchResult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;


public class SearchTab extends Fragment  {

	private View view = null;
	
	private Spinner areaSpinner;
	private Spinner typeSpinner;
	private Button submit ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		

			
			view = inflater.inflate(R.layout.tab_search, container, false);
			
			/* Init Area Spinner List */
			areaSpinner = (Spinner)view.findViewById(R.id.tab_search_spinner_area);
			
			ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(),
					R.array.tab_search_area, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    
		    areaSpinner.setAdapter(adapter);		
		    
		    typeSpinner = (Spinner)view.findViewById(R.id.tab_search_spinner_type);
			
			/* typeSpinner Init Area Spinner List */
			
			adapter = ArrayAdapter.createFromResource(this.getActivity(),
		            R.array.tab_search_type, android.R.layout.simple_spinner_item);
		    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		    typeSpinner.setAdapter(adapter);		

		    /* submit button init */
		    submit = (Button)view.findViewById(R.id.tab_search_button_submit);
		    
		    submit.setOnClickListener( new OnClickListener (){

				@Override
				public void onClick(View v) {
				
					Intent intent  = new Intent();
					intent.setClass(getActivity(),  SearchResult.class);
					
					intent.putExtra("area", areaSpinner.getSelectedItem().toString());
					intent.putExtra("type", typeSpinner.getSelectedItem().toString());
					
					getActivity().startActivity(intent);
					
				}
		    	
		    
		    });
		    
		return view;

	}
	

}
