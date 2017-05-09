package com.app.activity.setting;

import org.xutils.x;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import com.app.activity.R;
import com.app.dao.DaoMs;
import com.app.utils.ToastUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;


@ContentView(R.layout.activity_setting)
public class SettingActivity extends Activity {
	
	@ViewInject(R.id.edt_textSize)
	private EditText edt_textSize;
	
	@ViewInject(R.id.spinner_state)
	private Spinner spinner_state;
	
	@ViewInject(R.id.btn_submit_upd)
	private TextView btn_submit_upd;
	
	private int textSize;
	
	private int type;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		x.view().inject(this);
		initData();
		setOnListener();
		
		
	}
	
	private void initData(){
		try {
			int textSize = DaoMs.getInstance().dbFind().getTextSize();
			int type = DaoMs.getInstance().dbFind().getType();
			
			edt_textSize.setText(textSize+"");
			spinner_state.setSelection(type);
			
			
		} catch (DbException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	private void setOnListener(){
		
		spinner_state.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				type = arg2;
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		this.btn_submit_upd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				textSize = Integer.parseInt(edt_textSize.getText().toString().trim());
				try {
					DaoMs.getInstance().dbAdd(textSize, type);
					ToastUtils.makeLongText("change successfully", getApplicationContext());
					finish();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
								
				
			}
		});
		
		
		
	}
	
	

}
