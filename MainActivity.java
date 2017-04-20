package com.app.activity;

import org.xutils.x;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import com.app.activity.book.BookListActivity;
import com.app.activity.setting.SettingActivity;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;


@ContentView(R.layout.activity_main_page)
public class MainActivity extends ActionBarActivity {
	
	@ViewInject(R.id.main_linear_booklist)
	
	private LinearLayout main_linear_booklist;
	
	@ViewInject(R.id.linear_notems)
	private LinearLayout main_linear_notems;
	
	@ViewInject(R.id.linear_setting)
	private LinearLayout main_linear_setting;
	
	@ViewInject(R.id.linear_logout)
	private LinearLayout main_linear_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        setOnclickListener();
    }
    
    
    private void setOnclickListener(){
    	this.main_linear_booklist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getApplicationContext(),BookListActivity.class));
			}
		});
    	
    	
    	this.main_linear_notems.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
			}
		});
    	this.main_linear_setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(getApplicationContext(),SettingActivity.class));
				
			}
		});
    	
    	this.main_linear_logout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
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
