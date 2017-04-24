package com.app.activity.book;



import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.app.activity.R;
import com.app.adapter.BookListAdapter;
import com.app.commen.Constant;
import com.app.model.FileMsg;
import com.app.utils.ToastUtils;


@ContentView(R.layout.activity_booklist)
public class BookListActivity extends ActionBarActivity {
	
	@ViewInject(R.id.listv_list_books)
	
	private ListView main_booklist;
	
	private BookListAdapter bookListAdapter;
	
	private List<FileMsg> fileMsgs = new ArrayList<FileMsg>();
	
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        getFileList();
        initAdapter();
        setOnclickListener();
        
        
    }
    
 
    
    private void getFileList(){
    	Constant.filemsg.clear();
    	File file = new File("/sdcard/");
    	File[] files = file.listFiles();
    	System.out.println(files);
    	ToastUtils.makeLongText(files.length+"", getApplicationContext());
    	for(File f:files){
    		if(f.getName().endsWith(".txt")){
    			ToastUtils.makeLongText(f.getName()+"@@"+(f.length()/1024), getApplicationContext());
    			Constant.filemsg.add(f.getName()+"@@"+(f.length()/1024));
    		}
    	}
    	
    	
    }
    
    
    
    private void initAdapter(){
    	
    	for (String item:Constant.filemsg){
    		FileMsg fileMsg = new FileMsg(System.currentTimeMillis(),
    				item.split("@@")[0], Long.parseLong(item.split("@@")[1]));
    		
    		fileMsgs.add(fileMsg);
    		
    		
    	}
    	    	
    	this.bookListAdapter = new BookListAdapter(getApplicationContext(), fileMsgs);
    	this.main_booklist.setAdapter(this.bookListAdapter);
    }
    
    private void setOnclickListener(){
    	this.main_booklist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Intent it = new Intent(getApplicationContext(),BookDetailActivity.class);
				
				it.putExtra("filename", fileMsgs.get(arg2).getFileName());
				
				startActivity(it);
			}
		});
    	
    	
    	



    }
    
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
