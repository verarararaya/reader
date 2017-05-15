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
public class NoteAddActivity extends ActionBarActivity {
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        setOnclickListener();
        
    }
    
    
    
    
    
    private void initText() throws DbException{
    	this.btn_alter.setText("Add notes");
    	this.btn_del.setVisibility(View.GONE);
        	
    }
    
    
    private void setOnclickListener(){
    	this.btn_alter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					DaoMs.getInstance().dbNoteAdd(edt_title.getText().toString().trim(),edt_content.getText().toString().trim());
					ToastUtils.makeLongText("Add successfully", getApplicationContext());
					finish();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
