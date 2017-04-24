package com.app.activity.note;



import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.xutils.x;
import org.xutils.ex.DbException;
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
import com.app.adapter.NoteListAdapter;
import com.app.commen.Constant;
import com.app.dao.DaoMs;
import com.app.model.FileMsg;
import com.app.model.NoteModel;
import com.app.utils.ToastUtils;


@ContentView(R.layout.activity_notelist)
public class NoteListActivity extends ActionBarActivity {
	
	@ViewInject(R.id.listv_list_notes)
	
	private ListView main_notelist;
	
	private NoteListAdapter noteListAdapter;
	
	private List<NoteModel> noteModels = new ArrayList<NoteModel>();
	
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        getFileList();
        initAdapter();
        setOnclickListener();
        
        
    }
    
 
    
    private void getFileList(){
    	
    	try {
			noteModels = DaoMs.getInstance().dbNoteFind();
			
			if(noteModels==null){
				noteModels = new ArrayList<NoteModel>();
			}
		} catch (DbException e) {
			e.printStackTrace();
		}
    	
    }
    
    
    
    private void initAdapter(){
    	    	    	
    	this.noteListAdapter = new NoteListAdapter(getApplicationContext(), noteModels);
    	this.main_notelist.setAdapter(this.noteListAdapter);
    }
    
    private void setOnclickListener(){
    	this.main_notelist.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//Auto-generated method stub
				Intent it = new Intent(getApplicationContext(),NoteDetailActivity.class);
				
				it.putExtra("id", noteModels.get(arg2).getId());
				
				startActivity(it);
				finish();
			}
		});
    	
    	
    	



    }
    
    
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //The action bar will automatically handle clicks on the Home/Up button
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
