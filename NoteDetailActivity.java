package com.app.activity.note;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.Date;

import org.xutils.x;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

import com.app.activity.R;
import com.app.dao.DaoMs;
import com.app.model.NoteModel;
import com.app.utils.ToastUtils;


@ContentView(R.layout.activity_note_detail)
public class NoteDetailActivity extends ActionBarActivity {
	
	@ViewInject(R.id.edt_note_title)
	private EditText edt_title;
	@ViewInject(R.id.edt_note_content)
	private EditText edt_content;
	@ViewInject(R.id.bt_alter_note)
	private TextView btn_alter;
	@ViewInject(R.id.bt_delete_note)
	private TextView btn_del;
	
	private int current_id = 0;
	
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        try {
			initText();
		} catch (DbException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
        
        setOnclickListener();
        
    }
    
    
    
    
    
    private void initText() throws DbException{
    	
    	Intent it = getIntent();
    	int id = it.getIntExtra("id",0);
    	current_id = id;
    	
    	NoteModel noteModel = DaoMs.getInstance().dbNoteFind(id).get(0);
    	
    	this.edt_title.setText(noteModel.getTitle());
    	this.edt_content.setText(noteModel.getContent());
    	
    	
    	
    	
    	
    }
    
    
    private void setOnclickListener(){
    	this.btn_alter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Auto-generated method stub
				try {
					DaoMs.getInstance().dbNoteUpdate(new NoteModel(current_id,
							edt_title.getText().toString().trim(),edt_content.getText().toString().trim(),
							new Date()
							));
					ToastUtils.makeLongText("修改成功", getApplicationContext());
					finish();
				} catch (DbException e) {
					//Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
    	
    	this.btn_del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//Auto-generated method stub
				try {
					DaoMs.getInstance().dbNoteDel(current_id);
					ToastUtils.makeLongText("删除成功", getApplicationContext());
					finish();
				} catch (DbException e) {
					//Auto-generated catch block
					e.printStackTrace();
				}
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
