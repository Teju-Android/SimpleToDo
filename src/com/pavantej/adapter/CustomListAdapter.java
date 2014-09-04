package com.pavantej.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CustomListAdapter<T> extends ArrayAdapter<T> {

	public CustomListAdapter(Context context, int textView_1, ArrayList<T> items) {
		super(context, textView_1, items);
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
            view.setBackgroundColor(Color.LTGRAY);
        return view;
    }

}
