package com.pavantej.simpletodo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class TodoActivity extends Activity {

	ListView lvItems;
	ArrayList<String> items;
	ArrayAdapter<String> itemsAdapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        lvItems = (ListView) findViewById(R.id.lvTodoItems);
        items = new ArrayList<String>();
        readItems();
        itemsAdapter = new ArrayAdapter<String>(this,
        		android.R.layout.simple_list_item_1, items);
        lvItems.setAdapter(itemsAdapter);
        setupListViewListener();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.todo, menu);
        return true;
    }
    
    public void addTodoItem(View v){
    	EditText etNewItem = (EditText) findViewById(R.id.etNewItem);
    	itemsAdapter.add(etNewItem.getText().toString());
    	etNewItem.setText("");
    	saveItems();
    }

    private void setupListViewListener(){
    	lvItems.setOnItemLongClickListener(new OnItemLongClickListener(){
    		@Override
    		public boolean onItemLongClick(AdapterView<?> parent,
    				View v, int position, long rowId){
    			items.remove(position);
    			itemsAdapter.notifyDataSetChanged();
    			saveItems();
    			return true;
    		}
    	});
    }
    
    private void readItems(){
    	File itemsFile = getFilesDir();
    	File todoFile = new File(itemsFile, "todo.txt");
    	try {
			items = new ArrayList<String>(FileUtils.readLines(todoFile));
		} catch (IOException e) {
			items = new ArrayList<String>();
			e.printStackTrace();
		}
    }
    
    private void saveItems(){
    	File itemsFile = getFilesDir();
    	File todoFile = new File(itemsFile, "todo.txt");
    	try {
			FileUtils.writeLines(todoFile, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
}
